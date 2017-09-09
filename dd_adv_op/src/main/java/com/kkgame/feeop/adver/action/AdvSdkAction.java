package com.kkgame.feeop.adver.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.sdkinfo.bean.SdkdomainVO;
import com.kkgame.feeop.sdkinfo.service.SdkdomainService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.adver.bean.AdvSdkVO;
import com.kkgame.feeop.adver.service.AdvSdkService;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.data.bean.AdIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.service.AdIncomeService;
import com.kkgame.feeop.util.DatabaseException;
import com.kkgame.feeop.util.DateUtils;
import com.kkgame.feeop.util.FileUtil;

public class AdvSdkAction extends BaseAction {

	private static final String ACTION_RESULT_UPLOAD = "upload";
	public static final String ACTION_RESULT_VIEW = "view";
	public static final String ACTION_RESULT_COUNTRY_VIEW = "country";
	private String fileFileName;
	private File file;
	private String fileFileContentType;
	private String message = "你已成功上传文件";
	
	
	private static Log logger = LogFactory.getLog(AdvSdkAction.class);
	

	private AdvSdkVO advSdkVO;
	
	private List<AdvSdkVO> advSdkVOList;
	
	private AdvSdkService advSdkService;
	
	private AdIncomeService adIncomeService;

	private String ids;
	private SdkdomainService sdkdomainService;


	public String doBatchAlter() {
		if (null == advSdkVO) {
			advSdkVO = new AdvSdkVO();
		}
		int isOpen = advSdkVO.getIsOpen();
		advSdkVO.setIsOpen(isOpen == 0 ? 3200 : 3201);

		List<Integer> idList = new ArrayList<>();
		for (String s : ids.split(",")) {
			idList.add(Integer.parseInt(s));
		}
		advSdkVO.setIdList(idList);

		try {
			advSdkService.batchAlter(advSdkVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			this.printMessage("-1");
		}
		return null;
	}
	public String doCountryList() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
			advSdkVO.setPushStatus(-1);
			advSdkVO.setIsSlient(-1);
			advSdkVO.setIsTablePlaque(-1);
			advSdkVO.setCpid(-2);
			advSdkVO.setId(-1);
		}

		try {
			SearchVO searchVO = new SearchVO();
			advSdkVOList = advSdkService.getAdvSdkVOCountryList(advSdkVO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
		return ACTION_RESULT_COUNTRY_VIEW;
	}
	public String doList() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
			advSdkVO.setStatus(3200);
			advSdkVO.setPushStatus(-1);
			advSdkVO.setIsSlient(-1);
			advSdkVO.setIsTablePlaque(-1);
		}

