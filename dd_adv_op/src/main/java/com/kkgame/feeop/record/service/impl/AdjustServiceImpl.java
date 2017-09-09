package com.kkgame.feeop.record.service.impl;

import java.util.List;

import com.kkgame.feeop.record.bean.AdjustVO;
import com.kkgame.feeop.record.dao.AdjustDAO;
import com.kkgame.feeop.record.service.AdjustService;
import com.kkgame.feeop.util.DatabaseException;

public class AdjustServiceImpl implements AdjustService {

	private AdjustDAO adjustDAO;

	public AdjustDAO getAdjustDAO() {
		return adjustDAO;
	}

	public void setAdjustDAO(AdjustDAO adjustDAO) {
		this.adjustDAO = adjustDAO;
	}

	public void create(AdjustVO adjustVO) throws DatabaseException {

		adjustDAO.create(adjustVO);
	}

	public AdjustVO getAdjustVO(AdjustVO adjustVO) throws DatabaseException {

		return adjustDAO.getAdjustVO(adjustVO);
	}

	public List<AdjustVO> getAdjustVOList(AdjustVO adjustVO)
			throws DatabaseException {

		return adjustDAO.getAdjustVOList(adjustVO);
	}

	public void update(AdjustVO adjustVO) throws DatabaseException {

		adjustDAO.update(adjustVO);
	}

}
