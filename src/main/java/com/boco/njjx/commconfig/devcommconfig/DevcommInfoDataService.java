package com.boco.njjx.commconfig.devcommconfig;

import java.util.List;

public interface DevcommInfoDataService {
    List<DevcommInfo> getDevcommInfoList() throws Exception;
    DevcommInfo getCurDevcommInfo(String devid)  throws Exception;
}
