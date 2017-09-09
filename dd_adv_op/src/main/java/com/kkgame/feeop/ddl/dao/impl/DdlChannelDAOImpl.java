package com.kkgame.feeop.ddl.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.dao.DdlChannelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class DdlChannelDAOImpl extends SqlMapClientDaoSupport implements DdlChannelDAO {

	
	@Override
	public List<DdlChannelVO> getAllDdlChannel(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlChannelSqlMap.getAllDdlChannel", 	ddlChannelVO);
	}
	
	
	@Override
	public DdlChannelVO getDdlChannelVO(DdlChannelVO ddlChannelVO) throws DatabaseException {
		return (DdlChannelVO) getSqlMapClientTemplate().queryForObject("ddlChannelSqlMap.getDdlChannelVO", ddlChannelVO);
	}
	
	@Override
	public List<DdlChannelVO> getDdlChannelVOList(DdlChannelVO ddlChannelVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("ddlChannelSqlMap.getDdlChannelVOListCount",ddlChannelVO);
		ddlChannelVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("ddlChannelSqlMap.getDdlChannelVOList", 	ddlChannelVO);
	}
	
	@Override
	public void insert(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("ddlChannelSqlMap.insert", ddlChannelVO);
	}
	
	@Override
	public void update(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("ddlChannelSqlMap.update", ddlChannelVO);

	}
}
