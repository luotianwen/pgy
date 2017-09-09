package com.kkgame.feeop.adver.service;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdvSdkVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdvSdkService {

	List<AdvSdkVO> getAdvSdkVOList(AdvSdkVO advSdkVO) throws DatabaseException;

	AdvSdkVO getAdvSdkVO(AdvSdkVO advSdkVO) throws DatabaseException;

	void insert(AdvSdkVO advSdkVO) throws DatabaseException;

	void update(AdvSdkVO advSdkVO) throws DatabaseException;

	void updateUrl(AdvSdkVO advSdkVO) throws DatabaseException;

	void delete(AdvSdkVO advSdkVO) throws DatabaseException;

	void batchAlter(AdvSdkVO advSdkVO) throws DatabaseException;

	List<AdvSdkVO> getAdvSdkVOCountryList(AdvSdkVO advSdkVO);

	void insertCopeAdvSdkVO(AdvSdkVO advSdkVO);
}
