	package com.kkgame.feeop.record.service;
	import java.util.List;

	import com.kkgame.feeop.record.bean.CountryLevelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface CountryLevelService {

		List<CountryLevelVO> getCountryLevelVOList(CountryLevelVO countryLevelVO) throws DatabaseException;
		

		void create(CountryLevelVO countryLevelVO) throws DatabaseException;
		
		void update(CountryLevelVO countryLevelVO) throws DatabaseException;
		

		CountryLevelVO getCountryLevelVO(CountryLevelVO countryLevelVO) throws DatabaseException;


		void delete(CountryLevelVO countryLevelVO) throws DatabaseException;
		
		
		
	}

