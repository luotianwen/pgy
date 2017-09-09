	package com.kkgame.feeop.record.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.record.bean.AdjustVO;
import com.kkgame.feeop.record.dao.AdjustDAO;
import com.kkgame.feeop.util.DatabaseException;

public class AdjustDAOImpl extends SqlMapClientDaoSupport implements
		AdjustDAO { 

	//新增
	public void create(AdjustVO adjustVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("adjustSqlMap.create", adjustVO);
	}
	 
	
	public AdjustVO getAdjustVO(AdjustVO adjustVO) throws DatabaseException {
		return (AdjustVO) getSqlMapClientTemplate().queryForObject("adjustSqlMap.getAdjustVO", adjustVO);
	}
	
	public List<AdjustVO> getAdjustVOList(AdjustVO adjustVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("adjustSqlMap.getAdjustVOListCount", adjustVO);
		adjustVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("adjustSqlMap.getAdjustVOList", adjustVO);
	}
	
	
	//修改
	public void update(AdjustVO adjustVO) throws DatabaseException {
		getSqlMapClientTemplate().update("adjustSqlMap.update", adjustVO);
	}
	
 

	 
	 

}
