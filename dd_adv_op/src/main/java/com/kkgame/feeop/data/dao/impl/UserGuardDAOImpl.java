package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.UserGuardDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class UserGuardDAOImpl extends SqlMapClientDaoSupport implements UserGuardDAO {

	@Override
	public List<ProjectDataVO> getUserGuardDataList(SearchVO searchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable(month);
		String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
		searchVO.setRowFields(rowFields);
		searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("userGuardSqlMap.getUserGuardDataList", searchVO);
	}
	
	@Override
	public List<ProjectDataVO> getUserGuardSuccDataList(SearchVO searchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable(month);
		String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
		searchVO.setRowFields(rowFields);
		searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("userGuardSqlMap.getUserGuardSuccDataList", searchVO);
	}

}
