package com.kkgame.feeop.detail.service;

import java.util.List;

import com.kkgame.feeop.detail.bean.DdlDetailVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlDetailService {

	List<DdlDetailVO> getDdlSdkDetailVOList(DdlDetailVO ddlDetailVO) throws DatabaseException;

	List<DdlDetailVO> getDdlAdjustDetailVOList(DdlDetailVO ddlDetailVO) throws DatabaseException;

}
