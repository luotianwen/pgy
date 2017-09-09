package com.kokmobi.server.protocol.service.impl;


import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.AdLogDataProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.servlet.util.CompatibleUtil;
import com.kokmobi.server.util.CalendarFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AdLogDataProcessServiceImpl implements AdLogDataProcessService {

	private SdkService sdkService;
	private AreaService areaService;

	private static Log logger = LogFactory.getLog(AdLogDataProcessServiceImpl.class);
	
	@Override
	public AdLogReq parseRequest(HttpServletRequest req) throws Exception {
		AdLogReq reqInfo = new AdLogReq();

		 
		String xmodel = ProtocolUtil.getReqPara(req,"xmodel");
		String coo_id = ProtocolUtil.getReqPara(req,"coo_id");
		String xcoo_id = ProtocolUtil.getReqPara(req,"xc_coo_id");
		String imei = ProtocolUtil.getReqPara(req,"imei");
		String channelId = ProtocolUtil.getReqPara(req,"channelId");
		String xversion = ProtocolUtil.getReqPara(req,"xversion");
		String sdkversion = ProtocolUtil.getReqPara(req,"sdkversion");
		String infors = ProtocolUtil.getReqPara(req,"infors");

	    if("1".equals(sdkversion) || "1.0".equals(sdkversion)) {
	    	sdkversion = "1";
	    }
	    JSONObject inforoot = JSONObject.fromObject(infors);
        JSONArray pkgs = inforoot.getJSONArray("infors");
        List<AdLogPackage> logPkgs = new ArrayList<AdLogPackage>();
        for(int i=0; i<pkgs.size();i++) {
        	JSONObject pkg = pkgs.getJSONObject(i);
        	AdLogPackage logPkg = new AdLogPackage();
        	logPkg.setPkgid(pkg.getString("pkgid"));        	
        	JSONArray logs = pkg.getJSONArray("infors");
        	List<AdLogInfo> logInfos = new ArrayList<AdLogInfo>();
        	for(int j=0; j<logs.size(); j++) {
        		AdLogInfo log = new AdLogInfo();
        		JSONObject logObj = logs.getJSONObject(j);
        		log.setApkId(logObj.getInt("apkid"));
        		log.setSdkType(logObj.getInt("sdkType")); //广告类型
        		log.setDataType(logObj.getInt("dataType"));//数据类型
        		String sdk = logObj.getString("sdk");	//插件类型

//        		int sdkStyle = Constants.SDKSTYLE_SDK;
//        	    if("1".equals(sdkversion)) {
//        	    	if(!ProtocolUtil.isNullOrEmpty(xcoo_id) && !"0".equals(xcoo_id)){
//        	    		sdkStyle = Constants.SDKSTYLE_DOWN;
//        	    	} //
//        	    }
//        	    else {
//        	    	if("3".equals(sdk)) {
//        	    		sdkStyle = Constants.SDKSTYLE_GUIDE;
//        	    	}
//        	    	if("4".equals(sdk)) {
//        	    		sdkStyle = Constants.SDKSTYLE_DOWN;
//        	    	}
//        	    	if("5".equals(sdk)) {
//        	    		sdkStyle = Constants.SDKSTYLE_SILENCE;
//        	    	}
//        	    }
				int sdkType = CompatibleUtil.getSDKType(sdk, sdkversion, xcoo_id);
        	    log.setSdkStyle(sdkType);
        	    
        		logInfos.add(log);
        	}
        	logPkg.setInfors(logInfos);        	
        	logPkgs.add(logPkg);
        }
        
	    reqInfo.setCoo_id(coo_id);
	    reqInfo.setChannelId(channelId);
	    reqInfo.setImei(imei);
	    reqInfo.setInfors(logPkgs);
	    reqInfo.setSdkversion(sdkversion);
	    reqInfo.setXc_coo_id(ProtocolUtil.isNullOrEmpty(xcoo_id) ? "0" : xcoo_id);
	    reqInfo.setXmodel(xmodel);
	    reqInfo.setXversion(xversion);
	    
	    JSONObject j = JSONObject.fromObject(reqInfo);
	    logger.info(String.format("got a log: %s.", j.toString()));
		return reqInfo;
	}

	@Override
	public void process(AdLogReq req) {
		process(sdkService, areaService, req);
	}

	@Override
	public void process(SdkService sdkService, AreaService areaService, AdLogReq req) {
		// 判断该pkgId是否处理过，没处理过就塞入待保存队列
		try{
			if ((ProtocolUtil.isNullOrEmpty(req.getXc_coo_id()))
		    		|| (ProtocolUtil.isNullOrEmpty(req.getChannelId()))
		    		|| (ProtocolUtil.isNullOrEmpty(req.getCoo_id()))
		    		|| (ProtocolUtil.isNullOrEmpty(req.getImei())
		    		|| req.getInfors().size() == 0)) {
				logger.error(String.format("log data error:%s", req.getIpaddr()));
				return ;
		    }
			if (!ProtocolUtil.isIntegers(req.getCoo_id(), req.getXc_coo_id(), req.getChannelId(), req.getSdkversion())) {
				logger.error(String.format("log data is no int error:%s", req.getIpaddr()));
				return;
			}

			String imei=req.getImei();
		/*	String black=String.format(Constants.KEY_BLACKLIST_USER, imei);
			//黑名单
			if(!ProtocolUtil.isNullOrEmpty(RedisTool.get(black))) {
				return ;
			}

			String backcount=String.format(Constants.KEY_BACK_COUNT, imei);
			String backshPkg=RedisTool.get(backcount);

			if(ProtocolUtil.isNullOrEmpty(backshPkg)) {
				backshPkg="1";
				RedisTool.set(backcount,"1");
				RedisTool.expire(backcount, Constants.KEY_BACK_COUNT_EXPIRE);
			}
			 else {
				RedisTool.incr(backcount);
			}
			int numInt = Integer.parseInt(backshPkg);
			//检测数据超过50认为异常
			if(numInt>50) {
				RedisTool.set(black,"1");
				RedisTool.expire(black, Constants.KEY_BLACKLIST_USER_EXPIRE);
				logger.error(String.format("error: is black id: %s", imei));
				return ;
			}*/



			AdProjectInfo project = CacheInfoUtil.getAdProjectInfo(sdkService, req.getCoo_id());
			/*if(project == null) {
				logger.error(String.format("error: can not find project with id: %s", req.getCoo_id()));
		        return;
			}*/
			// 获取国家id和国家等级
			int countryId = -1;
			int countryLevel = 4;
			Country c = CacheInfoUtil.getCountry(areaService,req.getIpaddr());
			if(c != null) {
				countryId = c.getId();
				CountryLevel cl = CacheInfoUtil.getCountryLevel(sdkService, req.getCoo_id(), c.getId());
				if(cl != null) {
					countryLevel = cl.getLevel();
				}
			}
			req.setCou(countryId);
			req.setCountryLevel(countryLevel);
			req.setCdate(new Date());

			for(AdLogPackage pkg : req.getInfors()){
				 String pkey = String.format(Constants.KEY_LOG_PKG_INFO, pkg.getPkgid());
				String strPkg = RedisTool.get(pkey);
				if(ProtocolUtil.isNullOrEmpty(strPkg)) {
				 	RedisTool.set(pkey, pkg.getPkgid());
				 	RedisTool.expire(pkey, Constants.KEY_LOG_PKG_INFO_EXPIRE); 	//expired after 3 days.

//					List<String> toList = new ArrayList<String>();

					for(AdLogInfo logInfo : pkg.getInfors()) {



						AdLogData log = new AdLogData();
						log.setApkid(logInfo.getApkId());
						log.setCdate(CalendarFormat.getDateString(req.getCdate().getTime()));
						log.setChannelid(req.getChannelId());
						log.setCoo_id(req.getCoo_id());
						log.setCou(req.getCou());
						log.setCountryLevel(req.getCountryLevel());
						log.setImei(imei);
						log.setPkgid(pkg.getPkgid());
						switch(logInfo.getSdkType()) {
						case 1:
							log.setSdk(Constants.ADTYPE_PUSH);
							break;
						case 2:
							log.setSdk(Constants.ADTYPE_POP);
							break;
						case 3:
							log.setSdk(Constants.ADTYPE_GUIDE);
							break;
						case 4:
							log.setSdk(Constants.ADTYPE_DOWN);
							break;
						case 5:
							log.setSdk(Constants.ADTYPE_SILENCE);
							break;
						}
						if(logInfo.getDataType() == 8){
							log.setSdk(Constants.ADTYPE_SILENCE);	//
						}

						log.setSdkstyle(logInfo.getSdkStyle());
						log.setSdkversion(req.getSdkversion());
						log.setXc_coo_id(req.getXc_coo_id());

						JSONObject jsObj = JSONObject.fromObject(log);
						String shkey,shPkg;

						//dataType：展示，点击，下载成功，安装成功，引导成功，下沉成功,静默安装 激活成功（1,2,3,4,5,6,7，8）
						logger.info(String.format("datatype:%s, push log to redis:%s.", logInfo.getDataType(), jsObj.toString()));
						if(1==logInfo.getDataType()){
							   shkey = String.format(Constants.KEY_AD_SHOW, imei,logInfo.getApkId());
							  shPkg = RedisTool.get(shkey);
							if(ProtocolUtil.isNullOrEmpty(shPkg)) {
								RedisTool.set(shkey, "1");
								RedisTool.expire(shkey, Constants.KEY_AD_SHOW_EXPIRE);
								RedisTool.lpush(Constants.KEY_LOG_SHOW_TOSAVE, jsObj.toString());
							 }

						}

						else if(3==logInfo.getDataType()){
							  shkey = String.format(Constants.KEY_AD_DOWNLOAD, imei,logInfo.getApkId());
							  shPkg = RedisTool.get(shkey);
							if(ProtocolUtil.isNullOrEmpty(shPkg)) {
								RedisTool.set(shkey, "1");
								RedisTool.expire(shkey, Constants.KEY_AD_DOWNLOAD_EXPIRE);
								RedisTool.lpush(Constants.KEY_LOG_DOWNLOAD_TOSAVE, jsObj.toString());
							 }

						}


						else if(4==logInfo.getDataType()||5==logInfo.getDataType()||6==logInfo.getDataType()||7==logInfo.getDataType()){
							  shkey = String.format(Constants.KEY_AD_INSTALL, imei,logInfo.getApkId());
							  shPkg = RedisTool.get(shkey);
							if(ProtocolUtil.isNullOrEmpty(shPkg)) {
								RedisTool.set(shkey, "1");
								RedisTool.expire(shkey, Constants.KEY_AD_INSTALL_EXPIRE);
								RedisTool.lpush(Constants.KEY_LOG_INSTALLED_TOSAVE, jsObj.toString());
							 }
						}


						else if(8==logInfo.getDataType()){
							   shkey = String.format(Constants.KEY_AD_ACTIVATE, imei,logInfo.getApkId());
							  shPkg = RedisTool.get(shkey);
							if(ProtocolUtil.isNullOrEmpty(shPkg)) {
								RedisTool.set(shkey, "1");
								RedisTool.expire(shkey, Constants.KEY_AD_ACTIVATE_EXPIRE);
								RedisTool.lpush(Constants.KEY_LOG_ACTIVATE_TOSAVE, jsObj.toString());
							 }
						}
						else{
							 shkey = String.format(Constants.KEY_AD_CLICK, imei,logInfo.getApkId());
							  shPkg = RedisTool.get(shkey);
							if(ProtocolUtil.isNullOrEmpty(shPkg)) {
								RedisTool.set(shkey, "1");
								RedisTool.expire(shkey, Constants.KEY_AD_CLICK_EXPIRE);
								RedisTool.lpush(Constants.KEY_LOG_CLICK_TOSAVE, jsObj.toString());
							 }
						}



						/*switch(logInfo.getDataType()) {
						case 1:
							RedisTool.lpush(Constants.KEY_LOG_SHOW_TOSAVE, jsObj.toString());
							break;
						case 2:
							RedisTool.lpush(Constants.KEY_LOG_CLICK_TOSAVE, jsObj.toString());
							break;
						case 3:
							RedisTool.lpush(Constants.KEY_LOG_DOWNLOAD_TOSAVE, jsObj.toString());
							break;

						case 4:
							RedisTool.lpush(Constants.KEY_LOG_INSTALLED_TOSAVE, jsObj.toString());
							break;
						case 5:
							RedisTool.lpush(Constants.KEY_LOG_INSTALLED_TOSAVE, jsObj.toString());
							break;
						case 6:
							RedisTool.lpush(Constants.KEY_LOG_INSTALLED_TOSAVE, jsObj.toString());
							break;
						case 7:
							RedisTool.lpush(Constants.KEY_LOG_INSTALLED_TOSAVE, jsObj.toString());
							break;

						case 8:
							RedisTool.lpush(Constants.KEY_LOG_ACTIVATE_TOSAVE, jsObj.toString());
							break;
						default:
							RedisTool.lpush(Constants.KEY_LOG_CLICK_TOSAVE, jsObj.toString());
							break;
						}*/
					}

				 }
				else {
					logger.error(String.format("log error: the package with id %s %s already done.", pkg.getPkgid(),req.getCoo_id()));
					continue;
				}
			}

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setSdkService(SdkService sdkService) {
		this.sdkService = sdkService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
}
