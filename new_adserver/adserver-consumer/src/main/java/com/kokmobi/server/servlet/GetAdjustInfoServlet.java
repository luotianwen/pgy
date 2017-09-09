package com.kokmobi.server.servlet;

import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.GetAdjustKeyService;
import com.kokmobi.server.protocol.service.GetCommonInfoProcessService;
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
 * @author min
 * get adjust key with channel id
 */
public class GetAdjustInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(GetAdjustInfoServlet.class);
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
//			GetCommonInfoProcessService service = this.getGetAdjustKeyService();
			GetAdjustKeyService service = this.getGetAdjustKeyService();
//			SdkService sdkService = this.getSdkService();
//			AreaService areaService = this.getAreaService();
//			JSONObject reqInfo = service.parseRequest(req);
			JSONObject reqInfo = parseRequest(req);
			if(null != reqInfo) {
				reqInfo.put("ipAddr",this.fromAddr);
//				JSONObject respData = service.process(sdkService, areaService, reqInfo);
				JSONObject respData = JSONObject.fromObject(service.process(reqInfo.toString()));
				if(respData != null) {
					response(ProtocolUtil.getRespData(req, respData.toString()), "text/plain; charset=UTF8",resp );
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private JSONObject parseRequest(HttpServletRequest req) throws Exception {
		JSONObject jobj = new JSONObject();

		jobj.put("pname", ProtocolUtil.getReqPara(req, "pname"));	//adjust channel id
//		jobj.put("pname", ProtocolUtil.getReqPara(req, "pname"));	//adjust channel id
		return jobj;
	}

}
