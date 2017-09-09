package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.DdlDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.DdlDataDAO;
import com.kkgame.feeop.data.service.DdlDataService;
import com.kkgame.feeop.util.DatabaseException;

public class DdlDataServiceImpl implements DdlDataService {
	
	private DdlDataDAO ddlDataDAO;

	@Override
	public List<DdlDataVO> getDdlDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlDataDAO.getDdlDataVOList(searchVO);
	}
	
	@Override
	public List<DdlDataVO> getDdlSaleDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlDataDAO.getDdlSaleDataVOList(searchVO);
	}
	
	
	public DdlDataDAO getDdlDataDAO() {
		return ddlDataDAO;
	}

	public void setDdlDataDAO(DdlDataDAO ddlDataDAO) {
		this.ddlDataDAO = ddlDataDAO;
	}
	
}
