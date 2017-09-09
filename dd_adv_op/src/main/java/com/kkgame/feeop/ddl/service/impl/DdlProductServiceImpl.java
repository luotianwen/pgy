package com.kkgame.feeop.ddl.service.impl;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.ddl.dao.DdlProductDAO;
import com.kkgame.feeop.ddl.service.DdlProductService;
import com.kkgame.feeop.tag.bean.CountryVO;
import com.kkgame.feeop.util.DatabaseException;

public class DdlProductServiceImpl implements DdlProductService {

	private DdlProductDAO ddlProductDAO;

	@Override
	public List<DdlProductVO> getAllDdlProduct(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProductDAO.getAllDdlProduct(ddlProductVO);
	}
	
	@Override
	public DdlProductVO getDdlProductVO(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProductDAO.getDdlProductVO(ddlProductVO);
	}
	
	@Override
	public List<DdlProductVO> getDdlProductVOList(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProductDAO.getDdlProductVOList(ddlProductVO);
	}
	
	@Override
	public void insert(DdlProductVO ddlProductVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlProductDAO.insert(ddlProductVO);
	}
	
	public void update(DdlProductVO ddlProductVO) throws DatabaseException {
		ddlProductDAO.update(ddlProductVO);
	}
	
	@Override
	public List<CountryVO> getDdlCountryList(String valueCode) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlProductDAO.getDdlCountryList(valueCode);
	}
	
	public DdlProductDAO getDdlProductDAO() {
		return ddlProductDAO;
	}

	public void setDdlProductDAO(DdlProductDAO ddlProductDAO) {
		this.ddlProductDAO = ddlProductDAO;
	}
}
