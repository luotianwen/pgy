package com.kkgame.feeop.adver.action;

import java.util.List;

import com.kkgame.feeop.adver.bean.LinkadsconfigVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.adver.action.Linkads2Action;
import com.kkgame.feeop.adver.bean.Linkads2VO;
import com.kkgame.feeop.adver.service.Linkads2Service;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class Linkads2Action extends BaseAction {
	private static Log logger = LogFactory.getLog(Linkads2Action.class);	
	private Linkads2VO linkads2VO;
	private List<Linkads2VO> linkads2VOList;
	private Linkads2Service linkads2Service;
	 

	public String doList() {
		if(linkads2VO == null) {
			linkads2VO = new Linkads2VO();
		}
		try {	
			linkads2VOList = linkads2Service.getLinkads2VOList(linkads2VO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(linkads2VO == null) {
			linkads2VO = new Linkads2VO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(linkads2VO == null) {
			linkads2VO = new Linkads2VO();
		}
		try {
			linkads2Service.create(linkads2VO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("创建配置信息成功");
		return null;
	}
	public String copy() {

		try {
			linkads2Service.copy(linkads2VO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("成功");
		return null;
	}
	
	//修改通道
	public String doUpdate() {
		if(linkads2VO == null) {
			linkads2VO = new Linkads2VO();
		}
		try {
			linkads2Service.update(linkads2VO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(linkads2VO == null) {
			linkads2VO = new Linkads2VO();
		}
		try {
			linkads2VO = linkads2Service.getLinkads2VO(linkads2VO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail() {
		if(linkads2VO == null) {
			linkads2VO = new Linkads2VO();
		}
		try {
			linkads2VO = linkads2Service.getLinkads2VO(linkads2VO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public Linkads2VO getLinkads2VO() {
		return linkads2VO;
	}

	public void setLinkads2VO(Linkads2VO linkads2VO) {
		this.linkads2VO = linkads2VO;
	}

	public List<Linkads2VO> getLinkads2VOList() {
		return linkads2VOList;
	}

	public void setLinkads2VOList(List<Linkads2VO> linkads2VOList) {
		this.linkads2VOList = linkads2VOList;
	}

	public Linkads2Service getLinkads2Service() {
		return linkads2Service;
	}

	public void setLinkads2Service(Linkads2Service linkads2Service) {
		this.linkads2Service = linkads2Service;
	}
	 
	 

}
