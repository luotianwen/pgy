package com.kkgame.feeop.user.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.user.bean.*;
import com.kkgame.feeop.user.dao.*;
import com.kkgame.feeop.util.DatabaseException;

public  class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO{

	public List<UserVO> getUserVOList(UserVO userVO) throws DatabaseException {
		
		int count = (Integer) getSqlMapClientTemplate().queryForObject("userSqlMap.getUserVOListCount", userVO);
		userVO.getPage().setTotalRow(count);		
		return getSqlMapClientTemplate().queryForList("userSqlMap.getUserVOList", userVO);
	}

	public UserVO getUserVOByLoginId(String loginId) throws DatabaseException {

		return (UserVO) getSqlMapClientTemplate().queryForObject("userSqlMap.getUserVOByLoginId", loginId);
	}

	public void updateUserLastLogin(UserVO userVO) throws DatabaseException {

		getSqlMapClientTemplate().update("userSqlMap.updateUserLastLogin", userVO);
	}

	public int create(UserVO userVO) throws DatabaseException {

		return (Integer)getSqlMapClientTemplate().insert("userSqlMap.create",userVO);
	}	
	
	public UserVO getUserVOById(int id) throws DatabaseException {

		return (UserVO) getSqlMapClientTemplate().queryForObject("userSqlMap.getUserVOById", id);
	}
	
	public void update(UserVO userVO) throws DatabaseException {

		getSqlMapClientTemplate().update("userSqlMap.update", userVO);
	}
	
	public void delete(int id) throws DatabaseException {

		getSqlMapClientTemplate().delete("userSqlMap.delete", id);
	}

	public void updateStatus(UserVO userVO) throws DatabaseException {

		getSqlMapClientTemplate().update("userSqlMap.updateStatus", userVO);

	}

	public void updatePasswd(UserVO userVO) throws DatabaseException {

		getSqlMapClientTemplate().update("userSqlMap.updatePasswd", userVO);

	}
}
