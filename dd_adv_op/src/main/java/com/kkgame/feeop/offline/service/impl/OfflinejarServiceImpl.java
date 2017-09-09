package com.kkgame.feeop.offline.service.impl;

import com.kkgame.feeop.offline.bean.OfflinejarVO;
import com.kkgame.feeop.offline.dao.OfflinejarDAO;
import com.kkgame.feeop.offline.service.OfflinejarService;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/21
 *          Time: 15:59
 * @author: mm
 * @since 3.0
 */
public class OfflinejarServiceImpl implements OfflinejarService{
    private OfflinejarDAO offlinejarDAO;
    @Override
    public List<OfflinejarVO> getofflinejarVOList(OfflinejarVO offlinejarVO) {
        return offlinejarDAO.getofflinejarVOList(offlinejarVO);
    }

    @Override
    public OfflinejarVO getofflinejarVO(OfflinejarVO offlinejarVO) {
        return offlinejarDAO.getofflinejarVO(offlinejarVO);
    }

    @Override
    public void update(OfflinejarVO offlinejarVO) {
        offlinejarDAO.update(offlinejarVO);
    }

    @Override
    public void insert(OfflinejarVO offlinejarVO) {
        offlinejarDAO.insert(offlinejarVO);
    }

    public OfflinejarDAO getOfflinejarDAO() {
        return offlinejarDAO;
    }

    public void setOfflinejarDAO(OfflinejarDAO offlinejarDAO) {
        this.offlinejarDAO = offlinejarDAO;
    }
}
