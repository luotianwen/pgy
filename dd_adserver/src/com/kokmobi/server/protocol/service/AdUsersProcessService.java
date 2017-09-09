package com.kokmobi.server.protocol.service;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.AdUsersProcessResp;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

public interface AdUsersProcessService {
	public AdUserInfo parseRequest(HttpServletRequest req) throws Exception;	
	public AdUsersProcessResp process(SdkService sdkService, AreaService areaService, AdUserInfo userInfo);
}
