package com.kkgame.feeop.customer.service.impl;

import java.util.List;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.bean.ProjectFileVO;
import com.kkgame.feeop.customer.bean.ProjectVO;
import com.kkgame.feeop.customer.dao.ProjectDAO;
import com.kkgame.feeop.customer.service.ProjectService;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDAO projectDAO;

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	@Override
	public List<ProjectVO> getAllProject(ProjectVO projectVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return projectDAO.getAllProject(projectVO);
	}

	public List<ProjectVO> getProjectVOList(ProjectVO projectVO)
			throws DatabaseException {

		return projectDAO.getProjectVOList(projectVO);
	}
	
	public Integer create(ProjectVO projectVO) throws DatabaseException {
		int projectId = projectDAO.create(projectVO);
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
		/*projectDAO.deleteProjectProduct(projectVO);
		for(ProductVO p : projectVO.getProductVOList()) {			
			p.setProjectId(projectVO.getId());					
		}
		projectDAO.createProjectProduct(projectVO.getProductVOList());	*/	
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
}
