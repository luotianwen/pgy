package com.kkgame.feeop.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailSender;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.kkgame.feeop.tag.service.SystemService;
import com.kkgame.feeop.util.ServiceBeanID;

/**
 * 
 * @author rayi
 *
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(BaseAction.class);
	
	private static volatile Map<String, Object> serviceMap = new HashMap<String, Object>();
	public static final int BUFFER_SIZE = 16*1024;
	public static final String ACTION_RESULT_LIST = "list";
	public static final String ACTION_RESULT_CREATE = "create";
	public static final String ACTION_RESULT_MODIFY = "modify";
	public static final String ACTION_RESULT_DETAIL = "detail";
	public static final String ACTION_RESULT_AUDIT = "audit";
	public static final String ACTION_RESULT_QUERY = "query";
	public static final String ACTION_RESULT_UPDATE = "update";
	protected static final String PlAIN_MESSAGE = "plainMessage";	
	protected static final String RET_FAIL = "0";
	protected static final String RET_SUCCESS = "1";
	public static final String USER_SESSION = "user_session";
	public String errorMsg;
	public String flag;
	public static final String ACTION_REQUEST_SHOW = "show";
	public static final String ACTION_REQUEST_INIT = "init";
	public static final String ACTION_REQUEST_AJAX = "ajax";
	public static final String ACTION_REQUEST_AJAXS = "ajaxs";
	public static final String ACTION_REQUEST_DATA = "data";

	private static final long serialVersionUID = 2969577367265538184L;
	
	protected void setSession(String name, Object val){
		this.getRequest().getSession().setAttribute(name, val);
	}
	protected Object getSession(String name){
		return this.getRequest().getSession().getAttribute(name);
	}
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	protected String getParameter(String name){
		return getRequest().getParameter(name);
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public static Object getServiceObject(String beanId) {
		Object service = serviceMap.get(beanId);
		if (null == service) {
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(PkigConstants.PKIG_WEB_CONTEXT);
			service = wac.getBean(beanId);
			serviceMap.put(beanId, service);
		}
		return service;
	}
	
	public static void main(String[] args) {
		getServiceObject("mailSender");
	}
	
	public String getSystemBashPath(String key){
		try {
			SystemService service = (SystemService)ServiceBeanID.getServiceObject(ServiceBeanID.SYSTEM_SERVICE);
			return service.getConfigureByKey(key);
		} catch (Exception e) {
			logger.error("BasicAction getSystemBashPath error",e);
		}
		return "";
	}
	
	public static MailSender getMailSender() {
		return (MailSender) getServiceObject("mailSender");
	}
	
	protected int getParameterInt(String name, int errorInt){
		try {
			return Integer.parseInt(getRequest().getParameter(name));
		} catch (Exception e) {
			return errorInt;
		}
	}
	
	protected void printMessage(String message){
		try {
			getResponse().setCharacterEncoding("utf-8");
			getResponse().getWriter().print(message);
			getResponse().getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void printJsonMessage(String message) {
		try {
			getResponse().setCharacterEncoding("utf-8");
			getResponse().setContentType("application/json");
			getResponse().getWriter().print(message);
			getResponse().getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
