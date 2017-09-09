package com.kkgame.feeop.user.service.impl;

import java.util.List;

import com.kkgame.feeop.user.bean.*;
import com.kkgame.feeop.user.dao.*;
import com.kkgame.feeop.user.service.*;
import com.kkgame.feeop.util.DatabaseException;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO;

	public List<UserVO> getUserVOList(UserVO userVO) throws DatabaseException {
		return userDAO.getUserVOList(userVO);
	}

	public UserVO getUserVOByLoginId(String loginId) throws DatabaseException {
		return userDAO.getUserVOByLoginId(loginId);
	}

	public void updateUserLastLogin(UserVO userVO) throws DatabaseException {
		userDAO.updateUserLastLogin(userVO);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public int create(UserVO userVO) throws DatabaseException {
		return userDAO.create(userVO);
	}

	public UserVO getUserVOById(int id) throws DatabaseException {
		return userDAO.getUserVOById(id);
	}

	public void update(UserVO userVO) throws DatabaseException {
		userDAO.update(userVO);
	}

	public void delete(int id) throws DatabaseException {
		userDAO.delete(id);
	}

	public void updateStatus(UserVO userVO) throws DatabaseException {
		userDAO.updateStatus(userVO);
	}

	public void updatePasswd(UserVO userVO) throws DatabaseException {
		userDAO.updatePasswd(userVO);
	}
}


	
