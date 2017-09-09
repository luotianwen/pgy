package com.kkgame.feeop.offline.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.offline.bean.OfflinejarVO;
import com.kkgame.feeop.offline.service.OfflinejarService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class OffjarAction extends BaseAction {

	private static Log logger = LogFactory.getLog(OffjarAction.class);


	private OfflinejarVO offlinejarVO;

	private List<OfflinejarVO> offlinejarVOList;

	private OfflinejarService offlinejarService;

	public String doList() {
		if(offlinejarVO == null) {
			offlinejarVO = new OfflinejarVO();
		}

		try {
			offlinejarVOList = offlinejarService.getofflinejarVOList(offlinejarVO);
		} catch (Exception e) {
			logger.debug(e);
		}

		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if(offlinejarVO == null) {
			offlinejarVO = new OfflinejarVO();
		}
		return ACTION_RESULT_CREATE;
	}

	public String doModify() {
		if(offlinejarVO == null) {
			offlinejarVO = new OfflinejarVO();
		}
		try {
			offlinejarVO = offlinejarService.getofflinejarVO(offlinejarVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if(offlinejarVO == null) {
			offlinejarVO = new OfflinejarVO();
		}
		try {
			if(offlinejarVO.getId()>0) {
				offlinejarService.update(offlinejarVO);
				this.printMessage("更新线下JAR信息成功！！！");

			} else {
				offlinejarService.insert(offlinejarVO);
				this.printMessage("创建线下JAR信息成功！！！");

			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public OfflinejarVO getOfflinejarVO() {
		return offlinejarVO;
	}

	public void setOfflinejarVO(OfflinejarVO offlinejarVO) {
		this.offlinejarVO = offlinejarVO;
	}

	public List<OfflinejarVO> getOfflinejarVOList() {
		return offlinejarVOList;
	}

	public void setOfflinejarVOList(List<OfflinejarVO> offlinejarVOList) {
		this.offlinejarVOList = offlinejarVOList;
	}

	public OfflinejarService getOfflinejarService() {
		return offlinejarService;
	}

	public void setOfflinejarService(OfflinejarService offlinejarService) {
		this.offlinejarService = offlinejarService;
	}
}
