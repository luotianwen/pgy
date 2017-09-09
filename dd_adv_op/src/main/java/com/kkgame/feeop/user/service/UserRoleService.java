package com.kkgame.feeop.user.service;

import com.kkgame.feeop.user.bean.UserRoleVO;
import com.kkgame.feeop.util.DatabaseException;

public interface UserRoleService {

	public UserRoleVO existsUserRole(UserRoleVO userRoleVO) throws DatabaseException;
	
	public void create(UserRoleVO userRoleVO) throws DatabaseException;
	
	public void delete(UserRoleVO userRoleVO) throws DatabaseException;
}
