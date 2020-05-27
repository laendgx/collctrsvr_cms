package com.boco.njjx.commconfig;

import com.boco.njjx.driver.TDriverVarInfo;

import java.util.List;

public interface DevVarCollInfoDataService {
    List<DevVarCollInfo> getlistDevVarInfo() throws Exception;
    List<DevVarCollInfo>  getCurDevVarCollInfo(String devTypeid)  throws Exception;
}
