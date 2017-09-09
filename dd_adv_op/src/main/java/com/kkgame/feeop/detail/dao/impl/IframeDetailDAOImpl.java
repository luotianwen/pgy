package com.kkgame.feeop.detail.dao.impl;

import com.kkgame.feeop.detail.bean.IframeDetailVO;
import com.kkgame.feeop.detail.bean.IframeVO;
import com.kkgame.feeop.detail.dao.IframeDetailDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class IframeDetailDAOImpl implements IframeDetailDAO {

	@Override
	public List<IframeDetailVO> getIframeDetailVOList(IframeDetailVO iframeDetailVO) throws DatabaseException {

		Criteria criatira = new Criteria();

		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<IframeDetailVO> a =null       ;
		try {
			 a= (List<IframeDetailVO>) mongoTemplate.find(query, IframeDetailVO.class, "adv_iframe_active_" + iframeDetailVO.getCdate() );
		}catch (Exception e){
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<IframeVO> getIframeDataVOList(IframeDetailVO iframeDataVO) {
		Criteria criatira = new Criteria();

		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<IframeVO> a =null       ;
		try {
			a= (List<IframeVO>) mongoTemplate.find(query, IframeVO.class, "adv_iframe_list_" + iframeDataVO.getCdate() );
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
