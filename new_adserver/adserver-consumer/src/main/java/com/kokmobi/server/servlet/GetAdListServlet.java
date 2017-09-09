package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.AdUsersProcessResp;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.ActUserTool;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

import com.kokmobi.server.servlet.util.CompatibleUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 获取apk广告数据接口
 * @author min
 */
public class GetAdListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(GetAdListServlet.class);
	private String fromAddr;
   

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long start = System.currentTimeMillis();
		
		this.fromAddr = this.getRemoteAddr(req);//"121.201.37.64";//
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			GetAdListProcessService service = this.getGetAdListProcessService();
			GetAdListReq reqInfo = parseRequest(req);
			if(null != reqInfo) {
				reqInfo.setIpaddr(this.fromAddr);
//				GetAdListResp respData = service.process(sdkService, areaService, reqInfo);
				GetAdListResp respData = service.process(reqInfo);
				if(respData != null) {
					ObjectMapper mapper = new ObjectMapper();   
			        String json=mapper.writeValueAsString(respData);
			        
					response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
				} else {
					response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
				}
			}
			
			AdUsersProcessService userService = this.getAdUsersProcessService();
			AdUserInfo userInfo = parseAdUserRequest(req);
			if(null != userInfo) {
				userInfo.setIpaddr(this.fromAddr);
				ActUserTool actTool = new ActUserTool(userService, userInfo);
				actTool.start();
			}
		}
		catch(Exception e) {
			response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
		}
		
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private GetAdListReq parseRequest(HttpServletRequest req) throws Exception {
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

}
