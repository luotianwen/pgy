package com.kkgame.feeop.sdkinfo.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.ApkInfoVO;
import com.kkgame.feeop.sdkinfo.dao.ApkInfoDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ApkInfoDAOImpl extends SqlMapClientDaoSupport implements ApkInfoDAO {

	@Override
	public List<ApkInfoVO> getAllApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("apkInfoSqlMap.getAllApkInfoVOList", apkInfoVO);
	}
	
	@Override
	public ApkInfoVO getApkInfoVO(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (ApkInfoVO) getSqlMapClientTemplate().queryForObject("apkInfoSqlMap.getApkInfoVO", apkInfoVO);
	}
	
	@Override
	public List<ApkInfoVO> getApkInfoVOList(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("apkInfoSqlMap.getApkInfoVOListCount",apkInfoVO);
		apkInfoVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("apkInfoSqlMap.getApkInfoVOList", apkInfoVO);
	}
	
	@Override
	public void insert(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("apkInfoSqlMap.insert", apkInfoVO);

	}
	
	@Override
	public void update(ApkInfoVO apkInfoVO) throws DatabaseException {

		getSqlMapClientTemplate().update("apkInfoSqlMap.update", apkInfoVO);
	}
	
	@Override
	public void delete(ApkInfoVO apkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("apkInfoSqlMap.delete", apkInfoVO);

	}
}
