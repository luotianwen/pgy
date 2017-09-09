package com.kkgame.feeop.data.service;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.SubDataVO;
import com.kkgame.feeop.data.dao.IframeDataDAO;
import com.kkgame.feeop.detail.bean.IframeDetailVO;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/28
 *          Time: 17:24
 * @author: mm
 * @since 3.0
 */
public interface IframeDataService {

    List<IframeDetailVO> getTotalIframeVOList(SearchVO searchVO);
}
