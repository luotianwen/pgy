package com.kkgame.hz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kkgame.hz.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import sun.util.calendar.CalendarUtils;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.SpecialDataVO;
import com.kkgame.hz.entities.UserDivideVO;
import com.kkgame.hz.entities.UserRegisterVO;
import com.kkgame.hz.service.UserDivideService;
import com.kkgame.hz.service.UserRegisterService;
import com.kkgame.hz.util.CalendarFormat;
import com.kkgame.hz.util.SystemConstant;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

public class UserDivideAction extends BaseAction {

	private static Log logger = LogFactory.getLog(UserDivideAction.class);
	
	private UserDivideService userDivideService;
	
	private UserRegisterService userRegisterService;
	
	private List<UserDivideVO> userDivideVOList;
	
	private UserDivideVO userDivideVO;
	
	private SpecialDataVO specialDataVO;
	
	private List<SpecialDataVO> specialDataVOList;
	
	private BillSearchVO billSearchVO;

	private int queryType;
	
	private static final String ACTION_USERDIVIDE_QUERY="userdividequery";

	private static final String ACTION_RESULT_QUERY_SPECIAL = "specialquery";

	private static final String ACTION_RESULT_LIST_SPECIAL = "speciallist";
		

	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private String message = "你已成功上传文件";
	
	public String doGetUserDivideQuery() {
		billSearchVO = new BillSearchVO();
		billSearchVO.setProvinceId(0);
		billSearchVO.setAgentId(0);
		billSearchVO.setQueryType(queryType);
		billSearchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
		billSearchVO.setEndTime(CalendarFormat.getCurrentFormatDate("yyyy-MM-dd"));
		String userType = getLoginUserType();
		if("AG".endsWith(userType)) {
			billSearchVO.setAgentId(getSessionAgentVO().getId());
		}else {
			billSearchVO.setAgentId(0);
		}
		if("BD".endsWith(userType)) {
			billSearchVO.setBdId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBdId(0);
		}
		if("CM".endsWith(userType)) {
			billSearchVO.setCustomerId(getSessionCustomerVO().getId());
		} else {
			billSearchVO.setCustomerId(0);
		}	
		if("BH".endsWith(userType)) {
			billSearchVO.setBhId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBhId(0);
		}
		return ACTION_USERDIVIDE_QUERY;
	}
	
