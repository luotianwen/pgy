package com.kkgame.feeop.offerinfo.service.impl;

import com.kkgame.feeop.offerinfo.bean.OfferSdkVO;
import com.kkgame.feeop.offerinfo.dao.OfferSdkDao;
import com.kkgame.feeop.offerinfo.service.OfferSdkService;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/9
 *          Time: 17:01
 * @author: mm
 * @since 3.0
 */
public class OfferSdkServiceImpl implements OfferSdkService{
    private OfferSdkDao offerSdkDao;
    @Override
    public List<OfferSdkVO> getOfferSdkList(OfferSdkVO offerSdkVO) {
        return offerSdkDao.getOfferSdkList(offerSdkVO);
    }

    @Override
    public void save(OfferSdkVO offerSdkVO) {
        offerSdkDao.save(offerSdkVO);
    }

    @Override
    public OfferSdkVO getOfferSdkById(OfferSdkVO offerSdkVO) {
        return offerSdkDao.getOfferSdkById(offerSdkVO);
    }

    @Override
    public void deleteOfferSdkById(OfferSdkVO offerSdkVO) {
        offerSdkDao.deleteOfferSdkById(offerSdkVO);
    }

    @Override
    public List<OfferSdkVO> getOfferSdkList() {
        return offerSdkDao.getOfferSdkList();
    }

    @Override
    public void update(OfferSdkVO offerSdkVO) {
        offerSdkDao.update(offerSdkVO);
    }

    public OfferSdkDao getOfferSdkDao() {
        return offerSdkDao;
    }

    public void setOfferSdkDao(OfferSdkDao offerSdkDao) {
        this.offerSdkDao = offerSdkDao;
    }
}
