package com.kkgame.feeop.user.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.datasource.DataSourceContextHolder;
import com.kkgame.feeop.base.datasource.DataSourceMap;
import com.kkgame.feeop.user.bean.ResVO;
import com.kkgame.feeop.user.bean.RoleResVO;
import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.user.service.ResService;
import com.kkgame.feeop.user.service.RoleResService;
import com.kkgame.feeop.user.service.RoleService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.StringUtilities;

public class RoleAction extends BaseAction {

	private static final String ACTION_RESULT_GRANT_RES = "grantRes";

	private static Log logger = LogFactory.getLog(RoleAction.class);
	
	private RoleVO roleVO;
	
	private List<RoleVO> roleVOList;
	
	private RoleService roleService;
	
	private RoleResService roleResService;
	
	private ResService resService;
	
	private List<ResVO> resVOList;

	private List<ResVO> hasResList;
	
	private RoleResVO roleResVO;
	
	private String resIds;

	public String doList() {
		if(roleVO == null) {
			roleVO = new RoleVO();
		}		
		try {
			
			roleVOList = roleService.getRoleVOList();
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(roleVO == null) {
			roleVO = new RoleVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(roleVO == null) {
			roleVO = new RoleVO();
		}
		try {
			
			roleService.create(roleVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("创建角色成功!");
		return null;
	}
	
	public String doModify() {
		if(roleVO == null) {
			roleVO = new RoleVO();
		}
		try {
			
			roleVO = roleService.getRoleVO(roleVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doUpdate() {
		if(roleVO == null) {
			roleVO = new RoleVO();
		}
		try {
			
			roleService.update(roleVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("修改角色成功!");
		return null;
	}
	
	public String doDelete() {
		if(roleVO==null) {
			roleVO = new RoleVO();
		}
		try {
			
			roleService.delete(roleVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("删除角色成功!");
		return null;
	}
	
	public String doGrantRes() {
		if(roleVO==null) {
			roleVO = new RoleVO();
		}
		try {
			
			roleVO = roleService.getRoleVO(roleVO);
			resVOList = resService.getResByParent(0);
			for(ResVO r:resVOList) {
				r.setChildList(resService.getResByParent(r.getId()));
			}
			hasResList = resService.getResByRole(roleVO.getId());
			resIds = StringUtilities.joinObjectPK(hasResList);
		}  catch (Exception e) {
			logger.debug(e);
			errorMsg="系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}		
		return ACTION_RESULT_GRANT_RES;
	}
	
	public String doSetRes() {
		int roleId = this.getParameterInt("roleid", -1);
		int ret = this.getParameterInt("ret", -1);
		int resId = this.getParameterInt("resid", -1);
		if(roleId == -1 || ret == -1 || resId == -1){
			this.printMessage(RET_FAIL);
			return null;
		}
		roleResVO = new RoleResVO();
		roleResVO.setResId(resId);
		roleResVO.setRoleId(roleId);
		if(ret == 1){
			try {
				
				RoleResVO rrVO = roleResService.existRoleRes(roleResVO);
				if(rrVO==null) {
					roleResService.saveRoleRes(roleResVO);
				}
			} catch (Exception e) {
				logger.debug(e);
				errorMsg="系统出错，请重试或联系管理员";
				this.printMessage(RET_FAIL);
				return null;
			}
		} else {
			
			try {
				roleResService.deleteRoleRes(roleResVO);
			} catch (Exception e) {
				logger.debug(e);
				errorMsg="系统出错，请重试或联系管理员";
				this.printMessage(RET_FAIL);
				return null;
			}
		}
		return null;
	}
	
	public RoleVO getRoleVO() {
		return roleVO;
	}

	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}

	public List<RoleVO> getRoleVOList() {
		return roleVOList;
	}

	public void setRoleVOList(List<RoleVO> roleVOList) {
		this.roleVOList = roleVOList;
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

	public List<ResVO> getResVOList() {
		return resVOList;
	}

	public void setResVOList(List<ResVO> resVOList) {
		this.resVOList = resVOList;
	}
	

	public List<ResVO> getHasResList() {
		return hasResList;
	}

	public void setHasResList(List<ResVO> hasResList) {
		this.hasResList = hasResList;
	}

	public String getResIds() {
		return resIds;
	}

	public void setResIds(String resIds) {
		this.resIds = resIds;
	}

	public RoleResService getRoleResService() {
		return roleResService;
	}

	public void setRoleResService(RoleResService roleResService) {
		this.roleResService = roleResService;
	}
}
