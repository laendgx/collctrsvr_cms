package com.boco.njjx.commconfig;

import java.util.List;

public interface DevcommInfoDataService {
    List<DevcommInfo> listDevcommInfo() throws Exception;
    DevcommInfo getCurDevcommInfo(String devid)  throws Exception;
}
