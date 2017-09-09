package com.kkgame.feeop.sdkinfo.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.excel.DataRowBean;
import com.kkgame.feeop.excel.DataSheetBean;
import com.kkgame.feeop.excel.ExportExcel;
import com.kkgame.feeop.record.action.ProjectHzTotalModelAction;
import com.kkgame.feeop.sdkinfo.bean.ProjectSdkVO;
import com.kkgame.feeop.sdkinfo.bean.ProjectThreeStatVO;
import com.kkgame.feeop.sdkinfo.bean.SdkProjectVO;
import com.kkgame.feeop.sdkinfo.bean.ThreeSdkVO;
import com.kkgame.feeop.sdkinfo.service.ThreeSdkService;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.TwoRelate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/1
 *          Time: 12:12
 * @author: XJ
 * @since 3.0
 */
public class ThreeSdkAction extends BaseAction {
    private static final Log logger = LogFactory.getLog(ThreeSdkAction.class);

    private static final String ACTION_RESULT_All = "all";
    //
    private static final String ACTION_RESULT_PROJECTSDK_LIST = "projectsdklist";
    private static final String ACTION_RESULT_PROJECTSDK_CREATE = "projectsdkcreate";
    private static final String ACTION_RESULT_PROJECTSDK_MODIFY = "projectsdkmodify";
    //
    private static final String ACTION_RESULT_PROJECTTHREE_STAT = "projectthreestat";
    //
    private static final String ACTION_RESULT_PROJECTTHREE_IMPORTDATA = "projectthreeimportdata";
    private static final String ACTION_RESULT_LIST_INSIDE = "insidelist";

    private static final String ACTION_RESULT_CREATE_INSIDE = "insidecreate";

    private ThreeSdkService threeSdkService;
    // 三方SDK
    private ThreeSdkVO threeSdkVO;
    private List<ThreeSdkVO> threeSdkVOList;
    // 项目SDK
    private ProjectSdkVO projectSdkVO;
    private List<ProjectSdkVO> projectSdkVOList;
    private SearchVO searchVO;
    // 项目SDK销量统计
    private ProjectThreeStatVO projectThreeStatVO;
    private List<ProjectThreeStatVO> projectThreeStatVOList;
    private int refreshRow;
    // 导入数据
    private String fileFileName;
    private File file;
    private String fileFileContentType;
    private String message = "你已成功上传文件";

    public String doBatchSave() {
        String resStr = "{msg:\"%s\"}";
        try {
            if (file == null || (fileFileName.contains(".exe"))) {
                message = "文件不能为空或exe文件";
                this.printMessage(String.format(resStr, message));
                return null;
            }
            try {
                List<ProjectSdkVO> list = getFileData(file);
                threeSdkService.batchInsertProjectSdk(list);
            } catch (Exception e) {
                message = "读取数据出错";
                this.printMessage(String.format(resStr, message));
                return null;
            }
        } catch (Exception e) {
            logger.debug(e);
            message = "读取数据出错";
            this.printMessage(String.format(resStr, message));
            return null;
        }
        message = "数据上传成功";
        this.printMessage(String.format(resStr, message));
        return null;
    }

