	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.PcustomerVO;
import com.kkgame.feeop.adver.dao.PcustomerDAO;
import com.kkgame.feeop.util.DatabaseException;

public class PcustomerDAOImpl extends SqlMapClientDaoSupport implements
		PcustomerDAO { 

	//新增
	public void create(PcustomerVO pcustomerVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("pcustomerSqlMap.create", pcustomerVO);
	}
	 
	
	public PcustomerVO getPcustomerVO(PcustomerVO pcustomerVO) throws DatabaseException {
		return (PcustomerVO) getSqlMapClientTemplate().queryForObject("pcustomerSqlMap.getPcustomerVO", pcustomerVO);
	}



	public List<PcustomerVO> getPcustomerVOList(PcustomerVO pcustomerVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("pcustomerSqlMap.getPcustomerVOListCount", pcustomerVO);
		pcustomerVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("pcustomerSqlMap.getPcustomerVOList", pcustomerVO);
	}
	
	
	//修改
	public void update(PcustomerVO pcustomerVO) throws DatabaseException {
		getSqlMapClientTemplate().update("pcustomerSqlMap.update", pcustomerVO);
	}




	//新增
	public void createIframe(PcustomerVO pcustomerVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("pcustomerSqlMap.createIframe", pcustomerVO);
	}


	public PcustomerVO getPcustomerIframeVO(PcustomerVO pcustomerVO) throws DatabaseException {
		return (PcustomerVO) getSqlMapClientTemplate().queryForObject("pcustomerSqlMap.getPcustomerIframeVO", pcustomerVO);
	}



	public List<PcustomerVO> getPcustomerIframeVOList(PcustomerVO pcustomerVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("pcustomerSqlMap.getPcustomerIframeVOListCount", pcustomerVO);
		pcustomerVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("pcustomerSqlMap.getPcustomerIframeVOList", pcustomerVO);
	}


	//修改
	public void updateIframe(PcustomerVO pcustomerVO) throws DatabaseException {
		getSqlMapClientTemplate().update("pcustomerSqlMap.updateIframe", pcustomerVO);
	}



}
