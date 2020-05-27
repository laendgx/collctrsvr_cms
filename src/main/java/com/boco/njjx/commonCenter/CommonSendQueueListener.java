package com.boco.njjx.commonCenter;

import com.boco.njjx.cmsprotocolBody.Playlist;
import com.boco.njjx.constant.ConstVarient;
import com.boco.njjx.driver.TDriver;
import com.boco.njjx.driver.TDriverList;
import com.boco.njjx.protocolBody.*;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RabbitListener(queues = "${revQueueName}") //@RabbitListener(queues = "${sendQueueName}")  //
public class CommonSendQueueListener {
    private static final Logger logger= LoggerFactory.getLogger(CommonSendQueueListener.class);

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Autowired
    private Environment env;

    //json字符串与对象之间的转换
    public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr,
                    obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @RabbitHandler
    public void process(String revdatabody) {
        try {
            Protocolbody revprotocolbody =  (Protocolbody)JSONToObj(revdatabody,Protocolbody.class);
            String InfoTypeRev=revprotocolbody.getInfoType();
            switch (InfoTypeRev)
            {
                case InfoType.MSG_CMD_CMS:
                    SendCmsData(revprotocolbody);
                    break;
                default :
                    System.out.println("无效处理数据-->" + revdatabody);
            }
        }catch ( Exception e) {
            logger.error("数据包转发异常"+e.toString());
        }
    }

    public void SendCmsData(Protocolbody revprotocolbody) {
        try {
            TDriver driver = TDriverList.getInstance().gDriverMap.get(revprotocolbody.getSubPackage().getDevId());
            Integer sdf = driver.FStatus;
            if (sdf != ConstVarient.COMM_STATUS_Connected) {
                InvalidDevBack(revprotocolbody,2);//设备通讯中断反馈
            }
            if (driver == null) {
                InvalidDevBack(revprotocolbody,1);//无效设备反馈
            } else {
                DevVarInfo DevVarInfotemp = (DevVarInfo) (revprotocolbody.getSubPackage().getDevVarInfoList().get(0));

                System.out.println("发送数据" + DevVarInfotemp.getDevvartypeid() + "-->" + DevVarInfotemp.getDevvarvalue());
                driver.sendData(revprotocolbody.getBusinessno(), DevVarInfotemp);
            }
        } catch (Exception e) {
            System.out.println("数据发送异常" + e.toString());
        }
    }

    /**
     * 无效设备id反馈
     */
    public void InvalidDevBack(Protocolbody revprotocolbody,int DevStatus) {
        try {
            //下面为发送成功反馈
            String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Protocolbody Protocolbodytemp = new Protocolbody();
            Protocolbodytemp.setBusinessno(revprotocolbody.getBusinessno());
            Protocolbodytemp.setInfoType(revprotocolbody.getInfoType());
            Identity Identitytemp = new Identity();
            Identitytemp.setSourceId("collctrsvr_cms");
            Identitytemp.setTargetId("jkcommctrsvr");
            Identitytemp.setCreateTime(curTime);
            Protocolbodytemp.setIdentity(Identitytemp);
            Protocolbodytemp.setSubPackage(revprotocolbody.getSubPackage());
            ReturnState returnState = new ReturnState();
            returnState.setReturnCode(ReturnCode.ReturnCode_unknown);
            switch (DevStatus) {
                case 1:
                    returnState.setReturnMessage("采集服务无此设备id: " + revprotocolbody.getSubPackage().getDevId());
                case 2:
                    returnState.setReturnMessage("设备通讯中断" + revprotocolbody.getSubPackage().getDevId());
            }
            Protocolbodytemp.setReturnState(returnState);
            JSONObject object = JSONObject.fromObject(Protocolbodytemp);
            String jsonstr = object.toString();

            SendRabbitmqQueue(env.getProperty("exchangeName"), env.getProperty("sendQueueroutingkey"), jsonstr);


        } catch (Exception e) {
            System.out.println("数据发送异常" + e.toString());
        }
    }

    /**
     * rabbitmq通讯发送接口
     */
    public void  SendRabbitmqQueue(String Exchange,String RoutingKey,String jsonstr) {
        try {
            if (jsonstr == null || jsonstr.equals("")) {
                System.out.println("SendRabbitmq数据发送不能为空， " + "\n" + "Exchange-->" + Exchange +
                        "RoutingKey-->" + RoutingKey + "\n" + jsonstr);
                return;
            }
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(Exchange);
            rabbitTemplate.setRoutingKey(RoutingKey);
            rabbitTemplate.convertAndSend(jsonstr);
            System.out.println("SendRabbitmq: " + "\n" + "Exchange-->" + Exchange + "   " +
                    "RoutingKey-->" + RoutingKey + "\n" + jsonstr);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
