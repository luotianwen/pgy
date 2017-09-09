package com.kkgame.hz.service.impl;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.UserDivideDAO;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.SpecialDataVO;
import com.kkgame.hz.entities.UserDivideVO;
import com.kkgame.hz.service.UserDivideService;

public class UserDivideServiceImpl implements UserDivideService {

	private UserDivideDAO userDivideDAO;

	public UserDivideDAO getUserDivideDAO() {
		return userDivideDAO;
	}

	public void setUserDivideDAO(UserDivideDAO userDivideDAO) {
		this.userDivideDAO = userDivideDAO;
	}

	public List<UserDivideVO> getUserDivideVOList(BillSearchVO billSearchVO)
			throws DatabaseException {

		return userDivideDAO.getUserDivideVOList(billSearchVO);
	}

	public List<UserDivideVO> getUserDivideVOInterfaceList(
			BillSearchVO billSearchVO) throws DatabaseException {

		return userDivideDAO.getUserDivideVOInterfaceList(billSearchVO);
	}

	public List<UserDivideVO> getUserDivideVOMenLongList(
			BillSearchVO billSearchVO) throws DatabaseException {

		return userDivideDAO.getUserDivideVOMenLongList(billSearchVO);
	}

	public List<UserDivideVO> getUserDivideVOListDay(BillSearchVO billSearchVO)
			throws DatabaseException {
		return userDivideDAO.getUserDivideVOListDay(billSearchVO);
	}
	
	public void createDivideData(UserDivideVO divideVO)
			throws DatabaseException {

		userDivideDAO.createDivideData(divideVO);
	}
	public void createRegisterData(UserDivideVO divideVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		userDivideDAO.createRegisterData(divideVO);
	}
	
	public List<SpecialDataVO> getSpecialDataVOListDay(BillSearchVO billSearchVO)
			throws DatabaseException {

		return userDivideDAO.getSpecialDataVOListDay(billSearchVO);
	}
	
	@Override
	public void insert(UserDivideVO userDivideVO) throws DatabaseException {

		userDivideDAO.insert(userDivideVO);
	}

	@Override
	public List<SpecialDataVO> getSpecialsubscribeDataVOListDay(BillSearchVO billSearchVO) throws DatabaseException {
		return userDivideDAO.getSpecialsubscribeDataVOListDay(billSearchVO);
	}

	@Override
	public void insertSubscribe(SpecialDataVO u) throws DatabaseException {
		userDivideDAO.insertSubscribe(u);
	}
}
