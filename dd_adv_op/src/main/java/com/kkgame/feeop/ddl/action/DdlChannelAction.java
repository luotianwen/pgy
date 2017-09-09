package com.kkgame.feeop.ddl.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.service.DdlChannelService;
import com.kkgame.feeop.util.DatabaseException;

public class DdlChannelAction extends BaseAction {

	private static Log logger = LogFactory.getLog(DdlChannelAction.class);
	
	
	private DdlChannelVO ddlChannelVO;
	
	private List<DdlChannelVO> ddlChannelVOList;
	
	private DdlChannelService ddlChannelService;
	
	public String doList() {
		if(ddlChannelVO == null) {
			ddlChannelVO = new DdlChannelVO();
		}
		
		try {
			ddlChannelVOList = ddlChannelService.getDdlChannelVOList(ddlChannelVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(ddlChannelVO == null) {
			ddlChannelVO = new DdlChannelVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doModify() {
		if(ddlChannelVO == null) {
			ddlChannelVO = new DdlChannelVO();
		}
		try {
			ddlChannelVO = ddlChannelService.getDdlChannelVO(ddlChannelVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(ddlChannelVO == null) {
			ddlChannelVO = new DdlChannelVO();
		}
		try {
			if(ddlChannelVO.getId()>0) {
				ddlChannelService.update(ddlChannelVO);
				this.printMessage("更新渠道信息成功！！！");
	
			} else {
				ddlChannelService.insert(ddlChannelVO);
				this.printMessage("创建渠道信息成功！！！");
	
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public DdlChannelVO getDdlChannelVO() {
		return ddlChannelVO;
	}

	public void setDdlChannelVO(DdlChannelVO ddlChannelVO) {
		this.ddlChannelVO = ddlChannelVO;
	}

	public List<DdlChannelVO> getDdlChannelVOList() {
		return ddlChannelVOList;
	}

	public void setDdlChannelVOList(List<DdlChannelVO> ddlChannelVOList) {
		this.ddlChannelVOList = ddlChannelVOList;
	}

	public DdlChannelService getDdlChannelService() {
		return ddlChannelService;
	}

	public void setDdlChannelService(DdlChannelService ddlChannelService) {
		this.ddlChannelService = ddlChannelService;
	}
}
