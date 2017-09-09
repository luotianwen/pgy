package com.kkgame.feeop.user.dao;

import com.kkgame.feeop.user.bean.RoleResVO;
import com.kkgame.feeop.util.DatabaseException;

public interface RoleResDAO {

	public RoleResVO existRoleRes(RoleResVO roleResVO) throws DatabaseException;
	
	public void saveRoleRes(RoleResVO roleResVO) throws DatabaseException;
	
	public void deleteRoleRes(RoleResVO roleResVO) throws DatabaseException;
}
