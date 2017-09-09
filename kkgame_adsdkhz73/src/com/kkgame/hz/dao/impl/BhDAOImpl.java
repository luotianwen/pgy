package com.kkgame.hz.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.BhDAO;
import com.kkgame.hz.entities.BhVO;

public class BhDAOImpl extends SqlMapClientDaoSupport implements BhDAO {

	private static Log logger = LogFactory.getLog(BhDAOImpl.class);

	public void delete(int id) throws DatabaseException {
		getSqlMapClientTemplate().update("bhSqlMap.deleteBh", id);
	}

	public List<BhVO> getBhList(BhVO bhVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"bhSqlMap.getBhListCount", bhVO);
		bhVO.getPage().setTotalRow(count);
		return (List<BhVO>) getSqlMapClientTemplate().queryForList(
				"bhSqlMap.getBhList", bhVO);
	}

	public BhVO getBhById(int id) throws DatabaseException {
		return (BhVO) getSqlMapClientTemplate().queryForObject(
				"bhSqlMap.getBhById", id);
	}

	public int insert(BhVO bhVO) throws DatabaseException {
		Object id = getSqlMapClientTemplate().insert("bhSqlMap.insert", bhVO);
		return Integer.parseInt(id.toString());
	}

	public void update(BhVO BhVO) throws DatabaseException {
		getSqlMapClientTemplate().update("bhSqlMap.updateBh", BhVO);
	}

	public List<BhVO> getBhByCriteria(BhVO bhVO) throws DatabaseException {
		return (List<BhVO>) getSqlMapClientTemplate().queryForList(
				"bhSqlMap.getBhByCriteria", bhVO);
	}

}
