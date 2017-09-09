package com.kkgame.feeop.user.dao;

import com.kkgame.feeop.user.bean.UserRoleVO;
import com.kkgame.feeop.util.DatabaseException;

public interface UserRoleDAO {

public UserRoleVO existsUserRole(UserRoleVO userRoleVO) throws DatabaseException;
	
	public void create(UserRoleVO userRoleVO) throws DatabaseException;
	
	public void delete(UserRoleVO userRoleVO) throws DatabaseException;

}
