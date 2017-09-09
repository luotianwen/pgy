package com.kkgame.feeop.customer.dao.impl;

import com.kkgame.feeop.util.CalendarFormat;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.customer.bean.DdlVO;
import com.kkgame.feeop.customer.dao.DdlDAO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class DdlDAOImpl extends SqlMapClientDaoSupport implements DdlDAO {

	@Override
	public DdlVO getDdlVO(DdlVO ddlVO) throws DatabaseException {

		return (DdlVO) getSqlMapClientTemplate().queryForObject("ddlSqlMap.getDdlVO", ddlVO);
	}
	
	@Override
	public void update(DdlVO ddlVO) throws DatabaseException {

		getSqlMapClientTemplate().update("ddlSqlMap.update", ddlVO);
	}

	@Override
	public List<DdlVO> getDdlVOClickList(DdlVO ddlVO) throws DatabaseException {
		String date = CalendarFormat.switchFormatDate(ddlVO.getDate(),"yyyy-MM-dd","yyyyMMdd");
		ddlVO.setDate(date);
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getDdlVOClickList", ddlVO);
	}

}
