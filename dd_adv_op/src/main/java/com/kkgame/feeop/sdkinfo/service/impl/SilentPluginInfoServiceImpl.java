package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.bean.SilentPluginInfoVO;
import com.kkgame.feeop.sdkinfo.dao.ApkInfoDAO;
import com.kkgame.feeop.sdkinfo.dao.SilentPluginInfoDAO;
import com.kkgame.feeop.sdkinfo.service.ApkInfoService;
import com.kkgame.feeop.sdkinfo.service.SilentPluginInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class SilentPluginInfoServiceImpl implements SilentPluginInfoService {

	
	private SilentPluginInfoDAO silentPluginInfoDAO;

	@Override
	public List<SilentPluginInfoVO> getAllSilentPluginInfoVOList(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return silentPluginInfoDAO.getAllSilentPluginInfoVOList(silentPluginInfoVO);
	}
	
	@Override
	public SilentPluginInfoVO getSilentPluginInfoVO(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return silentPluginInfoDAO.getSilentPluginInfoVO(silentPluginInfoVO);
	}
	
	@Override
	public List<SilentPluginInfoVO> getSilentPluginInfoVOList(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return silentPluginInfoDAO.getSilentPluginInfoVOList(silentPluginInfoVO);
	}
	@Override
	public void insert(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		silentPluginInfoDAO.insert(silentPluginInfoVO);
	}
	
	@Override
	public void update(SilentPluginInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		silentPluginInfoDAO.update(apkInfoVO);
	}

	public SilentPluginInfoDAO getSilentPluginInfoDAO() {
		return silentPluginInfoDAO;
	}

	public void setSilentPluginInfoDAO(SilentPluginInfoDAO silentPluginInfoDAO) {
		this.silentPluginInfoDAO = silentPluginInfoDAO;
	}
	
	
}
