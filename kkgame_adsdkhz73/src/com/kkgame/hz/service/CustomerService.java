package com.kkgame.hz.service;

import java.util.List;
import java.util.Map;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.entities.PortalUserVO;

public interface CustomerService {

	void insert(CustomerVO customerVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	boolean checkCustomerNameExist(CustomerVO customerVO)
			throws DatabaseException;

	List<CustomerVO> getCustomerList(CustomerVO customerVO)
			throws DatabaseException;

	/**
	 * 有效客户列表 至少审核通过
	 */
	List<CustomerVO> getValidCustomerList(CustomerVO customerVO)
			throws DatabaseException;

	CustomerVO getCustomerById(int id) throws DatabaseException;

	void update(CustomerVO customerVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	void delete(int id, int portalUserId) throws DatabaseException;

	void updateStruts(CustomerVO customerVO) throws DatabaseException;

	int getCustomerCountForBd(int bdId) throws DatabaseException;

	int getCustomerCountForAgent(int bdId) throws DatabaseException;

	List<CustomerVO> getAllCustomerList(CustomerVO customerVO)
			throws DatabaseException;

	Map<Integer, Integer> getCuCountOfBdMap() throws DatabaseException;

	Map<Integer, Integer> getCuCountOfAgentMap() throws DatabaseException;

	/** 转移客户 */
	void updateCustomerDevolve(CustomerVO customerVO) throws DatabaseException;

	/** 申请客户 */
	void updateCustomerApply(CustomerVO customerVO) throws DatabaseException;

	/** 根据客户名称 模糊查询已存在的客户 */
	List<CustomerVO> getCustomerExistent(String name);

	List<CustomerVO> getCustomerIdleList(CustomerVO customerVO);

	/**
	 * 收回客户
	 * 
	 * @param customerId
	 *            客户ID
	 * @param Status
	 *            客户状态
	 * @param businessDeveloperId
	 *            所有商务ID
	 * @param relayStatus
	 *            备份客户状态
	 * @param relayBdId
	 *            备份商务ID
	 */
	void updateCallBackCustomer(Integer customerId, Integer status,
			Integer businessDeveloperId, Integer relayStatus,
			Integer relayBdId, String callBackReason) throws DatabaseException;

	/**
	 * 置客户为过期
	 * 
	 * @param projectId
	 *            客户ID
	 * @param Status
	 *            客户状态
	 */
	void updateOvertimeCustomer(Integer customerId, Integer status)
			throws DatabaseException;

	/**
	 * 所有客户列表-不带分页
	 * 
	 * @param status
	 *            客户状态
	 */
	List<CustomerVO> getAllCustomer(List<Integer> status,
			Integer businessDeveloperId, Integer agentId);

	List<CustomerVO> getWholeCustomer(CustomerVO customerVO);

	List<CustomerVO> getCustomerListByBdId(CustomerVO customerVO)
			throws DatabaseException;
}
