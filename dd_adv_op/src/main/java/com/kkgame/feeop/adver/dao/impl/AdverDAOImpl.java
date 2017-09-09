package com.kkgame.feeop.adver.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.AdverVO;
import com.kkgame.feeop.adver.dao.AdverDAO;
import com.kkgame.feeop.util.DatabaseException;

public class AdverDAOImpl extends SqlMapClientDaoSupport implements AdverDAO {

	@Override
	public AdverVO getAdverVO(AdverVO adverVO) throws DatabaseException {

		return (AdverVO) getSqlMapClientTemplate().queryForObject("adverSqlMap.getAdverVO",adverVO) ;
	}
	
	@Override
	public List<AdverVO> getAdverVOList(AdverVO adverVO) throws DatabaseException {

		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("adverSqlMap.getAdverVOListCount",adverVO);
		adverVO.getPage().setTotalRow(count);
		
		return getSqlMapClientTemplate().queryForList("adverSqlMap.getAdverVOList",adverVO);
	}
	
	@Override
	public List<AdverVO> getAllAdver(AdverVO adverVO) throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("adverSqlMap.getAllAdver", adverVO);
	}
	
	@Override
	public void insert(AdverVO adverVO) throws DatabaseException {
		getSqlMapClientTemplate().insert("adverSqlMap.insert",adverVO);
	}
	
	@Override
	public void update(AdverVO adverVO) throws DatabaseException {
		getSqlMapClientTemplate().update("adverSqlMap.update", adverVO);
	}
	
	@Override
	public void updateStatus(AdverVO adverVO) throws DatabaseException {
		getSqlMapClientTemplate().update("adverSqlMap.updateStatus", adverVO);
	}
}
