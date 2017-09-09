package com.kkgame.feeop.offline.service.impl;

import com.kkgame.feeop.offline.bean.OfflineapkVO;
import com.kkgame.feeop.offline.dao.OfflineapkDAO;
import com.kkgame.feeop.offline.service.OfflineapkService;

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
public class OfflineapkServiceImpl implements OfflineapkService{
    private OfflineapkDAO offlineapkDAO;
    @Override
    public List<OfflineapkVO> getofflineapkVOList(OfflineapkVO offlineapkVO) {
        return offlineapkDAO.getofflineapkVOList(offlineapkVO);
    }

    @Override
    public OfflineapkVO getofflineapkVO(OfflineapkVO offlineapkVO) {
        return offlineapkDAO.getofflineapkVO(offlineapkVO);
    }

    @Override
    public void update(OfflineapkVO offlineapkVO) {
        offlineapkDAO.update(offlineapkVO);
    }

    @Override
    public void insert(OfflineapkVO offlineapkVO) {
        offlineapkDAO.insert(offlineapkVO);
    }

    public OfflineapkDAO getOfflineapkDAO() {
        return offlineapkDAO;
    }

    public void setOfflineapkDAO(OfflineapkDAO offlineapkDAO) {
        this.offlineapkDAO = offlineapkDAO;
    }
}
