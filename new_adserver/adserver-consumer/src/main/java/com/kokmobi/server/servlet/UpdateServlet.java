package com.kokmobi.server.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokmobi.server.protocol.service.ProtocolUtil;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.SdkInfo;
import com.kokmobi.server.bean.UpdateInfo;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.util.BrowserUtil;

/**
 * wstest.sd4face.com/checkUpdate 121.201.34.67
 * ws.sd4face.com/checkUpdate 104.250.130.218 
 *
 */
public class UpdateServlet extends BaseServlet {
	private static Log logger = LogFactory.getLog(UpdateServlet.class);
	private String fromAddr;
	public UpdateServlet() {
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

			
			String pkgTypeStr = ProtocolUtil.getReqPara(req, "pkgType");
			String versionStr = ProtocolUtil.getReqPara(req, "version");
			int pkgType = ProtocolUtil.isNullOrEmpty(pkgTypeStr) ? 0 : Integer.valueOf(pkgTypeStr);
			int version = ProtocolUtil.isNullOrEmpty(versionStr) ? 0 : Integer.valueOf(versionStr);
			if(pkgType==0) {
				SdkInfo sdkInfo = getSdkService().getSdkInfo(version);
				if(sdkInfo!=null) {
					UpdateInfo updateInfo = new UpdateInfo();
					updateInfo.setHasNew(1);
					updateInfo.setUrl(sdkInfo.getUrl());
					updateInfo.setPkgType(pkgType);
					updateInfo.setVersion(sdkInfo.getSdkVersion());
					JSONObject obj = JSONObject.fromObject(updateInfo);
					response(obj.toString(), "text/plain; charset=UTF8", resp);
				} else {
					UpdateInfo updateInfo = new UpdateInfo();
					JSONObject obj = JSONObject.fromObject(updateInfo);
					response(obj.toString(), "text/plain; charset=UTF8", resp);
				}
			}
			logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			response("{\"status\":\"error\"}", "text/plain; charset=UTF8", resp);
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
