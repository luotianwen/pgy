package com.kkgame.feeop.data.service.impl;

import java.util.List;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.VersionDataVO;
import com.kkgame.feeop.data.dao.VersionDataDAO;
import com.kkgame.feeop.data.service.VersionDataService;
import com.kkgame.feeop.util.DatabaseException;

public class VersionDataServiceImpl implements VersionDataService {

	private VersionDataDAO versionDataDAO;

	public VersionDataDAO getVersionDataDAO() {
		return versionDataDAO;
	}

	public void setVersionDataDAO(VersionDataDAO versionDataDAO) {
		this.versionDataDAO = versionDataDAO;
	}
	
	@Override
	public List<VersionDataVO> getVersionDataVOList(SearchVO searchVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return versionDataDAO.getVersionDataVOList(searchVO);
	}
}
