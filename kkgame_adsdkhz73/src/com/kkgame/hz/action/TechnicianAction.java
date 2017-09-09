package com.kkgame.hz.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.entities.PortalUserVO;

import com.kkgame.hz.service.PortalUserService;
import com.kkgame.hz.util.UtilHelper;

public class TechnicianAction extends BaseAction {
	private static Log logger = LogFactory.getLog(TechnicianAction.class);
	private PortalUserVO portalUserVO;
	private List portalUserList = new ArrayList();

	public String doQuery() {
		if(portalUserVO==null) {
			portalUserVO = new PortalUserVO();
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doCreate() {
		if(portalUserVO==null) {
			portalUserVO = new PortalUserVO();
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		PortalUserService portalUserService = (PortalUserService) getServiceObject(UtilHelper.PORTALUSER_SERVICE);
		try {
			// set roleType
			portalUserVO.setRoleType(PkigConstants.USER_TYPE_TECHNICIAN);
			portalUserVO.setRoleId(0);
			portalUserService.insert(portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("创建技术人员成功");
		return null;
	}

	public String doList() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		PortalUserService portalUserService = (PortalUserService) getServiceObject(UtilHelper.PORTALUSER_SERVICE);
		try {
			portalUserVO.setRoleType(PkigConstants.USER_TYPE_TECHNICIAN);
			portalUserList = portalUserService.getPortalUserList(portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doModify() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		PortalUserService portalUserService = (PortalUserService) getServiceObject(UtilHelper.PORTALUSER_SERVICE);
		try {
			portalUserVO = portalUserService.getPortalUserById(portalUserVO.getId());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}

	public String doUpdate() {
		logger.debug(portalUserVO.getLoginId());
		PortalUserService portalUserService = (PortalUserService) getServiceObject(UtilHelper.PORTALUSER_SERVICE);
		try {
			portalUserService.update(portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("更新技术人员成功");
		return null;
	}

	public String doRemove() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		PortalUserService portalUserService = (PortalUserService) getServiceObject(UtilHelper.PORTALUSER_SERVICE);
		try {
			portalUserService.delete(portalUserVO.getId());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("删除技术人员成功");
		return null;
	}

	public void setPortalUserVO(PortalUserVO portalUserVO) {
		this.portalUserVO = portalUserVO;
	}

	public PortalUserVO getPortalUserVO() {
		return portalUserVO;
	}

	public List getPortalUserList() {
		return portalUserList;
	}

	public void setPortalUserList(List portalUserList) {
		this.portalUserList = portalUserList;
	}

}
