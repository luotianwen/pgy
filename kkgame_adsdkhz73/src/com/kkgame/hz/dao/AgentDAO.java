package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.AgentVO;

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
