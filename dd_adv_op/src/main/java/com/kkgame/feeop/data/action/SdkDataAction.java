package com.kkgame.feeop.data.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.data.bean.SdkDataVO;
import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.SdkDataService;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.Arith;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SdkDataAction extends BaseAction {

	private int dataType;
	
	private static Log logger = LogFactory.getLog(SdkDataAction.class);
	
	private SdkDataVO SdkDataVO;
	
	private List<SdkDataVO> SdkDataVOList;
	
	private SearchVO searchVO;
	
	private SdkDataService sdkDataService;

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
			SdkDataVOList = sdkDataService.getTotalSdkDataVOList(searchVO);
			
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
			SdkDataVOList = sdkDataService.getTotalSdkDataVOList(searchVO);
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

	public SdkDataVO getSdkDataVO() {
		return SdkDataVO;
	}

	public void setSdkDataVO(SdkDataVO SdkDataVO) {
		this.SdkDataVO = SdkDataVO;
	}

	public List<SdkDataVO> getSdkDataVOList() {
		return SdkDataVOList;
	}

	public void setSdkDataVOList(List<SdkDataVO> SdkDataVOList) {
		this.SdkDataVOList = SdkDataVOList;
	}

	public SdkDataService getSdkDataService() {
		return sdkDataService;
	}

	public void setSdkDataService(SdkDataService sdkDataService) {
		this.sdkDataService = sdkDataService;
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
