package com.kkgame.feeop.offline.service;

import com.kkgame.feeop.offline.bean.OfflineapkVO;

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
public interface OfflineapkService {
    List<OfflineapkVO> getofflineapkVOList(OfflineapkVO offlineapkVO);

    OfflineapkVO getofflineapkVO(OfflineapkVO offlineapkVO);

    void update(OfflineapkVO offlineapkVO);

    void insert(OfflineapkVO offlineapkVO);
}
