package com.kkgame.feeop.sdkinfo.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.sdkinfo.bean.GuideInfoVO;
import com.kkgame.feeop.sdkinfo.bean.SilentInfoVO;
import com.kkgame.feeop.sdkinfo.service.GuideInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class GuideInfoAction extends BaseAction {

	private static Log logger = LogFactory.getLog(GuideInfoAction.class);
	
	private GuideInfoVO guideInfoVO;
	
	private List<GuideInfoVO> guideInfoVOList;
	
	private GuideInfoService guideInfoService;

	public String doList() {
		if(guideInfoVO == null) {
			guideInfoVO = new GuideInfoVO();
		}
		try {
			guideInfoVOList = guideInfoService.getGuideInfoVOList(guideInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(guideInfoVO == null) {
			guideInfoVO = new GuideInfoVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doModify() {
		if(guideInfoVO == null) {
			guideInfoVO = new GuideInfoVO();
		}
		try {
			guideInfoVO = guideInfoService.getGuideInfoVO(guideInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if(guideInfoVO == null) {
			guideInfoVO = new GuideInfoVO();
		}
		try {
			if(guideInfoVO.getId()==0) {
				guideInfoService.insert(guideInfoVO);
				this.printMessage("创建引导配置信息成功！！！");

			} else {
				guideInfoService.update(guideInfoVO);
				this.printMessage("修改引导配置信息成功！！！");


			}
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		
		return null;
	}
	
	public String doDelete() {
		if(guideInfoVO == null) {
			guideInfoVO = new GuideInfoVO();
		}
		try {
			guideInfoService.delete(guideInfoVO);
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		
		this.printMessage("删除引导配置成功！");
		return null;
	}
	
	public GuideInfoVO getGuideInfoVO() {
		return guideInfoVO;
	}

	public void setGuideInfoVO(GuideInfoVO guideInfoVO) {
		this.guideInfoVO = guideInfoVO;
	}

	public List<GuideInfoVO> getGuideInfoVOList() {
		return guideInfoVOList;
	}

	public void setGuideInfoVOList(List<GuideInfoVO> guideInfoVOList) {
		this.guideInfoVOList = guideInfoVOList;
	}

	public GuideInfoService getGuideInfoService() {
		return guideInfoService;
	}

	public void setGuideInfoService(GuideInfoService guideInfoService) {
		this.guideInfoService = guideInfoService;
	}
}
