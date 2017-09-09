package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetAdListResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.GetCommonInfoProcessService;
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
 * @author min
 * get down or guide plugin apk
 * and guide images.
 */
public class GetPlugInInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(GetPlugInInfoServlet.class);
	private String fromAddr;
    
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		
		this.fromAddr = this.getRemoteAddr(req);//"202.137.156.74";//
		
		StringBuffer ipString = new StringBuffer();
		ipString.append("[from:").append(this.fromAddr).append("]");
		logger.info(ipString.toString());
		
		try{
			GetCommonInfoProcessService service = this.getGetPluginInfoProcessService();
			SdkService sdkService = this.getSdkService();		
			AreaService areaService = this.getAreaService();
			JSONObject reqInfo = service.parseRequest(req);
			if(null != reqInfo) {
				reqInfo.put("ipAddr",this.fromAddr);
				JSONObject respData = service.process(sdkService, areaService, reqInfo);
				if(respData != null) {
					response(ProtocolUtil.getRespData(req, respData.toString()), "text/plain; charset=UTF8",resp );
				} else {
					response(ProtocolUtil.getRespData(req, "{\"state\":0}"), "text/plain; charset=UTF8", resp);
				}
			}
		}
		catch(Exception e) {
			response(ProtocolUtil.getRespData(req, "{\"state\":0}"), "text/plain; charset=UTF8", resp);
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
