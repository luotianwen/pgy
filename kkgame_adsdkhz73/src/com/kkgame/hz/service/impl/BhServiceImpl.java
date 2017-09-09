package com.kkgame.hz.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.PkigConstants;
import com.kkgame.hz.dao.BhDAO;
import com.kkgame.hz.dao.PortalUserDAO;
import com.kkgame.hz.entities.BhVO;
import com.kkgame.hz.entities.PortalUserVO;
import com.kkgame.hz.service.BhService;

public class BhServiceImpl implements BhService {
	private static Log logger = LogFactory.getLog(BhServiceImpl.class);

	private BhDAO bhDAO;
	private PortalUserDAO portalUserDAO;

	public void insert(BhVO bhVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		int id = bhDAO.insert(bhVO);
		portalUserVO.setRoleId(id);
		portalUserVO.setRoleType(PkigConstants.USER_TYPE_BH);
		portalUserDAO.insert(portalUserVO);
	}

	public void update(BhVO bhVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		bhDAO.update(bhVO);
		portalUserDAO.update(portalUserVO);
	}

	public List<BhVO> getBhList(BhVO bhVO) throws DatabaseException {
		return bhDAO.getBhList(bhVO);
	}

	public BhVO getBhById(int id) throws DatabaseException {
		return bhDAO.getBhById(id);
	}

	public boolean checkBhNameExist(BhVO bhVO) throws DatabaseException {
		List list = bhDAO.getBhByCriteria(bhVO);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public void delete(int id, int portalUserId) throws DatabaseException {
		bhDAO.delete(id);
		portalUserDAO.delete(portalUserId);
	}

	public BhDAO getBhDAO() {
		return bhDAO;
	}

	public void setBhDAO(BhDAO bhDAO) {
		this.bhDAO = bhDAO;
	}

	public PortalUserDAO getPortalUserDAO() {
		return portalUserDAO;
	}

	public void setPortalUserDAO(PortalUserDAO portalUserDAO) {
		this.portalUserDAO = portalUserDAO;
	}

}
