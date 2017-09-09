package com.kkgame.feeop.data.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.IframeDataService;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.detail.bean.IframeDetailVO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class IframeDataAction extends BaseAction {

	private int dataType;
	
	private static Log logger = LogFactory.getLog(IframeDataAction.class);
	
	private IframeDetailVO iframeDataVO;
	
	private List<IframeDetailVO> iframeDataVOList;
	
	private SearchVO searchVO;
	
	private IframeDataService iframeDataService;

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


	public String doTotalList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		try {
			iframeDataVOList = iframeDataService.getTotalIframeVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}

	public IframeDetailVO getIframeDataVO() {
		return iframeDataVO;
	}

	public void setIframeDataVO(IframeDetailVO iframeDataVO) {
		this.iframeDataVO = iframeDataVO;
	}

	public List<IframeDetailVO> getIframeDataVOList() {
		return iframeDataVOList;
	}

	public void setIframeDataVOList(List<IframeDetailVO> iframeDataVOList) {
		this.iframeDataVOList = iframeDataVOList;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public IframeDataService getIframeDataService() {
		return iframeDataService;
	}

	public void setIframeDataService(IframeDataService iframeDataService) {
		this.iframeDataService = iframeDataService;
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
