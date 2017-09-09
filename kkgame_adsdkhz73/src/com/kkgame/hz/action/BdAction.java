package com.kkgame.hz.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.base.NameVO;
import com.kkgame.hz.entities.BdVO;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.BdService;
import com.kkgame.hz.service.PortalUserService;
import com.kkgame.hz.tag.TagService;
import com.kkgame.hz.util.HttpUtil;

public class BdAction extends BaseAction {
	private static Log logger = LogFactory.getLog(BdAction.class);
	private BdService bdService;
	private TagService tagService;
	private PortalUserService portalUserService;
	private BdVO bdVO;
	private PortalUserVO userVO;
	private PortalUserVO portalUserVO;
	private List<BdVO> bdList;
	private List<NameVO> agentList;

	public String doQuery() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doCreate() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		try {
			agentList = tagService.getAgentList();
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		try { 
			String userType = getLoginUserType();
			if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
				bdVO.setAgentId(getLoginUserRoleId());
			}
			bdService.insert(bdVO, portalUserVO);
			StringBuffer sb = new StringBuffer();
			sb.append("interVO.type=2");
			sb.append("&interVO.id=").append(bdVO.getId())
			.append("&interVO.agentId=").append(bdVO.getAgentId())
			.append("&interVO.name=").append(bdVO.getName());
			String resp = HttpUtil.post(PkigConstants.URL_PROJECT, sb.toString());
			logger.info(resp);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("创建商务拓展人员成功!");
		return null;
	}

	public String doList() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		try {
			String userType = getLoginUserType();
			if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
				bdVO.setAgentId(getLoginUserRoleId());
			}
			bdList = bdService.getBdList(bdVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}

	public String doModify() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		int id = bdVO.getId();
		int portalUserId = bdVO.getPortalUserId();
		try {
			bdVO = bdService.getBdById(id);
			portalUserVO = portalUserService.getPortalUserById(portalUserId);
			agentList = tagService.getAgentList();
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}

	public String doUpdate() {
		logger.debug(bdVO.getName());
		try {
			bdService.update(bdVO, portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("更新商务拓展人员成功!");
		return null;
	}

	public String doDetail() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		int id = bdVO.getId();
		int portalUserId = bdVO.getPortalUserId();
		try {
			bdVO = bdService.getBdById(id);
			portalUserVO = portalUserService.getPortalUserById(portalUserId);
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public String doRemove() {
		if (bdVO == null) {
			logger.info("BdVO is null");
			bdVO = new BdVO();
		}
		try {
			bdService.delete(bdVO.getId(), bdVO.getPortalUserId());
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("删除商务拓展人员成功!");
		return null;
	}

	public String doValidName() throws UnsupportedEncodingException {
		boolean flag;
		try {
			flag = bdService.checkBdNameExist(bdVO);
			if (flag == true) {
				this.addActionMessage("true");
			} else {
				this.addActionMessage("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PlAIN_MESSAGE;
	}

	public String getAllBdList() {
		if (bdVO == null) {
			bdVO = new BdVO();
		}
		String userType = getLoginUserType();
		if (PkigConstants.USER_TYPE_AGENT.equals(userType)) {
			bdVO.setAgentId(getLoginUserRoleId());
		}
		try {
			bdList = bdService.getAllBdList(bdVO);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String getAllBdListByCanalDirector() {
		try {
			userVO = (PortalUserVO) ServletActionContext.getRequest()
					.getSession()
					.getAttribute(PkigConstants.SESSION_PORTALUSER);
			bdVO = bdService.getBdById(userVO.getRoleId());
			bdList = bdService.getAllBdList(bdVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public PortalUserVO getPortalUserVO() {
		return portalUserVO;
	}

	public void setPortalUserVO(PortalUserVO portalUserVO) {
		this.portalUserVO = portalUserVO;
	}

	public BdVO getBdVO() {
		return bdVO;
	}

	public void setBdVO(BdVO bdVO) {
		this.bdVO = bdVO;
	}

	public List<BdVO> getBdList() {
		return bdList;
	}

	public void setBdList(List<BdVO> bdList) {
		this.bdList = bdList;
	}

	public List<NameVO> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<NameVO> agentList) {
		this.agentList = agentList;
	}

	public void setBdService(BdService bdService) {
		this.bdService = bdService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setPortalUserService(PortalUserService portalUserService) {
		this.portalUserService = portalUserService;
	}

	public BdService getBdService() {
		return bdService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public PortalUserService getPortalUserService() {
		return portalUserService;
	}

	public PortalUserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(PortalUserVO userVO) {
		this.userVO = userVO;
	}

}