    public String doDownloadTemplate() {
        // 获得数据
        List<ProjectSdkVO> projectSdkVOList;
        try {
            threeSdkVOList = threeSdkService.getAllThreeSdkVOList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (null == threeSdkVOList || 0 == threeSdkVOList.size()) {
            super.setErrorMsg("目前没有创建任何第三方SDK");
            return PlAIN_MESSAGE;
        }
        // 将数据写入响应流中
        DataSheetBean dataSheetBean = new DataSheetBean();
        // 1、列信息
        int numType = HSSFCell.CELL_TYPE_NUMERIC;
        int strType = HSSFCell.CELL_TYPE_STRING;
        LinkedList<TwoRelate<String, Integer>> columnList = new LinkedList<>();
        columnList.add(new TwoRelate<>("项目", numType));

        String threeSdkName;
        for (ThreeSdkVO threeSdkVO : threeSdkVOList) {
            threeSdkName = threeSdkVO.getName();
            columnList.addAll(Arrays.asList(new TwoRelate<>("是否" + threeSdkName, numType), new TwoRelate<>(threeSdkName + " key", strType),
                    new TwoRelate<>(threeSdkName + " note", numType)));
        }
        dataSheetBean.setColumes(columnList);
        // 2、行数据
        List<DataRowBean> dataRowBeanList = new ArrayList<>();
        dataRowBeanList.add(new ProjectHzTotalModelAction.ExportProjectSdk(threeSdkVOList));
        dataSheetBean.setRowsData(dataRowBeanList);
        //
        List<TwoRelate<String, DataSheetBean>> dataSheetList = new ArrayList<>(1);
        dataSheetList.add(new TwoRelate<>("导入项目SKD表", dataSheetBean));
        ExportExcel.exportExcel("导入项目SKD表.xls", dataSheetList);
        return null;
    }
    private   String getValue(Cell xssfCell) {
        if(xssfCell==null){
            return "";
        }
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue()).trim();
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {// 处理日期格式、时间格式
                SimpleDateFormat sdf = null;
                if (xssfCell.getCellStyle().getDataFormat() == HSSFDataFormat
                        .getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                Date date = xssfCell.getDateCellValue();
                return sdf.format(date);
            } else if (xssfCell.getCellStyle().getDataFormat() == 58) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = xssfCell.getDateCellValue();
                return sdf.format(date);
            }
            else{
                BigDecimal n = new BigDecimal(String.valueOf(xssfCell.getNumericCellValue()).trim());
                return  n.intValue()+"";
            }


        }
        else {
            return String.valueOf(xssfCell.getStringCellValue()).trim();
        }
    }
    private List<ProjectSdkVO> getFileData(File file) throws IOException {
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
        List<ProjectSdkVO> list = new ArrayList<>();
        String sdkId="";
        int sdknum=  (st.getRow(0).getLastCellNum()-1)/3;
        for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
            Row row = st.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < sdknum; j++) {
                sdkId=getValue(row.getCell( (j*3+1)));
                if(null!=sdkId&&!sdkId.equals("")){
                    ProjectSdkVO projectSdkVO = new ProjectSdkVO();
                    projectSdkVO.setProjectId(Integer.parseInt(getValue(row.getCell(0))));
                    projectSdkVO.setSdkId(Integer.parseInt(sdkId));
                    projectSdkVO.setSdkKey(getValue(row.getCell( (j*3+2))));
                    projectSdkVO.setNote(getValue(row.getCell( (j*3+3))));
                    list.add(projectSdkVO);
               }
            }
        }
        return list;
    }

    public String doImportData() {
        return ACTION_RESULT_PROJECTTHREE_IMPORTDATA;
    }

    public String doProjectThreeStatList() {
        if (null == searchVO) {
            searchVO = new SearchVO();
            searchVO.setStartDate(CalendarFormat.getCurrentYmdDate());
            searchVO.setEndDate(CalendarFormat.getCurrentYmdDate());
        } else {
            try {
                projectThreeStatVOList = threeSdkService.getProjectThreeStatVOList(searchVO);
            } catch (Exception e) {
                logger.debug(e);
                printMessage("-1");
                return null;
            }
        }
        return ACTION_RESULT_PROJECTTHREE_STAT;
    }

    public String doProjectSdkList() {
        if (null == projectSdkVO) {
            projectSdkVO = new ProjectSdkVO();
        }
        try {
            projectSdkVOList = threeSdkService.getProjectSdkVOList(projectSdkVO);
        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_PROJECTSDK_LIST;
    }

    public String doProjectSdkCreate() {
        projectSdkVO = new ProjectSdkVO();
        return ACTION_RESULT_PROJECTSDK_CREATE;
    }


    public String doInsidelist() {
        if(projectSdkVO == null) {
            projectSdkVO = new ProjectSdkVO();
        }
        try{
            projectSdkVOList = threeSdkService.getInsideSdkProjectVOList(projectSdkVO);
        } catch(Exception e) {
            logger.debug(e);
        }
        return ACTION_RESULT_LIST_INSIDE;
    }

    public String doInsidelistModify() {
        try {
            projectSdkVO = threeSdkService.getInsideVOById(projectSdkVO.getSdkId());
            searchVO = new SearchVO();
            searchVO.setProjectId(projectSdkVO.getProjectId());

        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_CREATE_INSIDE;
    }
    public String doInsidelistCreate() {
        if(projectSdkVO == null) {
            projectSdkVO = new ProjectSdkVO();
        }
        return ACTION_RESULT_CREATE_INSIDE;
    }
    public String doInsideDelete() {

        try {
            threeSdkService.deleteInside(projectSdkVO);
        } catch(Exception e) {
            logger.debug(e);
            this.printMessage("系统出错，请重试或联系管理员");
        }

        this.printMessage("删除成功！");
        return null;
    }
    public String doInsideSave() {
        if(projectSdkVO == null) {
            projectSdkVO = new ProjectSdkVO();
        }
        try {
            if(projectSdkVO.getSdkId()==0) {
                threeSdkService.insertInside(projectSdkVO);
                this.printMessage("创建成功");
            } else {
                threeSdkService.updateInside(projectSdkVO);
                this.printMessage("修改成功");
            }
        } catch(Exception e) {
            logger.debug(e);
            this.printMessage("系统出错，请重试或联系管理员");
        }

        return null;
    }




    public String doProjectSdkModify() {
        try {
            projectSdkVO = threeSdkService.getProjectSdkVOById(projectSdkVO.getProjectId(), projectSdkVO.getSdkId());
            searchVO = new SearchVO();
            searchVO.setProjectId(projectSdkVO.getProjectId());
            searchVO.setSdkId(projectSdkVO.getSdkId());
        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_PROJECTSDK_MODIFY;
    }

    public String doProjectSdkSave() {
        int oldProjectId = projectSdkVO.getOldProjectId();
        int oldSdkId = projectSdkVO.getOldSdkId();
        try {
            if (0 >= oldProjectId && 0 >= oldSdkId) {
                threeSdkService.insertProjectSdk(projectSdkVO);
                printMessage("成功创建项目SDK");
            } else {
                threeSdkService.updateProjectSdk(projectSdkVO);
                printMessage("成功修改项目SDK");
            }

        } catch (Exception e) {
            logger.debug(e);
            printMessage("-1");
        }
        return null;
    }

    public String doProjectSdkDelect() {
        try {
            threeSdkService.delectProjectSdk(projectSdkVO);
        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_PROJECTSDK_LIST;
    }

    public String doGetAllThreeSdk() {
        try {
            threeSdkVOList = threeSdkService.getAllThreeSdkVOList();
        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_All;
    }

    public String doList() {
        if (null == threeSdkVO) {
            threeSdkVO = new ThreeSdkVO();
        }
        try {
            threeSdkVOList = threeSdkService.getThreeSdkVOList(threeSdkVO);
        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doCreate() {
        threeSdkVO = new ThreeSdkVO();
        return ACTION_RESULT_CREATE;
    }

    public String doModify() {
        try {
            threeSdkVO = threeSdkService.getThreeSdkVOById(threeSdkVO.getId());
        } catch (DatabaseException e) {
            logger.debug(e);
        }
        return ACTION_RESULT_MODIFY;
    }

    public String doSave() {
        int id = threeSdkVO.getId();
        try {
            if (0 >= id) {
                threeSdkService.insertThreeSdk(threeSdkVO);
                printMessage("成功创建第三方SDK");
            } else {
                threeSdkService.updateThreeSdk(threeSdkVO);
                printMessage("成功修改第三方SDK");
            }
        } catch (DatabaseException e) {
            logger.debug(e);
            printMessage("-1");
        }
        return null;
    }

    public ThreeSdkService getThreeSdkService() {
        return threeSdkService;
    }
    public void setThreeSdkService(ThreeSdkService threeSdkService) {
        this.threeSdkService = threeSdkService;
    }

    public ThreeSdkVO getThreeSdkVO() {
        return threeSdkVO;
    }

    public void setThreeSdkVO(ThreeSdkVO threeSdkVO) {
        this.threeSdkVO = threeSdkVO;
    }

    public List<ThreeSdkVO> getThreeSdkVOList() {
        return threeSdkVOList;
    }

    public void setThreeSdkVOList(List<ThreeSdkVO> threeSdkVOList) {
        this.threeSdkVOList = threeSdkVOList;
    }

    public List<ProjectSdkVO> getProjectSdkVOList() {
        return projectSdkVOList;
    }

    public void setProjectSdkVOList(List<ProjectSdkVO> projectSdkVOList) {
        this.projectSdkVOList = projectSdkVOList;
    }

    public ProjectSdkVO getProjectSdkVO() {
        return projectSdkVO;
    }

    public void setProjectSdkVO(ProjectSdkVO projectSdkVO) {
        this.projectSdkVO = projectSdkVO;
    }

    public SearchVO getSearchVO() {
        return searchVO;
    }

    public void setSearchVO(SearchVO searchVO) {
        this.searchVO = searchVO;
    }

    public ProjectThreeStatVO getProjectThreeStatVO() {
        return projectThreeStatVO;
    }

    public void setProjectThreeStatVO(ProjectThreeStatVO projectThreeStatVO) {
        this.projectThreeStatVO = projectThreeStatVO;
    }

    public List<ProjectThreeStatVO> getProjectThreeStatVOList() {
        return projectThreeStatVOList;
    }

    public void setProjectThreeStatVOList(List<ProjectThreeStatVO> projectThreeStatVOList) {
        this.projectThreeStatVOList = projectThreeStatVOList;
    }

    public int getRefreshRow() {
        return refreshRow;
    }

    public void setRefreshRow(int refreshRow) {
        this.refreshRow = refreshRow;
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
