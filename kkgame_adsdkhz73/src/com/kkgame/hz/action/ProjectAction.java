package com.kkgame.hz.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.kkgame.hz.entities.*;
import com.kkgame.hz.excel.BasicExcelData;
import com.kkgame.hz.excel.ExcelBean;
import com.kkgame.hz.excel.ExportExcel;
import com.kkgame.hz.util.CalendarFormat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.base.NameVO;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.service.ProductService;
import com.kkgame.hz.service.ProjectService;
import com.kkgame.hz.tag.TagService;
import com.kkgame.hz.util.FileUtil;
import com.kkgame.hz.util.HttpUtil;
import com.kkgame.hz.util.SystemConstant;
import sun.util.calendar.CalendarUtils;

public class ProjectAction extends BaseAction {

	private static final String ACTION_RESULT_CREATE_CONFIRM = "createConfirm";
	private static final String ACTION_RESULT_MODIFY_CONFIRM = "modifyConfirm";
	private static final String ACTION_RESULT_CONFIRM = "confirmProject";
	private static final String ACTION_RESULT_UPLOAD = "upload";
	private static final String ACTION_RESULT_DOWNLOAD = "download";
	private static final String ACTION_RESULT_TEST = "test";
	private static final String ACTION_RESULT_PRICE = "price";
	private static final String ACTION_RESULT_ADDINFO = "addInfo";
	private static final String ACTION_RESULT_MODIFY_PRICE = "pricemodify";

	private static final String ACTION_RESULT_SUBSCRIBE_QUERY = "subscribequery";
	private static final String ACTION_RESULT_SUBSCRIBE_LIST = "subscribelist";
	private static final String ACTION_RESULT_SUBSCRIBE_CREATE = "subscribecreate";
	private static final String ACTION_RESULT_SUBSCRIBE_DETAIL = "subscribedetail";
	private static final String ACTION_RESULT_SUBSCRIBE_MODIFY = "subscribemodify";

	private static final String ACTION_RESULT_STAT_QUERY = "statQuery";
	private static final String ACTION_RESULT_STAT_LIST = "statList";


	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private static Log logger = LogFactory.getLog(ProjectAction.class);
	private String message = "你已成功上传文件";
	private ProjectService projectService;
	private JavaMailSenderImpl mailSender;

	private ProjectVO projectVO;

	private List<ProjectVO> projectVOList;

	private PortalUserVO userVO ;

	private ProductService productService;

	private List<ProductVO> productVOList;
	private List<ProductVO> existProductVOList;
	private ProductVO productVO;

	private List<NameVO> bdList ;

	private List<NameVO> bhList ;

	private List<NameVO> customerList;

	private TagService tagService;

	private ProjectFileVO projectFileVO;

	private List<ProjectFileVO> projectFileVOList;
	//
	private String downloadFileName;
	private ByteArrayInputStream excelFile;
	//
	private int queryType;
	private ProjectStatVO projectStatVO;
	private List<ProjectStatVO> projectStatVOList;

	public String doStatQuery() {
		if (null == projectStatVO) {
			projectStatVO = new ProjectStatVO();
		}
		if (1 == queryType) {
			//TODO
		}
		// 页面显示的查询条件
		projectStatVO.setStartTime(CalendarFormat.getFirstDayOfMonth());
		projectStatVO.setEndTime(CalendarFormat.ymdFormat.format(new Date()));
		return ACTION_RESULT_STAT_QUERY;
	}

