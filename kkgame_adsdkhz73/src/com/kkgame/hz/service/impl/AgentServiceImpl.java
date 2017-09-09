package com.kkgame.hz.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.dao.AgentDAO;
import com.kkgame.hz.dao.PortalUserDAO;
import com.kkgame.hz.entities.AgentVO;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.AgentService;

public class AgentServiceImpl implements AgentService {
	private static Log logger = LogFactory.getLog(AgentServiceImpl.class);

	private AgentDAO agentDAO;

	private PortalUserDAO portalUserDAO;

	public void insert(AgentVO agentVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		int id = agentDAO.insert(agentVO);
		agentVO.setId(id);
		portalUserVO.setRoleId(id);
		portalUserVO.setRoleType(PkigConstants.USER_TYPE_AGENT);
		portalUserDAO.insert(portalUserVO);
	}

	public void update(AgentVO agentVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		agentDAO.update(agentVO);
		portalUserDAO.update(portalUserVO);
	}

	public List<AgentVO> getAgentList(AgentVO agentVO) throws DatabaseException {
		return agentDAO.getAgentList(agentVO);
	}

	public AgentVO getAgentById(int id) throws DatabaseException {
		return agentDAO.getAgentById(id);
	}

	public boolean checkAgentNameExist(AgentVO agentVO)
			throws DatabaseException {
		List list = agentDAO.getAgentByCriteria(agentVO);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public void delete(int id, int portalUserId) throws DatabaseException {
		agentDAO.delete(id);
		portalUserDAO.delete(portalUserId);
	}

	public List<AgentVO> getAllAgentList() throws DatabaseException {
		return agentDAO.getAllAgentList();
	}

	public AgentDAO getAgentDAO() {
		return agentDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public PortalUserDAO getPortalUserDAO() {
		return portalUserDAO;
	}

	public void setPortalUserDAO(PortalUserDAO portalUserDAO) {
		this.portalUserDAO = portalUserDAO;
	}

}
