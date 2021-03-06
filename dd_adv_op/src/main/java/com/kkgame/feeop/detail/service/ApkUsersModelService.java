	package com.kkgame.feeop.detail.service;
	import java.util.List;

	import com.kkgame.feeop.detail.bean.ApkUsersModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface ApkUsersModelService {

		List<ApkUsersModelVO> getApkUsersModelVOList(ApkUsersModelVO apkUsersModelVO) throws DatabaseException;
		

		void create(ApkUsersModelVO apkUsersModelVO) throws DatabaseException;
		
		void update(ApkUsersModelVO apkUsersModelVO) throws DatabaseException;
		

		ApkUsersModelVO getApkUsersModelVO(ApkUsersModelVO apkUsersModelVO) throws DatabaseException;
		
		
		
	}

