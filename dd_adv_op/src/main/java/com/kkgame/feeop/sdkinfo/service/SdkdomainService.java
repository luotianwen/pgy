	package com.kkgame.feeop.sdkinfo.service;
	import java.util.List;

	import com.kkgame.feeop.sdkinfo.bean.SdkdomainVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface SdkdomainService {

		List<SdkdomainVO> getSdkdomainVOList(SdkdomainVO sdkdomainVO) throws DatabaseException;
		

		void create(SdkdomainVO sdkdomainVO) throws DatabaseException;
		
		void update(SdkdomainVO sdkdomainVO) throws DatabaseException;
		

		SdkdomainVO getSdkdomainVO(SdkdomainVO sdkdomainVO) throws DatabaseException;


		void delete(SdkdomainVO sdkdomainVO);
	}

