package com.kkgame.feeop.sdkinfo.dao.impl;

import com.kkgame.feeop.sdkinfo.bean.AppNameVO;
import com.kkgame.feeop.sdkinfo.dao.AppNameDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

public class AppNameDAOImpl extends SqlMapClientDaoSupport implements AppNameDAO {

	@Override
	public List<AppNameVO> getAllAppNameVOList(AppNameVO appNameVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("appNameSqlMap.getAllAppNameVOList",appNameVO);
	}
	
	@Override
	public AppNameVO getAppNameVO(AppNameVO appNameVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (AppNameVO) getSqlMapClientTemplate().queryForObject("appNameSqlMap.getAppNameVO", appNameVO);
	}
	
	@Override
	public List<AppNameVO> getAppNameVOList(AppNameVO appNameVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("appNameSqlMap.getAppNameVOListCount",appNameVO);
		appNameVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("appNameSqlMap.getAppNameVOList", appNameVO);
	}
	
	@Override
	public void insert(AppNameVO appNameVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("appNameSqlMap.insert", appNameVO);

	}
	
	@Override
	public void update(AppNameVO appNameVO) throws DatabaseException {

		getSqlMapClientTemplate().update("appNameSqlMap.update", appNameVO);
	}
	
	@Override
	public void delete(AppNameVO appNameVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("appNameSqlMap.delete", appNameVO);

	}
}
