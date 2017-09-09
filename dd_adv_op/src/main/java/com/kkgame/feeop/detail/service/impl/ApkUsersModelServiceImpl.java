package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.ApkUsersModelVO;
import com.kkgame.feeop.detail.dao.ApkUsersModelDAO;
import com.kkgame.feeop.detail.service.ApkUsersModelService;
import com.kkgame.feeop.util.DatabaseException;

public class ApkUsersModelServiceImpl implements ApkUsersModelService {

	private ApkUsersModelDAO apkUsersModelDAO;

	public ApkUsersModelDAO getApkUsersModelDAO() {
		return apkUsersModelDAO;
	}

	public void setApkUsersModelDAO(ApkUsersModelDAO apkUsersModelDAO) {
		this.apkUsersModelDAO = apkUsersModelDAO;
	}

	public void create(ApkUsersModelVO apkUsersModelVO) throws DatabaseException {

		apkUsersModelDAO.create(apkUsersModelVO);
	}

	public ApkUsersModelVO getApkUsersModelVO(ApkUsersModelVO apkUsersModelVO) throws DatabaseException {

		return apkUsersModelDAO.getApkUsersModelVO(apkUsersModelVO);
	}

	public List<ApkUsersModelVO> getApkUsersModelVOList(ApkUsersModelVO apkUsersModelVO)
			throws DatabaseException {

		return apkUsersModelDAO.getApkUsersModelVOList(apkUsersModelVO);
	}

	public void update(ApkUsersModelVO apkUsersModelVO) throws DatabaseException {

		apkUsersModelDAO.update(apkUsersModelVO);
	}

}
