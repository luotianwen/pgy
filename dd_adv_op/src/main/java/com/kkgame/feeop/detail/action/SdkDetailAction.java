package com.kkgame.feeop.detail.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.detail.bean.SdkDetailVO;
import com.kkgame.feeop.detail.service.SdkDetailService;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;

public class SdkDetailAction extends BaseAction {

	private static Log logger = LogFactory.getLog(SdkDetailAction.class);
	
	private SdkDetailVO sdkDetailVO;
	
	private List<SdkDetailVO> sdkDetailVOList;

	private SdkDetailService sdkDetailService;

	public String doList() {
		if(sdkDetailVO == null) {
			sdkDetailVO = new SdkDetailVO();
			sdkDetailVO.setCdate(DateUtils.formatYearMonthDay(new Date()));
		}
		if(sdkDetailVO.getCdate()!=null
		&&!"".equals(sdkDetailVO.getCdate())
				) {
			String day = sdkDetailVO.getCdate().trim().replaceAll("-", "");
			sdkDetailVO.setCdate(day);
			try {
			sdkDetailVOList = sdkDetailService.getSdkDetailVOList(sdkDetailVO);
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return ACTION_RESULT_LIST;
	}

	public SdkDetailVO getSdkDetailVO() {
		return sdkDetailVO;
	}

	public void setSdkDetailVO(SdkDetailVO sdkDetailVO) {
		this.sdkDetailVO = sdkDetailVO;
	}

	public List<SdkDetailVO> getSdkDetailVOList() {
		return sdkDetailVOList;
	}

	public void setSdkDetailVOList(List<SdkDetailVO> sdkDetailVOList) {
		this.sdkDetailVOList = sdkDetailVOList;
	}

	public SdkDetailService getSdkDetailService() {
		return sdkDetailService;
	}

	public void setSdkDetailService(SdkDetailService sdkDetailService) {
		this.sdkDetailService = sdkDetailService;
	}
}
