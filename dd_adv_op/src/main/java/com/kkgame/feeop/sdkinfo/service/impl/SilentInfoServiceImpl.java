package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SilentInfoVO;
import com.kkgame.feeop.sdkinfo.dao.SilentInfoDAO;
import com.kkgame.feeop.sdkinfo.service.SilentInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class SilentInfoServiceImpl implements SilentInfoService {

	private SilentInfoDAO silentInfoDAO;

	public SilentInfoDAO getSilentInfoDAO() {
		return silentInfoDAO;
	}

	public void setSilentInfoDAO(SilentInfoDAO silentInfoDAO) {
		this.silentInfoDAO = silentInfoDAO;
	}

	@Override
	public SilentInfoVO getSilentInfoVO(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return silentInfoDAO.getSilentInfoVO(silentInfoVO);
	}

	@Override
	public List<SilentInfoVO> getSilentInfoVOList(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return silentInfoDAO.getSilentInfoVOList(silentInfoVO);
	}

	@Override
	public List<SilentInfoVO> getAllSilentInfoVOList(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return silentInfoDAO.getAllSilentInfoVOList(silentInfoVO);
	}

	@Override
	public void insert(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		silentInfoDAO.insert(silentInfoVO);
	}

	@Override
	public void update(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		silentInfoDAO.update(silentInfoVO);
	}
	
	@Override
	public void delete(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		silentInfoDAO.delete(silentInfoVO);
	}
}
