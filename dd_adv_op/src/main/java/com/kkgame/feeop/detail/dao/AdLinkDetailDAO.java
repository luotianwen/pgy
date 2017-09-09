package com.kkgame.feeop.detail.dao;

import java.util.List;

import com.kkgame.feeop.detail.bean.AdLinkDetailVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdLinkDetailDAO {

	List<AdLinkDetailVO> getAdLinkDetailVOList(AdLinkDetailVO adLinkDetailVO) throws DatabaseException;

}
