package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.detail.action.SdkActusersModelAction;
import com.kkgame.feeop.detail.bean.SdkActusersModelVO;
import com.kkgame.feeop.detail.service.SdkActusersModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class SdkActusersModelAction extends BaseAction {
	private static Log logger = LogFactory.getLog(SdkActusersModelAction.class);
	private SdkActusersModelVO sdkActusersModelVO;
	private List<SdkActusersModelVO> sdkActusersModelVOList;
	private SdkActusersModelService sdkActusersModelService;

	public String doList() {
		if (sdkActusersModelVO == null) {
			sdkActusersModelVO = new SdkActusersModelVO();
			sdkActusersModelVO.setCdate(DateUtils.formatDate(new Date()));

		}
		if (null == sdkActusersModelVO.getCdate()
				|| "".equals(sdkActusersModelVO.getCdate())) {
			sdkActusersModelVO.setCdate(DateUtils.formatDate(new Date()));
		}
		try {
			if (sdkActusersModelVO.getImei() != null
					&& !sdkActusersModelVO.getImei().equals("")) {
				int a = Math.abs(sdkActusersModelVO.getImei().hashCode()) % 10;
				String tablename = "sdk_actusers_";
				String month = sdkActusersModelVO.getCdate()
						.replaceAll("-", "");
				tablename = tablename + a + "_" + month;
				sdkActusersModelVO.setTable(tablename);
				sdkActusersModelVOList = sdkActusersModelService
						.getSdkActusersModelVOList(sdkActusersModelVO);
			}
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if (sdkActusersModelVO == null) {
			sdkActusersModelVO = new SdkActusersModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (sdkActusersModelVO == null) {
			sdkActusersModelVO = new SdkActusersModelVO();
		}
		try {
			sdkActusersModelService.create(sdkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}

	// 修改通道
	public String doUpdate() {
		if (sdkActusersModelVO == null) {
			sdkActusersModelVO = new SdkActusersModelVO();
		}
		try {
			sdkActusersModelService.update(sdkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (sdkActusersModelVO == null) {
			sdkActusersModelVO = new SdkActusersModelVO();
		}
		try {
			sdkActusersModelVO = sdkActusersModelService
					.getSdkActusersModelVO(sdkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (sdkActusersModelVO == null) {
			sdkActusersModelVO = new SdkActusersModelVO();
		}
		try {
			sdkActusersModelVO = sdkActusersModelService
					.getSdkActusersModelVO(sdkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public SdkActusersModelVO getSdkActusersModelVO() {
		return sdkActusersModelVO;
	}

	public void setSdkActusersModelVO(SdkActusersModelVO sdkActusersModelVO) {
		this.sdkActusersModelVO = sdkActusersModelVO;
	}

	public List<SdkActusersModelVO> getSdkActusersModelVOList() {
		return sdkActusersModelVOList;
	}

	public void setSdkActusersModelVOList(
			List<SdkActusersModelVO> sdkActusersModelVOList) {
		this.sdkActusersModelVOList = sdkActusersModelVOList;
	}

	public SdkActusersModelService getSdkActusersModelService() {
		return sdkActusersModelService;
	}

	public void setSdkActusersModelService(
			SdkActusersModelService sdkActusersModelService) {
		this.sdkActusersModelService = sdkActusersModelService;
	}

}
