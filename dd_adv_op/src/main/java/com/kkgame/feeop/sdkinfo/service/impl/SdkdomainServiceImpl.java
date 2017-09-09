package com.kkgame.feeop.sdkinfo.service.impl;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SdkdomainVO;
import com.kkgame.feeop.sdkinfo.dao.SdkdomainDAO;
import com.kkgame.feeop.sdkinfo.service.SdkdomainService;
import com.kkgame.feeop.util.DatabaseException;

public class SdkdomainServiceImpl implements SdkdomainService {

	private SdkdomainDAO sdkdomainDAO;

	public SdkdomainDAO getSdkdomainDAO() {
		return sdkdomainDAO;
	}

	public void setSdkdomainDAO(SdkdomainDAO sdkdomainDAO) {
		this.sdkdomainDAO = sdkdomainDAO;
	}

	public void create(SdkdomainVO sdkdomainVO) throws DatabaseException {

		sdkdomainDAO.create(sdkdomainVO);
	}

	public SdkdomainVO getSdkdomainVO(SdkdomainVO sdkdomainVO) throws DatabaseException {

		return sdkdomainDAO.getSdkdomainVO(sdkdomainVO);
	}

	public void delete(SdkdomainVO sdkdomainVO) {
		sdkdomainDAO.delete(sdkdomainVO);
	}

	public List<SdkdomainVO> getSdkdomainVOList(SdkdomainVO sdkdomainVO)
			throws DatabaseException {

		return sdkdomainDAO.getSdkdomainVOList(sdkdomainVO);
	}

	public void update(SdkdomainVO sdkdomainVO) throws DatabaseException {

		sdkdomainDAO.update(sdkdomainVO);
	}

}
