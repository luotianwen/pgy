package com.kkgame.feeop.detail.service.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.SdkActusersModelVO;
import com.kkgame.feeop.detail.dao.SdkActusersModelDAO;
import com.kkgame.feeop.detail.service.SdkActusersModelService;
import com.kkgame.feeop.util.DatabaseException;

public class SdkActusersModelServiceImpl implements SdkActusersModelService {

	private SdkActusersModelDAO sdkActusersModelDAO;

	public SdkActusersModelDAO getSdkActusersModelDAO() {
		return sdkActusersModelDAO;
	}

	public void setSdkActusersModelDAO(SdkActusersModelDAO sdkActusersModelDAO) {
		this.sdkActusersModelDAO = sdkActusersModelDAO;
	}

	public void create(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException {

		sdkActusersModelDAO.create(sdkActusersModelVO);
	}

	public SdkActusersModelVO getSdkActusersModelVO(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException {

		return sdkActusersModelDAO.getSdkActusersModelVO(sdkActusersModelVO);
	}

	public List<SdkActusersModelVO> getSdkActusersModelVOList(SdkActusersModelVO sdkActusersModelVO)
			throws DatabaseException {

		return sdkActusersModelDAO.getSdkActusersModelVOList(sdkActusersModelVO);
	}

	public void update(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException {

		sdkActusersModelDAO.update(sdkActusersModelVO);
	}

}
