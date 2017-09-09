	package com.kkgame.feeop.detail.service;
	import java.util.List;

	import com.kkgame.feeop.detail.bean.SdkUsersModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface SdkUsersModelService {

		List<SdkUsersModelVO> getSdkUsersModelVOList(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException;
		

		void create(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException;
		
		void update(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException;
		

		SdkUsersModelVO getSdkUsersModelVO(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException;


		List<SdkUsersModelVO> getSdkUsersModelProjectList(
				SdkUsersModelVO sdkUsersModelVO);


		List<SdkUsersModelVO> getSdkUsersModelProjectValidList(SdkUsersModelVO sdkUsersModelVO);
	}

