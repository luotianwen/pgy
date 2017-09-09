package com.kokmobi.server.protocol.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.kokmobi.server.util.UpYun;
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
import com.kokmobi.server.service.SdkService;

import net.sf.json.JSONObject;

public class GetPluginInfoProcessServiceImpl implements GetCommonInfoProcessService {
	private static Log logger = LogFactory.getLog(GetPluginInfoProcessServiceImpl.class);
	
	@Override
	public JSONObject parseRequest(HttpServletRequest req) throws Exception {
		JSONObject jobj = new JSONObject();
		
		jobj.put("projectId", ProtocolUtil.getReqPara(req, "coo_id"));	//项目id
		jobj.put("version", ProtocolUtil.getReqPara(req, "version"));	//插件版本
		return jobj;
	}

	@Override
	public JSONObject process(SdkService sdkService, AreaService areaService,
			JSONObject req) {
		JSONObject resp = new JSONObject();
		if ((ProtocolUtil.isNullOrEmpty(req.getString("projectId"))) 
	    		|| (ProtocolUtil.isNullOrEmpty(req.getString("version"))) 
	    		) {
	      resp.put("state", 0);
	      resp.put("ydstatues", 0);
	      resp.put("isTablePlaqueDown", 0);
	      return resp;
	    }
		if (!ProtocolUtil.isIntegers(req.getString("projectId"))) {
			resp.put("state", 0);
			resp.put("ydstatues", 0);
			resp.put("isTablePlaqueDown", 0);
			return resp;
		}
		try{
			String projectId = req.getString("projectId");
			// 获取国家id
			
			Country c = CacheInfoUtil.getCountry(areaService,req.getString("ipAddr"));//areaService.getCountry();
			if(c == null || c.getId() == 183) {
				logger.error(String.format("error: can not find country: %s", req.getString("ipAddr")));
				resp.put("state", 0);
				resp.put("ydstatues", 0);
				resp.put("isTablePlaqueDown", 0);
				return resp;
			}
			AdProjectSetting projectSetting = CacheInfoUtil.getAdProjectSetting(sdkService, projectId);
	    	if(projectSetting == null) {
				logger.error(String.format("error: can not find project setting with id: %s", projectId));
				resp.put("ydstatues", 0);
				resp.put("isTablePlaqueDown", 0);		        
			}
	    	else {
	    		resp.put("ydstatues", projectSetting.getIsopen() == Constants.STATUS_YES?1:0);
	    		resp.put("isTablePlaqueDown", projectSetting.getIsTablePlaqueDown() == Constants.STATUS_YES?1:0);
	    	}
	    	//TODO：获取引导图片，暂时无用
	    	
	    	int version = 0;
	    	try{
	    		version = Integer.parseInt(req.getString("version"));
	    	}
	    	catch(Exception e) {
	    		logger.error(String.format("error: invalid version:%s.", req.getString("version")));
	    	}
	    	AdPluginInfo pluginInfo = CacheInfoUtil.getPluginInfo(sdkService, version);
	    	if(pluginInfo != null) {
	    		resp.put("state", 1);
	    		resp.put("apkid", pluginInfo.getApkid());
	    		resp.put("version", pluginInfo.getVersions());
	    		resp.put("packagename", pluginInfo.getPackagename());
	    		if(pluginInfo.getIsoutdownload() == Constants.STATUS_YES) {
	    			resp.put("apkurl", pluginInfo.getWwwurl());
	    		}
	    		else {
					String attachmentPath = "/" + pluginInfo.getAttachmentPath();
					resp.put("apkurl", String.format("%s%s%s", Constants.BASE_APK_URL_PATH, attachmentPath, UpYun.getPath(attachmentPath, null)));
				}
	    	}
	    	else {
	    		logger.error(String.format("error: can not find plugin with version: %s", version));
	    		resp.put("state", 0);
	    	}
	    	return resp;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			resp.put("state", 0);
			resp.put("ydstatues", 0);
			resp.put("isTablePlaqueDown", 0);
			return resp;
		}
		
	}

}
