package com.kkgame.feeop.record.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.record.action.AdjustAction;
import com.kkgame.feeop.record.bean.AdjustVO;
import com.kkgame.feeop.record.service.AdjustService;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class AdjustAction extends BaseAction {
	private static Log logger = LogFactory.getLog(AdjustAction.class);	
	private AdjustVO adjustVO;
	private List<AdjustVO> adjustVOList;
	private AdjustService adjustService;
	 

	public String doList() {
		if(adjustVO == null) {
			adjustVO = new AdjustVO();
		}
		try {	
			adjustVOList = adjustService.getAdjustVOList(adjustVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(adjustVO == null) {
			adjustVO = new AdjustVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(adjustVO == null) {
			adjustVO = new AdjustVO();
		}
		try {
			adjustService.create(adjustVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}
	 
	
	//修改通道
	public String doUpdate() {
		if(adjustVO == null) {
			adjustVO = new AdjustVO();
		}
		try {
			adjustService.update(adjustVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(adjustVO == null) {
			adjustVO = new AdjustVO();
		}
		try {
			adjustVO = adjustService.getAdjustVO(adjustVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(adjustVO == null) {
			adjustVO = new AdjustVO();
		}
		try {
			adjustVO = adjustService.getAdjustVO(adjustVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public AdjustVO getAdjustVO() {
		return adjustVO;
	}

	public void setAdjustVO(AdjustVO adjustVO) {
		this.adjustVO = adjustVO;
	}

	public List<AdjustVO> getAdjustVOList() {
		return adjustVOList;
	}

	public void setAdjustVOList(List<AdjustVO> adjustVOList) {
		this.adjustVOList = adjustVOList;
	}

	public AdjustService getAdjustService() {
		return adjustService;
	}

	public void setAdjustService(AdjustService adjustService) {
		this.adjustService = adjustService;
	}
	 
	 

}
