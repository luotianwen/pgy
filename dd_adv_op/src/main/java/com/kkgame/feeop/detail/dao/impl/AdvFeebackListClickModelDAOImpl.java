	package com.kkgame.feeop.detail.dao.impl;

	import com.kkgame.feeop.detail.bean.AdvFeebackListClickModelVO;
	import com.kkgame.feeop.detail.dao.AdvFeebackListClickModelDAO;
	import com.kkgame.feeop.util.DatabaseException;
	import com.mongodb.AggregationOutput;
	import com.mongodb.DBObject;
	import com.mongodb.util.JSON;
	import org.springframework.data.domain.Sort;
	import org.springframework.data.mongodb.core.MongoTemplate;
	import org.springframework.data.mongodb.core.aggregation.Aggregation;
	import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
	import org.springframework.data.mongodb.core.query.Criteria;
	import org.springframework.data.mongodb.core.query.Query;

	import java.util.ArrayList;
	import java.util.List;

	import static java.awt.PageAttributes.MediaType.C0;
	import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

	public class AdvFeebackListClickModelDAOImpl   implements
		AdvFeebackListClickModelDAO {

	//新增
	public void create(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException {
	}
	 
	
	public AdvFeebackListClickModelVO getAdvFeebackListClickModelVO(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException {
		//return (AdvFeebackListClickModelVO) getSqlMapClientTemplate().queryForObject("advFeebackListClickModelSqlMap.getAdvFeebackListClickModelVO", advFeebackListClickModelVO);
	   return null;
	}
	
	public List<AdvFeebackListClickModelVO> getAdvFeebackListClickModelVOList(AdvFeebackListClickModelVO advFeebackListClickModelVO)
			throws DatabaseException {


		Criteria criatira = new Criteria();

		if(!"".equals(advFeebackListClickModelVO.getImei())&&null!=advFeebackListClickModelVO.getImei()) {
			criatira.and("imei").is(advFeebackListClickModelVO.getImei());

		}
		if(!"".equals(advFeebackListClickModelVO.getCoo_id())&&0!=advFeebackListClickModelVO.getCoo_id()) {
			criatira.and("coo_id").is(advFeebackListClickModelVO.getCoo_id());
		}
		if(!"".equals(advFeebackListClickModelVO.getSdkversion())&&0!=advFeebackListClickModelVO.getSdkversion()) {
			criatira.and("sdkversion").is(advFeebackListClickModelVO.getSdkversion());
		}
		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<AdvFeebackListClickModelVO> a= (List<AdvFeebackListClickModelVO>) mongoTemplate.find(query,AdvFeebackListClickModelVO.class,advFeebackListClickModelVO.getTable());

		return a;
	}
	
	
	//修改
	public void update(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException {
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
