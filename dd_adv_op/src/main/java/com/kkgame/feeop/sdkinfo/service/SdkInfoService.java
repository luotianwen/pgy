package com.kkgame.feeop.sdkinfo.service;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SdkInfoVO;
import com.kkgame.feeop.util.DatabaseException;

public interface SdkInfoService {

	List<SdkInfoVO> getSdkInfoVOList(SdkInfoVO sdkInfoVO)
			throws DatabaseException;

	List<SdkInfoVO> getAllSdkInfoVOList(SdkInfoVO sdkInfoVO)
			throws DatabaseException;

	void insert(SdkInfoVO sdkInfoVO) throws DatabaseException;

	SdkInfoVO getSdkInfoVO(SdkInfoVO sdkInfoVO) throws DatabaseException;

	void update(SdkInfoVO sdkInfoVO) throws DatabaseException;

	void updateStatus(SdkInfoVO sdkInfoVO) throws DatabaseException;

}
