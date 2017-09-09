package com.kkgame.feeop.data.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.data.bean.AdDataVO;
import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.AdDataService;
import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;


//http://45.61.238.80:8280/product.jsp?coo_id=11444&cid=10365&pid=21&name=locktouch
//http://45.61.238.80:8280/product.jsp?coo_id=11445&cid=10365&pid=22&name=locktouch
//http://45.61.238.80:8280/product.jsp?coo_id=11446&cid=10365&pid=23&name=locktouch


public class AdDataAction extends BaseAction {

	private static final String ACTION_RESULT_EFFECT_QUERY = "effectQuery";

	private static final String ACTION_RESULT_EFFECT_LIST = "effectList";

	private static Log logger = LogFactory.getLog(AdDataAction.class);

	private AdDataVO adDataVO;

	private SearchVO searchVO;

	private List<AdDataVO> adDataVOList;

	private AdDataService adDataService;

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
			adDataVOList = adDataService
					.getAdDataVOList(searchVO);
		} catch (DatabaseException e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doEffectQuery() {
		if (searchVO == null) {
			searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
			searchVO.setEndTime(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_EFFECT_QUERY;
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
			adDataVOList = adDataService
					.getEffectAdDataVOList(searchVO);
			DecimalFormat df = new DecimalFormat("#####.000"); 
			for(AdDataVO adDataVO:adDataVOList) {
				adDataVO.setUpPercent(Double.parseDouble(df.format(adDataVO.getReceivePercent()*adDataVO.getChangePercent()*adDataVO.getPrice())));
			}
			
			
		} catch (DatabaseException e) {
			logger.debug(e);
		}
		return ACTION_RESULT_EFFECT_LIST;
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
				adDataVOList = adDataService
				.getAdDataVOList(searchVO);
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
			if(adDataVOList!=null&&adDataVOList.size()>0) {
				
				// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				int i=0;
				if(searchVO.getRowFieldVO().getIsShowDate()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "时间");
					i++;
				}
				if(searchVO.getRowFieldVO().getIsShowAd()==1) {
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "广告");
					i++;
					this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "广告ID");
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
				this.createCell(row, i, titleStyle, HSSFCell.CELL_TYPE_STRING, "线下展示数");
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
				HSSFRow row1 = null;
				DecimalFormat df = new DecimalFormat("#0.00");
				for(int j=0;j<adDataVOList.size();j++) {	
					AdDataVO p = adDataVOList.get(j);
					row1 = sheet.createRow((short) (j+1));// 建立新行
					int k=0;
					if(searchVO.getRowFieldVO().getIsShowDate()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getStatDate());
						k++;
					}

					if(searchVO.getRowFieldVO().getIsShowAd()==1) {
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getAdName());
						k++;
						this.createCell(row1, k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getAdId());
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
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushSendCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushSendTimes());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getPushReceiveTimes());//金额
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
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSendCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSendTimes());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getReceiveTimes());//金额
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
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceSendCount());//人数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceSendTimes());//次数
					k++;
					this.createCell(row1,k, bodyStyle, HSSFCell.CELL_TYPE_STRING, p.getSilenceReceiveTimes());//金额
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
	
	
	
	public AdDataVO getAdDataVO() {
		return adDataVO;
	}

	public void setAdDataVO(AdDataVO adDataVO) {
		this.adDataVO = adDataVO;
	}

	public SearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public List<AdDataVO> getAdDataVOList() {
		return adDataVOList;
	}

	public void setAdDataVOList(List<AdDataVO> adDataVOList) {
		this.adDataVOList = adDataVOList;
	}

	public AdDataService getAdDataService() {
		return adDataService;
	}

	public void setAdDataService(AdDataService adDataService) {
		this.adDataService = adDataService;
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
				+ "广告数据.xls";
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