		try {
			SearchVO searchVO = new SearchVO();
			searchVO.setStartDate(DateUtils.formatDate(new Date()));
			searchVO.setEndDate(DateUtils.formatDate(new Date()));
			searchVO.setRowFieldString("1,21,");

			advSdkVOList = advSdkService.getAdvSdkVOList(advSdkVO);
			List<AdIncomeVO> list = adIncomeService.getAdIncomeVOList(searchVO);
			for(AdvSdkVO a:advSdkVOList) {
				for(AdIncomeVO ai:list) {
					if(a.getId()==ai.getAdId()) {
						a.setInstalledCount(ai.getInstalledCount());
						a.setSilenceInstalledCount(ai.getSilenceInstalledCount());
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
			advSdkVO.setCpname(1);
			advSdkVO.setPushStatus(3200);
			advSdkVO.setIsTablePlaque(3200);
			advSdkVO.setIsSlient(3200);
			advSdkVO.setIsDel(3201);
			advSdkVO.setIsGprsDownLoad(3200);
			advSdkVO.setClsj(1);
			advSdkVO.setStatus(3200);
			advSdkVO.setApkStatus(10800202);
			advSdkVO.setIsNotification(3201);
			advSdkVO.setIsInterface(3201);
			advSdkVO.setIsoutcptp(3200);
			advSdkVO.setIsOutDownload(3200);
			advSdkVO.setIsouticon(3200);
			advSdkVO.setCpUp(10000000);
			advSdkVO.setTsUp(10000000);
			advSdkVO.setJhl(0.30d);
			advSdkVO.setPrice(0.1d);
			advSdkVO.setTracinglinkc(1);
			advSdkVO.setAdtype(100202);
			advSdkVO.setCpConversionRate(0.2d);
			advSdkVO.setActionStatus(8200902);
			advSdkVO.setDataOrSys(8200800);
			advSdkVO.setCap(1000);
			advSdkVO.setUserType(10800100);
			advSdkVO.setOrders(1.0);
			advSdkVO.setSilenceCpm(1.0);
			String www= PkigConstants.APK_CDN;
			try {
				List<SdkdomainVO> svos=sdkdomainService.getSdkdomainVOList(new SdkdomainVO());
				if(svos!=null&&svos.size()>0){
					www=svos.get(0).getDownload()+"/upload/apk/";
				}
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
			advSdkVO.setOutwww(www);
			advSdkVO.setOuticonwww(www);
			advSdkVO.setOutcptpwww(www);
		}
		return ACTION_RESULT_CREATE;
	}

	public String doView () {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		try {
			advSdkVO = advSdkService.getAdvSdkVO(advSdkVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_VIEW;
	}

	public String doCope() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		try {
			  advSdkService.insertCopeAdvSdkVO(advSdkVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
		}
		return null;
	}
	
	public String doModify() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		try {
			advSdkVO = advSdkService.getAdvSdkVO(advSdkVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		try {
			if(advSdkVO.getId()==0) {
				advSdkService.insert(advSdkVO);
				this.printMessage("创建广告信息成功！！！");
			} else {
				advSdkService.update(advSdkVO);
				this.printMessage("修改广告信息成功！！！");
			}
		}catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}
	
	public String doUpload() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		return ACTION_RESULT_UPLOAD;
	}
	
	public String doDelete() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		try {
			advSdkService.delete(advSdkVO);
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("-1");
		}
		if(advSdkVO.getDeleted()==0) {
			this.printMessage("上线成功！");
		} else {
			this.printMessage("下线成功！");
		}
		return null;
	}
	
	public String doSaveUpload() {
		if(advSdkVO==null) {
			advSdkVO = new AdvSdkVO();
		}
		
		try {
			StringBuffer sb = new StringBuffer();
			if (file == null || (fileFileName.indexOf(".exe") > -1)) {
				message = "文件不能为空或exe文件";
				sb.append("{msg:\"").append(message).append("\"}");
				this.printMessage(sb.toString());
				return null;
			}
			String filePath = "/home/kok/app/op/ROOT/upload/attachment/";
			String suffix = fileFileName.substring(fileFileName.lastIndexOf('.'), fileFileName.length());
			//日期
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String day = df.format(ca.getTime());
			df = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
			String dayhms = df.format(ca.getTime());
			//复制文件
			FileUtil.mkdir(filePath+day);
			FileUtils.copyFile(file, new File(filePath+day+"/"+dayhms+suffix));
			advSdkVO.setUrl("http://koknew.b0.upaiyun.com/upload/attachment/"+day+"/"+dayhms+suffix);
			advSdkService.updateUrl(advSdkVO);
		}catch (Exception e) {
			logger.debug(e);
			StringBuffer sb = new StringBuffer();
			message = "上传文件失败";
			sb.append("{msg:\"").append(message).append("\"}");
			this.printMessage(sb.toString());
			return null;
		}
		StringBuffer sb = new StringBuffer();
		message = "文件上传成功";
		sb.append("{msg:\"").append(message).append("\"}");
		this.printMessage(sb.toString());
		return null;
	}

	public AdvSdkVO getAdvSdkVO() {
		return advSdkVO;
	}

	public void setAdvSdkVO(AdvSdkVO advSdkVO) {
		this.advSdkVO = advSdkVO;
	}

	public List<AdvSdkVO> getAdvSdkVOList() {
		return advSdkVOList;
	}

	public void setAdvSdkVOList(List<AdvSdkVO> advSdkVOList) {
		this.advSdkVOList = advSdkVOList;
	}

	public AdvSdkService getAdvSdkService() {
		return advSdkService;
	}

	public void setAdvSdkService(AdvSdkService advSdkService) {
		this.advSdkService = advSdkService;
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
	
	public AdIncomeService getAdIncomeService() {
		return adIncomeService;
	}

	public void setAdIncomeService(AdIncomeService adIncomeService) {
		this.adIncomeService = adIncomeService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public SdkdomainService getSdkdomainService() {
		return sdkdomainService;
	}

	public void setSdkdomainService(SdkdomainService sdkdomainService) {
		this.sdkdomainService = sdkdomainService;
	}

	public static void main(String[] args) {
		String fileFileName = "xxxx.apk";
		String suffix = fileFileName.substring(fileFileName.lastIndexOf('.'), fileFileName.length());
		System.out.println(suffix);
	}
}
