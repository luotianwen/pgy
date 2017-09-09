package com.kkgame.feeop.record.service.impl;

import java.util.List;

import com.kkgame.feeop.record.bean.ProjectHzTotalModelVO;
import com.kkgame.feeop.record.dao.ProjectHzTotalModelDAO;
import com.kkgame.feeop.record.service.ProjectHzTotalModelService;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectHzTotalModelServiceImpl implements ProjectHzTotalModelService {

	private ProjectHzTotalModelDAO projectHzTotalModelDAO;

	public ProjectHzTotalModelDAO getProjectHzTotalModelDAO() {
		return projectHzTotalModelDAO;
	}

	public void setProjectHzTotalModelDAO(ProjectHzTotalModelDAO projectHzTotalModelDAO) {
		this.projectHzTotalModelDAO = projectHzTotalModelDAO;
	}

	public void create(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException {

		projectHzTotalModelDAO.create(projectHzTotalModelVO);
	}

	public ProjectHzTotalModelVO getProjectHzTotalModelVO(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException {

		return projectHzTotalModelDAO.getProjectHzTotalModelVO(projectHzTotalModelVO);
	}

	public List<ProjectHzTotalModelVO> getProjectHzTotalModelVOList(ProjectHzTotalModelVO projectHzTotalModelVO)
			throws DatabaseException {

		return projectHzTotalModelDAO.getProjectHzTotalModelVOList(projectHzTotalModelVO);
	}

	public void update(ProjectHzTotalModelVO projectHzTotalModelVO) throws DatabaseException {

		projectHzTotalModelDAO.update(projectHzTotalModelVO);
	}

}
