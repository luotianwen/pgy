package com.kkgame.feeop.tag.service;

import com.kkgame.feeop.tag.bean.OperateLogVO;
import com.kkgame.feeop.util.DatabaseException;

public interface LogService {

	/**
	 * 添加日志
	 * @param operateLogVO
	 * @throws DatabaseException
	 */
	public void createLog(OperateLogVO operateLogVO) throws DatabaseException;
	
}
