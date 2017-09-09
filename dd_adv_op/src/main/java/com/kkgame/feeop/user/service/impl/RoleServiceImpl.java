package com.kkgame.feeop.user.service.impl;

import java.util.List;

import com.kkgame.feeop.user.bean.RoleVO;
import com.kkgame.feeop.user.dao.RoleDAO;
import com.kkgame.feeop.user.service.RoleService;
import com.kkgame.feeop.util.DatabaseException;

public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List<RoleVO> getRoleVOList() throws DatabaseException {
		return roleDAO.getRoleVOList();
	}

	public void delete(RoleVO roleVO) throws DatabaseException {
		roleDAO.delete(roleVO);
	}

	public void create(RoleVO roleVO) throws DatabaseException {
		roleDAO.create(roleVO);
	}

	public RoleVO getRoleVO(RoleVO roleVO) throws DatabaseException {
		return roleDAO.getRoleVO(roleVO);
	}

	public void update(RoleVO roleVO) throws DatabaseException {
		roleDAO.update(roleVO);
	}

	public List<RoleVO> getRoleVOListByUser(int id) throws DatabaseException {
		return roleDAO.getRoleVOListByUser(id);
	}

	public List<RoleVO> getProjectRoleVOList() throws DatabaseException {
		return roleDAO.getProjectRoleVOList();
	}

	public List<RoleVO> getBdRoleVOList() throws DatabaseException {
		return roleDAO.getBdRoleVOList();
	}
}
