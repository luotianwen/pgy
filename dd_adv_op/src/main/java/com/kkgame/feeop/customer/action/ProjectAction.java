package com.kkgame.feeop.customer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.NameVO;
import com.kkgame.feeop.customer.bean.PortalUserVO;
import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.bean.ProjectFileVO;
import com.kkgame.feeop.customer.bean.ProjectVO;
import com.kkgame.feeop.customer.service.ProductService;
import com.kkgame.feeop.customer.service.ProjectService;
import com.kkgame.feeop.util.DatabaseException;

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
	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private static Log logger = LogFactory.getLog(ProjectAction.class);
	private String message = "你已成功上传文件";
	private ProjectService projectService;
	
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
	
//	private TagService tagService;
	
	private ProjectFileVO projectFileVO;
	
	private List<ProjectFileVO> projectFileVOList;
	
	public String getAllProject() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
		}
		try {
			projectVOList = projectService.getAllProject(projectVO);
		} catch (DatabaseException e) {
			return null;
		}
		return SUCCESS;
	}
	 
	public String doUpdate() {
		try {
 			projectService.update(projectVO);
 		} catch (Exception e) {
 			logger.debug(e);
 			printMessage("-1");
 			return null; 
 		}	
 		printMessage("修改信息成功!");
 		return null;
	}
	//项目基本信息
