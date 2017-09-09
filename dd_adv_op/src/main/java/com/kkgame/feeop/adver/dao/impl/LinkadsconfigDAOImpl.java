	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.LinkadsconfigVO;
import com.kkgame.feeop.adver.dao.LinkadsconfigDAO;
import com.kkgame.feeop.util.DatabaseException;

public class LinkadsconfigDAOImpl extends SqlMapClientDaoSupport implements
		LinkadsconfigDAO { 

	//新增
	public void create(LinkadsconfigVO linkadsconfigVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("linkadsconfigSqlMap.create", linkadsconfigVO);
	}
	 
	
	public LinkadsconfigVO getLinkadsconfigVO(LinkadsconfigVO linkadsconfigVO) throws DatabaseException {
		return (LinkadsconfigVO) getSqlMapClientTemplate().queryForObject("linkadsconfigSqlMap.getLinkadsconfigVO", linkadsconfigVO);
	}
	
	public List<LinkadsconfigVO> getLinkadsconfigVOList(LinkadsconfigVO linkadsconfigVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("linkadsconfigSqlMap.getLinkadsconfigVOListCount", linkadsconfigVO);
		linkadsconfigVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("linkadsconfigSqlMap.getLinkadsconfigVOList", linkadsconfigVO);
	}
	
	
	//修改
	public void update(LinkadsconfigVO linkadsconfigVO) throws DatabaseException {
		getSqlMapClientTemplate().update("linkadsconfigSqlMap.update", linkadsconfigVO);
	}
	
 

	 
	 

}
