package com.kkgame.feeop.detail.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.detail.bean.IframeDetailVO;
import com.kkgame.feeop.detail.service.IframeDetailService;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;

public class IframeDetailAction extends BaseAction {

	private static Log logger = LogFactory.getLog(IframeDetailAction.class);
	
	private IframeDetailVO iframeDetailVO;
	
	private List<IframeDetailVO> iframeDetailVOList;

	private IframeDetailService iframeDetailService;

	public String doList() {
		if(iframeDetailVO == null) {
			iframeDetailVO = new IframeDetailVO();
			iframeDetailVO.setCdate(DateUtils.formatDate(new Date()));
		}else{
			if(iframeDetailVO.getCdate()!=null
					&&!"".equals(iframeDetailVO.getCdate())
					) {
				String day = iframeDetailVO.getCdate().trim().replaceAll("-", "");
				iframeDetailVO.setCdate(day);
				try {
					iframeDetailVOList = iframeDetailService.getIframeDetailVOList(iframeDetailVO);
				} catch (Exception e) {
					logger.debug(e);
				}
			}
		}
		return ACTION_RESULT_LIST;
	}

	public IframeDetailVO getIframeDetailVO() {
		return iframeDetailVO;
	}

	public void setIframeDetailVO(IframeDetailVO iframeDetailVO) {
		this.iframeDetailVO = iframeDetailVO;
	}

	public List<IframeDetailVO> getIframeDetailVOList() {
		return iframeDetailVOList;
	}

	public void setIframeDetailVOList(List<IframeDetailVO> iframeDetailVOList) {
		this.iframeDetailVOList = iframeDetailVOList;
	}

	public IframeDetailService getIframeDetailService() {
		return iframeDetailService;
	}

	public void setIframeDetailService(IframeDetailService iframeDetailService) {
		this.iframeDetailService = iframeDetailService;
	}
}
