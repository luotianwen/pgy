package com.kkgame.feeop.tag.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.tag.bean.OperateLogVO;
import com.kkgame.feeop.tag.dao.LogDAO;
import com.kkgame.feeop.util.DatabaseException;

public class LogDAOImpl extends SqlMapClientDaoSupport implements LogDAO {

	public void createLog(OperateLogVO operateLogVO) throws DatabaseException {

		DateFormat df = new SimpleDateFormat("yyyyMM");
		String yearMonth = df.format(new Date());
		operateLogVO.setTable("OPERATE_LOG_"+yearMonth);
		getSqlMapClientTemplate().insert("logSqlMap.createLog" ,operateLogVO);
	}

}
