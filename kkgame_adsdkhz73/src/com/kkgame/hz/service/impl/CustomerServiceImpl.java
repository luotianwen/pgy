package com.kkgame.hz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.dao.CustomerDAO;
import com.kkgame.hz.dao.PortalUserDAO;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.CustomerService;
import com.kkgame.hz.tag.TagDAO;
import com.kkgame.hz.util.CalendarFormat;

public class CustomerServiceImpl implements CustomerService {
	private static Log logger = LogFactory.getLog(CustomerServiceImpl.class);
	private CustomerDAO customerDAO;
	private PortalUserDAO portalUserDAO;
	private TagDAO tagDAO;

	public void insert(CustomerVO customerVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		int id = customerDAO.insert(customerVO);
		customerVO.setId(id);
		portalUserVO.setRoleId(id);
		portalUserVO.setRoleType(PkigConstants.USER_TYPE_CUSTOMER);
		portalUserDAO.insert(portalUserVO);
	}

	public void update(CustomerVO customerVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		customerDAO.updateCustomer(customerVO);
		portalUserDAO.update(portalUserVO);
	}

	public List<CustomerVO> getCustomerList(CustomerVO customerVO)
			throws DatabaseException {
		List<CustomerVO> list = customerDAO.getCustomerList(customerVO);
		// 45天 60天过期客户分类
		if (customerVO.getStatus() == PkigConstants.CUSTOMER_STATUS_OVERDUE) {
			for (CustomerVO vo : list.toArray(new CustomerVO[0])) {
				if (customerVO.getExpiryDate() == 45) {
					if (vo.getExpiryDate() >= 60) {
						list.remove(vo);
						customerVO.getPage().setTotalRow(
								customerVO.getPage().getTotalRow() - 1);
					}
				} else if (customerVO.getExpiryDate() == 60) {
					if (vo.getExpiryDate() < 60) {
						list.remove(vo);
						customerVO.getPage().setTotalRow(
								customerVO.getPage().getTotalRow() - 1);
					}
				}

			}
		}

		return list;
	}

	public List<CustomerVO> getValidCustomerList(CustomerVO customerVO)
			throws DatabaseException {
		return customerDAO.getValidCustomerList(customerVO);
	}

	public CustomerVO getCustomerById(int id) throws DatabaseException {
		return customerDAO.getCustomerById(id);
	}

	public boolean checkCustomerNameExist(CustomerVO customerVO)
			throws DatabaseException {
		List list = customerDAO.getCustomerByCriteria(customerVO);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public void delete(int id, int portalUserId) throws DatabaseException {
		customerDAO.delete(id);
		portalUserDAO.delete(portalUserId);
	}

	public List<CustomerVO> getCustomerExistent(String name) {
		return customerDAO.getCustomerExistent(name);
	}

	public void updateStruts(CustomerVO customerVO) throws DatabaseException {
		customerDAO.updateStruts(customerVO);
	}

	public int getCustomerCountForBd(int bdId) throws DatabaseException {
		return customerDAO.getCustomerCountForBd(bdId);
	}

	public int getCustomerCountForAgent(int bdId) throws DatabaseException {
		return customerDAO.getCustomerCountForAgent(bdId);
	}

	public List<CustomerVO> getAllCustomerList(CustomerVO customerVO)
			throws DatabaseException {
		List<CustomerVO> list = new ArrayList();
		list = customerDAO.getAllCustomerList(customerVO);
		Map<Integer, String> bdMap = new HashMap<Integer, String>();
		bdMap = tagDAO.getBdMap();
		for (CustomerVO vo : list) {
			if (vo.getRelayBdId() > 0) {
				if (bdMap.containsKey(vo.getRelayBdId())) {
					vo.setRelayBdName(bdMap.get(vo.getRelayBdId()));
				}
			}
		}
		return list;
	}

	public Map<Integer, Integer> getCuCountOfBdMap() throws DatabaseException {
		return customerDAO.getCuCountOfBdMap();
	}

	public Map<Integer, Integer> getCuCountOfAgentMap()
			throws DatabaseException {
		return customerDAO.getCuCountOfAgentMap();
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public PortalUserDAO getPortalUserDAO() {
		return portalUserDAO;
	}

	public void setPortalUserDAO(PortalUserDAO portalUserDAO) {
		this.portalUserDAO = portalUserDAO;
	}

	public void updateCustomerDevolve(CustomerVO customerVO)
			throws DatabaseException {
		customerDAO.updateStruts(customerVO);
		//customerDAO.udateProjectToBd(customerVO);
		//customerDAO.udateProjectCooperateByBd(customerVO);
	}

	public void updateCustomerApply(CustomerVO customerVO)
			throws DatabaseException {
		customerDAO.updateStruts(customerVO);
		//customerDAO.udateProjectToBd(customerVO);
		//customerDAO.udateProjectCooperateByBd(customerVO);
	}

	public List<CustomerVO> getCustomerIdleList(CustomerVO customerVO) {
		return customerDAO.getCustomerIdleList(customerVO);
	}

	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kkfun.hz.service.CustomerService#CallBackCustomer(java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public void updateCallBackCustomer(Integer customerId, Integer status,
			Integer businessDeveloperId, Integer relayStatus,
			Integer relayBdId, String callBackReason) throws DatabaseException {
		CustomerVO customer = customerDAO.getCustomerById(customerId);
		customer.setId(customerId);
		customer.setStatus(status);
		customer.setBusinessDeveloperId(businessDeveloperId);
		customer.setRelayStatus(relayStatus);
		customer.setRelayBdId(relayBdId);
		customer.setCallBackReason(callBackReason);
		customer
				.setCallBackTime(CalendarFormat.ymdhmsFormat.format(new Date()));
		customerDAO.update(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kkfun.hz.service.CustomerService#OvertimeCustomer(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	public void updateOvertimeCustomer(Integer customerId, Integer status)
			throws DatabaseException {
		CustomerVO customer = customerDAO.getCustomerById(customerId);
		customer.setId(customerId);
		customer.setStatus(status);
		customerDAO.update(customer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kkfun.hz.service.CustomerService#getAllCustomer(java.lang.Integer[])
	 */
	public List<CustomerVO> getAllCustomer(List<Integer> status,
			Integer businessDeveloperId, Integer agentId) {
		if (status != null && status.size() == 0) {
			return new ArrayList();
		}
		return customerDAO.getAllCustomer(status, businessDeveloperId, agentId);
	}

	public List<CustomerVO> getWholeCustomer(CustomerVO customerVO) {
		return this.customerDAO.getWholeCustomer(customerVO);
	}

	public List<CustomerVO> getCustomerListByBdId(CustomerVO customerVO)
			throws DatabaseException {
		return this.customerDAO.getCustomerListByBdId(customerVO);
	}

}
