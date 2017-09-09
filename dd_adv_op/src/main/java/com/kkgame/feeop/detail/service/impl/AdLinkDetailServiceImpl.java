package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.AdLinkDetailVO;
import com.kkgame.feeop.detail.dao.AdLinkDetailDAO;
import com.kkgame.feeop.detail.service.AdLinkDetailService;
import com.kkgame.feeop.util.DatabaseException;

public class AdLinkDetailServiceImpl implements AdLinkDetailService {

	private AdLinkDetailDAO adLinkDetailDAO;

	@Override
	public List<AdLinkDetailVO> getAdLinkDetailVOList(AdLinkDetailVO adLinkDetailVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return adLinkDetailDAO.getAdLinkDetailVOList(adLinkDetailVO);
	}
	
	public AdLinkDetailDAO getAdLinkDetailDAO() {
		return adLinkDetailDAO;
	}

	public void setAdLinkDetailDAO(AdLinkDetailDAO adLinkDetailDAO) {
		this.adLinkDetailDAO = adLinkDetailDAO;
	}
}
