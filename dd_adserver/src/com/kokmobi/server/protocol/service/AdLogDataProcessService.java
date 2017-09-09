package com.kokmobi.server.protocol.service;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.bean.AdLogReq;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

public interface AdLogDataProcessService {
	public AdLogReq parseRequest(HttpServletRequest req) throws Exception;	
	public void process(SdkService sdkService, AreaService areaService, AdLogReq req);
}
