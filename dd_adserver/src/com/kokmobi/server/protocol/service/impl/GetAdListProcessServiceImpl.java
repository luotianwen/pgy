package com.kokmobi.server.protocol.service.impl;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.servlet.util.CompatibleUtil;
import com.kokmobi.server.servlet.util.TimeSpaceControllUtil;
import com.kokmobi.server.util.CalendarFormat;
import com.kokmobi.server.util.UpYun;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAdListProcessServiceImpl implements GetAdListProcessService {

	private static Log logger = LogFactory.getLog(GetAdListProcessServiceImpl.class);
	
	@Override
	public GetAdListReq parseRequest(HttpServletRequest req) throws Exception {
		long start = System.currentTimeMillis();

		
		String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
	    String xc_coo_id = ProtocolUtil.getReqPara(req, "xc_coo_id");
	    String imei = ProtocolUtil.getReqPara(req, "imei");
	    String channelId = ProtocolUtil.getReqPara(req, "channelId");
	    String sdk = ProtocolUtil.getReqPara(req, "sdk");
	    String language = ProtocolUtil.getReqPara(req, "language");
	    String internet = ProtocolUtil.getReqPara(req, "internet");
	    String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
	    String apkids = ProtocolUtil.getReqPara(req, "apkid");
	    String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
	    String xversion = ProtocolUtil.getReqPara(req, "xversion");
	    String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
	    String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
	    String xoperator = ProtocolUtil.getReqPara(req, "xoperator");

//		String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
//	    String xc_coo_id = ProtocolUtil.getReqPara(req, "xc_coo_id");
//	    String imei = ProtocolUtil.getReqPara(req, "imei");
//	    String channelId = ProtocolUtil.getReqPara(req, "channelId");
//	    String sdk = ProtocolUtil.getReqPara(req, "sdk");
//	    String language = ProtocolUtil.getReqPara(req, "language");
//	    String internet = ProtocolUtil.getReqPara(req, "internet");
//	    String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
//	    String apkids = ProtocolUtil.getReqPara(req, "apkid");
//	    String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
//	    String xversion = ProtocolUtil.getReqPara(req, "xversion");
//	    String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
//	    String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
//	    String xoperator = ProtocolUtil.getReqPara(req, "xoperator");
	    
//	    int sdkType = Constants.SDKSTYLE_SDK;
//	    if("1".equals(sdkversion) || "1.0".equals(sdkversion)) {
//	    	sdkversion = "1";
//	    	if(!ProtocolUtil.isNullOrEmpty(xc_coo_id) && !"0".equals(xc_coo_id)){
//	    		sdkType = Constants.SDKSTYLE_DOWN;
//	    	}
//	    }
//	    else {
//	    	if("3".equals(sdk)) {
//	    		sdkType = Constants.SDKSTYLE_GUIDE;
//	    	}
//	    	if("4".equals(sdk)) {
//	    		sdkType = Constants.SDKSTYLE_DOWN;
//	    	}
//	    	if("5".equals(sdk)) {
//	    		sdkType = Constants.SDKSTYLE_SILENCE;
//	    	}
//	    }
		int sdkType = CompatibleUtil.getSDKType(sdk, sdkversion, xc_coo_id);
		sdkversion = sdkversion.equals("1.0") ? "1" : sdkversion;

	    GetAdListReq reqInfo = new GetAdListReq();
	    reqInfo.setCoo_id(coo_id);
	    reqInfo.setImei(imei);
	    reqInfo.setXc_coo_id(ProtocolUtil.isNullOrEmpty(xc_coo_id) ? "0" : xc_coo_id);	    
	    reqInfo.setChannelId(channelId);
	    reqInfo.setLanguage(language);
	    reqInfo.setInternet(internet);
	    reqInfo.setApkid(apkids);
	    reqInfo.setXmodel(xmodel);
	    reqInfo.setXversion(xversion);	    
	    reqInfo.setXimsi(ximsi);
	    reqInfo.setXinternet(xinternet);
	    reqInfo.setXoperator(xoperator);
	    reqInfo.setSdkversion(Integer.parseInt(sdkversion));
	    reqInfo.setSdk(sdk);
	    reqInfo.setSdkStyle(sdkType);
	    
	    JSONObject j = JSONObject.fromObject(reqInfo);
	    logger.info(String.format("got a log: %s.", j.toString()));
	    logger.info("parse time:"+ (System.currentTimeMillis()-start) + "ms");
		return reqInfo;
	}

	@Override
	public GetAdListResp process(SdkService sdkService, AreaService areaService,
			GetAdListReq req) {
		long start = System.currentTimeMillis();
		GetAdListResp resp = new GetAdListResp();
		// TODO 根据国家下发对应的push和pop广告，是否可push和pop先检查项目的设置
		//TODO: 判断基本参数是否异常
		String projectId = req.getCoo_id();
//	    if(xcoo_id==null||xcoo_id.equals(""))
//	    	xcoo_id="10939";
	    if ((ProtocolUtil.isNullOrEmpty(req.getLanguage())) 
	    		|| (ProtocolUtil.isNullOrEmpty(req.getXc_coo_id())) 
	    		|| (ProtocolUtil.isNullOrEmpty(req.getChannelId())) 
	    		|| (ProtocolUtil.isNullOrEmpty(req.getCoo_id())) 
	    		|| (ProtocolUtil.isNullOrEmpty(req.getImei()))) {
	      resp.setIsExit(0);
	      return resp;
	    }
		if (!ProtocolUtil.isIntegers(req.getXc_coo_id(), req.getChannelId(), req.getCoo_id())) {
			logger.error("error: param is not int");
			resp.setIsExit(0);
			return resp;
		}

	    //黑名单
	    if(!ProtocolUtil.isNullOrEmpty(RedisTool.get(String.format(Constants.KEY_BLACKLIST_USER, req.getImei())))) {
	    	resp.setIsExit(0);
		    return resp;
	    }	  
	    logger.info("valid black time:"+ (System.currentTimeMillis()-start) + "ms");
	    start = System.currentTimeMillis();
	    // 加上访问时间间隔控制
		/*一个小时之内同一个项目用户请求广告超过4次，则该小时内不能再次请求*/
		String imei = req.getImei();
		boolean isInControll = TimeSpaceControllUtil.isAdListInControll(imei, req.getSdkStyle(), req.getCoo_id());
		if (false == isInControll) {
			logger.error(String.format("error: %s got ad list at %s, out of number:%s", imei,
					CalendarFormat.getCurrentDateTime(), String.valueOf(Constants.KEY_AD_LAST_SENT_NUM)));
			resp.setIsExit(0);
			return resp;
		}
//	    String lastKey = String.format(Constants.KEY_AD_LAST_SENT, req.getImei(), req.getSdkStyle(), req.getCoo_id());
//	    int rtimes = 0;
//	    String lastReq = CalendarFormat.getCurrentDateTime();
//	    try {
//	    	String tstr = RedisTool.get(lastKey);
//	    	if(!ProtocolUtil.isNullOrEmpty(tstr)) {
//	    		JSONObject tobj = JSONObject.fromObject(tstr);
//	    		rtimes = tobj.getInt("reqTimes");
//	    		lastReq = tobj.getString("lastTime");
//	    		Date lastd = CalendarFormat.getDateTime(lastReq, CalendarFormat.ymdhmsFormat);
//				/*这里逻辑应该不对  第一个限制条件是表示当前访问时间和前一次访问时间间隔小于一个小时 第二个限制条件表示累计访问次数大于200*/
//	    		if(((new Date()).getTime()/1000 - lastd.getTime()/1000)<60*60*1
//						&& rtimes>200) {
//	    			logger.error(String.format("error: %s got ad list at %s, total:%s", req.getImei(), lastReq, rtimes));
//	    			resp.setIsExit(0);
//	    		    return resp;
//	    		}
//	    	}
//	    }
//	    catch(Exception e) {
//	    	e.printStackTrace();
//	    }
	    logger.info("check last req time:"+ (System.currentTimeMillis()-start) + "ms");
	    start = System.currentTimeMillis();
		/*为什么在这里重新赋值projectId：一个项目对应多个插件*/
	    if (!"0".equals(req.getXc_coo_id())) {
			projectId = req.getXc_coo_id();
		}

		try {
	    	AdProjectSetting projectSetting = CacheInfoUtil.getAdProjectSetting(sdkService, projectId);
	    	if(projectSetting == null) {
				logger.error(String.format("error: can not find project setting with id: %s", projectId));
				resp.setIsExit(0);
		        return resp;
			}
			/*status 是否开启推送 isTablePlaque 是否开启插屏*/
	    	if(projectSetting.getStatus() == Constants.STATUS_NO 
	    			&& projectSetting.getIsTablePlaque() == Constants.STATUS_NO) {
	    		logger.error(String.format("error: project %s can not push or pop ad", projectId));
	    		resp.setIsExit(0);
			    return resp;
	    	}
	    	
	    	logger.info("check project Setting time:"+ (System.currentTimeMillis()-start) + "ms");
		    start = System.currentTimeMillis();
	    	// 获取国家id和国家等级
			int countryId = -1;
			int countryLevel = 4;
//			int countryId = 2;  //测试用的
//			int countryLevel = 1;
			Country c = CacheInfoUtil.getCountry(areaService,req.getIpaddr());
			logger.info("get country time:"+ (System.currentTimeMillis()-start) + "ms");
		    start = System.currentTimeMillis();
			if(c != null) {				
				countryId = c.getId();
				logger.info("get country id:"+c.getId());
				CountryLevel cl = CacheInfoUtil.getCountryLevel(sdkService, req.getCoo_id(), c.getId());
				if(cl != null) {
					countryLevel = cl.getLevel();
				}
			}
			logger.info(String.format("current coutry:%s of %s", countryId, req.getIpaddr()));
			logger.info("get country level time:"+ (System.currentTimeMillis()-start) + "ms");
		    start = System.currentTimeMillis();
		    
			req.setCou(countryId);
			req.setCountryLevel(countryLevel);
//			String pkgId = UUID.randomUUID().toString();
			String pkgId = CompatibleUtil.uuid();
			/*PkgId是用来做什么：用来标识下发数据的接收状况*/
			resp.setPkgId(pkgId);

			List<ApkAdRespInfo> dataList = new ArrayList<ApkAdRespInfo>();	//to cache for reply...
			
	    	if(projectSetting.getStatus() == Constants.STATUS_YES) {	    		
	    		List<ApkAdInfo> pushList = CacheInfoUtil.getApkAdList(sdkService, Constants.ADTYPE_PUSH, req, Constants.MAX_ADS);
	    		logger.info("get push ads time:"+ (System.currentTimeMillis()-start) + "ms");
			    start = System.currentTimeMillis();
	    		resp.setPushApkInfo(changeToJSONObject(pushList, Constants.ADTYPE_PUSH, dataList, req, pkgId));
	    		logger.info("set push ads time:"+ (System.currentTimeMillis()-start) + "ms");
			    start = System.currentTimeMillis();	    		
	    	}
	    	if(projectSetting.getIsTablePlaque() == Constants.STATUS_YES) {
	    		List<ApkAdInfo> popList = CacheInfoUtil.getApkAdList(sdkService, Constants.ADTYPE_POP, req, Constants.MAX_ADS);
	    		resp.setCpApkInfo(changeToJSONObject(popList, Constants.ADTYPE_POP, dataList, req, pkgId));
	    	}
	    	if(resp.getPushApkInfo().size()>0 || 
	    			resp.getCpApkInfo().size()>0) {
	    		resp.setIsExit(1);
	    		JSONArray jsarr = JSONArray.fromObject(dataList);	    		
	    		RedisTool.set(String.format(Constants.KEY_AD_SENT_PACKAGE, pkgId), jsarr.toString());
	    		RedisTool.expire(String.format(Constants.KEY_AD_SENT_PACKAGE, pkgId), Constants.KEY_AD_SENT_PACKAGE_EXPIRE);//expired after 48 hours
	    	}
	    	else {
	    		resp.setIsExit(0);
	    	}
//	    	JSONObject tobj = new JSONObject();
//	    	tobj.put("lastTime", lastReq);
//	    	tobj.put("reqTimes", ++rtimes);
//
//	    	RedisTool.set(lastKey, tobj.toString());
	    	resp.setLower(projectSetting.getLower());
	    	resp.setIsSilent(projectSetting.getIsCjPush()==Constants.STATUS_YES?1:0);
	    	resp.setTime(projectSetting.getTimss());
	    	resp.setIsDownLoad(projectSetting.getYdownLoad()==Constants.STATUS_YES?1:0);
	    	resp.setIsNotice(projectSetting.getIsNotice()==Constants.STATUS_YES?1:0);
	    	resp.setIsPops(projectSetting.getIsPops()==Constants.STATUS_YES?1:0);
	    	resp.setEffect(projectSetting.getIsReturnDebug()-10800300);	    	
	    	resp.setAdcount(projectSetting.getIsGame());
	    	resp.setEffectmethod(projectSetting.getIsCjTablePlaque() - 10800400);
	    	resp.setTimes(projectSetting.getTablePlaquelower());
	    	resp.setDelayTimes(projectSetting.getTablePlaquetimss());
	    	resp.setPushStatus(projectSetting.getStatus()==Constants.STATUS_YES?1:0);
	    	resp.setStatue(projectSetting.getIsTablePlaque()==Constants.STATUS_NO?1:0);
	    	
	    	return resp;
	    }
		catch(Exception ex) {
			ex.printStackTrace();
			resp.setIsExit(0);
		    return resp;
		}
	}
	private String getwww(){
		String www=Constants.BASE_APK_URL_PATH;
		String key="downloadUrl";
		String value=RedisTool.get(key);
		if(!ProtocolUtil.isNullOrEmpty(value)){
			www=value;
		}
		else {
			RedisTool.set(key, www);
		}
		return www;
	}
	private List<JSONObject> changeToJSONObject(List<ApkAdInfo> list, int adType, List<ApkAdRespInfo> dataList, GetAdListReq req, String pkgId) {
		List<JSONObject> arr = new ArrayList<JSONObject>();
		List<String> toLpushList = new ArrayList<String>();
		long start = System.currentTimeMillis();
		String separate = "/";  String iconStr, popImage, apkDownload;
		for(ApkAdInfo ad : list) {
			JSONObject o = new JSONObject();
			o.put("apkId", ad.getApkId());
			o.put("tracinglinkc", ad.getTracinglinkc());
			o.put("adtype", ad.getAdtype() - 100200);
			o.put("tracinglink", ad.getTracinglink());
			o.put("appName", ad.getAppName());
			o.put("pushText", ad.getPushText());
			o.put("intruduction", ad.getIntroduction());
			o.put("size", String.format("%s%s", ad.getSizes(), "M"));
			if(ad.getIsouticon() == Constants.STATUS_YES) {
				o.put("icon", ad.getOuticonwww());
			} else if(ad.getIcon() != null){
				iconStr = separate + ad.getIcon();
				o.put("icon", String.format("%s%s%s",getwww() , iconStr, UpYun.getPath(iconStr,null)));
			}
			else {
			    o.put("icon", "");
			}
//			if(adType == Constants.ADTYPE_POP){
			if(ad.getIsoutcptp() == Constants.STATUS_YES) {
				o.put("imageURL", ad.getOutcptpwww());
			}
			else if(ad.getPopImage2() != null){
				popImage = separate + ad.getPopImage2();
				o.put("imageURL", String.format("%s%s%s", getwww(), popImage,UpYun.getPath(popImage,null)));
			}
			else {
				o.put("imageURL", "");
			}
//			}
//			else {
//			    o.put("imageURL", "");
//			}
			if(ad.getIsOutDownload() == Constants.STATUS_YES) {
				o.put("apkdownloadURL", ad.getOutwww());
			}
			else {
				apkDownload = separate + ad.getApkDownloadUrl();
				o.put("apkdownloadURL", String.format("%s%s%s", getwww(), apkDownload, UpYun.getPath(apkDownload, null)));
			}

			o.put("dataOrSys", ad.getDataOrSys() - 8200800);
			o.put("retentionRate", ad.getRetentionRate());
			o.put("actionStatus", ad.getActionStatus() - 8200900);
			
			o.put("apkPackageName", ad.getApkPackageName());
			
			o.put("isNotification", ad.getIsNotification()==Constants.STATUS_YES?1:0);

			o.put("isInterface", ad.getIsInterface()==Constants.STATUS_YES?1:0);
			
			o.put("isGprsDownLoad", ad.getIsGprsDownLoad()==Constants.STATUS_YES?1:0);
			 if(ProtocolUtil.isNullOrEmpty(ad.getPassnote())){
	        	  o.put("md5", "ddl");
	          }
	          else{
	              o.put("md5", ad.getPassnote());
	          }
			 
			arr.add(o);
			
			Date cdate = new Date();
			ApkAdRespInfo data = new ApkAdRespInfo();
			data.setApkid(ad.getApkId());
			data.setCdate(CalendarFormat.getDateString(cdate.getTime()));
			data.setChannelid(req.getChannelId());
			data.setCoo_id(req.getCoo_id());
			data.setCou(req.getCou());
			data.setCountryLevel(req.getCountryLevel());
			data.setImei(req.getImei());
			data.setPkgid(pkgId);
			data.setPkgstatus(Constants.STATUS_NO);
			data.setSdk(adType);
			data.setSdkstyle(req.getSdkStyle());
			data.setSdkversion(String.format("%s", req.getSdkversion()));
			data.setXc_coo_id(req.getXc_coo_id());
			dataList.add(data);
			
			//set to saving queue
			JSONObject jsobj = JSONObject.fromObject(data);
		    
		    toLpushList.add(jsobj.toString());
		}		
		//
		logger.info("gen ad list time:"+ (System.currentTimeMillis()-start) + "ms");
	    start = System.currentTimeMillis();
	    RedisTool.lpush(Constants.KEY_AD_SENTLIST_TOSAVE, toLpushList);
		logger.info("lpush ad time:"+ (System.currentTimeMillis()-start) + "ms");
		return arr;
	}

}
