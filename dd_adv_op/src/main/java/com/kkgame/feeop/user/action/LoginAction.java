package com.kkgame.feeop.user.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.base.datasource.DataSourceContextHolder;
import com.kkgame.feeop.base.datasource.DataSourceMap;
import com.kkgame.feeop.user.bean.ResVO;
import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.user.bean.UserSession;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.user.service.ResService;
import com.kkgame.feeop.user.service.RoleService;
import com.kkgame.feeop.user.service.UserService;
import com.kkgame.feeop.util.KokMd5Encrypt;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {
	
	private static Log logger = LogFactory.getLog(LoginAction.class);

	private static final String ACTION_RESULT_LOGIN="login";
	
	private UserVO userVo;

	private UserService userService;
	
	HttpServletRequest request;
	
	private RoleService roleService;
	
	private ResService resService;
	
	private Set<String> hasResStringList = new HashSet<String>();
	
	public void setUserVo(UserVO userVo) {
		this.userVo = userVo;
	}

	public String doLoginView() {
		logger.debug("doLoginView is begin");
		return ACTION_RESULT_LOGIN;
	}

	public String doLogin() {       
		logger.debug("登陆");
		if(null==userVo) {
			userVo=new UserVO();
			errorMsg="用户名为空,请输入后登录！";
			return ACTION_RESULT_LOGIN;
		}
		String loginId=userVo.getLoginId();	
		String passwd=userVo.getPasswd();
		loginId = loginId.toLowerCase();
		passwd = passwd.toLowerCase();
		HttpSession session = ServletActionContext.getRequest().getSession();
		request = ServletActionContext.getRequest() ;
		UserVO us = new UserVO();
		
		if(loginId==null ||"".equals(loginId.trim())) {
			userVo=new UserVO();
			errorMsg="用户名为空,请输入后登录！";
			return ACTION_RESULT_LOGIN;
		}
		if(passwd==null ||"".equals(passwd.trim())) {
			errorMsg="密码为空,请输入后登录！";
			return ACTION_RESULT_LOGIN;
		}
		try {		
			us=userService.getUserVOByLoginId(loginId);			
			if(null==us) {
				userVo=new UserVO();
				errorMsg="用户不存在,请重新登录";
				return ACTION_RESULT_LOGIN;
			}
			if("manager".equalsIgnoreCase(loginId)) {
				if(!KokMd5Encrypt.md5(passwd).equalsIgnoreCase(us.getPasswd())) {
					errorMsg = "用户密码错误，请重新登录。";
					return ACTION_RESULT_LOGIN;
				}
			} else {
				if (!passwd.equals(us.getPasswd().toLowerCase())) {
					errorMsg = "用户密码错误，请重新登录。";
					 return ACTION_RESULT_LOGIN;
				}
			}			
			if (us.getStatus() == 1) {
				errorMsg = "您的账号已锁定，请联系管理员！";
				return ACTION_RESULT_LOGIN;
			}
			UserSession usession = new UserSession();
			List<RoleVO> hasRoleList = roleService.getRoleVOListByUser(us.getId());
			for(RoleVO role :hasRoleList) {
				List<ResVO> hasResList = resService.getResByRole(role.getId());
				for(ResVO res:hasResList) {
					usession.setPermission(res.getResName());
				}
			}		
			for(RoleVO role :hasRoleList) {
				if(role.getId()==5) {
					us.setIsBd(1);
				}
			}
			UserVO vo = new UserVO();
			vo.setId(us.getId());
			userService.updateUserLastLogin(vo);
			session.setAttribute("user", us);
			usession.setUserVO(us);
			session.setAttribute(PkigConstants.SESSION_USER, us);
			session.setAttribute(USER_SESSION,usession);
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.debug(e);			
			errorMsg = "抱歉！系统异常，请联系系统管理员。";
			return ACTION_RESULT_LOGIN;
		}
		return	SUCCESS;
	}
	
	public String doLogout() {
		logger.debug("doLoginOut begin");
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();			
			request = ServletActionContext.getRequest() ;			
			if(session!=null) {
				session.removeAttribute(PkigConstants.SESSION_USER);
				request.getSession().removeAttribute("user");
				request.getSession().removeAttribute(USER_SESSION);
				UserVO user = (UserVO)session.getAttribute(PkigConstants.SESSION_USER);
				if(PkigConstants.ONLINE_USER.containsKey(user.getLoginId())) {
					PkigConstants.ONLINE_USER.remove(user.getLoginId());
				}
			}
		}catch (Exception e) {
			logger.error("session is out.....");
		}
		logger.debug("doLoginOut end");
		return ACTION_RESULT_LOGIN;
	}
	
	public UserVO getUserVo() {
		return userVo;
	}

	public void setUserVO(UserVO userVO) {
		this.userVo = userVO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ResService getResService() {
		return resService;
	}

	public void setResService(ResService resService) {
		this.resService = resService;
	}
}
