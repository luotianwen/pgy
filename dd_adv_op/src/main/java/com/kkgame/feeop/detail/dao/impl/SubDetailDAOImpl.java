package com.kkgame.feeop.detail.dao.impl;

import com.kkgame.feeop.detail.bean.SubDetailVO;
import com.kkgame.feeop.detail.dao.SubDetailDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class SubDetailDAOImpl implements SubDetailDAO {

	@Override
	public List<SubDetailVO> getSubDetailVOList(SubDetailVO subDetailVO) throws DatabaseException {

		Criteria criatira = new Criteria();

		if(!"".equals(subDetailVO.getImei())&&null!=subDetailVO.getImei()) {
			criatira.and("imei").is(subDetailVO.getImei());

		}
		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<SubDetailVO> a =null       ;
		try {
			 a= (List<SubDetailVO>) mongoTemplate.find(query, SubDetailVO.class, "adv_click_list_sub_" + subDetailVO.getCdate() );
		}catch (Exception e){
			e.printStackTrace();
		}
		return a;
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
