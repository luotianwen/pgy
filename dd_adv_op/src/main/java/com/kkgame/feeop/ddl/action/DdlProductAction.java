package com.kkgame.feeop.ddl.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.service.DdlProductService;

public class DdlProductAction extends BaseAction {

	private static Log logger = LogFactory.getLog(DdlProductAction.class);
	
	private DdlProductVO ddlProductVO;
	
	private List<DdlProductVO> ddlProductVOList;
	
	private DdlProductService ddlProductService;
	
	public String doList() {
		if(ddlProductVO == null) {
			ddlProductVO = new DdlProductVO();
		}
		try {
			ddlProductVOList = ddlProductService.getDdlProductVOList(ddlProductVO);
		}catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(ddlProductVO == null) {
			ddlProductVO = new DdlProductVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doModify() {
		if(ddlProductVO == null) {
			ddlProductVO = new DdlProductVO();
		}
		try {
			ddlProductVO = ddlProductService.getDdlProductVO(ddlProductVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(ddlProductVO == null) {
			ddlProductVO = new DdlProductVO();
		}
		try {
			if(ddlProductVO.getId()>0) {
				ddlProductService.update(ddlProductVO);
				this.printMessage("更新产品信息成功！！！");
			} else {
				ddlProductService.insert(ddlProductVO);
				this.printMessage("创建产品信息成功！！！");
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}

	public DdlProductVO getDdlProductVO() {
		return ddlProductVO;
	}

	public void setDdlProductVO(DdlProductVO ddlProductVO) {
		this.ddlProductVO = ddlProductVO;
	}

	public List<DdlProductVO> getDdlProductVOList() {
		return ddlProductVOList;
	}

	public void setDdlProductVOList(List<DdlProductVO> ddlProductVOList) {
		this.ddlProductVOList = ddlProductVOList;
	}

	public DdlProductService getDdlProductService() {
		return ddlProductService;
	}

	public void setDdlProductService(DdlProductService ddlProductService) {
		this.ddlProductService = ddlProductService;
	}
}
