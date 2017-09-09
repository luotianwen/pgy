package com.kkgame.feeop.sdkinfo.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.bean.SilentPluginInfoVO;
import com.kkgame.feeop.sdkinfo.dao.ApkInfoDAO;
import com.kkgame.feeop.sdkinfo.dao.SilentPluginInfoDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SilentPluginInfoDAOImpl extends SqlMapClientDaoSupport implements SilentPluginInfoDAO {

	@Override
	public List<SilentPluginInfoVO> getAllSilentPluginInfoVOList(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("silentPluginInfoSqlMap.getAllSilentPluginInfoVOList", silentPluginInfoVO);
	}
	
	@Override
	public SilentPluginInfoVO getSilentPluginInfoVO(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (SilentPluginInfoVO) getSqlMapClientTemplate().queryForObject("silentPluginInfoSqlMap.getSilentPluginInfoVO", silentPluginInfoVO);
	}
	
	@Override
	public List<SilentPluginInfoVO> getSilentPluginInfoVOList(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("silentPluginInfoSqlMap.getSilentPluginInfoVOListCount",silentPluginInfoVO);
		silentPluginInfoVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("silentPluginInfoSqlMap.getSilentPluginInfoVOList", silentPluginInfoVO);
	}
	
	@Override
	public void insert(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("silentPluginInfoSqlMap.insert", silentPluginInfoVO);

	}
	
	@Override
	public void update(SilentPluginInfoVO silentPluginInfoVO) throws DatabaseException {

		getSqlMapClientTemplate().update("silentPluginInfoSqlMap.update", silentPluginInfoVO);
	}
}
