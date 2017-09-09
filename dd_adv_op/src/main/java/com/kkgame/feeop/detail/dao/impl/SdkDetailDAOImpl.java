package com.kkgame.feeop.detail.dao.impl;

import com.kkgame.feeop.detail.bean.SdkDetailVO;
import com.kkgame.feeop.detail.dao.SdkDetailDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

public class SdkDetailDAOImpl implements SdkDetailDAO {

	@Override
	public List<SdkDetailVO> getSdkDetailVOList(SdkDetailVO sdkDetailVO) throws DatabaseException {

		Criteria criatira = new Criteria();

		if(!"".equals(sdkDetailVO.getImei())&&null!=sdkDetailVO.getImei()) {
			criatira.and("imei").is(sdkDetailVO.getImei());

		}
		if(sdkDetailVO.getType()!=-1) {
			criatira.and("type").is(sdkDetailVO.getType());

		}
		if(sdkDetailVO.getPkgid()!=null&&!"".equals(sdkDetailVO.getPkgid())) {
			criatira.and("pkgid").is(sdkDetailVO.getPkgid());

		}

		Query query=new Query(criatira);
		query.limit(50);
		query.with(new Sort(Sort.Direction.DESC, "cdate"));
		List<SdkDetailVO> a =null       ;
		try {
			 a= (List<SdkDetailVO>) mongoTemplate.find(query, SdkDetailVO.class, "adv_feeback_list_dssdk_" + sdkDetailVO.getCdate() );
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
