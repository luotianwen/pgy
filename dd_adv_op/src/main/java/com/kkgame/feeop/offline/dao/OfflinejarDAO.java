package com.kkgame.feeop.offline.dao;

import com.kkgame.feeop.offline.bean.OfflinejarVO;

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
public interface OfflinejarDAO {
    List<OfflinejarVO> getofflinejarVOList(OfflinejarVO offlinejarVO);

    OfflinejarVO getofflinejarVO(OfflinejarVO offlinejarVO);

    void insert(OfflinejarVO offlinejarVO);

    void update(OfflinejarVO offlinejarVO);
}