//	public String doCreate() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		userVO = getUser();
//		String userType = userVO.getRoleType();
//		try {
//			if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
//				//商务拓展人员
//				bdList = tagService.getBdListByAgentId(getSessionAgentVO().getId());
//	        }
//			if (PkigConstants.USER_TYPE_BD.equals(userType)) {
//	            //客户ID
//	        	customerList = tagService.getCustomerListByBdId(getSessionBdVO().getId());
//				bhList = tagService.getBhListByBdId(getSessionBdVO().getId());
//	        }
//		} catch(Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}    
//		return ACTION_RESULT_CREATE;
//	}
	
	public String doModify() {
		if(projectVO == null) {
			projectVO = new ProjectVO();
			printMessage("1");
			return null;
		}
		try {
			projectVO = projectService.getProjectVO(projectVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}
//	
//	public String doAddInfo() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//			printMessage("1");
//			return null;
//		}
//		userVO = getUser();
//		String userType = userVO.getRoleType();
//		int queryType = projectVO.getQueryType();
//		try {
//			projectVO = projectService.getProjectVO(projectVO);
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		return ACTION_RESULT_ADDINFO;
//	}
	
	//产品
//	public String doCreateCooperateInput() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		userVO = getUser();
//		String userType = userVO.getRoleType();		    
//		if("BD".equals(userType)) {
//			BdVO bdVO = getSessionBdVO();
//			projectVO.setAgentId(bdVO.getAgentId());
//			projectVO.setBdId(bdVO.getId());
//		}
//		if("AG".equals(userType)) {
//			AgentVO agentVO = getSessionAgentVO();
//			projectVO.setAgentId(agentVO.getId());
//		}
//		String productIds = projectVO.getProductIds();
//		productIds = productIds.substring(0, productIds.length()-1);
//		try {
//			productVOList = productService.getProductVOListByProductIds(productIds);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		return ACTION_RESULT_CREATE_CONFIRM;
//	}
	
//	public String doUpdate() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			projectVO.setProductIds(projectVO.getProductIds().substring(0, projectVO.getProductIds().length()-1));
//			projectService.update(projectVO);
//			printMessage("项目P.[ "+projectVO.getId()+" ]");
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}	
//		printMessage("修改信息成功!");
//		return null;
//	}
	
//	public String doSaveAddInfo() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			StringBuffer sb = new StringBuffer();
//			sb.append(projectVO.getOldIntro()).append("\r\n添加备注:").append(projectVO.getIntro());
//			projectVO.setIntro(sb.toString());
//			projectService.updateInfo(projectVO);
//			printMessage(""+projectVO.getId());
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return null;
//	}
	
//	public String doModifyCooperateInput() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		userVO = getUser();
//		String userType = userVO.getRoleType();		    
//		if("BD".equals(userType)) {
//			BdVO bdVO = getSessionBdVO();
//			projectVO.setAgentId(bdVO.getAgentId());
//			projectVO.setBdId(bdVO.getId());
//		}
//		if("AG".equals(userType)) {
//			AgentVO agentVO = getSessionAgentVO();
//			projectVO.setAgentId(agentVO.getId());
//		}
//		String productIds = projectVO.getProductIds();
//		productIds = productIds.substring(0, productIds.length()-1);
//		try {
//			productVOList = productService.getProductVOListByProductIds(productIds);
//			existProductVOList = projectService.getProjectProductList(projectVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			this.printMessage("-1");
//			return null;
//		}
//		return ACTION_RESULT_MODIFY_CONFIRM;
//	}
	
//	public String doQuery() {
//		if(projectVO.getQueryType()==1) {
//			projectVO.setStatus(0);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==2) {
//			projectVO.setStatus(1);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==3) {
//			projectVO.setStatus(2);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==4) {
//			projectVO.setStatus(3);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==5) {
//			projectVO.setStatus(4);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==6) {
//			projectVO.setStatus(5);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==7) {
//			projectVO.setStatus(6);
//			projectVO.setPriceStatus(0);
//		} else if(projectVO.getQueryType()==8) {
//			projectVO.setStatus(0);
//			projectVO.setPriceStatus(1);
//		} else if(projectVO.getQueryType()==9) {
//			projectVO.setStatus(0);
//			projectVO.setPriceStatus(2);
//		} else if(projectVO.getQueryType()==10) {
//			projectVO.setStatus(7);
//			projectVO.setPriceStatus(3);
//		}
//		return ACTION_RESULT_QUERY;
//	}
	
//	public String doSave() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			int projectId = projectService.create(projectVO);
//			printMessage("项目P.[ "+projectId+" ]");
//		}  catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}		
//		printMessage("新建信息成功!");
//		return null;
//	}
	
//	public String doDetail() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			
//			int queryType = projectVO.getQueryType();
//			projectVO = projectService.getProjectVO(projectVO);
//			existProductVOList = projectService.getProjectProductList(projectVO);
//			projectFileVO = new ProjectFileVO();
//			projectFileVO.setProjectId(projectVO.getId());
//			projectFileVOList = projectService.getProjectFileVOList(projectFileVO);
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return ACTION_RESULT_DETAIL;
//	}
	
	
//	public String doUpdateStatus() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}		
//		try {
//			projectService.updateStatus(projectVO);
////			projectService.updatePriceStatus(projectVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		printMessage("0");
//		return null;
//	}
	
 	public String doList() {
 		if(projectVO == null) {
 			projectVO = new ProjectVO();
 	}
 	try {
 		 
 		projectVOList = projectService.getProjectVOList(projectVO);
 		} catch (Exception e) {
 			logger.debug(e);
 			printMessage("-1");
 			return null;
 		}
 		return ACTION_RESULT_LIST;
 	}
	
//	public String doConfirmProject() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}		
//		try {
//			int queryType = projectVO.getQueryType();
//			projectVO = projectService.getProjectVO(projectVO);
//			existProductVOList = projectService.getProjectProductList(projectVO);
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return ACTION_RESULT_CONFIRM;
//	}
	
//	public String doTestProject() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}		
//		try {
//			int queryType = projectVO.getQueryType();
//			projectVO = projectService.getProjectVO(projectVO);
//			existProductVOList = projectService.getProjectProductList(projectVO);
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return ACTION_RESULT_TEST;
//	}
	
//	public String doConfirm() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			int queryType = projectVO.getQueryType();
//			projectService.updateStatus(projectVO);
//			if(projectVO.getStatus()==7) {
//				projectVO.setQueryType(queryType);
//				return doDetail();
//			} else if(projectVO.getStatus()==3) {
//				projectVO = projectService.getProjectVO(projectVO);
//				existProductVOList = projectService.getProjectProductList(projectVO);
//				projectVO.setQueryType(queryType);
//				return ACTION_RESULT_UPLOAD;
//			}
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}		
//		return null;
//	}
	
