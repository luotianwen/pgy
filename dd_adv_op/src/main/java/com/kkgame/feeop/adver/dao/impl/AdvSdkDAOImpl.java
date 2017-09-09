package com.kkgame.feeop.adver.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.AdvSdkVO;
import com.kkgame.feeop.adver.dao.AdvSdkDAO;
import com.kkgame.feeop.util.DatabaseException;

public class AdvSdkDAOImpl extends SqlMapClientDaoSupport implements AdvSdkDAO {

	@Override
	public List<AdvSdkVO> getAdvSdkVOList(AdvSdkVO advSdkVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("advSdkSqlMap.getAdvSdkVOListCount",advSdkVO);
		advSdkVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("advSdkSqlMap.getAdvSdkVOList", advSdkVO);
	}

	@Override
	public AdvSdkVO getAdvSdkVO(AdvSdkVO advSdkVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (AdvSdkVO) getSqlMapClientTemplate().queryForObject("advSdkSqlMap.getAdvSdkVO",advSdkVO);
	}

	@Override
	public void insert(AdvSdkVO advSdkVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("advSdkSqlMap.insert", advSdkVO);
	}

	@Override
	public void update(AdvSdkVO advSdkVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("advSdkSqlMap.update", advSdkVO);
	}
	
	@Override
	public void updateUrl(AdvSdkVO advSdkVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("advSdkSqlMap.updateUrl", advSdkVO);
	}
	
	@Override
	public void delete(AdvSdkVO advSdkVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("advSdkSqlMap.updateDelete", advSdkVO);

	}

	@Override
	public void batchAlter(AdvSdkVO advSdkVO) throws DatabaseException {
		getSqlMapClientTemplate().update("advSdkSqlMap.batchAlter", advSdkVO);
	}

	@Override
	public List<AdvSdkVO> getAdvSdkVOCountryList(AdvSdkVO advSdkVO) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("advSdkSqlMap.getAdvSdkVOCountryListCount",advSdkVO);
		advSdkVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("advSdkSqlMap.getAdvSdkVOCountryList", advSdkVO);
	}

	@Override
	public void insertCopeAdvSdkVO(AdvSdkVO advSdkVO) {
		getSqlMapClientTemplate().insert("advSdkSqlMap.cope", advSdkVO);
	}
}
