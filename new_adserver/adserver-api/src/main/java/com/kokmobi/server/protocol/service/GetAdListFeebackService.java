package com.kokmobi.server.protocol.service;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.bean.GetAdListFeebackReq;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

public interface GetAdListFeebackService {
	public GetAdListFeebackReq parseRequest(HttpServletRequest req) throws Exception;	
	public void process(GetAdListFeebackReq req);
}
