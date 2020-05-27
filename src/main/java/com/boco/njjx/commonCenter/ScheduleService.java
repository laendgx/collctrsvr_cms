package com.boco.njjx.commonCenter;
import com.boco.njjx.driver.TDriver;
import com.boco.njjx.driver.TDriverList;
import com.boco.njjx.protocolBody.*;
import com.boco.njjx.service.CollDrvService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@EnableScheduling
public class ScheduleService {

    private static final Logger logger= LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Autowired
    private Environment env;

    // 每30秒调用一次
    //@Scheduled(cron = "0/20 * * * * ?")// corn语句 没20秒调用一次
    //@Scheduled(cron = "0 0/1 * * * ?") // corn语句  每1分钟调用一次
    @Async
    public void s1() {
        //System.out.println("ScheduleService定时服务运行");
//        SendRabbitmqQueue(env.getProperty("exchangeName"),
//                env.getProperty("sendQueueroutingkey"), GetSendjsonstr());
        //测试设备通讯状态
//        for (Map.Entry<Integer, TDriver> entry : TDriverList.getInstance().gDriverMap.entrySet()) {
//            TDriver driver = entry.getValue();  //获取设备驱动信息
//            Integer sdf=driver.FStatus;
//            String sdfasd=entry.getKey().toString();
//            logger.error("设备通讯编码："+sdfasd+"---状态："+sdf.toString());
//        }
    }

    /**
     * 通讯协议包父类
     */
    public void  SendRabbitmqQueue(String Exchange,String RoutingKey,String jsonstr)
    {
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(Exchange);
            rabbitTemplate.setRoutingKey(RoutingKey);
            rabbitTemplate.convertAndSend(jsonstr);
            System.out.println("SendRabbitmq: "+"\n"+"Exchange-->" +Exchange+"   "+
                    "RoutingKey-->" +RoutingKey+ "\n"+jsonstr);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public  String GetSendjsonstr() {
        String Sendjsonstr = "";
        try {
            String businessno = "-1";
            String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Protocolbody Protocolbodytemp = new Protocolbody();
            Protocolbodytemp.setBusinessno(businessno);
            Identity Identitytemp = new Identity();
            Identitytemp.setCreateTime(curTime);
            Protocolbodytemp.setIdentity(Identitytemp);
            Protocolbodytemp.setInfoType(InfoType.MSG_DATA_VD);

            SubPackage subPackage = new SubPackage();
            subPackage.setOrgId("1101");
            subPackage.setDevId("24010001");
            subPackage.setCollCtrTime(curTime);

            List<DevVarInfo> DevVarInfolist = new ArrayList<>();
            DevVarInfo DevVarInfo_1 = new DevVarInfo();
            DevVarInfo_1.setDevvartypeid("240101");
            DevVarInfo_1.setDevvartypedesc("车流量");
            DevVarInfo_1.setDevvarvalue("3");
            DevVarInfo_1.setDevvargroupid("1");
            DevVarInfolist.add(DevVarInfo_1);

            DevVarInfo DevVarInfo_2 = new DevVarInfo();
            DevVarInfo_2.setDevvartypeid("240102");
            DevVarInfo_2.setDevvartypedesc("车速");
            DevVarInfo_2.setDevvarvalue("3");
            DevVarInfo_2.setDevvargroupid("1");
            DevVarInfolist.add(DevVarInfo_2);

            DevVarInfo DevVarInfo_3 = new DevVarInfo();
            DevVarInfo_3.setDevvartypeid("240103");
            DevVarInfo_3.setDevvartypedesc("占有率");
            DevVarInfo_3.setDevvarvalue("3");
            DevVarInfo_3.setDevvargroupid("1");
            DevVarInfolist.add(DevVarInfo_3);

            DevVarInfo DevVarInfo_4 = new DevVarInfo();
            DevVarInfo_4.setDevvartypeid("240101");
            DevVarInfo_4.setDevvartypedesc("车流量");
            DevVarInfo_4.setDevvarvalue("3");
            DevVarInfo_4.setDevvargroupid("2");
            DevVarInfolist.add(DevVarInfo_4);

            DevVarInfo DevVarInfo_5 = new DevVarInfo();
            DevVarInfo_5.setDevvartypeid("240102");
            DevVarInfo_5.setDevvartypedesc("车速");
            DevVarInfo_5.setDevvarvalue("3");
            DevVarInfo_5.setDevvargroupid("2");
            DevVarInfolist.add(DevVarInfo_5);

            DevVarInfo DevVarInfo_6 = new DevVarInfo();
            DevVarInfo_6.setDevvartypeid("240103");
            DevVarInfo_6.setDevvartypedesc("占有率");
            DevVarInfo_6.setDevvarvalue("3");
            DevVarInfo_6.setDevvargroupid("2");
            DevVarInfolist.add(DevVarInfo_6);
            subPackage.setDevVarInfoList(DevVarInfolist);
            Protocolbodytemp.setSubPackage(subPackage);

            JSONObject objecttemp = JSONObject.fromObject(Protocolbodytemp);
            Sendjsonstr = objecttemp.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("下发数据异常发生异常" + ex.toString());
        }
        return Sendjsonstr;
    }

}
