package com.kkgame.feeop.customer.service.impl;

import com.kkgame.feeop.customer.bean.DdlVO;
import com.kkgame.feeop.customer.dao.DdlDAO;
import com.kkgame.feeop.customer.service.DdlService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class DdlServiceImpl implements DdlService {

	private DdlDAO ddlDAO;

	public DdlDAO getDdlDAO() {
		return ddlDAO;
	}

	public void setDdlDAO(DdlDAO ddlDAO) {
		this.ddlDAO = ddlDAO;
	}
	
	@Override
	public DdlVO getDdlVO(DdlVO ddlVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlDAO.getDdlVO(ddlVO);
	}
	
	@Override
	public void update(DdlVO ddlVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlDAO.update(ddlVO);
	}

	@Override
	public List<DdlVO> getDdlVOClickList(DdlVO ddlVO) throws DatabaseException {
		return ddlDAO.getDdlVOClickList(ddlVO);
	}

}
