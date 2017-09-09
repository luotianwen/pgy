package com.kkgame.feeop.detail.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.detail.bean.DdlDetailVO;
import com.kkgame.feeop.detail.service.DdlDetailService;
import com.kkgame.feeop.util.DateUtils;

public class DdlDetailAction extends BaseAction {

	private static Log logger = LogFactory.getLog(DdlDetailAction.class);

	private DdlDetailVO ddlDetailVO;
	
	private DdlDetailService ddlDetailService;
	
	private List<DdlDetailVO> ddlDetailVOList;

	public String doQuery() {
		if(ddlDetailVO == null) {
			ddlDetailVO = new DdlDetailVO();
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doList() {
		if(ddlDetailVO == null) {
			ddlDetailVO = new DdlDetailVO();
			ddlDetailVO.setCreateTime(DateUtils
					.formatDate(new Date()));
		}
		if(ddlDetailVO.getCreateTime()!=null&&!"".equals(ddlDetailVO.getCreateTime())) {
			String day = ddlDetailVO.getCreateTime().trim().replaceAll("-", "");
			ddlDetailVO.setTable(day);
			try {
				if(ddlDetailVO.getType()==1) {
					ddlDetailVOList = ddlDetailService.getDdlSdkDetailVOList(ddlDetailVO);
				} else if(ddlDetailVO.getType()==2) {
					ddlDetailVOList = ddlDetailService.getDdlAdjustDetailVOList(ddlDetailVO);
				}
			} catch (Exception e) {
				logger.debug(e);
			}
			
		}
		return ACTION_RESULT_LIST;
	}
	
	public DdlDetailVO getDdlDetailVO() {
		return ddlDetailVO;
	}

	public void setDdlDetailVO(DdlDetailVO ddlDetailVO) {
		this.ddlDetailVO = ddlDetailVO;
	}

	public DdlDetailService getDdlDetailService() {
		return ddlDetailService;
	}

	public void setDdlDetailService(DdlDetailService ddlDetailService) {
		this.ddlDetailService = ddlDetailService;
	}

	public List<DdlDetailVO> getDdlDetailVOList() {
		return ddlDetailVOList;
	}

	public void setDdlDetailVOList(List<DdlDetailVO> ddlDetailVOList) {
		this.ddlDetailVOList = ddlDetailVOList;
	}
}
