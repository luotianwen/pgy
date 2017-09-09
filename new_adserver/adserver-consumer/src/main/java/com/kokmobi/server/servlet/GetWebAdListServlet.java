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
import com.kokmobi.server.service.SdkService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取webapk广告数据接口
 * @author
 */
public class GetWebAdListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(GetWebAdListServlet.class);
	private String fromAddr;
   

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long start = System.currentTimeMillis();
		
		this.fromAddr = this.getRemoteAddr(req);//this.fromAddr = "121.201.37.64";//
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		try{
			long begin=System.currentTimeMillis();
			GetAdListProcessService service = this.getGetWebAdListProcessService();
			long end=  System.currentTimeMillis();
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
			AdUserInfo userInfo =parseAdUserRequest(req);
			if(null != userInfo) {
				userInfo.setIpaddr(this.fromAddr);
				ActUserTool actTool = new ActUserTool(userService, userInfo);
				actTool.start();
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private GetAdListReq parseRequest(HttpServletRequest req) throws Exception {
		long start = System.currentTimeMillis();
		String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
		String imei = ProtocolUtil.getReqPara(req, "imei");
		String language = ProtocolUtil.getReqPara(req, "language");
		String internet = ProtocolUtil.getReqPara(req, "internet");
		String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
		String apkids = ProtocolUtil.getReqPara(req, "apkid");
		String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
		String xversion = ProtocolUtil.getReqPara(req, "xversion");
		String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
		String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
		String xoperator = ProtocolUtil.getReqPara(req, "xoperator");
		sdkversion = sdkversion.equals("1.0") ? "1" : sdkversion;
		GetAdListReq reqInfo = new GetAdListReq();
		reqInfo.setCoo_id(coo_id);
		reqInfo.setImei(imei);
		reqInfo.setLanguage(language);
		reqInfo.setInternet(internet);
		reqInfo.setApkid(apkids);
		reqInfo.setXmodel(xmodel);
		reqInfo.setXversion(xversion);
		reqInfo.setXimsi(ximsi);
		reqInfo.setXinternet(xinternet);
		reqInfo.setXoperator(xoperator);
		reqInfo.setSdkversion(Integer.parseInt(sdkversion));
		reqInfo.setSdk("2");
		reqInfo.setSdkStyle(Constants.SDKSTYLE_SDK);
		JSONObject j = JSONObject.fromObject(reqInfo);
		logger.info(String.format("got a log: %s.", j.toString()));
		logger.info("parse time:" + (System.currentTimeMillis() - start) + "ms");
		return reqInfo;
	}

}
