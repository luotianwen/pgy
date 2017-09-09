package com.kkgame.feeop.sdkinfo.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.SdkInfoVO;
import com.kkgame.feeop.sdkinfo.dao.SdkInfoDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SdkInfoDAOImpl extends SqlMapClientDaoSupport implements
		SdkInfoDAO {

	@Override
	public List<SdkInfoVO> getAllSdkInfoVOList(SdkInfoVO sdkInfoVO)
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("sdkInfoSqlMap.getAllSdkInfoVOList", sdkInfoVO);
	}

	@Override
	public SdkInfoVO getSdkInfoVO(SdkInfoVO sdkInfoVO) throws DatabaseException {
		return (SdkInfoVO) getSqlMapClientTemplate().queryForObject("sdkInfoSqlMap.getSdkInfoVO", sdkInfoVO);
	}

	@Override
	public List<SdkInfoVO> getSdkInfoVOList(SdkInfoVO sdkInfoVO)
			throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("sdkInfoSqlMap.getSdkInfoVOListCount", sdkInfoVO);
		sdkInfoVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("sdkInfoSqlMap.getSdkInfoVOList", sdkInfoVO);
	}

	@Override
	public void insert(SdkInfoVO sdkInfoVO) throws DatabaseException {

		getSqlMapClientTemplate().insert("sdkInfoSqlMap.insert", sdkInfoVO);
	}
	
	@Override
	public void updateStatus(SdkInfoVO sdkInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("sdkInfoSqlMap.updateStatus", sdkInfoVO);

	}

	@Override
	public void update(SdkInfoVO sdkInfoVO) throws DatabaseException {

		getSqlMapClientTemplate().update("sdkInfoSqlMap.update", sdkInfoVO);

	}

}
