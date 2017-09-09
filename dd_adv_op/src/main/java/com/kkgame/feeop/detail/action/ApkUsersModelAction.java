package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.detail.action.ApkUsersModelAction;
import com.kkgame.feeop.detail.bean.ApkUsersModelVO;
import com.kkgame.feeop.detail.service.ApkUsersModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class ApkUsersModelAction extends BaseAction {
	private static Log logger = LogFactory.getLog(ApkUsersModelAction.class);
	private ApkUsersModelVO apkUsersModelVO;
	private List<ApkUsersModelVO> apkUsersModelVOList;
	private ApkUsersModelService apkUsersModelService;

	public String doList() {
		if (apkUsersModelVO == null) {
			apkUsersModelVO = new ApkUsersModelVO();

		}
		if (null == apkUsersModelVO.getCdate()
				|| "".equals(apkUsersModelVO.getCdate())) {
			apkUsersModelVO.setCdate(DateUtils.formatDate(new Date()));
		} else {
			try {


				String tablename = "sdktype_users_";
				String month = apkUsersModelVO.getCdate().replaceAll("-", "");
				// tablename = tablename + a + "_" + month;
				if (apkUsersModelVO.getSdkType().equals("sdk")) {
					tablename = tablename.replace("sdktype", "sdk");
				} else if (apkUsersModelVO.getSdkType().equals("sink")) {
					tablename = tablename.replace("sdktype", "apk");
				} else if (apkUsersModelVO.getSdkType().equals("lead")) {
					tablename = tablename.replace("sdktype", "guide");
				} else if (apkUsersModelVO.getSdkType().equals("silence")) {
					tablename = tablename.replace("sdktype", "slient");
				} else if (apkUsersModelVO.getSdkType().equals("websdk")) {
					tablename = tablename.replace("sdktype", "websdk");
				}
				else if (apkUsersModelVO.getSdkType().equals("offline")) {
					tablename = tablename.replace("sdktype", "offline");
				}
				if (apkUsersModelVO.getType().equals("2")) {
					int a = Math.abs(apkUsersModelVO.getImei().hashCode()) % 10;
					tablename = tablename + a;
				} else if (apkUsersModelVO.getType().equals("3")) {
					tablename = tablename + "project_" + month;
				} else
					tablename = tablename + month;
				apkUsersModelVO.setTable(tablename);
				apkUsersModelVOList = apkUsersModelService
						.getApkUsersModelVOList(apkUsersModelVO);
		}catch(Exception e){
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
	}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if (apkUsersModelVO == null) {
			apkUsersModelVO = new ApkUsersModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (apkUsersModelVO == null) {
			apkUsersModelVO = new ApkUsersModelVO();
		}
		try {
			apkUsersModelService.create(apkUsersModelVO);
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
		if (apkUsersModelVO == null) {
			apkUsersModelVO = new ApkUsersModelVO();
		}
		try {
			apkUsersModelService.update(apkUsersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (apkUsersModelVO == null) {
			apkUsersModelVO = new ApkUsersModelVO();
		}
		try {
			apkUsersModelVO = apkUsersModelService
					.getApkUsersModelVO(apkUsersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (apkUsersModelVO == null) {
			apkUsersModelVO = new ApkUsersModelVO();
		}
		try {
			apkUsersModelVO = apkUsersModelService
					.getApkUsersModelVO(apkUsersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public ApkUsersModelVO getApkUsersModelVO() {
		return apkUsersModelVO;
	}

	public void setApkUsersModelVO(ApkUsersModelVO apkUsersModelVO) {
		this.apkUsersModelVO = apkUsersModelVO;
	}

	public List<ApkUsersModelVO> getApkUsersModelVOList() {
		return apkUsersModelVOList;
	}

	public void setApkUsersModelVOList(List<ApkUsersModelVO> apkUsersModelVOList) {
		this.apkUsersModelVOList = apkUsersModelVOList;
	}

	public ApkUsersModelService getApkUsersModelService() {
		return apkUsersModelService;
	}

	public void setApkUsersModelService(
			ApkUsersModelService apkUsersModelService) {
		this.apkUsersModelService = apkUsersModelService;
	}

}
