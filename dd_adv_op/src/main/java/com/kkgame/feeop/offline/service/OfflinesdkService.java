package com.kkgame.feeop.offline.service;

import com.kkgame.feeop.offline.bean.OfflinesdkVO;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/21
 *          Time: 15:58
 * @author: mm
 * @since 3.0
 */
public interface OfflinesdkService {
    List<OfflinesdkVO> getofflinesdkVOList(OfflinesdkVO offlinesdkVO);

    OfflinesdkVO getofflinesdkVO(OfflinesdkVO offlinesdkVO);

    void update(OfflinesdkVO offlinesdkVO);

    void insert(OfflinesdkVO offlinesdkVO);
}
