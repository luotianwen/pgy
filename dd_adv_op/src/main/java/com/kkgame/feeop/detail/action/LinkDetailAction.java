package com.kkgame.feeop.detail.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.detail.bean.LinkDetailVO;
import com.kkgame.feeop.detail.service.LinkDetailService;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class LinkDetailAction extends BaseAction {

	private static Log logger = LogFactory.getLog(LinkDetailAction.class);
	
	private LinkDetailVO linkDetailVO;
	
	private List<LinkDetailVO> linkDetailVOList;

	private LinkDetailService linkDetailService;

	public String doList() {
		if(linkDetailVO == null) {
			linkDetailVO = new LinkDetailVO();
			linkDetailVO.setCdate(DateUtils.formatDate(new Date()));
		}else{
			if(linkDetailVO.getCdate()!=null
					&&!"".equals(linkDetailVO.getCdate())
					) {
				String day = linkDetailVO.getCdate().trim().replaceAll("-", "");
				linkDetailVO.setCdate(day);
				try {
					linkDetailVOList = linkDetailService.getLinkDetailVOList(linkDetailVO);
				} catch (Exception e) {
					logger.debug(e);
				}
			}
		}
		return ACTION_RESULT_LIST;
	}

	public LinkDetailVO getLinkDetailVO() {
		return linkDetailVO;
	}

	public void setLinkDetailVO(LinkDetailVO linkDetailVO) {
		this.linkDetailVO = linkDetailVO;
	}

	public List<LinkDetailVO> getLinkDetailVOList() {
		return linkDetailVOList;
	}

	public void setLinkDetailVOList(List<LinkDetailVO> linkDetailVOList) {
		this.linkDetailVOList = linkDetailVOList;
	}

	public LinkDetailService getLinkDetailService() {
		return linkDetailService;
	}

	public void setLinkDetailService(LinkDetailService linkDetailService) {
		this.linkDetailService = linkDetailService;
	}
}
