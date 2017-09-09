package com.kkgame.feeop.sdkinfo.dao.impl;

import com.kkgame.feeop.sdkinfo.bean.LinkAdverVO;
import com.kkgame.feeop.sdkinfo.dao.ApkInfoDAO;
import com.kkgame.feeop.sdkinfo.dao.LinkAdverDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

public class LinkAdverDAOImpl extends SqlMapClientDaoSupport implements LinkAdverDAO {

	@Override
	public List<LinkAdverVO> getAllLinkAdverVOList(LinkAdverVO linkAdverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("linkAdverSqlMap.getAllLinkAdverVOList", linkAdverVO);
	}
	
	@Override
	public LinkAdverVO getLinkAdverVO(LinkAdverVO linkAdverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return (LinkAdverVO) getSqlMapClientTemplate().queryForObject("linkAdverSqlMap.getLinkAdverVO", linkAdverVO);
	}
	
	@Override
	public List<LinkAdverVO> getLinkAdverVOList(LinkAdverVO linkAdverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("linkAdverSqlMap.getLinkAdverVOListCount",linkAdverVO);
		linkAdverVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("linkAdverSqlMap.getLinkAdverVOList", linkAdverVO);
	}
	
	@Override
	public void insert(LinkAdverVO linkAdverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("linkAdverSqlMap.insert", linkAdverVO);

	}
	
	@Override
	public void update(LinkAdverVO linkAdverVO) throws DatabaseException {

		getSqlMapClientTemplate().update("linkAdverSqlMap.update", linkAdverVO);
	}
	
	@Override
	public void delete(LinkAdverVO linkAdverVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("linkAdverSqlMap.delete", linkAdverVO);

	}
}
