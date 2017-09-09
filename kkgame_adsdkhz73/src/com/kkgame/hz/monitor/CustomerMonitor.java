package com.kkgame.hz.monitor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.service.CustomerService;

public class CustomerMonitor {
	private static Log logger = LogFactory.getLog(CustomerMonitor.class);
	private CustomerService customerService;

	/**
	 * 每小时执行一次
	 */
	public void customerRemindWork() {
		overtimeCustomer();
		callBackOverdueCustomer();
	}

	/**
	 * 收回过期客户
	 * 
	 * @throws DatabaseException
	 */
	public void callBackOverdueCustomer() {
		List<CustomerVO> customerList = null;
		List<Integer> status = new ArrayList<Integer>();
		status.add(PkigConstants.CUSTOMER_STATUS_OVERDUE);
		customerList = customerService.getAllCustomer(status, null, null);
		for (CustomerVO vo : customerList) {
			if (vo.getLeftDays() - vo.getExpiryDate() >= 3
					&& vo.getExpiryDate() < 180
					&& vo.getRelayStatus() != PkigConstants.CUSTOMER_STATUS_DEFERRED) {
				try {
					customerService.updateCallBackCustomer(vo.getId(),
							PkigConstants.CUSTOMER_STATUS_IDLE, 0,
							PkigConstants.CUSTOMER_STATUS_CALL_BACK, vo
									.getBusinessDeveloperId(),
							"此客户已过期三天未做申请延期处理，被系统自动收回！");
					logger.info(vo.getName() + "【ID:" + vo.getId() + "】  被收回！");
				} catch (DatabaseException e) {
					e.printStackTrace();
				}
			}
		}
		customerList = null;
	}

	/** 收回审核未通过客户 */
	public void callBackNoPassCustomer() {
		List<CustomerVO> customerList = null;
		List<Integer> status = new ArrayList<Integer>();
		status.add(PkigConstants.CUSTOMER_STATUS_NO_PASS);
		customerList = customerService.getAllCustomer(status, null, null);
		for (CustomerVO vo : customerList) {

		}
	}

	// 将客户置为过期
	public void overtimeCustomer() {
		try {
			List<CustomerVO> customerList = null;
			List<Integer> status = new ArrayList<Integer>();
			status.add(PkigConstants.CUSTOMER_STATUS_DEVING);
			customerList = customerService.getAllCustomer(status, null, null);
			for (CustomerVO vo : customerList) {
				CustomerVO newVO = customerService.getCustomerById(vo.getId());
				if (newVO.getStatus() == PkigConstants.CUSTOMER_STATUS_DEVING
						&& vo.getLeftDays() - vo.getExpiryDate() >= 0) {
					try {
						customerService.updateOvertimeCustomer(vo.getId(),
								PkigConstants.CUSTOMER_STATUS_OVERDUE);
						logger.info(vo.getName() + "【ID:" + vo.getId() + "】 "
								+ vo.getExpiryDate() + "天过期");
					} catch (DatabaseException e) {
						e.printStackTrace();
					}
				}
			}
			customerList = null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
