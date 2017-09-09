package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdLinkDataDAO {

	List<AdLinkDataVO> getAdLinkDataVOList(SearchVO searchVO) throws DatabaseException;

}
