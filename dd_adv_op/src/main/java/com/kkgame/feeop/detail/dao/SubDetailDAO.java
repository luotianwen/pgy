package com.kkgame.feeop.detail.dao;

import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface SubDetailDAO {

	List<SubDetailVO> getSubDetailVOList(SubDetailVO subDetailVO) throws DatabaseException;

}
