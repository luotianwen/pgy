package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.RetentionVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.RetentionDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class RetentionDAOImpl extends SqlMapClientDaoSupport implements RetentionDAO {

	@Override
	public List<RetentionVO> getRetentionVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable(month);
		searchVO.setMonth(month);
		return getSqlMapClientTemplate().queryForList("retentionSqlMap.getRetentionVOList", searchVO);

	}
	
	@Override
	public List<RetentionVO> getExportRetentionVOList(SearchVO searchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable(month);
		searchVO.setMonth(month);
		return getSqlMapClientTemplate().queryForList("retentionSqlMap.getExportRetentionVOList", searchVO);
	}
	
	@Override
	public List<RetentionVO> getTotalRetentionVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable(month);
		searchVO.setMonth(month);
		return getSqlMapClientTemplate().queryForList("retentionSqlMap.getTotalRetentionVOList", searchVO);

	}
}
