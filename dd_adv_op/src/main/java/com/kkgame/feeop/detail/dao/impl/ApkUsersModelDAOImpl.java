	package com.kkgame.feeop.detail.dao.impl;
import java.util.List;

import com.kkgame.feeop.detail.bean.AdvSentsListModelVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.detail.bean.ApkUsersModelVO;
import com.kkgame.feeop.detail.dao.ApkUsersModelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ApkUsersModelDAOImpl extends SqlMapClientDaoSupport implements
		ApkUsersModelDAO { 

	//新增
	public void create(ApkUsersModelVO apkUsersModelVO) throws DatabaseException {

	}
	 
	
	public ApkUsersModelVO getApkUsersModelVO(ApkUsersModelVO apkUsersModelVO) throws DatabaseException {
		return null;
	}
	
	public List<ApkUsersModelVO> getApkUsersModelVOList(ApkUsersModelVO apkUsersModelVO)
			throws DatabaseException {

/*
		Criteria criatira = new Criteria();

		if (!"".equals(apkUsersModelVO.getImei()) && null != apkUsersModelVO.getImei()) {
			criatira.and("imei").is(apkUsersModelVO.getImei());
		}
		if (!"".equals(apkUsersModelVO.getCoo_id()) && 0 != apkUsersModelVO.getCoo_id()) {
			criatira.and("coo_id").is(apkUsersModelVO.getCoo_id());
		}

		if (!"".equals(apkUsersModelVO.getSdkversion()) && 0 != apkUsersModelVO.getSdkversion()) {
			criatira.and("sdkversion").is(apkUsersModelVO.getSdkversion());
		}
		Query query = new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		return (List<ApkUsersModelVO>) mongoTemplate.find(query, ApkUsersModelVO.class, apkUsersModelVO.getTable());*/
		return getSqlMapClientTemplate().queryForList("apkUsersModelSqlMap.getApkUsersModelVOList", apkUsersModelVO);
	}
	
	
	//修改
	public void update(ApkUsersModelVO apkUsersModelVO) throws DatabaseException {

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
