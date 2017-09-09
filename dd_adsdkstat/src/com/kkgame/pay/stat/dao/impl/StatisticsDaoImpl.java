package com.kkgame.pay.stat.dao.impl;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.UserData;
import com.kkgame.pay.stat.dao.StatisticsDao;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.DatabaseException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsDaoImpl extends SqlMapClientDaoSupport implements
        StatisticsDao {

    @Override
    public void statDayActiveData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.insertDayActiveData", map);

    }

    @Override
    public void statDayInstalledData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.insertDayInstalledData", map);
    }

    @Override
    public void statDayUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.insertDayUserData", map);
    }

    @Override
    public void statTotalUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.insertTotalUserData", map);
    }

    @Override
    public void syncTempAdData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_STAT_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.insertTempAdData", map);
    }

    @Override
    public void deleteAdData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_STAT_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteAdData", map);
    }

    @Override
    public void syncTempAdProjectData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.insertTempAdProjectData", map);
    }


    @Override
    public void syncTempRetentionUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.insertTempRetentionUserData", map);
    }

    @Override
    public void syncTempUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.insertTempUserData", map);
    }

    @Override
    public void syncTempVersionUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.insertTempVersionUserData", map);
    }

    @Override
    public void deleteTempUserData(int i, int type) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteTempUserData", map);
    }

    @Override
    public void deleteTempVersionUserData(int i, int type) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteTempVersionUserData", map);
    }


    @Override
    public void deleteRetentionUserData(int i, int type)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteRetentionUserData", map);
    }


    @Override
    public void deleteUserData(int i, int type) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteUserData", map);
    }

    @Override
    public void deleteVersionUserData(int i, int type) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteVersionUserData", map);
    }

    @Override
    public void insertUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertUserData", map);
    }

    @Override
    public void insertUserDataProject(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertUserDataProject", map);
    }

    @Override
    public void insertVersionUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertVersionUserData", map);
    }

    @Override
    public void insertVersionProjectUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectUserData", map);
    }

    @Override
    public void insertRetentionUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertRetentionUserData",
                map);
    }

    @Override
    public void deleteUserActiveData(int i, int type) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate()
                .delete("statSqlMap.deleteUserActiveData", map);
    }

    @Override
    public void insertUserActiveData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertUserActiveData", map);
    }

    @Override
    public void insertVersionUserActiveData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertVersionUserActiveData", map);
    }

    @Override
    public void insertUserProjectActiveData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertUserProjectActiveData", map);
    }

    @Override
    public void insertVersionUserProjectActiveData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertVersionUserProjectActiveData", map);
    }

    @Override
    public void insertTotalUserActiveData(int i, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertTotalUserActiveData", map);
    }

    @Override
    public void insertTotalVersionUserActiveData(int i, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertTotalVersionUserActiveData", map);
    }

    @Override
    public void deleteBackData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteBackData", map);
    }

    @Override
    public void insertClickData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertPushClickData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertClickData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSinkClickData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLeadClickData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSilenceClickData", map);
        }
    }

    @Override
    public void insertAdLinkData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_LINK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdNoLimitLinkData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdIconLinkData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdStatusBarLinkData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdBrowserLinkData", map);
        }
    }

    @Override
    public void updateLinkData(int i) throws DatabaseException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_LINK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.updateLinkData", map);
    }

    @Override
    public void insertAdTotalLinkData(int i) throws DatabaseException {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.insertAdTotalLinkData", map);
    }

    @Override
    public void insertAdLinkDataUv(int i, List<Data> list) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_STAT_LINK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertAdTotalLinkDataUv", map);
    }

    @Override
    public List<Data> getLinkPv(int i) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_STAT_LINK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        return getSqlMapClientTemplate().queryForList("statSqlMap.getLinkPv", map);
    }

    @Override
    public void insertDssdkData(int i, Integer type, List<Data> insertList) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_DSSDK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", insertList);
        //1为桌面 2 为悬浮 3为push  4电商类型app拦截  5其他类型app拦截
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertdsdesktopData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertdssuspensionData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertdspushData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertdsappData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertdsotherappData", map);
        }
    }

    @Override
    public void updateDsData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_DSSDK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert("statSqlMap.updateDsData", map);
    }

    @Override
    public List<Data> getDsPv(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_DSSDK_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        return getSqlMapClientTemplate().queryForList("statSqlMap.getDSPv", map);
    }

    @Override
    public void insertAdSubData(int i, List<Data> insertList) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_SUBSCRIBE_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", insertList);
        getSqlMapClientTemplate().insert("statSqlMap.insertAdSubStatDate", map);
    }

    @Override
    public void insertSubTotal(int i, List<Data> subTotal) {

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        map.put("list", subTotal);
        getSqlMapClientTemplate().insert("statSqlMap.insertSub", map);

    }



    @Override
    public void deleteTempAdSubData(int i) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("ad_data_subscribe_"+CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteTempAdSubData", map);
    }


    @Override
    public void insertOfferSdkShowData(int i, List<Data> insertList) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("ad_data_offersdk_"+CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", insertList);
        getSqlMapClientTemplate().insert("statSqlMap.insertOfferSdkShowData", map);
    }

    @Override
    public void insertOfferSdkClickData(int i, List<Data> insertList) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("ad_data_offersdk_"+CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", insertList);
        getSqlMapClientTemplate().insert("statSqlMap.insertOfferSdkClickData", map);
    }

    @Override
    public void insertOfferSdkSendData(int i, List<Data> insertList) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("ad_data_offersdk_"+CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", insertList);
        getSqlMapClientTemplate().insert("statSqlMap.insertOfferSdkSendData", map);
    }

    @Override
    public void deleteOfferSdkData(int i) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("ad_data_offersdk_"+CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteTempAdOfferSdkData", map);
    }

    @Override
    public void syncTempAdOfferSdkData(int i) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("ad_data_offersdk_"+CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.insertTempOfferSdkData", map);
    }

    @Override
    public void insertOfferSdkTotal(int i, List<Data> insertList) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        map.put("list", insertList);
        getSqlMapClientTemplate().insert("statSqlMap.insertSub", map);
    }

    @Override
    public void insertDownloadData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertPushDownloadData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertDownloadData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSinkDownloadData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLeadDownloadData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSilenceDownloadData", map);
        }
    }

    @Override
    public void insertInstallData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertPushInstallData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertInstallData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSinkInstallData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLeadInstallData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSilenceInstallData", map);
        }
    }

    @Override
    public void insertActiveData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
        }
    }

    @Override
    public void insertShowData(int i, int type, List<Data> list) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertPushShowData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertShowData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSinkShowData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLeadShowData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSilenceShowData", map);
        }
    }

    @Override
    public void insertFirstUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertFirstUserData", map);
    }

    @Override
    public void insertSecondUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertSecondUserData", map);
    }

    @Override
    public void insertThirdUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertThirdUserData", map);
    }

    @Override
    public void insertFourthUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertFourthUserData", map);
    }

    @Override
    public void insertFifthUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertFifthUserData", map);
    }

    @Override
    public void insertSixthUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertSixthUserData", map);
    }

    @Override
    public void insertSeventhUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertSeventhUserData",
                map);
    }

    @Override
    public void insertFiftyUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertFiftyUserData", map);
    }

    @Override
    public void insertThirtyUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("RETENTION_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate()
                .insert("statSqlMap.insertThirtyUserData", map);
    }

    @Override
    public void deleteAdSendData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteAdSendData", map);
    }

    @Override
    public void deleteTempAdSendData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteTempAdSendData", map);
    }

    @Override
    public void insertAdReceiveData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdPushReceiveData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdReceiveData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdSinkReceiveData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdLeadReceiveData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdSilenceReceiveData", map);
        }
    }

    @Override
    public void insertAdSendData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdPushSendData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdSendData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdSinkSendData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdLeadSendData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertAdSilenceSendData", map);
        }
    }

    @Override
    public void deleteMonthUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("yearMonth", CalendarFormat.getYM(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteMonthUserData", map);
    }

    @Override
    public List<UserData> getMonthUserDataList(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("yearMonth", CalendarFormat.getYM(i));
        return getSqlMapClientTemplate().queryForList(
                "statSqlMap.getMonthUserDataList", map);
    }

    @Override
    public List<UserData> getMonthUserActiveDataList(int i)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("yearMonth", CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getyyyyMMdd(i));
        return getSqlMapClientTemplate().queryForList(
                "statSqlMap.getMonthUserActiveDataList", map);
    }

    @Override
    public List<UserData> getMonthUserIncomeDataList(int i)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("yearMonth", CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getyyyyMMdd(i));
        return getSqlMapClientTemplate().queryForList(
                "statSqlMap.getMonthUserIncomeDataList", map);
    }

    @Override
    public void insertMonthUserIncomeData(int i, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertMonthUserIncomeData", map);
    }

    @Override
    public void insertMonthUserActiveData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertMonthUserActiveData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertApkMonthUserActiveData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLeadMonthUserActiveData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSilenceMonthUserActiveData", map);
        }
    }

    @Override
    public void insertMonthUserData(int i, int type, List<UserData> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertMonthUserData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertApkMonthUserData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLeadMonthUserData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertSilenceMonthUserData", map);
        }
    }

    @Override
    public List<UserData> getTotalUserData() throws DatabaseException {
        return getSqlMapClientTemplate().queryForList("statSqlMap.getTotalUserData");
    }

    @Override
    public List<Data> getDaySendData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        return getSqlMapClientTemplate().queryForList("statSqlMap.getDaySendData", map);
    }

    @Override
    public void deleteProjectData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().delete("statSqlMap.deleteProjectData", map);
    }

    @Override
    public void insertProjectClickData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectPushClickData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectClickData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSinkClickData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectLeadClickData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSilenceClickData", map);
        }
    }

    @Override
    public void insertVersionProjectClickData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectPushClickData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectClickData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSinkClickData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectLeadClickData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSilenceClickData", map);
        }
    }

    @Override
    public void insertProjectDownloadData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectPushDownloadData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectDownloadData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSinkDownloadData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectLeadDownloadData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSilenceDownloadData", map);
        }

    }

    @Override
    public void insertVersionProjectDownloadData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectPushDownloadData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectDownloadData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSinkDownloadData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectLeadDownloadData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSilenceDownloadData", map);
        }

    }

    @Override
    public void insertProjectInstallData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectPushInstallData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectInstallData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSinkInstallData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectLeadInstallData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSilenceInstallData", map);
        }

    }

    @Override
    public void insertVersionProjectInstallData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectPushInstallData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectInstallData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSinkInstallData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectLeadInstallData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSilenceInstallData", map);
        }

    }


    @Override
    public void insertProjectActiveData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 5) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectActiveData", map);
        }
    }


    @Override
    public void insertVersionProjectActiveData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 5) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectActiveData", map);
        }
    }

    @Override
    public void insertProjectReceiveData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectPushReceiveData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectReceiveData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSinkReceiveData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectLeadReceiveData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSilenceReceiveData", map);
        }

    }


    @Override
    public void insertVersionProjectReceiveData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectPushReceiveData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectReceiveData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSinkReceiveData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectLeadReceiveData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSilenceReceiveData", map);
        }

    }

    @Override
    public void insertProjectSendData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectPushSendData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectSendData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSinkSendData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectLeadSendData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSilenceSendData", map);
        }

    }

    @Override
    public void insertVersionProjectSendData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectPushSendData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectSendData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSinkSendData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectLeadSendData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSilenceSendData", map);
        }

    }

    @Override
    public void insertProjectShowData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("REGISTER_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectPushShowData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertProjectShowData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSinkShowData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectLeadShowData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectSilenceShowData", map);
        }
    }

    @Override
    public void insertVersionProjectShowData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("VERSION_DATA_TEMP");
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 1) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectPushShowData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert(
                    "statSqlMap.insertVersionProjectShowData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSinkShowData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectLeadShowData", map);
        } else if (type == 5) {
            getSqlMapClientTemplate().insert("statSqlMap.insertVersionProjectSilenceShowData", map);
        }
    }

    @Override
    public List<Data> getAdData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getAdData", map);
    }

    @Override
    public void insertAdData(int i, List<Data> list) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertAdData", map);
    }

    @Override
    public List<Data> getAdEffectiveData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getAdEffectiveData", map);
    }

    @Override
    public void insertAdEffectiveData(int i, List<Data> list) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertAdEffectiveData", map);
    }

    @Override
    public void insertAdBackData(int i, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertAdBackData", map);
    }

    @Override
    public List<Data> getTotalProjectUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getTotalProjectUserData", map);
    }

    @Override
    public List<Data> getFirstProjectUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("RETENTION_DATA_").append(CalendarFormat.getYM(i + 1));
        map.put("statDate", CalendarFormat.getYmd(i + 1));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getFirstProjectUserData", map);
    }

    @Override
    public List<Data> getProjectUserData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("REGISTER_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getProjectUserData", map);
    }

    @Override
    public List<Data> getDdlList(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getDdlList", map);
    }

    @Override
    public void insertDdlList(int i, List<Data> list) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertDdlList", map);
    }

    @Override
    public void insertProjectUserData(int i, int type, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        if (type == 0) {
            getSqlMapClientTemplate().insert("statSqlMap.insertProjectUserData", map);
        } else if (type == 1) {
            getSqlMapClientTemplate().insert("statSqlMap.insertHighProjectUserData", map);
        } else if (type == 2) {
            getSqlMapClientTemplate().insert("statSqlMap.insertMidProjectUserData", map);
        } else if (type == 3) {
            getSqlMapClientTemplate().insert("statSqlMap.insertLowProjectUserData", map);
        } else if (type == 4) {
            getSqlMapClientTemplate().insert("statSqlMap.insertNoneProjectUserData", map);
        }
    }

    @Override
    public void insertFirstProjectUserData(int i, List<Data> list) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i + 1));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert("statSqlMap.insertFirstProjectUserData", map);
    }

    @Override
    public List<Data> getProjectShow(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getProjectShow", map);
    }

    @Override
    public void insertProjectShow(int i, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertProjectShow", map);
    }

    @Override
    public List<Data> getAdProjectData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getAdProjectData", map);
    }

    //广告项目数据
    @Override
    public void insertAdProjectData(int i, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_AD_INCOME_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertAdProjectData", map);
    }

    @Override
    public List<Data> getAdProjectEffectiveData(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("AD_DATA_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getAdProjectEffectiveData", map);
    }

    //广告项目数据
    @Override
    public void insertAdProjectEffectiveData(int i, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_AD_INCOME_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertAdProjectEffectiveData", map);
    }

    //广告比例
    @Override
    public void updateAdPercent(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateAdPercent", map);
    }

    //广告项目收入分配
    @Override
    public void updateAdProjectPercent(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateAdProjectPercent", map);
    }

    @Override
    public List<Data> getProjectIncome(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("PROJECT_AD_INCOME_").append(CalendarFormat.getYM(i));
        map.put("statDate", CalendarFormat.getYmd(i));
        map.put("table", sb.toString());
        return getSqlMapClientTemplate().queryForList("statSqlMap.getProjectIncome", map);
    }

    @Override
    public void insertProjectIncome(int i, List<Data> list)
            throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("list", list);
        getSqlMapClientTemplate().insert(
                "statSqlMap.insertProjectIncome", map);
    }

    @Override
    public void updateExpectIncome(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateExpectIncome", map);
    }

    @Override
    public void updateProjectUsers(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateProjectUsers", map);
    }

    @Override
    public void updateProjectOutcome(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateProjectOutcome", map);
    }

    @Override
    public void updateProjectOutcomeRate(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateProjectOutcomeRateFirst", map);
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateProjectOutcomeRateSecond", map);
    }

    @Override
    public void updateProjectUsersNeedBack(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("PROJECT_HZ_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateProjectUsersNeedBack", map);
    }

    @Override
    public void updateAdPrice(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateAdPrice", map);
    }

    @Override
    public void updateAdCpm(int i) throws DatabaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        getSqlMapClientTemplate().insert(
                "statSqlMap.updateAdCpm", map);
    }

    @Override
    public List<Data> getAdCpm(int i) throws DatabaseException {

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer table = new StringBuffer();
        table.append("APK_CPHC_TOTAL_").append(CalendarFormat.getYM(i));
        map.put("table", table.toString());
        map.put("statDate", CalendarFormat.getYmd(i));
        return getSqlMapClientTemplate().queryForList("statSqlMap.getAdCpm", map);
    }
}
