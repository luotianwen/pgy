package com.kkgame.feeop.data.service.impl;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.SubDataVO;
import com.kkgame.feeop.data.dao.OfferSdkDataDAO;
import com.kkgame.feeop.data.service.OfferSdkDataService;

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
public class OfferSdkDataServiceImpl implements OfferSdkDataService {
    private OfferSdkDataDAO offerSdkDataDAO;
    @Override
    public List<SubDataVO> getTotalOfferSdkVOList(SearchVO searchVO) {
        return offerSdkDataDAO.getTotalOfferSdkDataVOList(searchVO);
    }

    public OfferSdkDataDAO getOfferSdkDataDAO() {
        return offerSdkDataDAO;
    }

    public void setOfferSdkDataDAO(OfferSdkDataDAO offerSdkDataDAO) {
        this.offerSdkDataDAO = offerSdkDataDAO;
    }
}
