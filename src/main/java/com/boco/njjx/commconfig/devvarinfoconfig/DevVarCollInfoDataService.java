package com.boco.njjx.commconfig.devvarinfoconfig;

import java.util.List;

public interface DevVarCollInfoDataService {
    List<DevVarCollInfo> getListDevVarInfo() throws Exception;
    List<DevVarCollInfo>  getCurDevVarCollInfo(String devTypeid)  throws Exception;
}
