package com.kkgame.feeop.ddl.service.impl;

import com.kkgame.feeop.ddl.dao.DdlChannelDAO;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.ddl.service.DdlChannelService;
import com.kkgame.feeop.util.DatabaseException;

public class DdlChannelServiceImpl implements DdlChannelService {

	private DdlChannelDAO ddlChannelDAO;

	public DdlChannelDAO getDdlChannelDAO() {
		return ddlChannelDAO;
	}

	public void setDdlChannelDAO(DdlChannelDAO ddlChannelDAO) {
		this.ddlChannelDAO = ddlChannelDAO;
	}
	
	@Override
	public List<DdlChannelVO> getAllDdlChannel(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlChannelDAO.getAllDdlChannel(ddlChannelVO);
	}
	
	@Override
	public DdlChannelVO getDdlChannelVO(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlChannelDAO.getDdlChannelVO(ddlChannelVO);
	}
	
	@Override
	public List<DdlChannelVO> getDdlChannelVOList(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return ddlChannelDAO.getDdlChannelVOList(ddlChannelVO);
	}
	
	@Override
	public void insert(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlChannelDAO.insert(ddlChannelVO);
	}
	
	@Override
	public void update(DdlChannelVO ddlChannelVO) throws DatabaseException {
		// TODO Auto-generated method stub
		ddlChannelDAO.update(ddlChannelVO);
	}
	
}
