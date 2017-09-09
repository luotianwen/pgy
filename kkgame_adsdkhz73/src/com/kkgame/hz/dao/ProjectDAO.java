package com.kkgame.hz.dao;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.ProductVO;
import com.kkgame.hz.entities.ProjectFileVO;
import com.kkgame.hz.entities.ProjectStatVO;
import com.kkgame.hz.entities.ProjectVO;

import java.util.List;

public interface ProjectDAO {

	List<ProjectVO> getProjectVOList(ProjectVO projectVO) throws DatabaseException;

	Integer create(ProjectVO projectVO) throws DatabaseException;

	void createProjectProduct(List<ProductVO> productVOList) throws DatabaseException;

	ProjectVO getProjectVO(ProjectVO projectVO) throws DatabaseException;

	List<ProductVO> getProjectProductList(ProjectVO projectVO) throws DatabaseException;

	void update(ProjectVO projectVO) throws DatabaseException;

	void deleteProjectProduct(ProjectVO projectVO) throws DatabaseException;

	void updateStatus(ProjectVO projectVO) throws DatabaseException;

	void updatePriceStatus(ProjectVO projectVO) throws DatabaseException;

	void insertProjectFileVO(ProjectFileVO projectFileVO) throws DatabaseException;

	List<ProjectFileVO> getProjectFileVOList(ProjectFileVO projectFileVO) throws DatabaseException;

	void updateProjectFile(ProjectFileVO projectFileVO) throws DatabaseException;

	void updateInfo(ProjectVO projectVO) throws DatabaseException;

	ProductVO getProjectProductVO(ProductVO productVO) throws DatabaseException;

	void updatePrice(ProductVO productVO)throws DatabaseException;

	List<ProjectVO> getAllProjectVOList(ProjectVO projectVO) throws DatabaseException;

	List<ProjectStatVO> getProjectStatVOList(ProjectStatVO projectStatVO) throws DatabaseException;

	void updateSubscribe(ProjectVO projectVO)throws DatabaseException;

	int createSubscribe(ProjectVO projectVO)throws DatabaseException;

	ProjectVO getProjectVOSubscribe(ProjectVO projectVO)throws DatabaseException;

	List<ProjectVO> getProjectVOSubscribeList(ProjectVO projectVO)throws DatabaseException;
}
