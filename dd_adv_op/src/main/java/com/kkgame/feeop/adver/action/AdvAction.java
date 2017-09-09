package com.kkgame.feeop.adver.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.adver.bean.AdvVO;
import com.kkgame.feeop.adver.service.AdvService;
import com.kkgame.feeop.base.BaseAction;

public class AdvAction extends BaseAction {

	private static Log logger = LogFactory.getLog(AdvAction.class);
	
	private AdvVO advVO;
	
	private List<AdvVO> advVOList;
	
	private AdvService advService;
	
	public String doList() {
		if(advVO== null) {
			advVO = new AdvVO();
		}
		try {
			advVOList = advService.getAdvVOList(advVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doSave() {
		if(advVO== null) {
			advVO = new AdvVO();
		}
		try {
			if(advVO.getId()==0) {
				advService.insert(advVO);
				this.printMessage("创建广告信息成功！！！");
			} else {
				advService.update(advVO);
				this.printMessage("修改广告信息成功！！！");
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}
	
	public String doModify() {
		if(advVO== null) {
			advVO = new AdvVO();
		}
		try {
			advVO = advService.getAdvVO(advVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doCreate() {
		if(advVO== null) {
			advVO = new AdvVO();
		}
		return ACTION_RESULT_CREATE;
	}
	public AdvVO getAdvVO() {
		return advVO;
	}

	public void setAdvVO(AdvVO advVO) {
		this.advVO = advVO;
	}

	public List<AdvVO> getAdvVOList() {
		return advVOList;
	}

	public void setAdvVOList(List<AdvVO> advVOList) {
		this.advVOList = advVOList;
	}

	public AdvService getAdvService() {
		return advService;
	}

	public void setAdvService(AdvService advService) {
		this.advService = advService;
	}
}
