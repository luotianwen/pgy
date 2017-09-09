package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.commons.Constants;
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
 * Servlet implementation class GetSilentAdListServlet
 */
public class GetSilentAdListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(GetSilentAdListServlet.class);
	private String fromAddr;
    /**
     * Default constructor. 
     */
    public GetSilentAdListServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long start = System.currentTimeMillis();
		
		this.fromAddr =  this.getRemoteAddr(req);//"121.201.37.64";//

		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			GetAdListProcessService service = this.getGetSilentAdListProcessService();
			GetAdListReq reqInfo = parseRequest(req);
			if(null != reqInfo) {
				reqInfo.setIpaddr(this.fromAddr);
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
				userInfo.setSdk(5);
				userInfo.setSdkStyle(Constants.SDKSTYLE_SILENCE);
				ActUserTool actTool = new ActUserTool(userService, userInfo);
				actTool.start();
			}
			else{
				logger.error("get slient userinfo error");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
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

	private GetAdListReq parseRequest(HttpServletRequest req) throws Exception {

		String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
		String xc_coo_id = ProtocolUtil.getReqPara(req, "xc_coo_id");
		String imei = ProtocolUtil.getReqPara(req, "imei");
		String channelId = ProtocolUtil.getReqPara(req, "channelId");
		String language = ProtocolUtil.getReqPara(req, "language");
		String internet = ProtocolUtil.getReqPara(req, "internet");
		String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
		String apkids = ProtocolUtil.getReqPara(req, "apkid");
		String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
		String xversion = ProtocolUtil.getReqPara(req, "xversion");
		String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
		String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
		String xoperator = ProtocolUtil.getReqPara(req, "xoperator");


		int sdkType = Constants.SDKSTYLE_SILENCE;
		if("1".equals(sdkversion) || "1.0".equals(sdkversion)) {
			sdkversion = "1";
		}
		String sdk = "5";

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

		//JSONObject j = JSONObject.fromObject(reqInfo);
		//logger.info(String.format("got a log: %s.", j.toString()));
		return reqInfo;
	}

	public AdUserInfo parseAdUserRequest(HttpServletRequest req) throws Exception {
		AdUserInfo user = new AdUserInfo();



		String type = ProtocolUtil.getReqPara(req, "type");
		String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
		String xc_coo_id = ProtocolUtil.getReqPara(req, "xc_coo_id");
		String imei = ProtocolUtil.getReqPara(req, "imei");
		String channelId = ProtocolUtil.getReqPara(req, "channelId");
		String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
		String xversion = ProtocolUtil.getReqPara(req, "xversion");
		String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
		String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
		String xoperator = ProtocolUtil.getReqPara(req, "xoperator");
		String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
		String sdk = ProtocolUtil.getReqPara(req, "sdk");

		int sdkType = CompatibleUtil.getSDKType(sdk, sdkversion, xc_coo_id);
		sdkversion = sdkversion.equals("1.0") ? "1" : sdkversion;

		user.setCoo_id(coo_id);
		user.setImei(imei);
		user.setType(type);
		user.setXc_coo_id(ProtocolUtil.isNullOrEmpty(xc_coo_id) ? "0" : xc_coo_id);
		user.setChannelid(channelId);
		user.setXmodel(xmodel);
		user.setXversion(xversion);
		user.setXimsi(ximsi);
		user.setXinternet(xinternet);
		user.setXoperator(xoperator);
		user.setSdkversion(sdkversion);
		user.setSdk(ProtocolUtil.isNullOrEmpty(sdk)?0:Integer.parseInt(sdk));
		user.setSdkStyle(sdkType);

		//JSONObject j = JSONObject.fromObject(user);
		//logger.info(String.format("got a log: %s.", j.toString()));

		return user;
	}

}
