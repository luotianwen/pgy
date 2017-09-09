package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SdkInfoVO;
import com.kkgame.feeop.sdkinfo.dao.SdkInfoDAO;
import com.kkgame.feeop.sdkinfo.service.SdkInfoService;
import com.kkgame.feeop.util.DatabaseException;

public class SdkInfoServiceImpl implements SdkInfoService {

	private SdkInfoDAO sdkInfoDAO;

	public SdkInfoDAO getSdkInfoDAO() {
		return sdkInfoDAO;
	}

	public void setSdkInfoDAO(SdkInfoDAO sdkInfoDAO) {
		this.sdkInfoDAO = sdkInfoDAO;
	}

	@Override
	public List<SdkInfoVO> getAllSdkInfoVOList(SdkInfoVO sdkInfoVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkInfoDAO.getAllSdkInfoVOList(sdkInfoVO);
	}

	@Override
	public SdkInfoVO getSdkInfoVO(SdkInfoVO sdkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkInfoDAO.getSdkInfoVO(sdkInfoVO);
	}

	@Override
	public List<SdkInfoVO> getSdkInfoVOList(SdkInfoVO sdkInfoVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkInfoDAO.getSdkInfoVOList(sdkInfoVO);
	}
	
	@Override
	public void updateStatus(SdkInfoVO sdkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		sdkInfoDAO.updateStatus(sdkInfoVO);
	}

	@Override
	public void insert(SdkInfoVO sdkInfoVO) throws DatabaseException {

		sdkInfoDAO.insert(sdkInfoVO);		
	}

	@Override
	public void update(SdkInfoVO sdkInfoVO) throws DatabaseException {

		sdkInfoDAO.update(sdkInfoVO);
	}
		
}
