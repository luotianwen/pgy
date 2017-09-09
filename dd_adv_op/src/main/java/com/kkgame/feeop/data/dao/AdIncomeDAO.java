package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.AdIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdIncomeDAO {

	List<AdIncomeVO> getAdIncomeVOList(SearchVO searchVO) throws DatabaseException;

}
