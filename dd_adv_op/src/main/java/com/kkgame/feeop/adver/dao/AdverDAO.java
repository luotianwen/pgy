package com.kkgame.feeop.adver.dao;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdverVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdverDAO {

	List<AdverVO> getAllAdver(AdverVO adverVO) throws DatabaseException;
	
	List<AdverVO> getAdverVOList(AdverVO adverVO) throws DatabaseException;

	void insert(AdverVO adverVO) throws DatabaseException;

	void update(AdverVO adverVO) throws DatabaseException;

	AdverVO getAdverVO(AdverVO adverVO) throws DatabaseException;

	void updateStatus(AdverVO adverVO) throws DatabaseException;
}
