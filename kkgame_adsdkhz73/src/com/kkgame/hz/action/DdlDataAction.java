package com.kkgame.hz.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.RowFieldVO;
import com.kkgame.hz.excel.BasicExcelData;
import com.kkgame.hz.excel.ExcelBean;
import com.kkgame.hz.excel.ExportExcel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.DdlDataVO;
import com.kkgame.hz.service.DdlDataService;
import com.kkgame.hz.util.CalendarFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DdlDataAction extends BaseAction {

	private static Log logger = LogFactory.getLog(DdlDataAction.class);
	
	private  BillSearchVO billSearchVO;
	
	private int queryTYpe;
	
	private DdlDataService ddlDataService;
	
	private DdlDataVO ddlDataVO;
	
	private List<DdlDataVO> ddlDataVOList;
	
	private int queryType;
	private String downloadFileName;
	private ByteArrayInputStream excelFile;

	public String doExportData() {
		// get request parameters
		if (billSearchVO == null) {
			logger.debug("billSearchVO is null");
			billSearchVO = new BillSearchVO();
		}
		String userType = getLoginUserType();
		if ("AG".endsWith(userType)) {
			billSearchVO.setAgentId(getSessionAgentVO().getId());
		}
		if ("BD".endsWith(userType)) {
			billSearchVO.setBdId(getSessionBdVO().getId());
		}
		if ("CM".equals(userType)) {
			billSearchVO.setCustomerId(getSessionCustomerVO().getId());
		}
		if ("BH".equals(userType)) {
			billSearchVO.setBhId(getSessionBdVO().getId());
		}

		try {
			// get sql query data
			int queryType = billSearchVO.getQueryType();
			if (1 == queryType) {
				ddlDataVOList = ddlDataService.getDdlDataVOListDay(billSearchVO);
			}
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		// create ExcelBean Object
		ExcelBean<DdlExcelData> excel = new ExcelBean<>();
		int numType = HSSFCell.CELL_TYPE_NUMERIC;
		int strType = HSSFCell.CELL_TYPE_STRING;
		LinkedList<Integer> typeList = new LinkedList<>();
		LinkedList<String> titleList = new LinkedList<>();
		RowFieldVO rowFieldVO = billSearchVO.getRowFieldVO();
		if (1 == rowFieldVO.getIsShowDate()) {
			typeList.addLast(strType);
			titleList.addLast("时间");
		}
		if (1 == rowFieldVO.getIsShowAdType()) {
			typeList.addLast(strType);
			titleList.addLast("代理商");
		}
		if (1 == rowFieldVO.getIsShowBd()) {
			typeList.addLast(strType);
			titleList.addLast("商务");
		}
		if (1 == rowFieldVO.getIsShowCustomer()) {
			typeList.addLast(strType);
			titleList.addLast("客户");
		}
		if (1 == rowFieldVO.getIsShowProject()) {
			typeList.addLast(strType);
			titleList.addLast("项目");
		}
		if (1 == rowFieldVO.getIsShowProduct()) {
			typeList.addLast(strType);
			titleList.addLast("产品");
		}
		typeList.addLast(strType);
		titleList.addLast("合作模式");

		typeList.addAll(Arrays.asList(numType, numType, numType, numType));
		titleList.addAll(Arrays.asList("点击", "有效点击", "激活用户", "转化率"));
		excel.setColsType(typeList);
		excel.setColumes(titleList);

		List<DdlExcelData> userList = new ArrayList<>();
		if (null != ddlDataVOList && 0 < ddlDataVOList.size()) {
			for (DdlDataVO ddlDataVO : ddlDataVOList) {
				userList.add(new DdlExcelData(ddlDataVO, rowFieldVO));
			}
		}
		excel.setRowsData(userList);

		downloadFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " DDL日激活.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("error: get downloadFileName error", e);
		}

		HSSFWorkbook workbook = ExportExcel.createWorkBook("DDL日激活", excel);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			workbook.write(output);
			byte[] ba = output.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			output.flush();
			output.close();
		} catch (IOException e) {
			throw new RuntimeException("error: write workbook into output error", e);
		}

		return "excel";
	}

	public String doQuery() {
		billSearchVO = new BillSearchVO();
		billSearchVO.setProvinceId(0);
		billSearchVO.setAgentId(0);
		billSearchVO.setQueryType(queryType);
		billSearchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
		billSearchVO.setEndTime(CalendarFormat
				.getCurrentFormatDate("yyyy-MM-dd"));
		billSearchVO.setSearchMonth(CalendarFormat
				.getCurrentFormatDate("yyyyMM"));
		String userType = getLoginUserType();
		if ("AG".endsWith(userType)) {
			billSearchVO.setAgentId(getSessionAgentVO().getId());
		} else {
			billSearchVO.setAgentId(0);
		}
		if ("BD".endsWith(userType)) {
			billSearchVO.setBdId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBdId(0);
		}
		if ("CM".endsWith(userType)) {
			billSearchVO.setCustomerId(getSessionCustomerVO().getId());
		} else {
			billSearchVO.setCustomerId(0);
		}
		if ("BH".endsWith(userType)) {
			billSearchVO.setBhId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBhId(0);
		}
		return ACTION_RESULT_QUERY;
	}

	public String doListDay() {
		logger.debug("UserRegisterAction doListDay begin");
		try {
			if (billSearchVO == null) {
				logger.debug("billSearchVO is null");
				billSearchVO = new BillSearchVO();
			}
			String userType = getLoginUserType();
			if ("AG".endsWith(userType)) {
				billSearchVO.setAgentId(getSessionAgentVO().getId());
			}
			if ("BD".endsWith(userType)) {
				billSearchVO.setBdId(getSessionBdVO().getId());
			}
			if ("CM".equals(userType)) {
				billSearchVO.setCustomerId(getSessionCustomerVO().getId());
			}
			if ("BH".equals(userType)) {
				billSearchVO.setBhId(getSessionBdVO().getId());
			}
			ddlDataVOList = ddlDataService.getDdlDataVOListDay(billSearchVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doData() {
		if(ddlDataVO==null) {
			printMessage("error");
			return null;
		}
		try {
			ddlDataVO.setTable("DDL_DATA_"+CalendarFormat.switchFormatDate(ddlDataVO.getTime(), "yyyy-MM-dd", "yyyyMM"));
			ddlDataService.insert(ddlDataVO);
		} catch (Exception e) {
			logger.info(e);
			printMessage("error");
		}
		printMessage("ok");
		return null;
	}
	
	public BillSearchVO getBillSearchVO() {
		return billSearchVO;
	}

	public void setBillSearchVO(BillSearchVO billSearchVO) {
		this.billSearchVO = billSearchVO;
	}

	public int getQueryTYpe() {
		return queryTYpe;
	}

	public void setQueryTYpe(int queryTYpe) {
		this.queryTYpe = queryTYpe;
	}

	public DdlDataService getDdlDataService() {
		return ddlDataService;
	}

	public void setDdlDataService(DdlDataService ddlDataService) {
		this.ddlDataService = ddlDataService;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
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

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public ByteArrayInputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(ByteArrayInputStream excelFile) {
		this.excelFile = excelFile;
	}

	public static class DdlExcelData extends BasicExcelData<DdlDataVO, RowFieldVO> {
		public DdlExcelData(DdlDataVO ddl, RowFieldVO isShow) {
			super(ddl, isShow);
		}

		@Override
		public  void addAllShowData(DdlDataVO ddl, RowFieldVO isShow) {
			if (1 == isShow.getIsShowDate()) addData(ddl.getTime());
			if (1 == isShow.getIsShowAdType()) addData(ddl.getAgentName());
			if (1 == isShow.getIsShowBd()) addData(ddl.getBdName());
			if (1 == isShow.getIsShowCustomer()) addData(ddl.getCustomerName());
			if (1 == isShow.getIsShowProject())
				addData(ddl.getProjectId() <= 0 ? null : ddl.getProjectName() + "[" + ddl.getProjectId() + "]");
			if (1 == isShow.getIsShowProduct())
				addData(ddl.getProductId() <= 0 ? null : ddl.getProductName() + "[" + ddl.getProductId() + "]");
			if(ddl.getServerId()==1) {
				addData("联运");
			} else if  (ddl.getServerId()==2) {
				addData("换量");
			}
			else if  (ddl.getServerId()==3) {
				addData("自营");
			}
			else if  (ddl.getServerId()==4) {
				addData("友盟结算");
			}
			else if  (ddl.getServerId()==5) {
				addData("联盟产品");
			}
			else
				addData("");
			int clickCount = ddl.getClickCount();
			int validClickCount = ddl.getValidClickCount();
			int saleCount = ddl.getSaleCount();
			double percent = ddl.getPercent();
			addData("" + clickCount);
			addData("" + validClickCount);
			addData("" + saleCount);
			addData("" + percent);
		}
	}
}
