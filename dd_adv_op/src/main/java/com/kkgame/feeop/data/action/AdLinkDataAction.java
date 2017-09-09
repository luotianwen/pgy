package com.kkgame.feeop.data.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.AdLinkDataService;
import com.kkgame.feeop.data.service.DataService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;

public class AdLinkDataAction extends BaseAction {

	private static Log logger = LogFactory.getLog(AdLinkDataAction.class);
	
	private AdLinkDataVO adLinkDataVO;
	
	private List<AdLinkDataVO> adLinkDataVOList;
	
	private AdLinkDataService adLinkDataService;
	
	private DataService dataService;
	
	
	
	private SearchVO searchVO;
	
	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private String message = "你已成功上传文件";
	
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
			adLinkDataVOList = adLinkDataService.getAdLinkDataVOList(searchVO);
		} catch (DatabaseException e) {
			e.printStackTrace();
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		try {
			StringBuffer sb = new StringBuffer();
			if (file == null || (fileFileName.indexOf(".exe") > -1)) {
				message = "文件不能为空或exe文件";
				sb.append("{msg:\"").append(message).append("\"}");
				this.printMessage(sb.toString());
				return null;
			}
			try {
				List<AdLinkDataVO> list = getFileData(file);
				if(list!=null&&list.size()>0) {
					String statDate = list.get(0).getStatDate();
					dataService.updateAdLinkDataList(list,statDate);
				}
			} catch (Exception e) {
				message = "读取数据出错";
				sb.append("{msg:\"").append(message).append("\"}");
				this.printMessage(sb.toString());
				return null;
			}
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
	
	private List<AdLinkDataVO> getFileData(File file) throws IOException {
		List<AdLinkDataVO> list = new ArrayList<AdLinkDataVO>();
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
			AdLinkDataVO data = new AdLinkDataVO();
			for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
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
							if (columnIndex == 1||columnIndex == 2) {
								value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
							}
							if (columnIndex == 3) {
								value = new DecimalFormat("0.00").format(cell
										.getNumericCellValue());
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
						hasValue = false;
						break;
					}
					if (columnIndex == 3&&"0.00".equals(value)) {
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
						data.setThirdClickTimes(Integer.parseInt(value));
					}
					if (columnIndex == 3) {
						data.setLinkIncome(Double.parseDouble(value));
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

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public AdLinkDataVO getAdLinkDataVO() {
		return adLinkDataVO;
	}

	public void setAdLinkDataVO(AdLinkDataVO adLinkDataVO) {
		this.adLinkDataVO = adLinkDataVO;
	}

	public List<AdLinkDataVO> getAdLinkDataVOList() {
		return adLinkDataVOList;
	}

	public void setAdLinkDataVOList(List<AdLinkDataVO> adLinkDataVOList) {
		this.adLinkDataVOList = adLinkDataVOList;
	}

	public AdLinkDataService getAdLinkDataService() {
		return adLinkDataService;
	}

	public void setAdLinkDataService(AdLinkDataService adLinkDataService) {
		this.adLinkDataService = adLinkDataService;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
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
