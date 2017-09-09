package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.ProjectDistinctDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectDistinctDAOImpl extends SqlMapClientDaoSupport implements ProjectDistinctDAO {

	@Override
	public List<ProjectDataVO> getProjectDataVOList(SearchVO searchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable("REGISTER_DISTINCT_DATA_"+month);
		return getSqlMapClientTemplate().queryForList("projectDistinctSqlMap.getProjectDataVOList", searchVO);
	}
}
