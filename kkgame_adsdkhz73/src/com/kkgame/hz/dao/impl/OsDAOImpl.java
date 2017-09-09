package com.kkgame.hz.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.OsDAO;
import com.kkgame.hz.entities.OsVO;

public class OsDAOImpl extends SqlMapClientDaoSupport implements OsDAO {

	public void deleteOs(OsVO osVO) throws DatabaseException {
		getSqlMapClientTemplate().delete("osSqlMap.deleteOs", osVO);
	}

	@SuppressWarnings("unchecked")
	public List<OsVO> getOsList(OsVO osVO) throws DatabaseException {
		return (List<OsVO>) getSqlMapClientTemplate().queryForList(
				"osSqlMap.getAllOs", osVO);
	}

	public void insertOs(OsVO osVO) throws DatabaseException {
		getSqlMapClientTemplate().insert("osSqlMap.insertOs", osVO);
	}

	public void updateFlag(OsVO osVO) throws DatabaseException {

		getSqlMapClientTemplate().update("osSqlMap.updateOsFlag", osVO);
	}

	public OsVO getOs(OsVO osVO) throws DatabaseException {
		return (OsVO) getSqlMapClientTemplate().queryForObject(
				"osSqlMap.getOsById", osVO);
	}

	public void updateOs(OsVO osVO) throws DatabaseException {
		this.getSqlMapClientTemplate().update("osSqlMap.updateOs", osVO);
	}

	public List<OsVO> getOsVOList(OsVO osVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"osSqlMap.getOsListCount", osVO);
		osVO.getPage().setTotalRow(count);
		return this.getSqlMapClientTemplate().queryForList(
				"osSqlMap.getOsList", osVO);
	}

	public OsVO getOsByName(OsVO osVO) throws DatabaseException {
		return (OsVO) this.getSqlMapClientTemplate().queryForObject(
				"osSqlMap.getOsByName", osVO);
	}
}
