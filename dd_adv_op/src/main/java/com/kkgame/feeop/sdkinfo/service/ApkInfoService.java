package com.kkgame.feeop.sdkinfo.service;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ApkInfoService {

	List<ApkInfoVO> getApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException;
	List<ApkInfoVO> getAllApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException;

	ApkInfoVO getApkInfoVO(ApkInfoVO apkInfoVO) throws DatabaseException;
	void insert(ApkInfoVO apkInfoVO) throws DatabaseException;
	void update(ApkInfoVO apkInfoVO) throws DatabaseException;
	void delete(ApkInfoVO apkInfoVO) throws DatabaseException;
}
