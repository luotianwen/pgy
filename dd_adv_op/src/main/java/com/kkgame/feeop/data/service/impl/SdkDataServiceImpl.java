package com.kkgame.feeop.data.service.impl;

import com.kkgame.feeop.data.bean.SdkDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.SdkDataDAO;
import com.kkgame.feeop.data.service.SdkDataService;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/28
 *          Time: 17:25
 * @author: mm
 * @since 3.0
 */
public class SdkDataServiceImpl implements SdkDataService {
    private SdkDataDAO sdkDataDAO;
    @Override
    public List<SdkDataVO> getTotalSdkDataVOList(SearchVO searchVO) {
        return sdkDataDAO.getTotalSdkDataVOList(searchVO);
    }

    public SdkDataDAO getSdkDataDAO() {
        return sdkDataDAO;
    }

    public void setSdkDataDAO(SdkDataDAO sdkDataDAO) {
        this.sdkDataDAO = sdkDataDAO;
    }
}
