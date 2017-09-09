package com.kokmobi.server.protocol.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.AdPluginInfo;
import com.kokmobi.server.bean.AdProjectSetting;
import com.kokmobi.server.bean.Country;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.GetCommonInfoProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

import net.sf.json.JSONObject;

public class GetAdjustKeyServiceImpl implements GetCommonInfoProcessService {
	private static Log logger = LogFactory.getLog(GetAdjustKeyServiceImpl.class);
	@Override
	public JSONObject parseRequest(HttpServletRequest req) throws Exception {
		JSONObject jobj = new JSONObject();
		
		jobj.put("pname", ProtocolUtil.getReqPara(req, "pname"));	//adjust channel id
//		jobj.put("pname", ProtocolUtil.getReqPara(req, "pname"));	//adjust channel id
		return jobj;
	}

	@Override
	public JSONObject process(SdkService sdkService, AreaService areaService,
			JSONObject req) {
		JSONObject resp = new JSONObject();
		if (ProtocolUtil.isNullOrEmpty(req.getString("pname"))) {
	      resp.put("status", 0);
	      resp.put("key", "");
	      return resp;
	    }
		try{
			String key = CacheInfoUtil.getAdjustKey(sdkService, req.getString("pname"));
	    	if(key == null) {
				logger.error(String.format("error: can not find adjust key with id: %s", req.getString("pname")));
				 resp.put("status", 0);
			     resp.put("key", "");		        
			}
	    	else {
	    		resp.put("status", 1);
	    		resp.put("key", key);
	    	}	    	
	    	return resp;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			resp.put("status", 0);
		    resp.put("key", "");	
			return resp;
		}
	}
}
