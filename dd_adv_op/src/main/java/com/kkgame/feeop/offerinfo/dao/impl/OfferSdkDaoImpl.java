package com.kkgame.feeop.offerinfo.dao.impl;

import com.kkgame.feeop.offerinfo.bean.OfferSdkVO;
import com.kkgame.feeop.offerinfo.dao.OfferSdkDao;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/9
 *          Time: 17:10
 * @author: mm
 * @since 3.0
 */
public class OfferSdkDaoImpl extends SqlMapClientDaoSupport implements OfferSdkDao{
    @Override
    public List<OfferSdkVO> getOfferSdkList(OfferSdkVO offerSdkVO) {
        int count = (int)getSqlMapClientTemplate().queryForObject("offerSdkSqlMap.getOfferSdkCount", offerSdkVO);
        offerSdkVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("offerSdkSqlMap.getOfferSdkList", offerSdkVO);
    }

    @Override
    public void save(OfferSdkVO offerSdkVO) {
        getSqlMapClientTemplate().insert("offerSdkSqlMap.saveOfferSdk", offerSdkVO);
    }

    @Override
    public OfferSdkVO getOfferSdkById(OfferSdkVO offerSdkVO) {
        return (OfferSdkVO)getSqlMapClientTemplate().queryForObject("offerSdkSqlMap.getOfferSdkById", offerSdkVO);
    }

    @Override
    public void deleteOfferSdkById(OfferSdkVO offerSdkVO) {
        getSqlMapClientTemplate().delete("offerSdkSqlMap.deleteOfferSdkById", offerSdkVO);
    }

    @Override
    public List<OfferSdkVO> getOfferSdkList() {
        return getSqlMapClientTemplate().queryForList("offerSdkSqlMap.getAllOfferSdkList");
    }

    @Override
    public void update(OfferSdkVO offerSdkVO) {
        getSqlMapClientTemplate().update("offerSdkSqlMap.updateOfferSdk", offerSdkVO);
    }
}
