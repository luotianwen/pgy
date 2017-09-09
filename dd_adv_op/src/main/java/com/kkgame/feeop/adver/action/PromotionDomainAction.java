package com.kkgame.feeop.adver.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.adver.action.PromotionDomainAction;
import com.kkgame.feeop.adver.bean.PromotionDomainVO;
import com.kkgame.feeop.adver.service.PromotionDomainService;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class PromotionDomainAction extends BaseAction {
	private static Log logger = LogFactory.getLog(PromotionDomainAction.class);	
	private PromotionDomainVO promotionDomainVO;
	private List<PromotionDomainVO> promotionDomainVOList;
	private PromotionDomainService promotionDomainService;
	 

	public String doList() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {	
			promotionDomainVOList = promotionDomainService.getPromotionDomainVOList(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainService.create(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}

	//修改通道
	public String doRemove() {

		try {
			promotionDomainService.delete(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("删除成功");
		return null;
	}
	//修改通道
	public String doUpdate() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainService.update(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainVO = promotionDomainService.getPromotionDomainVO(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainVO = promotionDomainService.getPromotionDomainVO(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

    //iframe
	public String doIframeList() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainVOList = promotionDomainService.getPromotionDomainIframeVOList(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return "iframelist";
	}

	public String doIframeCreate() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		return "iframecreate";
	}

	//新增配置
	public String doIframeSave() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainService.createIframe(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}

	//修改通道
	public String doIframeRemove() {

		try {
			promotionDomainService.deleteIframe(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("删除成功");
		return null;
	}
	//修改通道
	public String doIframeUpdate() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainService.updateIframe(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doIframeModify() {
		if(promotionDomainVO == null) {
			promotionDomainVO = new PromotionDomainVO();
		}
		try {
			promotionDomainVO = promotionDomainService.getPromotionDomainIframeVO(promotionDomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}

		return "iframecreate";
	}



	public PromotionDomainVO getPromotionDomainVO() {
		return promotionDomainVO;
	}

	public void setPromotionDomainVO(PromotionDomainVO promotionDomainVO) {
		this.promotionDomainVO = promotionDomainVO;
	}

	public List<PromotionDomainVO> getPromotionDomainVOList() {
		return promotionDomainVOList;
	}

	public void setPromotionDomainVOList(List<PromotionDomainVO> promotionDomainVOList) {
		this.promotionDomainVOList = promotionDomainVOList;
	}

	public PromotionDomainService getPromotionDomainService() {
		return promotionDomainService;
	}

	public void setPromotionDomainService(PromotionDomainService promotionDomainService) {
		this.promotionDomainService = promotionDomainService;
	}
	 
	 

}
