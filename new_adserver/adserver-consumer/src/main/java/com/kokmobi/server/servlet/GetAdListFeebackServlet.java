package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.GetAdListFeebackReq;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.GetAdListFeebackService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.RedisService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 获取广告列表回调接口
 * @author min
 */
public class GetAdListFeebackServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(GetAdListFeebackServlet.class);
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
			GetAdListFeebackService service = this.getGetAdListFeebackService();
			GetAdListFeebackReq reqInfo = parseRequest(req);
			if(null != reqInfo) {
				service.process(reqInfo);
			}
			response(ProtocolUtil.getRespData(req, "{\"status\":1}"), "text/plain; charset=UTF8", resp);
		}
		catch(Exception e) {
			response(ProtocolUtil.getRespData(req, "{\"status\":0}"), "text/plain; charset=UTF8", resp);
		}
		
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");	
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private GetAdListFeebackReq parseRequest(HttpServletRequest req) throws Exception {

		String imei = ProtocolUtil.getReqPara(req, "imei");
		String pkgId = ProtocolUtil.getReqPara(req, "pkgid");
		GetAdListFeebackReq reqInfo = new GetAdListFeebackReq();
		reqInfo.setImei(imei);
		reqInfo.setPkgId(pkgId);
		return reqInfo;
	}

}
