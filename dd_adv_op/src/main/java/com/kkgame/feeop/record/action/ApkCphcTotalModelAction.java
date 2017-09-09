package com.kkgame.feeop.record.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kkgame.feeop.util.DatabaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.kkgame.feeop.record.action.ApkCphcTotalModelAction;
import com.kkgame.feeop.record.bean.ApkCphcTotalModelVO;
import com.kkgame.feeop.record.service.ApkCphcTotalModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class ApkCphcTotalModelAction extends BaseAction {
	private static Log logger = LogFactory.getLog(ApkCphcTotalModelAction.class);
	private static final String ACTION_RESULT_UPDATEPRICE = "updateprice";

	private ApkCphcTotalModelVO apkCphcTotalModelVO;
	private List<ApkCphcTotalModelVO> apkCphcTotalModelVOList;
	private ApkCphcTotalModelService apkCphcTotalModelService;
	private DataService dataService;
	private DataVO dataVO;
	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private String message = "你已成功上传文件";

	public String doUpdatePrice() {
		try {
			String tablename = "APK_CPHC_TOTAL_";
			String month = apkCphcTotalModelVO.getSdate().replaceAll("-","").substring(0, 6);
			apkCphcTotalModelVO.setTable(tablename + month);
			apkCphcTotalModelService.updatePrice(apkCphcTotalModelVO);
			printMessage("成功修改价格");
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
		}
		return null;
	}

	public String doModifyPrice() {
		return ACTION_RESULT_UPDATEPRICE;
	}
	
	public String doList() {
		if (apkCphcTotalModelVO == null) {
			apkCphcTotalModelVO = new ApkCphcTotalModelVO();
			apkCphcTotalModelVO.setSdate(DateUtils.formatDate(new Date()));
		}
		try {
			if (apkCphcTotalModelVO.getSdate() != null
					&& !apkCphcTotalModelVO.getSdate().equals("")) {
				String tablename = "APK_CPHC_TOTAL_";
				String month = apkCphcTotalModelVO.getSdate().replaceAll("-","").substring(0, 6);
				apkCphcTotalModelVO.setTable(tablename + month);
				apkCphcTotalModelVOList = apkCphcTotalModelService
						.getApkCphcTotalModelVOList(apkCphcTotalModelVO);
			}

		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if (apkCphcTotalModelVO == null) {
			apkCphcTotalModelVO = new ApkCphcTotalModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (apkCphcTotalModelVO == null) {
			apkCphcTotalModelVO = new ApkCphcTotalModelVO();
		}
		try {
//			String filePath = "/home/kok/app/advsdk/op/ROOT/";
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
					dataService.updateAdIncomeList(list,statDate);
				}
			} catch (Exception e) {
				message = "读取数据出错";
				sb.append("{msg:\"").append(message).append("\"}");
				this.printMessage(sb.toString());
				return null;
			}
			
//			dataVO = new DataVO();
//			dataVO.setStatDate(apkCphcTotalModelVO.getSdate());
//			dataVO.setInputTotal(apkCphcTotalModelVO.getInputtotal());
//			dataVO.setAdId(apkCphcTotalModelVO.getApkid());
//			String tablename = "APK_CPHC_TOTAL_";
//			String month = apkCphcTotalModelVO.getSdate().replaceAll("-","").substring(0, 6);
//			apkCphcTotalModelVO.setTable(tablename + month);
//			apkCphcTotalModelService.create(apkCphcTotalModelVO);
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
			DataVO data = new DataVO();
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
					if (value.trim().equals("")) {
						hasValue = false;
						break;
					}
					if (columnIndex == 2&&"0.00".equals(value)) {
						hasValue = false;
						break;
					}
					if (columnIndex == 0) {
						data.setStatDate(value);
					}
					if (columnIndex == 1) {
						data.setAdId(Integer.parseInt(value));
					}
					if (columnIndex == 2) {
						data.setInputTotal(Integer.parseInt(value));
					}
					hasValue = true;
				}
			}
			if(hasValue) {
				list.add(data);
			}
		}
		return list;
	}

	// 修改通道
	public String doUpdate() {
		if (apkCphcTotalModelVO == null) {
			apkCphcTotalModelVO = new ApkCphcTotalModelVO();
		}
		try {
			
//			String tablename = "APK_CPHC_TOTAL_";
//			String month = apkCphcTotalModelVO.getSdate().replaceAll("-","").substring(0, 6);
//			apkCphcTotalModelVO.setTable(tablename + month);
//			apkCphcTotalModelService.update(apkCphcTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (apkCphcTotalModelVO == null) {
			apkCphcTotalModelVO = new ApkCphcTotalModelVO();
		}
		try {
			String tablename = "APK_CPHC_TOTAL_";
			String month = apkCphcTotalModelVO.getSdate().replaceAll("-","").substring(0, 6);
			apkCphcTotalModelVO.setTable(tablename + month);
			apkCphcTotalModelVO = apkCphcTotalModelService
					.getApkCphcTotalModelVO(apkCphcTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (apkCphcTotalModelVO == null) {
			apkCphcTotalModelVO = new ApkCphcTotalModelVO();
		}
		try {
			String tablename = "APK_CPHC_TOTAL_";
			String month = apkCphcTotalModelVO.getSdate().replaceAll("-","").substring(0, 6);
			apkCphcTotalModelVO.setTable(tablename + month);
			apkCphcTotalModelVO = apkCphcTotalModelService
					.getApkCphcTotalModelVO(apkCphcTotalModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public ApkCphcTotalModelVO getApkCphcTotalModelVO() {
		return apkCphcTotalModelVO;
	}

	public void setApkCphcTotalModelVO(ApkCphcTotalModelVO apkCphcTotalModelVO) {
		this.apkCphcTotalModelVO = apkCphcTotalModelVO;
	}

	public List<ApkCphcTotalModelVO> getApkCphcTotalModelVOList() {
		return apkCphcTotalModelVOList;
	}

	public void setApkCphcTotalModelVOList(
			List<ApkCphcTotalModelVO> apkCphcTotalModelVOList) {
		this.apkCphcTotalModelVOList = apkCphcTotalModelVOList;
	}

	public ApkCphcTotalModelService getApkCphcTotalModelService() {
		return apkCphcTotalModelService;
	}

	public void setApkCphcTotalModelService(
			ApkCphcTotalModelService apkCphcTotalModelService) {
		this.apkCphcTotalModelService = apkCphcTotalModelService;
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
	
	
}
