package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.Linkads2VO;
import com.kkgame.feeop.adver.dao.Linkads2DAO;
import com.kkgame.feeop.adver.service.Linkads2Service;
import com.kkgame.feeop.util.DatabaseException;

public class Linkads2ServiceImpl implements Linkads2Service {

	private Linkads2DAO linkads2DAO;

	public Linkads2DAO getLinkads2DAO() {
		return linkads2DAO;
	}

	public void setLinkads2DAO(Linkads2DAO linkads2DAO) {
		this.linkads2DAO = linkads2DAO;
	}

	public void create(Linkads2VO linkads2VO) throws DatabaseException {

		linkads2DAO.create(linkads2VO);
	}

	public Linkads2VO getLinkads2VO(Linkads2VO linkads2VO) throws DatabaseException {

		return linkads2DAO.getLinkads2VO(linkads2VO);
	}

	public void copy(Linkads2VO linkads2VO) throws DatabaseException {
		linkads2DAO.copy(linkads2VO);
	}

	public List<Linkads2VO> getLinkads2VOList(Linkads2VO linkads2VO)
			throws DatabaseException {

		return linkads2DAO.getLinkads2VOList(linkads2VO);
	}

	public void update(Linkads2VO linkads2VO) throws DatabaseException {

		linkads2DAO.update(linkads2VO);
	}

}
