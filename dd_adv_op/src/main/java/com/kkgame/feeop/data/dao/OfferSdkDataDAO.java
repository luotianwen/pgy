package com.kkgame.feeop.data.dao;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.SubDataVO;
import com.kkgame.feeop.offerinfo.bean.OfferSdkVO;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/28
 *          Time: 17:31
 * @author: mm
 * @since 3.0
 */
public interface OfferSdkDataDAO {
    List<SubDataVO> getTotalOfferSdkDataVOList(SearchVO searchVO);
}
