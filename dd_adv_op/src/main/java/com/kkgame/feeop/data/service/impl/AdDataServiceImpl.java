package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.AdDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.AdDataDAO;
import com.kkgame.feeop.data.service.AdDataService;
import com.kkgame.feeop.util.DatabaseException;

public class AdDataServiceImpl implements AdDataService {

	private AdDataDAO adDataDAO;

	@Override
	public List<AdDataVO> getAdDataVOList(
			SearchVO searchVO) throws DatabaseException {
		return adDataDAO.getAdDataVOList(searchVO);
	}

	public AdDataDAO getAdDataDAO() {
		return adDataDAO;
	}

	public void setAdDataDAO(AdDataDAO adDataDAO) {
		this.adDataDAO = adDataDAO;
	}
	
	@Override
	public List<AdDataVO> getEffectAdDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return adDataDAO.getEffectAdDataVOList(searchVO);
	}
	
}
