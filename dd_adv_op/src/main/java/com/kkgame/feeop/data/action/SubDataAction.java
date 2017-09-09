package com.kkgame.feeop.data.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SubDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.data.service.SubDataService;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class SubDataAction extends BaseAction {

	private int dataType;
	
	private static Log logger = LogFactory.getLog(SubDataAction.class);
	
	private SubDataVO subDataVO;
	
	private List<SubDataVO> subDataVOList;
	
	private SearchVO searchVO;
	
	private SubDataService subDataService;

	private RetentionService retentionService;

	private RetentionVO retentionVO;

	private List<RetentionVO> retentionVOList;
	
	private InputStream excelFile;

	private String downloadFileName;
	
	private int totalUserCount;
	
	private int userCount;
	
	private int activeCount;
	
	private int installTimes;
	
	
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
			subDataVOList = subDataService.getTotalSubDataVOList(searchVO);
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}


	public String doTotalList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		try {
			subDataVOList = subDataService.getTotalSubDataVOList(searchVO);
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

	public SubDataVO getSubDataVO() {
		return subDataVO;
	}

	public void setSubDataVO(SubDataVO subDataVO) {
		this.subDataVO = subDataVO;
	}

	public List<SubDataVO> getSubDataVOList() {
		return subDataVOList;
	}

	public void setSubDataVOList(List<SubDataVO> subDataVOList) {
		this.subDataVOList = subDataVOList;
	}

	public SubDataService getSubDataService() {
		return subDataService;
	}

	public void setSubDataService(SubDataService subDataService) {
		this.subDataService = subDataService;
	}

	public RetentionService getRetentionService() {
		return retentionService;
	}

	public void setRetentionService(RetentionService retentionService) {
		this.retentionService = retentionService;
	}

	public RetentionVO getRetentionVO() {
		return retentionVO;
	}

	public void setRetentionVO(RetentionVO retentionVO) {
		this.retentionVO = retentionVO;
	}

	public List<RetentionVO> getRetentionVOList() {
		return retentionVOList;
	}

	public void setRetentionVOList(List<RetentionVO> retentionVOList) {
		this.retentionVOList = retentionVOList;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public int getTotalUserCount() {
		return totalUserCount;
	}

	public void setTotalUserCount(int totalUserCount) {
		this.totalUserCount = totalUserCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public int getInstallTimes() {
		return installTimes;
	}

	public void setInstallTimes(int installTimes) {
		this.installTimes = installTimes;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
}
