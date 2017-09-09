package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.AdUsersProcessResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
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
 * 销量与活跃用户接口
 * @author min
 */
public class UserInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;    
	private static Log logger = LogFactory.getLog(UserInfoServlet.class);
	private String fromAddr;
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long start = System.currentTimeMillis();
		
		this.fromAddr = this.getRemoteAddr(req);
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			AdUsersProcessService service = this.getAdUsersProcessService();

			AdUserInfo userInfo = parseRequest(req);
			if(null != userInfo) {
				userInfo.setIpaddr(this.fromAddr);
				AdUsersProcessResp respData = service.process(userInfo);
				if(respData != null) {
					JSONObject respVOJson = JSONObject.fromObject(respData);
					response(ProtocolUtil.getRespData(req, respVOJson.toString()), "text/plain; charset=UTF8",resp );
				} else {
					response(ProtocolUtil.getRespData(req, "{\"status\":0}"), "text/plain; charset=UTF8", resp);
				}
			}
		}
		catch(Exception e) {
			response(ProtocolUtil.getRespData(req, "{\"status\":0}"), "text/plain; charset=UTF8", resp);
		}
		
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public AdUserInfo parseRequest(HttpServletRequest req) throws Exception {
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

		JSONObject j = JSONObject.fromObject(user);
		logger.info(String.format("got a log: %s.", j.toString()));

		return user;
	}

}