//	public String doTest() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			int queryType = projectVO.getQueryType();
//			projectService.updateStatus(projectVO);	
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}		
//		return doDetail();
//	}
	
//	public String doUpload() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			int queryType = projectVO.getQueryType();
//			projectVO = projectService.getProjectVO(projectVO);
//			existProductVOList = projectService.getProjectProductList(projectVO);
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return ACTION_RESULT_UPLOAD;
//	}

//	public String doUploadFile() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		if(projectFileVO == null) {
//			projectFileVO = new ProjectFileVO();
//		}
//		int projectId = projectVO.getId();		
//		String filePath = SystemConstant.PROJECT_PATH+projectId+"/";
//		projectFileVO.setProjectId(projectId);
//		StringBuffer sb = new StringBuffer();
//		if(file==null||(fileFileName.indexOf(".exe")>-1)) {
//			message="文件不能为空或exe文件";
//			sb.append("{msg:\"").append(message).append("\"}");
//			this.printMessage(sb.toString());
//			return null;
//		}
//		try {
//			FileUtil.mkdir(filePath);
//			projectFileVO.setProjectFileFileName(fileFileName);
//			FileUtil.copyFile(file, new File(filePath+projectFileVO.getProjectFileFileName()));
//			projectFileVO.setFilePath(projectFileVO.getProjectFileFileName());
//			if(projectVO.getStatus()==3) {
//				projectVO.setStatus(4);
//				projectService.updateStatus(projectVO);
//			}
//			projectFileVO.setUploadId(getSessionUserVO().getId());
//			projectFileVO.setInfo(new String(projectFileVO.getInfo().getBytes("iso8859-1"),"UTF-8"));
//			projectService.insertProjectFileVO(projectFileVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			message = "对不起,文件上传失败了!!!!";
//			sb.append("{msg:\"").append(message).append("\"}");
//			this.printMessage(sb.toString());
//			return null;
//		}
//		sb.append("{msg:\"").append(message).append("\"}");
//		this.printMessage(sb.toString());
//		return null;
//	}
	

	private String inputPath;
	
//	public String doDownProject() {
//		if(projectFileVO==null) {
//			projectFileVO = new ProjectFileVO();
//		}
//		inputPath = SystemConstant.PROJECT_PATH+projectFileVO.getProjectId()+"/"+projectFileVO.getFilePath();
//		if(!new File(inputPath).exists()){
//			errorMsg = "文件不存在！";
//			return doDetail();
//		}
//		try {
//			projectService.updateProjectFile(projectFileVO);
//			projectFileVO.setFilePath(new String(projectFileVO.getFilePath().getBytes(),"ISO8859-1"));
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return ACTION_RESULT_DOWNLOAD;
//	}
	
//	public String doPriceAudit() {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			int queryType = projectVO.getQueryType();
//			projectVO = projectService.getProjectVO(projectVO);
//			existProductVOList = projectService.getProjectProductList(projectVO);
//			projectVO.setQueryType(queryType);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}
//		return ACTION_RESULT_PRICE;
//	}
	
//	public String doUpdatePriceStatus()	 {
//		if(projectVO == null) {
//			projectVO = new ProjectVO();
//		}
//		try {
//			projectService.updatePriceStatus(projectVO);			
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null; 
//		}	
//		return doDetail();
//	}
	
//	public String doModifyPrice() {
//		if(productVO == null) {
//			printMessage("-1");
//			return null;
//		}
//		try {
//			productVO = projectService.getProjectProductVO(productVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		return ACTION_RESULT_MODIFY_PRICE;
//	}
	
//	public String doUpdatePrice() {
//		if(productVO == null) {
//			printMessage("-1");
//			return null;
//		}
//		try {
//			projectService.updatePrice(productVO);
//		} catch (Exception e) {
//			logger.debug(e);
//			printMessage("-1");
//			return null;
//		}
//		printMessage("0");
//		return null;
//	}
	
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

//	public TagService getTagService() {
//		return tagService;
//	}
//
//	public void setTagService(TagService tagService) {
//		this.tagService = tagService;
//	}

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
	
	
}
