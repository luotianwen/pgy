package com.kkgame.feeop.tag.service.impl;

import com.kkgame.feeop.tag.bean.OperateLogVO;
import com.kkgame.feeop.tag.dao.LogDAO;
import com.kkgame.feeop.tag.service.LogService;
import com.kkgame.feeop.util.DatabaseException;

public class LogServiceImpl implements LogService {

	private LogDAO logDAO;
	
	public LogDAO getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public void createLog(OperateLogVO operateLogVO) throws DatabaseException {
		
		logDAO.createLog(operateLogVO);
	}

}
