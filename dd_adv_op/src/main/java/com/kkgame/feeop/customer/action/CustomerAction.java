package com.kkgame.feeop.customer.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.customer.bean.CustomerVO;
import com.kkgame.feeop.customer.bean.PortalUserVO;
import com.kkgame.feeop.customer.service.AgentService;
import com.kkgame.feeop.customer.service.BdService;
import com.kkgame.feeop.customer.service.CustomerService;
import com.kkgame.feeop.tag.Pagination;


public class CustomerAction extends BaseAction {
	private static Log logger = LogFactory.getLog(CustomerAction.class);
	public final static String ACTION_RESUTL_AUDIT = "audit";
	public final static String ACTION_RESUTL_ALL_LIST = "alllist";
	public final static String ACTION_RESUTL_DEVOLVE = "devolve";
	public final static String ACTION_RESUTL_REASON_EDIT = "reason_edit";
	public final static String CUSTOMER_EXIST_LIST = "customer_exist_list";
	private static final String ACTION_RESUTL_ALL_QUERY = "all_query";
	/** service */
//	private TagService tagService;
	private CustomerService customerService;
	private BdService bdService;
	private AgentService agentService;
//	private PortalUserService portalUserService;

	private List<CustomerVO> customerList;
	private CustomerVO customerVO;
	private PortalUserVO portalUserVO;
	private List agentList;
	private List bdList;
	/**
	 * 客户总数
	 */
	private int customerTotalForBd;
	private int customerTotalForAgent;
	private Map<Integer, Integer> cuCountForBdMap;
	private Map<Integer, Integer> cuCountForAgentMap;

//	public String doQuery() {
//		return ACTION_RESULT_QUERY;
//	}
//	
//	/**
//	 * 进入创建客户页面
//	 */
//	public String doCreate() {
//
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		String userType = getLoginUserType();
//		try {
//			if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//				BdVO bdVO = getSessionBdVO();
//				int count = customerService.getCustomerCountForBd(bdVO.getId());
//				int agentTotal = customerService.getCustomerCountForAgent(bdVO
//						.getAgentId());
//				AgentVO agentVO = agentService.getAgentById(bdVO.getAgentId());
//				if (count >= bdVO.getLevel()) {
//					errorMsg = "您的目前拥有 " + count + " 个未签约客户,已达到您的最大拓展数:"
//							+ bdVO.getLevel();
//				}
//				if (agentTotal >= agentVO.getLevel()) {
//					errorMsg = "您所属的代理商[" + agentVO.getName() + "]目前拥有 "
//							+ agentTotal + " 个未签约客户,已达到最大拓展数:"
//							+ agentVO.getLevel();
//				}
//			} else if (PkigConstants.USER_TYPE_SUPER.equals(userType)
//					|| PkigConstants.USER_TYPE_MANAGER.equals(userType)
//					|| PkigConstants.USER_TYPE_BH.equals(userType)) {
//				cuCountForBdMap = customerService.getCuCountOfBdMap();
//				cuCountForAgentMap = customerService.getCuCountOfAgentMap();
//				agentList = agentService.getAllAgentList();
//				bdList = bdService.getAllBdList(new BdVO());
//			} else if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
//				AgentVO agentVO = getSessionAgentVO();
//				BdVO bdVO = new BdVO();
//				bdVO.setAgentId(agentVO.getId());
//				bdList = bdService.getAllBdList(bdVO);
//				cuCountForBdMap = customerService.getCuCountOfBdMap();
//			}
//
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		return ACTION_RESULT_CREATE;
//	}
//
//	/**
//	 * 创建客户
//	 */
//	public String doSave() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		try {
//			boolean flag = false;
//			flag = validCreate();
//			if (flag) {
//				printMessage("-2");
//				return null;
//			}
//			String userType = getLoginUserType();
//			logger.debug("userType：" + userType);
//			if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//				BdVO bdVO = getSessionBdVO();
//				customerVO.setBusinessDeveloperId(bdVO.getId());
//			}
//
//			customerService.insert(customerVO, portalUserVO);
//
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		printMessage("创建客户成功!");
//		return null;
//	}
//
//	/**
//	 * 判断是否还有空间创建客户
//	 * 
//	 */
//	public boolean validCreate() throws Exception {
//		boolean flag = false;
//		String userType = getLoginUserType();
//		AgentVO agentVO = new AgentVO();
//		BdVO bdVO = new BdVO();
//		int cmCountByBd = 0;
//		int cmCountByAgent = 0;
//		if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//			bdVO = getSessionBdVO();
//			agentVO = agentService.getAgentById(bdVO.getAgentId());
//			cmCountByBd = customerService.getCustomerCountForBd(bdVO.getId());
//			cmCountByAgent = customerService.getCustomerCountForAgent(bdVO
//					.getAgentId());
//		} else if (PkigConstants.USER_TYPE_SUPER.equals(userType)
//				|| PkigConstants.USER_TYPE_BH.equals(userType)
//				|| PkigConstants.USER_TYPE_MANAGER.equals(userType)) {
//			cmCountByBd = customerService.getCustomerCountForBd(customerVO
//					.getBusinessDeveloperId());
//			cmCountByAgent = customerService
//					.getCustomerCountForAgent(customerVO.getAgentId());
//			agentVO = agentService.getAgentById(customerVO.getAgentId());
//			bdVO = bdService.getBdById(customerVO.getBusinessDeveloperId());
//		} else if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
//			agentVO = getSessionAgentVO();
//			bdVO = bdService.getBdById(customerVO.getBusinessDeveloperId());
//			cmCountByBd = customerService.getCustomerCountForBd(bdVO.getId());
//			cmCountByAgent = customerService.getCustomerCountForAgent(agentVO
//					.getId());
//		}
//		if (cmCountByBd >= bdVO.getLevel()) {
//			errorMsg = "商务拓展人员[" + bdVO.getName() + "]目前拥有 " + cmCountByBd
//					+ " 个未签约客户,已达到您的最大拓展数:" + bdVO.getLevel();
//			flag = true;
//		}
//		if (cmCountByAgent >= agentVO.getLevel()) {
//			errorMsg = "代理商[" + agentVO.getName() + "]目前拥有 " + cmCountByAgent
//					+ " 个未签约客户,已达到最大拓展数:" + agentVO.getLevel();
//			flag = true;
//		}
//		return flag;
//	}
//
//	public String doList() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		String userType = getLoginUserType();
//		logger.debug("userType：" + userType);
//		if (PkigConstants.USER_TYPE_SUPER.equals(userType)
//				|| PkigConstants.USER_TYPE_BH.equals(userType)
//				|| PkigConstants.USER_TYPE_MANAGER.equals(userType)) {
//			return doListOfAllAgent();
//		} else if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//			return doListByBd();
//		} else if (PkigConstants.USER_TYPE_AGENT.equals(userType)
//				|| PkigConstants.USER_TYPE_XJD.equals(userType)) {
//			return doListByAgent();
//		}
//
//		return null;
//	}
//
//	/**
//	 * 商务人员查询
//	 */
//	public String doListByBd() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		BdVO bdVO = getSessionBdVO();
//		logger.debug("bdId" + bdVO.getId());
//		if (customerVO.getQuery() == 1) {
//			customerVO.setPage(new Pagination());
//		}
//		try {
//			// 查询自有客户
//			if (customerVO.getStatus() == 90) {
//				customerVO.setBusinessDeveloperId(bdVO.getId());
//				customerList = customerService.getAllCustomerList(customerVO);
//				customerVO.setQuery(0);
//				for (CustomerVO vo : customerList) {
//					if (bdVO.getId() == vo.getBusinessDeveloperId()) {
//						vo.setIsMaster("Y");
//					} else {
//						vo.setIsMaster("N");
//					}
//				}
//				return ACTION_RESULT_LIST;
//			}
//			// 查询闲置客户
//			if (customerVO.getStatus() == PkigConstants.CUSTOMER_STATUS_IDLE) {
//				customerList = customerService.getCustomerList(customerVO);
//				customerVO.setQuery(0);
//				return ACTION_RESULT_LIST;
//			}
//			// 查询转移中客户
//			if (customerVO.getRelayStatus() == PkigConstants.CUSTOMER_STATUS_DEVOLVE) {
//				customerVO.setStatus(-1);
//			}
//			// 查询被收回客户
//			if (customerVO.getRelayStatus() == PkigConstants.CUSTOMER_STATUS_CALL_BACK) {
//				customerVO.setRelayBdId(bdVO.getId());
//				customerVO.setBusinessDeveloperId(0);
//				customerVO.setStatus(-1);
//				customerList = customerService.getCustomerList(customerVO);
//				customerVO.setQuery(0);
//				for (CustomerVO vo : customerList) {
//					if (bdVO.getId() == vo.getBusinessDeveloperId()) {
//						vo.setIsMaster("Y");
//					} else {
//						vo.setIsMaster("N");
//					}
//				}
//				return ACTION_RESULT_LIST;
//			}
//			customerVO.setBusinessDeveloperId(bdVO.getId());
//			customerList = customerService.getCustomerList(customerVO);
//			customerVO.setQuery(0);
//			for (CustomerVO vo : customerList) {
//				if (bdVO.getId() == vo.getBusinessDeveloperId()) {
//					vo.setIsMaster("Y");
//				} else {
//					vo.setIsMaster("N");
//				}
//			}
//
//		} catch (Exception e) {
//			logger.debug(e);
//			errorMsg = "抱歉！系统异常，请联系系统管理员。";
//			return ACTION_RESULT_LIST;
//		}
//		return ACTION_RESULT_LIST;
//	}
//
//	/**
//	 * 代理商查询客户
//	 */
//	public String doListByAgent() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		int agentId = 0;
//		if (PkigConstants.USER_TYPE_XJD.equals(getLoginUserType())) {
//			agentId = 5;
//		} else {
//			AgentVO agentVO = getSessionAgentVO();
//			agentId = agentVO.getId();
//		}
//		logger.debug("agentId : " + agentId);
//		if (customerVO.getQuery() == 1) {
//			customerVO.setPage(new Pagination());
//		}
//		try {
//
//			customerVO.setAgentId(agentId);
//			if (customerVO.getStatus() == 90) {
//				customerList = customerService.getAllCustomerList(customerVO);
//				customerVO.setQuery(0);
//				return ACTION_RESULT_LIST;
//			}
//			customerList = customerService.getCustomerList(customerVO);
//			customerVO.setQuery(0);
//		} catch (Exception e) {
//			logger.debug(e);
//			errorMsg = "抱歉！系统异常，请联系系统管理员。";
//			return ACTION_RESULT_LIST;
//		}
//		return ACTION_RESULT_LIST;
//	}
//
//	/**
//	 * 管理员 查询所有代理商客户
//	 */
//	public String doListOfAllAgent() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		if (customerVO.getQuery() == 1) {
//			customerVO.setPage(new Pagination());
//		}
//		try {
//			// 查询被收回客户
//			if (customerVO.getRelayStatus() == PkigConstants.CUSTOMER_STATUS_CALL_BACK) {
//				customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_IDLE);
//				customerList = customerService.getCustomerList(customerVO);
//				customerVO.setQuery(0);
//				return ACTION_RESULT_LIST;
//			}
//			if (customerVO.getRelayStatus() == PkigConstants.CUSTOMER_STATUS_DEVOLVE) {
//				customerVO.setStatus(-1);
//			}
//			customerList = customerService.getCustomerList(customerVO);
//			customerVO.setQuery(0);
//		} catch (Exception e) {
//			logger.debug(e);
//			errorMsg = "抱歉！系统异常，请联系系统管理员。";
//			return ACTION_RESULT_LIST;
//		}
//		return ACTION_RESULT_LIST;
//	}
//
//	public String doAllQuery() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		return ACTION_RESUTL_ALL_QUERY;
//	}
	
