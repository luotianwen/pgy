package com.kkgame.feeop.base;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.kkgame.feeop.user.action.LoginAction;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.CalendarFormat;

/**
 * 登陆超时INTECEPTOR
 * @author rayi
 *
 */
@SuppressWarnings("serial")
public class AuthorizationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Object action = arg0.getAction();
		if(action instanceof LoginAction) {
			return arg0.invoke();
		}
		
		UserVO userVO = null;
		javax.servlet.http.HttpSession session = ServletActionContext.getRequest().getSession();
		if(null!=session) {
			userVO = (UserVO) session.getAttribute(PkigConstants.SESSION_USER);
		}
		if(null != userVO) {
			if(!PkigConstants.ONLINE_USER.containsKey(userVO.getLoginId())) {
				PkigConstants.ONLINE_USER.put(userVO.getLoginId(), CalendarFormat.getNow());
			}
			return arg0.invoke();
		}else {
			return "loginout";
		}
	}

}
