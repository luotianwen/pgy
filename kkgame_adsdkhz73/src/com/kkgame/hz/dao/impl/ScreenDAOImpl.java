package com.kkgame.hz.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.ScreenDAO;
import com.kkgame.hz.entities.ScreenVO;

public class ScreenDAOImpl extends SqlMapClientDaoSupport implements ScreenDAO {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(ScreenDAOImpl.class);

	public void delete(ScreenVO screenVO) throws DatabaseException {
		getSqlMapClientTemplate().delete("screenSqlMap.deleteScreen", screenVO);
	}

	@SuppressWarnings("unchecked")
	public List<ScreenVO> getAllScreenList(ScreenVO screenVO)
			throws DatabaseException {
		return (List<ScreenVO>) getSqlMapClientTemplate().queryForList(
				"screenSqlMap.getAllScreen", screenVO);
	}

	public void insert(ScreenVO screenVO) throws DatabaseException {
		getSqlMapClientTemplate().insert("screenSqlMap.insertScreen", screenVO);
	}

	public ScreenVO getScreen(ScreenVO screenVO) throws DatabaseException {
		return (ScreenVO) getSqlMapClientTemplate().queryForObject(
				"screenSqlMap.getScreen", screenVO);
	}
}
