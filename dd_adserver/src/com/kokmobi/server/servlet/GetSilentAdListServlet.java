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
		
		this.fromAddr = this.getRemoteAddr(req);//"121.201.37.64";//
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			GetAdListProcessService service = this.getGetSilentAdListProcessService();
			SdkService sdkService = this.getSdkService();		
			AreaService areaService = this.getAreaService();
			GetAdListReq reqInfo = service.parseRequest(req);
			if(null != reqInfo) {
				reqInfo.setIpaddr(this.fromAddr);
				GetAdListResp respData = service.process(sdkService, areaService, reqInfo);
				if(respData != null) {					
//					JSONObject respVOJson = JSONObject.fromObject(respData);
					ObjectMapper mapper = new ObjectMapper();   
			        String json=mapper.writeValueAsString(respData);
					response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
				} else {
					response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
				}
			}
			
			//add thread?
			//Add to actuser
			AdUsersProcessService userService = this.getAdUsersProcessService();
			AdUserInfo userInfo = userService.parseRequest(req);
			if(null != userInfo) {
				userInfo.setIpaddr(this.fromAddr);
				userInfo.setSdk(5);
				userInfo.setSdkStyle(Constants.SDKSTYLE_SILENCE);
//				AdUsersProcessResp respData = userService.process(sdkService, redis, areaService, userInfo);
//				if(respData == null) {
//					logger.error(String.format("add act userInfo error:%s", userInfo.getImei()));
//				}
				ActUserTool actTool = new ActUserTool(userService, sdkService, areaService,userInfo);
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

}
