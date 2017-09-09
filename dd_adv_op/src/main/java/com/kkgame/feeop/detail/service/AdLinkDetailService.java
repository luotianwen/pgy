package com.kkgame.feeop.detail.service;

import java.util.List;

import com.kkgame.feeop.detail.bean.AdLinkDetailVO;
import com.kkgame.feeop.util.DatabaseException;

public interface AdLinkDetailService {

	List<AdLinkDetailVO> getAdLinkDetailVOList(AdLinkDetailVO adLinkDetailVO) throws DatabaseException;

}
