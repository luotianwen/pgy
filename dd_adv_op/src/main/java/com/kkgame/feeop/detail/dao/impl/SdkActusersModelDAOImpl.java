	package com.kkgame.feeop.detail.dao.impl;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.detail.bean.SdkActusersModelVO;
import com.kkgame.feeop.detail.dao.SdkActusersModelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class SdkActusersModelDAOImpl extends SqlMapClientDaoSupport implements
		SdkActusersModelDAO { 

	//新增
	public void create(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("sdkActusersModelSqlMap.create", sdkActusersModelVO);
	}
	 
	
	public SdkActusersModelVO getSdkActusersModelVO(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException {
		return (SdkActusersModelVO) getSqlMapClientTemplate().queryForObject("sdkActusersModelSqlMap.getSdkActusersModelVO", sdkActusersModelVO);
	}
	
	public List<SdkActusersModelVO> getSdkActusersModelVOList(SdkActusersModelVO sdkActusersModelVO)
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("sdkActusersModelSqlMap.getSdkActusersModelVOList", sdkActusersModelVO);
	}
	
	
	//修改
	public void update(SdkActusersModelVO sdkActusersModelVO) throws DatabaseException {
		getSqlMapClientTemplate().update("sdkActusersModelSqlMap.update", sdkActusersModelVO);
	}
	private MongoTemplate mongoTemplate;
	/**
	 * @return the mongoTemplate
	 */
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * @param mongoTemplate the mongoTemplate to set
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}






}
