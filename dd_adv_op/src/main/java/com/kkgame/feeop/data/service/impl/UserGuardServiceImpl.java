package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.UserGuardDAO;
import com.kkgame.feeop.data.service.UserGuardService;
import com.kkgame.feeop.util.DatabaseException;

public class UserGuardServiceImpl implements UserGuardService {

	private UserGuardDAO userGuardDAO;

	@Override
	public List<ProjectDataVO> getUserGuardDataList(SearchVO searchVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return userGuardDAO.getUserGuardDataList(searchVO);
	}
	
	@Override
	public List<ProjectDataVO> getUserGuardSuccDataList(SearchVO searchVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return userGuardDAO.getUserGuardSuccDataList(searchVO);
	}
	
	public UserGuardDAO getUserGuardDAO() {
		return userGuardDAO;
	}

	public void setUserGuardDAO(UserGuardDAO userGuardDAO) {
		this.userGuardDAO = userGuardDAO;
	}
}
