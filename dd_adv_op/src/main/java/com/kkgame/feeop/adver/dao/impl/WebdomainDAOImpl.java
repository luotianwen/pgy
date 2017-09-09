	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.WebdomainVO;
import com.kkgame.feeop.adver.dao.WebdomainDAO;
import com.kkgame.feeop.util.DatabaseException;

public class WebdomainDAOImpl extends SqlMapClientDaoSupport implements
		WebdomainDAO { 

	//新增
	public void create(WebdomainVO webdomainVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("webdomainSqlMap.create", webdomainVO);
	}
	 
	
	public WebdomainVO getWebdomainVO(WebdomainVO webdomainVO) throws DatabaseException {
		return (WebdomainVO) getSqlMapClientTemplate().queryForObject("webdomainSqlMap.getWebdomainVO", webdomainVO);
	}

	public void delete(WebdomainVO webdomainVO) {
		getSqlMapClientTemplate().delete("webdomainSqlMap.delete", webdomainVO);
	}

	public List<WebdomainVO> getWebdomainVOList(WebdomainVO webdomainVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("webdomainSqlMap.getWebdomainVOListCount", webdomainVO);
		webdomainVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("webdomainSqlMap.getWebdomainVOList", webdomainVO);
	}
	
	
	//修改
	public void update(WebdomainVO webdomainVO) throws DatabaseException {
		getSqlMapClientTemplate().update("webdomainSqlMap.update", webdomainVO);
	}
	
 

	 
	 

}
