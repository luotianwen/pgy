package com.kkgame.feeop.detail.dao;

import com.kkgame.feeop.detail.bean.AdLinkDetailVO;
import com.kkgame.feeop.detail.bean.SdkDetailVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface SdkDetailDAO {

	List<SdkDetailVO> getSdkDetailVOList(SdkDetailVO sdkDetailVO) throws DatabaseException;

}
