package com.kokmobi.server.protocol.service;

import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.SdkService;

import javax.servlet.http.HttpServletRequest;

public interface GetSilentAdListProcessService {
	GetAdListResp process(GetAdListReq req);
}
