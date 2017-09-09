package com.kokmobi.server.commons;

import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kokmobi.server.bean.AdUserInfo;
import com.kokmobi.server.bean.DistReqVO;
import com.kokmobi.server.protocol.service.*;
import com.kokmobi.server.servlet.util.CompatibleUtil;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.util.ByteUtils;

public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938872290469393539L;
	
	Log logger = LogFactory.getLog(BaseServlet.class);

	public Object getBean(String beanName) {
		ServletContext context = this.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		return ctx.getBean(beanName);
	}
	
	public String getRemoteAddr(HttpServletRequest req) {
		String ip = "";
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("x-forwarded-for");
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("X-Real-IP");
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getRemoteAddr();
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = req.getRemoteHost();
			}
			if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
				ip = "";
			}
		}
		if(ip.indexOf(",")!=-1) {
			ip = ip.split(",")[1].trim();
		}
 		return ip;
    }
	
	public void response(String result,String contentType,HttpServletResponse response){
       OutputStream os = null;
       try {
           response.setContentLength(ByteUtils.str2UTF8Bytes(result).length);
           response.setContentType(contentType);
           os = response.getOutputStream();
           os.write(ByteUtils.str2UTF8Bytes(result));
           os.flush();
           os.close();
           response.getWriter().flush();
       } catch (Exception e) {
       } finally {
           try{os.close();}catch (Exception e){};
       }
    }
	
	
	public SdkService getSdkService() {
		return (SdkService) getBean("sdkService");
	}
	
	public DisListService getDisListService() {
		return (DisListService) getBean("disListService");
	}
	
	public RedisService getRedisService() {
		return (RedisService) getBean("redisService");
	}
	
	public AreaService getAreaService() {
		return (AreaService) getBean("areaService");
	}
	
	public AdUsersProcessService getAdUsersProcessService() {
		return (AdUsersProcessService) getBean("adUsersProcessService");
	}
	
	public GetAdListProcessService getGetAdListProcessService (){
		return (GetAdListProcessService) getBean("getAdListProcessService");
	}
	public GetAdListFeebackService getGetAdListFeebackService (){
		return (GetAdListFeebackService) getBean("getAdListFeebackService");
	}
	public AdLogDataProcessService getAdLogDataProcessService() {
		return (AdLogDataProcessService) getBean("adLogDataProcessService");
	}
	public GetAdListProcessService getGetSilentAdListProcessService (){
		return (GetAdListProcessService) getBean("getSilentAdListProcessService");
	}
	public GetAdListProcessService getGetWebAdListProcessService (){
		return (GetAdListProcessService) getBean("getWebAdListProcessService");
	}
	public WebAdListProcessService getWebAdListProcessService (){
		return (WebAdListProcessService) getBean("webAdListProcessService");
	}
	public GetCommonInfoProcessService getGetPluginInfoProcessService() {
		return (GetCommonInfoProcessService) getBean("getPluginInfoProcessService");
	}
	public GetAdjustKeyService getGetAdjustKeyService() {
		return (GetAdjustKeyService) getBean("getAdjustKeyService");
	}
	public SubAdProcessService getSubProcessService() {
		return (SubAdProcessService)getBean("getSubProcessService");
	}
	public AdUserInfo parseAdUserRequest(HttpServletRequest req) throws Exception {
		AdUserInfo user = new AdUserInfo();
		String type = ProtocolUtil.getReqPara(req, "type");
		String coo_id = ProtocolUtil.getReqPara(req, "coo_id");
		String xc_coo_id = ProtocolUtil.getReqPara(req, "xc_coo_id");
		String imei = ProtocolUtil.getReqPara(req, "imei");
		String channelId = ProtocolUtil.getReqPara(req, "channelId");
		String xmodel = ProtocolUtil.getReqPara(req, "xmodel");
		String xversion = ProtocolUtil.getReqPara(req, "xversion");
		String ximsi = ProtocolUtil.getReqPara(req, "ximsi");
		String xinternet = ProtocolUtil.getReqPara(req, "xinternet");
		String xoperator = ProtocolUtil.getReqPara(req, "xoperator");
		String sdkversion = ProtocolUtil.getReqPara(req, "sdkversion");
		String sdk = ProtocolUtil.getReqPara(req, "sdk");
		int sdkType = CompatibleUtil.getSDKType(sdk, sdkversion, xc_coo_id);
		sdkversion = sdkversion.equals("1.0") ? "1" : sdkversion;
		user.setCoo_id(coo_id);
		user.setImei(imei);
		user.setType(type);
		user.setXc_coo_id(ProtocolUtil.isNullOrEmpty(xc_coo_id) ? "0" : xc_coo_id);
		user.setChannelid(channelId);
		user.setXmodel(xmodel);
		user.setXversion(xversion);
		user.setXimsi(ximsi);
		user.setXinternet(xinternet);
		user.setXoperator(xoperator);
		user.setSdkversion(sdkversion);
		user.setSdk(ProtocolUtil.isNullOrEmpty(sdk)?0:Integer.parseInt(sdk));
		user.setSdkStyle(sdkType);
		return user;
	}

	public DistReqVO adapter(HttpServletRequest req) throws Exception {

		String imei = ProtocolUtil.getReqPara(req, "imei");
		String cooIdStr = ProtocolUtil.getReqPara(req, "cooId");
		String xcooIdStr = ProtocolUtil.getReqPara(req, "xcooId");
		String sdkVersionStr = ProtocolUtil.getReqPara(req, "sdkVersion");
		int cooId = ProtocolUtil.isNullOrEmpty(cooIdStr) ? 0 : Integer.parseInt(cooIdStr);
		int xcooId = ProtocolUtil.isNullOrEmpty(xcooIdStr) ? 0 : Integer.parseInt(xcooIdStr);
		int sdkVersion = ProtocolUtil.isNullOrEmpty(sdkVersionStr) ? 0 : Integer.parseInt(sdkVersionStr);
		DistReqVO reqVO = new DistReqVO();
		reqVO.setImei(imei);
		reqVO.setXcooId(xcooId);
		reqVO.setCooId(cooId);
		reqVO.setSdkVersion(sdkVersion);
		return reqVO;
	}
}
