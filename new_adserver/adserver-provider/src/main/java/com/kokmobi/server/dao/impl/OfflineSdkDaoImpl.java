package com.kokmobi.server.dao.impl;

import com.kokmobi.server.bean.SilentPluginResp;
import com.kokmobi.server.dao.OfflineSdkDao;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * Created by win7 on 2016/9/8.
 */
public class OfflineSdkDaoImpl extends SqlMapClientDaoSupport implements OfflineSdkDao {
    @Override
    public int getTimeStep(String version) {
        Object o = getSqlMapClientTemplate().queryForObject("OfflineSqlMap.getTimeStep",version);
        if(o!=null){
            return (int)o;
        }else{
            return 2;
        }
    }

    @Override
    public List<SilentPluginResp> getOfflineJarsList() {
        return getSqlMapClientTemplate().queryForList("OfflineSqlMap.getOfflineJarsList");
    }

    @Override
    public List<SilentPluginResp> getOfflineApksList() {
        return  getSqlMapClientTemplate().queryForList("OfflineSqlMap.getOfflineApksList");
    }
}
