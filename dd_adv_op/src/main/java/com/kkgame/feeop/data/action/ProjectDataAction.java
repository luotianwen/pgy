package com.kkgame.feeop.data.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.kkgame.feeop.util.Arith;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.data.bean.AdDataVO;
import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.ProjectDataService;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;

public class ProjectDataAction extends BaseAction {

	private static final String ACTION_RESULT_QUERY_TOTAL = "totalQuery";

	private static final String ACTION_RESULT_LIST_TOTAL = "totalList";

	private static final String ACTION_RESULT_QUERY_AFFAIRTOTAL = "totalaffairQuery";
	private static final String ACTION_RESULT_LIST_AFFAIRTOTAL = "totalaffairList";


	private static final String ACTION_RESULT_QUERY_AFFAIR = "affairQuery";
	private static final String ACTION_RESULT_LIST_AFFAIR = "affairList";


	private static final String ACTION_RESULT_DAYINFO = "dayInfo";

	private static final String ACTION_RESULT_QUERY_PROJECT_TOTAL = "projectTotalQuery";
	
	private static final String ACTION_RESULT_LIST_PROJECT_TOTAL = "projectTotalList";

	private int dataType;
	
	private static Log logger = LogFactory.getLog(ProjectDataAction.class);
	
	private ProjectDataVO projectDataVO;
	
	private List<ProjectDataVO> projectDataVOList;
	
	private SearchVO searchVO;
	
