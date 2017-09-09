package com.kkgame.feeop.adver.service.impl;

import com.kkgame.feeop.adver.bean.AdvLinkmanVO;
import com.kkgame.feeop.adver.dao.AdvLinkmanDAO;
import com.kkgame.feeop.adver.service.AdvLinkmanService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/14
 *          Time: 17:41
 * @author: XJ
 * @since 3.0
 */
public class AdvLinkmanServiceImpl implements AdvLinkmanService {
    private AdvLinkmanDAO advLinkmanDAO;

    @Override
    public List<AdvLinkmanVO> getAdvLinkmanList(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        return advLinkmanDAO.getAdvLinkmanList(advLinkmanVO);
    }

    @Override
    public AdvLinkmanVO getAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        return advLinkmanDAO.getAdvLinkman(advLinkmanVO);
    }

    @Override
    public void insertAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        advLinkmanDAO.insertAdvLinkman(advLinkmanVO);
    }

    @Override
    public void deleteAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        advLinkmanDAO.deleteAdvLinkman(advLinkmanVO);
    }

    @Override
    public void updateAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException {
        advLinkmanDAO.updateAdvLinkman(advLinkmanVO);
    }

    @Override
    public List<AdvLinkmanVO> getAllAdvLinkman() throws DatabaseException {
        return advLinkmanDAO.getAllAdvLinkman();
    }

    public AdvLinkmanDAO getAdvLinkmanDAO() {
        return advLinkmanDAO;
    }

    public void setAdvLinkmanDAO(AdvLinkmanDAO advLinkmanDAO) {
        this.advLinkmanDAO = advLinkmanDAO;
    }
}