	public String doListDay() {
		logger.debug("UserDivideAction doListDay begin");
		String userType = getLoginUserType();
		try {
			if(billSearchVO == null) {
				logger.debug("billSearchVO is null");
				billSearchVO = new BillSearchVO();
			}
			if("AG".endsWith(userType)) {
				billSearchVO.setAgentId(getSessionAgentVO().getId());
			}
			if("BD".endsWith(userType)) {
				billSearchVO.setBdId(getSessionBdVO().getId());
			}
			if("CM".equals(userType)) {
				billSearchVO.setCustomerId(getSessionCustomerVO().getId());
			}
			if("BH".equals(userType)) {
				billSearchVO.setBhId(getSessionBdVO().getId());
			}
			userDivideVOList = userDivideService.getUserDivideVOListDay(billSearchVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doListMonth() {		
		logger.debug("UserDivideAction doListMonth begin");
		String userType = getLoginUserType();
		try {
			if(billSearchVO == null) {
				logger.debug("billSearchVO is null");
				billSearchVO = new BillSearchVO();
			}
			if("AG".endsWith(userType)) {
				billSearchVO.setAgentId(getSessionAgentVO().getId());
			}
			if("BD".endsWith(userType)) {
				billSearchVO.setBdId(getSessionBdVO().getId());
			}
			if("CM".equals(userType)) {
				billSearchVO.setCustomerId(getSessionCustomerVO().getId());
			}	
			if("BH".equals(userType)) {
				billSearchVO.setBhId(getSessionBdVO().getId());
			}
			userDivideVOList = userDivideService.getUserDivideVOList(billSearchVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		return ACTION_RESULT_LIST;		
	}
	
	public String getSendData() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String ip = request.getRemoteAddr();
		try {
			int dataType = Integer.parseInt(request.getParameter("dataType"));
			if(dataType==1) {
				String statDate = request.getParameter("statDate");
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				int productId = Integer.parseInt(request.getParameter("productId"));
				int downCount = Integer.parseInt(request.getParameter("downCount"));
				int provinceId = Integer.parseInt(request.getParameter("provinceId"));
				int registerCount = Integer.parseInt(request.getParameter("registerCount"));
				int serverId = 1;
				UserDivideVO divideVO = new UserDivideVO();
				divideVO.setTime(statDate);
				divideVO.setProjectId(projectId);
				divideVO.setProductId(productId);
				divideVO.setDownCount(downCount);
				divideVO.setProvinceId(provinceId);
				divideVO.setRegisterCount(registerCount);
				divideVO.setServerId(serverId);
				try {
					userDivideService.createRegisterData(divideVO);
				} catch (DatabaseException e) {
					logger.debug(e);
					printMessage("fail");
					return null;
				}
				printMessage("ok");
				return null;
			} else if(dataType==2) {
				String statDate = request.getParameter("statDate");
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				int productId = Integer.parseInt(request.getParameter("productId"));
				int provinceId = Integer.parseInt(request.getParameter("provinceId"));
				int fee = Integer.parseInt(request.getParameter("fee"));
				UserDivideVO divideVO = new UserDivideVO();
				divideVO.setTime(statDate);
				divideVO.setProjectId(projectId);
				divideVO.setProductId(productId);
				divideVO.setProvinceId(provinceId);
				divideVO.setCount(fee);
				try {
					userDivideService.createDivideData(divideVO);
				} catch (DatabaseException e) {
					logger.debug(e);
					printMessage("fail");
					return null;
				}
				printMessage("ok");
				return null;
			} else if(dataType==3) {
				String statDate = request.getParameter("statDate");
				int projectId = Integer.parseInt(request.getParameter("projectId"));
				int productId = Integer.parseInt(request.getParameter("productId"));
				int provinceId = Integer.parseInt(request.getParameter("provinceId"));
				int num = Integer.parseInt(request.getParameter("num"));
				int registerCount = Integer.parseInt(request.getParameter("registerCount"));
				
				UserRegisterVO registerVO = new UserRegisterVO();
				registerVO.setTime(statDate);
				registerVO.setProjectId(projectId);
				registerVO.setProductId(productId);
				registerVO.setServerId(1);
				registerVO.setProvinceId(provinceId);
				registerVO.setRegisterCount(registerCount);
				registerVO.setNum(num);				
				userRegisterService.update(registerVO);
				printMessage("ok");
			}
		} catch (Exception e) {
			printMessage("fail");
			return null;
		}
		
		return null;
	}
	
	public String doSpecialQuery() {
		billSearchVO = new BillSearchVO();
		billSearchVO.setProvinceId(0);
		billSearchVO.setAgentId(0);
		billSearchVO.setQueryType(queryType);
		billSearchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
		billSearchVO.setEndTime(CalendarFormat.getCurrentFormatDate("yyyy-MM-dd"));
		String userType = getLoginUserType();
		if("AG".endsWith(userType)) {
			billSearchVO.setAgentId(getSessionAgentVO().getId());
		}else {
			billSearchVO.setAgentId(0);
		}
		if("BD".endsWith(userType)) {
			billSearchVO.setBdId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBdId(0);
		}
		if("CM".endsWith(userType)) {
			billSearchVO.setCustomerId(getSessionCustomerVO().getId());
		} else {
			billSearchVO.setCustomerId(0);
		}	
		if("BH".endsWith(userType)) {
			billSearchVO.setBhId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBhId(0);
		}
		return ACTION_RESULT_QUERY_SPECIAL;
	}
	
	public String doSpecialList() {
		logger.debug("UserDivideAction doListDay begin");
		String userType = getLoginUserType();
		try {
			if(billSearchVO == null) {
				logger.debug("billSearchVO is null");
				billSearchVO = new BillSearchVO();
			}
			if("AG".endsWith(userType)) {
				billSearchVO.setAgentId(getSessionAgentVO().getId());
			}
			if("BD".endsWith(userType)) {
				billSearchVO.setBdId(getSessionBdVO().getId());
			}
			if("CM".equals(userType)) {
				billSearchVO.setCustomerId(getSessionCustomerVO().getId());
			}
			if("BH".equals(userType)) {
				billSearchVO.setBhId(getSessionBdVO().getId());
			}
			specialDataVOList = userDivideService.getSpecialDataVOListDay(billSearchVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		return ACTION_RESULT_LIST_SPECIAL;
	}


	public String doSpecialsubscribeQuery() {
		billSearchVO = new BillSearchVO();
		billSearchVO.setProvinceId(0);
		billSearchVO.setAgentId(0);
		billSearchVO.setQueryType(queryType);
		billSearchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
		billSearchVO.setEndTime(CalendarFormat.getCurrentFormatDate("yyyy-MM-dd"));
		String userType = getLoginUserType();
		if("AG".endsWith(userType)) {
			billSearchVO.setAgentId(getSessionAgentVO().getId());
		}else {
			billSearchVO.setAgentId(0);
		}
		if("BD".endsWith(userType)) {
			billSearchVO.setBdId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBdId(0);
		}
		if("CM".endsWith(userType)) {
			billSearchVO.setCustomerId(getSessionCustomerVO().getId());
		} else {
			billSearchVO.setCustomerId(0);
		}
		if("BH".endsWith(userType)) {
			billSearchVO.setBhId(getSessionBdVO().getId());
		} else {
			billSearchVO.setBhId(0);
		}
		return "subscribequery";
	}

	public String doSpecialsubscribeList() {

		String userType = getLoginUserType();
		try {
			if(billSearchVO == null) {
				logger.debug("billSearchVO is null");
				billSearchVO = new BillSearchVO();
			}
			if("AG".endsWith(userType)) {
				billSearchVO.setAgentId(getSessionAgentVO().getId());
			}
			if("BD".endsWith(userType)) {
				billSearchVO.setBdId(getSessionBdVO().getId());
			}
			if("CM".equals(userType)) {
				billSearchVO.setCustomerId(getSessionCustomerVO().getId());
			}
			if("BH".equals(userType)) {
				billSearchVO.setBhId(getSessionBdVO().getId());
			}
			specialDataVOList = userDivideService.getSpecialsubscribeDataVOListDay(billSearchVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		return "subscribelist";
	}
	public String doUpload() {
		String filePath = SystemConstant.PROJECT_PATH + "/";
		StringBuffer sb = new StringBuffer();
		if (file == null || (fileFileName.indexOf(".exe") > -1)) {
			message = "文件不能为空或exe文件";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		try {
			List<UserDivideVO> list = getFileData(file);
			for(UserDivideVO u:list) {
				u.setServerId(1);
				u.setTable("DIVIDE_STAT_"+CalendarFormat.switchFormatDate(u.getTime(), "yyyy-MM-dd", "yyyyMM"));
				userDivideService.insert(u);
			}
		} catch (Exception e) {
			logger.info(e);
			message = "对不起,文件上传失败了!!!!";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		sb.append("{msg:\"").append(message).append("\"}");
		this.printMessage(sb.toString());
		return null;
	}
	public String doSubscribeUpload() {
		String filePath = SystemConstant.PROJECT_PATH + "/";
		StringBuffer sb = new StringBuffer();
		if (file == null || (fileFileName.indexOf(".exe") > -1)) {
			message = "文件不能为空或exe文件";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		try {

			/*List<SpecialDataVO> list = new ArrayList<>();
			SpecialDataVO a=new SpecialDataVO();
			a.setTime("2016-05-21");
			a.setUv(100);
			a.setPv(1000);
			a.setProjectId(1);
			a.setIncome(200);
			a.setDividedincome(200*0.7);
			list.add(a);*/
			List<SpecialDataVO> list = getFileSubscribeData(file);
			for(SpecialDataVO u:list) {
				u.setTable("PROJECT_SUBSCRIBE_"+CalendarFormat.switchFormatDate(u.getTime(), "yyyy-MM-dd", "yyyyMM"));
				userDivideService.insertSubscribe(u);
			}
		} catch (Exception e) {
			logger.info(e);
			message = "对不起,文件上传失败了!!!!";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		sb.append("{msg:\"").append(message).append("\"}");
		this.printMessage(sb.toString());
		return null;
	}

	private List<SpecialDataVO> getFileSubscribeData(File file)throws IOException  {
		List<SpecialDataVO> list = new ArrayList<SpecialDataVO>();
		InputStream in = new FileInputStream(file);
		int rowSize = 0;
		Workbook wb = null;
		try {
			wb = (Workbook) new HSSFWorkbook(in);
		} catch (Exception e) {
			in.close();
			in = new FileInputStream(file);
			wb = new XSSFWorkbook(in);
		}
		Cell cell = null;
		Sheet st = (Sheet) wb.getSheetAt(0);
		double income=0d;
		DecimalFormat df= new DecimalFormat("#0.00");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
			Row row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			boolean hasValue = false;
			SpecialDataVO data = new SpecialDataVO();
			data.setTime(sdf.format(row.getCell(0).getDateCellValue()));
			data.setProjectId(Integer.parseInt( new DecimalFormat("0").format(row.getCell(1).getNumericCellValue())));
			data.setPv(Integer.parseInt( new DecimalFormat("0").format(row.getCell(2).getNumericCellValue())));
			data.setUv(Integer.parseInt( new DecimalFormat("0").format(row.getCell(3).getNumericCellValue())));
			income=Double.parseDouble( df.format(row.getCell(4).getNumericCellValue()));
			data.setIncome(income);
			data.setDividedincome(Double.parseDouble(df.format(income*0.7)));
			list.add(data);
		}
		return list;
	}

	public List<UserDivideVO> getFileData(File file) throws IOException {
		List<UserDivideVO> list = new ArrayList<UserDivideVO>();
		InputStream in = new FileInputStream(file);
		int rowSize = 0;
		Workbook wb = null;
		try {
			wb = (Workbook) new HSSFWorkbook(in);
		} catch (Exception e) {
			in.close();
			in = new FileInputStream(file);
			wb = new XSSFWorkbook(in);
		}
		Cell cell = null;
		Sheet st = (Sheet) wb.getSheetAt(0);
		for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
			Row row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			boolean hasValue = false;
			UserDivideVO data = new UserDivideVO();
			for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.format(date);
							} else {
								value = "";
							}
						} else {
							value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						// 导入时如果为公式生成的数据则无值
						if (!cell.getStringCellValue().equals("")) {
							value = cell.getStringCellValue();
						} else {
							value = cell.getNumericCellValue() + "";
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						value = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = "";
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					if (columnIndex == 0) {
						data.setTime(value);
					} else if (columnIndex == 1) {
						data.setProjectId(Integer.parseInt(value));
					} else if(columnIndex==2) {
						data.setProductId(Integer.parseInt(value));
					} else if(columnIndex==3) {
						data.setCount(Integer.parseInt(value));
					} 
					hasValue = true;
				}
			}
			list.add(data);// 将每行的字符串用一个String类型的集合保存。
		}
		return list;
	}
	
	public UserDivideService getUserDivideService() {
		return userDivideService;
	}

	public void setUserDivideService(UserDivideService userDivideService) {
		this.userDivideService = userDivideService;
	}

	public List<UserDivideVO> getUserDivideVOList() {
		return userDivideVOList;
	}

	public void setUserDivideVOList(List<UserDivideVO> userDivideVOList) {
		this.userDivideVOList = userDivideVOList;
	}

	public UserDivideVO getUserDivideVO() {
		return userDivideVO;
	}

	public void setUserDivideVO(UserDivideVO userDivideVO) {
		this.userDivideVO = userDivideVO;
	}

	public BillSearchVO getBillSearchVO() {
		return billSearchVO;
	}

	public void setBillSearchVO(BillSearchVO billSearchVO) {
		this.billSearchVO = billSearchVO;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public UserRegisterService getUserRegisterService() {
		return userRegisterService;
	}

	public void setUserRegisterService(UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}

	public SpecialDataVO getSpecialDataVO() {
		return specialDataVO;
	}

	public void setSpecialDataVO(SpecialDataVO specialDataVO) {
		this.specialDataVO = specialDataVO;
	}

	public List<SpecialDataVO> getSpecialDataVOList() {
		return specialDataVOList;
	}

	public void setSpecialDataVOList(List<SpecialDataVO> specialDataVOList) {
		this.specialDataVOList = specialDataVOList;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
