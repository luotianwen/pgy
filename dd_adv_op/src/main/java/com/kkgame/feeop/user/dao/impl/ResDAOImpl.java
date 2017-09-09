package com.kkgame.feeop.user.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.user.bean.ResVO;
import com.kkgame.feeop.user.dao.ResDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ResDAOImpl extends SqlMapClientDaoSupport implements ResDAO {

	public List<ResVO> getResByParent(int pid) throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("resSqlMap.getResByParent", pid);
	}

	public void create(ResVO resVO) throws DatabaseException {

		getSqlMapClientTemplate().insert("resSqlMap.create", resVO);
	}

	public void delete(ResVO resVO) throws DatabaseException {

		getSqlMapClientTemplate().delete("resSqlMap.delete", resVO);
	}

	public ResVO getRes(ResVO resVO) throws DatabaseException {

		return (ResVO) getSqlMapClientTemplate().queryForObject("resSqlMap.getRes", resVO);
	}

	public void update(ResVO resVO) throws DatabaseException {

		getSqlMapClientTemplate().update("resSqlMap.update", resVO);
	}

	public List<ResVO> getResByRole(int id) throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("resSqlMap.getResByRole", id);
	}
}
