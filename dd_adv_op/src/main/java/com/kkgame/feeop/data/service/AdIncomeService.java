package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.AdIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdIncomeService {

	List<AdIncomeVO> getAdIncomeVOList(SearchVO searchVO) throws DatabaseException;

}
