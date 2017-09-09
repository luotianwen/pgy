package com.kkgame.pay.stat.dao.impl;

import com.kkgame.pay.stat.bean.*;
import com.kkgame.pay.stat.dao.StatisticsDao;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DBCursor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StatisticsDaoImpl  extends SqlMapClientDaoSupport implements
        StatisticsDao {
    private MongoTemplate mongoTemplate;
    public void saveUser(List<UserData> listUser) {
        Map map = new HashMap();
        List<UserData> ud = new ArrayList<UserData>(), uz0 = new ArrayList<UserData>(), uz1 = new ArrayList<UserData>(), uz2 = new ArrayList<UserData>(), uz3 = new ArrayList<UserData>(), uz4 = new ArrayList<UserData>(), uz5 = new ArrayList<UserData>(), uz6 = new ArrayList<UserData>(), uz7 = new ArrayList<UserData>(), uz8 = new ArrayList<UserData>(), uz9 = new ArrayList<UserData>(), od = new ArrayList<UserData>(), o0 = new ArrayList<UserData>(), o1 = new ArrayList<UserData>(), o2 = new ArrayList<UserData>(), o3 = new ArrayList<UserData>(), o4 = new ArrayList<UserData>(), o5 = new ArrayList<UserData>(), o6 = new ArrayList<UserData>(), o7 = new ArrayList<UserData>(), o8 = new ArrayList<UserData>(), o9 = new ArrayList<UserData>();
        String statDate = CalendarFormat.switchFormatDate(listUser.get(0)
                .getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        for (UserData adVO : listUser) {
            adVO.setTable(statDate);
            int a = Math.abs(adVO.getImei().hashCode()) % 10;
            if (null == map.get(adVO.getSdkStyle())) {
                map.put(adVO.getSdkStyle(),
                        Constants.TYPE_USER_MAP.get(adVO.getSdkStyle())
                                + statDate);
            }
            if (null == map.get(adVO.getSdkStyle() + "_" + a)) {
                map.put(adVO.getSdkStyle() + "_" + a,
                        Constants.TYPE_USER_MAP.get(adVO.getSdkStyle()) + a);
            }
            if (600400 == adVO.getSdkStyle()) {
                ud.add(adVO);
                switch (a) {
                    case 0:
                        uz0.add(adVO);
                        break;
                    case 1:
                        uz1.add(adVO);
                        break;
                    case 2:
                        uz2.add(adVO);
                        break;
                    case 3:
                        uz3.add(adVO);
                        break;
                    case 4:
                        uz4.add(adVO);
                        break;
                    case 5:
                        uz5.add(adVO);
                        break;
                    case 6:
                        uz6.add(adVO);
                        break;
                    case 7:
                        uz7.add(adVO);
                        break;
                    case 8:
                        uz8.add(adVO);
                        break;
                    case 9:
                        uz9.add(adVO);
                        break;
                    default:
                        break;
                }
            } else {
                od.add(adVO);
                switch (a) {
                    case 0:
                        o0.add(adVO);
                        break;
                    case 1:
                        o1.add(adVO);
                        break;
                    case 2:
                        o2.add(adVO);
                        break;
                    case 3:
                        o3.add(adVO);
                        break;
                    case 4:
                        o4.add(adVO);
                        break;

                    case 5:
                        o5.add(adVO);
                        break;
                    case 6:
                        o6.add(adVO);
                        break;
                    case 7:
                        o7.add(adVO);
                        break;
                    case 8:
                        o8.add(adVO);
                        break;
                    case 9:
                        o9.add(adVO);
                        break;
                    default:
                        break;
                }
            }
        }
        Map map0 = new HashMap();

        for (Integer usermap : Constants.TYPE_LIST) {
            if (null != map.get(usermap) && usermap == 600400) {
                map0.clear();
                map0.put("table", map.get(usermap));
                map0.put("list", ud);
                getSqlMapClientTemplate().insert("statSqlMap.insertSDKUserData", map0);

            }
            if (null != map.get(usermap) && usermap != 600400) {
                map0.clear();
                map0.put("table", map.get(usermap));
                map0.put("list", od);
                getSqlMapClientTemplate().insert("statSqlMap.insertUserData", map0);

            }
            for (int i = 0; i < 10; i++) {

                if (null != map.get(usermap + "_" + i) && usermap == 600400) {
                    map0.clear();
                    map0.put("table", map.get(usermap + "_" + i));
                    switch (i) {
                        case 0:
                            map0.put("list", uz0);
                            break;
                        case 1:
                            map0.put("list", uz1);
                            break;
                        case 2:
                            map0.put("list", uz2);
                            break;
                        case 3:
                            map0.put("list", uz3);
                            break;
                        case 4:
                            map0.put("list", uz4);
                            break;
                        case 5:
                            map0.put("list", uz5);
                            break;
                        case 6:
                            map0.put("list", uz6);
                            break;
                        case 7:
                            map0.put("list", uz7);
                            break;
                        case 8:
                            map0.put("list", uz8);
                            break;
                        case 9:
                            map0.put("list", uz9);
                            break;
                        default:
                            break;
                    }
                    getSqlMapClientTemplate().insert("statSqlMap.insertSDKUserData", map0);
                }

                if (null != map.get(usermap + "_" + i) && usermap != 600400) {
                    map0.clear();
                    map0.put("table", map.get(usermap + "_" + i));
                    switch (i) {
                        case 0:
                            map0.put("list", o0);
                            break;
                        case 1:
                            map0.put("list", o1);
                            break;
                        case 2:
                            map0.put("list", o2);
                            break;
                        case 3:
                            map0.put("list", o3);
                            break;
                        case 4:
                            map0.put("list", o4);
                            break;
                        case 5:
                            map0.put("list", o5);
                            break;
                        case 6:
                            map0.put("list", o6);
                            break;
                        case 7:
                            map0.put("list", o7);
                            break;
                        case 8:
                            map0.put("list", o8);
                            break;
                        case 9:
                            map0.put("list", o9);
                            break;
                        default:
                            break;
                    }
                    getSqlMapClientTemplate().insert("statSqlMap.insertUserData", map0);
                }

            }
        }

    }

    @Override
    public void saveActive(List<UserData> listUser) {
        String statDate = CalendarFormat.switchFormatDate(listUser.get(0).getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        List<UserData> u1 = new ArrayList<UserData>(), u2 = new ArrayList<UserData>(), u3 = new ArrayList<UserData>(), u4 = new ArrayList<UserData>()
                , u5 = new ArrayList<UserData>(), u6 = new ArrayList<UserData>();
        for (UserData usermap : listUser) {
            switch (usermap.getSdkStyle()) {
                case 600400:
                    u1.add(usermap);
                    break;
                case 600403:
                    u2.add(usermap);
                    break;
                case 600404:
                    u3.add(usermap);
                    break;
                case 600405:
                    u4.add(usermap);
                    break;
                case 600406:
                    u5.add(usermap);
                    break;
                case 600407:
                    u6.add(usermap);
                    break;
            }
        }
        for (Integer type : Constants.TYPE_LIST) {
            Map map = new HashMap();
            switch (type) {
                case 600400:
                    map.clear();
                    map.put("table", Constants.TYPE_ACTIVE_MAP.get(type) + statDate);
                    map.put("list", u1);
                    if (u1.size() > 0) {
                        getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
                    }
                    break;
                case 600403:
                    map.clear();
                    map.put("table", Constants.TYPE_ACTIVE_MAP.get(type) + statDate);
                    map.put("list", u2);
                    if (u2.size() > 0) {
                        getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
                    }
                    break;
                case 600404:

                    map.clear();
                    map.put("table", Constants.TYPE_ACTIVE_MAP.get(type) + statDate);
                    map.put("list", u3);
                    if (u3.size() > 0) {
                        getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
                    }
                    break;
                case 600405:
                    map.clear();
                    map.put("table", Constants.TYPE_ACTIVE_MAP.get(type) + statDate);
                    map.put("list", u4);
                    if (u4.size() > 0) {
                        getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
                    }
                    break;
                case 600406:
                    map.clear();
                    map.put("table", Constants.TYPE_ACTIVE_MAP.get(type) + statDate);
                    map.put("list", u5);
                    if (u5.size() > 0) {
                        getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
                    }
                    break;
                case 600407:
                    map.clear();
                    map.put("table", Constants.TYPE_ACTIVE_MAP.get(type) + statDate);
                    map.put("list", u6);
                    if (u6.size() > 0) {
                        getSqlMapClientTemplate().insert("statSqlMap.insertActiveData", map);
                    }
                    break;
            }


        }


    }

    @Override
    public void saveProjectActive(List<UserData> listUser) {


        Map map = new HashMap();
        List<UserData> ud = new ArrayList<UserData>(), uz0 = new ArrayList<UserData>(), uz1 = new ArrayList<UserData>(), uz2 = new ArrayList<UserData>(), uz3 = new ArrayList<UserData>(), uz4 = new ArrayList<UserData>(), uz5 = new ArrayList<UserData>(), uz6 = new ArrayList<UserData>(), uz7 = new ArrayList<UserData>(), uz8 = new ArrayList<UserData>(), uz9 = new ArrayList<UserData>(), od = new ArrayList<UserData>(), o0 = new ArrayList<UserData>(), o1 = new ArrayList<UserData>(), o2 = new ArrayList<UserData>(), o3 = new ArrayList<UserData>(), o4 = new ArrayList<UserData>(), o5 = new ArrayList<UserData>(), o6 = new ArrayList<UserData>(), o7 = new ArrayList<UserData>(), o8 = new ArrayList<UserData>(), o9 = new ArrayList<UserData>();
        String statDate = CalendarFormat.switchFormatDate(listUser.get(0)
                .getCdate(), "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        for (UserData adVO : listUser) {
            adVO.setTable(statDate);
            int a = Math.abs(adVO.getImei().hashCode()) % 10;
            if (null == map.get(adVO.getSdkStyle())) {
                map.put(adVO.getSdkStyle(),
                        Constants.TYPE_USER_MAP.get(adVO.getSdkStyle())
                                + "project_" + statDate);
            }
            if (null == map.get(adVO.getSdkStyle() + "_" + a)) {
                map.put(adVO.getSdkStyle() + "_" + a,
                        Constants.TYPE_USER_MAP.get(adVO.getSdkStyle()) + "project_" + a);
            }
            if (600400 == adVO.getSdkStyle()) {
                ud.add(adVO);
                switch (a) {
                    case 0:
                        uz0.add(adVO);
                        break;
                    case 1:
                        uz1.add(adVO);
                        break;
                    case 2:
                        uz2.add(adVO);
                        break;
                    case 3:
                        uz3.add(adVO);
                        break;
                    case 4:
                        uz4.add(adVO);
                        break;
                    case 5:
                        uz5.add(adVO);
                        break;
                    case 6:
                        uz6.add(adVO);
                        break;
                    case 7:
                        uz7.add(adVO);
                        break;
                    case 8:
                        uz8.add(adVO);
                        break;
                    case 9:
                        uz9.add(adVO);
                        break;
                    default:
                        break;
                }
            } else {
                od.add(adVO);
                switch (a) {
                    case 0:
                        o0.add(adVO);
                        break;
                    case 1:
                        o1.add(adVO);
                        break;
                    case 2:
                        o2.add(adVO);
                        break;
                    case 3:
                        o3.add(adVO);
                        break;
                    case 4:
                        o4.add(adVO);
                        break;

                    case 5:
                        o5.add(adVO);
                        break;
                    case 6:
                        o6.add(adVO);
                        break;
                    case 7:
                        o7.add(adVO);
                        break;
                    case 8:
                        o8.add(adVO);
                        break;
                    case 9:
                        o9.add(adVO);
                        break;
                    default:
                        break;
                }
            }
        }
        Map map0 = new HashMap();

        for (Integer usermap : Constants.TYPE_LIST) {
            if (null != map.get(usermap) && usermap == 600400) {
                map0.clear();
                map0.put("table", map.get(usermap));
                map0.put("list", ud);
                getSqlMapClientTemplate().insert("statSqlMap.insertProjectUserData", map0);

            }
            if (null != map.get(usermap) && usermap != 600400) {
                map0.clear();
                map0.put("table", map.get(usermap));
                map0.put("list", od);
                getSqlMapClientTemplate().insert("statSqlMap.insertProjectUserData", map0);

            }
            for (int i = 0; i < 10; i++) {

                if (null != map.get(usermap + "_" + i) && usermap == 600400) {
                    map0.clear();
                    map0.put("table", map.get(usermap + "_" + i));
                    switch (i) {
                        case 0:
                            map0.put("list", uz0);
                            break;
                        case 1:
                            map0.put("list", uz1);
                            break;
                        case 2:
                            map0.put("list", uz2);
                            break;
                        case 3:
                            map0.put("list", uz3);
                            break;
                        case 4:
                            map0.put("list", uz4);
                            break;
                        case 5:
                            map0.put("list", uz5);
                            break;
                        case 6:
                            map0.put("list", uz6);
                            break;
                        case 7:
                            map0.put("list", uz7);
                            break;
                        case 8:
                            map0.put("list", uz8);
                            break;
                        case 9:
                            map0.put("list", uz9);
                            break;
                        default:
                            break;
                    }
                    getSqlMapClientTemplate().insert("statSqlMap.insertProjectUserData", map0);
                }

                if (null != map.get(usermap + "_" + i) && usermap != 600400) {
                    map0.clear();
                    map0.put("table", map.get(usermap + "_" + i));
                    switch (i) {
                        case 0:
                            map0.put("list", o0);
                            break;
                        case 1:
                            map0.put("list", o1);
                            break;
                        case 2:
                            map0.put("list", o2);
                            break;
                        case 3:
                            map0.put("list", o3);
                            break;
                        case 4:
                            map0.put("list", o4);
                            break;
                        case 5:
                            map0.put("list", o5);
                            break;
                        case 6:
                            map0.put("list", o6);
                            break;
                        case 7:
                            map0.put("list", o7);
                            break;
                        case 8:
                            map0.put("list", o8);
                            break;
                        case 9:
                            map0.put("list", o9);
                            break;
                        default:
                            break;
                    }
                    getSqlMapClientTemplate().insert("statSqlMap.insertProjectUserData", map0);
                }

            }
        }


    }

    @Override
    public void saveSent(List<AdDataVO> listUser) {
        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_sents_list_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");

    }

    @Override
    public void saveSentSucc(List<AdDataVO> listUser) {
        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_sents_succ_list_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveShow(List<AdDataVO> listUser) {
        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_show_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveActivate(List<AdDataVO> listUser) {
        // TODO Auto-generated method stub
        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_activate_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveInstalled(List<AdDataVO> listUser) {
        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_installed_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveDownload(List<AdDataVO> listUser) {
        // TODO Auto-generated method stub
        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_download_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveClick(List<AdDataVO> listUser) {

        AdDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_click_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public List<AdDataVO> query() {
         Pattern pattern = Pattern.compile("1",Pattern.CASE_INSENSITIVE);
         Criteria criatira = new Criteria("imei").regex(pattern.toString());

        //Criteria criatira = new Criteria();
       // criatira.andOperator(Criteria.where("userName").is("admin"), Criteria.where("password").is("f818fa8cf51ca364f367f0046bd014ff"));
       /*
        query.addCriteria(cr.orOperator(
                Criteria.where("communityName").regex(keyword)
                ,Criteria.where("communityName").regex(keyword)
                ,Criteria.where("remark").regex(keyword)
        ));*/
        //criatira.andOperator(Criteria.where("createTime").gte(todayStart).lte(endStart)) ;
        Query query=new Query(criatira);

        BasicDBObject keys = new BasicDBObject();
        keys.put("_id", 1);
        keys.put("name", 1);
        keys.put("age", 1);

        DBCursor cursor = mongoTemplate.getCollection("adv_feeback_list_show_20160602")
                .find(new BasicDBObject(), keys)
                .addOption(Bytes.QUERYOPTION_NOTIMEOUT);

        mongoTemplate.getCollection("adv_feeback_list_show_20160602").drop();

        return null;
      // return (List<AdDataVO>) mongoTemplate.find(query,AdDataVO.class,"adv_feeback_list_show_20160602");
    }

    @Override
    public void saveLinkAdData(List<AdLinkDataVO> listUser) {
        AdLinkDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_link_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveDssdkAdData(List<AdDssdkDataVO> listUser) {
        AdDssdkDataVO adVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_feeback_list_dssdk_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveSubAdData(List<AdSubDataVO> listUser) {
        AdSubDataVO adSubDataVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adSubDataVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_click_list_sub_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveSubLinkAdData(List<SubLinkDataVO> listUser) {
        SubLinkDataVO adSubDataVO = listUser.get(0);
        String statDate = CalendarFormat.switchFormatDate(adSubDataVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", "adv_link_list_sub_" + statDate);
        map.put("list", listUser);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    @Override
    public void saveIframeActive(List<IframeVO> list, String table) {
        IframeVO iframeVO = list.get(0);
        String statDate = CalendarFormat.switchFormatDate(iframeVO.getCdate(),
                "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        Map map = new HashMap();
        map.put("table", table + statDate);
        map.put("list", list);
        mongoTemplate.insert((List)map.get("list"),map.get("table")+"");
    }

    /**
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * @param mongoTemplate the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
