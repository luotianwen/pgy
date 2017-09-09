package com.kkgame.feeop.user.service.impl;

import com.kkgame.feeop.user.bean.UserRoleVO;
import com.kkgame.feeop.user.dao.UserRoleDAO;
import com.kkgame.feeop.user.service.UserRoleService;
import com.kkgame.feeop.util.DatabaseException;

public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDAO userRoleDAO;
	
	public UserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public void create(UserRoleVO userRoleVO) throws DatabaseException {
		userRoleDAO.create(userRoleVO);
	}

	public void delete(UserRoleVO userRoleVO) throws DatabaseException {
		userRoleDAO.delete(userRoleVO);
	}

	public UserRoleVO existsUserRole(UserRoleVO userRoleVO)
			throws DatabaseException {
		return userRoleDAO.existsUserRole(userRoleVO);
	}
}
