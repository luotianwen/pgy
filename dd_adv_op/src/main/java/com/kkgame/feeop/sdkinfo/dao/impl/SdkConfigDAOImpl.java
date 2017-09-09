package com.kkgame.feeop.sdkinfo.dao.impl;

import com.kkgame.feeop.sdkinfo.bean.SdkConfigVO;
import com.kkgame.feeop.sdkinfo.dao.ApkInfoDAO;
import com.kkgame.feeop.sdkinfo.dao.SdkConfigDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

public class SdkConfigDAOImpl extends SqlMapClientDaoSupport implements SdkConfigDAO {

	@Override
	public List<SdkConfigVO> getAllSdkConfigVOList(SdkConfigVO sdkConfigVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("sdkConfigSqlMap.getAllSdkConfigVOList",sdkConfigVO);
	}
	
	@Override
	public SdkConfigVO getSdkConfigVO(SdkConfigVO sdkConfigVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (SdkConfigVO) getSqlMapClientTemplate().queryForObject("sdkConfigSqlMap.getSdkConfigVO", sdkConfigVO);
	}
	
	@Override
	public List<SdkConfigVO> getSdkConfigVOList(SdkConfigVO sdkConfigVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("sdkConfigSqlMap.getSdkConfigVOListCount",sdkConfigVO);
		sdkConfigVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("sdkConfigSqlMap.getSdkConfigVOList", sdkConfigVO);
	}
	
	@Override
	public void insert(SdkConfigVO sdkConfigVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("sdkConfigSqlMap.insert", sdkConfigVO);

	}
	
	@Override
	public void update(SdkConfigVO sdkConfigVO) throws DatabaseException {

		getSqlMapClientTemplate().update("sdkConfigSqlMap.update", sdkConfigVO);
	}
	
	@Override
	public void delete(SdkConfigVO sdkConfigVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("sdkConfigSqlMap.delete", sdkConfigVO);

	}
}
