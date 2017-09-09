package com.kkgame.hz.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.PortalUserDAO;
import com.kkgame.hz.entities.PortalUserVO;

public class PortalUserDAOImpl extends SqlMapClientDaoSupport implements
		PortalUserDAO {

	private static Log logger = LogFactory.getLog(PortalUserDAOImpl.class);

	public void delete(int id) throws DatabaseException {
		getSqlMapClientTemplate().update("portalUserSqlMap.deletePortalUser",
				id);
	}

	public List<PortalUserVO> getPortalUserList(PortalUserVO portalUserVO)
			throws DatabaseException {
		return (List<PortalUserVO>) getSqlMapClientTemplate().queryForList(
				"portalUserSqlMap.getPortalUserList", portalUserVO);
	}

	public PortalUserVO getPortalUserById(int id) throws DatabaseException {
		return (PortalUserVO) getSqlMapClientTemplate().queryForObject(
				"portalUserSqlMap.getPortalUserById", id);
	}

	public void insert(PortalUserVO portalUserVO) throws DatabaseException {
		getSqlMapClientTemplate().update("portalUserSqlMap.insert",
				portalUserVO);
	}

	public void update(PortalUserVO portalUserVO) throws DatabaseException {
		getSqlMapClientTemplate().update("portalUserSqlMap.updatePortalUser",
				portalUserVO);
	}

	public List<PortalUserVO> getPortalUserByCriteria(PortalUserVO portalUserVO)
			throws DatabaseException {
		return (List<PortalUserVO>) getSqlMapClientTemplate().queryForList(
				"portalUserSqlMap.getPortalUserByCriteria", portalUserVO);
	}

	public PortalUserVO getPortalUserByLoginId(String loginId)
			throws DatabaseException {
		return (PortalUserVO) getSqlMapClientTemplate().queryForObject(
				"portalUserSqlMap.getPortalUserByLoginId", loginId);
	}

	public void updatePasswd(PortalUserVO portalUserVO)
			throws DatabaseException {
		getSqlMapClientTemplate().update("portalUserSqlMap.updatePasswd",
				portalUserVO);
	}

	public void updateUserLastLogin(PortalUserVO portalUserVO)
			throws DatabaseException {
		getSqlMapClientTemplate().update(
				"portalUserSqlMap.updateUserLastLogin", portalUserVO);
	}
}
