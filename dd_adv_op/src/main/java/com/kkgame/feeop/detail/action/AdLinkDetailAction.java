package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.detail.bean.AdLinkDetailVO;
import com.kkgame.feeop.detail.service.AdLinkDetailService;
import com.kkgame.feeop.util.DateUtils;

public class AdLinkDetailAction extends BaseAction {

	private static Log logger = LogFactory.getLog(AdLinkDetailAction.class);
	
	private AdLinkDetailVO adLinkDetailVO;
	
	private List<AdLinkDetailVO> adLinkDetailVOList;
	
	private AdLinkDetailService adLinkDetailService;

	public String doList() {
		if(adLinkDetailVO == null) {
			adLinkDetailVO = new AdLinkDetailVO();
			adLinkDetailVO.setCdate(DateUtils
					.formatDate(new Date()));
		}
		if(adLinkDetailVO.getCdate()!=null
		&&!"".equals(adLinkDetailVO.getCdate())
				) {
			String day = adLinkDetailVO.getCdate().trim().replaceAll("-", "");
			adLinkDetailVO.setTable(day);
			try {
			adLinkDetailVOList = adLinkDetailService.getAdLinkDetailVOList(adLinkDetailVO);
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return ACTION_RESULT_LIST;
	}
	
	public AdLinkDetailVO getAdLinkDetailVO() {
		return adLinkDetailVO;
	}

	public void setAdLinkDetailVO(AdLinkDetailVO adLinkDetailVO) {
		this.adLinkDetailVO = adLinkDetailVO;
	}

	public List<AdLinkDetailVO> getAdLinkDetailVOList() {
		return adLinkDetailVOList;
	}

	public void setAdLinkDetailVOList(List<AdLinkDetailVO> adLinkDetailVOList) {
		this.adLinkDetailVOList = adLinkDetailVOList;
	}

	public AdLinkDetailService getAdLinkDetailService() {
		return adLinkDetailService;
	}

	public void setAdLinkDetailService(AdLinkDetailService adLinkDetailService) {
		this.adLinkDetailService = adLinkDetailService;
	}
}
