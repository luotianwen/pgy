package com.kkgame.hz.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.PortalUserDAO;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.PortalUserService;

public class PortalUserServiceImpl implements PortalUserService {
	
	private static Log logger = LogFactory.getLog(PortalUserServiceImpl.class);
	private PortalUserDAO portalUserDAO;

	public void insert(PortalUserVO portalUserVO) throws DatabaseException {
		try {
			portalUserDAO.insert(portalUserVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			e.printStackTrace();
		}
	}

	public boolean checkPortalUserLoginIdExist(PortalUserVO portalUserVO)
			throws DatabaseException {
		try {
			List list = portalUserDAO.getPortalUserByCriteria(portalUserVO);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (DatabaseException e) {
			logger.debug(e);
		}
		return false;
	}

	public PortalUserVO getPortalUserById(int id) throws DatabaseException {
		return portalUserDAO.getPortalUserById(id);
	}

	public PortalUserVO getPortalUserByLoginId(String loginId)
			throws DatabaseException {
		return portalUserDAO.getPortalUserByLoginId(loginId);
	}

	public void updateUserLastLogin(PortalUserVO portalUserVO)
			throws DatabaseException {
		portalUserDAO.updateUserLastLogin(portalUserVO);
	}

	public void updatePasswd(PortalUserVO portalUserVO)
			throws DatabaseException {
		portalUserDAO.updatePasswd(portalUserVO);
	}

	public PortalUserDAO getPortalUserDAO() {
		return portalUserDAO;
	}

	public void setPortalUserDAO(PortalUserDAO portalUserDAO) {
		this.portalUserDAO = portalUserDAO;
	}

	public List<PortalUserVO> getPortalUserList(PortalUserVO portalUserVO)
			throws DatabaseException {

		return portalUserDAO.getPortalUserList(portalUserVO);
	}

	public void update(PortalUserVO portalUserVO) throws DatabaseException {
		portalUserDAO.update(portalUserVO);
	}

	public void delete(int id) throws DatabaseException {
		portalUserDAO.delete(id);
	}

}
