package com.kkgame.feeop.adver.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.adver.action.LinkadsconfigAction;
import com.kkgame.feeop.adver.bean.LinkadsconfigVO;
import com.kkgame.feeop.adver.service.LinkadsconfigService;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class LinkadsconfigAction extends BaseAction {
	private static Log logger = LogFactory.getLog(LinkadsconfigAction.class);	
	private LinkadsconfigVO linkadsconfigVO;
	private List<LinkadsconfigVO> linkadsconfigVOList;
	private LinkadsconfigService linkadsconfigService;
	 

	public String doList() {
		if(linkadsconfigVO == null) {
			linkadsconfigVO = new LinkadsconfigVO();
		}
		try {	
			linkadsconfigVOList = linkadsconfigService.getLinkadsconfigVOList(linkadsconfigVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(linkadsconfigVO == null) {
			linkadsconfigVO = new LinkadsconfigVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(linkadsconfigVO == null) {
			linkadsconfigVO = new LinkadsconfigVO();
		}
		try {
			linkadsconfigService.create(linkadsconfigVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}
	 
	
	//修改通道
	public String doUpdate() {
		if(linkadsconfigVO == null) {
			linkadsconfigVO = new LinkadsconfigVO();
		}
		try {
			linkadsconfigService.update(linkadsconfigVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(linkadsconfigVO == null) {
			linkadsconfigVO = new LinkadsconfigVO();
		}
		try {
			linkadsconfigVO = linkadsconfigService.getLinkadsconfigVO(linkadsconfigVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(linkadsconfigVO == null) {
			linkadsconfigVO = new LinkadsconfigVO();
		}
		try {
			linkadsconfigVO = linkadsconfigService.getLinkadsconfigVO(linkadsconfigVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public LinkadsconfigVO getLinkadsconfigVO() {
		return linkadsconfigVO;
	}

	public void setLinkadsconfigVO(LinkadsconfigVO linkadsconfigVO) {
		this.linkadsconfigVO = linkadsconfigVO;
	}

	public List<LinkadsconfigVO> getLinkadsconfigVOList() {
		return linkadsconfigVOList;
	}

	public void setLinkadsconfigVOList(List<LinkadsconfigVO> linkadsconfigVOList) {
		this.linkadsconfigVOList = linkadsconfigVOList;
	}

	public LinkadsconfigService getLinkadsconfigService() {
		return linkadsconfigService;
	}

	public void setLinkadsconfigService(LinkadsconfigService linkadsconfigService) {
		this.linkadsconfigService = linkadsconfigService;
	}
	 
	 

}
