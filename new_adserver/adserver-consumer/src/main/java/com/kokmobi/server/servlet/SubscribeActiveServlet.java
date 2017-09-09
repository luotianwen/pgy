package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.ActiveInfo;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.GetOnlineReq;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.SubAdProcessService;
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
public class SubscribeActiveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(SubscribeActiveServlet.class);
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
			SubAdProcessService service = this.getSubProcessService();
			ActiveInfo activeInfo = parseRequest(req);
			if(null != activeInfo) {
				service.processActive(activeInfo);
			}
		}
		catch(Exception e) {
			logger.error("sub active data error"+e);
		}
		
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private ActiveInfo parseRequest(HttpServletRequest req) throws Exception {
		ActiveInfo activeInfo = new ActiveInfo();
		String clickid = ProtocolUtil.getReqPara(req, "clickid");
		String payout = ProtocolUtil.getReqPara(req, "payout");
		String affid = ProtocolUtil.getReqPara(req, "affid");
		if(ProtocolUtil.isNullOrEmpty(clickid)){
			return null;
		}
		activeInfo.setAffId(affid);
			activeInfo.setClickId(clickid);
		activeInfo.setPayout(payout);
		return activeInfo;
	}

}
