package com.kkgame.feeop.data.service.impl;

import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.bean.SubDataVO;
import com.kkgame.feeop.data.dao.IframeDataDAO;
import com.kkgame.feeop.data.service.IframeDataService;
import com.kkgame.feeop.data.service.IframeDataService;
import com.kkgame.feeop.detail.bean.IframeDetailVO;

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
public class IframeDataServiceImpl implements IframeDataService {
    private IframeDataDAO iframeDataDAO;
    @Override
    public List<IframeDetailVO> getTotalIframeVOList(SearchVO searchVO) {
        return iframeDataDAO.getTotalIframeDataVOList(searchVO);
    }

    public IframeDataDAO getIframeDataDAO() {
        return iframeDataDAO;
    }

    public void setIframeDataDAO(IframeDataDAO iframeDataDAO) {
        this.iframeDataDAO = iframeDataDAO;
    }
}