	private ProjectDataService projectDataService;

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
			projectDataVOList = projectDataService.getProjectDataVOList(searchVO);
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	
	public String doTotalQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_TOTAL;
	}




	public String doTotalaffairQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_AFFAIRTOTAL;
	}


	public String doTotalaffairList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			projectDataVOList = projectDataService.getProjectDataVOList(searchVO);

		} catch (Exception e) {
			logger.debug(e);
		}

		return ACTION_RESULT_LIST_AFFAIRTOTAL;
	}

	public String doAffairQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_AFFAIR;
	}

	public String doAffairList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			projectDataVOList = projectDataService.getProjectDataVOList(searchVO);

		} catch (Exception e) {
			logger.debug(e);
		}

		return ACTION_RESULT_LIST_AFFAIR;
	}





	public String doTotalList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			projectDataVOList = projectDataService.getProjectDataVOList(searchVO);
			if (null != projectDataVOList) {
				Double installCount, adIncome, thirdIncome, totalIncome; int activeCount,usercount,projectusercount;
				for (ProjectDataVO dataVO : projectDataVOList) {

					installCount = Arith.add(Arith.add(dataVO.getPushInstallTimes(), dataVO.getInstallTimes()),dataVO.getSilenceInstallTimes());
					activeCount = dataVO.getTotalActiveCount();
					adIncome = dataVO.getAdIncome();
					thirdIncome = dataVO.getThirdIncome();

					usercount=     dataVO.getUserCount();
					projectusercount  = dataVO.getProjectUserCount();

					totalIncome = Arith.add(adIncome, thirdIncome);
					dataVO.setActiveInstallPercent(Arith.div(installCount*100, activeCount, 2));
					dataVO.setEachActiveIncome(Arith.div(adIncome, activeCount, 4));
					dataVO.setEachThridActiveIncome(Arith.div(thirdIncome, activeCount, 4));
					dataVO.setTotalIncome(totalIncome);
					dataVO.setEachUserIncome(Arith.div(totalIncome, dataVO.getUserCount(), 4));

					dataVO.setAfteradIncome(Arith.div(adIncome, usercount, 4));
					dataVO.setBeforeadIncome(Arith.div(adIncome, projectusercount, 4));
					dataVO.setAfterthirdIncome(Arith.div(thirdIncome, usercount, 4));
					dataVO.setBeforethirdIncome(Arith.div(thirdIncome, projectusercount, 4));

				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST_TOTAL;
	}
	
	
	public String doExportTotalList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			projectDataVOList = projectDataService.getProjectDataVOList(searchVO);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
			String downloadFileName = (sf.format(new Date()).toString())
					+ "数据总览数据.xls";
			try {
				downloadFileName = new String(downloadFileName.getBytes("gb2312"),
						"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			this.setDownloadFileName(downloadFileName);
			HSSFWorkbook workbook = exportTotalListExcel();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			workbook.write(output);
			byte[] ba = output.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			output.flush();
			output.close();
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return "excel";
	}
	

	
	public String doProjectTotalQuery() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_PROJECT_TOTAL;
	}
	
	public String doProjectTotalList() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		try {
			projectDataVOList = projectDataService.getTotalProjectDataVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST_PROJECT_TOTAL;
	}
	
	public String doExportTotal() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(searchVO == null) {
				searchVO = new SearchVO();
			}
			UserVO userVO = (UserVO) getSession(PkigConstants.SESSION_USER);
			if (userVO.getCustomerId() > 0) {
				searchVO.setCustomerId(userVO.getCustomerId());
			}
			if(userVO.getAgentId()>0) {
				searchVO.setAgentId(userVO.getAgentId());
			}
			try {
				projectDataVOList = projectDataService.getTotalProjectDataVOList(searchVO);
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
				String downloadFileName = (sf.format(new Date()).toString())
						+ "项目累计数据.xls";
				try {
					downloadFileName = new String(downloadFileName.getBytes("gb2312"),
							"ISO8859-1");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				this.setDownloadFileName(downloadFileName);
			} catch (DatabaseException e) {
				logger.debug(e);
				printMessage("-1");
				return null;
			}
			HSSFWorkbook workbook = exportTotalExcel();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			workbook.write(output);
			byte[] ba = output.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			output.flush();
			output.close();
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return "excel";
	}
	
	public String doDayInfo() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setRowFieldString("1,");
		}
		try {
			projectDataVOList = projectDataService.getDayInfoProjectDataVO(searchVO);
			if(projectDataVOList!=null&&projectDataVOList.size()>0) {
				projectDataVO = projectDataVOList.get(0);
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_DAYINFO;
	}
	
	public String doDayInfoChart() {
		if(searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(CalendarFormat.getLastSevenDay());
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setRowFieldString("1,");
		}
		try {
			if (getSession("dayInfo") != null
					&& getSession("dayInfoTimeOut") != null
					&& (System.currentTimeMillis() - (Long) getSession("dayInfoTimeOut")) < 1000 * 60 * 5) {
				projectDataVOList = (List<ProjectDataVO>) getSession("dayInfo");
			} else {
				projectDataVOList = projectDataService.getDayInfoProjectDataVO(searchVO);
				setSession("dayInfo", projectDataVOList);
				setSession("dayInfoTimeOut", System.currentTimeMillis());
			}
			StringBuffer returnString = new StringBuffer();
			Map<String, ProjectDataVO> map = new HashMap<String, ProjectDataVO>();
			for(ProjectDataVO p:projectDataVOList) {
				map.put(p.getStatDate(), p);
			}
			StringBuffer dates = new StringBuffer();
			dates.append("[");
			StringBuffer totalSdkCount = new StringBuffer();
			StringBuffer sdkCount = new StringBuffer();
			StringBuffer activeCount = new StringBuffer();
			StringBuffer installCount = new StringBuffer();
			totalSdkCount.append("[");
			sdkCount.append("[");
			activeCount.append("[");
			installCount.append("[");
			List<String> dateList = null;
			dateList = CalendarFormat.getyyyyMMddList(searchVO.getStartDate(), searchVO.getEndDate());
			for(String s:dateList) {
				if(map.containsKey(s)) {
					ProjectDataVO p = map.get(s);
					totalSdkCount.append(p.getTotalUserCount()).append(",");
					sdkCount.append(p.getUserCount()).append(",");
					activeCount.append(p.getTotalActiveCount()).append(",");
					installCount.append(p.getInstallCount()).append(",");
				} else {
					totalSdkCount.append("0,");
					sdkCount.append("0,");
					activeCount.append("0,");
					installCount.append("0,");
				}
				s=s.replaceAll("-", "");
				s=s.substring(4, 8);
				dates.append("\"").append(s).append("\",");
			}
			dates.deleteCharAt(dates.length()-1).append("]");
			totalSdkCount.deleteCharAt(totalSdkCount.length()-1).append("]");
			sdkCount.deleteCharAt(sdkCount.length()-1).append("]");
			activeCount.deleteCharAt(activeCount.length()-1).append("]");	
			installCount.deleteCharAt(installCount.length()-1).append("]");	
			returnString.append("{\"dates\":").
			append(dates.toString()).
			append(",\"stats\":[");
			if(dataType==1) {
				returnString.append("{\"visible\":true,\"data\":").
				append(totalSdkCount).append(",\"name\":\"当月sdk销量\"}");
			} else if(dataType==2) {
				returnString.append("{\"visible\":true,\"data\":").
				append(sdkCount).append(",\"name\":\"当日sdk销量\"}");
			}  else if(dataType==3) {
				returnString.append("{\"visible\":true,\"data\":")
				.append(activeCount).append(",\"name\":\"当日总活跃\"}");
			} else if(dataType==4) {
				returnString.append("{\"visible\":true,\"data\":")
				.append(installCount).append(",\"name\":\"当日总安装\"}");
			}
			returnString.append("],\"result\":\"success\"}");	
			printJsonMessage(returnString.toString());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return null;
	}

	public String doExport() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(searchVO == null) {
				searchVO = new SearchVO();
			}
			UserVO userVO = (UserVO) getSession(PkigConstants.SESSION_USER);
			if (userVO.getCustomerId() > 0) {
				searchVO.setCustomerId(userVO.getCustomerId());
			}
			if(userVO.getAgentId()>0) {
				searchVO.setAgentId(userVO.getAgentId());
			}
			try {
				projectDataVOList = projectDataService.getProjectDataVOList(searchVO);
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
				String downloadFileName = (sf.format(new Date()).toString())
						+ "项目数据.xls";
				try {
					downloadFileName = new String(downloadFileName.getBytes("gb2312"),
							"ISO8859-1");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				this.setDownloadFileName(downloadFileName);
			} catch (DatabaseException e) {
				logger.debug(e);
				printMessage("-1");
				return null;
			}
			HSSFWorkbook workbook = exportExcel();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			workbook.write(output);
			byte[] ba = output.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			output.flush();
			output.close();
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return "excel";
	}

	private HSSFWorkbook exportExcel() {
		HSSFWorkbook workbook = null;
		try {
			// 创建工作簿实例
			workbook = new HSSFWorkbook();
			// 创建工作表实例
			HSSFSheet sheet = workbook.createSheet("data");
			// 设置列宽
			this.setSheetColumnWidth(sheet);
			// 获取样式
			HSSFCellStyle titleStyle = this.createTitleStyle(workbook);
			HSSFCellStyle bodyStyle = this.createBodyStyle(workbook);
			HSSFCellStyle bodyRedStyle = this.createBodyRedStyle(workbook);
			if(projectDataVOList!=null&&projectDataVOList.size()>0) {

				// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				int i=0;
				if(searchVO.getRowFieldVO().getIsShowDate()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "时间");
					i++;
				}

				if(searchVO.getRowFieldVO().getIsShowProject()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目");
					i++;
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目ID");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowProduct()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "产品");
					i++;
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "产品ID");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowCountry()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "国家");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowType()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "类型");
					i++;
				}
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "活跃");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "总活跃");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送次数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送成功数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送接收率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送展示人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送展示数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送展示率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送点击数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送点击率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送下载数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送下载率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "推送安装率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏次数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏成功数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏接收率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏展示人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏展示数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏展示率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏点击数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏点击率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏下载数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏下载率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "插屏安装率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "总安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "aipu");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "下沉安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "下沉率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导展示人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导展示数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导点击数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导点击率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导下载数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导下载率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下次数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下成功数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下接收率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下展示人数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下展示次数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下展示率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下点击数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下点击率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下下载数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下下载率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下安装率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下激活数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "aipu");
				HSSFRow row1 = null;
				DecimalFormat df = new DecimalFormat("#0.00");
				for(int j=0;j<projectDataVOList.size();j++) {	
					ProjectDataVO p = projectDataVOList.get(j);
					row1 = sheet.createRow((short) (j+1));// 建立新行
					int k=0;
					if(searchVO.getRowFieldVO().getIsShowDate()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getStatDate());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowProject()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectName());
						k++;
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectId());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowProduct()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProductName());
						k++;
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProductId());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowCountry()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getCountryName());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowType()==1) {
						if(p.getType()==600400) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "SDK");
						} else if(p.getType()==600403) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "引导");
						} else if(p.getType()==600404) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "下沉");
						} else if(p.getType()==600405) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "线下");
						}
						k++;
					}
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getUserCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getActiveCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getTotalActiveCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushSendCount());//金额
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushSendTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushReceiveTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getReceivePushPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushShowCount());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushShowTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getShowPushPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushClickTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getClickPushPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushDownloadTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getDownloadPushPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallPushPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSendCount());//金额
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSendTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getReceiveTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getReceivePercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getShowCount());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getShowTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getShowPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getClickTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getClickPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getDownloadTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getDownloadPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallPercent());
					k++;
					//总安装数
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getTotalInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getTotalAipu());
					k++;
					//下沉
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSinkInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSinkAipu()*100);
					k++;
					//引导
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadShowCount());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadShowTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadClickTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getClickLeadPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadDownloadTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getDownloadLeadPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadAipu()*100);
					k++;
					//线下
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceSendCount());//金额
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceSendTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceReceiveTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getReceiveSilencePercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceShowCount());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceShowTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getShowSilencePercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceClickTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getClickSilencePercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceDownloadTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getDownloadSilencePercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallSilencePercent());
					k++;
					//总安装数
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getActivateTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilencePercent());
					k++;
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	private HSSFWorkbook exportTotalExcel() {
		HSSFWorkbook workbook = null;
		try {
			// 创建工作簿实例
			workbook = new HSSFWorkbook();
			// 创建工作表实例
			HSSFSheet sheet = workbook.createSheet("data");
			// 设置列宽
			this.setSheetColumnWidth(sheet);
			// 获取样式
			HSSFCellStyle titleStyle = this.createTitleStyle(workbook);
			HSSFCellStyle bodyStyle = this.createBodyStyle(workbook);
			HSSFCellStyle bodyRedStyle = this.createBodyRedStyle(workbook);
			if(projectDataVOList!=null&&projectDataVOList.size()>0) {

				// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				int i=0;
				
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目ID");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "SDK销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "下沉销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "SDK安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "下沉安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "广告总收益");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "第三方收益");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "链接收益");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "渠道转化数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "渠道转化率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "渠道成本");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "利润");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "销量利润");
				i++;
				HSSFRow row1 = null;
				DecimalFormat df = new DecimalFormat("#0.00");
				for(int j=0;j<projectDataVOList.size();j++) {	
					ProjectDataVO p = projectDataVOList.get(j);
					row1 = sheet.createRow((short) (j+1));// 建立新行
					int k=0;
					
					this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectName());
					k++;
					this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectId());
					k++;
					
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getUserCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadUserCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getApkUserCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceUserCount());//金额
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallTimes()+p.getPushInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadTypeInstallTimes()+p.getLeadPushInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getApkInstallTimes()+p.getApkPushInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceSilenceInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getIncome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getThirdIncome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLinkIncome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getTotalUsers());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getConversionPercent());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getOutcome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProfit());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProfitRegister());
					k++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	private HSSFWorkbook exportTotalListExcel() {
		HSSFWorkbook workbook = null;
		try {
			// 创建工作簿实例
			workbook = new HSSFWorkbook();
			// 创建工作表实例
			HSSFSheet sheet = workbook.createSheet("data");
			// 设置列宽
			this.setSheetColumnWidth(sheet);
			// 获取样式
			HSSFCellStyle titleStyle = this.createTitleStyle(workbook);
			HSSFCellStyle bodyStyle = this.createBodyStyle(workbook);
			HSSFCellStyle bodyRedStyle = this.createBodyRedStyle(workbook);
			if(projectDataVOList!=null&&projectDataVOList.size()>0) {
				// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				int i=0;
				if(searchVO.getRowFieldVO().getIsShowDate()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "时间");
					i++;
				}

				if(searchVO.getRowFieldVO().getIsShowProject()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目");
					i++;
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目ID");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowProduct()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "产品");
					i++;
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "产品ID");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowCountry()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "国家");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowType()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "类型");
					i++;
				}
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "活跃");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "总活跃");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "安装数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "下沉数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "aipu");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "下沉率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "引导率");
				i++;
				HSSFRow row1 = null;
				DecimalFormat df = new DecimalFormat("#0.00");
				for(int j=0;j<projectDataVOList.size();j++) {	
					ProjectDataVO p = projectDataVOList.get(j);
					row1 = sheet.createRow((short) (j+1));// 建立新行
					int k=0;
					if(searchVO.getRowFieldVO().getIsShowDate()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getStatDate());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowProject()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectName());
						k++;
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectId());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowProduct()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProductName());
						k++;
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProductId());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowCountry()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getCountryName());
						k++;
					}
					if(searchVO.getRowFieldVO().getIsShowType()==1) {
						if(p.getType()==600400) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "SDK");
						} else if(p.getType()==600403) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "引导");
						} else if(p.getType()==600404) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "下沉");
						} else if(p.getType()==600405) {
							this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "线下");
						}
						k++;
					}
					
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getUserCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectUserCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getActiveCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getTotalActiveCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallTimes()+p.getPushInstallTimes()+p.getSilenceInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSinkInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadInstallTimes());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushAipu()+p.getAipu());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSinkAipu()*100.0);
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLeadAipu()*100.0);
					k++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	private void setSheetColumnWidth(HSSFSheet sheet) {
		// 根据你数据里面的记录有多少列，就设置多少列
		sheet.setColumnWidth((short) 0, (short) 5000);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 3000);
		sheet.setColumnWidth((short) 5, (short) 3000);
		sheet.setColumnWidth((short) 6, (short) 3000);
		sheet.setColumnWidth((short) 7, (short) 3000);
	}

	// 设置excel的title样式
	private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		HSSFFont font2 = wb.createFont();
		font2.setFontName("宋体");
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font2.setFontHeightInPoints((short) 11);
		style.setFont(font2);		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		return style;
	}
	
	// 设置excel的title样式
	private HSSFCellStyle createBodyStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		boldFont.setFontName("宋体");
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		return style;

	}
	// 设置excel的title样式
	private HSSFCellStyle createBodyRedStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		boldFont.setColor(HSSFColor.RED.index);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		return style;

	}

	// 创建Excel单元格
	private void createCell(HSSFRow row, int column, HSSFCellStyle style,
			int cellType, Object value) {
		HSSFCell cell = row.createCell((short) column);
		if (style != null) {
			cell.setCellStyle(style);
		}
		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {}
			break;
		case HSSFCell.CELL_TYPE_STRING: {
				cell.setCellValue(value.toString());		
			}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
		}
			break;
		default:
			break;
		}
	}
	
	
	
	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public ProjectDataVO getProjectDataVO() {
		return projectDataVO;
	}

	public void setProjectDataVO(ProjectDataVO projectDataVO) {
		this.projectDataVO = projectDataVO;
	}

	public List<ProjectDataVO> getProjectDataVOList() {
		return projectDataVOList;
	}

	public void setProjectDataVOList(List<ProjectDataVO> projectDataVOList) {
		this.projectDataVOList = projectDataVOList;
	}

	public ProjectDataService getProjectDataService() {
		return projectDataService;
	}

	public void setProjectDataService(ProjectDataService projectDataService) {
		this.projectDataService = projectDataService;
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
