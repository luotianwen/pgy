package com.kkgame.feeop.sdkinfo.service;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SdkProjectVO;
import com.kkgame.feeop.util.DatabaseException;

public interface SdkProjectService {

	List<SdkProjectVO> getSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException;
	
	List<SdkProjectVO> getAllSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException;

	SdkProjectVO getSdkProjectVO(SdkProjectVO sdkProjectVO) throws DatabaseException;

	int insert(SdkProjectVO sdkProjectVO) throws DatabaseException;

	void update(SdkProjectVO sdkProjectVO) throws DatabaseException;
	
	List<SdkProjectVO> getAdvSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException;
	
	SdkProjectVO getAdvSdkProjectVO(SdkProjectVO sdkProjectVO) throws DatabaseException;

	int insertAdv(SdkProjectVO sdkProjectVO) throws DatabaseException;

	void updateAdv(SdkProjectVO sdkProjectVO) throws DatabaseException;

}
