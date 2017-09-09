package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.DdlDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlDataService {

	List<DdlDataVO> getDdlSaleDataVOList(SearchVO searchVO) throws DatabaseException;
	List<DdlDataVO> getDdlDataVOList(SearchVO searchVO) throws DatabaseException;

}
