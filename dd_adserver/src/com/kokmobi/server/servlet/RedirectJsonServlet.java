package com.kokmobi.server.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokmobi.server.service.CacheInfoUtil;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.UpdateInfo;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.util.BrowserUtil;

/**
 * wstest.sd4face.com/goLink 121.201.34.67
 * ws.sd4face.com/goLink 104.250.130.218 
 * @author rayi
 *
 */
public class RedirectJsonServlet extends BaseServlet {
	private static Log logger = LogFactory.getLog(RedirectJsonServlet.class);
	private String fromAddr;
	public RedirectJsonServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long start = System.currentTimeMillis();
		JSONObject  disRespVOJson=new JSONObject();
		try{
			
			this.fromAddr = this.getRemoteAddr(req);
			
			String ua = req.getHeader("User-Agent");
			ua = ua == null?"":ua;
			int uaType = BrowserUtil.getAgentType(ua);
			int adId = Integer.parseInt(req.getParameter("adId"));
			
			StringBuffer ipString = new StringBuffer();
			ipString.append("[from:").append(this.fromAddr).append("]");
			logger.info(ipString.toString());
//			SdkInfo sdkInfo = getSdkService().getLinkAdUrl(adId);
			String sdkUrl = CacheInfoUtil.getLinkAdUrl(getSdkService(), adId);

			if (null != sdkUrl) {
				disRespVOJson.put("url", sdkUrl);
			} else {
				disRespVOJson.put("url", "http://orzmobi.com/in/mugua1/");
			}
			
		}
		catch(Exception ex) {
			logger.debug(ex);
				 disRespVOJson.put("url", "http://orzmobi.com/in/mugua1/");
		}	
			response(disRespVOJson.toString(), "text/plain; charset=UTF8",resp );
		logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");	
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
