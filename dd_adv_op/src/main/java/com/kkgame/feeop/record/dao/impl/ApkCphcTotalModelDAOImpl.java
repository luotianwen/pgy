	package com.kkgame.feeop.record.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.record.bean.ApkCphcTotalModelVO;
import com.kkgame.feeop.record.dao.ApkCphcTotalModelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ApkCphcTotalModelDAOImpl extends SqlMapClientDaoSupport implements
		ApkCphcTotalModelDAO { 

	//新增
	public void create(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {
		 getSqlMapClientTemplate().insert("apkCphcTotalModelSqlMap.create", apkCphcTotalModelVO);
	}
	 
	
	public ApkCphcTotalModelVO getApkCphcTotalModelVO(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {
		return (ApkCphcTotalModelVO) getSqlMapClientTemplate().queryForObject("apkCphcTotalModelSqlMap.getApkCphcTotalModelVO", apkCphcTotalModelVO);
	}

	@Override
	public void updatePrice(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {
		getSqlMapClientTemplate().update("apkCphcTotalModelSqlMap.updatePrice", apkCphcTotalModelVO);
	}

	public List<ApkCphcTotalModelVO> getApkCphcTotalModelVOList(ApkCphcTotalModelVO apkCphcTotalModelVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("apkCphcTotalModelSqlMap.getApkCphcTotalModelVOListCount", apkCphcTotalModelVO);
		apkCphcTotalModelVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("apkCphcTotalModelSqlMap.getApkCphcTotalModelVOList", apkCphcTotalModelVO);
	}
	
	
	//修改
	public void update(ApkCphcTotalModelVO apkCphcTotalModelVO) throws DatabaseException {
		getSqlMapClientTemplate().update("apkCphcTotalModelSqlMap.update", apkCphcTotalModelVO);
	}
	
 

	 
	 

}
