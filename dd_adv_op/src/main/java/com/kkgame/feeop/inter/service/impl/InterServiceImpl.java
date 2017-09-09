package com.kkgame.feeop.inter.service.impl;

import com.kkgame.feeop.inter.bean.InterVO;
import com.kkgame.feeop.inter.dao.InterDAO;
import com.kkgame.feeop.inter.service.InterService;
import com.kkgame.feeop.util.DatabaseException;

public class InterServiceImpl implements InterService {

	private InterDAO interDAO;
	
	@Override
	public void create(InterVO interVO) throws DatabaseException {

		interDAO.create(interVO);
	}

	public InterDAO getInterDAO() {
		return interDAO;
	}

	public void setInterDAO(InterDAO interDAO) {
		this.interDAO = interDAO;
	}
}
