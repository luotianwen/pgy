package com.kkgame.feeop.customer.service;

import java.util.List;

import com.kkgame.feeop.customer.bean.AgentVO;
import com.kkgame.feeop.customer.bean.PortalUserVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AgentService {

	public void insert(AgentVO agentVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	public boolean checkAgentNameExist(AgentVO agentVO)
			throws DatabaseException;

	public List<AgentVO> getAgentList(AgentVO agentVO) throws DatabaseException;

	public AgentVO getAgentById(int id) throws DatabaseException;

	public void update(AgentVO agentVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	public void delete(int id, int portalUserId) throws DatabaseException;

	public List<AgentVO> getAllAgentList() throws DatabaseException;
}
