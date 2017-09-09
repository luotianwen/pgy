package com.kokmobi.server.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.ActUserTool;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.SdkService;
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
		
		this.fromAddr = this.getRemoteAddr(req);
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());

		try{
			GetAdListProcessService service = this.getGetWebAdListProcessService();
			SdkService sdkService = this.getSdkService();		
			AreaService areaService = this.getAreaService();
			GetAdListReq reqInfo = service.parseRequest(req);
			if(null != reqInfo) {
				reqInfo.setIpaddr(this.fromAddr);
				GetAdListResp respData = service.process(sdkService, areaService, reqInfo);
				if(respData != null) {			
					ObjectMapper mapper = new ObjectMapper();   
			        String json=mapper.writeValueAsString(respData);
					response(ProtocolUtil.getRespData(req, json), "text/plain; charset=UTF8",resp );
				} else {
					response(ProtocolUtil.getRespData(req, "{\"isExit\":0}"), "text/plain; charset=UTF8", resp);
				}
			}
			
			AdUsersProcessService userService = this.getAdUsersProcessService();
			AdUserInfo userInfo = userService.parseRequest(req);
			if(null != userInfo) {
				userInfo.setIpaddr(this.fromAddr);
				ActUserTool actTool = new ActUserTool(userService, sdkService, areaService,userInfo);
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

}
