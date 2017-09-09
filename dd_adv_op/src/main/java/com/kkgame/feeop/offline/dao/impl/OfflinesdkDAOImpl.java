package com.kkgame.feeop.offline.dao.impl;

import com.kkgame.feeop.offline.bean.OfflinesdkVO;
import com.kkgame.feeop.offline.dao.OfflinesdkDAO;
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
public class OfflinesdkDAOImpl extends SqlMapClientDaoSupport implements OfflinesdkDAO{
    @Override
    public List<OfflinesdkVO> getofflinesdkVOList(OfflinesdkVO offlinesdkVO) {
        int count = (int)getSqlMapClientTemplate().queryForObject("offlinesdkSqlMap.getOfflinesdkVOListCount",offlinesdkVO);
        offlinesdkVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("offlinesdkSqlMap.getOfflinesdkVOList",offlinesdkVO);
    }

    @Override
    public OfflinesdkVO getofflinesdkVO(OfflinesdkVO offlinesdkVO) {
        return (OfflinesdkVO)getSqlMapClientTemplate().queryForObject("offlinesdkSqlMap.getOfflinesdkVO",offlinesdkVO);
    }

    @Override
    public void insert(OfflinesdkVO offlinesdkVO) {
        getSqlMapClientTemplate().insert("offlinesdkSqlMap.insert",offlinesdkVO);
    }

    @Override
    public void update(OfflinesdkVO offlinesdkVO) {
        getSqlMapClientTemplate().update("offlinesdkSqlMap.update",offlinesdkVO) ;
    }
}
