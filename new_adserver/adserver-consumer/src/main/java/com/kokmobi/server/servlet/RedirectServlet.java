package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.SdkInfo;
import com.kokmobi.server.bean.UpdateInfo;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.util.BrowserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * wstest.sd4face.com/goLink 121.201.34.67
 * ws.sd4face.com/goLink 104.250.130.218 
 *
 */
public class RedirectServlet extends BaseServlet {
	private static Log logger = LogFactory.getLog(RedirectServlet.class);
	private String fromAddr;
	public RedirectServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long start = System.currentTimeMillis();
		try{
			
			this.fromAddr = this.getRemoteAddr(req);
			
			String ua = req.getHeader("User-Agent");
			ua = ua == null?"":ua;
			int uaType = BrowserUtil.getAgentType(ua);
			StringBuffer ipString = new StringBuffer();
			ipString.append("[from:").append(this.fromAddr).append("]");
			logger.info(ipString.toString());

			String adIdStr = ProtocolUtil.getOneOrNoneReqPara(req, "adId");
			int adId = ProtocolUtil.isNullOrEmpty(adIdStr) ? 0 : Integer.parseInt(adIdStr);
			String sdkUrl =getSdkService().getLinkAdUrl(adId);

			SdkInfo sdkInfo = new SdkInfo();
			sdkInfo.setUrl(sdkUrl);
			if(null!=sdkUrl){
				resp.addHeader("location", sdkUrl);
				resp.setStatus(302);
			}
			 else {
				resp.addHeader("location", "http://orzmobi.com/in/mugua1/");
				resp.setStatus(302);
			}
			
		}
		catch(Exception ex) {
			logger.debug(ex);
			resp.addHeader("location", "http://orzmobi.com/in/mugua1/");
			resp.setStatus(302);
		}	
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
