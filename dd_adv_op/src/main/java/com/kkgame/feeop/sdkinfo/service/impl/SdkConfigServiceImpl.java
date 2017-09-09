package com.kkgame.feeop.sdkinfo.service.impl;

import com.kkgame.feeop.sdkinfo.bean.SdkConfigVO;
import com.kkgame.feeop.sdkinfo.dao.SdkConfigDAO;
import com.kkgame.feeop.sdkinfo.service.SdkConfigService;
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
public class SdkConfigServiceImpl implements SdkConfigService {

    private SdkConfigDAO sdkConfigDAO;

    @Override
    public List<SdkConfigVO> getSdkConfigVOList(SdkConfigVO sdkConfigVO) throws DatabaseException {
        return sdkConfigDAO.getSdkConfigVOList(sdkConfigVO);
    }

    @Override
    public List<SdkConfigVO> getAllSdkConfigVOList(SdkConfigVO sdkConfigVO) throws DatabaseException {
        return sdkConfigDAO.getAllSdkConfigVOList(sdkConfigVO);
    }

    @Override
    public SdkConfigVO getSdkConfigVO(SdkConfigVO sdkConfigVO) throws DatabaseException {
        return sdkConfigDAO.getSdkConfigVO(sdkConfigVO);
    }

    @Override
    public void insert(SdkConfigVO sdkConfigVO) throws DatabaseException {
        sdkConfigDAO.insert(sdkConfigVO);
    }

    @Override
    public void update(SdkConfigVO sdkConfigVO) throws DatabaseException {
        sdkConfigDAO.update(sdkConfigVO);
    }

    @Override
    public void delete(SdkConfigVO sdkConfigVO) throws DatabaseException {
        sdkConfigDAO.delete(sdkConfigVO);
    }

    public SdkConfigDAO getSdkConfigDAO() {
        return sdkConfigDAO;
    }

    public void setSdkConfigDAO(SdkConfigDAO sdkConfigDAO) {
        this.sdkConfigDAO = sdkConfigDAO;
    }
}
