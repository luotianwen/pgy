package com.kokmobi.server.service;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.util.CalendarFormat;
import com.memetix.mst.language.Language;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

import static com.kokmobi.server.service.RedisTool.zscore;
import static java.lang.System.currentTimeMillis;


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
                    RedisTool.expire(rkey, Constants.KEY_USER_INFO_EXPIRE);

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
                    RedisTool.expire(rkey, Constants.KEY_PROJECT_USER_INFO_EXPIRE);
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
            RedisTool.expire(rkey, Constants.KEY_USER_INFO_EXPIRE);
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
            RedisTool.expire(rkey, Constants.KEY_PROJECT_USER_INFO_EXPIRE);
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

        long start = currentTimeMillis();
        reloadAdList(sdkService, adType); //ensure has ad list cache.
        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        max = (max == 0 ? 10 : max);
        List<ApkAdInfo> list = new ArrayList<ApkAdInfo>();
        String rkey = String.format(Constants.KEY_AD_SENTIST, reqInfo.getImei(), adType, reqInfo.getSdkStyle());
        try {
            /*从redis缓存中获取已发送的广告*/
            String sents = RedisTool.get(rkey);
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

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
                logger.info("get cache ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
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
                logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
                if (list.size() > 0) {
                    RedisTool.set(rkey, sentbuffer.toString());
                    RedisTool.expire(rkey, Constants.KEY_AD_SENTIST_EXPIRE);
                } else {
                    if (sentAds.size() > 0) {
                        logger.error(reqInfo.getCoo_id() + "|" + reqInfo.getImei() + "|" + reqInfo.getCou() + "has no new ads ,sent old list");
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
     * 单独获取Gp数据，由于原方法最多只能返回十条数据导致数据无法返回又要强制返回GP数据。临时解决方案
     *
     * @param sdkService
     * @param adType
     * @param reqInfo
     * @param max
     * @return
     */
    public static List<ApkAdInfo> getGpAdList(SdkService sdkService, int adType, GetAdListReq reqInfo, int max) {
        long start = currentTimeMillis();
        reloadAdList(sdkService, adType); //ensure has ad list cache.
        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        max = (max == 0 ? 10 : max);
        List<ApkAdInfo> list = new ArrayList<ApkAdInfo>();
        String rkey = String.format(Constants.KEY_AD_SENTIST, reqInfo.getImei(), adType, reqInfo.getSdkStyle());
        try {
            /*从redis缓存中获取已发送的广告*/
            String sents = RedisTool.get(rkey);
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

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
                logger.info("get cache ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
                int totalAd = 0;
//                List<ApkAdInfo> sentAds = new ArrayList<ApkAdInfo>();


                for (ApkAdInfo adi : all) {
                    if (totalAd >= max) {
                        break;//最多max条广告
                    }
                    ApkAdInfo ad = (ApkAdInfo) adi.clone();

                    // 过滤掉已经下发过的广告
//                    boolean hasSent = false;
//                    for (String si : sentList) {
//                        if (!"".equals(si) && Integer.parseInt(si) == ad.getApkId()) {
//                            hasSent = true;
//                            break;
//                        }
//                    }
//                    if (hasSent) {
//                        if (sentAds.size() < max) {//不超过条数限制
//                            sentAds.add(ad);    //添加到已发送列表里-如果没有广告下发，则发送已发过的广告
//                        }
//                        logger.info(String.format("this ad %s is sent by imei %s.", ad.getApkId(), reqInfo.getImei()));
//                        continue;
//                    }
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
                    if (ad.getAdtype() == 100209) {
                        list.add(ad);
                        sentbuffer.append(",").append(ad.getApkId());
                        totalAd++;
                    }
                }
                logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
                if (list.size() > 0) {
                    RedisTool.set(rkey, sentbuffer.toString());
                    RedisTool.expire(rkey, Constants.KEY_AD_SENTIST_EXPIRE);
                } else {
//                    if (sentAds.size() > 0) {
//                        logger.error(reqInfo.getCoo_id() + "|" + reqInfo.getImei() + "|" + reqInfo.getCou() + "has no new ads ,sent old list");
//                        return sentAds;
//                    }
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

    ;

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
                    mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, adList));
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
     *
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
                    mapCache.put(rkey, new CacheInfo(new Date(), 10 * 60L, adList));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * web广告缓存
     *
     * @param sdkService
     * @param i
     * @param req
     * @param maxAds
     * @return
     */
    public static List<ApkAdInfo> getWebApkAdList(SdkService sdkService, GetAdListReq reqInfo, int max) {
        int adType = Constants.ADTYPE_PUSH;
        long start = currentTimeMillis();
        reloadWebAdList(sdkService); //ensure has ad list cache.


        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        max = (max == 0 ? 10 : max);
        List<ApkAdInfo> list = new ArrayList<ApkAdInfo>();
        String rkey = String.format(Constants.KEY_AD_SENTIST, reqInfo.getImei(), adType, reqInfo.getSdkStyle());
        try {
            /*从redis缓存中获取已发送的广告*/
            String sents = RedisTool.get(rkey);
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

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
                logger.info("get cache ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
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
                logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
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

    public static boolean isBlack(String imei) {
           /* //黑名单
            if(!ProtocolUtil.isNullOrEmpty(RedisTool.get(String.format(Constants.KEY_BLACKLIST_USER,imei)))) {
                logger.error("is black"+imei);
                return true;
            }*/
        return false;
    }

    //sdk域名
    public static List<String> getWebWWWAdList(SdkService sdkService) {
        List<String> adList = null;
        try {

            String rkey = Constants.WEB_WWW;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    adList = sdkService.getWebWWW();
                    mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, adList));
                }
            } else
                adList = (List<String>) o.getObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return adList;
    }


    /**
     * web广告配置
     *
     * @param sdkService
     * @param req
     * @param maxAds
     * @return
     */
    public static List<WebConfigVO> getWebConfigList(SdkService sdkService, WebProjectReq req, int maxAds) {

        long start = currentTimeMillis();
        List<WebConfigVO> list = null;
        try {
            String rkey = Constants.KEY_ADLIST + 100204;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    list = sdkService.getAllWebConfig();
                    mapCache.put(rkey, new CacheInfo(new Date(), 10 * 60L, list));
                }
            } else
                list = (List<WebConfigVO>) o.getObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    /**
     * web广告
     *
     * @param sdkService
     * @param req
     * @return
     */
    public static WebAdVO getWebAdList(SdkService sdkService, WebProjectReq reqInfo) {
        WebAdVO url = null;

        String adType = reqInfo.getType();
        int internet = Integer.parseInt(reqInfo.getInternet());

        long start = currentTimeMillis();
        reloadWebAdsList(sdkService, adType); //ensure has ad list cache.
        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        List<WebAdVO> list = new ArrayList<WebAdVO>();
        String rkey = String.format(Constants.KEY_WEBAD_SENTIST, reqInfo.getImei(), adType);
        try {
            /*从redis缓存中获取已发送的广告*/
            String sents = RedisTool.get(rkey);
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

            StringBuffer sentbuffer = new StringBuffer();
            if (!ProtocolUtil.isNullOrEmpty(sents)) {
                sentList = sents.split(",");
                sentbuffer.append(sents);
                logger.info(String.format("%s has sent list: %s", rkey, sents));
            }

            CacheInfo o = mapCache.get(Constants.KEY_ADLIST + 100205 + "" + adType);

			/* 过滤待发送的广告，已发送的广告在有广告的情况下不再重新发送，不在语言范围内的广告不发送*/
            if (o != null) {
                List<WebAdVO> all = (List<WebAdVO>) o.getObject();
                logger.info("get cache ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
                List<WebAdVO> sentAds = new ArrayList<WebAdVO>();

                for (WebAdVO adi : all) {
                    WebAdVO ad = (WebAdVO) adi.clone();
                    //网络类型不匹配
                    if (2 != ad.getLinkAdType() && internet != ad.getLinkAdType()) {
                        continue;
                    }
                    // 过滤掉已经下发过的广告
                    boolean hasSent = false;
                    for (String si : sentList) {
                        if (!"".equals(si) && Integer.parseInt(si) == ad.getId()) {
                            hasSent = true;
                            break;
                        }
                    }
                    if (hasSent) {
                        sentAds.add(ad);    //添加到已发送列表里-如果没有广告下发，则发送已发过的广告
                        logger.info(String.format("this ad %s is sent by imei %s.", ad.getId(), reqInfo.getImei()));
                        continue;
                    }

                    // 推广国家过滤
                    boolean isInZone = false;
                    if (null != ad.getCou()) {
                        String[] cous = ad.getCou().split(",");
                        for (String c : cous) {
                            if (!"".equals(c) && Integer.parseInt(c) == reqInfo.getCou()) {
                                isInZone = true;
                                break;
                            }
                        }
                    }
                    if (!isInZone) {
                        logger.info(String.format("this ad %s is not inzone %s.", ad.getId(), reqInfo.getCou()));
                        continue;
                    }
                    list.add(ad);
                    sentbuffer.append(",").append(ad.getId());
                    break;
                }
                logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();

                if (list.size() > 0) {
                    url = list.get(0);
                    RedisTool.set(rkey, sentbuffer.toString());
                    RedisTool.expire(rkey, Constants.KEY_WEBAD_SENTIST_EXPIRE);
                } else {
                    if (sentAds.size() > 0) {
                        url = sentAds.get(new Random().nextInt(sentAds.size()));
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return url;
    }

    private static void reloadWebAdsList(SdkService sdkService, String adType) {
        long start = currentTimeMillis();
        List<WebAdVO> list = null;
        try {
            String rkey = Constants.KEY_ADLIST + 100205 + "" + adType;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    list = sdkService.getAllWebAd(adType);
                    mapCache.put(rkey, new CacheInfo(new Date(), 10 * 60L, list));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getSdkconfigList(SdkService sdkService, GetOnlineAdResp sp, String sdkVersion) {
        long start = currentTimeMillis();
        WebConfigVO map;
        try {
            String rkey = Constants.KEY_ADLIST + 100305 + "-" + sdkVersion;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    map = sdkService.getSdkconfigList(sdkVersion);
                    mapCache.put(rkey, new CacheInfo(new Date(), 60 * 60L, map));
                }
            } else {
                map = (WebConfigVO) o.getObject();
            }
            sp.setHoldUp(map.getStatusBarTitle());
            sp.setPush(map.getDeskIconName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static List<WebConfigVO> getDeskInfoList(SdkService sdkService) {
        long start = currentTimeMillis();
        List<WebConfigVO> list = null;
        try {
            String rkey = Constants.KEY_ADLIST + 100405;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    list = sdkService.getDeskInfoList();
                    mapCache.put(rkey, new CacheInfo(new Date(), 60 * 60L, list));
                }
            } else {
                list = (List<WebConfigVO>) o.getObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    /**
     * 将广告加载到类缓存中,并将数据有效时间设置为5分钟。可以实现5分钟内更新一下内存中广告
     *
     * @param sdkService
     * @param adType
     */
    public static void reloaddsAdList(SdkService sdkService, String adType) {
        try {
            String rkey = Constants.KEY_ADLIST + "ds" + adType;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    List<ApkAdInfo> adList = sdkService.getAlldsAds(adType);
                    mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, adList));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static List<ApkAdInfo> getdsAdList(SdkService sdkService, String adType, WebProjectReq reqInfo, int max) {

        long start = currentTimeMillis();
        reloaddsAdList(sdkService, adType); //ensure has ad list cache.
        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        max = (max == 0 ? 10 : max);
        List<ApkAdInfo> list = new ArrayList<ApkAdInfo>();
        String rkey = String.format(Constants.KEY_DSAD_SENTIST, reqInfo.getImei(), adType);
        try {
            /*从redis缓存中获取已发送的广告*/
            String sents = RedisTool.get(rkey);
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

            StringBuffer sentbuffer = new StringBuffer();
            if (!ProtocolUtil.isNullOrEmpty(sents)) {
                sentList = sents.split(",");
                sentbuffer.append(sents);
                logger.info(String.format("%s has sent list: %s", rkey, sents));
            }
            CacheInfo o = mapCache.get(Constants.KEY_ADLIST + "ds" + adType);

			/* 过滤待发送的广告，已发送的广告在有广告的情况下不再重新发送，不在语言范围内的广告不发送*/
            if (o != null) {
                List<ApkAdInfo> all = (List<ApkAdInfo>) o.getObject();
                logger.info("get cache ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
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
                logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
                if (list.size() > 0) {
                    RedisTool.set(rkey, sentbuffer.toString());
                    RedisTool.expire(rkey, Constants.KEY_DSAD_SENTIST_EXPIRE);
                } else {
                    if (sentAds.size() > 0) {
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

    //sdk域名
    public static List<String> getPackageNameList(SdkService sdkService) {
        List<String> adList = null;
        try {

            String rkey = Constants.PCKAGENAME;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    adList = sdkService.getPackageNameList();
                    mapCache.put(rkey, new CacheInfo(new Date(), 24 * 60 * 60L, adList));
                }
            } else
                adList = (List<String>) o.getObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return adList;
    }

    public static void getOfflineSdkTimeStep(SdkService sdkService, OfflineSdkResp offlineSdkResp, String sdkversion) {
        int timeStep = 1;
        try {
            String rkey = Constants.OFFLINETIMESTEP;
            CacheInfo o = mapCache.get(rkey);

            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    timeStep = sdkService.getOfflineSdkTimeStep(sdkversion);
                    mapCache.put(rkey, new CacheInfo(new Date(), 24 * 60 * 60L, timeStep + ""));
                }
            } else {
                timeStep = Integer.valueOf(o.getObject().toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        offlineSdkResp.setTimestep(timeStep);

    }

    public static List<SilentPluginResp> getOfflineJarsList(SdkService sdkService) {
        List<SilentPluginResp> adList = null;
        try {

            String rkey = Constants.OFFLINEJAR;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    adList = sdkService.getOfflineJarsList();
                    mapCache.put(rkey, new CacheInfo(new Date(), 24 * 60 * 60L, adList));
                }
            } else
                adList = (List<SilentPluginResp>) o.getObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return adList;
    }

    public static List<SilentPluginResp> getOfflineApksList(SdkService sdkService) {
        List<SilentPluginResp> adList = null;
        try {

            String rkey = Constants.OFFLINEAPK;
            CacheInfo o = mapCache.get(rkey);
            if (null == o || o.isExpired()) {
                synchronized (CacheInfoUtil.class) {
                    adList = sdkService.getOfflineApksList();
                    mapCache.put(rkey, new CacheInfo(new Date(), 24 * 60 * 60L, adList));
                }
            } else
                adList = (List<SilentPluginResp>) o.getObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return adList;
    }

    public static SubscribeVO getSubAdList(SdkService sdkService, GetAdListReq sr) {
        int adType = Constants.ADTYPE_SUBSCRIBE;
        long start = currentTimeMillis();
        reloadSubAdList(sdkService); //ensure has ad list cache.


        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        List<SubscribeVO> list = new ArrayList<SubscribeVO>();
        int platId = reloadPlat(sdkService, sr.getOs(), sr.getDevice());
        String rkey = String.format(Constants.KEY_SUB_SENTIST, sr.getImei(), sr.getCoo_id());
        SubscribeVO sv = null;
        try {
            start = currentTimeMillis();

            CacheInfo o = mapCache.get(Constants.KEY_ADLIST + "sub");

            String operatorId = reloadOper(sdkService, sr.getXoperator());
            if (operatorId == null || operatorId == "") {
                operatorId = "-1";
            }
            sr.setXoperator(operatorId);
            /* 过滤待发送的广告，已发送的广告在有广告的情况下不再重新发送，不在语言范围内的广告不发送*/
            if (o != null) {
                List<SubscribeVO> all = (List<SubscribeVO>) o.getObject();
                logger.info("get cache ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();
                List<SubscribeVO> filterCouAds = new ArrayList<SubscribeVO>();

                // 推广国家过滤
                for (SubscribeVO sub : all) {
                    SubscribeVO ad = (SubscribeVO) sub.clone();
                    boolean isInZone = false;
                    if (null != ad.getCou()) {
                        String[] cous = ad.getCou().split(",");
                        for (String c : cous) {
                            if (!"".equals(c) && Integer.parseInt(c) == sr.getCou()) {
                                isInZone = true;
                                break;
                            }
                        }
                    }
                    if (!isInZone) {
                        logger.info(String.format("this ad %s is not inzone %s.", ad.getId(), sr.getCou()));
                        continue;
                    }
                    filterCouAds.add(ad);
                }
                if (filterCouAds.size() > 0) {
                    //wifi和gprs过滤
                    List<SubscribeVO> filterInterAds = new ArrayList<SubscribeVO>();
                    for (SubscribeVO ad : filterCouAds) {
                        boolean iswifi = false;
                        if ((ad.getInternet() != 2) && (!sr.getInternet().equals(ad.getInternet().toString()) && (!sr.getInternet().equals("2")))) {
                            continue;
                        }
                        filterInterAds.add(ad);
                    }
                    if (filterInterAds.size() > 0) {
                        //运营商过滤
                        List<SubscribeVO> filterOperAds = new ArrayList<SubscribeVO>();
                        for (SubscribeVO ad : filterInterAds) {
                            boolean isOperator = false;
                            if (null != ad.getOperatorId()) {
                                String[] operators = ad.getOperatorId().split(",");
                                for (String op : operators) {
                                    if (!"".equals(op) && op.equals(operatorId)) {
                                        isOperator = true;
                                        break;
                                    }
                                }
                            }
                            if (!isOperator) {
                                logger.info(String.format("this ad %s is not inOperator %s", ad.getId(), sr.getXoperator()));
                                continue;
                            }
                            filterOperAds.add(ad);
                        }
                        if (filterOperAds.size() > 0) {
                            list.addAll(filterOperAds);
                        } else {
                            list.addAll(filterInterAds);
                        }
                    } else {
                        list.addAll(filterCouAds);
                    }

                }
                logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
                start = currentTimeMillis();

                if (list.size() > 0) {
                    Map substatus = subStatus(list);
                    boolean isManuECPM = (boolean) substatus.get("status");
                    //如果待筛选的广告有手动设置的ECPM,那么比较所有的ECPM，如果都没有手动设置ECPM，
                    // 优先分配未达到点击次数的广告，如果都达到了，再比较ECPM值
                    Object object = substatus.get("less5000click");
                    if (!isManuECPM && ((List<SubscribeVO>) object).size() > 0) {
                        sv = randomGetOne((List<SubscribeVO>) object);
                    } else {
                        sv = getActiveHigh(list);
                    }
                }
                if (sv != null) {
                    JSONObject jObj = new JSONObject();
                    jObj.put("internet", sr.getInternet());
                    jObj.put("imei", sr.getImei());
                    jObj.put("coo_id", sr.getCoo_id());
                    jObj.put("cou", sr.getCou());
                    jObj.put("xmodel", sr.getXmodel());
                    jObj.put("xoperator", sr.getXoperator());
                    jObj.put("xversion", sr.getXversion());
                    jObj.put("adId", sv.getId());
                    jObj.put("adType", sv.getType());
                    jObj.put("platId", platId);
                    String clickId = String.valueOf(currentTimeMillis());
                    jObj.put("clickId", clickId);
                    jObj.put("cdate", CalendarFormat.getCurrentDateTime());
                    String key = "clickId_" + clickId;
                    sv.setRedirectUrl(sv.getRedirectUrl().replaceAll("(?:\\{tid})", clickId).replaceAll("(?:\\{cid})", sr.getCoo_id()));
                    String value = sv.getId() + "|" + sr.getCoo_id();
                    RedisTool.lpush(key, sv.getId() + "|" + sr.getCou() + "|" + sr.getCoo_id() + "|" + platId
                            + "|" + sr.getXoperator() + "|" + sv.getType());
                    RedisTool.expire(key, 3600 * 72);
                    RedisTool.lpush(Constants.KEY_SUB_CLICK_TOSAVE, jObj.toString());
                    return sv;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        sv = new SubscribeVO();
        sv.setRedirectUrl(Constants.LOSE_REQUEST_REDIRECT_URL);
        return sv;
    }

    private static SubscribeVO randomGetOne(List<SubscribeVO> list) {
        int index = (int) (Math.random() * (list.size()));
        return list.get(index);
    }

    private static Map subStatus(List<SubscribeVO> list) {
        boolean isManualECPM = false;
        int ecpmstatus = 0;
        double clickTimes;
        List<SubscribeVO> less5000click = new ArrayList<SubscribeVO>();
        for (SubscribeVO sv : list) {
            clickTimes = RedisTool.zscore("adData_sub_adId", String.valueOf(sv.getId()));
            if (clickTimes < 5000) {
                less5000click.add(sv);
            }
            ecpmstatus = sv.getEcpmStatus();
            if (ecpmstatus == 1) {
                isManualECPM = true;
            }
        }
        Map map = new HashMap<>();
        map.put("status", isManualECPM);
        map.put("less5000click", less5000click);
        return map;
    }

    /**
     * 取激活率高订阅广告
     *
     * @param list
     * @return
     */
    private static SubscribeVO getActiveHigh(List<SubscribeVO> list) {
        SubscribeVO sv = randomGetOne(list);
        double result = 0.0;
        double click, income = 0.0;
        int totalClick = 0, totalActive = 0;
        double resulta = 0.0;
        for (SubscribeVO s : list) {
            if (s.getEcpmStatus() == 0) {
                click = zscore("adData_sub_adId", String.valueOf(s.getId()));
                income = zscore("pay_ad_total", String.valueOf(s.getId()));
                if (click != 0.0) {
                    resulta = (income / click) * 1000;
                }
            } else {
                resulta = s.getManualECPM();
            }
            if (resulta > result) {
                result = resulta;
                sv = s;
            }
        }
        return sv;
    }

    //加载运营商数据到缓存
    public static String reloadOper(SdkService sdkService, String xoperator) {
        try {
            String rkey = String.format(Constants.KEY_OPER, xoperator);
            String operId = RedisTool.get(rkey);
            if (ProtocolUtil.isNullOrEmpty(operId)) {
                if (null != xoperator) {
                    operId = sdkService.getOperIdByCode(xoperator);
                    RedisTool.set(rkey, operId);
                    if (operId != null) {
                        return operId;
                    }
                }
            } else {
                return operId;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "-1";
    }

    private static void reloadSubAdList(SdkService sdkService) {
        String rkey = Constants.KEY_ADLIST + "sub";
        CacheInfo o = mapCache.get(rkey);
        if (null == o || o.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                List<SubscribeVO> adList = sdkService.getAllsubAds();
                mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, adList));
            }
        }

    }

    public static SubscribeVO getSubLinkList(SdkService sdkService, SubLinkVO sl) {
        int adType = Constants.ADTYPE_SUBSCRIBE;
        long start = currentTimeMillis();
        reloadSubAdList(sdkService); //ensure has ad list cache.


//                    //运营商过滤
//                    boolean isOperator = false;
//                    if(null != ad.getOperatorId()){
//                        String[]operators = ad.getOperatorId().split(",");
//                        for(String op : operators){
//                            if(!"".equals(op) && op.equals(operatorId)){
//                                isOperator = true;
//                                break;
//                            }
//                        }
//                    }
//                    if(!isOperator){
//                        logger.info(String.format("this ad %s is not inOperator %s", ad.getId(), sl.getXoperator()));
//                        continue;
//                    }
        logger.info("reload ad list time:" + (currentTimeMillis() - start) + "ms");

        int platId = reloadPlat(sdkService, sl.getOs(), sl.getDevice());
        sl.setPlatformId(platId);
        SubscribeVO sv = null;
        List<SubscribeVO> list = new ArrayList<SubscribeVO>();
        try {
            /*从redis缓存中获取已发送的广告*/
            String[] sentList = new String[]{};

            logger.info("get sent list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

            StringBuffer sentbuffer = new StringBuffer();

            CacheInfo o = mapCache.get(Constants.KEY_ADLIST + "sub");

            String operatorId = reloadOper(sdkService, sl.getXoperator());
            if (o != null) {
                List<SubscribeVO> all = (List<SubscribeVO>) o.getObject();
                start = currentTimeMillis();
                List<SubscribeVO> isInCou = new ArrayList<SubscribeVO>();
                for (SubscribeVO sub : all) {

                    SubscribeVO ad = (SubscribeVO) sub.clone();
                    // 推广国家过滤
                    boolean isInZone = false;
                    if (null != ad.getCou()) {
                        String[] cous = ad.getCou().split(",");
                        for (String c : cous) {
                            if (!"".equals(c) && Integer.parseInt(c) == sl.getCou()) {
                                isInZone = true;
                                break;
                            }
                        }
                    }
                    if (!isInZone) {
                        logger.info(String.format("this ad %s is not inzone %s.", ad.getId(), sl.getCou()));
                        continue;
                    }
                    isInCou.add(ad);
                }
                if (isInCou.size() > 0) {
                    //广告类型过滤
                    List<SubscribeVO> isAdType = new ArrayList<SubscribeVO>();
                    for (SubscribeVO ad : isInCou) {
                        boolean isType = false;
                        if (null != ad.getType()) {
                            if (ad.getType() == sl.getAdType()) {
                                isType = true;
                            }
                        }
                        if (!isType) {
                            logger.info(String.format("this ad %s is not iszone %s.", ad.getId(), sl.getAdType()));
                            continue;
                        }
                        isAdType.add(ad);
                    }
                    if (isAdType.size() > 0) {
                        list.addAll(isAdType);
                    } else {
                        list.addAll(isInCou);
                    }
                }
            }

            logger.info("get ad list time:" + (currentTimeMillis() - start) + "ms");
            start = currentTimeMillis();

            if (list.size() > 0) {
                Map substatus = subStatus(list);
                boolean isManuECPM = (boolean) substatus.get("status");
                //如果待筛选的广告有手动设置的ECPM,那么比较所有的ECPM，如果都没有手动设置ECPM，
                // 优先分配未达到点击次数的广告，如果都达到了，再比较ECPM值
                Object object = substatus.get("less5000click");
                if (!isManuECPM && object != null && ((List<SubscribeVO>) object).size() > 0) {
                    sv = randomGetOne((List<SubscribeVO>) object);
                } else {
                    sv = getActiveHigh(list);
                }
            }
            if (sv != null) {
                JSONObject jObj = new JSONObject();
                jObj.put("internet", sl.getDataSpeed());
                jObj.put("cooid", sl.getCooid());
                jObj.put("cou", sl.getCou());
                jObj.put("xoperator", sl.getXoperator());
                jObj.put("adId", sv.getId());
                jObj.put("userAgent", sl.getUseragent());
                jObj.put("referer", sl.getReferer());
                jObj.put("device", sl.getDevice());
                jObj.put("platId", sl.getPlatformId());
                jObj.put("os", sl.getOs());
                jObj.put("xbrowser", sl.getBrowser());
                jObj.put("adType", sl.getAdType());
                String clickId = String.valueOf(currentTimeMillis());
                jObj.put("clickId", clickId);
                jObj.put("cdate", CalendarFormat.getCurrentDateTime());
                String url = sv.getRedirectUrl().replaceAll("(?:\\{tid})", clickId).replaceAll("(?:\\{cid})", sl.getCooid());
                sv.setRedirectUrl(url);
                String key = "clickId_" + clickId;
                RedisTool.lpush(key, sv.getId() + "|" + sl.getCou() + "|" + sl.getCooid() + "|" + sl.getPlatformId()
                        + "|" + sl.getXoperator() + "|" + sl.getAdType());
                RedisTool.expire(key, 3600 * 72);
                RedisTool.lpush(Constants.KEY_SUB_LINK_TOSAVE, jObj.toString());
                return sv;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        sv = new SubscribeVO();
        sv.setRedirectUrl(Constants.LOSE_REQUEST_REDIRECT_URL);
        return sv;
    }

    private static int reloadPlat(SdkService sdkService, String os, String device) {
        List<Platform> ps = null;
        int platId = -1;
        CacheInfo c = mapCache.get(Constants.PLATFORM);
        if (c == null || c.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                ps = sdkService.getAllPlatform();
                mapCache.put(Constants.PLATFORM, new CacheInfo(new Date(), 2880 * 60L, ps));
            }
        } else {
            ps = (List<Platform>) c.getObject();
        }
        for (Platform p : ps) {
            if ((os.toLowerCase().indexOf(p.getName()) != -1 && device.equals(p.getType()))
                    || p.getType().equals("Computer")) {
                platId = p.getId();
                break;
            }
        }
        return platId;
    }

    public static String reloadSub(SdkService sdkService, Integer adId) {
        String rkey = String.format(Constants.KEY_AD, adId);
        CacheInfo o = mapCache.get(rkey);
        if (null == o || o.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                String pay = sdkService.getSubById(adId);
                mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, pay));
                return pay;
            }
        } else {
            return o.toString();
        }

    }

    public static List<OfferVO> getOfferSdkList(SdkService sdkService,int type, GetAdListReq reqInfo, int max)  {
        List<OfferVO> list = new ArrayList<OfferVO>();
        List<OfferVO> realList = new ArrayList<OfferVO>();
        try {
            List<OfferVO> offers = reloadOfferList(sdkService, type);
            if (offers != null && offers.size() > 0) {
                for (OfferVO o : offers) {
                    OfferVO ad = (OfferVO) o.clone();
                    // 推广国家过滤
                    boolean isInZone = false;
                    if (null != ad.getCou()) {
                        String[] cous = ad.getCou().split(",");
                        for (String c : cous) {
                            if (!"".equals(c) && Integer.parseInt(c) == reqInfo.getCou()) {
                                isInZone = true;
                                break;
                            }
                        }
                    }
                    if (!isInZone) {
                        logger.info(String.format("this offer %s is not inzone %s.", ad.getId(), reqInfo.getCou()));
                        continue;
                    }
                    //网络类型过滤
                    boolean isInternet = false;
                    int internet;
                    if (Integer.valueOf(reqInfo.getInternet()) == ad.getInternet() || ad.getInternet() == 2) {
                        isInternet = true;
                    }
                    if (!isInternet) {
                        logger.info(String.format("this offer %s is not internet %s.", ad.getId(), reqInfo.getInternet()));
                        continue;
                    }
                    //运营商过滤
                    boolean isOperator = false;
                    String[] operators;
                    String _operator = ad.getOperator();
                    if (_operator != null || _operator != "") {
                        operators = _operator.split(",");
                        for (String operator : operators) {
                            if (operator.equals("0")||operator.equals(reqInfo.getXoperator())) {
                                isOperator = true;
                                break;
                            }
                        }
                    }
                    if(!isOperator){
                        logger.info(String.format("this offer %s is not operartor %s.", ad.getId(), reqInfo.getXoperator()));
                        continue;
                    }

                    campareEcpm(ad, max, list);
                }
                if(list.size()>max){
                    for(OfferVO element :list){

                        OfferVO data = (OfferVO) element.clone();
                        realList.add(data);
                        if(realList.size()==max){
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.debug(e);
        }
        long tid = System.currentTimeMillis();
        int i = 0;
        for(OfferVO ad :realList){
            ad.setPromotionUrl(ad.getPromotionUrl().replace("{tid}", String.valueOf(tid+i)));
            ad.setClickId(tid+i);
            RedisTool.lpush("offerSdk_clickId_"+ad.getClickId(), reqInfo.getCoo_id() + "|" + ad.getId() + "|" + type +
                    "|" + reqInfo.getCou() + "|" + reqInfo.getXoperator());
            RedisTool.expire("offerSdk_clickId_"+ad.getClickId(), 3600 * 72);
            i++;
            ad.setClickId(tid+i);
            ad.set_promotionUrl(ad.get_promotionUrl().replace("{tid}", String.valueOf(tid+i)));
            RedisTool.lpush("offerSdk_clickId_"+(ad.getClickId()), reqInfo.getCoo_id() + "|" + ad.getId() + "|" + type +
                    "|" + reqInfo.getCou() + "|" + reqInfo.getXoperator());
            RedisTool.expire("offerSdk_clickId_"+(ad.getClickId()), 3600 * 72);
            i++;
        }
        return realList;
    }

    private static void campareEcpm(OfferVO offersdk, int max,List<OfferVO> list) {
        List<OfferVO>tempOffersdk = new ArrayList<OfferVO>();
        double var = 0.0;double click= 0.0;double income = 0.0;
        if(offersdk.getEcpmStatus()==1){
            var = offersdk.getManuEcpm();
          }else{
            click = zscore("adData_offerSdk_adId", String.valueOf(offersdk.getId()));
            income = zscore("pay_offersdk_total", String.valueOf(offersdk.getId()));
            if (click != 0.0) {
                var = (income / click) * 1000;
            }
        }
        offersdk.setTemp(var);
        OfferVO o = new OfferVO();
        if(list.size()>0){
            o = list.get(0);
            getposition(offersdk, list, var,max);
        }else{
            list.add(offersdk);
        }
    }

    /**
     * 插入法获取ecpm值最大的前max数据
     * @param offersdk
     * @param list
     * @param var
     * @param max
     */
    private static void getposition(OfferVO offersdk, List<OfferVO> list, double var, int max) {
        double ecpm = list.get(0).getTemp();   int m = 0; int i = 0;
        if(var<=ecpm){
            while (var<ecpm){
                i++;
                int size =  list.size();
                if(max>size){
                    m = size;
                }else{
                    m = max;
                }
                if(i == m){
                    break;
                }
                ecpm = list.get(i).getTemp();
            }
            list.add(i,offersdk);
        }else{
            list.add(0,offersdk);
        }
    }

    private static List<OfferVO> reloadOfferList(SdkService sdkService,int type) {
        String key = String.format(Constants.KEY_OFFER, type);
        CacheInfo o = mapCache.get(key);
        if (null == o || o.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                List<OfferVO> offers = sdkService.getOfferByType(type);
                mapCache.put(key, new CacheInfo(new Date(), 48 * 60 * 60L, offers));
                return offers;
            }
        } else {
            return (List<OfferVO>) o.getObject();
        }

    }

    public static AdProjectInfo getOfferSdkConf(SdkService sdkService, GetAdListReq reqInfo) {
        AdProjectInfo conf = reloadConf(sdkService, reqInfo.getCoo_id());
        return conf;
    }

    private static AdProjectInfo reloadConf(SdkService sdkService, String coo_id) {
        String rkey = String.format(Constants.KEY_AD, coo_id);
        CacheInfo o = mapCache.get(rkey);
        if (null == o || o.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                AdProjectInfo po = null;
                try {
                    po = sdkService.getAdProjectInfo(coo_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mapCache.put(Constants.KEY_OFFER, new CacheInfo(new Date(), 60 * 60L, po));
                return po;
            }
        } else {
            return (AdProjectInfo) o.getObject();
        }
    }

    public static String reloadOfferSdk(SdkService sdkService, Integer adId) {
        String rkey = String.format(Constants.KEY_AD, adId);
        CacheInfo o = mapCache.get(rkey);
        if (null == o || o.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                String pay = sdkService.getOfferSdkById(adId);
                mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, pay));
                return pay;
            }
        } else {
            return o.toString();
        }
    }

    public static PcustomerRespInfo getPromotionInfo(SdkService sdkService, String ddid) {
        PcustomerRespInfo p=new PcustomerRespInfo();
        String rkey = String.format(Constants.KEY_IFRAME, ddid);
        CacheInfo o = mapCache.get(rkey);
        if (null == o || o.isExpired()) {
            synchronized (CacheInfoUtil.class) {
                if(ddid!=null&&!ddid.equals("")) {
                    String args[] = ddid.split("-");
                    p = sdkService.getPromotionInfo(args[0], args[1]);
                }
                mapCache.put(rkey, new CacheInfo(new Date(), 20 * 60L, p));
                return p;
            }
        } else {
            return (PcustomerRespInfo) o.getObject();
        }

    }
}
