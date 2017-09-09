package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.AdvFeebackListClickModelVO;
import com.kkgame.feeop.detail.dao.AdvFeebackListClickModelDAO;
import com.kkgame.feeop.detail.service.AdvFeebackListClickModelService;
import com.kkgame.feeop.util.DatabaseException;

public class AdvFeebackListClickModelServiceImpl implements AdvFeebackListClickModelService {

	private AdvFeebackListClickModelDAO advFeebackListClickModelDAO;

	public AdvFeebackListClickModelDAO getAdvFeebackListClickModelDAO() {
		return advFeebackListClickModelDAO;
	}

	public void setAdvFeebackListClickModelDAO(AdvFeebackListClickModelDAO advFeebackListClickModelDAO) {
		this.advFeebackListClickModelDAO = advFeebackListClickModelDAO;
	}

	public void create(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException {

		advFeebackListClickModelDAO.create(advFeebackListClickModelVO);
	}

	public AdvFeebackListClickModelVO getAdvFeebackListClickModelVO(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException {

		return advFeebackListClickModelDAO.getAdvFeebackListClickModelVO(advFeebackListClickModelVO);
	}

	public List<AdvFeebackListClickModelVO> getAdvFeebackListClickModelVOList(AdvFeebackListClickModelVO advFeebackListClickModelVO)
			throws DatabaseException {

		return advFeebackListClickModelDAO.getAdvFeebackListClickModelVOList(advFeebackListClickModelVO);
	}

	public void update(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException {

		advFeebackListClickModelDAO.update(advFeebackListClickModelVO);
	}

}
