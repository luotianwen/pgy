package com.kkgame.feeop.user.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.user.bean.UserRoleVO;
import com.kkgame.feeop.user.dao.UserRoleDAO;
import com.kkgame.feeop.util.DatabaseException;

public class UserRoleDAOImpl extends SqlMapClientDaoSupport implements
		UserRoleDAO {

	public void create(UserRoleVO userRoleVO) throws DatabaseException {

		getSqlMapClientTemplate().insert("userRoleSqlMap.insert", userRoleVO);
	}

	public void delete(UserRoleVO userRoleVO) throws DatabaseException {

		getSqlMapClientTemplate().delete("userRoleSqlMap.delete", userRoleVO);

	}

	public UserRoleVO existsUserRole(UserRoleVO userRoleVO)
			throws DatabaseException {

		return (UserRoleVO) getSqlMapClientTemplate().queryForObject("userRoleSqlMap.existsUserRoleVO", userRoleVO);
	}

}
