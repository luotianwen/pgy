package com.kkgame.feeop.adver.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.AdvVO;
import com.kkgame.feeop.adver.bean.AdverVO;
import com.kkgame.feeop.adver.dao.AdvDAO;
import com.kkgame.feeop.util.DatabaseException;

public class AdvDAOImpl extends SqlMapClientDaoSupport implements AdvDAO {

	@Override
	public AdvVO getAdvVO(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (AdvVO) getSqlMapClientTemplate().queryForObject("advSqlMap.getAdvVO",advVO) ;
	}
	
	@Override
	public List<AdvVO> getAdvVOList(AdvVO advVO) throws DatabaseException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("advSqlMap.getAdvVOListCount",advVO);
		advVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("advSqlMap.getAdvVOList",advVO);
	}
	
	@Override
	public List<AdvVO> getAllAdvVOList(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		//return getSqlMapClientTemplate().queryForList("advSqlMap.getAllAdvVOList", advVO);
		return getSqlMapClientTemplate().queryForList("linkads2SqlMap.getLinkads2VOAll",advVO);
	}
	
	@Override
	public void insert(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("advSqlMap.insert",advVO);

	}
	
	@Override
	public void update(AdvVO advVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("advSqlMap.update", advVO);

	}
}
