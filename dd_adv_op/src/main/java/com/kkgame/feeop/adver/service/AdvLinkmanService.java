package com.kkgame.feeop.adver.service;

import com.kkgame.feeop.adver.bean.AdvLinkmanVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/14
 *          Time: 17:33
 * @author: XJ
 * @since 3.0
 */
public interface AdvLinkmanService {
    List<AdvLinkmanVO> getAdvLinkmanList(AdvLinkmanVO advLinkmanVO) throws DatabaseException;

    AdvLinkmanVO getAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException;

    void insertAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException;

    void deleteAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException;

    void updateAdvLinkman(AdvLinkmanVO advLinkmanVO) throws DatabaseException;

    List<AdvLinkmanVO> getAllAdvLinkman() throws DatabaseException;
}