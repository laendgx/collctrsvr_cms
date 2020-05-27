package com.boco.njjx.commconfig;

import com.boco.njjx.driver.TDriverVarInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service("devVarCollInfoDataServiceImpl")
public class DevVarCollInfoDataServiceImpl implements DevVarCollInfoDataService {
    @Override
    public List<DevVarCollInfo> getlistDevVarInfo() throws Exception {
        //读取Resource目录下的XML文件
        Resource resource = new ClassPathResource("./static/devVarListConfig.xml");
        //利用输入流获取XML文件内容
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "GBK"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        //XML转为JAVA对象
        DevVarCollInfoList devVarInfoList = (DevVarCollInfoList) XmlBuilder.xmlStrToObject(DevVarCollInfoList.class, buffer.toString());
        return devVarInfoList.getDevVarCollInfoList();
    }

    @Override
    public List<DevVarCollInfo> getCurDevVarCollInfo(String devTypeid)  throws Exception
    {
        List<DevVarCollInfo> result=new ArrayList<>();

        //读取Resource目录下的XML文件
        Resource resource = new ClassPathResource("./static/devVarListConfig.xml");
        //利用输入流获取XML文件内容
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "GBK"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        //XML转为对象
        DevVarCollInfoList DevVarCollInfoList = (DevVarCollInfoList) XmlBuilder.xmlStrToObject(DevVarCollInfoList.class, buffer.toString());
        List<DevVarCollInfo> DevVarCollInfos=DevVarCollInfoList.getDevVarCollInfoList();
        for (int k = 0; k < DevVarCollInfos.size(); k++) {
            if(DevVarCollInfos.get(k).getDwdevTypeid().equals(Integer.valueOf(devTypeid)))
            {
                result.add(DevVarCollInfos.get(k));
            }

        }
        return result;
    }

}
