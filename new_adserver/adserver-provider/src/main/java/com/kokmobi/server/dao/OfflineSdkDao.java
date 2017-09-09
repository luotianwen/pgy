package com.kokmobi.server.dao;

import com.kokmobi.server.bean.SilentPluginResp;

import java.util.List;

/**
 * Created by win7 on 2016/9/8.
 */
public interface OfflineSdkDao {
    public int getTimeStep(String version);

    List<SilentPluginResp> getOfflineJarsList();

    List<SilentPluginResp> getOfflineApksList();
}
