package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdvVO;
import com.kkgame.feeop.adver.dao.AdvDAO;
import com.kkgame.feeop.adver.service.AdvService;
import com.kkgame.feeop.util.DatabaseException;

public class AdvServiceImpl implements AdvService {

	private AdvDAO advDAO;

	@Override
	public AdvVO getAdvVO(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return advDAO.getAdvVO(advVO);
	}
	
	@Override
	public List<AdvVO> getAdvVOList(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return advDAO.getAdvVOList(advVO);
	}
	
	@Override
	public List<AdvVO> getAllAdvVOList(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return advDAO.getAllAdvVOList(advVO);
	}
	
	@Override
	public void insert(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		advDAO.insert(advVO);
	}
	
	@Override
	public void update(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		advDAO.update(advVO);
	}
	
	
	public AdvDAO getAdvDAO() {
		return advDAO;
	}

	public void setAdvDAO(AdvDAO advDAO) {
		this.advDAO = advDAO;
	}	
}
