	package com.kkgame.feeop.detail.dao;

	import java.util.List;

	import com.kkgame.feeop.detail.bean.SdkActusersModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface SdkActusersModelDAO {

	List<SdkActusersModelVO> getSdkActusersModelVOList(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException;
		

		void create(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException;

		void update(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException;

		SdkActusersModelVO getSdkActusersModelVO(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException;

	}

