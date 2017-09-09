package com.kkgame.feeop.user.service;

import com.kkgame.feeop.user.bean.RoleResVO;
import com.kkgame.feeop.util.DatabaseException;

public interface RoleResService {

public RoleResVO existRoleRes(RoleResVO roleResVO) throws DatabaseException;
	
	public void saveRoleRes(RoleResVO roleResVO) throws DatabaseException;
	
	public void deleteRoleRes(RoleResVO roleResVO) throws DatabaseException;

}
