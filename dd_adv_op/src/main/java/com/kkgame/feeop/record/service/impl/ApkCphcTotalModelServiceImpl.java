package com.kkgame.feeop.record.service.impl;

import java.util.List;

import com.kkgame.feeop.record.bean.ApkCphcTotalModelVO;
import com.kkgame.feeop.record.dao.ApkCphcTotalModelDAO;
import com.kkgame.feeop.record.service.ApkCphcTotalModelService;
import com.kkgame.feeop.util.DatabaseException;

public class ApkCphcTotalModelServiceImpl implements ApkCphcTotalModelService {

	private ApkCphcTotalModelDAO apkCphcTotalModelDAO;

	public ApkCphcTotalModelDAO getApkCphcTotalModelDAO() {
		return apkCphcTotalModelDAO;
	}

	public void setApkCphcTotalModelDAO(ApkCphcTotalModelDAO apkCphcTotalModelDAO) {
		this.apkCphcTotalModelDAO = apkCphcTotalModelDAO;
	}

	public void create(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {

		apkCphcTotalModelDAO.create(apkCphcTotalModelVO);
	}

	public ApkCphcTotalModelVO getApkCphcTotalModelVO(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {

		return apkCphcTotalModelDAO.getApkCphcTotalModelVO(apkCphcTotalModelVO);
	}

	@Override
	public void updatePrice(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {
		apkCphcTotalModelDAO.updatePrice(apkCphcTotalModelVO);
	}

	public List<ApkCphcTotalModelVO> getApkCphcTotalModelVOList(ApkCphcTotalModelVO apkCphcTotalModelVO)
			throws DatabaseException {

		return apkCphcTotalModelDAO.getApkCphcTotalModelVOList(apkCphcTotalModelVO);
	}

	public void update(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {

		apkCphcTotalModelDAO.update(apkCphcTotalModelVO);
	}

}
