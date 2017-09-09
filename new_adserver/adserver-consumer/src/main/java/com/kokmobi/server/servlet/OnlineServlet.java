package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.GetAdListFeebackReq;
import com.kokmobi.server.bean.GetOnlineReq;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.GetAdListFeebackService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取广告列表回调接口
 * @author min
 */
public class OnlineServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(OnlineServlet.class);
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
		this.fromAddr = this.getRemoteAddr(req);
		try{
			WebAdListProcessService service = this.getWebAdListProcessService();
			GetOnlineReq reqInfo = parseRequest(req);
			if(null != reqInfo) {
				reqInfo.setIpaddr(this.fromAddr);
				service.processOnline(reqInfo);
			}
			response(ProtocolUtil.getRespData(req, "{\"status\":1}"), "text/plain; charset=UTF8", resp);
		}
		catch(Exception e) {
			logger.error("onlie back data error"+e);
			response(ProtocolUtil.getRespData(req, "{\"status\":0}"), "text/plain; charset=UTF8", resp);
		}
		
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private GetOnlineReq parseRequest(HttpServletRequest req) throws Exception {

		String imei = ProtocolUtil.getReqPara(req, "imei");
		String adId = ProtocolUtil.getReqPara(req, "adId");
		String type = ProtocolUtil.getReqPara(req, "type");
		String pkgid = ProtocolUtil.getReqPara(req, "pkgid");
		if(ProtocolUtil.isNullOrEmpty(pkgid)||ProtocolUtil.isNullOrEmpty(imei)||ProtocolUtil.isNullOrEmpty(adId)||ProtocolUtil.isNullOrEmpty(type)){
			return null;
		}
		GetOnlineReq reqInfo = new GetOnlineReq();
		reqInfo.setImei(imei);
		reqInfo.setAdId(adId);
		reqInfo.setType(type);
		reqInfo.setPkgid(pkgid);
		return reqInfo;
	}

}
