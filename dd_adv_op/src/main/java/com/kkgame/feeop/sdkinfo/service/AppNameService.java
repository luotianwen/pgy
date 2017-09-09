package com.kkgame.feeop.sdkinfo.service;

import com.kkgame.feeop.sdkinfo.bean.AppNameVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface AppNameService {

	List<AppNameVO> getAppNameVOList(AppNameVO appNameVO) throws DatabaseException;
	List<AppNameVO> getAllAppNameVOList(AppNameVO appNameVO) throws DatabaseException;

	AppNameVO getAppNameVO(AppNameVO appNameVO) throws DatabaseException;
	void insert(AppNameVO appNameVO) throws DatabaseException;
	void update(AppNameVO appNameVO) throws DatabaseException;
	void delete(AppNameVO appNameVO) throws DatabaseException;
}
