package com.kkgame.feeop.data.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.DataService;
import com.kkgame.feeop.data.service.ProjectIncomeService;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;
import com.kkgame.feeop.util.URLUtil;

public class ProjectIncomeAction extends BaseAction {

	private static final String ACTION_RESULT_QUERY_POST = "postquery";

	private static final String ACTION_RESULT_LIST_POST = "postlist";

	private static final String ACTION_RESULT_QUERY_TOTAL = "totalQuery";

	private static final String ACTION_RESULT_LIST_TOTAL = "totalList";

	private static final String ACTION_RESULT_QUERY_EFFECT = "effectQuery";
	private static final String ACTION_RESULT_LIST_EFFECT = "effectList";
	
	private static Log logger = LogFactory.getLog(ProjectIncomeAction.class);
	
	private SearchVO searchVO;
	
	private ProjectIncomeService projectIncomeService;
	
	private ProjectIncomeVO projectIncomeVO;
	
	private DataService dataService;
	
	private List<ProjectIncomeVO> projectIncomeVOList;
	
	private InputStream excelFile;

	private String downloadFileName;
	
	private RetentionService retentionService;

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
			projectIncomeVOList = projectIncomeService.getProjectIncomeVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	
	public String doTotalQuery() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_TOTAL;
	}
	
	public String doEffectQuery() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_EFFECT;
	}
	
	public String doTotalList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		try {
			projectIncomeVOList = projectIncomeService.getTotalProjectVOList(searchVO);
			Map<String,ProjectIncomeVO> sdkMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> leadMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> sinkMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> silenceMap = new HashMap<String, ProjectIncomeVO>();
			
			for(ProjectIncomeVO projectIncomeVO:projectIncomeVOList) {
				StringBuffer sb = new StringBuffer();
				sb.append(projectIncomeVO.getProjectId());
				if(projectIncomeVO.getType()==600400) {
					sdkMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600403) {
					leadMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600404) {
					sinkMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600405) {
					silenceMap.put(sb.toString(), projectIncomeVO);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> sdkEntry:sdkMap.entrySet()) {
				String key = sdkEntry.getKey();
				ProjectIncomeVO sdkIncome = sdkEntry.getValue();
				if(leadMap.containsKey(key)) {
					ProjectIncomeVO leadIncome  = leadMap.get(key);
					sdkIncome.setLeadNewUsers(leadIncome.getNewUsers());
					sdkIncome.setLeadInstallTotal(leadIncome.getInstallTotal());
					sdkIncome.setIncome(leadIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setThirdIncome(leadIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setLinkIncome(leadIncome.getLinkIncome()+sdkIncome.getLinkIncome());
					sdkIncome.setProfit(leadIncome.getProfit()+sdkIncome.getProfit());
				}
				if(sinkMap.containsKey(key)) {
					ProjectIncomeVO sinkIncome  = sinkMap.get(key);
					sdkIncome.setSinkNewUsers(sinkIncome.getNewUsers());
					sdkIncome.setSinkInstallTotal(sinkIncome.getInstallTotal());
					sdkIncome.setIncome(sinkIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setThirdIncome(sinkIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setLinkIncome(sinkIncome.getLinkIncome()+sinkIncome.getLinkIncome());
					sdkIncome.setProfit(sinkIncome.getProfit()+sdkIncome.getProfit());
				}
				if(silenceMap.containsKey(key)) {
					ProjectIncomeVO silenceIncome  = silenceMap.get(key);
					sdkIncome.setSilenceNewUsers(silenceIncome.getNewUsers());
					sdkIncome.setSilenceInstallTotal(silenceIncome.getInstallTotal());
					sdkIncome.setIncome(silenceIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setThirdIncome(silenceIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setLinkIncome(silenceIncome.getLinkIncome()+silenceIncome.getLinkIncome());
					sdkIncome.setProfit(silenceIncome.getProfit()+sdkIncome.getProfit());
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> leadEntry:leadMap.entrySet()) {
				String key = leadEntry.getKey();
				ProjectIncomeVO leadIncome = leadEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					leadIncome.setLeadNewUsers(leadIncome.getNewUsers());
					leadIncome.setLeadInstallTotal(leadIncome.getInstallTotal());
					sdkMap.put(key, leadIncome);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> sinkEntry:sinkMap.entrySet()) {
				String key = sinkEntry.getKey();
				ProjectIncomeVO sinkIncome = sinkEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					sinkIncome.setSinkNewUsers(sinkIncome.getNewUsers());
					sinkIncome.setSinkInstallTotal(sinkIncome.getInstallTotal());
					sdkMap.put(key, sinkIncome);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> silenceEntry:silenceMap.entrySet()) {
				String key = silenceEntry.getKey();
				ProjectIncomeVO silenceIncome = silenceEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					silenceIncome.setSilenceNewUsers(silenceIncome.getNewUsers());
					silenceIncome.setSilenceInstallTotal(silenceIncome.getInstallTotal());
					sdkMap.put(key, silenceIncome);
				}
			}
			projectIncomeVOList = new ArrayList<ProjectIncomeVO>();
			DecimalFormat df = new DecimalFormat("#####.00"); 
			for(Map.Entry<String, ProjectIncomeVO> sdkEntry:sdkMap.entrySet()) {
				String key = sdkEntry.getKey();
				ProjectIncomeVO sdkIncome = sdkEntry.getValue();
				sdkIncome.setIncome(Double.parseDouble(df.format(sdkIncome.getIncome())));
				sdkIncome.setOutcome(Double.parseDouble(df.format(sdkIncome.getOutcome())));
				sdkIncome.setProfit(Double.parseDouble(df.format(sdkIncome.getProfit())));
				sdkIncome.setQualityWarn(Double.parseDouble(df.format(sdkIncome.getFirstPercent()/0.2*0.4+sdkIncome.getActiveInstallPercent()/20*0.6)));
				projectIncomeVOList.add(sdkIncome);
			}
			if(projectIncomeVOList.size()>0) {
				Collections.sort(projectIncomeVOList, new Comparator<ProjectIncomeVO>() {
					@Override
					public int compare(ProjectIncomeVO o1, ProjectIncomeVO o2) {
						if(o2.getNewUsers()>o1.getNewUsers()) {
							return 1;
						}
						return -1;
					}
				});
			}
			
//			List<RetentionVO> retentionVOList = retentionService.getTotalRetentionVOList(searchVO);
//			for(ProjectIncomeVO pVO:projectIncomeVOList) {
//				for(RetentionVO rVO:retentionVOList) {
//					if(pVO.getStatDate().equals(rVO.getStatDate())&&pVO.getProjectId()==rVO.getProjectId()) {
//						pVO.setFirstPercent(rVO.getFirstPercent());
//						pVO.setFirstCount(rVO.getFirstCount());
//					}
//				}
//			}
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST_TOTAL;
	}
	

	public String doEffectList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		try {
			projectIncomeVOList = projectIncomeService.getEffectProjectVOList(searchVO);
			Map<String,ProjectIncomeVO> sdkMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> leadMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> sinkMap = new HashMap<String, ProjectIncomeVO>();
			Map<String,ProjectIncomeVO> silenceMap = new HashMap<String, ProjectIncomeVO>();
			for(ProjectIncomeVO projectIncomeVO:projectIncomeVOList) {
				StringBuffer sb = new StringBuffer();
				sb.append(projectIncomeVO.getProjectId());
				if(projectIncomeVO.getType()==600400) {
					sdkMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600403) {
					leadMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600404) {
					sinkMap.put(sb.toString(), projectIncomeVO);
				} else if(projectIncomeVO.getType()==600405) {
					silenceMap.put(sb.toString(), projectIncomeVO);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> sdkEntry:sdkMap.entrySet()) {
				String key = sdkEntry.getKey();
				ProjectIncomeVO sdkIncome = sdkEntry.getValue();
				if(leadMap.containsKey(key)) {
					ProjectIncomeVO leadIncome  = leadMap.get(key);
					sdkIncome.setLeadNewUsers(leadIncome.getNewUsers());
					sdkIncome.setLeadInstallTotal(leadIncome.getInstallTotal());
					sdkIncome.setIncome(leadIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setLinkIncome(leadIncome.getLinkIncome()+sdkIncome.getLinkIncome());
					sdkIncome.setThirdIncome(leadIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setProfit(leadIncome.getProfit()+sdkIncome.getProfit());
					sdkIncome.setTotalActUsers(leadIncome.getTotalActUsers()+sdkIncome.getTotalActUsers());
					sdkIncome.setInstallTotal(leadIncome.getInstallTotal()+sdkIncome.getInstallTotal());
				}
				if(sinkMap.containsKey(key)) {
					ProjectIncomeVO sinkIncome  = sinkMap.get(key);
					sdkIncome.setSinkNewUsers(sinkIncome.getNewUsers());
					sdkIncome.setSinkInstallTotal(sinkIncome.getInstallTotal());
					sdkIncome.setIncome(sinkIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setLinkIncome(sinkIncome.getLinkIncome()+sinkIncome.getLinkIncome());
					sdkIncome.setThirdIncome(sinkIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setProfit(sinkIncome.getProfit()+sdkIncome.getProfit());
					sdkIncome.setTotalActUsers(sinkIncome.getTotalActUsers()+sdkIncome.getTotalActUsers());
					sdkIncome.setInstallTotal(sinkIncome.getInstallTotal()+sdkIncome.getInstallTotal());
				}
				if(silenceMap.containsKey(key)) {
					ProjectIncomeVO silenceIncome  = silenceMap.get(key);
					sdkIncome.setSilenceNewUsers(silenceIncome.getNewUsers());
					sdkIncome.setSilenceInstallTotal(silenceIncome.getInstallTotal());
					sdkIncome.setIncome(silenceIncome.getIncome()+sdkIncome.getIncome());
					sdkIncome.setLinkIncome(silenceIncome.getLinkIncome()+silenceIncome.getLinkIncome());
					sdkIncome.setThirdIncome(silenceIncome.getThirdIncome()+sdkIncome.getThirdIncome());
					sdkIncome.setProfit(silenceIncome.getProfit()+sdkIncome.getProfit());
					sdkIncome.setTotalActUsers(silenceIncome.getTotalActUsers()+sdkIncome.getTotalActUsers());
					sdkIncome.setInstallTotal(silenceIncome.getInstallTotal()+sdkIncome.getInstallTotal());
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> leadEntry:leadMap.entrySet()) {
				String key = leadEntry.getKey();
				ProjectIncomeVO leadIncome = leadEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					leadIncome.setLeadNewUsers(leadIncome.getNewUsers());
					leadIncome.setLeadInstallTotal(leadIncome.getInstallTotal());
					sdkMap.put(key, leadIncome);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> sinkEntry:sinkMap.entrySet()) {
				String key = sinkEntry.getKey();
				ProjectIncomeVO sinkIncome = sinkEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					sinkIncome.setSinkNewUsers(sinkIncome.getNewUsers());
					sinkIncome.setSinkInstallTotal(sinkIncome.getInstallTotal());
					sdkMap.put(key, sinkIncome);
				}
			}
			for(Map.Entry<String, ProjectIncomeVO> silenceEntry:silenceMap.entrySet()) {
				String key = silenceEntry.getKey();
				ProjectIncomeVO silenceIncome = silenceEntry.getValue();
				if(!sdkMap.containsKey(key)) {
					silenceIncome.setSilenceNewUsers(silenceIncome.getNewUsers());
					silenceIncome.setSilenceInstallTotal(silenceIncome.getInstallTotal());
					sdkMap.put(key, silenceIncome);
				}
			}
			projectIncomeVOList = new ArrayList<ProjectIncomeVO>();
			DecimalFormat df = new DecimalFormat("#####.000"); 
			for(Map.Entry<String, ProjectIncomeVO> sdkEntry:sdkMap.entrySet()) {
				String key = sdkEntry.getKey();
				ProjectIncomeVO sdkIncome = sdkEntry.getValue();
				sdkIncome.setIncome(Double.parseDouble(df.format(sdkIncome.getIncome())));
				sdkIncome.setTotalIncome(Double.parseDouble(df.format(sdkIncome.getIncome()+sdkIncome.getThirdIncome()+sdkIncome.getLinkIncome())));
				sdkIncome.setOutcome(Double.parseDouble(df.format(sdkIncome.getOutcome())));
				sdkIncome.setProfit(Double.parseDouble(df.format(sdkIncome.getProfit())));
				if(sdkIncome.getOutcome()>0) {
					sdkIncome.setIo(Double.parseDouble(df.format((sdkIncome.getIncome()+sdkIncome.getThirdIncome()+sdkIncome.getLinkIncome())/sdkIncome.getOutcome())));
				}else if(sdkIncome.getOutcome()==0&&sdkIncome.getIncome()>0) {
					sdkIncome.setIo(1.0);
				}
				if(sdkIncome.getTotalActUsers()>0) {
					sdkIncome.setActiveInstallPercent(Double.parseDouble(df.format(sdkIncome.getInstallTotal()*100.0/sdkIncome.getTotalActUsers())));
				}
				sdkIncome.setQualityWarn(Double.parseDouble(df.format(sdkIncome.getFirstPercent()/0.2*0.4+sdkIncome.getActiveInstallPercent()/20*0.6)));
				projectIncomeVOList.add(sdkIncome);
			}
			if(projectIncomeVOList.size()>0) {
				Collections.sort(projectIncomeVOList, new Comparator<ProjectIncomeVO>() {
					@Override
					public int compare(ProjectIncomeVO o1, ProjectIncomeVO o2) {
						if(o2.getNewUsers()>o1.getNewUsers()) {
							return 1;
						}
						return -1;
					}
				});
			}
			
//			List<RetentionVO> retentionVOList = retentionService.getTotalRetentionVOList(searchVO);
//			for(ProjectIncomeVO pVO:projectIncomeVOList) {
//				for(RetentionVO rVO:retentionVOList) {
//					if(pVO.getStatDate().equals(rVO.getStatDate())&&pVO.getProjectId()==rVO.getProjectId()) {
//						pVO.setFirstPercent(rVO.getFirstPercent());
//						pVO.setFirstCount(rVO.getFirstCount());
//					}
//				}
//			}
			
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST_EFFECT;
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
				projectIncomeVOList = projectIncomeService.getProjectIncomeVOList(searchVO);
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
			if(projectIncomeVOList!=null&&projectIncomeVOList.size()>0) {
				// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				int i=0;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "时间");
				i++;
				if(searchVO.getRowFieldVO().getIsShowAgent()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "代理");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowBd()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "商务");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowCustomer()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "客户");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowProject()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目");
					i++;
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目ID");
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
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "高");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "中");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "低");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "安装次数");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "广告收益");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "第三方收益");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "链接收益");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "结算高");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "结算中");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "结算低");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "结算额");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "利润率");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "转化率");
				HSSFRow row1 = null;
				DecimalFormat df = new DecimalFormat("#0.00");
				for(int j=0;j<projectIncomeVOList.size();j++) {	
					ProjectIncomeVO p = projectIncomeVOList.get(j);
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
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getNewUsers());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getNewProjectUsers());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallHigh());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallMid());//金额
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallLow());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getInstallTotal());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getIncome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getThirdIncome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLinkIncome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getHighUsers());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getMidUsers());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getLowUsers());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getOutcome());
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getRateOut());
					k++;
					if(p.getNewUsers()==0) {
						this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, 0);

					} else {
						this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, (p.getHighUsers()+p.getMidUsers()+p.getLowUsers())*100.0/p.getNewUsers());
					}
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
	
	
	public String doPostQuery() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_POST;
	}

	public String doPostList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			projectIncomeVOList = projectIncomeService.getProjectIncomeVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST_POST;
	}
	
	
	
	public String doPostModify() {
		if(projectIncomeVO==null) {
			projectIncomeVO = new ProjectIncomeVO();
		}
		try {
			projectIncomeVO = projectIncomeService.getProjectIncomeVO(projectIncomeVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
		}
		return ACTION_RESULT_MODIFY;
	}
	
	public String doPostUpdate() {
		if(projectIncomeVO==null) {
			projectIncomeVO = new ProjectIncomeVO();
		}
		try {
			projectIncomeService.updateProjectIncomeVO(projectIncomeVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("修改配置成功！");
		return null;
	}
	
	public String doGenerateData() {
		if(projectIncomeVO==null) {
			projectIncomeVO = new ProjectIncomeVO();
		}
		try {
			DataVO data = new DataVO();
			data.setStatDate(projectIncomeVO.getStatDate());
			data.setProjectId(projectIncomeVO.getProjectId());
			String month = CalendarFormat.switchFormatDate(data.getStatDate(),"yyyy-MM-dd","yyyyMM");
			data.setTable(month);
			data.setYearMonth(Integer.parseInt(month));
			dataService.updateProjectIncomeNew(data);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		//生成新的
		printMessage("生成新数据成功");
		return null;
	}
	
	
	
	public String doPostData() {
		if(projectIncomeVO==null) {
			projectIncomeVO = new ProjectIncomeVO();
		}
		try {
			projectIncomeVO = projectIncomeService.getProjectIncomeVO(projectIncomeVO);
			if((projectIncomeVO.getHighUsers()+projectIncomeVO.getLowUsers()+projectIncomeVO.getMidUsers())>0) {
				StringBuffer sb = new StringBuffer();
				sb.append("userRegisterVO.time=").append(projectIncomeVO.getStatDate()).append("&");
				sb.append("userRegisterVO.projectId=").append(projectIncomeVO.getProjectId()).append("&");
				sb.append("userRegisterVO.registerHighCount=").append(projectIncomeVO.getHighUsers()).append("&");
				sb.append("userRegisterVO.registerMidCount=").append(projectIncomeVO.getMidUsers()).append("&");
				sb.append("userRegisterVO.registerLowCount=").append(projectIncomeVO.getLowUsers());
				String resp = URLUtil.sendPost(PkigConstants.POST_URL, sb.toString());
//				String resp = URLUtil.sendPost("", sb.toString());
				if("ok".equals(resp)) {
					projectIncomeService.updateProjectIncomeVOStatus(projectIncomeVO);
				}
				logger.info(resp);
			} else {
				printMessage("无可同步数据");
				return null;
			}
			
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("同步数据成功");
		return null;
	}
	
	public String doPostDataList() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
		}
		try {
			searchVO.setRowFieldString("1,18,");
			projectIncomeVOList = projectIncomeService.getProjectIncomeVOList(searchVO);
			//发送合作数据
			for(ProjectIncomeVO p:projectIncomeVOList) {
				if((p.getHighUsers()+p.getLowUsers()+p.getMidUsers())>0) {
					StringBuffer sb = new StringBuffer();
					sb.append("userRegisterVO.time=").append(p.getStatDate()).append("&");
					sb.append("userRegisterVO.projectId=").append(p.getProjectId()).append("&");
					sb.append("userRegisterVO.registerHighCount=").append(p.getHighUsers()).append("&");
					sb.append("userRegisterVO.registerMidCount=").append(p.getMidUsers()).append("&");
					sb.append("userRegisterVO.registerLowCount=").append(p.getLowUsers());
					String resp = URLUtil.sendPost(PkigConstants.POST_URL, sb.toString());
					if("ok".equals(resp)) {
						projectIncomeService.updateProjectIncomeVOStatus(p);
					}
//					String resp = URLUtil.sendPost("", sb.toString());
					logger.info(resp);
				}
			}
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
		}
		printMessage("同步数据成功");
		return null;
	}

	
	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public ProjectIncomeService getProjectIncomeService() {
		return projectIncomeService;
	}

	public void setProjectIncomeService(ProjectIncomeService projectIncomeService) {
		this.projectIncomeService = projectIncomeService;
	}

	public ProjectIncomeVO getProjectIncomeVO() {
		return projectIncomeVO;
	}

	public void setProjectIncomeVO(ProjectIncomeVO projectIncomeVO) {
		this.projectIncomeVO = projectIncomeVO;
	}

	public List<ProjectIncomeVO> getProjectIncomeVOList() {
		return projectIncomeVOList;
	}

	public void setProjectIncomeVOList(List<ProjectIncomeVO> projectIncomeVOList) {
		this.projectIncomeVOList = projectIncomeVOList;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getDownloadFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String downloadFileName = (sf.format(new Date()).toString())
				+ "收益数据.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes("gb2312"),
					"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public RetentionService getRetentionService() {
		return retentionService;
	}

	public void setRetentionService(RetentionService retentionService) {
		this.retentionService = retentionService;
	}
}
