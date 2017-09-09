package com.kkgame.feeop.detail.dao.impl;

import com.kkgame.feeop.detail.bean.LinkDetailVO;
import com.kkgame.feeop.detail.dao.LinkDetailDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class LinkDetailDAOImpl implements LinkDetailDAO {

	@Override
	public List<LinkDetailVO> getLinkDetailVOList(LinkDetailVO linkDetailVO) throws DatabaseException {

		Criteria criatira = new Criteria();

		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<LinkDetailVO> a =null       ;
		try {
			 a= (List<LinkDetailVO>) mongoTemplate.find(query, LinkDetailVO.class, "adv_link_list_sub_" + linkDetailVO.getCdate() );
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
