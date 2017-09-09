package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectIncomeVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ProjectIncomeDAO {

	List<ProjectIncomeVO> getProjectIncomeVOList(SearchVO searchVO) throws DatabaseException;

	ProjectIncomeVO getProjectIncomeVO(ProjectIncomeVO projectIncomeVO) throws DatabaseException;

	void updateProjectIncomeVO(ProjectIncomeVO projectIncomeVO) throws DatabaseException;
	void updateProjectIncomeVOStatus(ProjectIncomeVO projectIncomeVO) throws DatabaseException;
	List<ProjectIncomeVO> getTotalProjectVOList(SearchVO searchVO) throws DatabaseException;
	List<ProjectIncomeVO> getEffectProjectVOList(SearchVO searchVO) throws DatabaseException;

}
