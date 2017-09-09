package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.dao.ApkInfoDAO;
import com.kkgame.feeop.sdkinfo.service.ApkInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class ApkInfoServiceImpl implements ApkInfoService {

	
	private ApkInfoDAO apkInfoDAO;

	@Override
	public List<ApkInfoVO> getAllApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return apkInfoDAO.getAllApkInfoVOList(apkInfoVO);
	}
	
	@Override
	public ApkInfoVO getApkInfoVO(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return apkInfoDAO.getApkInfoVO(apkInfoVO);
	}
	
	@Override
	public List<ApkInfoVO> getApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return apkInfoDAO.getApkInfoVOList(apkInfoVO);
	}
	@Override
	public void insert(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		apkInfoDAO.insert(apkInfoVO);
	}
	
	@Override
	public void update(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		apkInfoDAO.update(apkInfoVO);
	}
	
	@Override
	public void delete(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		apkInfoDAO.delete(apkInfoVO);
	}
	
	public ApkInfoDAO getApkInfoDAO() {
		return apkInfoDAO;
	}

	public void setApkInfoDAO(ApkInfoDAO apkInfoDAO) {
		this.apkInfoDAO = apkInfoDAO;
	}
	
	
}
