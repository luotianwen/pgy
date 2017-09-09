package com.kkgame.feeop.offline.service;

import com.kkgame.feeop.offline.bean.OfflinejarVO;

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
public interface OfflinejarService {
    List<OfflinejarVO> getofflinejarVOList(OfflinejarVO offlinejarVO);

    OfflinejarVO getofflinejarVO(OfflinejarVO offlinejarVO);

    void update(OfflinejarVO offlinejarVO);

    void insert(OfflinejarVO offlinejarVO);
}
