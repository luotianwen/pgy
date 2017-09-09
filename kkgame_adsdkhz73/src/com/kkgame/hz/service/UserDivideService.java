package com.kkgame.hz.service;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.SpecialDataVO;
import com.kkgame.hz.entities.UserDivideVO;
import com.kkgame.hz.entities.UserRegisterVO;

public interface UserDivideService {

	public List<UserDivideVO> getUserDivideVOList(BillSearchVO billSearchVO) throws DatabaseException;
	
	public List<UserDivideVO> getUserDivideVOInterfaceList(BillSearchVO billSearchVO) throws DatabaseException;

	public List<UserDivideVO> getUserDivideVOMenLongList(
			BillSearchVO billSearchVO) throws DatabaseException;

	public List<UserDivideVO> getUserDivideVOListDay(BillSearchVO billSearchVO) throws DatabaseException;

	public void createRegisterData(UserDivideVO divideVO) throws DatabaseException;

	public void createDivideData(UserDivideVO divideVO)throws DatabaseException;

	public List<SpecialDataVO> getSpecialDataVOListDay(BillSearchVO billSearchVO) throws DatabaseException;

	public void insert(UserDivideVO userDivideVO) throws DatabaseException;

	List<SpecialDataVO> getSpecialsubscribeDataVOListDay(BillSearchVO billSearchVO)throws DatabaseException;

	void insertSubscribe(SpecialDataVO u)throws DatabaseException;
}
