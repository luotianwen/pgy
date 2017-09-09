	package com.kkgame.feeop.record.service;
	import java.util.List;

	import com.kkgame.feeop.record.bean.ApkCphcTotalModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface ApkCphcTotalModelService {

		List<ApkCphcTotalModelVO> getApkCphcTotalModelVOList(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException;
		

		void create(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException;
		
		void update(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException;
		

		ApkCphcTotalModelVO getApkCphcTotalModelVO(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException;


		void updatePrice(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException;
	}

