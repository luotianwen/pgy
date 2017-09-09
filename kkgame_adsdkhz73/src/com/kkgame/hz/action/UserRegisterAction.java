package com.kkgame.hz.action;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.*;
import com.kkgame.hz.excel.BasicExcelData;
import com.kkgame.hz.excel.ExcelBean;
import com.kkgame.hz.excel.ExportExcel;
import com.kkgame.hz.service.UserRegisterService;
import com.kkgame.hz.util.CalendarFormat;
import com.kkgame.hz.util.SystemConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserRegisterAction extends BaseAction {

    private static final String ACTION_USERREGISTER_QUERY = "userregisterquery";
    private static Log logger = LogFactory.getLog(UserRegisterAction.class);
    private CustomerVO customerVO;
    private ProjectVO projectVO;
    private List<CustomerVO> customerList;
    private List<ProjectVO> projectList;
    private List<UserRegisterVO> userRegisterVOList;
    private UserRegisterService userRegisterService;
    private BillSearchVO billSearchVO;
    private int queryType;
    private UserRegisterVO userRegisterVO;
    private String fileFileName;
    private File file;
    private String fileFileContentType;
    private String message = "你已成功上传文件";
    private String downloadFileName;
    private InputStream excelFile;

    public String doExportData() {
        // get request parameters
        if (billSearchVO == null) {
            logger.debug("billSearchVO is null");
            billSearchVO = new BillSearchVO();
        }
        String userType = getLoginUserType();
        if ("AG".endsWith(userType)) {
            billSearchVO.setAgentId(getSessionAgentVO().getId());
        }
        if ("BD".endsWith(userType)) {
            billSearchVO.setBdId(getSessionBdVO().getId());
        }
        if ("CM".equals(userType)) {
            billSearchVO.setCustomerId(getSessionCustomerVO().getId());
        }
        if ("BH".equals(userType)) {
            billSearchVO.setBhId(getSessionBdVO().getId());
        }

        String sheetName = null;
        try {
            // get sql query data
            int queryType = billSearchVO.getQueryType();
            if (1 == queryType) {
                sheetName = "广告日激活数据";
                userRegisterVOList = userRegisterService.getUserRegisterVOListDay(billSearchVO);
            } else if (2 == queryType) {
                sheetName = "广告月激活数据";
                userRegisterVOList = userRegisterService.getUserRegisterVOListMonth(billSearchVO);
            }
        } catch (DatabaseException e) {
            logger.debug(e);
            errorMsg = "查询出错！请重试！";
            this.printMessage("1");
            return null;
        }
        // create ExcelBean Object
        ExcelBean<UserRegExcelData> excel = new ExcelBean<>();
        int numType = HSSFCell.CELL_TYPE_NUMERIC;
        int strType = HSSFCell.CELL_TYPE_STRING;
        LinkedList<Integer> typeList = new LinkedList<>();
        LinkedList<String> titleList = new LinkedList<>();
        RowFieldVO rowFieldVO = billSearchVO.getRowFieldVO();
        if (1 == rowFieldVO.getIsShowDate()) {
            typeList.addLast(strType);
            titleList.addLast("时间");
        }
        if (1 == rowFieldVO.getIsShowAdType()) {
            typeList.addLast(strType);
            titleList.addLast("代理商");
        }
        if (1 == rowFieldVO.getIsShowBd()) {
            typeList.addLast(strType);
            titleList.addLast("商务");
        }
        if (1 == rowFieldVO.getIsShowCustomer()) {
            typeList.addLast(strType);
            titleList.addLast("客户");
        }
        if (1 == rowFieldVO.getIsShowProject()) {
            typeList.addLast(strType);
            titleList.addLast("项目");
        }
        if (1 == rowFieldVO.getIsShowProduct()) {
            typeList.addLast(strType);
            titleList.addLast("产品");
        }

        typeList.addLast(strType);
        titleList.addLast("合作模式");

        typeList.addAll(Arrays.asList(numType, numType, numType, numType));
        titleList.addAll(Arrays.asList("激活用户 高", "激活用户 中", "激活用户 低", "激活用户 总"));
        excel.setColsType(typeList);
        excel.setColumes(titleList);

        List<UserRegExcelData> userList = new ArrayList<UserRegExcelData>();
        if (null != userRegisterVOList && 0 < userRegisterVOList.size()) {
            for (UserRegisterVO registerVO : userRegisterVOList) {
                userList.add(new UserRegExcelData(registerVO, rowFieldVO));
            }
        }
        excel.setRowsData(userList);

        downloadFileName = new StringBuilder().append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .append(" ").append(sheetName).append(".xls").toString();
        try {
            downloadFileName = new String(downloadFileName.getBytes("gb2312"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("error: get downloadFileName error", e);
        }

        HSSFWorkbook workbook = ExportExcel.createWorkBook(sheetName, excel);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            workbook.write(output);
            byte[] ba = output.toByteArray();
            excelFile = new ByteArrayInputStream(ba);
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException("error: write workbook into output error", e);
        }

        return "excel";
    }


    public String doGetUserRegisterQuery() {
        billSearchVO = new BillSearchVO();
        billSearchVO.setProvinceId(0);
        billSearchVO.setAgentId(0);
        billSearchVO.setQueryType(queryType);
        billSearchVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
        billSearchVO.setEndTime(CalendarFormat
                .getCurrentFormatDate("yyyy-MM-dd"));
        billSearchVO.setSearchMonth(CalendarFormat
                .getCurrentFormatDate("yyyyMM"));
        String userType = getLoginUserType();
        if ("AG".endsWith(userType)) {
            billSearchVO.setAgentId(getSessionAgentVO().getId());
        } else {
            billSearchVO.setAgentId(0);
        }
        if ("BD".endsWith(userType)) {
            billSearchVO.setBdId(getSessionBdVO().getId());
        } else {
            billSearchVO.setBdId(0);
        }
        if ("CM".endsWith(userType)) {
            billSearchVO.setCustomerId(getSessionCustomerVO().getId());
        } else {
            billSearchVO.setCustomerId(0);
        }
        if ("BH".endsWith(userType)) {
            billSearchVO.setBhId(getSessionBdVO().getId());
        } else {
            billSearchVO.setBhId(0);
        }
        return ACTION_USERREGISTER_QUERY;
    }

    public String doListMonth() {
        logger.debug("UserRegisterAction doListMonth begin");
        try {
            if (billSearchVO == null) {
                logger.debug("billSearchVO is null");
                billSearchVO = new BillSearchVO();
            }
            String userType = getLoginUserType();
            if ("AG".endsWith(userType)) {
                billSearchVO.setAgentId(getSessionAgentVO().getId());
            }
            if ("BD".endsWith(userType)) {
                billSearchVO.setBdId(getSessionBdVO().getId());
            }
            if ("CM".equals(userType)) {
                billSearchVO.setCustomerId(getSessionCustomerVO().getId());
            }
            if ("BH".equals(userType)) {
                billSearchVO.setBhId(getSessionBdVO().getId());
            }
            userRegisterVOList = userRegisterService
                    .getUserRegisterVOListMonth(billSearchVO);
        } catch (Exception e) {
            logger.debug(e);
            errorMsg = "查询出错！请重试！";
            this.printMessage("1");
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doListDay() {
        logger.debug("UserRegisterAction doListDay begin");
        try {
            if (billSearchVO == null) {
                logger.debug("billSearchVO is null");
                billSearchVO = new BillSearchVO();
            }
            String userType = getLoginUserType();
            if ("AG".endsWith(userType)) {
                billSearchVO.setAgentId(getSessionAgentVO().getId());
            }
            if ("BD".endsWith(userType)) {
                billSearchVO.setBdId(getSessionBdVO().getId());
            }
            if ("CM".equals(userType)) {
                billSearchVO.setCustomerId(getSessionCustomerVO().getId());
            }
            if ("BH".equals(userType)) {
                billSearchVO.setBhId(getSessionBdVO().getId());
            }
            userRegisterVOList = userRegisterService
                    .getUserRegisterVOListDay(billSearchVO);
        } catch (Exception e) {
            logger.debug(e);
            errorMsg = "查询出错！请重试！";
            this.printMessage("1");
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doListPercentDay() {
        logger.debug("UserRegisterAction doListDay begin");
        try {
            if (billSearchVO == null) {
                logger.debug("billSearchVO is null");
                billSearchVO = new BillSearchVO();
            }
            String userType = getLoginUserType();
            if ("AG".endsWith(userType)) {
                billSearchVO.setAgentId(getSessionAgentVO().getId());
            }
            if ("BD".endsWith(userType)) {
                billSearchVO.setBdId(getSessionBdVO().getId());
            }
            if ("CM".equals(userType)) {
                billSearchVO.setCustomerId(getSessionCustomerVO().getId());
            }
            if ("BH".equals(userType)) {
                billSearchVO.setBhId(getSessionBdVO().getId());
            }
            userRegisterVOList = userRegisterService
                    .getUserRegisterPercentVOListDay(billSearchVO);
        } catch (Exception e) {
            logger.debug(e);
            errorMsg = "查询出错！请重试！";
            this.printMessage("1");
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String getAllCustomer() {
        if (customerVO == null) {
            logger.debug("customerVO is null");
            customerVO = new CustomerVO();
        }
        customerList = new ArrayList<CustomerVO>();
        try {
            customerList = userRegisterService.getCustomerList(customerVO);
        } catch (Exception e) {
            logger.debug(e);
            errorMsg = "加载客户失败！";
        }
        return SUCCESS;
    }

    public String getAllProject() {
        if (projectVO == null) {
            logger.debug("projectVO is null");
            projectVO = new ProjectVO();
        }
        String userType = getLoginUserType();
        if ("CM".equals(userType)) {
            PortalUserVO userVO = getSessionUserVO();
            projectVO.setCustomerId(userVO.getRoleId());
        }
        projectList = new ArrayList<ProjectVO>();
        try {
            projectList = userRegisterService.getProjectList(projectVO);
        } catch (Exception e) {
            logger.debug(e);
            errorMsg = "加载客户失败！";
        }
        return SUCCESS;
    }
    public String getAllSubscribeProject() {
        if (projectVO == null) {
            logger.debug("projectVO is null");
            projectVO = new ProjectVO();
        }
        String userType = getLoginUserType();
        if ("CM".equals(userType)) {
            PortalUserVO userVO = getSessionUserVO();
            projectVO.setCustomerId(userVO.getRoleId());
        }
        projectList = new ArrayList<ProjectVO>();
        try {
            projectList = userRegisterService.getSubscribeProjectList(projectVO);
        } catch (Exception e) {
            logger.debug(e);
            errorMsg = "加载客户失败！";
        }
        return SUCCESS;
    }


    public String doData() {
        if (userRegisterVO == null) {
            printMessage("error");
            return null;
        }
        try {
            userRegisterVO.setTable("REGISTER_DATA_" + CalendarFormat.switchFormatDate(userRegisterVO.getTime(), "yyyy-MM-dd", "yyyyMM"));
            userRegisterService.insert(userRegisterVO);
        } catch (Exception e) {
            logger.info(e);
            printMessage("error");
        }
        printMessage("ok");
        return null;
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
            List<UserRegisterVO> list = getFileData(file);
            for (UserRegisterVO u : list) {
                u.setTable("REGISTER_DATA_" + CalendarFormat.switchFormatDate(u.getTime(), "yyyy-MM-dd", "yyyyMM"));
                userRegisterService.insert(u);
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

    public List<UserRegisterVO> getFileData(File file) throws IOException {
        List<UserRegisterVO> list = new ArrayList<UserRegisterVO>();
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
            UserRegisterVO data = new UserRegisterVO();
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
                    } else if (columnIndex == 2) {
                        data.setRegisterHighCount(Integer.parseInt(value));
                    } else if (columnIndex == 3) {
                        data.setRegisterMidCount(Integer.parseInt(value));
                    } else if (columnIndex == 4) {
                        data.setRegisterLowCount(Integer.parseInt(value));
                    }
                    hasValue = true;
                }
            }
            if (data.getRegisterHighCount() + data.getRegisterLowCount() + data.getRegisterMidCount() > 0) {
                list.add(data);// 将每行的字符串用一个String类型的集合保存。
            }
        }
        return list;
    }

    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    public void setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }

    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
    }

    public List<CustomerVO> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerVO> customerList) {
        this.customerList = customerList;
    }

    public List<ProjectVO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectVO> projectList) {
        this.projectList = projectList;
    }

    public UserRegisterService getUserRegisterService() {
        return userRegisterService;
    }

    public void setUserRegisterService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    public BillSearchVO getBillSearchVO() {
        return billSearchVO;
    }

    public void setBillSearchVO(BillSearchVO billSearchVO) {
        this.billSearchVO = billSearchVO;
    }

    public List<UserRegisterVO> getUserRegisterVOList() {
        return userRegisterVOList;
    }

    public void setUserRegisterVOList(List<UserRegisterVO> userRegisterVOList) {
        this.userRegisterVOList = userRegisterVOList;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
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

    public UserRegisterVO getUserRegisterVO() {
        return userRegisterVO;
    }

    public void setUserRegisterVO(UserRegisterVO userRegisterVO) {
        this.userRegisterVO = userRegisterVO;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public InputStream getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(InputStream excelFile) {
        this.excelFile = excelFile;
    }

    public static class UserRegExcelData extends BasicExcelData<UserRegisterVO, RowFieldVO> {
        public UserRegExcelData(UserRegisterVO ug, RowFieldVO isShow) {
            super(ug, isShow);
        }

        @Override
        public void addAllShowData(UserRegisterVO ug, RowFieldVO isShow) {
            if (1 == isShow.getIsShowDate()) addData(ug.getTime());
            if (1 == isShow.getIsShowAdType()) addData(ug.getAgentName());
            if (1 == isShow.getIsShowBd()) addData(ug.getBdName());
            if (1 == isShow.getIsShowCustomer()) addData(ug.getCustomerName());
            if (1 == isShow.getIsShowProject())
                addData(ug.getProjectId() <= 0 ? null : ug.getProjectName() + "[" + ug.getProjectId() + "]");
            if (1 == isShow.getIsShowProduct())
                addData(ug.getProductId() <= 0 ? null : ug.getProductName() + "[" + ug.getProductId() + "]");
            if(ug.getCityId()==1) {
                addData("联运");
            } else if  (ug.getCityId()==2) {
                addData("换量");
            }
            else if  (ug.getCityId()==3) {
                addData("自营");
            }
            else if  (ug.getCityId()==4) {
                addData("友盟结算");
            }
            else if  (ug.getCityId()==5) {
                addData("联盟产品");
            }
            else
                addData("");
            int high = ug.getRegisterHighCount();
            int middle = ug.getRegisterMidCount();
            int low = ug.getRegisterLowCount();
            addData("" + high);
            addData("" + middle);
            addData("" + low);
            addData(high + middle + low + "");
        }
    }
}
