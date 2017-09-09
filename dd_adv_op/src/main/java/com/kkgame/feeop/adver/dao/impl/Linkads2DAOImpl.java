	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.Linkads2VO;
import com.kkgame.feeop.adver.dao.Linkads2DAO;
import com.kkgame.feeop.util.DatabaseException;

public class Linkads2DAOImpl extends SqlMapClientDaoSupport implements
		Linkads2DAO { 

	//新增
	public void create(Linkads2VO linkads2VO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("linkads2SqlMap.create", linkads2VO);
	}
	 
	
	public Linkads2VO getLinkads2VO(Linkads2VO linkads2VO) throws DatabaseException {
		return (Linkads2VO) getSqlMapClientTemplate().queryForObject("linkads2SqlMap.getLinkads2VO", linkads2VO);
	}

	public void copy(Linkads2VO linkads2VO) throws DatabaseException {
		getSqlMapClientTemplate().insert("linkads2SqlMap.copy", linkads2VO);
	}

	public List<Linkads2VO> getLinkads2VOList(Linkads2VO linkads2VO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("linkads2SqlMap.getLinkads2VOListCount", linkads2VO);
		linkads2VO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("linkads2SqlMap.getLinkads2VOList", linkads2VO);
	}
	
	
	//修改
	public void update(Linkads2VO linkads2VO) throws DatabaseException {
		getSqlMapClientTemplate().update("linkads2SqlMap.update", linkads2VO);
	}
	
 

	 
	 

}
