package com.kkgame.feeop.adver.service.impl;

import com.kkgame.feeop.adver.bean.SpromotionVO;
import com.kkgame.feeop.adver.dao.SpromotionDAO;
import com.kkgame.feeop.adver.service.SpromotionService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class SpromotionServiceImpl implements SpromotionService {

	private SpromotionDAO spromotionDAO;

	public SpromotionDAO getSpromotionDAO() {
		return spromotionDAO;
	}

	public void setSpromotionDAO(SpromotionDAO spromotionDAO) {
		this.spromotionDAO = spromotionDAO;
	}

	public void create(SpromotionVO spromotionVO) throws DatabaseException {

		spromotionDAO.create(spromotionVO);
	}

	public SpromotionVO getSpromotionVO(SpromotionVO spromotionVO) throws DatabaseException {

		return spromotionDAO.getSpromotionVO(spromotionVO);
	}



	public List<SpromotionVO> getSpromotionVOList(SpromotionVO spromotionVO)
			throws DatabaseException {

		return spromotionDAO.getSpromotionVOList(spromotionVO);
	}

	public void update(SpromotionVO spromotionVO) throws DatabaseException {

		spromotionDAO.update(spromotionVO);
	}

}