	public String doStatList() {
		if (null == projectStatVO) {
			projectStatVO = new ProjectStatVO();
		}
		//
		int queryType = projectStatVO.getQueryType();
		if (1 == queryType) {
			//TODO
		}
		try {
			projectStatVOList = projectService.getProjectStatVOList(projectStatVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_STAT_LIST;
	}

	public String doExportData() {
		// get request parameters
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		String userType = getLoginUserType();
		projectVO.setRoleType(userType);
		session = ServletActionContext.getRequest().getSession();
		if("BD".equals(userType)) {
			BdVO bdVO = getSessionBdVO();
			projectVO.setAgentId(bdVO.getAgentId());
			projectVO.setBdId(bdVO.getId());
		}
		if("AG".equals(userType)) {
			AgentVO agentVO = getSessionAgentVO();
			projectVO.setAgentId(agentVO.getId());
		}
		if("BH".equals(userType)) {
			BhVO bhVO = getSessionBhVO();
			projectVO.setBhId(bhVO.getId());
		}

		try {
			// get sql query data
			projectVOList = projectService.getAllProjectVOList(projectVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "查询出错！请重试！";
			this.printMessage("1");
			return null;
		}
		// create ExcelBean Object
		ExcelBean<ProjectExcelData> excel = new ExcelBean<>();
		int numType = HSSFCell.CELL_TYPE_NUMERIC;
		int strType = HSSFCell.CELL_TYPE_STRING;
		LinkedList<Integer> typeList = new LinkedList<>();
		LinkedList<String> titleList = new LinkedList<>();
		typeList.addAll(Arrays.asList(numType, strType, strType,strType, strType, strType, strType, numType, strType,strType,
				strType, strType, numType));
		titleList.addAll(Arrays.asList("编号", "名称", "产品名称","所属客户", "拓展人员", "代理商", "状态", "版本号","创建时间", "审核时间",
				"出包时间", "是否开发者", "出包时长"));
		boolean priceStatusB = projectVO.getPriceStatus() > 0;
		if (priceStatusB) {
			typeList.add(strType);
			titleList.add("价格审核");
		}
		typeList.add(strType);
		titleList.add("状态流转");
		excel.setColsType(typeList);
		excel.setColumes(titleList);

		List<ProjectExcelData> projectExcelList = new ArrayList<>();
		if (null != projectVOList && 0 < projectVOList.size()) {
			for (ProjectVO projectVO : projectVOList) {
				projectExcelList.add(new ProjectExcelData(projectVO, priceStatusB));
			}
		}
		excel.setRowsData(projectExcelList);

		downloadFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 项目总表.xls";
		try {
			downloadFileName = new String(downloadFileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("error: get downloadFileName error", e);
		}

		HSSFWorkbook workbook = ExportExcel.createWorkBook("项目总表", excel);
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

	//项目基本信息
	public String doCreate() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		userVO = getUser();
		String userType = userVO.getRoleType();
		try {
			if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
				//商务拓展人员
				bdList = tagService.getBdListByAgentId(getSessionAgentVO().getId());
	        }
			if (PkigConstants.USER_TYPE_BD.equals(userType)) {
	            //客户ID
	        	customerList = tagService.getCustomerListByBdId(getSessionBdVO().getId());
				bhList = tagService.getBhListByBdId(getSessionBdVO().getId());
	        }
		} catch(Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}

	public String doModify() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
			printMessage("1");
			return null;
		}
		userVO = getUser();
		String userType = userVO.getRoleType();
		try {
			int queryType = projectVO.getQueryType();
			projectVO = projectService.getProjectVO(projectVO);
			if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
				//商务拓展人员
				bdList = tagService.getBdListByAgentId(getSessionAgentVO().getId());
	        }
			if (PkigConstants.USER_TYPE_BD.equals(userType)) {
	            //客户ID
	        	customerList = tagService.getCustomerListByBdId(getSessionBdVO().getId());
				bhList = tagService.getBhListByBdId(getSessionBdVO().getId());
	        }
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_MODIFY;
	}

	public String doAddInfo() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
			printMessage("1");
			return null;
		}
		userVO = getUser();
		String userType = userVO.getRoleType();
		int queryType = projectVO.getQueryType();
		try {
			projectVO = projectService.getProjectVO(projectVO);
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_ADDINFO;
	}

	//产品
	public String doCreateCooperateInput() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		userVO = getUser();
		String userType = userVO.getRoleType();
		if("BD".equals(userType)) {
			BdVO bdVO = getSessionBdVO();
			projectVO.setAgentId(bdVO.getAgentId());
			projectVO.setBdId(bdVO.getId());
		}
		if("AG".equals(userType)) {
			AgentVO agentVO = getSessionAgentVO();
			projectVO.setAgentId(agentVO.getId());
		}
		String productIds = projectVO.getProductIds();
		productIds = productIds.substring(0, productIds.length()-1);
		try {
			productVOList = productService.getProductVOListByProductIds(productIds);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE_CONFIRM;
	}

	public String doUpdate() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			projectVO.setProductIds(projectVO.getProductIds().substring(0, projectVO.getProductIds().length()-1));
			projectService.update(projectVO);
			printMessage("项目P.[ "+projectVO.getId()+" ]");
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("修改信息成功!");
		return null;
	}

