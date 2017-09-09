package com.kkgame.feeop.data.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.UserAdVO;
import com.kkgame.feeop.data.dao.UserAdDAO;
import com.kkgame.feeop.data.service.UserAdService;
import com.kkgame.feeop.util.DateUtils;

public class UserAdAction extends BaseAction {

	private static Log logger = LogFactory.getLog(UserAdAction.class);
	
	private UserAdVO userAdVO;
	
	private List<UserAdVO> userAdVOList;
	
	private UserAdService userAdService;
	
	private SearchVO searchVO;
	
	public String doQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			userAdVOList = userAdService.getUserAdVOList(searchVO);
		} catch(Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}

	public UserAdVO getUserAdVO() {
		
		return userAdVO;
	}

	public void setUserAdVO(UserAdVO userAdVO) {
		this.userAdVO = userAdVO;
	}

	public List<UserAdVO> getUserAdVOList() {
		return userAdVOList;
	}

	public void setUserAdVOList(List<UserAdVO> userAdVOList) {
		this.userAdVOList = userAdVOList;
	}

	public UserAdService getUserAdService() {
		return userAdService;
	}

	public void setUserAdService(UserAdService userAdService) {
		this.userAdService = userAdService;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
}
