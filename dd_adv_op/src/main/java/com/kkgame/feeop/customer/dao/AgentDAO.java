package com.kkgame.feeop.customer.dao;

import java.util.List;

import com.kkgame.feeop.customer.bean.AgentVO;
import com.kkgame.feeop.util.DatabaseException;


public interface AgentDAO {
	public int insert(AgentVO agentVO) throws DatabaseException;

	public void update(AgentVO agentVO) throws DatabaseException;

	public void delete(int id) throws DatabaseException;

	public AgentVO getAgentById(int id) throws DatabaseException;

	public List<AgentVO> getAgentList(AgentVO agentVO) throws DatabaseException;

	public List<AgentVO> getAgentByCriteria(AgentVO agentVO)
			throws DatabaseException;

	public List<AgentVO> getAllAgentList() throws DatabaseException;
}
