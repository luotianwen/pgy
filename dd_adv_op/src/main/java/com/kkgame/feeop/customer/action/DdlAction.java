package com.kkgame.feeop.customer.action;

import com.kkgame.feeop.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.customer.bean.DdlVO;
import com.kkgame.feeop.customer.service.DdlService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.Date;
import java.util.List;

public class DdlAction extends BaseAction {
	private static final String ACTION_RESULT_QUERY_CLICK = "queryClick";

	private static Log logger = LogFactory.getLog( DdlAction.class);
	
	private DdlService ddlService;
	
	private DdlVO ddlVO;

	private List<DdlVO> ddlVOList;
	
	public String doModify() {
		if(ddlVO==null) {
			ddlVO = new DdlVO();
		}
		try {
			ddlVO = ddlService.getDdlVO(ddlVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
		}
		return ACTION_RESULT_MODIFY;
	}
	
	public String doUpdate() {
		if(ddlVO==null) {
			ddlVO = new DdlVO();
		}
		try {
			ddlService.update(ddlVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
		}
		printMessage("修改成功");
		return null;
	}

	public String doQueryClick() {
		if(ddlVO == null) {
			ddlVO = new DdlVO();
			ddlVO.setDate(DateUtils.formatDate(new Date()));
		}
		return ACTION_RESULT_QUERY_CLICK;
	}

	public String doListClick() {
		if (ddlVO == null) {
			ddlVO = new DdlVO();
			ddlVO.setDate(DateUtils.formatDate(new Date()));
		}
		try {
			ddlVOList = ddlService.getDdlVOClickList(ddlVO);
			for (DdlVO vo : ddlVOList) {
				vo.setProjectId(ddlVO.getProjectId());
			}
		} catch (Exception e) {
			logger.debug(e);
			return null;
		}
		return ACTION_RESULT_QUERY_CLICK;
	}

	public DdlService getDdlService() {
		return ddlService;
	}

	public void setDdlService(DdlService ddlService) {
		this.ddlService = ddlService;
	}

	public DdlVO getDdlVO() {
		return ddlVO;
	}

	public void setDdlVO(DdlVO ddlVO) {
		this.ddlVO = ddlVO;
	}

	public List<DdlVO> getDdlVOList() {
		return ddlVOList;
	}

	public void setDdlVOList(List<DdlVO> ddlVOList) {
		this.ddlVOList = ddlVOList;
	}
}
