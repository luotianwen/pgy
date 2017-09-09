	package com.kkgame.feeop.detail.dao.impl;

	import com.kkgame.feeop.detail.bean.SdkUsersModelVO;
	import com.kkgame.feeop.detail.dao.SdkUsersModelDAO;
	import com.kkgame.feeop.util.DatabaseException;
	import com.mongodb.DBObject;
	import org.springframework.data.mongodb.core.MongoTemplate;
	import org.springframework.data.mongodb.core.query.Criteria;
	import org.springframework.data.mongodb.core.query.Query;
	import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.regex.Pattern;

	public class SdkUsersModelDAOImpl  extends SqlMapClientDaoSupport implements
		SdkUsersModelDAO { 

	//新增
	public void create(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException {

	}
	 
	
	public SdkUsersModelVO getSdkUsersModelVO(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException {
		return null;
	}
	
	public List<SdkUsersModelVO> getSdkUsersModelVOList(SdkUsersModelVO sdkUsersModelVO)
			throws DatabaseException {
		return null;// getSqlMapClientTemplate().queryForList("sdkUsersModelSqlMap.getSdkUsersModelVOList", sdkUsersModelVO);
	}
	
	
	//修改
	public void update(SdkUsersModelVO sdkUsersModelVO) throws DatabaseException {

	}


	public List<SdkUsersModelVO> getSdkUsersModelProjectList(
			SdkUsersModelVO sdkUsersModelVO) {


	/*	Criteria criatira = new Criteria();

		if (!"".equals(sdkUsersModelVO.getCoo_id()) && 0 != sdkUsersModelVO.getCoo_id()) {
			criatira.and("coo_id").is(sdkUsersModelVO.getCoo_id());
		}
		criatira.and("creator").is(1);
		Query query = new Query(criatira);
		return (List<SdkUsersModelVO>) mongoTemplate.find(query, SdkUsersModelVO.class, sdkUsersModelVO.getTable());
		 */
	    return getSqlMapClientTemplate().queryForList("sdkUsersModelSqlMap.getSdkUsersModelProjectList", sdkUsersModelVO);
		
	}

	@Override
	public List<SdkUsersModelVO> getSdkUsersModelProjectValidList(SdkUsersModelVO sdkUsersModelVO) {

		/*Pattern pattern = Pattern.compile("[A-Za-z].*[A-Za-z]",Pattern.CASE_INSENSITIVE);
		Criteria criatira = new Criteria("imei").regex(pattern.toString());

		if (!"".equals(sdkUsersModelVO.getCoo_id()) && 0 != sdkUsersModelVO.getCoo_id()) {
			criatira.and("coo_id").is(sdkUsersModelVO.getCoo_id());
		}

		Query query = new Query(criatira);

		long a=mongoTemplate.count(query, sdkUsersModelVO.getTable());
		List<SdkUsersModelVO>  sdkUsersModelVOs=new ArrayList<>();
		SdkUsersModelVO sdkUsersModelVO2=new SdkUsersModelVO();
		sdkUsersModelVO2.setXcou((int)a);
		sdkUsersModelVOs.add(sdkUsersModelVO2);*/


		return getSqlMapClientTemplate().queryForList("sdkUsersModelSqlMap.getSdkUsersModelProjectValidList", sdkUsersModelVO);
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
