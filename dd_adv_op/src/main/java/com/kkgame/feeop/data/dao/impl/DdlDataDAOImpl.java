package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.DdlDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.DdlDataDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class DdlDataDAOImpl extends SqlMapClientDaoSupport implements DdlDataDAO {

	@Override
	public List<DdlDataVO> getDdlDataVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable("DDL_CLICK_DATA_"+month);
		searchVO.setMonth(month);
		String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
		searchVO.setRowFields(rowFields);
		searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("ddlDataSqlMap.getDdlDataVOList", searchVO);
	}
	
	@Override
	public List<DdlDataVO> getDdlSaleDataVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable("DDL_SALE_DATA_"+month);
		searchVO.setMonth(month);
		String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
		searchVO.setRowFields(rowFields);
		searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("ddlDataSqlMap.getDdlSaleDataVOList", searchVO);
	}
	
}
