package com.kkgame.feeop.detail.dao.impl;

import com.kkgame.feeop.detail.bean.AdvSentsListModelVO;
import com.kkgame.feeop.detail.dao.AdvSentsListModelDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class AdvSentsListModelDAOImpl implements
        AdvSentsListModelDAO {

    //新增
    public void create(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException {

    }


    public AdvSentsListModelVO getAdvSentsListModelVO(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException {
        return null;
    }

    public List<AdvSentsListModelVO> getAdvSentsListModelVOList(AdvSentsListModelVO advSentsListModelVO)
            throws DatabaseException {


        Criteria criatira = new Criteria();

        if (!"".equals(advSentsListModelVO.getImei()) && null != advSentsListModelVO.getImei()) {
            criatira.and("imei").is(advSentsListModelVO.getImei());
        }
        if (!"".equals(advSentsListModelVO.getCoo_id()) && 0 != advSentsListModelVO.getCoo_id()) {
            criatira.and("coo_id").is(advSentsListModelVO.getCoo_id());
        }
        if (!"".equals(advSentsListModelVO.getSdkversion()) && 0 != advSentsListModelVO.getSdkversion()) {
            criatira.and("sdkversion").is(advSentsListModelVO.getSdkversion());
        }
        Query query = new Query(criatira);
        query.limit(50);
        query.with(new Sort(Sort.Direction.DESC, "cdate"));
        return (List<AdvSentsListModelVO>) mongoTemplate.find(query, AdvSentsListModelVO.class, advSentsListModelVO.getTable());

    }


    //修改
    public void update(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException {

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
