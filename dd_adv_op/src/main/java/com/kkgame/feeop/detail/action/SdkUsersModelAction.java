package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.detail.action.SdkUsersModelAction;
import com.kkgame.feeop.detail.bean.SdkUsersModelVO;
import com.kkgame.feeop.detail.service.SdkUsersModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class SdkUsersModelAction extends BaseAction {
	private static Log logger = LogFactory.getLog(SdkUsersModelAction.class);
	private SdkUsersModelVO sdkUsersModelVO;
	private List<SdkUsersModelVO> sdkUsersModelVOList;
	private SdkUsersModelService sdkUsersModelService;

	public String doList() {
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();


		}
		if (null == sdkUsersModelVO.getCdate()
				|| "".equals(sdkUsersModelVO.getCdate())) {
			sdkUsersModelVO.setCdate(DateUtils.formatDate(new Date()));
		}
		else {
			try {



					String tablename = "sdk_users_";
					String month = sdkUsersModelVO.getCdate().replaceAll("-",
							"");
					// tablename = tablename + a + "_" + month;
					if (sdkUsersModelVO.getType().equals("2")) {
						int a = Math.abs(sdkUsersModelVO.getImei().hashCode()) % 10;
						tablename = tablename + a;
					} else if (sdkUsersModelVO.getType().equals("3")) {
						tablename = tablename + "cooid_" + month;
					} else
						tablename = tablename + month;

					sdkUsersModelVO.setTable(tablename);
					sdkUsersModelVOList = sdkUsersModelService
							.getSdkUsersModelVOList(sdkUsersModelVO);



			} catch (Exception e) {
				logger.debug(e);
				printMessage(PkigConstants.RESPONSE_ERROR);
				return null;
			}
		}
		return ACTION_RESULT_LIST;
	}

	public String doProjectlist() {
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();


		}
		if (null == sdkUsersModelVO.getCdate()
				|| "".equals(sdkUsersModelVO.getCdate())) {
			sdkUsersModelVO.setCdate(DateUtils.formatDate(new Date()));
		}
		else {
			try {

				if (sdkUsersModelVO.getCdate() != null
						&& !sdkUsersModelVO.getCdate().equals("")) {
					String tablename = "sdk_users_";
					String month = sdkUsersModelVO.getCdate().replaceAll("-", "");
					tablename = tablename + "project_" + month;
					sdkUsersModelVO.setTable(tablename);
					sdkUsersModelVOList = sdkUsersModelService.getSdkUsersModelProjectList(sdkUsersModelVO);
				}


			} catch (Exception e) {
				logger.debug(e);
				printMessage(PkigConstants.RESPONSE_ERROR);
				return null;
			}
		}
		return "projectlist";
	}
	public String doProjectValidlist() {
		//projectValidlist
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();


		}
		if (null == sdkUsersModelVO.getCdate()
				|| "".equals(sdkUsersModelVO.getCdate())) {
			sdkUsersModelVO.setCdate(DateUtils.formatDate(new Date()));
		}
		else {
			try {

				if (sdkUsersModelVO.getCdate() != null
						&& !sdkUsersModelVO.getCdate().equals("")) {
					String tablename = "sdk_users_";
					String month = sdkUsersModelVO.getCdate().replaceAll("-", "");
					tablename = tablename + "project_" + month;
					sdkUsersModelVO.setTable(tablename);
					sdkUsersModelVOList = sdkUsersModelService.getSdkUsersModelProjectValidList(sdkUsersModelVO);
				}


			} catch (Exception e) {
				logger.debug(e);
				printMessage(PkigConstants.RESPONSE_ERROR);
				return null;
			}
		}
		return "projectValidlist";
	}


	public String doCreate() {
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();
		}
		try {
			sdkUsersModelService.create(sdkUsersModelVO);
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
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();
		}
		try {
			sdkUsersModelService.update(sdkUsersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();
		}
		try {
			sdkUsersModelVO = sdkUsersModelService
					.getSdkUsersModelVO(sdkUsersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (sdkUsersModelVO == null) {
			sdkUsersModelVO = new SdkUsersModelVO();
		}
		try {
			sdkUsersModelVO = sdkUsersModelService
					.getSdkUsersModelVO(sdkUsersModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public SdkUsersModelVO getSdkUsersModelVO() {
		return sdkUsersModelVO;
	}

	public void setSdkUsersModelVO(SdkUsersModelVO sdkUsersModelVO) {
		this.sdkUsersModelVO = sdkUsersModelVO;
	}

	public List<SdkUsersModelVO> getSdkUsersModelVOList() {
		return sdkUsersModelVOList;
	}

	public void setSdkUsersModelVOList(List<SdkUsersModelVO> sdkUsersModelVOList) {
		this.sdkUsersModelVOList = sdkUsersModelVOList;
	}

	public SdkUsersModelService getSdkUsersModelService() {
		return sdkUsersModelService;
	}

	public void setSdkUsersModelService(
			SdkUsersModelService sdkUsersModelService) {
		this.sdkUsersModelService = sdkUsersModelService;
	}

}
