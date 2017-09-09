package com.kkgame.feeop.detail.service;

import java.util.List;

import com.kkgame.feeop.detail.bean.ApkActusersModelVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ApkActusersModelService {

	List<ApkActusersModelVO> getApkActusersModelVOList(ApkActusersModelVO apkActusersModelVO) throws DatabaseException;

	void create(ApkActusersModelVO apkActusersModelVO) throws DatabaseException;

	void update(ApkActusersModelVO apkActusersModelVO) throws DatabaseException;

	ApkActusersModelVO getApkActusersModelVO(ApkActusersModelVO apkActusersModelVO) throws DatabaseException;

	List<ApkActusersModelVO> getApkGuardModelVOList(ApkActusersModelVO apkActusersModelVO) throws DatabaseException;

}
