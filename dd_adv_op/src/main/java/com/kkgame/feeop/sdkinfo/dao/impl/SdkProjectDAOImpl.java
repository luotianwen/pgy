package com.kkgame.feeop.sdkinfo.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.SdkProjectVO;
import com.kkgame.feeop.sdkinfo.dao.SdkProjectDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SdkProjectDAOImpl extends SqlMapClientDaoSupport implements SdkProjectDAO {

	@Override
	public List<SdkProjectVO> getSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("sdkProjectSqlMap.getSdkProjectVOListCount",sdkProjectVO);
		sdkProjectVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("sdkProjectSqlMap.getSdkProjectVOList", sdkProjectVO);
	}

	@Override
	public List<SdkProjectVO> getAllSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("sdkProjectSqlMap.getAllSdkProjectVOList", sdkProjectVO);
	}

	@Override
	public SdkProjectVO getSdkProjectVO(SdkProjectVO sdkProjectVO) throws DatabaseException {

		return (SdkProjectVO) getSqlMapClientTemplate().queryForObject("sdkProjectSqlMap.getSdkProjectVO", sdkProjectVO);
	}

	@Override
	public int insert(SdkProjectVO sdkProjectVO) throws DatabaseException {

		return (Integer) getSqlMapClientTemplate().insert("sdkProjectSqlMap.insert", sdkProjectVO);
	}

	@Override
	public void update(SdkProjectVO sdkProjectVO) throws DatabaseException {

		getSqlMapClientTemplate().update("sdkProjectSqlMap.update", sdkProjectVO);
	}

	@Override
	public List<SdkProjectVO> getAdvSdkProjectVOList(SdkProjectVO sdkProjectVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("sdkProjectSqlMap.getAdvSdkProjectVOListCount",sdkProjectVO);
		sdkProjectVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("sdkProjectSqlMap.getAdvSdkProjectVOList", sdkProjectVO);
	}

	@Override
	public SdkProjectVO getAdvSdkProjectVO(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (SdkProjectVO) getSqlMapClientTemplate().queryForObject("sdkProjectSqlMap.getAdvSdkProjectVO", sdkProjectVO);
	}

	@Override
	public int insertAdv(SdkProjectVO sdkProjectVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().insert("sdkProjectSqlMap.insertAdv", sdkProjectVO);
	}

	@Override
	public void updateAdv(SdkProjectVO sdkProjectVO) throws DatabaseException {
		getSqlMapClientTemplate().update("sdkProjectSqlMap.updateAdv", sdkProjectVO);
		
	}
	
	

}
