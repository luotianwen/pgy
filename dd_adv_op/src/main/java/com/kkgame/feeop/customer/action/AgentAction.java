package com.kkgame.feeop.customer.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.customer.bean.AgentVO;
import com.kkgame.feeop.customer.bean.PortalUserVO;
import com.kkgame.feeop.customer.service.AgentService;
import com.kkgame.feeop.util.DatabaseException;


public class AgentAction extends BaseAction {

	private static Log logger = LogFactory.getLog(AgentAction.class);

	private AgentVO agentVO;

	private PortalUserVO portalUserVO;

	private List agentList;

	private AgentService agentService;

//	private PortalUserService portalUserService;

	public String doQuery() {
		return ACTION_RESULT_QUERY;
	}
	
	public String doCreate() {
		if (agentVO == null) {
			logger.info("agentVO is null");
			agentVO = new AgentVO();
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if (agentVO == null) {
			logger.info("agentVO is null");
			agentVO = new AgentVO();
		}
		try {
			agentService.insert(agentVO, portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("创建代理商成功!");
		return null;
	}

	public String doList() {
		if (agentVO == null) {
			logger.info("agentVO is null");
			agentVO = new AgentVO();
		}
		try {
			agentList = agentService.getAgentList(agentVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doModify() {
		if (agentVO == null) {
			logger.info("agentVO is null");
			agentVO = new AgentVO();
		}
		int id = agentVO.getId();
		int portalUserId = agentVO.getPortalUserId();
		try {
			agentVO = agentService.getAgentById(id);
//			portalUserVO = portalUserService.getPortalUserById(portalUserId);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}

	public String doUpdate() {
		logger.debug(agentVO.getName());
		try {
			agentService.update(agentVO, portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			agentVO.setPortalUserId(portalUserVO.getId());
			return null;
		}
		printMessage("修改代理商成功!");
		return null;
	}

	public String doRemove() {
		if (agentVO == null) {
			logger.info("agentVO is null");
			agentVO = new AgentVO();
		}
		try {
			agentService.delete(agentVO.getId(), agentVO.getPortalUserId());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("删除代理商信息成功!");
		return null;
	}

	public String doDetail() {
		if (agentVO == null) {
			logger.info("agentVO is null");
			agentVO = new AgentVO();
		}
		int id = agentVO.getId();
		int portalUserId = agentVO.getPortalUserId();
		try {
			agentVO = agentService.getAgentById(id);
//			portalUserVO = portalUserService.getPortalUserById(portalUserId);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public String doValidName() throws UnsupportedEncodingException {
		boolean flag;
		try {
			flag = agentService.checkAgentNameExist(agentVO);
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

	public String getAllAgent() {
		try {
			agentList = agentService.getAllAgentList();
		} catch (DatabaseException e) {
			logger.debug(e);
		}
		return SUCCESS;
	}

	public PortalUserVO getPortalUserVO() {
		return portalUserVO;
	}

	public void setPortalUserVO(PortalUserVO portalUserVO) {
		this.portalUserVO = portalUserVO;
	}

	public AgentVO getAgentVO() {
		return agentVO;
	}

	public void setAgentVO(AgentVO agentVO) {
		this.agentVO = agentVO;
	}

	public List getAgentList() {
		return agentList;
	}

	public void setAgentList(List agentList) {
		this.agentList = agentList;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

//	public void setPortalUserService(PortalUserService portalUserService) {
//		this.portalUserService = portalUserService;
//	}

	public AgentService getAgentService() {
		return agentService;
	}

//	public PortalUserService getPortalUserService() {
//		return portalUserService;
//	}

}
