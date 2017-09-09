package com.kkgame.feeop.adver.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.adver.action.PromotionCustomerAction;
import com.kkgame.feeop.adver.bean.PromotionCustomerVO;
import com.kkgame.feeop.adver.service.PromotionCustomerService;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class PromotionCustomerAction extends BaseAction {
	private static Log logger = LogFactory.getLog(PromotionCustomerAction.class);	
	private PromotionCustomerVO promotionCustomerVO;
	private List<PromotionCustomerVO> promotionCustomerVOList;
	private PromotionCustomerService promotionCustomerService;
	 

	public String doList() {
		if(promotionCustomerVO == null) {
			promotionCustomerVO = new PromotionCustomerVO();
		}
		try {	
			promotionCustomerVOList = promotionCustomerService.getPromotionCustomerVOList(promotionCustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(promotionCustomerVO == null) {
			promotionCustomerVO = new PromotionCustomerVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(promotionCustomerVO == null) {
			promotionCustomerVO = new PromotionCustomerVO();
		}
		try {
			promotionCustomerService.create(promotionCustomerVO);
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
		if(promotionCustomerVO == null) {
			promotionCustomerVO = new PromotionCustomerVO();
		}
		try {
			promotionCustomerService.update(promotionCustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(promotionCustomerVO == null) {
			promotionCustomerVO = new PromotionCustomerVO();
		}
		try {
			promotionCustomerVO = promotionCustomerService.getPromotionCustomerVO(promotionCustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(promotionCustomerVO == null) {
			promotionCustomerVO = new PromotionCustomerVO();
		}
		try {
			promotionCustomerVO = promotionCustomerService.getPromotionCustomerVO(promotionCustomerVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public PromotionCustomerVO getPromotionCustomerVO() {
		return promotionCustomerVO;
	}

	public void setPromotionCustomerVO(PromotionCustomerVO promotionCustomerVO) {
		this.promotionCustomerVO = promotionCustomerVO;
	}

	public List<PromotionCustomerVO> getPromotionCustomerVOList() {
		return promotionCustomerVOList;
	}

	public void setPromotionCustomerVOList(List<PromotionCustomerVO> promotionCustomerVOList) {
		this.promotionCustomerVOList = promotionCustomerVOList;
	}

	public PromotionCustomerService getPromotionCustomerService() {
		return promotionCustomerService;
	}

	public void setPromotionCustomerService(PromotionCustomerService promotionCustomerService) {
		this.promotionCustomerService = promotionCustomerService;
	}
	 
	 

}
