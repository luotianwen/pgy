package com.kkgame.feeop.inter.service;

import com.kkgame.feeop.inter.bean.InterVO;
import com.kkgame.feeop.util.DatabaseException;

public interface InterService {

	void create(InterVO interVO) throws DatabaseException;

}
