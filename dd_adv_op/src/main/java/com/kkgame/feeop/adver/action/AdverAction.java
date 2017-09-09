package com.kkgame.feeop.adver.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.adver.bean.AdverVO;
import com.kkgame.feeop.adver.service.AdverService;
import com.kkgame.feeop.base.BaseAction;

public class AdverAction extends BaseAction {

	private static Log logger = LogFactory.getLog(AdverAction.class);
	
	private AdverVO adverVO;
	
	private List<AdverVO> adverVOList;
	
	private AdverService adverService;
	
	public String doList() {
		if(adverVO == null) {
			adverVO = new AdverVO();
		}
		try {
			adverVOList = adverService.getAdverVOList(adverVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(adverVO == null) {
			adverVO = new AdverVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(adverVO == null) {
			adverVO = new AdverVO();
		}
		try {
			adverVO.setUrl("");
			adverVO.setPostValue("");
			adverVO.setImplementClass("");
			adverVO.setUrl("");
			if(adverVO.getId()==0) {
				
				adverService.insert(adverVO);
				this.printMessage("创建广告主信息成功！！！");
			} else {
				adverService.update(adverVO);
				this.printMessage("修改广告主信息成功！！！");
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}
	
	public String doModify() {
		if(adverVO == null) {
			adverVO = new AdverVO();
		}
		try {
			adverVO = adverService.getAdverVO(adverVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doUpdateStatus() {
		if(adverVO == null) {
			adverVO = new AdverVO();
		}
		try {
			adverService.updateStatus(adverVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return null;
	}

	public AdverVO getAdverVO() {
		return adverVO;
	}

	public void setAdverVO(AdverVO adverVO) {
		this.adverVO = adverVO;
	}

	public List<AdverVO> getAdverVOList() {
		return adverVOList;
	}

	public void setAdverVOList(List<AdverVO> adverVOList) {
		this.adverVOList = adverVOList;
	}

	public AdverService getAdverService() {
		return adverService;
	}

	public void setAdverService(AdverService adverService) {
		this.adverService = adverService;
	}
}
