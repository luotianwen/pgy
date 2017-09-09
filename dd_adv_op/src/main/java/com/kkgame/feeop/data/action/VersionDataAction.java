package com.kkgame.feeop.data.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.VersionDataVO;
import com.kkgame.feeop.data.service.VersionDataService;
import com.kkgame.feeop.util.DateUtils;

public class VersionDataAction extends BaseAction {

	private static Log logger = LogFactory.getLog(VersionDataAction.class);
	
	private VersionDataService versionDataService;
	
	private VersionDataVO versionDataVO;
	
	private List<VersionDataVO> versionDataVOList;
	
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
			versionDataVOList = versionDataService.getVersionDataVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	

	public VersionDataVO getVersionDataVO() {
		return versionDataVO;
	}

	public void setVersionDataVO(VersionDataVO versionDataVO) {
		this.versionDataVO = versionDataVO;
	}

	public List<VersionDataVO> getVersionDataVOList() {
		return versionDataVOList;
	}

	public void setVersionDataVOList(List<VersionDataVO> versionDataVOList) {
		this.versionDataVOList = versionDataVOList;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public VersionDataService getVersionDataService() {
		return versionDataService;
	}

	public void setVersionDataService(VersionDataService versionDataService) {
		this.versionDataService = versionDataService;
	}
	
}
