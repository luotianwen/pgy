package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.detail.action.ApkActusersModelAction;
import com.kkgame.feeop.detail.bean.ApkActusersModelVO;
import com.kkgame.feeop.detail.service.ApkActusersModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class ApkActusersModelAction extends BaseAction {
	private static final String ACTION_RESULT_GUARD_LIST = "guardlist";
	private static Log logger = LogFactory.getLog(ApkActusersModelAction.class);
	private ApkActusersModelVO apkActusersModelVO;
	private List<ApkActusersModelVO> apkActusersModelVOList;
	private ApkActusersModelService apkActusersModelService;

	public String doList() {
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();


		}
		if (null == apkActusersModelVO.getCdate()
				|| "".equals(apkActusersModelVO.getCdate())) {
			apkActusersModelVO.setCdate(DateUtils.formatDate(new Date()));
		} else {
			try {

				//1.活跃，2.总活跃3，项目活跃
				String tablename = "sdktype_actusers_";
				if (apkActusersModelVO.getType().equals("2")) {
					tablename = tablename.replace("sdktype_", "");
				}
				if (apkActusersModelVO.getSdkType().equals("sdk")) {
					tablename = tablename.replace("sdktype", "sdk");
				} else if (apkActusersModelVO.getSdkType().equals("sink")) {
					tablename = tablename.replace("sdktype", "apk");
				} else if (apkActusersModelVO.getSdkType().equals("lead")) {
					tablename = tablename.replace("sdktype", "guide");
				} else if (apkActusersModelVO.getSdkType().equals("silence")) {
					tablename = tablename.replace("sdktype", "slient");
				} else if (apkActusersModelVO.getSdkType().equals("websdk")) {
					tablename = tablename.replace("sdktype", "websdk");
				}
				else if (apkActusersModelVO.getSdkType().equals("offline")) {
					tablename = tablename.replace("sdktype", "offline");
				}
				String month = apkActusersModelVO.getCdate()
						.replaceAll("-", "");
				if (apkActusersModelVO.getType().equals("1")) {
					tablename = tablename + month;
				} else if (apkActusersModelVO.getType().equals("3")) {
					tablename = tablename.replaceAll("actusers", "users_cooid");
					tablename = tablename + month;
				} else if (apkActusersModelVO.getType().equals("2")) {
					tablename = tablename + month;
				}
				apkActusersModelVO.setTable(tablename);
				apkActusersModelVOList = apkActusersModelService
						.getApkActusersModelVOList(apkActusersModelVO);
			}catch(Exception e){
				logger.debug(e);
				printMessage(PkigConstants.RESPONSE_ERROR);
				return null;
			}
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doGuardList() {
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();
			apkActusersModelVO.setCdate(DateUtils.formatDate(new Date()));

		}
		if (null == apkActusersModelVO.getCdate()
				|| "".equals(apkActusersModelVO.getCdate())) {
			apkActusersModelVO.setCdate(DateUtils.formatDate(new Date()));
		}
		try {
			if (apkActusersModelVO.getImei() != null
					&& !apkActusersModelVO.getImei().equals("")) {
				int a = Math.abs(apkActusersModelVO.getImei().hashCode()) % 10;
				//1.活跃，2.总活跃3，项目活跃
				String tablename = "guard_logs_";
				String month = apkActusersModelVO.getCdate()
				.replaceAll("-", "");
				
				tablename = tablename + month;

				apkActusersModelVO.setTable(tablename);
				apkActusersModelVOList = apkActusersModelService
						.getApkGuardModelVOList(apkActusersModelVO);
			}
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_GUARD_LIST;
	}

	public String doCreate() {
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();
		}
		try {
			apkActusersModelService.create(apkActusersModelVO);
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
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();
		}
		try {
			apkActusersModelService.update(apkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();
		}
		try {
			apkActusersModelVO = apkActusersModelService
					.getApkActusersModelVO(apkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (apkActusersModelVO == null) {
			apkActusersModelVO = new ApkActusersModelVO();
		}
		try {
			apkActusersModelVO = apkActusersModelService
					.getApkActusersModelVO(apkActusersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public ApkActusersModelVO getApkActusersModelVO() {
		return apkActusersModelVO;
	}

	public void setApkActusersModelVO(ApkActusersModelVO apkActusersModelVO) {
		this.apkActusersModelVO = apkActusersModelVO;
	}

	public List<ApkActusersModelVO> getApkActusersModelVOList() {
		return apkActusersModelVOList;
	}

	public void setApkActusersModelVOList(
			List<ApkActusersModelVO> apkActusersModelVOList) {
		this.apkActusersModelVOList = apkActusersModelVOList;
	}

	public ApkActusersModelService getApkActusersModelService() {
		return apkActusersModelService;
	}

	public void setApkActusersModelService(
			ApkActusersModelService apkActusersModelService) {
		this.apkActusersModelService = apkActusersModelService;
	}

}
