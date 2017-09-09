package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.DdlDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlDataDAO {

	List<DdlDataVO> getDdlSaleDataVOList(SearchVO searchVO) throws DatabaseException;
	List<DdlDataVO> getDdlDataVOList(SearchVO searchVO) throws DatabaseException;
}
