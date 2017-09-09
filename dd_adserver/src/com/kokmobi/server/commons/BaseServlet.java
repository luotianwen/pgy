package com.kokmobi.server.commons;

import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kokmobi.server.protocol.service.AdLogDataProcessService;
import com.kokmobi.server.protocol.service.AdUsersProcessService;
import com.kokmobi.server.protocol.service.DisListService;
import com.kokmobi.server.protocol.service.GetAdListFeebackService;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.GetCommonInfoProcessService;
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
	public GetCommonInfoProcessService getGetPluginInfoProcessService() {
		return (GetCommonInfoProcessService) getBean("getPluginInfoProcessService");
	}
	public GetCommonInfoProcessService getGetAdjustKeyService() {
		return (GetCommonInfoProcessService) getBean("getAdjustKeyService");
	}
	
}
