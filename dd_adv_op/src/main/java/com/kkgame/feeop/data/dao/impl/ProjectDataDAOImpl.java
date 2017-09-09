package com.kkgame.feeop.data.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.ProjectDataDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectDataDAOImpl extends SqlMapClientDaoSupport implements
		ProjectDataDAO {

	@Override
	public List<ProjectDataVO> getProjectDataVOList(SearchVO searchVO)
			throws DatabaseException {
			String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
			searchVO.setTable(month);
			searchVO.setMonth(month);
			String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
			searchVO.setRowFields(rowFields);
			searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
		if(0==searchVO.getSpId()) {
			return getSqlMapClientTemplate().queryForList("projectDataSqlMap.getProjectDataVOList", searchVO);
		}
		else{
			//商务
			return getSqlMapClientTemplate().queryForList("projectDataSqlMap.getAffairProjectDataVOList", searchVO);
		}
	}
	
	@Override
	public List<ProjectDataVO> getDayInfoProjectDataVO(SearchVO searchVO)
			throws DatabaseException {
		
		return getSqlMapClientTemplate().queryForList("projectDataSqlMap.getDayInfoProjectDataVO", searchVO);
	}
	
	@Override
	public List<ProjectDataVO> getTotalProjectDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("projectDataSqlMap.getTotalProjectDataVOList", searchVO);
	}
}
