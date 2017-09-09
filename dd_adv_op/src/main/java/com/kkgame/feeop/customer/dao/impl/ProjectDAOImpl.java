package com.kkgame.feeop.customer.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.bean.ProjectFileVO;
import com.kkgame.feeop.customer.bean.ProjectVO;
import com.kkgame.feeop.customer.dao.ProjectDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ProjectDAOImpl extends SqlMapClientDaoSupport implements
		ProjectDAO {

	@Override
	public List<ProjectVO> getAllProject(ProjectVO projectVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("projectSqlMap.getAllProject", projectVO);
	}
	
	public List<ProjectVO> getProjectVOList(ProjectVO projectVO)
			throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("projectSqlMap.getProjectVOListCount", projectVO);
		projectVO.getPage().setTotalRow(count);		
		return getSqlMapClientTemplate().queryForList("projectSqlMap.getProjectVOList", projectVO);
	}
	
	public Integer create(ProjectVO projectVO) throws DatabaseException {
		return (Integer)getSqlMapClientTemplate().insert("projectSqlMap.insert",projectVO);
	}
	
	public void createProjectProduct(List<ProductVO> list)
			throws DatabaseException {
		getSqlMapClientTemplate().insert("projectSqlMap.insertProjectProduct", list);
	}
	
	public List<ProductVO> getProjectProductList(ProjectVO projectVO)
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("projectSqlMap.getProjectProductList", projectVO);
	}
	
	public ProjectVO getProjectVO(ProjectVO projectVO) throws DatabaseException {
		return (ProjectVO) getSqlMapClientTemplate().queryForObject("projectSqlMap.getProjectVO", projectVO);
	}
	
	public void deleteProjectProduct(ProjectVO projectVO)
			throws DatabaseException {
		getSqlMapClientTemplate().delete("projectSqlMap.deleteProjectProduct",projectVO);
	}
	
	public void update(ProjectVO projectVO) throws DatabaseException {
		getSqlMapClientTemplate().update("projectSqlMap.update", projectVO);
	}
	
	public void updateStatus(ProjectVO projectVO) throws DatabaseException {
		getSqlMapClientTemplate().update("projectSqlMap.updateStatus", projectVO);
	}
	
	public void updatePriceStatus(ProjectVO projectVO) throws DatabaseException {
		getSqlMapClientTemplate().update("projectSqlMap.updatePriceStatus", projectVO);		
	}
	
	public List<ProjectFileVO> getProjectFileVOList(ProjectFileVO projectFileVO)
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("projectSqlMap.getProjectFileVOList", projectFileVO);
	}
	
	public void insertProjectFileVO(ProjectFileVO projectFileVO)
			throws DatabaseException {
		getSqlMapClientTemplate().insert("projectSqlMap.insertProjectFileVO", projectFileVO);
	}
	
	public void updateProjectFile(ProjectFileVO projectFileVO)
			throws DatabaseException {

		getSqlMapClientTemplate().update("projectSqlMap.updateProjectFile", projectFileVO);
	}
	
	public void updateInfo(ProjectVO projectVO) throws DatabaseException {
		getSqlMapClientTemplate().update("projectSqlMap.updateInfo", projectVO);
	
	}
	
	public ProductVO getProjectProductVO(ProductVO productVO)
			throws DatabaseException {

		return (ProductVO) getSqlMapClientTemplate().queryForObject("projectSqlMap.getProjectProductVO", productVO);
	}
	
	public void updatePrice(ProductVO productVO) throws DatabaseException {

		getSqlMapClientTemplate().update("projectSqlMap.updatePrice", productVO);
	}
}
