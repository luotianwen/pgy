package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.DdlDetailVO;
import com.kkgame.feeop.detail.dao.DdlDetailDAO;
import com.kkgame.feeop.detail.service.DdlDetailService;
import com.kkgame.feeop.util.DatabaseException;

public class DdlDetailServiceImpl implements DdlDetailService {

	private DdlDetailDAO ddlDetailDAO;
	
	public DdlDetailDAO getDdlDetailDAO() {
		return ddlDetailDAO;
	}

	public void setDdlDetailDAO(DdlDetailDAO ddlDetailDAO) {
		this.ddlDetailDAO = ddlDetailDAO;
	}

	@Override
	public List<DdlDetailVO> getDdlAdjustDetailVOList(DdlDetailVO ddlDetailVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlDetailDAO.getDdlAdjustDetailVOList(ddlDetailVO);
	}
	
	@Override
	public List<DdlDetailVO> getDdlSdkDetailVOList(DdlDetailVO ddlDetailVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlDetailDAO.getDdlSdkDetailVOList(ddlDetailVO);
	}
	
	
}
