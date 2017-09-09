package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.ProjectDataDAO;
import com.kkgame.feeop.data.service.ProjectDataService;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectDataServiceImpl implements ProjectDataService {

	private ProjectDataDAO projectDataDAO;
	
	@Override
	public List<ProjectDataVO> getProjectDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectDataDAO.getProjectDataVOList(searchVO);
	}
	
	@Override
	public List<ProjectDataVO> getTotalProjectDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectDataDAO.getTotalProjectDataVOList(searchVO);
	}
	
	@Override
	public List<ProjectDataVO> getDayInfoProjectDataVO(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectDataDAO.getDayInfoProjectDataVO(searchVO);
	}

	public ProjectDataDAO getProjectDataDAO() {
		return projectDataDAO;
	}

	public void setProjectDataDAO(ProjectDataDAO projectDataDAO) {
		this.projectDataDAO = projectDataDAO;
	}

}
