package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.AdDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdDataDAO {

	List<AdDataVO> getAdDataVOList(
			SearchVO searchVO) throws DatabaseException;
	
	List<AdDataVO> getEffectAdDataVOList(SearchVO searchVO) throws DatabaseException;

}
