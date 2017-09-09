package com.kkgame.feeop.sdkinfo.service.impl;

import com.kkgame.feeop.sdkinfo.bean.AppNameVO;
import com.kkgame.feeop.sdkinfo.dao.AppNameDAO;
import com.kkgame.feeop.sdkinfo.service.AppNameService;
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
public class AppNameServiceImpl implements AppNameService {

    private AppNameDAO appNameDAO;

    @Override
    public List<AppNameVO> getAppNameVOList(AppNameVO appNameVO) throws DatabaseException {
        return appNameDAO.getAppNameVOList(appNameVO);
    }

    @Override
    public List<AppNameVO> getAllAppNameVOList(AppNameVO appNameVO) throws DatabaseException {
        return appNameDAO.getAllAppNameVOList(appNameVO);
    }

    @Override
    public AppNameVO getAppNameVO(AppNameVO appNameVO) throws DatabaseException {
        return appNameDAO.getAppNameVO(appNameVO);
    }

    @Override
    public void insert(AppNameVO appNameVO) throws DatabaseException {
        appNameDAO.insert(appNameVO);
    }

    @Override
    public void update(AppNameVO appNameVO) throws DatabaseException {
        appNameDAO.update(appNameVO);
    }

    @Override
    public void delete(AppNameVO appNameVO) throws DatabaseException {
        appNameDAO.delete(appNameVO);
    }

    public AppNameDAO getAppNameDAO() {
        return appNameDAO;
    }

    public void setAppNameDAO(AppNameDAO appNameDAO) {
        this.appNameDAO = appNameDAO;
    }
}
