package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.SdkUsersModelVO;
import com.kkgame.feeop.detail.dao.SdkUsersModelDAO;
import com.kkgame.feeop.detail.service.SdkUsersModelService;
import com.kkgame.feeop.util.DatabaseException;

public class SdkUsersModelServiceImpl implements SdkUsersModelService {

	private SdkUsersModelDAO sdkUsersModelDAO;

	public SdkUsersModelDAO getSdkUsersModelDAO() {
		return sdkUsersModelDAO;
	}

	public void setSdkUsersModelDAO(SdkUsersModelDAO sdkUsersModelDAO) {
		this.sdkUsersModelDAO = sdkUsersModelDAO;
	}

	public void create(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException {

		sdkUsersModelDAO.create(sdkUsersModelVO);
	}

	public SdkUsersModelVO getSdkUsersModelVO(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException {

		return sdkUsersModelDAO.getSdkUsersModelVO(sdkUsersModelVO);
	}

	public List<SdkUsersModelVO> getSdkUsersModelVOList(SdkUsersModelVO sdkUsersModelVO)
			throws DatabaseException {

		return sdkUsersModelDAO.getSdkUsersModelVOList(sdkUsersModelVO);
	}

	public void update(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException {

		sdkUsersModelDAO.update(sdkUsersModelVO);
	}

	public List<SdkUsersModelVO> getSdkUsersModelProjectList(
			SdkUsersModelVO sdkUsersModelVO) {
		// TODO Auto-generated method stub
		return sdkUsersModelDAO.getSdkUsersModelProjectList(sdkUsersModelVO);
	}

	@Override
	public List<SdkUsersModelVO> getSdkUsersModelProjectValidList(SdkUsersModelVO sdkUsersModelVO) {
		return sdkUsersModelDAO.getSdkUsersModelProjectValidList(sdkUsersModelVO);
	}

}
