package com.kkgame.feeop.sdkinfo.action;

import java.util.List;

import com.kkgame.feeop.base.PkigConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.sdkinfo.bean.SdkProjectVO;
import com.kkgame.feeop.sdkinfo.service.SdkProjectService;
import com.kkgame.feeop.util.CalendarFormat;

public class SdkProjectAction extends BaseAction {

	private static final String ACTION_RESULT_LIST_ADV = "advlist";

	private static final String ACTION_RESULT_CREATE_ADV = "advcreate";

	private static Log logger = LogFactory.getLog(SdkProjectAction.class);
	
	private SdkProjectVO sdkProjectVO;
	
	private List<SdkProjectVO> sdkProjectVOList;
	
	private SdkProjectService sdkProjectService;
	
	public String doList() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		try{
			sdkProjectVOList = sdkProjectService.getSdkProjectVOList(sdkProjectVO);
		} catch(Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		sdkProjectVO.setExe(3201);
		sdkProjectVO.setChangeState(3201);
		sdkProjectVO.setIssyndata(3201);
		sdkProjectVO.setIsfull100(3201);
		sdkProjectVO.setFulls(50);
		sdkProjectVO.setIssale(3201);
		sdkProjectVO.setIsOpen(3200);
		sdkProjectVO.setSaleurl(PkigConstants.DDL_FORWORD);
		return ACTION_RESULT_CREATE;
	}
	

	public String doModify() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		try {
			sdkProjectVO = sdkProjectService.getSdkProjectVO(sdkProjectVO);
		} catch(Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		try {
			if(sdkProjectVO.getId()==0) {
				sdkProjectService.insert(sdkProjectVO);
				this.printMessage("创建项目配置成功");
			} else {
				sdkProjectService.update(sdkProjectVO);
				this.printMessage("修改项目配置成功");
			}
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		
		return null;
	}
	
	
	public String doAdvList() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		try{
			sdkProjectVOList = sdkProjectService.getAdvSdkProjectVOList(sdkProjectVO);
		} catch(Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST_ADV;
	}
	
	public String doAdvCreate() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		return ACTION_RESULT_CREATE_ADV;
	}
	

	public String doAdvModify() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		try {
			sdkProjectVO = sdkProjectService.getAdvSdkProjectVO(sdkProjectVO);
		} catch(Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE_ADV;
	}
	
	public String doAdvSave() {
		if(sdkProjectVO == null) {
			sdkProjectVO = new SdkProjectVO();
		}
		try {
			if(sdkProjectVO.getId()==0) {
				sdkProjectService.insertAdv(sdkProjectVO);
				this.printMessage("创建项目广告配置成功");
			} else {
				sdkProjectService.updateAdv(sdkProjectVO);
				this.printMessage("修改项目广告配置成功");
			}
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		
		return null;
	}
	

	public SdkProjectVO getSdkProjectVO() {
		return sdkProjectVO;
	}

	public void setSdkProjectVO(SdkProjectVO sdkProjectVO) {
		this.sdkProjectVO = sdkProjectVO;
	}

	public List<SdkProjectVO> getSdkProjectVOList() {
		return sdkProjectVOList;
	}

	public void setSdkProjectVOList(List<SdkProjectVO> sdkProjectVOList) {
		this.sdkProjectVOList = sdkProjectVOList;
	}

	public SdkProjectService getSdkProjectService() {
		return sdkProjectService;
	}

	public void setSdkProjectService(SdkProjectService sdkProjectService) {
		this.sdkProjectService = sdkProjectService;
	}
}
