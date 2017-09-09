package com.kokmobi.server.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kokmobi.server.bean.ProjectVO;
import com.kokmobi.server.dao.ProjectDao;

public class ProjectDaoImpl extends SqlMapClientDaoSupport implements
		ProjectDao {

	@Override
	public ProjectVO getProjectVOById(int projectId) throws Exception {
		return (ProjectVO) getSqlMapClientTemplate().queryForObject("projectSqlMap.getProjectVOById", projectId);
	}
}
