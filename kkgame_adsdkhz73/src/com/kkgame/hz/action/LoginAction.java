package com.kkgame.hz.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.base.PkigLog;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.entities.SettingVO;
import com.kkgame.hz.service.AgentService;
import com.kkgame.hz.service.BdService;
import com.kkgame.hz.service.BhService;
import com.kkgame.hz.service.CustomerService;
import com.kkgame.hz.service.PortalUserService;
import com.kkgame.hz.util.MD5Util;



public class LoginAction extends BaseAction {
	private static Log logger = LogFactory.getLog(LoginAction.class);
	public final static String LOGIN_VIEW_ZHONGZHENG = "zhongzheng_login";
	public final static String LOGIN_SUCCESS_ZHONGZHENG = "zhongzheng_success";
	
	private PortalUserVO portalUserVO;		
	private SettingVO settingVO;
	private PortalUserService portalUserService;
	private AgentService agentService;
	private BdService bdService;
	private BhService bhService  ;
	private CustomerService customerService ; 
	
	public final static String LOGIN_SUCCESS = "success";
	public final static String LOGIN_FAILD = "login";
	public final static String LOGIN_VIEW = "login";
	public final static String MODIFY_PASSWD = "modifypasswd";
	private static final String ACTION_RESULT_FIRST_PAGE = "firstPage";
	HttpServletRequest request;
	  
	public String doLogin(){
		logger.debug("登陆");
		if(null==portalUserVO) {
			portalUserVO=new PortalUserVO();
			errorMsg="用户名为空,请输入后登录！";
			return doLoginFaild();
		}
		if (portalUserVO.getLoginId()==null || "".equals(portalUserVO.getLoginId())){
			errorMsg = "请输入登录名！";
			return doLoginFaild();
		}
		if(portalUserVO.getLoginId().equalsIgnoreCase("youjieqing")) {
			errorMsg = "用户名不存在！";
			return doLoginFaild();
		}
		if (portalUserVO.getPasswd()==null || "".equals(portalUserVO.getPasswd())){
			errorMsg = "请输入密码！";
			return doLoginFaild();
		}
		String loginId = portalUserVO.getLoginId();
		String passwd = portalUserVO.getPasswd();
		// 统一为小写的
		loginId = loginId.toLowerCase();
		passwd = passwd.toLowerCase();
		HttpSession session = ServletActionContext.getRequest().getSession();
		request = ServletActionContext.getRequest() ;
		if (loginId == null || "".equals(loginId.trim())) {
			portalUserVO = new PortalUserVO();
			errorMsg = "登录用户名不能为空。";
			return doLoginFaild();
		}
		if (passwd == null || "".equals(passwd.trim())) {
			errorMsg="登录密码不能为空。";
			return doLoginFaild();
		}
		try {
			
			portalUserVO = portalUserService.getPortalUserByLoginId(loginId);
			if ( null == portalUserVO) {
				portalUserVO = new PortalUserVO();
				errorMsg = "登录用户名不存在。";
				return doLoginFaild();
			}
			//管理员需MD5加密
			if(portalUserVO.getLoginId().equals("super")){
			    if (!MD5Util.MD5(passwd).toLowerCase().equals(portalUserVO.getPasswd().toLowerCase())) {
                    errorMsg = "密码错误，请重新尝试。";
                    return doLoginFaild();
                }
			}else{
    			if (!passwd.equals(portalUserVO.getPasswd().toLowerCase()) && !MD5Util.MD5(passwd).toLowerCase().equals(portalUserVO.getPasswd().toLowerCase())) {
    				errorMsg = "密码错误，请重新尝试。";
    				return doLoginFaild();
    			}
			}
			if (portalUserVO.getStatus() == PkigConstants.USER_STATUS_LOCKED) {
				errorMsg = "您的账号已锁定，请联系管理员！";
				return doLoginFaild();
			}
			PortalUserVO  vo = new PortalUserVO();
			vo.setId(portalUserVO.getId());
			vo.setLastLogin(request.getRemoteAddr());
			if (portalUserVO.getPasswd().length()==32){
				vo.setPasswd(passwd);
			}
			portalUserService.updateUserLastLogin(vo);
			//代理商
			if(portalUserVO.getRoleType().equals(PkigConstants.USER_TYPE_AGENT)){
				 
				session.setAttribute(PkigConstants.SESSION_AGENT,agentService.getAgentById(portalUserVO.getRoleId()));
			}
			//用户类型-商务拓展人员
			if(portalUserVO.getRoleType().equals(PkigConstants.USER_TYPE_BD)){
				
				session.setAttribute(PkigConstants.SESSION_BD,bdService.getBdById(portalUserVO.getRoleId()));
			}	
			//用户类型-商务协助人员
			if(portalUserVO.getRoleType().equals(PkigConstants.USER_TYPE_BH)){				
				session.setAttribute(PkigConstants.SESSION_BH,bhService.getBhById(portalUserVO.getRoleId()));
			}
			//用户类型-客户
			if(portalUserVO.getRoleType().equals(PkigConstants.USER_TYPE_CUSTOMER)){				
				session.setAttribute(PkigConstants.SESSION_CUSTOMER,customerService.getCustomerById(portalUserVO.getRoleId()));
			}
			session.setAttribute(PkigConstants.SESSION_PORTALUSER, portalUserVO);
			errorMsg = " 登录成功  !";			
		} catch (DatabaseException e) {
			logger.debug(e);
			return doLoginFaild();
		}
		
		return LOGIN_SUCCESS;
	}

