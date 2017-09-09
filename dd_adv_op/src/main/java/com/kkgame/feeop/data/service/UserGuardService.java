package com.kkgame.feeop.data.service;

import java.util.List;


import com.kkgame.feeop.data.bean.ProjectDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.util.DatabaseException;

public interface UserGuardService {

	List<ProjectDataVO> getUserGuardDataList(SearchVO searchVO) throws DatabaseException;

	List<ProjectDataVO> getUserGuardSuccDataList(SearchVO searchVO) throws DatabaseException;

}
