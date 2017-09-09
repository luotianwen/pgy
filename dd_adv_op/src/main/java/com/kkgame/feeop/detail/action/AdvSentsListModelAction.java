package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.detail.action.AdvSentsListModelAction;
import com.kkgame.feeop.detail.bean.AdvSentsListModelVO;
import com.kkgame.feeop.detail.service.AdvSentsListModelService;
import com.kkgame.feeop.util.DateUtils;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class AdvSentsListModelAction extends BaseAction {
	private static Log logger = LogFactory
			.getLog(AdvSentsListModelAction.class);
	private AdvSentsListModelVO advSentsListModelVO;
	private List<AdvSentsListModelVO> advSentsListModelVOList;
	private AdvSentsListModelService advSentsListModelService;

	public String doList() {
		if (advSentsListModelVO == null) {
			advSentsListModelVO = new AdvSentsListModelVO();
		}
		if (null == advSentsListModelVO.getCdate()
				|| "".equals(advSentsListModelVO.getCdate())) {
			advSentsListModelVO.setCdate(DateUtils.formatDate(new Date()));
		}
		  else {
			try {


				String tablename = "adv_sents_list_";
				String month = advSentsListModelVO.getCdate().replaceAll("-",
						"");
				tablename = tablename + month;
				advSentsListModelVO.setTable(tablename);

				advSentsListModelVOList = advSentsListModelService
						.getAdvSentsListModelVOList(advSentsListModelVO);

			} catch (Exception e) {
				logger.debug(e);
				printMessage(PkigConstants.RESPONSE_ERROR);
				return null;
			}
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		if (advSentsListModelVO == null) {
			advSentsListModelVO = new AdvSentsListModelVO();
		}
		return ACTION_RESULT_CREATE;
	}

	// 新增配置
	public String doSave() {
		if (advSentsListModelVO == null) {
			advSentsListModelVO = new AdvSentsListModelVO();
		}
		try {
			advSentsListModelService.create(advSentsListModelVO);
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
		if (advSentsListModelVO == null) {
			advSentsListModelVO = new AdvSentsListModelVO();
		}
		try {
			advSentsListModelService.update(advSentsListModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}

	public String doModify() {
		if (advSentsListModelVO == null) {
			advSentsListModelVO = new AdvSentsListModelVO();
		}
		try {
			advSentsListModelVO = advSentsListModelService
					.getAdvSentsListModelVO(advSentsListModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return ACTION_RESULT_CREATE;
	}

	public String doDetail() {
		if (advSentsListModelVO == null) {
			advSentsListModelVO = new AdvSentsListModelVO();
		}
		try {
			advSentsListModelVO = advSentsListModelService
					.getAdvSentsListModelVO(advSentsListModelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public AdvSentsListModelVO getAdvSentsListModelVO() {
		return advSentsListModelVO;
	}

	public void setAdvSentsListModelVO(AdvSentsListModelVO advSentsListModelVO) {
		this.advSentsListModelVO = advSentsListModelVO;
	}

	public List<AdvSentsListModelVO> getAdvSentsListModelVOList() {
		return advSentsListModelVOList;
	}

	public void setAdvSentsListModelVOList(
			List<AdvSentsListModelVO> advSentsListModelVOList) {
		this.advSentsListModelVOList = advSentsListModelVOList;
	}

	public AdvSentsListModelService getAdvSentsListModelService() {
		return advSentsListModelService;
	}

	public void setAdvSentsListModelService(
			AdvSentsListModelService advSentsListModelService) {
		this.advSentsListModelService = advSentsListModelService;
	}

}
