package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.UserAdVO;
import com.kkgame.feeop.util.DatabaseException;

public interface UserAdDAO {
	
	List<UserAdVO> getUserAdVOList(SearchVO searchVO) throws DatabaseException;	
}
