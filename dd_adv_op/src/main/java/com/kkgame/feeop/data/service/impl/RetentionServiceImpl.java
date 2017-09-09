package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.RetentionDAO;
import com.kkgame.feeop.data.service.RetentionService;
import com.kkgame.feeop.util.DatabaseException;

public class RetentionServiceImpl implements RetentionService {

	private RetentionDAO retentionDAO;

	@Override
	public List<RetentionVO> getRetentionVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return retentionDAO.getRetentionVOList(searchVO);
	}
	
	@Override
	public List<RetentionVO> getTotalRetentionVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return retentionDAO.getTotalRetentionVOList(searchVO);
	}
	
	@Override
	public List<RetentionVO> getExportRetentionVOList(SearchVO searchVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return retentionDAO.getExportRetentionVOList(searchVO);
	}
	
	public RetentionDAO getRetentionDAO() {
		return retentionDAO;
	}

	public void setRetentionDAO(RetentionDAO retentionDAO) {
		this.retentionDAO = retentionDAO;
	}
}
