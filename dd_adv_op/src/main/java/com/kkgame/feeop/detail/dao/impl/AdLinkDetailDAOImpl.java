package com.kkgame.feeop.detail.dao.impl;

import java.util.List;

import com.kkgame.feeop.detail.bean.AdvFeebackListClickModelVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.detail.bean.AdLinkDetailVO;
import com.kkgame.feeop.detail.dao.AdLinkDetailDAO;
import com.kkgame.feeop.util.DatabaseException;

public class AdLinkDetailDAOImpl  implements AdLinkDetailDAO {

	@Override
	public List<AdLinkDetailVO> getAdLinkDetailVOList(AdLinkDetailVO adLinkDetailVO) throws DatabaseException {

		Criteria criatira = new Criteria();

		if(!"".equals(adLinkDetailVO.getImei())&&null!=adLinkDetailVO.getImei()) {
			criatira.and("imei").is(adLinkDetailVO.getImei());

		}
		if(!"".equals(adLinkDetailVO.getCoo_id())&&0!=adLinkDetailVO.getCoo_id()) {
			criatira.and("coo_id").is(adLinkDetailVO.getCoo_id());
		}
		if(!"".equals(adLinkDetailVO.getLinkType())&&null!=adLinkDetailVO.getLinkType()) {
			criatira.and("linkType").is(adLinkDetailVO.getLinkType());

		}
		if(!"".equals(adLinkDetailVO.getClickType())&&0!=adLinkDetailVO.getClickType()) {
			criatira.and("clickType").is(adLinkDetailVO.getClickType());
		}
		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<AdLinkDetailVO> a =null       ;
		try {
			 a= (List<AdLinkDetailVO>) mongoTemplate.find(query, AdLinkDetailVO.class, "adv_feeback_list_link_" + adLinkDetailVO.getTable());
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
