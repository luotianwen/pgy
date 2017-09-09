package com.kkgame.feeop.offline.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.offline.bean.OfflinesdkVO;
import com.kkgame.feeop.offline.service.OfflinesdkService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class OffsdkAction extends BaseAction {

	private static Log logger = LogFactory.getLog(OffsdkAction.class);
	
	
	private OfflinesdkVO offlinesdkVO;
	
	private List<OfflinesdkVO> offlinesdkVOList;
	
	private OfflinesdkService offlinesdkService;
	
	public String doList() {
		if(offlinesdkVO == null) {
			offlinesdkVO = new OfflinesdkVO();
		}
		
		try {
			offlinesdkVOList = offlinesdkService.getofflinesdkVOList(offlinesdkVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(offlinesdkVO == null) {
			offlinesdkVO = new OfflinesdkVO();
		}
		return ACTION_RESULT_CREATE;
	}


	public String doModify() {
		if(offlinesdkVO == null) {
			offlinesdkVO = new OfflinesdkVO();
		}
		try {
			offlinesdkVO = offlinesdkService.getofflinesdkVO(offlinesdkVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(offlinesdkVO == null) {
			offlinesdkVO = new OfflinesdkVO();
		}
		try {
			if(offlinesdkVO.getId()>0) {
				offlinesdkService.update(offlinesdkVO);
				this.printMessage("更新线下SDK信息成功！！！");
	
			} else {
				offlinesdkService.insert(offlinesdkVO);
				this.printMessage("创建线下SDK信息成功！！！");
	
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public OfflinesdkVO getOfflinesdkVO() {
		return offlinesdkVO;
	}

	public void setOfflinesdkVO(OfflinesdkVO offlinesdkVO) {
		this.offlinesdkVO = offlinesdkVO;
	}

	public List<OfflinesdkVO> getOfflinesdkVOList() {
		return offlinesdkVOList;
	}

	public void setOfflinesdkVOList(List<OfflinesdkVO> offlinesdkVOList) {
		this.offlinesdkVOList = offlinesdkVOList;
	}

	public OfflinesdkService getOfflinesdkService() {
		return offlinesdkService;
	}

	public void setOfflinesdkService(OfflinesdkService offlinesdkService) {
		this.offlinesdkService = offlinesdkService;
	}
}
