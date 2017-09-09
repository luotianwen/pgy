package com.kkgame.hz.service.impl;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.UserRegisterDAO;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.entities.ProjectVO;
import com.kkgame.hz.entities.UserRegisterVO;
import com.kkgame.hz.service.UserRegisterService;

public class UserRegisterServiceImpl implements UserRegisterService {

	private UserRegisterDAO userRegisterDAO;

	public UserRegisterDAO getUserRegisterDAO() {
		return userRegisterDAO;
	}

	public void setUserRegisterDAO(UserRegisterDAO userRegisterDAO) {
		this.userRegisterDAO = userRegisterDAO;
	}
	
	public List<CustomerVO> getCustomerList(CustomerVO customerVO)
			throws DatabaseException {

		return userRegisterDAO.getCustomerList(customerVO);
	}

	public List<ProjectVO> getProjectList(ProjectVO projectVO)
			throws DatabaseException {

		return userRegisterDAO.getProjectList(projectVO);
	}

	public List<UserRegisterVO> getUserRegisterVOListMonth(
			BillSearchVO billSearchVO) throws DatabaseException {

		return userRegisterDAO.getUserRegisterVOListMonth(billSearchVO);
	}

	public List<UserRegisterVO> getUserRegisterVOListDay(
			BillSearchVO billSearchVO) throws DatabaseException {

		return userRegisterDAO.getUserRegisterVOListDay(billSearchVO);
	}
	
	public void insert(UserRegisterVO userRegisterVO)
			throws DatabaseException {

		userRegisterDAO.insert(userRegisterVO);
	}
	
	public List<UserRegisterVO> getUserRegisterPercentVOListDay(
			BillSearchVO billSearchVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return userRegisterDAO.getUserRegisterPercentVOListDay(billSearchVO);
	}
	
	public void update(UserRegisterVO userRegisterVO) throws DatabaseException {
		// TODO Auto-generated method stub
		userRegisterDAO.update(userRegisterVO);
	}

	@Override
	public List<ProjectVO> getSubscribeProjectList(ProjectVO projectVO) throws DatabaseException {
		return userRegisterDAO.getSubscribeProjectList(projectVO);
	}
}
