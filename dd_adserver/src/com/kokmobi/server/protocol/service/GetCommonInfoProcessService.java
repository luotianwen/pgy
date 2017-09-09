package com.kokmobi.server.protocol.service;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

import net.sf.json.JSONObject;

public interface GetCommonInfoProcessService {
	public JSONObject parseRequest(HttpServletRequest req) throws Exception;	
	public JSONObject process(SdkService sdkService, AreaService areaService, JSONObject req);
}
