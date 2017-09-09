package com.kkgame.feeop.ddl.service.impl;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.ddl.bean.DomainVO;
import com.kkgame.feeop.ddl.dao.DdlProjectDAO;
import com.kkgame.feeop.ddl.service.DdlProjectService;
import com.kkgame.feeop.util.DatabaseException;

public class DdlProjectServiceImpl implements DdlProjectService {

	private DdlProjectDAO ddlProjectDAO;

	@Override
	public List<DdlProjectVO> getAllDdlProjectVO(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProjectDAO.getAllDdlProjectVO(ddlProjectVO);
	}
	
	@Override
	public DdlProjectVO getDdlProjectVO(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProjectDAO.getDdlProjectVO(ddlProjectVO);
	}
	
	@Override
	public List<DdlProjectVO> getDdlProjectVOList(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProjectDAO.getDdlProjectVOList(ddlProjectVO);
	}
	
	@Override
	public void insert(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlProjectDAO.insert(ddlProjectVO);
	}

	@Override
	public void update(DdlProjectVO ddlProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlProjectDAO.update(ddlProjectVO);
	}
	
	public DdlProjectDAO getDdlProjectDAO() {
		return ddlProjectDAO;
	}

	public void setDdlProjectDAO(DdlProjectDAO ddlProjectDAO) {
		this.ddlProjectDAO = ddlProjectDAO;
	}

	@Override
	public List<DomainVO> getAllDomainVO() throws DatabaseException {
		return ddlProjectDAO.getAllDomainVO();
	}

	@Override
	public DomainVO getDomainVO(int id) throws DatabaseException {
		return ddlProjectDAO.getDomainVO(id);
	}

	@Override
	public void insertDomain(DomainVO domainVO) throws DatabaseException {
		ddlProjectDAO.insertDomain(domainVO);
	}

	@Override
	public void updateDomain(DomainVO domainVO) throws DatabaseException {
		ddlProjectDAO.updateDomain(domainVO);
	}

}
