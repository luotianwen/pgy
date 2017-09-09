package com.kokmobi.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokmobi.server.protocol.service.ProtocolUtil;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.ApkInfoVO;
import com.kokmobi.server.bean.DisRespVO;
import com.kokmobi.server.bean.DistReqVO;
import com.kokmobi.server.bean.SdkInfo;
import com.kokmobi.server.bean.UpdateInfo;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.DisListService;
import com.kokmobi.server.util.BrowserUtil;

/**
 * wstest.sd4face.com/checkUpdate 121.201.34.67
 * ws.sd4face.com/checkUpdate 104.250.130.218 
 * @author rayi
 *
 */
public class SHDisListServlet extends BaseServlet {
	private static Log logger = LogFactory.getLog(SHDisListServlet.class);
	private String fromAddr;
	public SHDisListServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try{
			long start = System.currentTimeMillis();
			this.fromAddr = this.getRemoteAddr(req);
			
			String ua = req.getHeader("User-Agent");
			ua = ua == null?"":ua;
			int uaType = BrowserUtil.getAgentType(ua);
			
			StringBuffer ipString = new StringBuffer();
			ipString.append("[from:").append(this.fromAddr).append("]");
			logger.info(ipString.toString());
//			String sdkversion=req.getParameter("sdkversion");
			String sdkversion = ProtocolUtil.getOneOrNoneReqPara(req, "sdkversion");
			// 获取参数		
			DisListService service = this.getDisListService();
			DistReqVO distReqVO = service.adapter(req);
			if(null!=distReqVO) {
				DisRespVO disRespVO = service.processs(distReqVO);
				if(disRespVO!=null) {
					JSONObject disRespVOJson = JSONObject.fromObject(disRespVO);
					response(disRespVOJson.toString(), "text/plain; charset=UTF8",resp );
				} else {
					response("{\"status\":0}", "text/plain; charset=UTF8", resp);
				}
			}
			logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			response("{\"status\":0}", "text/plain; charset=UTF8", resp);
		}		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	public void init() throws ServletException {
		// Put your code here
	}
	
	public static void main(String[] args) {
		UpdateInfo updateInfo = new UpdateInfo();
		JSONObject obj = JSONObject.fromObject(updateInfo);
		System.out.println(obj.toString());
	}
}
