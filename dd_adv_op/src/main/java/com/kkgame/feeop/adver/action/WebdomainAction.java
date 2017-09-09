package com.kkgame.feeop.adver.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.adver.action.WebdomainAction;
import com.kkgame.feeop.adver.bean.WebdomainVO;
import com.kkgame.feeop.adver.service.WebdomainService;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class WebdomainAction extends BaseAction {
	private static Log logger = LogFactory.getLog(WebdomainAction.class);	
	private WebdomainVO webdomainVO;
	private List<WebdomainVO> webdomainVOList;
	private WebdomainService webdomainService;
	 

	public String doList() {
		if(webdomainVO == null) {
			webdomainVO = new WebdomainVO();
		}
		try {	
			webdomainVOList = webdomainService.getWebdomainVOList(webdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(webdomainVO == null) {
			webdomainVO = new WebdomainVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(webdomainVO == null) {
			webdomainVO = new WebdomainVO();
		}
		try {
			webdomainService.create(webdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}

	//修改通道
	public String doRemove() {

		try {
			webdomainService.delete(webdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("删除成功");
		return null;
	}
	//修改通道
	public String doUpdate() {
		if(webdomainVO == null) {
			webdomainVO = new WebdomainVO();
		}
		try {
			webdomainService.update(webdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(webdomainVO == null) {
			webdomainVO = new WebdomainVO();
		}
		try {
			webdomainVO = webdomainService.getWebdomainVO(webdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(webdomainVO == null) {
			webdomainVO = new WebdomainVO();
		}
		try {
			webdomainVO = webdomainService.getWebdomainVO(webdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public WebdomainVO getWebdomainVO() {
		return webdomainVO;
	}

	public void setWebdomainVO(WebdomainVO webdomainVO) {
		this.webdomainVO = webdomainVO;
	}

	public List<WebdomainVO> getWebdomainVOList() {
		return webdomainVOList;
	}

	public void setWebdomainVOList(List<WebdomainVO> webdomainVOList) {
		this.webdomainVOList = webdomainVOList;
	}

	public WebdomainService getWebdomainService() {
		return webdomainService;
	}

	public void setWebdomainService(WebdomainService webdomainService) {
		this.webdomainService = webdomainService;
	}
	 
	 

}