	/**
	 * 获取所有客户
	 */
	public String doAllCustomerList() {
		if (customerVO == null) {
			logger.info("customerVO is null");
			customerVO = new CustomerVO();
		}
		try {
			if (customerVO.getQuery() == 1) {
				customerVO.setPage(new Pagination());
			}
			customerList = customerService.getAllCustomerList(customerVO);
			customerVO.setQuery(0);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "抱歉！系统异常，请联系系统管理员。";
			printMessage("-1");
			return null;
		}
		return ACTION_RESUTL_ALL_LIST;
	}
	
	public String getAllCustomer() {
		if (customerVO == null) {
			logger.info("customerVO is null");
			customerVO = new CustomerVO();
		}
		try {
			customerList = customerService.getAllCustomerForSelect(customerVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "抱歉！系统异常，请联系系统管理员。";
			printMessage("-1");
			return null;
		}
		return SUCCESS;
	}

//	/**
//	 * 进入修改客户页面
//	 */
//	public String doModify() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		int flowStatus = customerVO.getStatus();
//		int pageNum = customerVO.getPage().getPageNum();
//		int totalRow = customerVO.getPage().getTotalRow();
//
//		int id = customerVO.getId();
//		int portalUserId = customerVO.getPortalUserId();
//		String userType = getLoginUserType();
//		try {
//			if (PkigConstants.USER_TYPE_SUPER.equals(userType)
//					|| PkigConstants.USER_TYPE_BH.equals(userType)
//					|| PkigConstants.USER_TYPE_MANAGER.equals(userType)) {
//				cuCountForBdMap = customerService.getCuCountOfBdMap();
//				cuCountForAgentMap = customerService.getCuCountOfAgentMap();
//				agentList = agentService.getAllAgentList();
//				bdList = bdService.getAllBdList(new BdVO());
//			} else if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
//				AgentVO agentVO = getSessionAgentVO();
//				BdVO bdVO = new BdVO();
//				bdVO.setAgentId(agentVO.getId());
//				bdList = bdService.getAllBdList(bdVO);
//				cuCountForBdMap = customerService.getCuCountOfBdMap();
//			}
//			customerVO = customerService.getCustomerById(id);
//			portalUserVO = portalUserService.getPortalUserById(portalUserId);
//
//			customerVO.setFlowStatus(flowStatus);
//			customerVO.getPage().setPageNum(pageNum);
//			customerVO.getPage().setTotalRow(totalRow);
//		} catch (Exception e) {
//			logger.error("doModify:" + e);
//			printMessage("-1");
//			return null;
//		}
//		return ACTION_RESULT_CREATE;
//	}
//
//	/**
//	 * 更新客户信息
//	 */
//	public String doUpdate() {
//		logger.debug(customerVO.getName());
//		int status = customerVO.getFlowStatus();
//		try {
//			customerService.update(customerVO, portalUserVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			customerVO.setPortalUserId(portalUserVO.getId());
//			return null;
//		}
//		customerVO.setStatus(status);
//		printMessage("更新客户信息成功!");
//		return null;
//	}
//
//	/**
//	 * 删除客户
//	 */
//	public String doRemove() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		try {
//			customerService.delete(customerVO.getId(), customerVO
//					.getPortalUserId());
//		} catch (Exception e) {
//			logger.debug(e);
//			errorMsg = "抱歉！系统异常，请联系系统管理员。";
//			printMessage("-1");
//			return null;
//		}
//		printMessage("删除客户信息成功");
//		return null;
//	}
//
//	/**
//	 * 查看客户信息
//	 */
//	public String doDetail() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		int id = customerVO.getId();
//		int portalUserId = customerVO.getPortalUserId();
//		try {
//			customerVO = customerService.getCustomerById(id);
//			portalUserVO = portalUserService.getPortalUserById(portalUserId);
//		} catch (Exception e) {
//			logger.debug(e);
//			errorMsg = "抱歉！系统异常，请联系系统管理员。";
//			return doList();
//		}
//		return ACTION_RESULT_DETAIL;
//	}
//
//	/**
//	 * 更新客户状态
//	 */
//	public String doUpdateStruts() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//			return doList();
//		}
//		int newStatus = customerVO.getNewStatus();
//		int oldStatus = customerVO.getStatus();
//		try {
//			// 更新前校验 , 移交客户时可以不同步
//			if (PkigConstants.CUSTOMER_STATUS_DEVOLVE != customerVO
//					.getNewStatus()) {
//				CustomerVO vo = customerService.getCustomerById(customerVO
//						.getId());
//				if (null == vo) {
//					errorMsg = "系统数据不同步，请刷新再试！";
//					printMessage(errorMsg);
//					return null;
//				}
//				if (customerVO.getStatus() != vo.getStatus()
//						|| customerVO.getBusinessDeveloperId() != vo
//								.getBusinessDeveloperId()
//						|| customerVO.getRelayBdId() != vo.getRelayBdId()
//						|| customerVO.getRelayStatus() != vo.getRelayStatus()) {
//					errorMsg = "系统数据不同步，请刷新再试！";
//					printMessage(errorMsg);
//					return null;
//				}
//			}
//			// 提交审核
//			if (PkigConstants.CUSTOMER_STATUS_WAIT_CONFIRM == customerVO
//					.getNewStatus()) {
//
//				if (PkigConstants.USER_TYPE_BD.equals(getLoginUserType())) {
//					BdVO bdVO = getSessionBdVO();
//					if (PkigConstants.CUSTOMER_STATUS_IDLE == customerVO
//							.getStatus()) {
//						if (getLoginUserId() == customerVO.getRelayBdId()) {
//							// 重新申请被收回客户时 以前的收回信息清零
//							customerVO.setRelayBdId(0);
//							customerVO.setRelayStatus(0);
//						}
//
//						customerVO.setBusinessDeveloperId(bdVO.getId());
//					}
//				}
//				customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_WAIT_CONFIRM);
//				errorMsg = "提交客户审核成功";
//			}
//
//			// 放弃
//			if (PkigConstants.CUSTOMER_STATUS_ABANDON == customerVO
//					.getNewStatus()) {
//				BdVO bdVO = getSessionBdVO();
//				if (customerVO.getBusinessDeveloperId() != bdVO.getId()) {
//					errorMsg = "对不起，不是您的客户，你无权操作！";
//					printMessage(errorMsg);
//					return null;
//				}
//				// 释放拓展权
//				customerVO.setRelayBdId(customerVO.getBusinessDeveloperId());
//				customerVO
//						.setRelayStatus(PkigConstants.CUSTOMER_STATUS_ABANDON);
//				customerVO.setBusinessDeveloperId(0);
//				customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_IDLE);
//				errorMsg = "放弃客户成功";
//			}
//			// 收回
//			if (PkigConstants.CUSTOMER_STATUS_CALL_BACK == customerVO
//					.getNewStatus()) {
//				customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_IDLE);
//				customerVO
//						.setRelayStatus(PkigConstants.CUSTOMER_STATUS_CALL_BACK);
//				customerVO.setRelayBdId(customerVO.getBusinessDeveloperId());
//				// 释放拓展权
//				customerVO.setBusinessDeveloperId(0);
//				errorMsg = "收回客户成功";
//			}
//			// 转移
//			if (PkigConstants.CUSTOMER_STATUS_DEVOLVE == customerVO
//					.getNewStatus()) {
//				int cmCountByBd = 0;
//				cmCountByBd = customerService.getCustomerCountForBd(customerVO
//						.getRelayBdId());
//				BdVO bdVO = bdService.getBdById(customerVO.getRelayBdId());
//				if (cmCountByBd >= bdVO.getLevel()) {
//					errorMsg = "对方已达到最大拓展数量!";
//					printMessage(errorMsg);
//					return null;
//				}
//				customerVO.setRelayStatus(PkigConstants.CUSTOMER_STATUS_DEVOLVE);
//				errorMsg = "转移客户成功";
//			}
//
//			// 不同意移交 或 取消移交
//			if (PkigConstants.CUSTOMER_DEVOLVE_DISAGREE == customerVO
//					.getNewStatus()
//					|| PkigConstants.CUSTOMER_DEVOLVE_CANCEL == customerVO
//							.getNewStatus()) {
//				customerVO.setNewStatus(PkigConstants.CUSTOMER_DEVOLVE_DISAGREE);
//				customerVO.setRelayBdId(0);
//				customerVO.setRelayStatus(0);
//			}
//			// 申请延期
//			if (PkigConstants.CUSTOMER_STATUS_DEFERRED == customerVO
//					.getNewStatus()) {
//				customerVO.setRelayStatus(PkigConstants.CUSTOMER_STATUS_DEFERRED);
//			}
//			// 同意延期
//			if (PkigConstants.CUSTOMERS_DEFERRED_AGREE == customerVO
//					.getNewStatus()) {
//				customerVO.setRelayStatus(0);
//				if (customerVO.getExpiryDate() <= 45) {
//					customerVO.setExpiryDate(60);
//				} else if (customerVO.getExpiryDate() > 45) {
//					customerVO.setExpiryDate(customerVO.getExpiryDate() + 30);
//				}
//				// customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_DEVING);
//			}
//			/**
//			 * 签约 审核通过或不通过
//			 */
//			if (PkigConstants.CUSTOMER_STATUS_DEVING == customerVO
//					.getNewStatus()
//					|| PkigConstants.CUSTOMER_STATUS_NO_PASS == customerVO
//							.getNewStatus()
//					|| PkigConstants.CUSTOMER_STATUS_SIGN_UP == customerVO
//							.getNewStatus()) {
//				customerVO.setStatus(customerVO.getNewStatus());
//			}
//
//			customerService.updateStruts(customerVO);
//		} catch (Exception e) {
//			errorMsg = "系统出错，请重试或联系管理员";
//			printMessage(errorMsg);
//			return null;
//		}
//		// 不同意移交 跳转 到移交中客户列表
//		if (PkigConstants.CUSTOMER_DEVOLVE_DISAGREE == customerVO
//				.getNewStatus()
//				|| PkigConstants.CUSTOMER_DEVOLVE_CANCEL == customerVO
//						.getNewStatus()) {
//			customerVO.setRelayStatus(PkigConstants.CUSTOMER_STATUS_DEVOLVE);
//		}
//		errorMsg = "客户信息更新成功";
//		printMessage(errorMsg);
//		customerVO.setStatus(oldStatus);
//		return null;
//	}
//
//	/**
//	 * 转移客户
//	 */
//	public String doCustomerDevolve() {
//
//		// 同意移交
//		if (PkigConstants.CUSTOMER_DEVOLVE_AGREE == customerVO.getNewStatus()) {
//			int relayBdId = customerVO.getRelayBdId();
//			// int businessDeveloperId = customerVO.getBusinessDeveloperId();
//			customerVO.setBusinessDeveloperId(relayBdId);
//			customerVO.setRelayBdId(0);
//			customerVO.setRelayStatus(0);
//		}
//		try {
//			customerService.updateCustomerDevolve(customerVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		printMessage("客户移交成功");
//		return null;
//	}
//
//	/**
//	 * 申请闲置客户
//	 */
//	public String doCustomerApply() {
//		// 申请闲置 客户
//		if (PkigConstants.CUSTOMER_STATUS_WAIT_CONFIRM == customerVO
//				.getNewStatus()) {
//			if (PkigConstants.USER_TYPE_BD.equals(getLoginUserType())) {
//				BdVO bdVO = getSessionBdVO();
//				if (PkigConstants.CUSTOMER_STATUS_IDLE == customerVO
//						.getStatus()) {
//					if (getLoginUserId() == customerVO.getRelayBdId()) {
//						// 重新申请被收回客户时 以前的收回信息清零
//						customerVO.setRelayBdId(0);
//						customerVO.setRelayStatus(0);
//					}
//					customerVO.setBusinessDeveloperId(bdVO.getId());
//				}
//			}
//			customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_WAIT_CONFIRM);
//		}
//		try {
//			customerService.updateCustomerApply(customerVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		printMessage("申请闲置客户成功！");
//		return null;
//	}
//
//	/**
//	 * 校验是名称是否合法
//	 */
//	public String validName() throws UnsupportedEncodingException {
//		boolean flag;
//		try {
//			flag = customerService.checkCustomerNameExist(customerVO);
//			if (flag == true) {
//				this.addActionMessage("true");
//			} else {
//				this.addActionMessage("false");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return PlAIN_MESSAGE;
//	}
//
//	/**
//	 * 进入审核页面
//	 */
//	public String gotoAuditCustomerView() {
//		try {
//			customerVO = customerService.getCustomerById(customerVO.getId());
//			BdVO bdVO = bdService
//					.getBdById(customerVO.getBusinessDeveloperId());
//			AgentVO agentVO = agentService.getAgentById(bdVO.getAgentId());
//			customerTotalForBd = customerService
//					.getCustomerCountForBd(customerVO.getBusinessDeveloperId());
//			customerTotalForAgent = customerService
//					.getCustomerCountForAgent(bdVO.getAgentId());
//			customerVO.setBdLevel(bdVO.getLevel());
//			customerVO.setAgentLevel(agentVO.getLevel());
//		} catch (Exception e) {
//			logger.debug(e);
//		}
//		return ACTION_RESUTL_AUDIT;
//	}
//
//	/**
//	 * 审核客户
//	 */
//	public String doAuditCustomer() throws UnsupportedEncodingException {
//
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//			return doList();
//		}
//		try {
//			CustomerVO vo = customerService.getCustomerById(customerVO.getId());
//			if (null == vo) {
//				this.addActionMessage("false");
//				return PlAIN_MESSAGE;
//			}
//			if (customerVO.getStatus() != vo.getStatus()
//					|| customerVO.getBusinessDeveloperId() != vo
//							.getBusinessDeveloperId()
//					|| customerVO.getRelayBdId() != vo.getRelayBdId()
//					|| customerVO.getRelayStatus() != vo.getRelayStatus()) {
//				this.addActionMessage("echo");
//				return PlAIN_MESSAGE;
//			}
//			customerVO.setStatus(customerVO.getNewStatus());
//			customerService.updateStruts(customerVO);
//			this.addActionMessage("success");
//		} catch (Exception e) {
//			this.addActionMessage("false");
//			e.printStackTrace();
//		}
//
//		return PlAIN_MESSAGE;
//	}
//
//	/**
//	 * 进入移交客户页面
//	 */
//	public String gotoDevolveCustomer() {
//		try {
//			customerVO = customerService.getCustomerById(customerVO.getId());
//			String userType = getLoginUserType();
//			bdList = tagService.getBdList();
//			if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//				for (int i = 0; i < bdList.size(); i++) {
//					NameVO vo = (NameVO) bdList.get(i);
//					if (vo.getId().equals(
//							"" + customerVO.getBusinessDeveloperId())) {
//						bdList.remove(vo);
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.debug(e);
//		}
//		return ACTION_RESUTL_DEVOLVE;
//	}
//
//	/**
//	 * 校验是否还有拓展空间
//	 */
//	public String validDevolveTerm() throws UnsupportedEncodingException {
//		boolean flag = false;
//		flag = checkCustomerCount(customerVO.getBusinessDeveloperId());
//		try {
//			if (flag == true) {
//				// 还有拓展空间
//				this.addActionMessage("true");
//			} else {
//				this.addActionMessage("false");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return PlAIN_MESSAGE;
//	}
//
//	public boolean checkCustomerCount(int bdId) {
//		BdVO bdVO;
//		boolean flag = false;
//		try {
//			bdVO = bdService.getBdById(bdId);
//			// AgentVO agentVO = agentService.getAgentById(bdVO.getAgentId());
//			int count = customerService.getCustomerCountForBd(customerVO
//					.getBusinessDeveloperId());
//			int agentTotal = customerService.getCustomerCountForAgent(bdVO
//					.getAgentId());
//			if (count < bdVO.getLevel() || agentTotal < bdVO.getLevel()) {
//				flag = true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return flag;
//	}
//
//	/**
//	 * 进入编辑原因页面
//	 */
//	public String gotoReasonEditView() {
//		try {
//			int newStatus = customerVO.getNewStatus();
//			customerVO = customerService.getCustomerById(customerVO.getId());
//			customerVO.setNewStatus(newStatus);
//		} catch (Exception e) {
//			logger.debug(e);
//		}
//		return ACTION_RESUTL_REASON_EDIT;
//	}
//
//	public String getCustomerExistent() throws UnsupportedEncodingException {
//
//		String name = java.net.URLDecoder.decode(customerVO.getName(), "UTF-8");
//		customerList = customerService.getCustomerExistent(name);
//		return CUSTOMER_EXIST_LIST;
//	}
//
//	// 闲置客户列表
//	public String getCustomerIdleList() {
//		if (customerVO == null) {
//			logger.info("customerVO is null");
//			customerVO = new CustomerVO();
//		}
//		customerVO.setStatus(PkigConstants.CUSTOMER_STATUS_IDLE);
//		customerList = customerService.getCustomerIdleList(customerVO);
//		return ACTION_RESULT_LIST;
//	}
//
//	/**
//	 * 获取客户过期提示信息
//	 */
//	public String getPromptMessage() throws UnsupportedEncodingException {
//
//		try {
//			List overdueList = new ArrayList();
//			List nopassList = new ArrayList();
//			List<Integer> status = new ArrayList<Integer>();
//			status.add(PkigConstants.CUSTOMER_STATUS_OVERDUE);
//			status.add(PkigConstants.CUSTOMER_STATUS_NO_PASS);
//			Integer businessDeveloperId = null;
//			Integer agentId = null;
//			StringBuffer sb = new StringBuffer();
//			if (PkigConstants.USER_TYPE_AGENT.equals(getLoginUserType())) {
//				agentId = getLoginUserId();
//			} else if (PkigConstants.USER_TYPE_BD.equals(getLoginUserType())) {
//				businessDeveloperId = getLoginUserId();
//			}
//			customerList = customerService.getAllCustomer(status,
//					businessDeveloperId, agentId);
//
//			if (customerList.size() == 0) {
//				sb.append(false);
//			} else {
//
//				for (int i = 0; i < customerList.size(); i++) {
//					CustomerVO vo = (CustomerVO) customerList.get(i);
//					if (vo.getStatus() == PkigConstants.CUSTOMER_STATUS_OVERDUE) {
//						overdueList.add(vo);
//					} else if (vo.getStatus() == PkigConstants.CUSTOMER_STATUS_NO_PASS) {
//						nopassList.add(vo);
//					}
//				}
//				if (overdueList.size() > 0) {
//					sb.append("<font color=\"#0000FF\">您有 "
//							+ overdueList.size() + " 个客户已过期,请及时处理！</font>");
//					for (int i = 0; i < overdueList.size(); i++) {
//						CustomerVO vo = (CustomerVO) overdueList.get(i);
//
//						if (i != overdueList.size() - 1) {
//							sb.append(vo.getName() + " , ");
//						} else {
//							sb.append(vo.getName());
//						}
//					}
//				}
//				if (nopassList.size() > 0) {
//					sb.append("         <font color=\"#0000FF\">您有 "
//							+ nopassList.size() + " 个客户审核未通过,请及时处理！</font>");
//					for (int i = 0; i < nopassList.size(); i++) {
//						CustomerVO vo = (CustomerVO) nopassList.get(i);
//						if (i != nopassList.size() - 1) {
//							sb.append(vo.getName() + " , ");
//						} else {
//							sb.append(vo.getName());
//						}
//					}
//				}
//			}
//			this.addActionMessage(sb.toString());
//			overdueList = null;
//			nopassList = null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return PlAIN_MESSAGE;
//	}
//
//	public String doGetCustomerJson() {
//		try {
//			customerVO = customerService.getCustomerById(customerVO.getId());
//		} catch (Exception e) {
//			logger.debug(e);
//			errorMsg = "抱歉！系统异常，请联系系统管理员。";
//		}
//		this.addActionMessage(JSONObject.fromObject(customerVO).toString());
//		return PlAIN_MESSAGE;
//	}

	public PortalUserVO getPortalUserVO() {
		return portalUserVO;
	}

	public void setPortalUserVO(PortalUserVO portalUserVO) {
		this.portalUserVO = portalUserVO;
	}

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	public List getBdList() {
		return bdList;
	}

	public int getCustomerTotalForBd() {
		return customerTotalForBd;
	}

	public void setCustomerTotalForBd(int customerTotalForBd) {
		this.customerTotalForBd = customerTotalForBd;
	}

	public int getCustomerTotalForAgent() {
		return customerTotalForAgent;
	}

	public void setCustomerTotalForAgent(int customerTotalForAgent) {
		this.customerTotalForAgent = customerTotalForAgent;
	}

	public List<CustomerVO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerVO> customerList) {
		this.customerList = customerList;
	}

