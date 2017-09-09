	package com.kkgame.feeop.sdkinfo.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.sdkinfo.bean.SdkdomainVO;
import com.kkgame.feeop.sdkinfo.dao.SdkdomainDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SdkdomainDAOImpl extends SqlMapClientDaoSupport implements
		SdkdomainDAO { 

	//新增
	public void create(SdkdomainVO sdkdomainVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("sdkdomainSqlMap.create", sdkdomainVO);
	}
	 
	
	public SdkdomainVO getSdkdomainVO(SdkdomainVO sdkdomainVO) throws DatabaseException {
		return (SdkdomainVO) getSqlMapClientTemplate().queryForObject("sdkdomainSqlMap.getSdkdomainVO", sdkdomainVO);
	}

	public void delete(SdkdomainVO sdkdomainVO) {
		getSqlMapClientTemplate().delete("sdkdomainSqlMap.delete", sdkdomainVO);
	}

	public List<SdkdomainVO> getSdkdomainVOList(SdkdomainVO sdkdomainVO)
			throws DatabaseException {

		return getSqlMapClientTemplate().queryForList("sdkdomainSqlMap.getSdkdomainVOList", sdkdomainVO);
	}
	
	
	//修改
	public void update(SdkdomainVO sdkdomainVO) throws DatabaseException {
		getSqlMapClientTemplate().update("sdkdomainSqlMap.update", sdkdomainVO);
	}
	
 

	 
	 

}