	public String doSaveAddInfo() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(projectVO.getOldIntro()).append("\r\n添加备注:").append(projectVO.getIntro());
			projectVO.setIntro(sb.toString());
			projectService.updateInfo(projectVO);
			printMessage(""+projectVO.getId());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return null;
	}

	public String doModifyCooperateInput() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		userVO = getUser();
		String userType = userVO.getRoleType();
		if("BD".equals(userType)) {
			BdVO bdVO = getSessionBdVO();
			projectVO.setAgentId(bdVO.getAgentId());
			projectVO.setBdId(bdVO.getId());
		}
		if("AG".equals(userType)) {
			AgentVO agentVO = getSessionAgentVO();
			projectVO.setAgentId(agentVO.getId());
		}
		String productIds = projectVO.getProductIds();
		productIds = productIds.substring(0, productIds.length()-1);
		try {
			productVOList = productService.getProductVOListByProductIds(productIds);
			existProductVOList = projectService.getProjectProductList(projectVO);
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("-1");
			return null;
		}
		return ACTION_RESULT_MODIFY_CONFIRM;
	}

	public String doQuery() {
		if(projectVO.getQueryType()==1) {
			projectVO.setStatus(0);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==2) {
			projectVO.setStatus(1);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==3) {
			projectVO.setStatus(2);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==4) {
			projectVO.setStatus(3);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==5) {
			projectVO.setStatus(4);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==6) {
			projectVO.setStatus(5);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==7) {
			projectVO.setStatus(6);
			projectVO.setPriceStatus(0);
		} else if(projectVO.getQueryType()==8) {
			projectVO.setStatus(0);
			projectVO.setPriceStatus(1);
		} else if(projectVO.getQueryType()==9) {
			projectVO.setStatus(0);
			projectVO.setPriceStatus(2);
		} else if(projectVO.getQueryType()==10) {
			projectVO.setStatus(7);
			projectVO.setPriceStatus(3);
		}
		return ACTION_RESULT_QUERY;
	}
	public String doSave() {

		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int projectId = projectService.create(projectVO);
			printMessage("项目P.[ "+projectId+" ]");
			StringBuffer sb = new StringBuffer();
			sb.append("interVO.type=4");
			sb.append("&interVO.id=").append(projectId)
			.append("&interVO.bdId=").append(projectVO.getBdId())
			.append("&interVO.agentId=").append(projectVO.getAgentId())
			.append("&interVO.customerId=").append(projectVO.getCustomerId())
			.append("&interVO.name=").append(projectVO.getName())
			.append("&interVO.productId=").append(projectVO.getProductVOList().get(0).getId())
			.append("&interVO.projectType=").append(projectVO.getType());
			String resp = HttpUtil.post(PkigConstants.URL_PROJECT, sb.toString());
			logger.info("url:"+PkigConstants.URL_PROJECT+sb.toString()+resp);
			if(projectVO.getType()==2) {
				sb = new StringBuffer();
				sb.append("coo_id=").append(projectId)
				.append("&cid=").append(projectVO.getCustomerId())
				.append("&pid=").append(projectVO.getProductVOList().get(0).getId())
				.append("&name=").append(projectVO.getGameName());
				resp = HttpUtil.post(PkigConstants.URL_DDL_PROJECT, sb.toString());
				logger.info("url:"+PkigConstants.URL_DDL_PROJECT+sb.toString()+resp);
			}

			sb = new StringBuffer();
			sb.append("coo_id=").append(projectId);
			resp = HttpUtil.post(PkigConstants.URL_SDK_PROJECT, sb.toString());
			logger.info("url:"+PkigConstants.URL_SDK_PROJECT+sb.toString()+resp);
		}  catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("新建信息成功!");
		return null;
	}

	public String doDetail() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {

			int queryType = projectVO.getQueryType();
			projectVO = projectService.getProjectVO(projectVO);
			existProductVOList = projectService.getProjectProductList(projectVO);
			projectFileVO = new ProjectFileVO();
			projectFileVO.setProjectId(projectVO.getId());
			projectFileVOList = projectService.getProjectFileVOList(projectFileVO);
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}


	public String doUpdateStatus() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {

			projectService.updateStatus(projectVO);
			if(projectVO.getStatus()==2) {
				//待技术审核
				StringBuffer sb = new StringBuffer();
				sb.append("项目ID【").append(projectVO.getId()).append("】已提交技术审核，请及时安排，谢谢，系统提示，请勿回复！");
				sendMail("项目提交技术审核", "blue@kk-games.com", sb.toString());
			} else if(projectVO.getStatus()==3) {
				//待出包通知
			} else if(projectVO.getStatus()==5) {
				//测试通过
			}

//			projectService.updatePriceStatus(projectVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("0");
		return null;
	}

	public String doRenewProject() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			projectVO.setStatus(3);
			projectService.updateStatus(projectVO);
