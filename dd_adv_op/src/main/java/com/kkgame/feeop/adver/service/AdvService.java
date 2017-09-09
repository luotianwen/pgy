package com.kkgame.feeop.adver.service;

import java.util.List;

import com.kkgame.feeop.adver.bean.AdvVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdvService {

	AdvVO getAdvVO(AdvVO advVO) throws DatabaseException;

	void insert(AdvVO advVO) throws DatabaseException;

	void update(AdvVO advVO) throws DatabaseException;

	List<AdvVO> getAdvVOList(AdvVO advVO) throws DatabaseException;

	List<AdvVO> getAllAdvVOList(AdvVO advVO) throws DatabaseException;

}
