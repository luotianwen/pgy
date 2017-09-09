package com.kkgame.feeop.user.service.impl;

import com.kkgame.feeop.user.bean.RoleResVO;
import com.kkgame.feeop.user.dao.RoleResDAO;
import com.kkgame.feeop.user.service.RoleResService;
import com.kkgame.feeop.util.DatabaseException;

public class RoleResServiceImpl implements RoleResService {

	private RoleResDAO roleResDAO;
	
	public RoleResDAO getRoleResDAO() {
		return roleResDAO;
	}

	public void setRoleResDAO(RoleResDAO roleResDAO) {
		this.roleResDAO = roleResDAO;
	}

	public void deleteRoleRes(RoleResVO roleResVO) throws DatabaseException {
		roleResDAO.deleteRoleRes(roleResVO);
	}

	public RoleResVO existRoleRes(RoleResVO roleResVO) throws DatabaseException {
		return roleResDAO.existRoleRes(roleResVO);
	}

	public void saveRoleRes(RoleResVO roleResVO) throws DatabaseException {
		roleResDAO.saveRoleRes(roleResVO);
	}
}
