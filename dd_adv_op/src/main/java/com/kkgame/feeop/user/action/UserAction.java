package com.kkgame.feeop.user.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.datasource.DataSourceContextHolder;
import com.kkgame.feeop.base.datasource.DataSourceMap;
import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.user.bean.UserRoleVO;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.user.service.ResService;
import com.kkgame.feeop.user.service.RoleService;
import com.kkgame.feeop.user.service.UserRoleService;
import com.kkgame.feeop.user.service.UserService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.KokMd5Encrypt;
import com.kkgame.feeop.util.StringUtilities;

public class UserAction extends BaseAction {

	private static final String ACTION_RESULT_GRANT_ROLE = "grantRole";

	private static final String ACTION_RESULT_MODIFY_PASSWD = "modifyPasswd";

	private static Log logger = LogFactory.getLog(UserAction.class);
	
	private UserVO userVO;
	
	private List<UserVO> userVOList;
	
	private UserService userService;
	
	private RoleService roleService;
	
	private ResService resService;
	
	private UserRoleService userRoleService;
	
	private List<RoleVO> roleVOList;

	private List<RoleVO> hasRoleList;
	
	private String roleIds;
	
	public String doList() {
		if(userVO == null) {
			userVO = new UserVO();
		}		
		try {
			
			userVOList = userService.getUserVOList(userVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
		}		
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		userVO = new UserVO();
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {		
		if(userVO == null) {
			userVO = new UserVO();
		}
		try {
			
			userService.create(userVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("创建用户信息成功!");
		return null;
	}
	
	public String  doExist() {
		String loginId = getParameter("loginId");
		try {
			
			UserVO u = userService.getUserVOByLoginId(loginId);
			if(u==null) {
				this.printMessage(RET_SUCCESS);
				return null;
			} else {
				this.printMessage(RET_FAIL);
				return null;
			}
		} catch (DatabaseException e) {
			logger.debug(e);
			this.printMessage(RET_FAIL);
		} catch (Exception e) {
			this.printMessage(RET_FAIL);
		}
		return null;
	}
	
	public String doPasswd() {
		String passwd = getParameter("passwd");
		HttpSession session = ServletActionContext.getRequest().getSession();		
		userVO = (UserVO) session.getAttribute("user");
		if(userVO.getLoginId().equalsIgnoreCase("manager")) {
			if(!KokMd5Encrypt.md5(passwd).equalsIgnoreCase(userVO.getPasswd())) {
				this.printMessage(RET_FAIL);
				return null;
			}
		} else {
			if(!passwd.equalsIgnoreCase(userVO.getPasswd())) {
				this.printMessage(RET_FAIL);
				return null;
			}
		}
		
		this.printMessage(RET_SUCCESS);
		return null;
	}
	
	public String doModify() {
		if(userVO == null) {
			userVO = new UserVO();
		}
		try {
			
			userVO = userService.getUserVOById(userVO.getId());
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doUpdate() {
		if(userVO == null) {
			userVO = new UserVO();
		}	
		try {
			
			userService.update(userVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("修改用户信息成功!");
		return null;
	}
	
	public String doDelete() {
		if(userVO == null) {
			userVO = new UserVO();
		}		
		try {
			
			userService.delete(userVO.getId());
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("删除用户信息成功!");
		return null;
	}

	public String doUpdateStatus() {
		if(userVO == null) {
			userVO = new UserVO();
		}	
		try {
			
			userService.updateStatus(userVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		if(userVO.getStatus()==0) {
			printMessage("解锁用户信息成功!");
		} else if(userVO.getStatus()==1) {
			printMessage("锁定用户信息成功!");
		}
		return null;
	}
	
	public String doGrantRole() {
		if(userVO == null) {
			userVO = new UserVO();
		}
		try {
			
			userVO = userService.getUserVOById(userVO.getId());
			roleVOList = roleService.getRoleVOList();
			hasRoleList = roleService.getRoleVOListByUser(userVO.getId());
			StringBuilder sb = new StringBuilder();
			for(RoleVO roleVO:hasRoleList) {
				sb.append(roleVO.getId()).append(",");
			}
			if(sb.toString().length() > 0){
				roleIds = sb.deleteCharAt(sb.length()-1).toString();
			}
			roleIds = StringUtilities.joinObjectPK(hasRoleList);
			
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg="系统出错，请重试或联系管理员";
		} catch (Exception e) {
			logger.debug(e);
			errorMsg="系统出错，请重试或联系管理员";
		}
		return ACTION_RESULT_GRANT_ROLE;
		
	}
	
	public String doSetPermission() {
		int puid = this.getParameterInt("puid", -1);
		int ret = this.getParameterInt("ret", -1);
		int roleid = this.getParameterInt("roleid", -1);
		if(puid == -1 || ret == -1 || roleid == -1){
			this.printMessage(RET_FAIL);
			return null;
		}
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO.setRoleId(roleid);
		userRoleVO.setUserId(puid);
		if(ret == 1){			
			try {
				
				UserRoleVO ur = userRoleService.existsUserRole(userRoleVO);
				if(ur==null) {
					userRoleService.create(userRoleVO);
				}
			} catch (Exception e) {
				logger.debug(e);
				errorMsg="系统出错，请重试或联系管理员";
				this.printMessage(RET_FAIL);
				return null;
			}
		}else{
			try {
				
				userRoleService.delete(userRoleVO);
			} catch (Exception e) {
				logger.debug(e);
				errorMsg="系统出错，请重试或联系管理员";
				this.printMessage(RET_FAIL);
				return null;
			}
		}			
		return null;
	}
	
	public String doModifyPasswd() {
		HttpSession session = ServletActionContext.getRequest().getSession();		
		userVO = (UserVO) session.getAttribute("user");
		return ACTION_RESULT_MODIFY_PASSWD;
	}
	
	public String doUpdatePasswd() {
		String newpass = getParameter("newpass");
		HttpSession session = ServletActionContext.getRequest().getSession();		
		userVO = (UserVO) session.getAttribute("user");
		String newPass = "";
		if(userVO.getLoginId().equalsIgnoreCase("manager")) {
			newPass = KokMd5Encrypt.md5(newpass);
			userVO.setPasswd(newPass);			
		} else {
			userVO.setPasswd(newpass);		
		}		
		try {
			
			userService.updatePasswd(userVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg="系统出错，请重试或联系管理员";
			this.printMessage(RET_FAIL);
			return null;
		}
		this.printMessage(RET_SUCCESS);
		return null;
	}
	
	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public List<UserVO> getUserVOList() {
		return userVOList;
	}

	public void setUserVOList(List<UserVO> userVOList) {
		this.userVOList = userVOList;
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

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	

	public List<RoleVO> getRoleVOList() {
		return roleVOList;
	}

	public void setRoleVOList(List<RoleVO> roleVOList) {
		this.roleVOList = roleVOList;
	}

	public List<RoleVO> getHasRoleList() {
		return hasRoleList;
	}

	public void setHasRoleList(List<RoleVO> hasRoleList) {
		this.hasRoleList = hasRoleList;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
