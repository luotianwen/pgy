package com.kkgame.feeop.sdkinfo.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.SilentInfoVO;
import com.kkgame.feeop.sdkinfo.dao.SilentInfoDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SilentInfoDAOImpl extends SqlMapClientDaoSupport implements SilentInfoDAO {

	@Override
	public SilentInfoVO getSilentInfoVO(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (SilentInfoVO) getSqlMapClientTemplate().queryForObject("silentInfoSqlMap.getSilentInfoVO", silentInfoVO);
	}

	@Override
	public List<SilentInfoVO> getSilentInfoVOList(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("silentInfoSqlMap.getSilentInfoVOListCount",silentInfoVO);
		silentInfoVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("silentInfoSqlMap.getSilentInfoVOList", silentInfoVO);
	}

	@Override
	public List<SilentInfoVO> getAllSilentInfoVOList(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("silentInfoSqlMap.getSilentInfoVOList", silentInfoVO);
	}

	@Override
	public void insert(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("silentInfoSqlMap.insert", silentInfoVO);

	}

	@Override
	public void update(SilentInfoVO silentInfoVO) throws DatabaseException {

		getSqlMapClientTemplate().update("silentInfoSqlMap.update", silentInfoVO);
	}

	@Override
	public void delete(SilentInfoVO silentInfoVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("silentInfoSqlMap.delete", silentInfoVO);

	}
	
}
