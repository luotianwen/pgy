package com.kokmobi.server.protocol.service;

import com.kokmobi.server.bean.ActiveInfo;
import com.kokmobi.server.bean.GetAdListReq;
import com.kokmobi.server.bean.SubLinkVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/18
 *          Time: 11:49
 * @author: mm
 * @since 3.0
 */
public interface SubAdProcessService {
    String processSubCli(GetAdListReq sr);

    void processActive(ActiveInfo activeInfo);

    String processSubLink(SubLinkVO sl);
}
