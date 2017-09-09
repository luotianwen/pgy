package com.kkgame.feeop.adver.dao.impl;

import com.kkgame.feeop.adver.bean.AdvLinkmanVO;
import com.kkgame.feeop.adver.dao.AdvLinkmanDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/14
 *          Time: 17:39
 * @author: XJ
 * @since 3.0
 */
public class AdvLinkmanDaoImpl extends SqlMapClientDaoSupport implements AdvLinkmanDAO {

    @Override
    public List<AdvLinkmanVO> getAdvLinkmanList(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        int count = (int) getSqlMapClientTemplate().queryForObject("advLinkmanSqlMap.getAdvLinkmanListCount", advLinkmanVO);
        advLinkmanVO.getPage().setTotalRow(count);
        return getSqlMapClientTemplate().queryForList("advLinkmanSqlMap.getAdvLinkmanList", advLinkmanVO);
    }

    @Override
    public AdvLinkmanVO getAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        return (AdvLinkmanVO) getSqlMapClientTemplate().queryForObject("advLinkmanSqlMap.getAdvLinkman", advLinkmanVO);
    }

    @Override
    public void insertAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        getSqlMapClientTemplate().insert("advLinkmanSqlMap.insertAdvLinkman", advLinkmanVO);
    }

    @Override
    public void deleteAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        getSqlMapClientTemplate().delete("advLinkmanSqlMap.deleteAdvLinkman", advLinkmanVO);
    }

    @Override
    public void updateAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        getSqlMapClientTemplate().update("advLinkmanSqlMap.updateAdvLinkman", advLinkmanVO);
    }

    @Override
    public List<AdvLinkmanVO> getAllAdvLinkman() throws DatabaseException {
        return getSqlMapClientTemplate().queryForList("advLinkmanSqlMap.getAllAdvLinkman");
    }
}
