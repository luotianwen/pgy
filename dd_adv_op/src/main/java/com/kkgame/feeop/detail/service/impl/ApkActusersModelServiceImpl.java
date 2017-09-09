package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.ApkActusersModelVO;
import com.kkgame.feeop.detail.dao.ApkActusersModelDAO;
import com.kkgame.feeop.detail.service.ApkActusersModelService;
import com.kkgame.feeop.util.DatabaseException;

public class ApkActusersModelServiceImpl implements ApkActusersModelService {

	private ApkActusersModelDAO apkActusersModelDAO;

	public ApkActusersModelDAO getApkActusersModelDAO() {
		return apkActusersModelDAO;
	}

	public void setApkActusersModelDAO(ApkActusersModelDAO apkActusersModelDAO) {
		this.apkActusersModelDAO = apkActusersModelDAO;
	}

	public void create(ApkActusersModelVO apkActusersModelVO) throws DatabaseException {

		apkActusersModelDAO.create(apkActusersModelVO);
	}

	public ApkActusersModelVO getApkActusersModelVO(ApkActusersModelVO apkActusersModelVO) throws DatabaseException {

		return apkActusersModelDAO.getApkActusersModelVO(apkActusersModelVO);
	}

	public List<ApkActusersModelVO> getApkActusersModelVOList(ApkActusersModelVO apkActusersModelVO)
			throws DatabaseException {

		return apkActusersModelDAO.getApkActusersModelVOList(apkActusersModelVO);
	}

	public void update(ApkActusersModelVO apkActusersModelVO) throws DatabaseException {

		apkActusersModelDAO.update(apkActusersModelVO);
	}

	@Override
	public List<ApkActusersModelVO> getApkGuardModelVOList(ApkActusersModelVO apkActusersModelVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return apkActusersModelDAO.getApkGuardModelVOList(apkActusersModelVO);
	}

}