//			projectService.updatePriceStatus(projectVO);
			StringBuffer sb = new StringBuffer();
			sb.append("项目ID【").append(projectVO.getId()).append("】需要重新出包，请及时安排，谢谢，系统提示，请勿回复！");
			sendMail("项目重新出包", "Terry@kk-games.com", sb.toString());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("0");
		return null;
	}

	public String doList() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			String userType = getLoginUserType();
			projectVO.setRoleType(userType);
			session = ServletActionContext.getRequest().getSession();
			if("BD".equals(userType)) {
				BdVO bdVO = getSessionBdVO();
				projectVO.setAgentId(bdVO.getAgentId());
				projectVO.setBdId(bdVO.getId());
			}
			if("AG".equals(userType)) {
				AgentVO agentVO = getSessionAgentVO();
				projectVO.setAgentId(agentVO.getId());
			}
			if("BH".equals(userType)) {
				BhVO bhVO = getSessionBhVO();
				projectVO.setBhId(bhVO.getId());
			}

			projectVOList = projectService.getProjectVOList(projectVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doConfirmProject() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int queryType = projectVO.getQueryType();
			projectVO = projectService.getProjectVO(projectVO);
			existProductVOList = projectService.getProjectProductList(projectVO);
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CONFIRM;
	}

	public String doTestProject() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int queryType = projectVO.getQueryType();
			projectVO = projectService.getProjectVO(projectVO);
			existProductVOList = projectService.getProjectProductList(projectVO);
			projectVO.setQueryType(queryType);

		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_TEST;
	}

	public String doConfirm() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int queryType = projectVO.getQueryType();
			projectService.updateStatus(projectVO);
			if(projectVO.getStatus()==7) {
				projectVO.setQueryType(queryType);
				return doDetail();
			} else if(projectVO.getStatus()==3) {
				projectVO = projectService.getProjectVO(projectVO);
				existProductVOList = projectService.getProjectProductList(projectVO);
				projectVO.setQueryType(queryType);
				StringBuffer sb = new StringBuffer();
				sb.append("项目ID【").append(projectVO.getId()).append("】技术审核已通过，待出包，请及时处理，谢谢，系统提示，请勿回复！");
				sendMail("项目待出包", "Terry@kk-games.com", sb.toString());
				return ACTION_RESULT_UPLOAD;
			}
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return null;
	}

	public String doTest() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int queryType = projectVO.getQueryType();
			projectService.updateStatus(projectVO);
			projectVO = projectService.getProjectVO(projectVO);
			projectVO.setQueryType(queryType);

			if(projectVO.getStatus()==5) {
				StringBuffer sb = new StringBuffer();
				sb.append("项目ID【").append(projectVO.getId()).append("】已出包并测试通过，谢谢，系统提示，请勿回复！");
				if(null!=projectVO.getMail()&&!"".equals(projectVO.getMail())) {
					sendMail("项目已出包", "tec@kokgc.com", sb.toString());
				}
			}

		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return doDetail();
	}

	public String doUpload() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int queryType = projectVO.getQueryType();
			projectVO = projectService.getProjectVO(projectVO);
			existProductVOList = projectService.getProjectProductList(projectVO);
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_UPLOAD;
	}

	public String doUploadFile() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		if(projectFileVO == null) {
			projectFileVO = new ProjectFileVO();
		}
		int projectId = projectVO.getId();
		String filePath = SystemConstant.PROJECT_PATH+projectId+"/";
		projectFileVO.setProjectId(projectId);
		StringBuffer sb = new StringBuffer();
		if(file==null||(fileFileName.indexOf(".exe")>-1)) {
			message="文件不能为空或exe文件";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		try {
			FileUtil.mkdir(filePath);
			projectFileVO.setProjectFileFileName(fileFileName);
			FileUtils.copyFile(file, new File(filePath+projectFileVO.getProjectFileFileName()));
			projectFileVO.setFilePath(projectFileVO.getProjectFileFileName());
			if(projectVO.getStatus()==3) {
				projectVO.setStatus(4);
				projectService.updateStatus(projectVO);
			}
			projectFileVO.setUploadId(getSessionUserVO().getId());
			projectFileVO.setInfo(new String(projectFileVO.getInfo().getBytes("iso8859-1"),"UTF-8"));
			projectService.insertProjectFileVO(projectFileVO);
		} catch (Exception e) {
			logger.debug(e);
			message = "对不起,文件上传失败了!!!!";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		sb.append("{msg:\"").append(message).append("\"}");
		this.printMessage(sb.toString());
		return null;
	}


	private String inputPath;

	public String doDownProject() {
		if(projectFileVO==null) {
			projectFileVO = new ProjectFileVO();
		}
		inputPath = SystemConstant.PROJECT_PATH+projectFileVO.getProjectId()+"/"+projectFileVO.getFilePath();
		if(!new File(inputPath).exists()){
			errorMsg = "文件不存在！";
			return doDetail();
		}
		try {
			projectService.updateProjectFile(projectFileVO);
			projectFileVO.setFilePath(new String(projectFileVO.getFilePath().getBytes(),"ISO8859-1"));
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_DOWNLOAD;
	}

	public String doPriceAudit() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int queryType = projectVO.getQueryType();
			projectVO = projectService.getProjectVO(projectVO);
			existProductVOList = projectService.getProjectProductList(projectVO);
			projectVO.setQueryType(queryType);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_PRICE;
	}

	public String doUpdatePriceStatus()	 {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			projectService.updatePriceStatus(projectVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return doDetail();
	}

	public String doModifyPrice() {
		if(productVO == null) {
			printMessage("-1");
			return null;
		}
		try {
			productVO = projectService.getProjectProductVO(productVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_MODIFY_PRICE;
	}

	public String doUpdatePrice() {
		if(productVO == null) {
			printMessage("-1");
			return null;
		}
		try {
			projectService.updatePrice(productVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("0");
		return null;
	}

	/*订阅项目*/


	public String doSubscribeList() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {

			projectVOList = projectService.getProjectVOSubscribeList(projectVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_SUBSCRIBE_LIST;
	}

	public String doSubscribeDetail() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {


			projectVO = projectService.getProjectVOSubscribe(projectVO);

		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_SUBSCRIBE_DETAIL;
	}
	public String doSubscribeSave() {

		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			int projectId = projectService.createSubscribe(projectVO);

		}  catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("新建信息成功!");
		return null;
	}


	public String doSubscribeQuery() {

		return ACTION_RESULT_SUBSCRIBE_QUERY;
	}
	public String doSubscribeUpdate() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {

			projectService.updateSubscribe(projectVO);
			printMessage("项目P.[ "+projectVO.getId()+" ]");
		} catch (Exception e) {
			logger.debug(e);
			printMessage("0");
			return null;
		}
		printMessage("修改信息成功!");
		return null;
	}
	public String doSubscribeModify() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
			printMessage("1");
			return null;
		}
		try {
			projectVO = projectService.getProjectVOSubscribe(projectVO);


		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_SUBSCRIBE_MODIFY;
	}

	//项目基本信息
	public String doSubscribeCreate() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}

		return ACTION_RESULT_SUBSCRIBE_CREATE;
	}


	public boolean sendMail(String title, String sendTo,String content) {
		try {
			MimeMessage mailMessage=getMailSender().createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
			messageHelper.setTo(sendTo);
			messageHelper.setFrom("hz@kokgc.com");
			messageHelper.setSubject(title);
			messageHelper.setText(content);
//			getMailSender().send(mailMessage);
		} catch (MessagingException e) {
			logger.debug(e);
			return false;
		}
		return true;

	}

	public InputStream getInputStream() throws Exception {
		return new FileInputStream(inputPath);
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public ProjectVO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}

	public List<ProjectVO> getProjectVOList() {
		return projectVOList;
	}

	public void setProjectVOList(List<ProjectVO> projectVOList) {
		this.projectVOList = projectVOList;
	}

	public PortalUserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(PortalUserVO userVO) {
		this.userVO = userVO;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<ProductVO> getProductVOList() {
		return productVOList;
	}

	public void setProductVOList(List<ProductVO> productVOList) {
		this.productVOList = productVOList;
	}

	public List<NameVO> getBdList() {
		return bdList;
	}

	public void setBdList(List<NameVO> bdList) {
		this.bdList = bdList;
	}

	public List<NameVO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<NameVO> customerList) {
		this.customerList = customerList;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public List<NameVO> getBhList() {
		return bhList;
	}

	public void setBhList(List<NameVO> bhList) {
		this.bhList = bhList;
	}

	public List<ProductVO> getExistProductVOList() {
		return existProductVOList;
	}

	public void setExistProductVOList(List<ProductVO> existProductVOList) {
		this.existProductVOList = existProductVOList;
	}

	public ProjectFileVO getProjectFileVO() {
		return projectFileVO;
	}

	public void setProjectFileVO(ProjectFileVO projectFileVO) {
		this.projectFileVO = projectFileVO;
	}

	public List<ProjectFileVO> getProjectFileVOList() {
		return projectFileVOList;
	}

	public void setProjectFileVOList(List<ProjectFileVO> projectFileVOList) {
		this.projectFileVOList = projectFileVOList;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
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

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

	public static String getActionResultCreateConfirm() {
		return ACTION_RESULT_CREATE_CONFIRM;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public ByteArrayInputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(ByteArrayInputStream excelFile) {
		this.excelFile = excelFile;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public ProjectStatVO getProjectStatVO() {
		return projectStatVO;
	}

	public void setProjectStatVO(ProjectStatVO projectStatVO) {
		this.projectStatVO = projectStatVO;
	}

	public List<ProjectStatVO> getProjectStatVOList() {
		return projectStatVOList;
	}

	public void setProjectStatVOList(List<ProjectStatVO> projectStatVOList) {
		this.projectStatVOList = projectStatVOList;
	}

	public static class ProjectExcelData extends BasicExcelData<ProjectVO, Boolean> {
		public ProjectExcelData(ProjectVO project, Boolean isShow) {
			super(project, isShow);
		}

		@Override
		public  void addAllShowData(ProjectVO project, Boolean isShow) {
			int status = project.getStatus();
			addData("" + project.getId());
			addData(project.getName());
			addData(project.getProductName());
			addData(project.getCustomerName());
			addData(project.getBdName());
			addData(project.getAgentName());
			addData(ProjectVO.parseStatus(status));
			addData("" + project.getVersion());
			addData(project.getCreateTime());
			addData(project.getConfirmTime());
			addData(project.getPackageTime());
			addData(project.getIsDevCustomer() == 0 ? "否" : "是");
			addData("" + project.getTimeStep());
			if (isShow) addData(ProjectVO.parsePriceStatus(project.getPriceStatus()));
			addData(ProjectVO.parseProjectStatus(status));
		}
	}
}
