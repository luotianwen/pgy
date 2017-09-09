package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.AdIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.AdIncomeDAO;
import com.kkgame.feeop.data.service.AdIncomeService;
import com.kkgame.feeop.util.DatabaseException;

public class AdIncomeServiceImpl implements AdIncomeService {

	private AdIncomeDAO adIncomeDAO;

	@Override
	public List<AdIncomeVO> getAdIncomeVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return adIncomeDAO.getAdIncomeVOList(searchVO);
	}
	
	public AdIncomeDAO getAdIncomeDAO() {
		return adIncomeDAO;
	}

	public void setAdIncomeDAO(AdIncomeDAO adIncomeDAO) {
		this.adIncomeDAO = adIncomeDAO;
	}
}
