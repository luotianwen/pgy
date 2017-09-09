package com.kkgame.feeop.sdkinfo.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.sdkinfo.bean.SilentInfoVO;
import com.kkgame.feeop.sdkinfo.service.SilentInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class SilentInfoAction extends BaseAction {

	private static Log logger = LogFactory.getLog(SilentInfoAction.class);
	
	private SilentInfoVO silentInfoVO;
	
	private List<SilentInfoVO> silentInfoVOList;
	
	private SilentInfoService silentInfoService;
	
	public String doList() {
		if(silentInfoVO==null) {
			silentInfoVO = new SilentInfoVO();
		}
		try {
			silentInfoVOList = silentInfoService.getSilentInfoVOList(silentInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(silentInfoVO==null) {
			silentInfoVO = new SilentInfoVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doModify() {
		if(silentInfoVO==null) {
			silentInfoVO = new SilentInfoVO();
		}
		try {
			silentInfoVO = silentInfoService.getSilentInfoVO(silentInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if(silentInfoVO==null) {
			silentInfoVO = new SilentInfoVO();
		}
		try {
			if(silentInfoVO.getId()==0) {
				silentInfoService.insert(silentInfoVO);
				this.printMessage("创建线下配置信息成功！！！");

			} else {
				silentInfoService.update(silentInfoVO);
				this.printMessage("修改线下配置信息成功！！！");


			}
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		
		return null;
	}
	
	public String doDelete() {
		if(silentInfoVO==null) {
			silentInfoVO = new SilentInfoVO();
		}
		try {
			silentInfoService.delete(silentInfoVO);
			this.printMessage("删除线下配置信息成功！！！");
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}
	
	public SilentInfoVO getSilentInfoVO() {
		return silentInfoVO;
	}

	public void setSilentInfoVO(SilentInfoVO silentInfoVO) {
		this.silentInfoVO = silentInfoVO;
	}

	public List<SilentInfoVO> getSilentInfoVOList() {
		return silentInfoVOList;
	}

	public void setSilentInfoVOList(List<SilentInfoVO> silentInfoVOList) {
		this.silentInfoVOList = silentInfoVOList;
	}

	public SilentInfoService getSilentInfoService() {
		return silentInfoService;
	}

	public void setSilentInfoService(SilentInfoService silentInfoService) {
		this.silentInfoService = silentInfoService;
	}
}
