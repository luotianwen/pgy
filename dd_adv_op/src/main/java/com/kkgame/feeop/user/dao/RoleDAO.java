package com.kkgame.feeop.user.dao;

import java.util.List;

import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.util.DatabaseException;

public interface RoleDAO {

	public List<RoleVO> getRoleVOList() throws DatabaseException;

	public void delete(RoleVO roleVO) throws DatabaseException;

	public void create(RoleVO roleVO) throws DatabaseException;
	
	public RoleVO getRoleVO(RoleVO roleVO) throws DatabaseException;
	
	public void update(RoleVO roleVO) throws DatabaseException;

	public List<RoleVO> getRoleVOListByUser(int id) throws DatabaseException;
		
	public List<RoleVO> getProjectRoleVOList() throws DatabaseException;

	public List<RoleVO> getBdRoleVOList() throws DatabaseException;

}
