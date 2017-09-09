package com.kkgame.feeop.customer.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.customer.bean.AgentVO;
import com.kkgame.feeop.customer.dao.AgentDAO;
import com.kkgame.feeop.util.DatabaseException;


public class AgentDAOImpl extends SqlMapClientDaoSupport implements AgentDAO {

	private static Log logger = LogFactory.getLog(AgentDAOImpl.class);

	public void delete(int id) throws DatabaseException {
		getSqlMapClientTemplate().update("agentSqlMap.deleteAgent", id);
	}

	public List<AgentVO> getAgentList(AgentVO agentVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"agentSqlMap.getAgentListCount", agentVO);
		agentVO.getPage().setTotalRow(count);
		return (List<AgentVO>) getSqlMapClientTemplate().queryForList(
				"agentSqlMap.getAgentList", agentVO);
	}

	public AgentVO getAgentById(int id) throws DatabaseException {
		return (AgentVO) getSqlMapClientTemplate().queryForObject(
				"agentSqlMap.getAgentById", id);
	}

	public int insert(AgentVO agentVO) throws DatabaseException {
		Object id = getSqlMapClientTemplate().insert("agentSqlMap.insert",
				agentVO);
		return Integer.parseInt(id.toString());
	}

	public void update(AgentVO agentVO) throws DatabaseException {
		getSqlMapClientTemplate().update("agentSqlMap.updateAgent", agentVO);
	}

	public List<AgentVO> getAgentByCriteria(AgentVO agentVO)
			throws DatabaseException {
		return (List<AgentVO>) getSqlMapClientTemplate().queryForList(
				"agentSqlMap.getAgentByCriteria", agentVO);
	}

	public List<AgentVO> getAllAgentList() throws DatabaseException {
		return (List<AgentVO>) getSqlMapClientTemplate().queryForList(
				"agentSqlMap.getAllAgentList");
	}

}
