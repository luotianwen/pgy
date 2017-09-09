package com.kkgame.feeop.record.service.impl;

import java.util.List;

import com.kkgame.feeop.record.bean.CountryLevelVO;
import com.kkgame.feeop.record.dao.CountryLevelDAO;
import com.kkgame.feeop.record.service.CountryLevelService;
import com.kkgame.feeop.util.DatabaseException;

public class CountryLevelServiceImpl implements CountryLevelService {

	private CountryLevelDAO countryLevelDAO;

	public CountryLevelDAO getCountryLevelDAO() {
		return countryLevelDAO;
	}

	public void setCountryLevelDAO(CountryLevelDAO countryLevelDAO) {
		this.countryLevelDAO = countryLevelDAO;
	}

	public void create(CountryLevelVO countryLevelVO) throws DatabaseException {

		countryLevelDAO.create(countryLevelVO);
	}

	public CountryLevelVO getCountryLevelVO(CountryLevelVO countryLevelVO) throws DatabaseException {

		return countryLevelDAO.getCountryLevelVO(countryLevelVO);
	}

	public List<CountryLevelVO> getCountryLevelVOList(CountryLevelVO countryLevelVO)
			throws DatabaseException {

		return countryLevelDAO.getCountryLevelVOList(countryLevelVO);
	}
	
	@Override
	public void delete(CountryLevelVO countryLevelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		countryLevelDAO.delete(countryLevelVO);
	}

	public void update(CountryLevelVO countryLevelVO) throws DatabaseException {

		countryLevelDAO.update(countryLevelVO);
	}

}
