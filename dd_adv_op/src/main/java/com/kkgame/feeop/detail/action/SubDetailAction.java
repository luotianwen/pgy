package com.kkgame.feeop.detail.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.detail.service.SubDetailService;
import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class SubDetailAction extends BaseAction {

	private static Log logger = LogFactory.getLog(SubDetailAction.class);
	
	private SubDetailVO subDetailVO;
	
	private List<SubDetailVO> subDetailVOList;

	private SubDetailService subDetailService;

	public String doList() {
		if(subDetailVO == null) {
			subDetailVO = new SubDetailVO();
			subDetailVO.setCdate(DateUtils.formatDate(new Date()));
		} else{
			if(subDetailVO.getCdate()!=null
					&&!"".equals(subDetailVO.getCdate())
					) {
				String day = subDetailVO.getCdate().trim().replaceAll("-", "");
				subDetailVO.setCdate(day);
				try {
					subDetailVOList = subDetailService.getSubDetailVOList(subDetailVO);
				} catch (Exception e) {
					logger.debug(e);
				}
			}
		}
		return ACTION_RESULT_LIST;
	}

	public SubDetailVO getSubDetailVO() {
		return subDetailVO;
	}

	public void setSubDetailVO(SubDetailVO subDetailVO) {
		this.subDetailVO = subDetailVO;
	}

	public List<SubDetailVO> getSubDetailVOList() {
		return subDetailVOList;
	}

	public void setSubDetailVOList(List<SubDetailVO> subDetailVOList) {
		this.subDetailVOList = subDetailVOList;
	}

	public SubDetailService getSubDetailService() {
		return subDetailService;
	}

	public void setSubDetailService(SubDetailService subDetailService) {
		this.subDetailService = subDetailService;
	}
}
