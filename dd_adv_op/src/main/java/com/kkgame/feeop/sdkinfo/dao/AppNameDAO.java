package com.kkgame.feeop.sdkinfo.dao;

import com.kkgame.feeop.sdkinfo.bean.AppNameVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface AppNameDAO {

	List<AppNameVO> getAppNameVOList(AppNameVO appName) throws DatabaseException;
	List<AppNameVO> getAllAppNameVOList(AppNameVO appName) throws DatabaseException;

	AppNameVO getAppNameVO(AppNameVO appName) throws DatabaseException;
	void insert(AppNameVO appName) throws DatabaseException;
	void update(AppNameVO appName) throws DatabaseException;
	void delete(AppNameVO appName) throws DatabaseException;
}
