package com.kkgame.feeop.detail.dao;

import java.util.List;

import com.kkgame.feeop.detail.bean.DdlDetailVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlDetailDAO {

	List<DdlDetailVO> getDdlSdkDetailVOList(DdlDetailVO ddlDetailVO) throws DatabaseException;

	List<DdlDetailVO> getDdlAdjustDetailVOList(DdlDetailVO ddlDetailVO) throws DatabaseException;

}
