package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.ProjectIncomeDAO;
import com.kkgame.feeop.data.service.ProjectIncomeService;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectIncomeServiceImpl implements ProjectIncomeService {

	private ProjectIncomeDAO projectIncomeDAO;

	@Override
	public List<ProjectIncomeVO> getProjectIncomeVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectIncomeDAO.getProjectIncomeVOList(searchVO);
	}
	
	@Override
	public ProjectIncomeVO getProjectIncomeVO(ProjectIncomeVO projectIncomeVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectIncomeDAO.getProjectIncomeVO(projectIncomeVO);
	}

	@Override
	public void updateProjectIncomeVO(ProjectIncomeVO projectIncomeVO)
			throws DatabaseException {

		projectIncomeDAO.updateProjectIncomeVO(projectIncomeVO);
	}
	
	@Override
	public void updateProjectIncomeVOStatus(ProjectIncomeVO projectIncomeVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		projectIncomeDAO.updateProjectIncomeVOStatus(projectIncomeVO);
	}
	
	public ProjectIncomeDAO getProjectIncomeDAO() {
		return projectIncomeDAO;
	}

	public void setProjectIncomeDAO(ProjectIncomeDAO projectIncomeDAO) {
		this.projectIncomeDAO = projectIncomeDAO;
	}
	
	@Override
	public List<ProjectIncomeVO> getTotalProjectVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectIncomeDAO.getTotalProjectVOList(searchVO);
	}
	
	@Override
	public List<ProjectIncomeVO> getEffectProjectVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectIncomeDAO.getEffectProjectVOList(searchVO);
	}
	
}
