package com.kkgame.feeop.sdkinfo.service.impl;

import com.kkgame.feeop.sdkinfo.bean.DesktopInfoVO;
import com.kkgame.feeop.sdkinfo.dao.DesktopInfoDAO;
import com.kkgame.feeop.sdkinfo.service.DesktopInfoService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/22
 *          Time: 18:29
 * @author: mm
 * @since 3.0
 */
public class DesktopInfoServiceImpl implements DesktopInfoService{

    private DesktopInfoDAO desktopInfoDAO;

    @Override
    public List<DesktopInfoVO> getDesktopInfoVOList(DesktopInfoVO desktopInfoVO) throws DatabaseException {
        return desktopInfoDAO.getDesktopInfoVOList(desktopInfoVO);
    }

    @Override
    public List<DesktopInfoVO> getAllDesktopInfoVOList(DesktopInfoVO desktopInfoVO) throws DatabaseException {
        return desktopInfoDAO.getAllDesktopInfoVOList(desktopInfoVO);
    }

    @Override
    public DesktopInfoVO getDesktopInfoVO(DesktopInfoVO desktopInfoVO) throws DatabaseException {
        return desktopInfoDAO.getDesktopInfoVO(desktopInfoVO);
    }

    @Override
    public void insert(DesktopInfoVO desktopInfoVO) throws DatabaseException {
        desktopInfoDAO.insert(desktopInfoVO);
    }

    @Override
    public void update(DesktopInfoVO desktopInfoVO) throws DatabaseException {
        desktopInfoDAO.update(desktopInfoVO);
    }

    @Override
    public void delete(DesktopInfoVO desktopInfoVO) throws DatabaseException {
        desktopInfoDAO.delete(desktopInfoVO);
    }

    public DesktopInfoDAO getDesktopInfoDAO() {
        return desktopInfoDAO;
    }

    public void setDesktopInfoDAO(DesktopInfoDAO desktopInfoDAO) {
        this.desktopInfoDAO = desktopInfoDAO;
    }
}
