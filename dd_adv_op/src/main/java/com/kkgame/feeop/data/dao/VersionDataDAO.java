package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.VersionDataVO;
import com.kkgame.feeop.util.DatabaseException;

public interface VersionDataDAO {

	List<VersionDataVO> getVersionDataVOList(SearchVO searchVO) throws DatabaseException;

}
