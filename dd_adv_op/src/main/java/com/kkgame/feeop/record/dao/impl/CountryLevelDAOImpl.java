package com.kkgame.feeop.record.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.record.bean.CountryLevelVO;
import com.kkgame.feeop.record.dao.CountryLevelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class CountryLevelDAOImpl extends SqlMapClientDaoSupport implements
		CountryLevelDAO {

	// 新增
	public void create(CountryLevelVO countryLevelVO) throws DatabaseException {
		getSqlMapClientTemplate().insert(
				"countryLevelSqlMap.create", countryLevelVO);
	}

	public CountryLevelVO getCountryLevelVO(CountryLevelVO countryLevelVO)
			throws DatabaseException {
		return (CountryLevelVO) getSqlMapClientTemplate().queryForObject(
				"countryLevelSqlMap.getCountryLevelVO", countryLevelVO);
	}

	public List<CountryLevelVO> getCountryLevelVOList(
			CountryLevelVO countryLevelVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"countryLevelSqlMap.getCountryLevelVOListCount",
						countryLevelVO);
		countryLevelVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList(
				"countryLevelSqlMap.getCountryLevelVOList", countryLevelVO);
	}

	@Override
	public void delete(CountryLevelVO countryLevelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("countryLevelSqlMap.delete",
				countryLevelVO);
	}
	
	// 修改
	public void update(CountryLevelVO countryLevelVO) throws DatabaseException {
		getSqlMapClientTemplate().update("countryLevelSqlMap.update",
				countryLevelVO);
	}

}
