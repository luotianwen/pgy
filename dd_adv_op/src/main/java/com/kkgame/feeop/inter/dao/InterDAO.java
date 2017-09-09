package com.kkgame.feeop.inter.dao;

import com.kkgame.feeop.inter.bean.InterVO;
import com.kkgame.feeop.util.DatabaseException;

public interface InterDAO {

	void create(InterVO interVO) throws DatabaseException;

}
