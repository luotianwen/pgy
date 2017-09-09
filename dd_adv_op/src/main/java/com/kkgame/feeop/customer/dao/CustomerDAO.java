package com.kkgame.feeop.customer.dao;

import java.util.List;
import java.util.Map;

import com.kkgame.feeop.customer.bean.CustomerVO;
import com.kkgame.feeop.util.DatabaseException;


public interface CustomerDAO {
	int insert(CustomerVO customerVO) throws DatabaseException;

	void updateCustomer(CustomerVO customerVO) throws DatabaseException;

	void delete(int id) throws DatabaseException;

	CustomerVO getCustomerById(int id) throws DatabaseException;

	List<CustomerVO> getCustomerList(CustomerVO customerVO)
			throws DatabaseException;

	List<CustomerVO> getValidCustomerList(CustomerVO customerVO)
			throws DatabaseException;

	List<CustomerVO> getCustomerByCriteria(CustomerVO customerVO)
			throws DatabaseException;

	void updateStruts(CustomerVO customerVO) throws DatabaseException;

	int getCustomerCountForBd(int bdId) throws DatabaseException;

	int getCustomerCountForAgent(int bdId) throws DatabaseException;

	List<CustomerVO> getAllCustomerList(CustomerVO customerVO)
			throws DatabaseException;

	Map<Integer, Integer> getCuCountOfBdMap() throws DatabaseException;

	Map<Integer, Integer> getCuCountOfAgentMap() throws DatabaseException;

	/** 项目随客户转移给新商务拓展人员 */
	void udateProjectToBd(CustomerVO customerVO) throws DatabaseException;

	/** 更改项目合作条件里的BD */
	void udateProjectCooperateByBd(CustomerVO customerVO)
			throws DatabaseException;

	List<CustomerVO> getCustomerExistent(String name);

	/** 闲置客户列表 */
	List<CustomerVO> getCustomerIdleList(CustomerVO customerVO);


	List<CustomerVO> getAllCustomer(List<Integer> status,
			Integer businessDeveloperId, Integer agentId);

	void update(CustomerVO customerVO) throws DatabaseException;

	List<CustomerVO> getWholeCustomer(CustomerVO customerVO);

	List<CustomerVO> getCustomerListByBdId(CustomerVO customerVO)
			throws DatabaseException;

	List<CustomerVO> getAllCustomerForSelect(CustomerVO customerVO);

}
