package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.ProjectDistinctDAO;
import com.kkgame.feeop.data.service.ProjectDistinctService;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectDistinctServiceImpl implements ProjectDistinctService {

	private ProjectDistinctDAO projectDistinctDAO;

	public ProjectDistinctDAO getProjectDistinctDAO() {
		return projectDistinctDAO;
	}

	public void setProjectDistinctDAO(ProjectDistinctDAO projectDistinctDAO) {
		this.projectDistinctDAO = projectDistinctDAO;
	}
	
	@Override
	public List<ProjectDataVO> getProjectDataVOList(SearchVO searchVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return projectDistinctDAO.getProjectDataVOList(searchVO);
	}
}
