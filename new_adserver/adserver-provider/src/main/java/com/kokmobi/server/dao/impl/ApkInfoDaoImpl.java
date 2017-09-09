package com.kokmobi.server.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kokmobi.server.bean.ApkInfoVO;
import com.kokmobi.server.dao.ApkInfoDao;

public class ApkInfoDaoImpl extends SqlMapClientDaoSupport implements
		ApkInfoDao {

	@Override
	public List<ApkInfoVO> getApkInfoVOList() throws Exception {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("apkInfoSqlMap.getApkInfoVOList");
	}
	@Override
	public List<ApkInfoVO> getydApkInfoVOList() throws Exception {
		return getSqlMapClientTemplate().queryForList("apkInfoSqlMap.getydApkInfoVOList");
	}
}
