package com.kkgame.feeop.data.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.AdIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.AdIncomeService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;

public class AdIncomeAction extends BaseAction {

	private static Log logger = LogFactory.getLog(AdIncomeAction.class);
	
	private SearchVO searchVO;
	
	private AdIncomeService adIncomeService;
	
	private AdIncomeVO adIncomeVO;
	
	private List<AdIncomeVO> adIncomeVOList;

	public String doQuery() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY;
	}

	public String doList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			adIncomeVOList = adIncomeService.getAdIncomeVOList(searchVO);
			
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public AdIncomeService getAdIncomeService() {
		return adIncomeService;
	}

	public void setAdIncomeService(AdIncomeService adIncomeService) {
		this.adIncomeService = adIncomeService;
	}

	public AdIncomeVO getAdIncomeVO() {
		return adIncomeVO;
	}

	public void setAdIncomeVO(AdIncomeVO adIncomeVO) {
		this.adIncomeVO = adIncomeVO;
	}

	public List<AdIncomeVO> getAdIncomeVOList() {
		return adIncomeVOList;
	}

	public void setAdIncomeVOList(List<AdIncomeVO> adIncomeVOList) {
		this.adIncomeVOList = adIncomeVOList;
	}
}
