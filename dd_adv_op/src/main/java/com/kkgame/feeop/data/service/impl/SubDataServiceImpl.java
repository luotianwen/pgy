package com.kkgame.feeop.data.service.impl;

import com.kkgame.feeop.data.bean.SubDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.SubDataDAO;
import com.kkgame.feeop.data.service.SubDataService;

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
public class SubDataServiceImpl implements SubDataService {
    private SubDataDAO subDataDAO;
    @Override
    public List<SubDataVO> getTotalSubDataVOList(SearchVO searchVO) {
        return subDataDAO.getTotalSubDataVOList(searchVO);
    }

    public SubDataDAO getSubDataDAO() {
        return subDataDAO;
    }

    public void setSubDataDAO(SubDataDAO subDataDAO) {
        this.subDataDAO = subDataDAO;
    }
}
