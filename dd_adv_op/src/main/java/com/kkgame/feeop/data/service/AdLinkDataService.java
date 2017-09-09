package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdLinkDataService {

	List<AdLinkDataVO> getAdLinkDataVOList(SearchVO searchVO) throws DatabaseException;

}
