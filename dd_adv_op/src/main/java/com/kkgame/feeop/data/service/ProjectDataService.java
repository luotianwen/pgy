package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ProjectDataService {

	List<ProjectDataVO> getProjectDataVOList(SearchVO searchVO) throws DatabaseException;

	List<ProjectDataVO> getTotalProjectDataVOList(SearchVO searchVO) throws DatabaseException;

	List<ProjectDataVO> getDayInfoProjectDataVO(SearchVO searchVO) throws DatabaseException;

}
