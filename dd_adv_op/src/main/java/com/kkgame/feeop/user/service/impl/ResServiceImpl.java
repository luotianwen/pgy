package com.kkgame.feeop.user.service.impl;

import java.util.List;

import com.kkgame.feeop.user.bean.ResVO;
import com.kkgame.feeop.user.dao.ResDAO;
import com.kkgame.feeop.user.service.ResService;
import com.kkgame.feeop.util.DatabaseException;

public class ResServiceImpl implements ResService {

	private ResDAO resDAO;

	public ResDAO getResDAO() {
		return resDAO;
	}

	public void setResDAO(ResDAO resDAO) {
		this.resDAO = resDAO;
	}

	public List<ResVO> getResByParent(int pid) throws DatabaseException {
		return resDAO.getResByParent(pid);
	}

	public void create(ResVO resVO) throws DatabaseException {
		resDAO.create(resVO);
	}

	public void delete(ResVO resVO) throws DatabaseException {
		resDAO.delete(resVO);
	}

	public ResVO getRes(ResVO resVO) throws DatabaseException {
		return resDAO.getRes(resVO);
	}

	public void update(ResVO resVO) throws DatabaseException {
		resDAO.update(resVO);
	}

	public List<ResVO> getResByRole(int id) throws DatabaseException {
		return resDAO.getResByRole(id);
	}	
}
