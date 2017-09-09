package com.kkgame.feeop.tag.dao;

import com.kkgame.feeop.tag.bean.OperateLogVO;
import com.kkgame.feeop.util.DatabaseException;

public interface LogDAO {

	public void createLog(OperateLogVO operateLogVO) throws DatabaseException;

}
