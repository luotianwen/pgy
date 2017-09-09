package com.kkgame.feeop.detail.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.detail.action.AdvFeebackListClickModelAction;
import com.kkgame.feeop.detail.bean.AdvFeebackListClickModelVO;
import com.kkgame.feeop.detail.service.AdvFeebackListClickModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class AdvFeebackListClickModelAction extends BaseAction {
	private static Log logger = LogFactory
			.getLog(AdvFeebackListClickModelAction.class);
	private AdvFeebackListClickModelVO advFeebackListClickModelVO;
	private List<AdvFeebackListClickModelVO> advFeebackListClickModelVOList;
	private AdvFeebackListClickModelService advFeebackListClickModelService;

	public String doList() {
		if (advFeebackListClickModelVO == null) {
			advFeebackListClickModelVO = new AdvFeebackListClickModelVO();

		}
		if (null == advFeebackListClickModelVO.getCdate()
				|| "".equals(advFeebackListClickModelVO.getCdate())) {
			advFeebackListClickModelVO.setCdate(DateUtils
					.formatDate(new Date()));
		}

			else {
			try {
				String tablename = "adv_feeback_list_model_";
				String month = advFeebackListClickModelVO.getCdate()
						.replaceAll("-", "");

				String type = advFeebackListClickModelVO.getType();
				if (type.equals("1")) {
					tablename = tablename.replace("model", "show");
				} else if (type.equals("2")) {
					tablename = tablename.replace("model", "click");
				} else if (type.equals("3")) {
					tablename = tablename.replace("model", "download");
				} else {
					tablename = tablename.replace("model", "installed");
				}
				tablename = tablename + month;
				advFeebackListClickModelVO.setTable(tablename);

				advFeebackListClickModelVOList = advFeebackListClickModelService
						.getAdvFeebackListClickModelVOList(advFeebackListClickModelVO);

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e);
				printMessage(PkigConstants.RESPONSE_ERROR);
				return null;
			}
		}
		return ACTION_RESULT_LIST;
	}

	public AdvFeebackListClickModelVO getAdvFeebackListClickModelVO() {
		return advFeebackListClickModelVO;
	}

	public void setAdvFeebackListClickModelVO(
			AdvFeebackListClickModelVO advFeebackListClickModelVO) {
		this.advFeebackListClickModelVO = advFeebackListClickModelVO;
	}

	public List<AdvFeebackListClickModelVO> getAdvFeebackListClickModelVOList() {
		return advFeebackListClickModelVOList;
	}

	public void setAdvFeebackListClickModelVOList(
			List<AdvFeebackListClickModelVO> advFeebackListClickModelVOList) {
		this.advFeebackListClickModelVOList = advFeebackListClickModelVOList;
	}

	public AdvFeebackListClickModelService getAdvFeebackListClickModelService() {
		return advFeebackListClickModelService;
	}

	public void setAdvFeebackListClickModelService(
			AdvFeebackListClickModelService advFeebackListClickModelService) {
		this.advFeebackListClickModelService = advFeebackListClickModelService;
	}

}
