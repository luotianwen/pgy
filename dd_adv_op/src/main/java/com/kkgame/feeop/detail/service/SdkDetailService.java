package com.kkgame.feeop.detail.service;

import com.kkgame.feeop.detail.bean.SdkDetailVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface SdkDetailService {

	List<SdkDetailVO> getSdkDetailVOList(SdkDetailVO sdkDetailVO) throws DatabaseException;

}
