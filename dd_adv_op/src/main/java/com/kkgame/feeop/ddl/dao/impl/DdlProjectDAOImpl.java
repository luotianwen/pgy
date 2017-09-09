package com.kkgame.feeop.ddl.dao.impl;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DomainVO;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.ddl.dao.DdlProjectDAO;
import com.kkgame.feeop.util.DatabaseException;

public class DdlProjectDAOImpl extends SqlMapClientDaoSupport implements DdlProjectDAO {

	@Override
	public List<DdlProjectVO> getAllDdlProjectVO(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlProjectSqlMap.getAllDdlProjectVO", ddlProjectVO);
	}
	
	@Override
	public DdlProjectVO getDdlProjectVO(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (DdlProjectVO) getSqlMapClientTemplate().queryForObject("ddlProjectSqlMap.getDdlProjectVO", ddlProjectVO);
	}
	
	@Override
	public List<DdlProjectVO> getDdlProjectVOList(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("ddlProjectSqlMap.getDdlProjectVOListCount",ddlProjectVO);
		ddlProjectVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("ddlProjectSqlMap.getDdlProjectVOList", 	ddlProjectVO);
	}
	
	@Override
	public void insert(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("ddlProjectSqlMap.insert", ddlProjectVO);

	}

	@Override
	public void update(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("ddlProjectSqlMap.update", ddlProjectVO);

	}

	@Override
	public List<DomainVO> getAllDomainVO() throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("ddlProjectSqlMap.getAllDomainVO");
	}

	@Override
	public DomainVO getDomainVO(int id) throws DatabaseException {
		return (DomainVO) getSqlMapClientTemplate().queryForObject("ddlProjectSqlMap.getDomainVO", id);
	}

	@Override
	public void insertDomain(DomainVO domainVO) throws DatabaseException {
		getSqlMapClientTemplate().insert("ddlProjectSqlMap.insertDomain", domainVO);
	}

	@Override
	public void updateDomain(DomainVO domainVO) throws DatabaseException {
		getSqlMapClientTemplate().update("ddlProjectSqlMap.updateDomain", domainVO);
	}

}
