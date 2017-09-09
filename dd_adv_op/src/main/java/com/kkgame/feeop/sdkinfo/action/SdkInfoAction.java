package com.kkgame.feeop.sdkinfo.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.sdkinfo.bean.SdkInfoVO;
import com.kkgame.feeop.sdkinfo.service.SdkInfoService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.FileUtil;

public class SdkInfoAction extends BaseAction {

	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private static Log logger = LogFactory.getLog(SdkInfoAction.class);
	
	private SdkInfoVO sdkInfoVO;
	
	private SdkInfoService sdkInfoService;
	
	private List<SdkInfoVO> sdkInfoVOList;
	
	private String doQuery() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doList() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		try {
			sdkInfoVOList = sdkInfoService.getSdkInfoVOList(sdkInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		StringBuffer sb = new StringBuffer();
		try {
			
//			if(file==null) {
//				sdkInfoVO.setUrl("");
//			} else {
//				String filePath = "/home/kok/app/adsdk/kokServer/ROOT/";
//				//String filePath = "/Users/rayi/Downloads/test/";
//				StringBuffer fileName = new StringBuffer();
//				fileName.append("sdk_").append(sdkInfoVO.getSdkVersion()).append("_").append(UUID.randomUUID()).append(".jar");
//				FileUtil.mkdir(filePath);
//				FileUtils.copyFile(file, new File(filePath+fileName.toString()));
//				sdkInfoVO.setUrl("http://ws.sd4face.com/"+fileName.toString());
//			}
//			
			if(sdkInfoVO.getId()==0) {
				sdkInfoService.insert(sdkInfoVO);
				sb = new StringBuffer();
				this.printMessage("创建SDK信息成功！！！");
			} else {
				sdkInfoService.update(sdkInfoVO);
				sb = new StringBuffer();
				this.printMessage("修改SDK信息成功！！！");
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}
	
	public String doModify() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		try {
			sdkInfoVO = sdkInfoService.getSdkInfoVO(sdkInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doUpdateStatus() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		try{
			sdkInfoService.updateStatus(sdkInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return null;
	}
	
	public String doUpdate() {
		if(sdkInfoVO == null) {
			sdkInfoVO = new SdkInfoVO();
		}
		try{
			sdkInfoService.update(sdkInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
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

	public SdkInfoVO getSdkInfoVO() {
		return sdkInfoVO;
	}

	public void setSdkInfoVO(SdkInfoVO sdkInfoVO) {
		this.sdkInfoVO = sdkInfoVO;
	}

	public SdkInfoService getSdkInfoService() {
		return sdkInfoService;
	}

	public void setSdkInfoService(SdkInfoService sdkInfoService) {
		this.sdkInfoService = sdkInfoService;
	}

	public List<SdkInfoVO> getSdkInfoVOList() {
		return sdkInfoVOList;
	}

	public void setSdkInfoVOList(List<SdkInfoVO> sdkInfoVOList) {
		this.sdkInfoVOList = sdkInfoVOList;
	}
}
