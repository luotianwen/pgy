package com.kkgame.feeop.customer.service;

import java.util.List;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.bean.ProjectFileVO;
import com.kkgame.feeop.customer.bean.ProjectVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ProjectService {

	List<ProjectVO> getProjectVOList(ProjectVO projectVO) throws DatabaseException;

	Integer create(ProjectVO projectVO) throws DatabaseException;

	ProjectVO getProjectVO(ProjectVO projectVO) throws DatabaseException;

	List<ProductVO> getProjectProductList(ProjectVO projectVO) throws DatabaseException;

	void update(ProjectVO projectVO) throws DatabaseException;

	void updateStatus(ProjectVO projectVO) throws DatabaseException;

	void updatePriceStatus(ProjectVO projectVO) throws DatabaseException;

	void insertProjectFileVO(ProjectFileVO projectFileVO) throws DatabaseException;
	
	List<ProjectFileVO> getProjectFileVOList(ProjectFileVO projectFileVO) throws DatabaseException;

	void updateProjectFile(ProjectFileVO projectFileVO) throws DatabaseException;

	void updateInfo(ProjectVO projectVO) throws DatabaseException;

	ProductVO getProjectProductVO(ProductVO productVO) throws DatabaseException;
	
	void updatePrice(ProductVO productVO)throws DatabaseException;

	List<ProjectVO> getAllProject(ProjectVO projectVO) throws DatabaseException;
}
