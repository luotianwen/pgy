package com.kkgame.hz.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.PortalUserService;

public class PortalUserAction extends BaseAction {
	private static Log logger = LogFactory.getLog(PortalUserAction.class);
	private PortalUserService portalUserService;
	private PortalUserVO portalUserVO;
	private List portalUserList;

	public String validLoginId() throws UnsupportedEncodingException {
		boolean flag;
		try {
			flag = portalUserService.checkPortalUserLoginIdExist(portalUserVO);
			if (flag == true) {
				this.addActionMessage("true");
			} else {
				this.addActionMessage("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PlAIN_MESSAGE;
	}

	public String doCreate() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		try {

			portalUserVO.setRoleId(0);
			portalUserService.insert(portalUserVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "创建失败！请重试";
			return doCreate();
		}
		errorMsg = "创建成功";
		return doList();
	}

	public String doList() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		try {
			portalUserList = portalUserService.getPortalUserList(portalUserVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "抱歉！系统异常，请联系系统管理员。";
			return ACTION_RESULT_LIST;
		}
		return ACTION_RESULT_LIST;
	}

	public String doModify() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		try {
			portalUserVO = portalUserService.getPortalUserById(portalUserVO
					.getId());
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "抱歉！系统异常，请联系系统管理员。";
			return doList();
		}
		return ACTION_RESULT_MODIFY;
	}

	public String doUpdate() {
		logger.debug(portalUserVO.getLoginId());
		try {
			portalUserService.update(portalUserVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "修改失败，请重试！";
			return doModify();
		}
		errorMsg = "更新成功！";
		return doList();
	}

	public String doRemove() {
		if (portalUserVO == null) {
			logger.info("portalUserVO is null");
			portalUserVO = new PortalUserVO();
		}
		try {
			portalUserService.delete(portalUserVO.getId());
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "抱歉！系统异常，请联系系统管理员。";
			return doList();
		}
		return doList();
	}

	public List getPortalUserList() {
		return portalUserList;
	}

	public void setPortalUserList(List portalUserList) {
		this.portalUserList = portalUserList;
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

}
