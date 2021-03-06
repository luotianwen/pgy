package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.PortalUserVO;

public interface PortalUserDAO {
	public void insert(PortalUserVO portalUserVO) throws DatabaseException;

	public void update(PortalUserVO portalUserVO) throws DatabaseException;

	public void delete(int id) throws DatabaseException;

	public PortalUserVO getPortalUserById(int id) throws DatabaseException;

	public List<PortalUserVO> getPortalUserList(PortalUserVO portalUserVO)
			throws DatabaseException;

	public List<PortalUserVO> getPortalUserByCriteria(PortalUserVO portalUserVO)
			throws DatabaseException;

	public PortalUserVO getPortalUserByLoginId(String loginId)
			throws DatabaseException;

	public void updatePasswd(PortalUserVO portalUserVO)
			throws DatabaseException;

	public void updateUserLastLogin(PortalUserVO portalUserVO)
			throws DatabaseException;
}
