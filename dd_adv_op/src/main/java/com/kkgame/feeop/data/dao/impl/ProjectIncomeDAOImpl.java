package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.ProjectIncomeDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectIncomeDAOImpl extends SqlMapClientDaoSupport implements
		ProjectIncomeDAO {

	@Override
	public List<ProjectIncomeVO> getProjectIncomeVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable("PROJECT_HZ_TOTAL_"+month);
		String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
		searchVO.setRowFields(rowFields);
		searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("projectIncomeSqlMap.getProjectIncomeVOList", searchVO);
	}
	
	@Override
	public List<ProjectIncomeVO> getTotalProjectVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable("PROJECT_HZ_TOTAL_"+month);
		return getSqlMapClientTemplate().queryForList("projectIncomeSqlMap.getTotalProjectVOList", searchVO);

	}
	
	@Override
	public List<ProjectIncomeVO> getEffectProjectVOList(SearchVO searchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
		searchVO.setTable("PROJECT_HZ_TOTAL_"+month);
		return getSqlMapClientTemplate().queryForList("projectIncomeSqlMap.getEffectProjectVOList", searchVO);
	}
	
	@Override
	public ProjectIncomeVO getProjectIncomeVO(ProjectIncomeVO projectIncomeVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(projectIncomeVO.getStatDate(),"yyyy-MM-dd","yyyyMM");
		projectIncomeVO.setTable("PROJECT_HZ_TOTAL_"+month);
		return (ProjectIncomeVO) getSqlMapClientTemplate().queryForObject("projectIncomeSqlMap.getProjectIncomeVO", projectIncomeVO);
	}
	
	@Override
	public void updateProjectIncomeVO(ProjectIncomeVO projectIncomeVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(projectIncomeVO.getStatDate(),"yyyy-MM-dd","yyyyMM");
		projectIncomeVO.setTable("PROJECT_HZ_TOTAL_"+month);
		getSqlMapClientTemplate().update("projectIncomeSqlMap.updateProjectIncomeVO", projectIncomeVO);
		getSqlMapClientTemplate().update("projectIncomeSqlMap.updateExpectPrice", projectIncomeVO);
	}
	
	@Override
	public void updateProjectIncomeVOStatus(ProjectIncomeVO projectIncomeVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(projectIncomeVO.getStatDate(),"yyyy-MM-dd","yyyyMM");
		projectIncomeVO.setTable("PROJECT_HZ_TOTAL_"+month);
		getSqlMapClientTemplate().update("projectIncomeSqlMap.updateProjectIncomeVOStatus", projectIncomeVO);
	}
}
