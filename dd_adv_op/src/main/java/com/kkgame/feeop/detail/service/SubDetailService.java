package com.kkgame.feeop.detail.service;

import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface SubDetailService {

	List<SubDetailVO> getSubDetailVOList(SubDetailVO subDetailVO) throws DatabaseException;

}
