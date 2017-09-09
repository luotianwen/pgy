package com.kkgame.hz.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.BdDAO;
import com.kkgame.hz.entities.BdVO;

public class BdDAOImpl extends SqlMapClientDaoSupport implements BdDAO {

	private static Log logger = LogFactory.getLog(BdDAOImpl.class);

	public void delete(int id) throws DatabaseException {
		getSqlMapClientTemplate().update("bdSqlMap.deleteBd", id);
	}

	public List<BdVO> getBdList(BdVO bdVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"bdSqlMap.getBdListCount", bdVO);
		bdVO.getPage().setTotalRow(count);
		return (List<BdVO>) getSqlMapClientTemplate().queryForList(
				"bdSqlMap.getBdList", bdVO);
	}

	public BdVO getBdById(int id) throws DatabaseException {
		return (BdVO) getSqlMapClientTemplate().queryForObject(
				"bdSqlMap.getBdById", id);
	}

	public int insert(BdVO bdVO) throws DatabaseException {
		Object id = getSqlMapClientTemplate().insert("bdSqlMap.insert", bdVO);
		return Integer.parseInt(id.toString());
	}

	public void update(BdVO BdVO) throws DatabaseException {
		getSqlMapClientTemplate().update("bdSqlMap.updateBd", BdVO);
	}

	public List<BdVO> getBdByCriteria(BdVO bdVO) throws DatabaseException {
		return (List<BdVO>) getSqlMapClientTemplate().queryForList(
				"bdSqlMap.getBdByCriteria", bdVO);
	}

	public List<BdVO> getAllBdList(BdVO bdVO) throws DatabaseException {
		return (List<BdVO>) getSqlMapClientTemplate().queryForList(
				"bdSqlMap.getAllBdList", bdVO);
	}

	public BdVO getBdByName(BdVO bdVO) throws DatabaseException {
		return (BdVO) this.getSqlMapClientTemplate().queryForObject(
				"bdSqlMap.getBdByName", bdVO);
	}

	public List<BdVO> getBdVOList(BdVO bdVO) throws DatabaseException {
		return (List<BdVO>) getSqlMapClientTemplate().queryForList(
				"bdSqlMap.getBdVOList", bdVO);
	}

}
