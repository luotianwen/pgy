package com.kkgame.feeop.offline.dao;

import com.kkgame.feeop.offline.bean.OfflineapkVO;

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
public interface OfflineapkDAO {
    List<OfflineapkVO> getofflineapkVOList(OfflineapkVO offlineapkVO);

    OfflineapkVO getofflineapkVO(OfflineapkVO offlineapkVO);

    void insert(OfflineapkVO offlineapkVO);

    void update(OfflineapkVO offlineapkVO);
}
