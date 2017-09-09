package com.kkgame.feeop.data.dao;

import com.kkgame.feeop.data.bean.SdkDataVO;
import com.kkgame.feeop.data.bean.SearchVO;

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
public interface SdkDataDAO {
    List<SdkDataVO> getTotalSdkDataVOList(SearchVO searchVO);
}
