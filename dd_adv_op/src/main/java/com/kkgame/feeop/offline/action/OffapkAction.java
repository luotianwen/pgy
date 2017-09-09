package com.kkgame.feeop.offline.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.offline.bean.OfflineapkVO;
import com.kkgame.feeop.offline.service.OfflineapkService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class OffapkAction extends BaseAction {

	private static Log logger = LogFactory.getLog(OffapkAction.class);


	private OfflineapkVO offlineapkVO;

	private List<OfflineapkVO> offlineapkVOList;

	private OfflineapkService offlineapkService;

	public String doList() {
		if(offlineapkVO == null) {
			offlineapkVO = new OfflineapkVO();
		}

		try {
			offlineapkVOList = offlineapkService.getofflineapkVOList(offlineapkVO);
		} catch (Exception e) {
			logger.debug(e);
		}

		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if(offlineapkVO == null) {
			offlineapkVO = new OfflineapkVO();
		}
		return ACTION_RESULT_CREATE;
	}

	public String doModify() {
		if(offlineapkVO == null) {
			offlineapkVO = new OfflineapkVO();
		}
		try {
			offlineapkVO = offlineapkService.getofflineapkVO(offlineapkVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if(offlineapkVO == null) {
			offlineapkVO = new OfflineapkVO();
		}
		try {
			if(offlineapkVO.getId()>0) {
				offlineapkService.update(offlineapkVO);
				this.printMessage("更新线下APK信息成功！！！");

			} else {
				offlineapkService.insert(offlineapkVO);
				this.printMessage("创建线下APK信息成功！！！");

			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public OfflineapkService getOfflineapkService() {
		return offlineapkService;
	}

	public void setOfflineapkService(OfflineapkService offlineapkService) {
		this.offlineapkService = offlineapkService;
	}

	public OfflineapkVO getOfflineapkVO() {
		return offlineapkVO;
	}

	public void setOfflineapkVO(OfflineapkVO offlineapkVO) {
		this.offlineapkVO = offlineapkVO;
	}

	public List<OfflineapkVO> getOfflineapkVOList() {
		return offlineapkVOList;
	}

	public void setOfflineapkVOList(List<OfflineapkVO> offlineapkVOList) {
		this.offlineapkVOList = offlineapkVOList;
	}
}
