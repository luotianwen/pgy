package com.kkgame.feeop.sdkinfo.service;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.SilentInfoVO;
import com.kkgame.feeop.util.DatabaseException;

public interface SilentInfoService {

	SilentInfoVO getSilentInfoVO(SilentInfoVO silentInfoVO) throws DatabaseException;

	List<SilentInfoVO> getSilentInfoVOList(SilentInfoVO silentInfoVO) throws DatabaseException;
	List<SilentInfoVO> getAllSilentInfoVOList(SilentInfoVO silentInfoVO) throws DatabaseException;

	void insert(SilentInfoVO silentInfoVO) throws DatabaseException;

	void update(SilentInfoVO silentInfoVO) throws DatabaseException;
	void delete(SilentInfoVO silentInfoVO) throws DatabaseException;

}
