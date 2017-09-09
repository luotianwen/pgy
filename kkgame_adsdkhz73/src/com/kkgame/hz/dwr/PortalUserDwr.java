package com.kkgame.hz.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.hz.action.AgentAction;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.PortalUserService;
import com.kkgame.hz.util.UtilHelper;

public class PortalUserDwr {
	private static Log logger = LogFactory.getLog(AgentAction.class);

	public boolean checkLoginId(PortalUserVO portalUserVO) {
		try {
			String loginId = portalUserVO.getLoginId();
			int id = portalUserVO.getId();
			logger.info("loginId: " + loginId);
			portalUserVO = new PortalUserVO();
			if (loginId == null && loginId.equals("")) {
				return true;
			}
			if (id > 0) {
				portalUserVO.setId(id);
			}
			portalUserVO.setLoginId(loginId);
			PortalUserService portalUserService = (PortalUserService) new BaseAction()
					.getServiceObject(UtilHelper.PORTALUSER_SERVICE);
			return portalUserService.checkPortalUserLoginIdExist(portalUserVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

}
