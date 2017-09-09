package com.kkgame.hz.action;



import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.entities.PortalUserVO;

public class AuthorizationInterceptor
		extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(AuthorizationInterceptor.class);
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		logger.info("AuthorizationInterceptor intercept");
		Object action = arg0.getAction();
		if (action instanceof LoginAction){
			logger.info("login action not check.");
			return arg0.invoke();
		}
		PortalUserVO userVO = null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (null != session){
			userVO = (PortalUserVO)session.getAttribute(PkigConstants.SESSION_PORTALUSER);
		}   
		if ( null != userVO)  {
			return arg0.invoke();
		} else  {
			//ServletActionContext.getContext().put("invalidUser", true);
			return "loginout";
		} 
	}
}
