	package com.kkgame.feeop.record.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.record.bean.ProjectHzTotalModelVO;
import com.kkgame.feeop.record.dao.ProjectHzTotalModelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectHzTotalModelDAOImpl extends SqlMapClientDaoSupport implements
		ProjectHzTotalModelDAO { 

	//新增
	public void create(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException {
		 getSqlMapClientTemplate().insert("projectHzTotalModelSqlMap.create", projectHzTotalModelVO);
	}
	 
	
	public ProjectHzTotalModelVO getProjectHzTotalModelVO(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException {
		return (ProjectHzTotalModelVO) getSqlMapClientTemplate().queryForObject("projectHzTotalModelSqlMap.getProjectHzTotalModelVO", projectHzTotalModelVO);
	}
	
	public List<ProjectHzTotalModelVO> getProjectHzTotalModelVOList(ProjectHzTotalModelVO projectHzTotalModelVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("projectHzTotalModelSqlMap.getProjectHzTotalModelVOListCount", projectHzTotalModelVO);
		projectHzTotalModelVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("projectHzTotalModelSqlMap.getProjectHzTotalModelVOList", projectHzTotalModelVO);
	}
	
	
	//修改
	public void update(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException {
		getSqlMapClientTemplate().update("projectHzTotalModelSqlMap.update", projectHzTotalModelVO);
	}
	
 

	 
	 

}
