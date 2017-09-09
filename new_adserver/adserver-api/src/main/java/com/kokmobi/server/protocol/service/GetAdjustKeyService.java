package com.kokmobi.server.protocol.service;

import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.SdkService;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface GetAdjustKeyService {
	String process(String req);
}
