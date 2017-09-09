package com.kkgame.hz.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.service.CustomerService;
import com.kkgame.hz.util.UtilHelper;

public class CustomerDwr {
	private static Log logger = LogFactory.getLog(CustomerDwr.class);

	public boolean checkCustomerName(CustomerVO customerVO) {
		try {
			String name = customerVO.getName();
			int id = customerVO.getId();
			logger.info("name: " + name);
			customerVO = new CustomerVO();
			if (name == null && name.equals("")) {
				return true;
			}
			if (id > 0) {
				customerVO.setId(id);
			}
			customerVO.setName(name);
			CustomerService customerService = (CustomerService) new BaseAction()
					.getServiceObject(UtilHelper.AGENT_SERVICE);
			return customerService.checkCustomerNameExist(customerVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

}
