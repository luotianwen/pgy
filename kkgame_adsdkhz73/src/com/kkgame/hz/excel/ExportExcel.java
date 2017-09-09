package com.kkgame.hz.excel;

import com.kkgame.hz.util.ThreeRelate;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/17
 *          Time: 13:52
 * @author: XJ
 * @since 3.0
 */
public class ExportExcel {

    public static <T extends ExcelDataImp> String exportExcel( HttpServletResponse resp, String fileName, String sheetName, ExcelBean<T> excelData) {
        String result="系统提示：Excel文件导出成功！";
        try {
            // 创建工作薄
            HSSFWorkbook workbook = createWorkBook(sheetName, excelData);
            // 定义输出流，以便打开保存对话框
//            HttpServletResponse resp = ServletActionContext.getResponse();
            OutputStream out = resp.getOutputStream();
            resp.reset();// 清空输出流
            resp.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
//            resp.setContentType("application/msexcel");// 设定输出文件头 定义输出类型
            resp.setContentType("application/vnd.ms-excel");// 设定输出文件头 定义输出类型
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            workbook.write(bao);
            bao.flush();
            out.write(bao.toByteArray());
        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e;
            e.printStackTrace();
        }
        return result;
    }

    public static <T extends ExcelDataImp> HSSFWorkbook createWorkBook (String sheetName, ExcelBean<T> excelData) {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 以下设置三种单元格样式，灵活备用
        HSSFCellStyle titleStyle = createTitleStyle(workbook);
        HSSFCellStyle bodyStyle = createBodyStyle(workbook);
        HSSFCellStyle bodyRedStyle = createBodyRedStyle(workbook);
        // 开头大标题，暂时省略
        // 当前行数 列数
        int rowNum = 0; int colNum = 0;
        // 设置查询条件
        List<ThreeRelate<Boolean, String, String>> queryList = excelData.getQueryList();
        if (null != queryList && 0 < queryList.size()) {
            HSSFRow queryRow = sheet.createRow(rowNum++);
            for (ThreeRelate<Boolean, String, String> relate : queryList) {
                if (relate.getOne()) {
                    colNum = 0;
                    queryRow = sheet.createRow(rowNum++);
                }
                createCell(queryRow, colNum++, bodyRedStyle, HSSFCell.CELL_TYPE_STRING, relate.getTwo());
                createCell(queryRow, colNum++, bodyRedStyle, HSSFCell.CELL_TYPE_STRING, relate.getThree());
            }
        }
        // 初始化列宽
        List<Integer> colsWidth = new ArrayList<>();
        // 添加列标题
        List<String> columes = excelData.getColumes();
        if (null != columes && 0 < columes.size()) {
            colNum = 0;
            HSSFRow titleRow = sheet.createRow(rowNum++);
            for (String colume : columes) {
                colsWidth.add(colume == null ? 0 : colume.getBytes().length);
                createCell(titleRow, colNum, titleStyle, HSSFCell.CELL_TYPE_STRING, colume);
                colNum++;
            }
        }
        // 正文数据
        List<T> rows = excelData.getRowsData();
        if (null != rows && 0 < rows.size()) {
            // 初始化行的列宽
            int rowSize = rows.get(0).getCurSize();
            int loseWidthSize = rowSize - colsWidth.size();
            for (int i = 0; i < loseWidthSize; i++) {
                colsWidth.add(0);
            }
            // 初始化行的列类型
            List<Integer> colsType = excelData.getColsType();
            int loseTypeSize = rowSize - colsType.size();
            for (int j = 0; j < loseTypeSize; j++) {
                colsType.add(HSSFCell.CELL_TYPE_STRING);
            }
            //
            HSSFRow dataRow = null; String nextCol; int curWidth; int nextWidth;
            for (T row : rows) {
                colNum = 0;
                dataRow = sheet.createRow(rowNum++);
                while (row.hashNext()) {
                    // 比较得到最大的列宽
                    if (null != (nextCol = row.nextColumn())) {
                        curWidth = colsWidth.get(colNum);
                        nextWidth = nextCol.getBytes().length;
                        if (curWidth < nextWidth) colsWidth.set(colNum, nextWidth);
                    }
                    createCell(dataRow, colNum, bodyStyle, colsType.get(colNum), nextCol);
                    colNum++;
                }
            }
        }
        // 自动调整各列的宽度
        for (short i = 0; i < colsWidth.size(); i++) {
            sheet.setColumnWidth(i, (short) ((colsWidth.get(i) + 2) * 256));
        }

        return  workbook;
    }

    /**
     * 设置excel cell的title样式
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        HSSFFont font2 = workbook.createFont();
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

    /**
     * 设置excel cell的正文样式
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createBodyStyle(HSSFWorkbook workbook) {
        HSSFFont boldFont = workbook.createFont();
        boldFont.setFontHeight((short) 200);
        boldFont.setFontName("宋体");
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(boldFont);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return style;

    }

    /**
     * 设置excel cell的正文样式2
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createBodyRedStyle(HSSFWorkbook workbook) {
        HSSFFont boldFont = workbook.createFont();
        boldFont.setFontHeight((short) 200);
        boldFont.setColor(HSSFColor.RED.index);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(boldFont);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return style;
    }

    /**
     * 创建Excel单元格
     * @param row
     * @param column
     * @param style
     * @param cellType
     * @param value
     */
    private static void createCell(HSSFRow row, int column, HSSFCellStyle style, int cellType, Object value) {
        HSSFCell cell = row.createCell((short) column);
        if (style != null) cell.setCellStyle(style);
        if (value == null) return;

        switch (cellType) {
            case HSSFCell.CELL_TYPE_BLANK:
                break;
            case HSSFCell.CELL_TYPE_STRING:
                cell.setCellValue(value.toString());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(value.toString()));
                break;
            default:
                break;
        }
    }
}
