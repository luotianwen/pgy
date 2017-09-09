package com.kkgame.feeop.detail.service.impl;

import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.detail.dao.SubDetailDAO;
import com.kkgame.feeop.detail.service.SubDetailService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class SubDetailServiceImpl implements SubDetailService {

	private SubDetailDAO subDetailDAO;

	@Override
	public List<SubDetailVO> getSubDetailVOList(SubDetailVO subDetailVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return subDetailDAO.getSubDetailVOList(subDetailVO);
	}
	
	public SubDetailDAO getSubDetailDAO() {
		return subDetailDAO;
	}

	public void setSubDetailDAO(SubDetailDAO subDetailDAO) {
		this.subDetailDAO = subDetailDAO;
	}
}
