package com.kkgame.hz.service.impl;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.ProjectDAO;
import com.kkgame.hz.entities.ProductVO;
import com.kkgame.hz.entities.ProjectFileVO;
import com.kkgame.hz.entities.ProjectStatVO;
import com.kkgame.hz.entities.ProjectVO;
import com.kkgame.hz.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDAO projectDAO;

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public List<ProjectVO> getProjectVOList(ProjectVO projectVO)
			throws DatabaseException {

		return projectDAO.getProjectVOList(projectVO);
	}
	
	public Integer create(ProjectVO projectVO) throws DatabaseException {
		int projectId = projectDAO.create(projectVO);
		projectVO.setId(projectId);
		List<ProductVO> list = projectVO.getProductVOList();
		for(ProductVO p : list) {			
			p.setProjectId(projectId);					
		}
		projectDAO.createProjectProduct(list);	
		return projectId;
	}
	
	public ProjectVO getProjectVO(ProjectVO projectVO) throws DatabaseException {
		return projectDAO.getProjectVO(projectVO);
	}
	
	public List<ProductVO> getProjectProductList(ProjectVO projectVO)
			throws DatabaseException {
		return projectDAO.getProjectProductList(projectVO);
	}
	
	public void update(ProjectVO projectVO) throws DatabaseException {
		projectDAO.update(projectVO);
		projectDAO.deleteProjectProduct(projectVO);
		for(ProductVO p : projectVO.getProductVOList()) {			
			p.setProjectId(projectVO.getId());					
		}
		projectDAO.createProjectProduct(projectVO.getProductVOList());		
	}
	
	public void updateStatus(ProjectVO projectVO) throws DatabaseException {
		projectDAO.updateStatus(projectVO);
	}
	
	public void updatePriceStatus(ProjectVO projectVO) throws DatabaseException {

		projectDAO.updatePriceStatus(projectVO);
	}
	
	public List<ProjectFileVO> getProjectFileVOList(ProjectFileVO projectFileVO)
			throws DatabaseException {
		return projectDAO.getProjectFileVOList(projectFileVO);
	}
	
	public void insertProjectFileVO(ProjectFileVO projectFileVO)
			throws DatabaseException {
		projectDAO.insertProjectFileVO(projectFileVO);
	}
	
	public void updateProjectFile(ProjectFileVO projectFileVO)
			throws DatabaseException {

		projectDAO.updateProjectFile(projectFileVO);
	}

	public void updateInfo(ProjectVO projectVO) throws DatabaseException {
		projectDAO.updateInfo(projectVO);
	}
	
	public ProductVO getProjectProductVO(ProductVO productVO)
			throws DatabaseException {

		return projectDAO.getProjectProductVO(productVO);
	}
	
	public void updatePrice(ProductVO productVO) throws DatabaseException {

		projectDAO.updatePrice(productVO);
	}

	@Override
	public List<ProjectVO> getAllProjectVOList(ProjectVO projectVO) throws DatabaseException {
		return projectDAO.getAllProjectVOList(projectVO);
	}

	@Override
	public List<ProjectStatVO> getProjectStatVOList(ProjectStatVO projectStatVO) throws DatabaseException {
		return projectDAO.getProjectStatVOList(projectStatVO);
	}

	@Override
	public void updateSubscribe(ProjectVO projectVO) throws DatabaseException {
		projectDAO.updateSubscribe(projectVO);
	}

	@Override
	public int createSubscribe(ProjectVO projectVO) throws DatabaseException {
		return projectDAO.createSubscribe(projectVO);
	}

	@Override
	public ProjectVO getProjectVOSubscribe(ProjectVO projectVO) throws DatabaseException {
		return projectDAO.getProjectVOSubscribe(projectVO);
	}

	@Override
	public List<ProjectVO> getProjectVOSubscribeList(ProjectVO projectVO) throws DatabaseException {
		return projectDAO.getProjectVOSubscribeList(projectVO);
	}
}
