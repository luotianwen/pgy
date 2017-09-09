package com.kkgame.feeop.sdkinfo.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.bean.SilentPluginInfoVO;
import com.kkgame.feeop.sdkinfo.service.ApkInfoService;
import com.kkgame.feeop.sdkinfo.service.SilentPluginInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class SilentPluginInfoAction extends BaseAction {

	private static Log logger = LogFactory.getLog(SilentPluginInfoAction.class);
	
	private SilentPluginInfoVO silentPluginInfoVO;
	
	private List<SilentPluginInfoVO> silentPluginInfoVOList;
	
	private SilentPluginInfoService silentPluginInfoService;
	
	public String doList() {
		if(silentPluginInfoVO == null) {
			silentPluginInfoVO = new SilentPluginInfoVO();
			silentPluginInfoVO.setState(3200);
		}
		try {
			silentPluginInfoVOList = silentPluginInfoService.getSilentPluginInfoVOList(silentPluginInfoVO);
		} catch(Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(silentPluginInfoVO == null) {
			silentPluginInfoVO = new SilentPluginInfoVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doModify() {
		if(silentPluginInfoVO == null) {
			silentPluginInfoVO = new SilentPluginInfoVO();
		}
		try {
			silentPluginInfoVO = silentPluginInfoService.getSilentPluginInfoVO(silentPluginInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(silentPluginInfoVO == null) {
			silentPluginInfoVO = new SilentPluginInfoVO();
		}
		
		try {
			if(silentPluginInfoVO.getId()==0) {
				silentPluginInfoService.insert(silentPluginInfoVO);
				this.printMessage("创建线下插件服务信息成功！！！");

			} else {
				silentPluginInfoService.update(silentPluginInfoVO);
				this.printMessage("修改线下插件服务信息成功！！！");

			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public SilentPluginInfoVO getSilentPluginInfoVO() {
		return silentPluginInfoVO;
	}

	public void setSilentPluginInfoVO(SilentPluginInfoVO silentPluginInfoVO) {
		this.silentPluginInfoVO = silentPluginInfoVO;
	}

	public List<SilentPluginInfoVO> getSilentPluginInfoVOList() {
		return silentPluginInfoVOList;
	}

	public void setSilentPluginInfoVOList(List<SilentPluginInfoVO> silentPluginInfoVOList) {
		this.silentPluginInfoVOList = silentPluginInfoVOList;
	}

	public SilentPluginInfoService getSilentPluginInfoService() {
		return silentPluginInfoService;
	}

	public void setSilentPluginInfoService(SilentPluginInfoService silentPluginInfoService) {
		this.silentPluginInfoService = silentPluginInfoService;
	}

}
