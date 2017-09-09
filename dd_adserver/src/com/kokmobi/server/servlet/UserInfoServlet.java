package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.AdUsersProcessResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
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
		
		this.fromAddr = this.getRemoteAddr(req);//"202.137.156.74";//
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			AdUsersProcessService service = this.getAdUsersProcessService();
			SdkService sdkService = this.getSdkService();		
			AreaService areaService = this.getAreaService();
			AdUserInfo userInfo = service.parseRequest(req);
			if(null != userInfo) {
				userInfo.setIpaddr(this.fromAddr);
				AdUsersProcessResp respData = service.process(sdkService, areaService, userInfo);
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

}
