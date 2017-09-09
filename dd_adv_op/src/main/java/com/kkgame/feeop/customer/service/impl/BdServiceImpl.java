package com.kkgame.feeop.customer.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.customer.bean.BdVO;
import com.kkgame.feeop.customer.bean.PortalUserVO;
import com.kkgame.feeop.customer.dao.BdDAO;
import com.kkgame.feeop.customer.service.BdService;
import com.kkgame.feeop.util.DatabaseException;

public class BdServiceImpl implements BdService {
	private static Log logger = LogFactory.getLog(BdServiceImpl.class);

	private BdDAO bdDAO;
//	private PortalUserDAO portalUserDAO;

	public void insert(BdVO bdVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		int id = bdDAO.insert(bdVO);
//		portalUserVO.setRoleId(id);
//		portalUserVO.setRoleType(PkigConstants.USER_TYPE_BD);
//		portalUserDAO.insert(portalUserVO);
	}

	public void update(BdVO bdVO, PortalUserVO portalUserVO)
			throws DatabaseException {
		bdDAO.update(bdVO);
//		portalUserDAO.update(portalUserVO);
	}

	public List<BdVO> getBdList(BdVO bdVO) throws DatabaseException {
		return bdDAO.getBdList(bdVO);
	}

	public BdVO getBdById(int id) throws DatabaseException {
		return bdDAO.getBdById(id);
	}

	public List<BdVO> getAllBdList(BdVO bdVO) throws DatabaseException {

		return bdDAO.getAllBdList(bdVO);
	}

	public boolean checkBdNameExist(BdVO bdVO) throws DatabaseException {
		List list = bdDAO.getBdByCriteria(bdVO);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public void delete(int id, int portalUserId) throws DatabaseException {
		bdDAO.delete(id);
//		portalUserDAO.delete(portalUserId);
	}

	public BdDAO getBdDAO() {
		return bdDAO;
	}

	public void setBdDAO(BdDAO bdDAO) {
		this.bdDAO = bdDAO;
	}
//
//	public PortalUserDAO getPortalUserDAO() {
//		return portalUserDAO;
//	}
//
//	public void setPortalUserDAO(PortalUserDAO portalUserDAO) {
//		this.portalUserDAO = portalUserDAO;
//	}

	public BdVO getBdByName(BdVO bdVO) throws DatabaseException {
		return this.bdDAO.getBdByName(bdVO);
	}

	public List<BdVO> getBdVOList(BdVO bdVO) throws DatabaseException {
		return this.bdDAO.getBdVOList(bdVO);
	}

}
