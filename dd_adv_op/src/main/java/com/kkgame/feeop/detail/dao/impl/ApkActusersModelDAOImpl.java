package com.kkgame.feeop.detail.dao.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.ApkUsersModelVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.detail.bean.ApkActusersModelVO;
import com.kkgame.feeop.detail.dao.ApkActusersModelDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ApkActusersModelDAOImpl extends SqlMapClientDaoSupport  implements ApkActusersModelDAO {

	// 新增
	public void create(ApkActusersModelVO apkActusersModelVO) throws DatabaseException {

	}

	public ApkActusersModelVO getApkActusersModelVO(ApkActusersModelVO apkActusersModelVO) throws DatabaseException {
		return null;
	}

	public List<ApkActusersModelVO> getApkActusersModelVOList(ApkActusersModelVO apkActusersModelVO)
			throws DatabaseException {

/*
		Criteria criatira = new Criteria();

		if (!"".equals(apkActusersModelVO.getImei()) && null != apkActusersModelVO.getImei()) {
			criatira.and("imei").is(apkActusersModelVO.getImei());
		}
		if (!"".equals(apkActusersModelVO.getCoo_id()) && 0 != apkActusersModelVO.getCoo_id()) {
			criatira.and("coo_id").is(apkActusersModelVO.getCoo_id());
		}

		if (!"".equals(apkActusersModelVO.getSdkversion()) && 0 != apkActusersModelVO.getSdkversion()) {
			criatira.and("sdkversion").is(apkActusersModelVO.getSdkversion());
		}
		Query query = new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		return (List<ApkActusersModelVO>) mongoTemplate.find(query, ApkActusersModelVO.class, apkActusersModelVO.getTable());*/
		 return getSqlMapClientTemplate().queryForList("apkActusersModelSqlMap.getApkActusersModelVOList",apkActusersModelVO);
	}

	// 修改
	public void update(ApkActusersModelVO apkActusersModelVO) throws DatabaseException {
		//getSqlMapClientTemplate().update("apkActusersModelSqlMap.update", apkActusersModelVO);
	}


	public List<ApkActusersModelVO> getApkGuardModelVOList(ApkActusersModelVO apkActusersModelVO)
			throws DatabaseException {
		// TODO Auto-generated method stub
		//return getSqlMapClientTemplate().queryForList("apkActusersModelSqlMap.getApkGuardModelVOList",apkActusersModelVO);
		return null;
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
