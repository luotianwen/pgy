package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface RetentionService {

	List<RetentionVO> getRetentionVOList(SearchVO searchVO) throws DatabaseException;
	List<RetentionVO> getTotalRetentionVOList(SearchVO searchVO) throws DatabaseException;
	List<RetentionVO> getExportRetentionVOList(SearchVO searchVO) throws DatabaseException;

}
