package com.kkgame.feeop.offline.dao.impl;

import com.kkgame.feeop.offline.bean.OfflineapkVO;
import com.kkgame.feeop.offline.dao.OfflineapkDAO;
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
public class OfflineapkDAOImpl extends SqlMapClientDaoSupport implements OfflineapkDAO{
    @Override
    public List<OfflineapkVO> getofflineapkVOList(OfflineapkVO offlineapkVO) {
        int count = (int)getSqlMapClientTemplate().queryForObject("offlineapkSqlMap.getOfflineapkVOListCount",offlineapkVO);
        offlineapkVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("offlineapkSqlMap.getOfflineapkVOList",offlineapkVO);
    }

    @Override
    public OfflineapkVO getofflineapkVO(OfflineapkVO offlineapkVO) {
        return (OfflineapkVO)getSqlMapClientTemplate().queryForObject("offlineapkSqlMap.getOfflineapkVO",offlineapkVO);
    }

    @Override
    public void insert(OfflineapkVO offlineapkVO) {
        getSqlMapClientTemplate().insert("offlineapkSqlMap.insert",offlineapkVO);
    }

    @Override
    public void update(OfflineapkVO offlineapkVO) {
        getSqlMapClientTemplate().update("offlineapkSqlMap.update",offlineapkVO) ;
    }
}
