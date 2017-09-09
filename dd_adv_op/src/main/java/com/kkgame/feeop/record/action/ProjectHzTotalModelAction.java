package com.kkgame.feeop.record.action;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.kkgame.feeop.excel.DataRowBean;
import com.kkgame.feeop.excel.DataSheetBean;
import com.kkgame.feeop.excel.ExportExcel;
import com.kkgame.feeop.sdkinfo.bean.ProjectSdkVO;
import com.kkgame.feeop.sdkinfo.bean.ThreeSdkVO;
import com.kkgame.feeop.sdkinfo.service.ThreeSdkService;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.TwoRelate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.service.DataService;
import com.kkgame.feeop.record.bean.ProjectHzTotalModelVO;
import com.kkgame.feeop.record.service.ProjectHzTotalModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class ProjectHzTotalModelAction extends BaseAction {
	private static Log logger = LogFactory.getLog(ProjectHzTotalModelAction.class);
	private static final String ACTION_RESULT_DOWNLOAD = "download";

	private ProjectHzTotalModelVO projectHzTotalModelVO;
	private List<ProjectHzTotalModelVO> projectHzTotalModelVOList;
	private ProjectHzTotalModelService projectHzTotalModelService;
	private DataService dataService;
	private DataVO dataVO;
	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private String message = "你已成功上传文件";
	// 导出模版
	private ThreeSdkService threeSdkService;
	private String exportDate;
	private int logDateNum;

	public String doDownloadImportTemplate() {
		/***************写入第一页数据：第三方收益******************/
		// 获得数据
		List<ProjectSdkVO> projectSdkVOList;
		List<ThreeSdkVO> threeSdkVOList;
		List<ProjectSdkVO> projectSdkLogsList = new ArrayList<>();
		try {
			threeSdkVOList = threeSdkService.getAllThreeSdkVOList();
			projectSdkVOList = threeSdkService.getAllExportProjectSdkVOList(exportDate);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDay = new Date(CalendarFormat.getChangDate(sdf.parse(exportDate), -logDateNum));
			SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
			projectSdkLogsList = threeSdkService.getExportProjectSdkLogsList(ymdFormat.format(startDay), exportDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (null == projectSdkVOList || 0 == projectSdkVOList.size() || null == threeSdkVOList || 0 == threeSdkVOList.size()) {
			super.setErrorMsg("目前没有创建任何第三方SDK项目");
			return PlAIN_MESSAGE;
		}

		// 将数据写入响应流中
		DataSheetBean dataSheetBean = new DataSheetBean();
		// 1、列信息
		int numType = HSSFCell.CELL_TYPE_NUMERIC;
		int strType = HSSFCell.CELL_TYPE_STRING;
		LinkedList<TwoRelate<String, Integer>> columnList = new LinkedList<>();
		columnList.addAll(Arrays.asList( new TwoRelate<>("时间", strType), new TwoRelate<>("项目", numType),
				new TwoRelate<>("项目销量", numType), new TwoRelate<>("项目说明", strType)));

		String threeSdkName;
		for (ThreeSdkVO threeSdkVO : threeSdkVOList) {
			threeSdkName = threeSdkVO.getName();
			columnList.addAll(Arrays.asList(new TwoRelate<>(threeSdkName, numType), new TwoRelate<>(threeSdkName + "key", strType),
					new TwoRelate<>(threeSdkName + "新增", numType), new TwoRelate<>(threeSdkName + "收益", numType)));
		}
		dataSheetBean.setColumes(columnList);
		// 2、行数据
		// 2.1 三方sdk字典
		Map<Integer, String> threeSdkMap = new LinkedHashMap<>();
		for (ThreeSdkVO threeSdkVO : threeSdkVOList) {
			threeSdkMap.put(threeSdkVO.getId(), threeSdkVO.getName());
		}
		// 2.2 项目字典（一个项目对应多个sdk）
		int threeSdkSize = threeSdkVOList.size();
		Map<Integer, Map<Integer, ProjectSdkVO>> projectMap = new HashMap<>();
		int projectId; Map<Integer, ProjectSdkVO> projectSdkMap;
		for (ProjectSdkVO projectSdkVO : projectSdkVOList) {
			projectId = projectSdkVO.getProjectId();
			projectSdkMap = projectMap.get(projectId);
			if (null == projectSdkMap) {
				projectSdkMap = new HashMap<>(threeSdkSize);
				projectSdkMap.put(projectSdkVO.getSdkId(), projectSdkVO);
				projectMap.put(projectId, projectSdkMap);
			} else {
				projectSdkMap.put(projectSdkVO.getSdkId(), projectSdkVO);
			}
		}
		// 2.3 将项目sdk转变为excel数据
		List<DataRowBean> dataRowBeanList = new ArrayList<>();
		for (Map.Entry<Integer, Map<Integer, ProjectSdkVO>> project : projectMap.entrySet()) {
			dataRowBeanList.add(new ExportProjectSdk(threeSdkMap, project.getValue(), exportDate));
		}
		dataSheetBean.setRowsData(dataRowBeanList);
		/***************写入第二页数据：操作日志******************/
		// 将数据写入响应流中
		DataSheetBean dataSheetBeanLogs = new DataSheetBean();
		// 1、列信息
		columnList = new LinkedList<>();
		columnList.addAll(Arrays.asList( new TwoRelate<>("时间", strType), new TwoRelate<>("操作日志", strType)));
		dataSheetBeanLogs.setColumes(columnList);
		// 2、行数据
		dataRowBeanList = new ArrayList<>();
		for (ProjectSdkVO projectSdkVO : projectSdkLogsList) {
			dataRowBeanList.add(new ExportProjectSdkLog(projectSdkVO));
		}
		dataSheetBeanLogs.setRowsData(dataRowBeanList);
		/***********************写入响应流*********************/
		List<TwoRelate<String, DataSheetBean>> dataSheetList = new ArrayList<>(2);
		dataSheetList.add(new TwoRelate<>("第三方项目收益表", dataSheetBean));
		dataSheetList.add(new TwoRelate<>("日志", dataSheetBeanLogs));
		ExportExcel.exportExcel(exportDate + " 第三方项目收益表.xls", dataSheetList);
		return null;
	}

	public String doDownload() {
		return ACTION_RESULT_DOWNLOAD;
	}

	public String doList() {
		if (projectHzTotalModelVO == null) {
			projectHzTotalModelVO = new ProjectHzTotalModelVO();
			projectHzTotalModelVO.setSdate(DateUtils.formatDate(new Date()));
		}
		try {
			if (projectHzTotalModelVO.getSdate() != null
					&& !projectHzTotalModelVO.getSdate().equals("")) {
				String tablename = "PROJECT_HZ_TOTAL_";
				String month = projectHzTotalModelVO.getSdate().replaceAll("-",
						"").substring(0, 6);
				projectHzTotalModelVO.setTable(tablename + month);
				projectHzTotalModelVOList = projectHzTotalModelService
						.getProjectHzTotalModelVOList(projectHzTotalModelVO);
			}
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if (projectHzTotalModelVO == null) {
			projectHzTotalModelVO = new ProjectHzTotalModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (projectHzTotalModelVO == null) {
			projectHzTotalModelVO = new ProjectHzTotalModelVO();
		}
		try {
//			String filePath = "/home/kok/app/advsdk/op/ROOT/";
//			String filePath = "/Users/rayi/Documents";
			StringBuffer sb = new StringBuffer();
			if (file == null || (fileFileName.indexOf(".exe") > -1)) {
				message = "文件不能为空或exe文件";
				sb.append("{msg:\"").append(message).append("\"}");
				this.printMessage(sb.toString());
				return null;
			}
			try {
				List<DataVO> list = getFileData(file);
				if(null!=list&&list.size()>0) {
					String statDate = list.get(0).getStatDate();
					for (DataVO vo : list) {
						if (!statDate.equals(vo.getStatDate())) {
							this.printMessage(sb.toString());
							message = "导入的数据不是同一天的数据";
							sb.append("{msg:\"").append(message).append("\"}");
							this.printMessage(sb.toString());
							return null;
						}
					}
					dataService.insertProjectSdkList(list,statDate);
				}
			} catch (Exception e) {
				message = "读取数据出错";
				sb.append("{msg:\"").append(message).append("\"}");
				this.printMessage(sb.toString());
				return null;
			}
			
//			dataVO = new DataVO();
//			dataVO.setProjectId(projectHzTotalModelVO.getProjectId());
//			dataVO.setStatDate(projectHzTotalModelVO.getSdate());
//			dataVO.setThirdIncome(projectHzTotalModelVO.getThirdincome());
//			dataVO.setType(1);
//			dataService.updateProjectIncome(dataVO);
//			
//			String tablename = "PROJECT_HZ_TOTAL_";
//			String month = projectHzTotalModelVO.getSdate().replaceAll("-", "")
//					.substring(0, 6);
//			projectHzTotalModelVO.setTable(tablename + month);
//			projectHzTotalModelService.create(projectHzTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			StringBuffer sb = new StringBuffer();
			message = "读取数据出错";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		StringBuffer sb = new StringBuffer();
		message = "数据上传成功";
		sb.append("{msg:\"").append(message).append("\"}");
		this.printMessage(sb.toString());
		return null;
	}
	
	private List<DataVO> getFileData(File file) throws IOException {
		List<DataVO> list = new ArrayList<DataVO>();
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
		DataVO data = null;
		for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
			Row row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
//			boolean hasValue = false;
//			DataVO data = new DataVO();
			int fakeColIndex, projectId = 0; String incomeDate = "";
			for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				// fakeColIndex 把8之后的列与之前的列以相同的方式处理
				fakeColIndex = columnIndex < 8 ? columnIndex : (columnIndex - 4) % 4 + 4;
				String value = "";
				cell = row.getCell(columnIndex);
				// 通过sdkId来过滤不存在的sdk数据
				if (4 == fakeColIndex && 0==cell.getNumericCellValue()) {
					columnIndex += 3; // 注意for循环有一个自加的操作
					continue;
				}
				// 过滤后，剩余应该是合法的数据 【第2+1、3+1、5+1 列都是无关数据列】
				if (2 != fakeColIndex && 3 != fakeColIndex && 5 != fakeColIndex && null != cell) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						System.out.println(value);
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							System.out.println(date);
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.format(date);
							} else {
								value = "";
							}
						} else {
							System.out.println(cell.getNumericCellValue());
//							if (columnIndex == 1) {
//								value = new DecimalFormat("0").format(cell
//									.getNumericCellValue());
//							} else if (columnIndex == 2) {
//								value = new DecimalFormat("0.00").format(cell
//										.getNumericCellValue());
//							}
							switch (fakeColIndex) {
								case 0: break;
								case 1:
								case 2:
									value = new DecimalFormat("0").format(cell.getNumericCellValue());
									break;
								case 3: break;
								case 4:
									value = new DecimalFormat("0").format(cell.getNumericCellValue());
									break;
								case 5: break;
								case 6:
									value = new DecimalFormat("0").format(cell.getNumericCellValue());
									break;
								case 7:
									value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
									break;
							}
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
					if (value.trim().equals("")) {
//						hasValue = false;
//						break;
						if (fakeColIndex <= 2) {
							break;
						} else {
							// 合法数据如果存在数据问题，则跳到下一个SDK数据集合【注意for循环有一个自加的操作】
							columnIndex = (columnIndex / 4 + 1) * 4 - 1;
							continue;
						}
					}
					if (fakeColIndex == 7 && "0.00".equals(value)) {
//						hasValue = false;
//						break;
						continue;
					}
					switch (fakeColIndex) {
						case 0:
							incomeDate = value;
							break;
						case 1:
							projectId = Integer.parseInt(value);
							break;
						case 2:
						case 3: break;
						case 4:
							data = new DataVO();
							data.setProjectId(projectId);
							data.setStatDate(incomeDate);
							data.setSdkId(Integer.parseInt(value));
							break;
						case 5: break;
						case 6:
							data.setActiveCount(Integer.parseInt(value));
							break;
						case 7:
							data.setThirdIncome(Double.parseDouble(value));
							data.setType(600400);
							list.add(data);
							break;
					}
//					if (columnIndex == 0) {
//						data.setStatDate(value);
//					}
//					if (columnIndex == 1) {
//						data.setStatDate(value);
//					}
//					if (columnIndex == 2) {
//						data.setSdkId(Integer.parseInt(value));
//					}
//					if (columnIndex == 3) {
//						data.setActiveCount(Integer.parseInt(value));
//					}
//					if (columnIndex == 4) {
//						data.setThirdIncome(Double.parseDouble(value));
//					}
//					hasValue = true;
				}
			}

		}
		return list;
	}

	// 修改通道
	public String doUpdate() {
		if (projectHzTotalModelVO == null) {
			projectHzTotalModelVO = new ProjectHzTotalModelVO();
		}
		try {
			String tablename = "PROJECT_HZ_TOTAL_";
			String month = projectHzTotalModelVO.getSdate().replaceAll("-", "")
					.substring(0, 6);
			projectHzTotalModelVO.setTable(tablename + month);
			projectHzTotalModelService.update(projectHzTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (projectHzTotalModelVO == null) {
			projectHzTotalModelVO = new ProjectHzTotalModelVO();
		}
		try {
			String tablename = "PROJECT_HZ_TOTAL_";
			String month = projectHzTotalModelVO.getSdate().replaceAll("-", "")
					.substring(0, 6);
			projectHzTotalModelVO.setTable(tablename + month);
			projectHzTotalModelVO = projectHzTotalModelService
					.getProjectHzTotalModelVO(projectHzTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (projectHzTotalModelVO == null) {
			projectHzTotalModelVO = new ProjectHzTotalModelVO();
		}
		try {
			String tablename = "PROJECT_HZ_TOTAL_";
			String month = projectHzTotalModelVO.getSdate().replaceAll("-", "")
					.substring(0, 6);
			projectHzTotalModelVO.setTable(tablename + month);
			projectHzTotalModelVO = projectHzTotalModelService
					.getProjectHzTotalModelVO(projectHzTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public ProjectHzTotalModelVO getProjectHzTotalModelVO() {
		return projectHzTotalModelVO;
	}

	public void setProjectHzTotalModelVO(
			ProjectHzTotalModelVO projectHzTotalModelVO) {
		this.projectHzTotalModelVO = projectHzTotalModelVO;
	}

	public List<ProjectHzTotalModelVO> getProjectHzTotalModelVOList() {
		return projectHzTotalModelVOList;
	}

	public void setProjectHzTotalModelVOList(
			List<ProjectHzTotalModelVO> projectHzTotalModelVOList) {
		this.projectHzTotalModelVOList = projectHzTotalModelVOList;
	}

	public ProjectHzTotalModelService getProjectHzTotalModelService() {
		return projectHzTotalModelService;
	}

	public void setProjectHzTotalModelService(
			ProjectHzTotalModelService projectHzTotalModelService) {
		this.projectHzTotalModelService = projectHzTotalModelService;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public DataVO getDataVO() {
		return dataVO;
	}

	public void setDataVO(DataVO dataVO) {
		this.dataVO = dataVO;
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

	public ThreeSdkService getThreeSdkService() {
		return threeSdkService;
	}

	public void setThreeSdkService(ThreeSdkService threeSdkService) {
		this.threeSdkService = threeSdkService;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public int getLogDateNum() {
		return logDateNum;
	}

	public void setLogDateNum(int logDateNum) {
		this.logDateNum = logDateNum;
	}

	public static class ExportProjectSdk extends DataRowBean {

		public ExportProjectSdk(List<ThreeSdkVO> threeSdkList) {
			LinkedList<String> columnValues = new LinkedList<>();
			columnValues.add("");
			for (ThreeSdkVO threeSdk : threeSdkList) {
				columnValues.add("" + threeSdk.getId());
				columnValues.add("");
				columnValues.add("");
			}
			super.setColumnValues(columnValues);
		}

		public ExportProjectSdk(Map<Integer, String> threeSdkMap, Map<Integer, ProjectSdkVO> project, String exportDate) {
			LinkedList<String> columnValues = new LinkedList<>();
			columnValues.add(exportDate);
			// 项目所有SDK
			StringBuilder sb = new StringBuilder();
			int sdkId; boolean isAddProject = true;
			for (ProjectSdkVO projectSdkVO : project.values()) {
				sdkId = projectSdkVO.getSdkId();
				sb.append(threeSdkMap.get(sdkId));
				sb.append("[");
				sb.append(sdkId);
				sb.append("]、");
				// 添加项目和销量
				if (isAddProject) {
					columnValues.add("" + projectSdkVO.getProjectId());
					columnValues.add("" + projectSdkVO.getActivate());
					isAddProject = false;
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			columnValues.add(sb.toString());
			// 项目所有SDK详情
			String sdkName, sdkKey, sdkIdStr, sdkOther;  Integer key;
			for (Map.Entry<Integer, String> threeSdk : threeSdkMap.entrySet()) {
				key = threeSdk.getKey();
				if (null == project.get(key)) {
					sdkIdStr = "";
					sdkName = "";
					sdkKey = "";
					sdkOther = "";
				} else {
					sdkIdStr = "" + key;
					sdkName = threeSdk.getValue();
					sdkKey = project.get(key).getSdkKey();
					sdkOther = "0";
				}
				columnValues.add(sdkIdStr);
				columnValues.add(sdkKey);
				columnValues.add(sdkOther);
				columnValues.add(sdkOther);
			}
			super.setColumnValues(columnValues);
		}

	}

	private class ExportProjectSdkLog extends DataRowBean {
		public ExportProjectSdkLog(ProjectSdkVO projectSdkVO) {
			LinkedList<String> columnValues = new LinkedList<>();
			columnValues.add(projectSdkVO.getOperateDate());
			columnValues.add(projectSdkVO.getOperateNote());
			super.setColumnValues(columnValues);
		}
	}
}