	public String  doLoginFaild(){
		return LOGIN_FAILD;
	}
	
	public String doLogout(){
		request = ServletActionContext.getRequest() ;
		if(null != request.getSession() ){
			request.getSession().invalidate();
		}
		return LOGIN_VIEW;
	}
	
	public String doGoModifyPasswd(){
		
		if (settingVO == null) {
			settingVO = new SettingVO();
		}
		PkigLog.debug("doGoModifyPasswd ");		
		return MODIFY_PASSWD;
	}

	public String doUpdatePasswd(){
		PortalUserVO portalUserVO = (PortalUserVO) ServletActionContext.getRequest().getSession().getAttribute(PkigConstants.SESSION_PORTALUSER);
		if (portalUserVO == null) {
			return LOGIN_FAILD;
		}
		String sessionPwd = portalUserVO.getPasswd();
		if(portalUserVO.getLoginId().equals("super")){
		    settingVO.setOldPwd(MD5Util.MD5(settingVO.getOldPwd()));
		    settingVO.setNewPwd(MD5Util.MD5(settingVO.getNewPwd()));
		    settingVO.setConfirmPwd(MD5Util.MD5(settingVO.getConfirmPwd()));		    
		}
		if (!settingVO.getOldPwd().equals(sessionPwd)) {
			printMessage("0");
			return null;
		} else {
			try {
				portalUserVO.setPasswd(settingVO.getNewPwd());
				portalUserService.updatePasswd(portalUserVO);
				errorMsg="密码更改成功.";
			} catch (Exception e) {
				logger.debug(e);
				printMessage("-1");
				return null;
			}
		}
		printMessage("密码更新成功!");
		return null;
	}
	
	public String doFirstPage() {
		return ACTION_RESULT_FIRST_PAGE;
	}
	
	public String doLoginView(){
		return LOGIN_VIEW;
	}

	public PortalUserVO getPortalUserVO() {
		return portalUserVO;
	}

	public void setPortalUserVO(PortalUserVO portalUserVO) {
		this.portalUserVO = portalUserVO;
	}

	public void setPortalUserService(PortalUserService portalUserService) {
		this.portalUserService = portalUserService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

	public void setBdService(BdService bdService) {
		this.bdService = bdService;
	}

	public void setBhService(BhService bhService) {
		this.bhService = bhService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public SettingVO getSettingVO() {
		return settingVO;
	}

	public void setSettingVO(SettingVO settingVO) {
		this.settingVO = settingVO;
	}
}
