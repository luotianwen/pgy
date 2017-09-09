package com.kkgame.hz.service.impl;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.DdlDataDAO;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.DdlDataVO;
import com.kkgame.hz.service.DdlDataService;

public class DdlDataServiceImpl implements DdlDataService {

	
	private DdlDataDAO ddlDataDAO;

	@Override
	public List<DdlDataVO> getDdlDataVOListDay(BillSearchVO billSearchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlDataDAO.getDdlDataVOListDay(billSearchVO);
	}
	
	public DdlDataDAO getDdlDataDAO() {
		return ddlDataDAO;
	}

	public void setDdlDataDAO(DdlDataDAO ddlDataDAO) {
		this.ddlDataDAO = ddlDataDAO;
	}
	
	@Override
	public void insert(DdlDataVO ddlDataVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlDataDAO.insert(ddlDataVO);
	}
}
