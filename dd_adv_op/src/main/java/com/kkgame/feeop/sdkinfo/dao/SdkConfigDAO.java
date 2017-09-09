package com.kkgame.feeop.sdkinfo.dao;

import com.kkgame.feeop.sdkinfo.bean.SdkConfigVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface SdkConfigDAO {

	List<SdkConfigVO> getSdkConfigVOList(SdkConfigVO sdkConfigVO) throws DatabaseException;
	List<SdkConfigVO> getAllSdkConfigVOList(SdkConfigVO sdkConfigVO) throws DatabaseException;

	SdkConfigVO getSdkConfigVO(SdkConfigVO sdkConfigVO) throws DatabaseException;
	void insert(SdkConfigVO sdkConfigVO) throws DatabaseException;
	void update(SdkConfigVO sdkConfigVO) throws DatabaseException;
	void delete(SdkConfigVO sdkConfigVO) throws DatabaseException;
}
