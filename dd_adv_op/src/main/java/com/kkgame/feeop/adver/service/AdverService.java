package com.kkgame.feeop.adver.service;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdverVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdverService {
	
	List<AdverVO> getAllAdver(AdverVO adverVO) throws DatabaseException;
	
	List<AdverVO> getAdverVOList(AdverVO adverVO) throws DatabaseException;

	void insert(AdverVO adverVO) throws DatabaseException;

	void update(AdverVO adverVO) throws DatabaseException;

	AdverVO getAdverVO(AdverVO adverVO) throws DatabaseException;

	void updateStatus(AdverVO adverVO) throws DatabaseException;
}
