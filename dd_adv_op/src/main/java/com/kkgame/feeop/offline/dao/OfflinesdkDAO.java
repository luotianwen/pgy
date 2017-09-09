package com.kkgame.feeop.offline.dao;

import com.kkgame.feeop.offline.bean.OfflinesdkVO;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/21
 *          Time: 16:05
 * @author: mm
 * @since 3.0
 */
public interface OfflinesdkDAO {
    List<OfflinesdkVO> getofflinesdkVOList(OfflinesdkVO offlinesdkVO);

    OfflinesdkVO getofflinesdkVO(OfflinesdkVO offlinesdkVO);

    void insert(OfflinesdkVO offlinesdkVO);

    void update(OfflinesdkVO offlinesdkVO);
}