	public List getAgentList() {
		return agentList;
	}

	public void setAgentList(List agentList) {
		this.agentList = agentList;
	}

	public void setBdList(List bdList) {
		this.bdList = bdList;
	}

	public Map<Integer, Integer> getCuCountForBdMap() {
		return cuCountForBdMap;
	}

	public void setCuCountForBdMap(Map<Integer, Integer> cuCountForBdMap) {
		this.cuCountForBdMap = cuCountForBdMap;
	}

	public Map<Integer, Integer> getCuCountForAgentMap() {
		return cuCountForAgentMap;
	}

	public void setCuCountForAgentMap(Map<Integer, Integer> cuCountForAgentMap) {
		this.cuCountForAgentMap = cuCountForAgentMap;
	}

//	public void setTagService(TagService tagService) {
//		this.tagService = tagService;
//	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setBdService(BdService bdService) {
		this.bdService = bdService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

//	public void setPortalUserService(PortalUserService portalUserService) {
//		this.portalUserService = portalUserService;
//	}

	public static String getCUSTOMER_EXIST_LIST() {
		return CUSTOMER_EXIST_LIST;
	}
//
//	public TagService getTagService() {
//		return tagService;
//	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public BdService getBdService() {
		return bdService;
	}

	public AgentService getAgentService() {
		return agentService;
	}

//	public PortalUserService getPortalUserService() {
//		return portalUserService;
//	}

}
