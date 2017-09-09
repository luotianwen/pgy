package com.kokmobi.server.service;

import java.util.*;

import com.kokmobi.server.bean.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.util.CalendarFormat;
import com.memetix.mst.language.Language;

import net.sf.json.JSONObject;

public class CacheInfoUtil {


    private static Map<String, CacheInfo> mapCache = new HashMap<String, CacheInfo>();

    private static Log logger = LogFactory.getLog(CacheInfoUtil.class);

    /**
     * 得到用户信息
     * 先到缓存中查找是否存在该SDKType的用户，没有则查找数据库
     * 数据库中有数据则将数据保存到缓存，无则返回null
     *
     * @param sdkService
     * @param imei
     * @param sdkType    sdk项目类型
     * @return
     */
    public static AdUserInfo getActUserInfo(SdkService sdkService, String imei, int sdkType) {
        try {
            String rkey = String.format(Constants.KEY_USER_INFO, sdkType, imei);
            logger.info(String.format("find %s is new user or not.", imei));
            String strUser = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strUser)) {
                //find in db
                AdUserInfo rawUser = sdkService.getAdUserInfo(imei, sdkType);
                if (rawUser == null) {
                    //未注册用户
                    logger.info(String.format("%s is not registed", imei));
                } else {
                    logger.info(String.format("%s is registed", imei));
                    JSONObject jsobj = JSONObject.fromObject(rawUser);
                    RedisTool.set(rkey, jsobj.toString());
                }
                return rawUser;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strUser);
                AdUserInfo rawUser = (AdUserInfo) JSONObject.toBean(jsObj, AdUserInfo.class);
                return rawUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 得到同一项目用户信息
     * 先到缓存中查找是否存在该项目、SDKType用户，没有则查找数据库
     * 数据库中有数据则将数据保存到缓存，无则返回null
     *
     * @param sdkService
     * @param imei
     * @param sdkType
     * @param projectId  coo_id
     * @return
     */
    public static AdUserInfo getActUserInfoWithProject(SdkService sdkService, String imei, int sdkType, String projectId) {
        try {
            String rkey = String.format(Constants.KEY_PROJECT_USER_INFO, projectId, sdkType, imei);
            String strUser = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strUser)) {
                //find in db
                AdUserInfo rawUser = sdkService.getAdUserInfoWithProjectId(imei, sdkType, projectId);
                if (rawUser == null) {
                    //未注册用户
                    logger.info(String.format("%s is not registed with project %s in sdktype %s.", imei, projectId, sdkType));
                } else {
                    logger.info(String.format("%s is registed with project %s in sdktype %s.", imei, projectId, sdkType));
                    JSONObject jsobj = JSONObject.fromObject(rawUser);
                    RedisTool.set(rkey, jsobj.toString());
                }
                return rawUser;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strUser);
                AdUserInfo rawUser = (AdUserInfo) JSONObject.toBean(jsObj, AdUserInfo.class);
                return rawUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 将注册用户加入到缓存及待处理流中
     *
     * @param redis
     * @param user
     */
    public static void addAdUserInfoToCache(AdUserInfo user) {
        try {
            String rkey = String.format(Constants.KEY_USER_INFO, user.getSdkStyle(), user.getImei());
            String lkey = Constants.KEY_USER_LIST_TOSAVE;

            JSONObject jsobj = JSONObject.fromObject(user);
            RedisTool.set(rkey, jsobj.toString());
            logger.info(String.format("lpush imei %s to new user list with sdkstyle :%s.", user.getImei(), user.getSdkStyle()));
            RedisTool.lpush(lkey, jsobj.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 将注册用户加入到缓存及待处理流中
     *
     * @param redis
     * @param user
     */
    public static void addAdUserInfoWithProjectIdToCache(AdUserInfo user) {
        try {
            String rkey = String.format(Constants.KEY_PROJECT_USER_INFO, user.getCoo_id(), user.getSdkStyle(), user.getImei());
            String lkey = Constants.KEY_PROJECT_USER_LIST_TOSAVE;

            JSONObject jsobj = JSONObject.fromObject(user);
            RedisTool.set(rkey, jsobj.toString());
            logger.info(String.format("lpush %s to new user list with project %s in sdkstyle %s.", user.getImei(), user.getCoo_id(), user.getSdkStyle()));
            RedisTool.lpush(lkey, jsobj.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 保存活跃数据：
     * 先检查是否总活跃，如果没有，则插入到总活跃key中，返回是否为总活跃标记
     * 再检查是否已存在待保存队列里，不存在，则放到待保存活跃列表里
     *
     * @param redis
     * @param user
     */
    public static void addActUserInfoToCache(AdUserInfo user) {
        //先检查是否总活跃，如果没有，则插入到总活跃key中，返回是否为总活跃标记
        //再检查是否已存在待保存队列里，不存在，则放到待保存活跃列表里
        try {
            String strDay = CalendarFormat.getyyyyMMdd(0);

            String rkey = String.format(Constants.KEY_ACT_USER_INFO, strDay, user.getImei());

            int isRepeat = 0;
            if (ProtocolUtil.isNullOrEmpty(RedisTool.get(rkey))) {
                JSONObject jsobj = JSONObject.fromObject(user);
                RedisTool.set(rkey, jsobj.toString());
                RedisTool.expire(rkey, Constants.KEY_ACT_USER_INFO_EXPIRE);    //expired after 24 hours
                isRepeat = 1;
            }
            user.setDataType(isRepeat); //设置是否为总活跃标记
            //0date,1sdkStyle,2projectid,3channelid,4plugid,5country,6countrylevel,7imei
            String gkey = String.format(Constants.KEY_ACT_USER_GROUP_INFO, strDay,
                    user.getSdkStyle(), user.getCoo_id(), user.getChannelid(),
                    user.getXc_coo_id(), user.getXcou(), user.getCountryLevel(),
                    user.getImei());

            if (ProtocolUtil.isNullOrEmpty(RedisTool.get(gkey))) {
                //不存在，可以放到待保存列表
                JSONObject jsobj = JSONObject.fromObject(user);
                RedisTool.set(gkey, jsobj.toString());
                RedisTool.expire(gkey, Constants.KEY_ACT_USER_GROUP_INFO_EXPIRE);    //expired in 24 hours
                //放到待保存列表
                String lkey = Constants.KEY_ACT_USER_LIST_TOSAVE;
                RedisTool.lpush(lkey, jsobj.toString());
//				redis.expire(lkey, 30*60*60);	//expired after 30 hours
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AdProjectInfo getAdProjectInfo(SdkService sdkService, String projectId) {
        try {
            String rkey = String.format(Constants.KEY_PROJECT_INFO, projectId);
            String strUser = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strUser)) {
                //find in db
                AdProjectInfo projectInfo = sdkService.getAdProjectInfo(projectId);
                if (projectInfo == null) {
                    //未注册用户
                } else {
                    JSONObject jsobj = JSONObject.fromObject(projectInfo);
                    RedisTool.set(rkey, jsobj.toString());
                    RedisTool.expire(rkey, Constants.KEY_PROJECT_INFO_EXPIRE);    //5 minutes expired
                }
                return projectInfo;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strUser);
                AdProjectInfo projectInfo = (AdProjectInfo) JSONObject.toBean(jsObj, AdProjectInfo.class);
                return projectInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static CountryLevel getCountryLevel(SdkService sdkService, String projectId, int countryId) {

        try {
            String strDay = CalendarFormat.getyyyyMMdd(0);

            String rkey = String.format(Constants.KEY_COUNTRY_LEVEL, projectId, countryId, strDay);

            String strCL = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strCL)) {
                //find in db
                CountryLevel cl = sdkService.getCountryLevel(countryId, projectId);
                if (cl == null) {
                    //未按产品配置，取默认配置
                    cl = sdkService.getDefaultCountryLevel(countryId);
                }
                if (null == cl) {
                    //默认为无效等级
                    cl = new CountryLevel();
                    cl.setCountryId(countryId);
                    cl.setProductId(Integer.parseInt(projectId));
                    cl.setLevel(Constants.LEVEL_NONE);
                }
                JSONObject jsobj = JSONObject.fromObject(cl);
                RedisTool.set(rkey, jsobj.toString());
                RedisTool.expire(rkey, Constants.KEY_COUNTRY_LEVEL_EXPIRE);    //24 hours expired
                return cl;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strCL);
                CountryLevel cl = (CountryLevel) JSONObject.toBean(jsObj, CountryLevel.class);
                return cl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static AdProjectSetting getAdProjectSetting(SdkService sdkService, String projectId) {
        try {
            String rkey = String.format(Constants.KEY_PROJECT_ADSETTING, projectId);
            String strUser = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strUser)) {
                //find in db
                AdProjectSetting projectInfo = sdkService.getAdProjectSetting(projectId);
                if (projectInfo == null) {
                    //未注册用户
                } else {
                    JSONObject jsobj = JSONObject.fromObject(projectInfo);
                    RedisTool.set(rkey, jsobj.toString());
                    RedisTool.expire(rkey, Constants.KEY_PROJECT_ADSETTING_EXPIRE);    //5 minutes expired
                }
                return projectInfo;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strUser);
                AdProjectSetting projectInfo = (AdProjectSetting) JSONObject.toBean(jsObj, AdProjectSetting.class);
                return projectInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static SilentSetting getSilentSetting(SdkService sdkService, int sdkVersion) {
        try {
            String rkey = String.format(Constants.KEY_SILENT_SETTING, sdkVersion);
            String strUser = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strUser)) {
                //find in db
                SilentSetting projectInfo = sdkService.getSilentSetting(sdkVersion);
                if (projectInfo == null) {
                    //未注册用户
                } else {
                    JSONObject jsobj = JSONObject.fromObject(projectInfo);
                    RedisTool.set(rkey, jsobj.toString());
                    RedisTool.expire(rkey, Constants.KEY_SILENT_SETTING_EXPIRE);    //5 minutes expired
                }
                return projectInfo;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strUser);
                SilentSetting projectInfo = (SilentSetting) JSONObject.toBean(jsObj, SilentSetting.class);
                return projectInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static AdPluginInfo getPluginInfo(SdkService sdkService, int version) {
        try {
            String strPluginInfo = RedisTool.get(Constants.KEY_CURRENT_PLUGINFO);
            if (ProtocolUtil.isNullOrEmpty(strPluginInfo)) {
                //find in db
                AdPluginInfo pluginInfo = sdkService.getPluginInfo(version);
                if (pluginInfo == null) {
                    //no plug to download
                } else {
                    JSONObject jsobj = JSONObject.fromObject(pluginInfo);
                    RedisTool.set(Constants.KEY_CURRENT_PLUGINFO, jsobj.toString());
                    RedisTool.expire(Constants.KEY_CURRENT_PLUGINFO, Constants.KEY_CURRENT_PLUGINFO_EXPIRE);    //5 minutes expired
                }
                return pluginInfo;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strPluginInfo);
                AdPluginInfo projectInfo = (AdPluginInfo) JSONObject.toBean(jsObj, AdPluginInfo.class);
                return projectInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getAdjustKey(SdkService sdkService, String channel) {
        try {
            String akey = String.format(Constants.KEY_ADJUST_KEY, channel);
            String strKey = RedisTool.get(akey);
            if (ProtocolUtil.isNullOrEmpty(strKey)) {
                //find in db
                String adjustKey = sdkService.getAdjustKey(channel);
                if (adjustKey == null) {
                    //no plug to download
                } else {

                    RedisTool.set(akey, adjustKey);
                    RedisTool.expire(akey, Constants.KEY_ADJUST_KEY_EXPIRE);    //24 minutes expired
                }
                return adjustKey;
            } else {
                return strKey;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<ApkAdInfo> getApkAdList(SdkService sdkService, int adType, GetAdListReq reqInfo, int max) {

        long start = System.currentTimeMillis();
        reloadAdList(sdkService, adType); //ensure has ad list cache.
        logger.info("reload ad list time:" + (System.currentTimeMillis() - start) + "ms");

        max = (max == 0 ? 10 : max);
        List<ApkAdInfo> list = new ArrayList<ApkAdInfo>();
        String rkey = String.format(Constants.KEY_AD_SENTIST, reqInfo.getImei(), adType, reqInfo.getSdkStyle());
        try {
            /*从redis缓存中获取已发送的广告*/
            String sents = RedisTool.get(rkey);
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();

            StringBuffer sentbuffer = new StringBuffer();
            if (!ProtocolUtil.isNullOrEmpty(sents)) {
                sentList = sents.split(",");
                sentbuffer.append(sents);
                logger.info(String.format("%s has sent list: %s", rkey, sents));
            }
            CacheInfo o = mapCache.get(Constants.KEY_ADLIST + adType);

			/* 过滤待发送的广告，已发送的广告在有广告的情况下不再重新发送，不在语言范围内的广告不发送*/
            if (o != null) {
                List<ApkAdInfo> all = (List<ApkAdInfo>) o.getObject();
                logger.info("get cache ad list time:" + (System.currentTimeMillis() - start) + "ms");
                start = System.currentTimeMillis();
                int totalAd = 0;
                List<ApkAdInfo> sentAds = new ArrayList<ApkAdInfo>();


                for (ApkAdInfo adi : all) {
                    if (totalAd >= max) {
                        break;//最多max条广告
                    }
                    ApkAdInfo ad = (ApkAdInfo) adi.clone();

                    // 过滤掉已经下发过的广告
                    boolean hasSent = false;
                    for (String si : sentList) {
                        if (!"".equals(si) && Integer.parseInt(si) == ad.getApkId()) {
                            hasSent = true;
                            break;
                        }
                    }
                    if (hasSent) {
                        if (sentAds.size() < max) {//不超过条数限制
                            sentAds.add(ad);    //添加到已发送列表里-如果没有广告下发，则发送已发过的广告
                        }
                        logger.info(String.format("this ad %s is sent by imei %s.", ad.getApkId(), reqInfo.getImei()));
                        continue;
                    }
                    // 推广国家过滤
                    boolean isInZone = false;
                    if (null != ad.getLanguage()) {
                        String[] cous = ad.getLanguage().split(",");
                        for (String c : cous) {
                            if (!"".equals(c) && Integer.parseInt(c) == reqInfo.getCou()) {
                                isInZone = true;
                                break;
                            }
                        }
                    }
                    if (!isInZone) {
                        logger.info(String.format("this ad %s is not inzone %s.", ad.getApkId(), reqInfo.getCou()));
                        continue;
                    }


                    // 广告默认语言为英语，非英语需要翻译
                    String lanid = reqInfo.getLanguage();
                    boolean falg = true;
                    for (Language string : Language.values()) {
                        if (string.toString().contains(lanid)) {
                            falg = false;
                            break;
                        }
                    }
                    if (falg) {
                        lanid = "en";
                    }
                    String llkey = String.format(Constants.KEY_AD_LANGUAGE_INFO, ad.getApkId(), lanid);
                    if (!"en".equals(lanid)) {
                        String strLa = RedisTool.get(llkey);
                        if (ProtocolUtil.isNullOrEmpty(strLa)) {
                            //放在后台进行翻译,直接返回英文
                            AdLanguageInfo linfo = new AdLanguageInfo();
                            linfo.setAppName(ad.getAppName());
                            linfo.setPushText(ad.getPushText());
                            linfo.setIntroduction(ad.getIntroduction());

                            TranslateTool tt = new TranslateTool(linfo, lanid, llkey);
                            tt.start();
                        } else {
                            try {
                                JSONObject jsObj = JSONObject.fromObject(strLa);
                                AdLanguageInfo linfo = (AdLanguageInfo) JSONObject.toBean(jsObj, AdLanguageInfo.class);
                                ad.setAppName(linfo.getAppName());
                                ad.setPushText(linfo.getPushText());
                                ad.setIntroduction(linfo.getIntroduction());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    list.add(ad);

                    sentbuffer.append(",").append(ad.getApkId());
                    totalAd++;
                }
                logger.info("get ad list time:" + (System.currentTimeMillis() - start) + "ms");
                start = System.currentTimeMillis();
                if (list.size() > 0) {
                    RedisTool.set(rkey, sentbuffer.toString());
                    RedisTool.expire(rkey, Constants.KEY_AD_SENTIST_EXPIRE);
                } else {
                    if (sentAds.size() > 0) {
                        logger.info("has no new ads ,sent old list");
                        return sentAds;
                    }
                    if (all.size() > 0) {
                        logger.info(String.format("has ads, but no ad list : %s", rkey));
                    } else {
                        logger.info("no ad list");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return list;
    }

    /**
     * 将广告加载到类缓存中,并将数据有效时间设置为5分钟。可以实现5分钟内更新一下内存中广告
     *
     * @param sdkService
     * @param adType
     */
    public static void reloadAdList(SdkService sdkService, int adType) {
        try {
            String rkey = Constants.KEY_ADLIST + adType;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    List<ApkAdInfo> adList = sdkService.getAllApkAds(adType);
                    mapCache.put(rkey, new CacheInfo(new Date(), 5 * 60L, adList));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Country getCountry(AreaService area, String ipAddr) {
        try {
            String rkey = String.format(Constants.KEY_IP_COUNTRY, ipAddr);
            String strCL = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(strCL)) {
                //find in db
                Country cl = area.getCountry(ipAddr);
                if (null != cl) {
                    JSONObject jsobj = JSONObject.fromObject(cl);
                    RedisTool.set(rkey, jsobj.toString());
                    RedisTool.expire(rkey, Constants.KEY_IP_COUNTRY_EXPIRE);    //24 hours expired
                }
                return cl;
            } else {
                JSONObject jsObj = JSONObject.fromObject(strCL);
                Country cl = (Country) JSONObject.toBean(jsObj, Country.class);
                return cl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 得到所有静默插件列表（先缓存后数据库）
     *
     * @param sdkService
     * @return
     * @throws Exception
     */
    public static List<SilentPluginResp> getAllSilentPluginList(SdkService sdkService) throws Exception {
        List<SilentPluginResp> list;
        String key = Constants.KEY_SILENT_PLUGIN_INFO;
        CacheInfo value = mapCache.get(key);
        if (null == value || value.isExpired()) {
            list = sdkService.getAllSilentPluginList();
            if (null != list && 0 < list.size()) {
                synchronized (CacheInfoUtil.class) {
                    value = new CacheInfo(new Date(), Constants.KEY_SILENT_PLUGIN_INFO_EXPIRE, list);
                    mapCache.put(key, value);
                }
            }
        } else {
            list = (List<SilentPluginResp>) value.getObject();
        }
        return list;
    }

    /**
     * web网址的链接
     * @param sdkService
     * @param adId
     * @return
     */
    public static String getLinkAdUrl(SdkService sdkService, int adId) {
        List<SdkInfo> list = null;
        String linkTypeKey1 = Constants.KEY_LINKAD_URL + 1;
        String linkTypeKey2 = Constants.KEY_LINKAD_URL + 2;

        CacheInfo value1 = mapCache.get(Constants.KEY_LINKAD_URL + adId);
        if (null == value1 || value1.isExpired()) {
            List<SdkInfo> listAll = sdkService.getAllLinkAdUrl();
            if (null != listAll && 0 < listAll.size()) {
                List<SdkInfo> list1 = new ArrayList<>();
                List<SdkInfo> list2 = new ArrayList<>();
                synchronized (CacheInfoUtil.class) {
                    for (SdkInfo info : listAll) {
                        if (info.getStatus() == 1) {
                            list1.add(info);
                        } else if (info.getStatus() == 2) {
                            list2.add(info);
                        }
                    }
                    Date date = new Date();
                    mapCache.put(linkTypeKey1, new CacheInfo(date, Constants.KEY_LINKAD_URL_EXPIRE, list1));
                    mapCache.put(linkTypeKey2, new CacheInfo(date, Constants.KEY_LINKAD_URL_EXPIRE, list2));

                }
                if (adId == 2) {
                    list = list2;
                } else {
                    list = list1;
                }
            }
        } else {
            list = (List<SdkInfo>) value1.getObject();
        }

        String urlStr;
        if (null == list || 0 == list.size()) {
            urlStr = null;
        } else {
            urlStr = list.get(new Random().nextInt(list.size())).getUrl();
        }
        return urlStr;
    }

    /**
     * 将广告加载到类缓存中,并将数据有效时间设置为5分钟。可以实现5分钟内更新一下内存中广告
     *
     * @param sdkService
     * @param adType
     */
    public static void reloadWebAdList(SdkService sdkService) {
        try {
            String rkey = Constants.KEY_ADLIST + 100203;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    List<ApkAdInfo> adList = sdkService.getAllWebApkAds();
                    mapCache.put(rkey, new CacheInfo(new Date(), 5 * 60L, adList));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    /**
     * web广告缓存
     * @param sdkService
     * @param i
     * @param req
     * @param maxAds
     * @return
     */
    public static List<ApkAdInfo> getWebApkAdList(SdkService sdkService,   GetAdListReq reqInfo, int max) {
            int  adType=  Constants.ADTYPE_PUSH;
            long start = System.currentTimeMillis();
            reloadWebAdList(sdkService); //ensure has ad list cache.


            logger.info("reload ad list time:" + (System.currentTimeMillis() - start) + "ms");

            max = (max == 0 ? 10 : max);
            List<ApkAdInfo> list = new ArrayList<ApkAdInfo>();
            String rkey = String.format(Constants.KEY_AD_SENTIST, reqInfo.getImei(), adType, reqInfo.getSdkStyle());
            try {
            /*从redis缓存中获取已发送的广告*/
                String sents = RedisTool.get(rkey);
                String[] sentList = new String[]{};

                logger.info("get sent list time:" + (System.currentTimeMillis() - start) + "ms");
                start = System.currentTimeMillis();

                StringBuffer sentbuffer = new StringBuffer();
                if (!ProtocolUtil.isNullOrEmpty(sents)) {
                    sentList = sents.split(",");
                    sentbuffer.append(sents);
                    logger.info(String.format("%s has sent list: %s", rkey, sents));
                }
                CacheInfo o = mapCache.get(Constants.KEY_ADLIST + 100203);

			/* 过滤待发送的广告，已发送的广告在有广告的情况下不再重新发送，不在语言范围内的广告不发送*/
                if (o != null) {
                    List<ApkAdInfo> all = (List<ApkAdInfo>) o.getObject();
                    logger.info("get cache ad list time:" + (System.currentTimeMillis() - start) + "ms");
                    start = System.currentTimeMillis();
                    int totalAd = 0;
                    List<ApkAdInfo> sentAds = new ArrayList<ApkAdInfo>();


                    for (ApkAdInfo adi : all) {
                        if (totalAd >= max) {
                            break;//最多max条广告
                        }
                        ApkAdInfo ad = (ApkAdInfo) adi.clone();

                        // 过滤掉已经下发过的广告
                        boolean hasSent = false;
                        for (String si : sentList) {
                            if (!"".equals(si) && Integer.parseInt(si) == ad.getApkId()) {
                                hasSent = true;
                                break;
                            }
                        }
                        if (hasSent) {
                            if (sentAds.size() < max) {//不超过条数限制
                                sentAds.add(ad);    //添加到已发送列表里-如果没有广告下发，则发送已发过的广告
                            }
                            logger.info(String.format("this ad %s is sent by imei %s.", ad.getApkId(), reqInfo.getImei()));
                            continue;
                        }
                        // 推广国家过滤
                        boolean isInZone = false;
                        if (null != ad.getLanguage()) {
                            String[] cous = ad.getLanguage().split(",");
                            for (String c : cous) {
                                if (!"".equals(c) && Integer.parseInt(c) == reqInfo.getCou()) {
                                    isInZone = true;
                                    break;
                                }
                            }
                        }
                        if (!isInZone) {
                            logger.info(String.format("this ad %s is not inzone %s.", ad.getApkId(), reqInfo.getCou()));
                            continue;
                        }
                        list.add(ad);
                        sentbuffer.append(",").append(ad.getApkId());
                        totalAd++;
                    }
                    logger.info("get ad list time:" + (System.currentTimeMillis() - start) + "ms");
                    start = System.currentTimeMillis();
                    if (list.size() > 0) {
                        RedisTool.set(rkey, sentbuffer.toString());
                        RedisTool.expire(rkey, Constants.KEY_AD_SENTIST_EXPIRE);
                    } else {
                        if (sentAds.size() > 0) {
                            logger.info("has no new ads ,sent old list");
                            return sentAds;
                        }
                        if (all.size() > 0) {
                            logger.info(String.format("has ads, but no ad list : %s", rkey));
                        } else {
                            logger.info("no ad list");
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex.getMessage());
            }
            return list;
        }
}
