package com.kkgame.hz.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.base.NameVO;
import com.kkgame.hz.entities.BhVO;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.BhService;
import com.kkgame.hz.service.PortalUserService;
import com.kkgame.hz.tag.TagService;

public class BhAction extends BaseAction {
	private static Log logger = LogFactory.getLog(BhAction.class);
	private BhService bhService;
	private TagService tagService;
	private PortalUserService portalUserService;
	
	private BhVO bhVO;
	private PortalUserVO portalUserVO;
	private List bhList ;
	private List<NameVO> bdList ;
	
	public BhService getBhService() {
		return bhService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public PortalUserService getPortalUserService() {
		return portalUserService;
	}

	public String doQuery() {
		if (bhVO==null){
			logger.info("bhVO is null");
			bhVO = new BhVO();
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doCreate(){	
		if (bhVO==null){
			logger.info("bhVO is null");
			bhVO = new BhVO();
		}
		try {
			bdList = tagService.getBdList();
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave(){
		if (bhVO==null){
			logger.info("bhVO is null");
			bhVO = new BhVO();
		}
		String  userType = getLoginUserType();
		if(PkigConstants.USER_TYPE_BD.equals(userType)){
			bhVO.setBusinessDeveloperId(getLoginUserRoleId());
		}
		try {
			bhService.insert(bhVO,portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("创建商务协助人员成功!");
		return null;
	}
	
	public String doList(){
		if (bhVO==null){
			logger.info("bhVO is null");
			bhVO = new BhVO();
		}
		try {
			String  userType = getLoginUserType();
			if(PkigConstants.USER_TYPE_BD.equals(userType)){
				bhVO.setBusinessDeveloperId(getLoginUserRoleId());
			}
			bhList = bhService.getBhList(bhVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doModify(){
		if (bhVO==null){
			logger.info("BhVO is null");
			bhVO = new BhVO();
		}
		int id = bhVO.getId();
		int portalUserId = bhVO.getPortalUserId();
		try {
			bhVO = bhService.getBhById(id);
			portalUserVO = portalUserService.getPortalUserById(portalUserId);
			bdList = tagService.getBdList();
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doDetail(){
		if (bhVO==null){
			logger.info("BhVO is null");
			bhVO = new BhVO();
		}
		int id = bhVO.getId();
		int portalUserId = bhVO.getPortalUserId();
		try {
			bhVO = bhService.getBhById(id);
			portalUserVO = portalUserService.getPortalUserById(portalUserId);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}
	
	public String  doUpdate(){
		logger.debug(bhVO.getName());
		try {
			bhService.update(bhVO, portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("更新商务协助人员成功!");
		return null;
	}
	
	public String doRemove(){
		if (bhVO==null){
			logger.info("BdVO is null");
			bhVO = new BhVO();
		}
		try {
			bhService.delete(bhVO.getId(),bhVO.getPortalUserId());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("删除商务协助人员成功!");
		return null;
	}
	public String doValidName() throws UnsupportedEncodingException{
		boolean flag;	 
		try {
			flag = bhService.checkBhNameExist(bhVO);
			if(flag == true) {
	            this.addActionMessage("true");
	        } else {
	            this.addActionMessage("false");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return PlAIN_MESSAGE;
	}
	
	public PortalUserVO getPortalUserVO() {
		return portalUserVO;
	}

	public void setPortalUserVO(PortalUserVO portalUserVO) {
		this.portalUserVO = portalUserVO;
	}

	public BhVO getBhVO() {
		return bhVO;
	}
	public void setBhVO(BhVO bhVO) {
		this.bhVO = bhVO;
	}

	public List getBhList() {
		return bhList;
	}

	public void setBhList(List bhList) {
		this.bhList = bhList;
	}

	public List<NameVO> getBdList() {
		return bdList;
	}

	public void setBdList(List<NameVO> bdList) {
		this.bdList = bdList;
	}

	public void setBhService(BhService bhService) {
		this.bhService = bhService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setPortalUserService(PortalUserService portalUserService) {
		this.portalUserService = portalUserService;
	}
}
