package com.kokmobi.server.protocol.service;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

public interface GetAdListProcessService {

	public GetAdListResp process(SdkService sdkService, AreaService areaService, GetAdListReq req);

	GetAdListResp process(GetAdListReq req);
}
