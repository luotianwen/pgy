package com.kkgame.feeop.offerinfo.dao;

import com.kkgame.feeop.offerinfo.bean.OfferSdkVO;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/9
 *          Time: 17:09
 * @author: mm
 * @since 3.0
 */
public interface OfferSdkDao {
    List<OfferSdkVO> getOfferSdkList(OfferSdkVO offerSdkVO);

    void save(OfferSdkVO offerSdkVO);

    OfferSdkVO getOfferSdkById(OfferSdkVO offerSdkVO);

    void deleteOfferSdkById(OfferSdkVO offerSdkVO);

    List<OfferSdkVO> getOfferSdkList();

    void update(OfferSdkVO offerSdkVO);
}
