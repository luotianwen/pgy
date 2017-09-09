package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ProjectDistinctService {

	List<ProjectDataVO> getProjectDataVOList(SearchVO searchVO) throws DatabaseException;
}
