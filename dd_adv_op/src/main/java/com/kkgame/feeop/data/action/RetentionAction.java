package com.kkgame.feeop.data.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.util.DateUtils;

public class RetentionAction extends BaseAction {

	private static Log logger = LogFactory.getLog(RetentionAction.class);

	private RetentionService retentionService;

	private RetentionVO retentionVO;

	private List<RetentionVO> retentionVOList;

	private SearchVO searchVO;
	
	private InputStream excelFile;

	private String downloadFileName;

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
			retentionVOList = retentionService.getRetentionVOList(searchVO);
		} catch (Exception e) {
			logger.debug(e);
		}

		return ACTION_RESULT_LIST;
	}
	
	public String doExportData() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if (searchVO == null) {
				searchVO = new SearchVO();
				searchVO.setStartDate(DateUtils.formatDate(new Date()));
				searchVO.setEndDate(DateUtils.formatDate(new Date()));
			}
			try {
				retentionVOList = retentionService.getExportRetentionVOList(searchVO);
			} catch (Exception e) {
				logger.debug(e);
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
			if(retentionVOList!=null&&retentionVOList.size()>0) {
				// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				int i=0;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "时间");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "项目ID");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "国家");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "类型");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "销量");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "活跃");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "总活跃");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "1");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "2");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "3");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "4");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "5");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "6");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "7");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "15");
				i++;
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "30");
				HSSFRow row1 = null;
				for(int j=0;j<retentionVOList.size();j++) {
					RetentionVO p = retentionVOList.get(j);
					row1 = sheet.createRow((short) (j+1));// 建立新行
					int k=0;
					this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getStatDate());
					k++;
					this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectName());
					k++;
					this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getProjectId());
					k++;
					this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getCountryName());
					k++;
					if(p.getType()==600400) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "SDK");
					} else if(p.getType()==600403) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "引导");
					} else if(p.getType()==600404) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "下沉");
					} else if(p.getType()==600405) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "线下");
					} else {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, "所有");
					}
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getUserCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getActiveCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getTotalActiveCount());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getFirstPercent());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSecondPercent());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getThirdPercent());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getFourthPercent());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getFifthPercent());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSixthPercent());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSeventhPercent());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getFiftyPercent());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getThirtyPercent());//次数
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

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
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
				+ "留存数据.xls";
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
}
