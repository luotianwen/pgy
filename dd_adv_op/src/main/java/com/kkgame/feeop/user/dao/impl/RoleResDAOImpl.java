package com.kkgame.feeop.user.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.user.bean.RoleResVO;
import com.kkgame.feeop.user.dao.RoleResDAO;
import com.kkgame.feeop.util.DatabaseException;

public class RoleResDAOImpl extends SqlMapClientDaoSupport implements
		RoleResDAO {

	public void deleteRoleRes(RoleResVO roleResVO) throws DatabaseException {
		
		getSqlMapClientTemplate().delete("roleResSqlMap.delete", roleResVO);
	}

	public RoleResVO existRoleRes(RoleResVO roleResVO) throws DatabaseException {

		return (RoleResVO) getSqlMapClientTemplate().queryForObject("roleResSqlMap.existRoleRes", roleResVO);
	}

	public void saveRoleRes(RoleResVO roleResVO) throws DatabaseException {
		
		getSqlMapClientTemplate().insert("roleResSqlMap.insert", roleResVO);
	}

}
