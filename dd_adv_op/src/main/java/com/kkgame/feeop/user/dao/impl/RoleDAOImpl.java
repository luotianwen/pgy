package com.kkgame.feeop.user.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.user.bean.UserRoleVO;
import com.kkgame.feeop.user.dao.RoleDAO;
import com.kkgame.feeop.util.DatabaseException;

public class RoleDAOImpl extends SqlMapClientDaoSupport implements RoleDAO {

	public List<RoleVO> getRoleVOList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("roleSqlMap.getRoleVOList");
	}

	public void delete(RoleVO roleVO) throws DatabaseException {

		getSqlMapClientTemplate().delete("roleSqlMap.delete", roleVO);
	}

	public void create(RoleVO roleVO) throws DatabaseException {

		getSqlMapClientTemplate().insert("roleSqlMap.create",roleVO);
	}

	public RoleVO getRoleVO(RoleVO roleVO) throws DatabaseException {
		return (RoleVO) getSqlMapClientTemplate().queryForObject("roleSqlMap.getRoleVO", roleVO);
	}

	public void update(RoleVO roleVO) throws DatabaseException {

		getSqlMapClientTemplate().update("roleSqlMap.update", roleVO);
	}
	
	public void deleteRoleByUserRole(UserRoleVO userRoleVO) throws DatabaseException {

		getSqlMapClientTemplate().delete("roleSqlMap.deleteRoleByUserRole", userRoleVO);
	}
	
	public List<RoleVO> getRoleVOListByUser(int id) throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("roleSqlMap.getRoleVOListByUser", id);
	}

	public List<RoleVO> getProjectRoleVOList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("roleSqlMap.getProjectRoleVOList");
	}

	public List<RoleVO> getBdRoleVOList() throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("roleSqlMap.getBdRoleVOList");
	}
}
