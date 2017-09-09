package com.kkgame.feeop.user.dao;
import java.util.List;

import com.kkgame.feeop.user.bean.UserVO;
import com.kkgame.feeop.util.DatabaseException;

public interface UserDAO {

	public List<UserVO> getUserVOList(UserVO userVO) throws DatabaseException;

	public UserVO getUserVOByLoginId(String loginId) throws DatabaseException;

	public void updateUserLastLogin(UserVO userVO) throws DatabaseException;

	public int create(UserVO userVO) throws DatabaseException;
	
	public UserVO getUserVOById(int id) throws DatabaseException;

	public void update(UserVO userVO) throws DatabaseException;

	public void delete(int id) throws DatabaseException;

	public void updateStatus(UserVO userVO) throws DatabaseException;

	public void updatePasswd(UserVO userVO) throws DatabaseException;

}
