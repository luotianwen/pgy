package com.kkgame.feeop.sdkinfo.dao;

import java.util.List;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ApkInfoDAO {

	List<ApkInfoVO> getApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException;
	List<ApkInfoVO> getAllApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException;

	ApkInfoVO getApkInfoVO(ApkInfoVO apkInfoVO) throws DatabaseException;
	void insert(ApkInfoVO apkInfoVO) throws DatabaseException;
	void update(ApkInfoVO apkInfoVO) throws DatabaseException;
	void delete(ApkInfoVO apkInfoVO) throws DatabaseException;
}
