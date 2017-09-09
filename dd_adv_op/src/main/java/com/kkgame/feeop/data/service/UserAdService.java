package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.UserAdVO;
import com.kkgame.feeop.util.DatabaseException;

public interface UserAdService {

	List<UserAdVO> getUserAdVOList(SearchVO searchVO) throws DatabaseException;

}
