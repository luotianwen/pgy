package com.kkgame.feeop.detail.service.impl;

import com.kkgame.feeop.detail.bean.SdkDetailVO;
import com.kkgame.feeop.detail.dao.SdkDetailDAO;
import com.kkgame.feeop.detail.service.SdkDetailService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class SdkDetailServiceImpl implements SdkDetailService {

	private SdkDetailDAO sdkDetailDAO;

	@Override
	public List<SdkDetailVO> getSdkDetailVOList(SdkDetailVO sdkDetailVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sdkDetailDAO.getSdkDetailVOList(sdkDetailVO);
	}
	
	public SdkDetailDAO getSdkDetailDAO() {
		return sdkDetailDAO;
	}

	public void setSdkDetailDAO(SdkDetailDAO sdkDetailDAO) {
		this.sdkDetailDAO = sdkDetailDAO;
	}
}
