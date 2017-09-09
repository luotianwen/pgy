package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.AdLinkDataDAO;
import com.kkgame.feeop.data.service.AdLinkDataService;
import com.kkgame.feeop.util.DatabaseException;

public class AdLinkDataServiceImpl implements AdLinkDataService {

	private AdLinkDataDAO adLinkDataDAO;

	@Override
	public List<AdLinkDataVO> getAdLinkDataVOList(SearchVO searchVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return adLinkDataDAO.getAdLinkDataVOList(searchVO);
	}
	
	public AdLinkDataDAO getAdLinkDataDAO() {
		return adLinkDataDAO;
	}

	public void setAdLinkDataDAO(AdLinkDataDAO adLinkDataDAO) {
		this.adLinkDataDAO = adLinkDataDAO;
	}
}
