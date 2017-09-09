package com.kkgame.feeop.adver.dao.impl;

import com.kkgame.feeop.adver.bean.SubscribeVO;
import com.kkgame.feeop.adver.dao.SubscribeDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscribeDAOImpl extends SqlMapClientDaoSupport implements
        SubscribeDAO {

    //新增
    public void create(SubscribeVO subscribeVO) throws DatabaseException {
        int id = (Integer) getSqlMapClientTemplate().insert("subscribeSqlMap.create", subscribeVO);
    }


    public SubscribeVO getSubscribeVO(SubscribeVO subscribeVO) throws DatabaseException {
        return (SubscribeVO) getSqlMapClientTemplate().queryForObject("subscribeSqlMap.getSubscribeVO", subscribeVO);
    }

    @Override
    public void copy(SubscribeVO subscribeVO) {
        getSqlMapClientTemplate().insert("subscribeSqlMap.copySubscribeVO", subscribeVO);
    }

    @Override
    public List<SubscribeVO> getSelectSubscribeVOList(SubscribeVO subscribeVO) {
        return getSqlMapClientTemplate().queryForList("subscribeSqlMap.getSelectSubscribeVOList", subscribeVO);
    }

    @Override
    public void insertSubs(List<SubscribeVO> subscribeVOs) {
        Map map = new  HashMap<String, List<SubscribeVO>>();
        map.put("list", subscribeVOs);
        getSqlMapClientTemplate().insert("subscribeSqlMap.insertSubscribeVOList", map);
    }


    public List<SubscribeVO> getSubscribeVOList(SubscribeVO subscribeVO)
            throws DatabaseException {
        int count = (Integer) getSqlMapClientTemplate().queryForObject("subscribeSqlMap.getSubscribeVOListCount", subscribeVO);
        subscribeVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("subscribeSqlMap.getSubscribeVOList", subscribeVO);
    }


    //修改
    public void update(SubscribeVO subscribeVO) throws DatabaseException {
        getSqlMapClientTemplate().update("subscribeSqlMap.update", subscribeVO);
    }


}
