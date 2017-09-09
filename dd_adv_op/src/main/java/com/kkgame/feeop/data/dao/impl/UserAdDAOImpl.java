package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.UserAdVO;
import com.kkgame.feeop.data.dao.UserAdDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class UserAdDAOImpl extends SqlMapClientDaoSupport implements UserAdDAO {

	@Override
	public List<UserAdVO> getUserAdVOList(SearchVO searchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable(month);
		searchVO.setMonth(month);
		String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
		searchVO.setRowFields(rowFields);
		searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("userAdSqlMap.getUserAdVOList", searchVO);
	}
}
