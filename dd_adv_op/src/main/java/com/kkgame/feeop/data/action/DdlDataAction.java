package com.kkgame.feeop.data.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.DdlDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.DdlDataService;
import com.kkgame.feeop.util.DateUtils;

public class DdlDataAction extends BaseAction {

	private static final String ACTION_RESULT_QUERY_SALE = "salequery";

	private static final String ACTION_RESULT_LIST_SALE = "salelist";

	private static Log logger = LogFactory.getLog(DdlDataAction.class);
	
	private SearchVO searchVO;
	
	private DdlDataService ddlDataService;
	
	private DdlDataVO ddlDataVO;
	
	private List<DdlDataVO> ddlDataVOList;
	
	public String doQuery() {
		if(searchVO == null) {
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
			ddlDataVOList = ddlDataService.getDdlDataVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}

	public String doSaleQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_SALE;
	}
	
	public String doSaleList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			ddlDataVOList = ddlDataService.getDdlSaleDataVOList(searchVO);

		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST_SALE;
	}

	
	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public DdlDataService getDdlDataService() {
		return ddlDataService;
	}

	public void setDdlDataService(DdlDataService ddlDataService) {
		this.ddlDataService = ddlDataService;
	}

	public DdlDataVO getDdlDataVO() {
		return ddlDataVO;
	}

	public void setDdlDataVO(DdlDataVO ddlDataVO) {
		this.ddlDataVO = ddlDataVO;
	}

	public List<DdlDataVO> getDdlDataVOList() {
		return ddlDataVOList;
	}

	public void setDdlDataVOList(List<DdlDataVO> ddlDataVOList) {
		this.ddlDataVOList = ddlDataVOList;
	}
}
