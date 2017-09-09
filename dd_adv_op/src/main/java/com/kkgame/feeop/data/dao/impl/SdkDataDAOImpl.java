package com.kkgame.feeop.data.dao.impl;

import com.kkgame.feeop.data.bean.SdkDataVO;
import com.kkgame.feeop.data.bean.SearchVO;
import com.kkgame.feeop.data.dao.SdkDataDAO;
import com.kkgame.feeop.util.CalendarFormat;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/28
 *          Time: 17:33
 * @author: mm
 * @since 3.0
 */
public class SdkDataDAOImpl extends SqlMapClientDaoSupport implements SdkDataDAO {
    @Override
    public List<SdkDataVO> getTotalSdkDataVOList(SearchVO searchVO) {
        String month = CalendarFormat.switchFormatDate(searchVO.getStartDate(),"yyyy-MM-dd","yyyyMM");
        searchVO.setTable(month);
        String[] rowFields = searchVO.getRowFieldString().substring(0, searchVO.getRowFieldString().length()-1).split(",");
        searchVO.setRowFields(rowFields);
        searchVO.getRowFieldVO().setShowRowField(searchVO.getRowFields());
        return getSqlMapClientTemplate().queryForList("sdkDataSqlMap.getSdkDataVOList", searchVO);
    }
}