package com.kkgame.hz.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.DdlDataDAO;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.DdlDataVO;
import com.kkgame.hz.entities.UserRegisterVO;
import com.kkgame.hz.util.CalendarFormat;

public class DdlDataDAOImpl extends SqlMapClientDaoSupport implements
		DdlDataDAO {

	@Override
	public List<DdlDataVO> getDdlDataVOListDay(BillSearchVO billSearchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(billSearchVO.getStartTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		billSearchVO.setTable("DDL_DATA_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		return (List<DdlDataVO>) getSqlMapClientTemplate().queryForList("ddlDataSqlMap.getDdlDataVOListDay", billSearchVO);
	}
	
	@Override
	public void insert(DdlDataVO ddlDataVO) throws DatabaseException {

		getSqlMapClientTemplate().insert("ddlDataSqlMap.insert", ddlDataVO);
	}
}
