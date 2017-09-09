package com.kkgame.feeop.ddl.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.dao.DdlProductDAO;
import com.kkgame.feeop.tag.bean.CountryVO;
import com.kkgame.feeop.util.DatabaseException;

public class DdlProductDAOImpl extends SqlMapClientDaoSupport implements DdlProductDAO {

	@Override
	public List<DdlProductVO> getAllDdlProduct(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlProductSqlMap.getAllDdlProduct", 	ddlProductVO);
	}
	
	@Override
	public DdlProductVO getDdlProductVO(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (DdlProductVO) getSqlMapClientTemplate().queryForObject("ddlProductSqlMap.getDdlProductVO", ddlProductVO);
	}
	
	@Override
	public List<DdlProductVO> getDdlProductVOList(DdlProductVO ddlProductVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("ddlProductSqlMap.getDdlProductVOListCount",ddlProductVO);
		ddlProductVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("ddlProductSqlMap.getDdlProductVOList", 	ddlProductVO);
	}
	
	@Override
	public void insert(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("ddlProductSqlMap.insert", ddlProductVO);

	}
	
	@Override
	public void update(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("ddlProductSqlMap.update", ddlProductVO);

	}
	
	@Override
	public List<CountryVO> getDdlCountryList(String valueCode) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlProductSqlMap.getDdlCountryList", 	valueCode);
	}
}
