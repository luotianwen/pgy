	package com.kkgame.feeop.sdkinfo.dao;

	import java.util.List;

	import com.kkgame.feeop.sdkinfo.bean.SdkdomainVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface SdkdomainDAO {

	List<SdkdomainVO> getSdkdomainVOList(SdkdomainVO sdkdomainVO) throws DatabaseException;
		

		void create(SdkdomainVO sdkdomainVO) throws DatabaseException;

		void update(SdkdomainVO sdkdomainVO) throws DatabaseException;

		SdkdomainVO getSdkdomainVO(SdkdomainVO sdkdomainVO) throws DatabaseException;

		void delete(SdkdomainVO sdkdomainVO);
	}

