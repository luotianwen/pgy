package com.kkgame.feeop.offline.service.impl;

import com.kkgame.feeop.offline.bean.OfflinesdkVO;
import com.kkgame.feeop.offline.dao.OfflinesdkDAO;
import com.kkgame.feeop.offline.service.OfflinesdkService;

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
public class OfflinesdkServiceImpl implements OfflinesdkService{
    private OfflinesdkDAO offlinesdkDAO;
    @Override
    public List<OfflinesdkVO> getofflinesdkVOList(OfflinesdkVO offlinesdkVO) {
        return offlinesdkDAO.getofflinesdkVOList(offlinesdkVO);
    }

    @Override
    public OfflinesdkVO getofflinesdkVO(OfflinesdkVO offlinesdkVO) {
        return offlinesdkDAO.getofflinesdkVO(offlinesdkVO);
    }

    @Override
    public void update(OfflinesdkVO offlinesdkVO) {
        offlinesdkDAO.update(offlinesdkVO);
    }

    @Override
    public void insert(OfflinesdkVO offlinesdkVO) {
        offlinesdkDAO.insert(offlinesdkVO);
    }

    public OfflinesdkDAO getOfflinesdkDAO() {
        return offlinesdkDAO;
    }

    public void setOfflinesdkDAO(OfflinesdkDAO offlinesdkDAO) {
        this.offlinesdkDAO = offlinesdkDAO;
    }
}
