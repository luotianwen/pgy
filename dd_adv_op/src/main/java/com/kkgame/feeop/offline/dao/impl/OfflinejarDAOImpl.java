package com.kkgame.feeop.offline.dao.impl;

import com.kkgame.feeop.offline.bean.OfflinejarVO;
import com.kkgame.feeop.offline.dao.OfflinejarDAO;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/21
 *          Time: 16:06
 * @author: mm
 * @since 3.0
 */
public class OfflinejarDAOImpl extends SqlMapClientDaoSupport implements OfflinejarDAO{
    @Override
    public List<OfflinejarVO> getofflinejarVOList(OfflinejarVO offlinejarVO) {
        int count = (int)getSqlMapClientTemplate().queryForObject("offlinejarSqlMap.getOfflinejarVOListCount",offlinejarVO);
        offlinejarVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("offlinejarSqlMap.getOfflinejarVOList",offlinejarVO);
    }

    @Override
    public OfflinejarVO getofflinejarVO(OfflinejarVO offlinejarVO) {
        return (OfflinejarVO)getSqlMapClientTemplate().queryForObject("offlinejarSqlMap.getOfflinejarVO",offlinejarVO);
    }

    @Override
    public void insert(OfflinejarVO offlinejarVO) {
        getSqlMapClientTemplate().insert("offlinejarSqlMap.insert",offlinejarVO);
    }

    @Override
    public void update(OfflinejarVO offlinejarVO) {
        getSqlMapClientTemplate().update("offlinejarSqlMap.update",offlinejarVO) ;
    }
}
