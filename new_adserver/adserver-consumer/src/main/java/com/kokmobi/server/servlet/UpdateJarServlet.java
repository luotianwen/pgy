package com.kokmobi.server.servlet;

import com.kokmobi.server.bean.ProjectVO;
import com.kokmobi.server.bean.SdkInfo;
import com.kokmobi.server.bean.UpdateInfo;
import com.kokmobi.server.bean.UpdateJarResp;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.DisListService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.util.BrowserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * wstest.sd4face.com/checkUpdate 121.201.34.67
 * ws.sd4face.com/checkUpdate 104.250.130.218 
 *
 */
public class UpdateJarServlet extends BaseServlet {
	private static Log logger = LogFactory.getLog(UpdateJarServlet.class);

	public UpdateJarServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			long start = System.currentTimeMillis();
			String fromAddr = this.getRemoteAddr(req);

			String ua = req.getHeader("User-Agent");
			ua = ua == null?"":ua;
			int uaType = BrowserUtil.getAgentType(ua);

			StringBuilder ipString = new StringBuilder();
			ipString.append("[from:").append(fromAddr).append("]");
			logger.info(ipString.toString());

			org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();
			// 获取参数

			String versionStr = ProtocolUtil.getReqPara(req, "version");
			String type = ProtocolUtil.getReqPara(req, "type");
			String coo_id = req.getParameter("coo_id");
//			String type = req.getParameter("type");
			// 参数过滤
			int versionInt;
			if (ProtocolUtil.isNullOrEmpty(versionStr)) {
				jsonObj.put("jarinfo", "");
				response(jsonObj.toString(), "text/plain; charset=UTF8", resp);
				return;
			} else {
				try {
					versionInt = Integer.parseInt(versionStr);
				} catch (Exception e) {
					logger.error("error: version can not parse int");
					jsonObj.put("jarinfo", "");
					response(jsonObj.toString(), "text/plain; charset=UTF8", resp);
					return;
				}
			}



			// 回传信息
			List<JSONObject> updateJarResps = new ArrayList<>();
			if (ProtocolUtil.isNullOrEmpty(type)) {
				jsonObj.put("status",1);

				DisListService service = this.getDisListService();
				ProjectVO projectVO=service.getProject(coo_id);
				int is= Constants.statusMap.get(projectVO.getIsDown());
				jsonObj.put("isDown",is);
				if(is==1) {
					List<UpdateJarResp> updateJars = getSdkService().getUpdateJars(versionInt);
					if (null != updateJars && 0 < updateJars.size()) {
						for (UpdateJarResp jar : updateJars) {
							updateJarResps.add(JSONObject.fromObject(jar));
						}
					}
				}
			}
			jsonObj.put("jarinfo",  updateJarResps);
			response(jsonObj.toString(), "text/plain; charset=UTF8", resp);

			logger.info("et:"+ (System.currentTimeMillis()-start) + "ms");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			response("{\"status\":\"0\"}", "text/plain; charset=UTF8", resp);
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
