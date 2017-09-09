package com.kkgame.hz.dao.impl;

import java.util.List;

import com.kkgame.hz.entities.ProjectStatVO;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.ProjectDAO;
import com.kkgame.hz.entities.ProductVO;
import com.kkgame.hz.entities.ProjectFileVO;
import com.kkgame.hz.entities.ProjectVO;

public class ProjectDAOImpl extends SqlMapClientDaoSupport implements
		ProjectDAO {

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

	@Override
	public List<ProjectVO> getAllProjectVOList(ProjectVO projectVO)
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("projectSqlMap.getAllProjectVOList", projectVO);
	}

	@Override
	public List<ProjectStatVO> getProjectStatVOList(ProjectStatVO projectStatVO) throws DatabaseException {
		List<ProjectStatVO> list = getSqlMapClientTemplate().queryForList("projectSqlMap.getProjectStatVOList", projectStatVO);
		return list;
	}

	@Override
	public void updateSubscribe(ProjectVO projectVO) throws DatabaseException {
		getSqlMapClientTemplate().update("projectSqlMap.updateSubscribe", projectVO);
	}

	@Override
	public int createSubscribe(ProjectVO projectVO) throws DatabaseException {
		return (int) getSqlMapClientTemplate().insert("projectSqlMap.createSubscribe", projectVO);
	}

	@Override
	public ProjectVO getProjectVOSubscribe(ProjectVO projectVO) throws DatabaseException {
		return (ProjectVO)getSqlMapClientTemplate().queryForObject("projectSqlMap.getProjectVOSubscribe", projectVO);
	}

	@Override
	public List<ProjectVO> getProjectVOSubscribeList(ProjectVO projectVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("projectSqlMap.getProjectVOSubscribeListCount", projectVO);
		projectVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("projectSqlMap.getProjectVOSubscribeList", projectVO);
	}
}
