package com.kkgame.feeop.sdkinfo.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.service.ApkInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class ApkInfoAction extends BaseAction {

	private static Log logger = LogFactory.getLog(ApkInfoAction.class);

	private ApkInfoVO apkInfoVO;

	private List<ApkInfoVO> apkInfoVOList;

	private ApkInfoService apkInfoService;

	public String doList() {
		if (apkInfoVO == null) {
			apkInfoVO = new ApkInfoVO();
		}
		try {
			apkInfoVOList = apkInfoService.getApkInfoVOList(apkInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if (apkInfoVO == null) {
			apkInfoVO = new ApkInfoVO();
		}
		return ACTION_RESULT_CREATE;
	}

	public String doModify() {
		if (apkInfoVO == null) {
			apkInfoVO = new ApkInfoVO();
		}
		try {
			apkInfoVO = apkInfoService.getApkInfoVO(apkInfoVO);
		} catch (Exception e) {
			logger.debug(e);
		}

		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if (apkInfoVO == null) {
			apkInfoVO = new ApkInfoVO();
		}

		try {
			if (apkInfoVO.getId() == 0) {
				apkInfoService.insert(apkInfoVO);
				this.printMessage("创建插件信息成功！！！");

			} else {
				apkInfoService.update(apkInfoVO);
				this.printMessage("修改插件信息成功！！！");

			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public String doDelete() {
		if (apkInfoVO == null) {
			apkInfoVO = new ApkInfoVO();
		}

		try {
			apkInfoService.delete(apkInfoVO);
			this.printMessage("删除插件信息成功！！！");
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public ApkInfoVO getApkInfoVO() {
		return apkInfoVO;
	}

	public void setApkInfoVO(ApkInfoVO apkInfoVO) {
		this.apkInfoVO = apkInfoVO;
	}

	public List<ApkInfoVO> getApkInfoVOList() {
		return apkInfoVOList;
	}

	public void setApkInfoVOList(List<ApkInfoVO> apkInfoVOList) {
		this.apkInfoVOList = apkInfoVOList;
	}

	public ApkInfoService getApkInfoService() {
		return apkInfoService;
	}

	public void setApkInfoService(ApkInfoService apkInfoService) {
		this.apkInfoService = apkInfoService;
	}
}
