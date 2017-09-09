package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdverVO;
import com.kkgame.feeop.adver.dao.AdverDAO;
import com.kkgame.feeop.adver.service.AdverService;
import com.kkgame.feeop.util.DatabaseException;

public class AdverServiceImpl implements AdverService {

	private AdverDAO adverDAO;

	@Override
	public AdverVO getAdverVO(AdverVO adverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return adverDAO.getAdverVO(adverVO);
	}
	
	@Override
	public List<AdverVO> getAdverVOList(AdverVO adverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return adverDAO.getAdverVOList(adverVO);
	}
	
	@Override
	public List<AdverVO> getAllAdver(AdverVO adverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return adverDAO.getAllAdver(adverVO);
	}
	
	@Override
	public void insert(AdverVO adverVO) throws DatabaseException {

		adverDAO.insert(adverVO);
	}
	
	@Override
	public void update(AdverVO adverVO) throws DatabaseException {

		adverDAO.update(adverVO);
	}
	
	@Override
	public void updateStatus(AdverVO adverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		adverDAO.updateStatus(adverVO);
	}
	
	public AdverDAO getAdverDAO() {
		return adverDAO;
	}

	public void setAdverDAO(AdverDAO adverDAO) {
		this.adverDAO = adverDAO;
	}
}
