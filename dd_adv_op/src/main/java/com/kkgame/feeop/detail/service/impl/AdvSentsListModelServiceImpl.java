package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.AdvSentsListModelVO;
import com.kkgame.feeop.detail.dao.AdvSentsListModelDAO;
import com.kkgame.feeop.detail.service.AdvSentsListModelService;
import com.kkgame.feeop.util.DatabaseException;

public class AdvSentsListModelServiceImpl implements AdvSentsListModelService {

	private AdvSentsListModelDAO advSentsListModelDAO;

	public AdvSentsListModelDAO getAdvSentsListModelDAO() {
		return advSentsListModelDAO;
	}

	public void setAdvSentsListModelDAO(AdvSentsListModelDAO advSentsListModelDAO) {
		this.advSentsListModelDAO = advSentsListModelDAO;
	}

	public void create(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException {

		advSentsListModelDAO.create(advSentsListModelVO);
	}

	public AdvSentsListModelVO getAdvSentsListModelVO(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException {

		return advSentsListModelDAO.getAdvSentsListModelVO(advSentsListModelVO);
	}

	public List<AdvSentsListModelVO> getAdvSentsListModelVOList(AdvSentsListModelVO advSentsListModelVO)
			throws DatabaseException {

		return advSentsListModelDAO.getAdvSentsListModelVOList(advSentsListModelVO);
	}

	public void update(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException {

		advSentsListModelDAO.update(advSentsListModelVO);
	}

}
