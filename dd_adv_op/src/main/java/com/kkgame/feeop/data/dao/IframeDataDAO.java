package com.kkgame.feeop.data.dao;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.detail.bean.IframeDetailVO;

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
public interface IframeDataDAO {
    List<IframeDetailVO> getTotalIframeDataVOList(SearchVO searchVO);
}
