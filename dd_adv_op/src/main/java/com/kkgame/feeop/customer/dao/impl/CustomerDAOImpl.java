package com.kkgame.feeop.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.customer.bean.CustomerVO;
import com.kkgame.feeop.customer.dao.CustomerDAO;
import com.kkgame.feeop.util.DatabaseException;


public class CustomerDAOImpl extends SqlMapClientDaoSupport implements
		CustomerDAO {

	private static Log logger = LogFactory.getLog(CustomerDAOImpl.class);

	public void delete(int id) throws DatabaseException {
		getSqlMapClientTemplate().update("customerSqlMap.deleteCustomer", id);
	}

	public List<CustomerVO> getCustomerList(CustomerVO customerVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"customerSqlMap.getCustomerListCount", customerVO);
		customerVO.getPage().setTotalRow(count);

		logger.debug("list.size:" + count);
		return (List<CustomerVO>) getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getCustomerList", customerVO);
	}

	public List<CustomerVO> getValidCustomerList(CustomerVO customerVO)
			throws DatabaseException {
		return (List<CustomerVO>) getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getValidCustomerList", customerVO);
	}

	public CustomerVO getCustomerById(int id) throws DatabaseException {
		return (CustomerVO) getSqlMapClientTemplate().queryForObject(
				"customerSqlMap.getCustomerById", id);
	}

	public int insert(CustomerVO customerVO) throws DatabaseException {
		Object id = getSqlMapClientTemplate().insert("customerSqlMap.insert",
				customerVO);
		return Integer.parseInt(id.toString());
	}

	public void updateCustomer(CustomerVO customerVO) throws DatabaseException {
		getSqlMapClientTemplate().update("customerSqlMap.updateCustomer",
				customerVO);
	}

	public List<CustomerVO> getCustomerByCriteria(CustomerVO customerVO)
			throws DatabaseException {
		return (List<CustomerVO>) getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getCustomerByCriteria", customerVO);
	}

	public void updateStruts(CustomerVO customerVO) throws DatabaseException {
		getSqlMapClientTemplate().update("customerSqlMap.updateCustomerStruts",
				customerVO);
	}

	public int getCustomerCountForBd(int bdId) throws DatabaseException {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"customerSqlMap.getCustomerCountByBd", bdId);
	}

	public int getCustomerCountForAgent(int agentId) throws DatabaseException {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"customerSqlMap.getCustomerCountByAgent", agentId);
	}

	public List<CustomerVO> getAllCustomerList(CustomerVO customerVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"customerSqlMap.getAllCustomerListCount", customerVO);
		customerVO.getPage().setTotalRow(count);
		logger.debug("list.size:" + count);
		return (List<CustomerVO>) getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getAllCustomerList", customerVO);

	}

	public Map<Integer, Integer> getCuCountOfBdMap() throws DatabaseException {
		return getSqlMapClientTemplate().queryForMap(
				"customerSqlMap.getCuCountOfBdMap", "", "id", "count");
	}

	public Map<Integer, Integer> getCuCountOfAgentMap()
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForMap(
				"customerSqlMap.getCuCountOfAgentMap", "", "id", "count");
	}

	public void udateProjectToBd(CustomerVO customerVO)
			throws DatabaseException {
		getSqlMapClientTemplate().update("customerSqlMap.udateProjectToBd",
				customerVO);
	}

	public void udateProjectCooperateByBd(CustomerVO customerVO)
			throws DatabaseException {
		getSqlMapClientTemplate().update(
				"customerSqlMap.udateProjectCooperateByBd", customerVO);
	}

	public List<CustomerVO> getCustomerExistent(String name) {
		String sqlId = "customerSqlMap.getCustomerExistent";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);

		return super.getSqlMapClientTemplate().queryForList(sqlId, map);
	}

	public List<CustomerVO> getCustomerIdleList(CustomerVO customerVO) {
		String sqlId = "customerSqlMap.getCustomerIdleList";
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"customerSqlMap.getCustomerIdleListCount", customerVO);
		customerVO.getPage().setTotalRow(count);
		return super.getSqlMapClientTemplate().queryForList(sqlId, customerVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kkfun.hz.dao.CustomerDAO#getAllCustomer(java.lang.Integer[])
	 */
	public List<CustomerVO> getAllCustomer(List<Integer> status,
			Integer businessDeveloperId, Integer agentId) {
		String sqlId = "customerSqlMap.getAllCustomer";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("businessDeveloperId", businessDeveloperId);
		map.put("agentId", agentId);
		return getSqlMapClientTemplate().queryForList(sqlId, map);
	}

	public void update(CustomerVO customerVO) throws DatabaseException {
		getSqlMapClientTemplate().update("customerSqlMap.update", customerVO);
	}

	public List<CustomerVO> getWholeCustomer(CustomerVO customerVO) {
		return getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getWholeCustomerList", customerVO);
	}

	@SuppressWarnings("unchecked")
	public List<CustomerVO> getCustomerListByBdId(CustomerVO customerVO)
			throws DatabaseException {
		return this.getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getCustomerListByBdId", customerVO);
	}
	
	@Override
	public List<CustomerVO> getAllCustomerForSelect(CustomerVO customerVO) {
		return this.getSqlMapClientTemplate().queryForList(
				"customerSqlMap.getAllCustomerForSelect", customerVO);
	}
}
