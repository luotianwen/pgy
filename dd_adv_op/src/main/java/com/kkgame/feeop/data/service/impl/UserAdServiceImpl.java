package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.UserAdVO;
import com.kkgame.feeop.data.dao.UserAdDAO;
import com.kkgame.feeop.data.service.UserAdService;
import com.kkgame.feeop.util.DatabaseException;

public class UserAdServiceImpl implements UserAdService {

	private UserAdDAO userAdDAO;

	@Override
	public List<UserAdVO> getUserAdVOList(SearchVO searchVO) throws DatabaseException {

		return userAdDAO.getUserAdVOList(searchVO);
	}
	
	public UserAdDAO getUserAdDAO() {
		return userAdDAO;
	}

	public void setUserAdDAO(UserAdDAO userAdDAO) {
		this.userAdDAO = userAdDAO;
	}
}
