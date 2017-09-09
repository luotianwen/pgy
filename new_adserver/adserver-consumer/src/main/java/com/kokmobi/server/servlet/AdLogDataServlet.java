package com.kokmobi.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokmobi.server.bean.AdLogInfo;
import com.kokmobi.server.bean.AdLogPackage;
import com.kokmobi.server.servlet.util.CompatibleUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.AdLogReq;
import com.kokmobi.server.bean.GetAdListFeebackReq;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdLogDataProcessService;
import com.kokmobi.server.protocol.service.GetAdListFeebackService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

/**
 * @author min
 * 接收广告日志数据接口
 */
public class AdLogDataServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(AdLogDataServlet.class);
	private String fromAddr;
    
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		
		this.fromAddr = this.getRemoteAddr(req);
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			AdLogDataProcessService service = this.getAdLogDataProcessService();
			AdLogReq reqInfo = parseRequest(req);
			if(null != reqInfo) {
				reqInfo.setIpaddr(this.fromAddr);
				service.process(reqInfo);
			}
			response(ProtocolUtil.getRespData(req, "{\"status\":1}"), "text/plain; charset=UTF8", resp);
		}
		catch(Exception e) {
			logger.error(e);
			response(ProtocolUtil.getRespData(req, "{\"status\":0}"), "text/plain; charset=UTF8", resp);
		}
		
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private AdLogReq parseRequest(HttpServletRequest req) throws Exception {
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

}
