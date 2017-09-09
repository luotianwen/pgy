package com.kokmobi.server.protocol.service.impl;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.WebAdListProcessService;
import com.kokmobi.server.service.*;
import com.kokmobi.server.servlet.util.TimeSpaceControllUtil;
import com.kokmobi.server.util.CalendarFormat;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class WebAdListProcessServiceImpl implements WebAdListProcessService {

    private static Log logger = LogFactory.getLog(WebAdListProcessServiceImpl.class);

    private SdkService sdkService;
    private AreaService areaService;


    public void setSdkService(SdkService sdkService) {
        this.sdkService = sdkService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    public WebProjectResp process(WebProjectReq req) {

        long start = System.currentTimeMillis();
        WebProjectResp resp = new WebProjectResp();
        // TODO 根据国家下发对应的push和pop广告，是否可push和pop先检查项目的设置
        //TODO: 判断基本参数是否异常
        String projectId = req.getCooId();
        if ((ProtocolUtil.isNullOrEmpty(projectId))
                || (ProtocolUtil.isNullOrEmpty(req.getImei()))) {
            resp.setIsExit(0);
            return resp;
        }

        //黑名单
        if(CacheInfoUtil.isBlack(req.getImei())) {
            resp.setIsExit(0);
            return resp;
        }
        logger.info("valid black time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        // 加上访问时间间隔控制
        /*一个小时之内同一个项目用户请求广告超过4次，则该小时内不能再次请求*/
        String imei = req.getImei();
        boolean isInControll = TimeSpaceControllUtil.isAdListInControll(imei, Constants.SDKSTYLE_WEB, projectId);
        if (false == isInControll) {
            logger.error(String.format("error: %s  got web ad list at %s, out of number:%s", imei,
                    CalendarFormat.getCurrentDateTime(), String.valueOf(Constants.KEY_AD_LAST_SENT_NUM)));
            resp.setIsExit(0);
            return resp;
        }
        logger.info("check last req time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();

        try {
             AdProjectSetting projectSetting = CacheInfoUtil.getAdProjectSetting(sdkService, projectId);
            if (projectSetting == null) {
                projectSetting = CacheInfoUtil.getAdProjectSetting(sdkService, "68");
            }
            if (projectSetting == null) {
                logger.error(String.format("error: can not find project setting with id: %s", projectId));
                resp.setIsExit(0);
                return resp;
            }
            resp.setIsLink(projectSetting.getIsLink());
            resp.setDesktopInterval(projectSetting.getDesktopInterval());
            resp.setDesktopTimes(projectSetting.getDesktopTimes());
            resp.setNoBrowserInterval(projectSetting.getNoBrowserInterval());
            resp.setNoBrowserTimes(projectSetting.getNoBrowserTimes());
            resp.setStatusBarTimes(projectSetting.getStatusBarTimes());
            resp.setStatusBarInterval(projectSetting.getStatusBarInterval());

            logger.info("check project Setting time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            // 获取国家id和国家等级
            int countryId = -1;
            int countryLevel = 4;
            Country c = CacheInfoUtil.getCountry(areaService, req.getIpaddr());
            logger.info("get country time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            if (c != null) {
                countryId = c.getId();
                logger.info("get country id:" + c.getId());
                CountryLevel cl = CacheInfoUtil.getCountryLevel(sdkService, projectId, c.getId());
                if (cl != null) {
                    countryLevel = cl.getLevel();
                }
            }
            logger.info(String.format("current coutry:%s of %s", countryId, req.getIpaddr()));
            logger.info("get country level time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();

            req.setCou(countryId);
            req.setCountryLevel(countryLevel);
            List<String> wwwList =CacheInfoUtil.getWebWWWAdList(sdkService);
           if(wwwList!=null&&wwwList.size()>0) {
               List<JSONObject> jsonList = new ArrayList<>(wwwList.size());
               for (String a : wwwList) {
                   JSONObject j=new JSONObject();
                    j.put("url",a);
                   jsonList.add(j);
               }
               resp.setWwws(jsonList);
           }


            List<WebConfigVO> adList = CacheInfoUtil.getWebConfigList(sdkService,   req, Constants.MAX_ADS);

            resp.setLinkInfors(changeToJSONObject(adList));
            if (resp.getLinkInfors().size() > 0) {
                resp.setIsExit(1);
            } else {
                resp.setIsExit(0);
            }

            return resp;
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setIsExit(0);
            return resp;
        }
    }

     //广告点击
    public String processSdkUrl(WebProjectReq req) {
        String url=null;
            long start = System.currentTimeMillis();
            String projectId = req.getCooId();

            if ((ProtocolUtil.isNullOrEmpty(req.getInternet()))
                    || (ProtocolUtil.isNullOrEmpty(req.getType()))
                    || (ProtocolUtil.isNullOrEmpty(req.getImei()))) {

                return null;
            }
            if (!ProtocolUtil.isIntegers(projectId, req.getInternet(), req.getType())) {
                logger.error("error: param is not int");
                return null;
            }
            //黑名单
            if(CacheInfoUtil.isBlack(req.getImei())) {
                return null;
            }
            logger.info("valid black time:"+ (System.currentTimeMillis()-start) + "ms");
            start = System.currentTimeMillis();


            try {

                logger.info("check project Setting time:"+ (System.currentTimeMillis()-start) + "ms");
                start = System.currentTimeMillis();
                // 获取国家id和国家等级
                int countryId = -1;
                int countryLevel = 4;
                Country c = CacheInfoUtil.getCountry(areaService,req.getIpaddr());
                logger.info("get country time:"+ (System.currentTimeMillis()-start) + "ms");
                start = System.currentTimeMillis();
                if(c != null) {
                    countryId = c.getId();
                    logger.info("get country id:"+c.getId());
                    CountryLevel cl = CacheInfoUtil.getCountryLevel(sdkService, projectId, c.getId());
                    if(cl != null) {
                        countryLevel = cl.getLevel();
                    }
                }
                logger.info(String.format("current coutry:%s of %s", countryId, req.getIpaddr()));
                logger.info("get country level time:"+ (System.currentTimeMillis()-start) + "ms");
                start = System.currentTimeMillis();

                req.setCou(countryId);
                req.setCountryLevel(countryLevel);

                WebAdVO  a = CacheInfoUtil.getWebAdList(sdkService, req);
                 if(a!=null) {
                     url = a.getRedirectUrl();

                     webAdToRedis(a,req);
                 }
                else{
                     logger.info(String.format("has not webad in :0 of %s", countryId, req.getImei()));
                 }
                logger.info("get push ads time:"+ (System.currentTimeMillis()-start) + "ms");

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        return url;

    }




    private  void webAdToRedis( WebAdVO  a,WebProjectReq req){
    Date cdate = new Date();
    JSONObject jsObj =new JSONObject();

    jsObj.put("adId",a.getId());
    jsObj.put("imei",req.getImei());
    jsObj.put("coo_id",req.getCooId());
    //网络类型
    jsObj.put("linkType",req.getInternet());
    //推广类型
    jsObj.put("clickType",req.getType());

    jsObj.put("cou",req.getCou());
    jsObj.put("cdate",CalendarFormat.getDateString(cdate.getTime()));

    RedisTool.lpush(Constants.KEY_AD_LINK_TOSAVE, jsObj.toString());

}

    private List<JSONObject> changeToJSONObject(List<WebConfigVO> list) {
        List<JSONObject> arr = new ArrayList<JSONObject>();
          if(list==null||list.size()==0)
              return arr;

        long start = System.currentTimeMillis();

        for (WebConfigVO ad : list) {
            JSONObject o = new JSONObject();
            o.put("linkId", ad.getLinkId());
            o.put("linkUrl", ad.getLinkUrl());
            o.put("linkType", ad.getLinkType());
            o.put("deskIconName", ad.getDeskIconName());
            o.put("deskIconUrl", ad.getDeskIconUrl());
            o.put("statusBarTitle", ad.getStatusBarTitle());
            o.put("statusBarContent", ad.getStatusBarContent());
            arr.add(o);

        }
        logger.info("gen ad list time:" + (System.currentTimeMillis() - start) + "ms");

        return arr;
    }


    //渠道推广链接
    public String promotionUrl(String ddid) {
        String rkey = String.format(Constants.KEY_PROMOTION, ddid);

        String url=RedisTool.get(rkey);
        if(!ProtocolUtil.isNullOrEmpty(url)){
            try {
                url= URLDecoder.decode(url,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            url=url.replaceAll("\\{tid\\}",get20UUID());
        }
        return url;
    }

    @Override
    public String promotionIframeUrl(String ddid, String fromAddr) {
        int countryId = -1;
        Country c = CacheInfoUtil.getCountry(areaService,fromAddr);
        if(c != null) {
            countryId = c.getId();
        }
        PcustomerRespInfo promotion=CacheInfoUtil.getPromotionInfo(sdkService,ddid);
        if(promotion.getCou()!=countryId){
            return promotion.getOtherUrl();
        }
        String rkey = String.format(Constants.KEY_PROMOTION_IFRAME, ddid);
        String url=RedisTool.get(rkey);
        if(!ProtocolUtil.isNullOrEmpty(url)){
            try {
                url= URLDecoder.decode(url,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else{
            url=promotion.getRedirectUrl();
            RedisTool.set(rkey,url);
            RedisTool.expire(rkey,60*60*24*7);
        }
        return url;

    }

    @Override
    public void processOnline(GetOnlineReq reqInfo) {
        String pkgid=reqInfo.getPkgid();
        String imei=reqInfo.getImei();
        String pkey = String.format(Constants.KEY_ONLINE, imei,pkgid);
        String strPkg = RedisTool.get(pkey);
        if(ProtocolUtil.isNullOrEmpty(strPkg)) {
            RedisTool.set(pkey,pkgid);
            RedisTool.expire(pkey, Constants.KEY_ONLINE_EXPIRE);    //expired after 3 days.

            int countryId = -1;
            Country c = CacheInfoUtil.getCountry(areaService,reqInfo.getIpaddr());
            if(c != null) {
                countryId = c.getId();
            }
            JSONObject jsObj = new JSONObject();
            Date cdate = new Date();
            jsObj.put("cou", countryId);
            jsObj.put("imei", imei);
            jsObj.put("adId", reqInfo.getAdId());
            jsObj.put("clickType", reqInfo.getType());
            jsObj.put("linkType", pkgid);
            jsObj.put("cdate",CalendarFormat.getDateString(cdate.getTime()));
            RedisTool.lpush(Constants.KEY_AD_DSSDK_TOSAVE, jsObj.toString());
        }
    }

    @Override
    public GetOnlineAdResp processOnlineWeb(WebProjectReq req) {
        GetOnlineAdResp sp=new GetOnlineAdResp();
        if ((ProtocolUtil.isNullOrEmpty(req.getCooId()))
                || (ProtocolUtil.isNullOrEmpty(req.getSdkVersion()))
                || (ProtocolUtil.isNullOrEmpty(req.getImei()))) {
            return null;
        }
        int countryId = -1;
        Country c = CacheInfoUtil.getCountry(areaService,req.getIpaddr());
        if(c != null) {
            countryId = c.getId();
        }
        req.setCou(countryId);
        //电商sdk版本配置
         CacheInfoUtil.getSdkconfigList(sdkService, sp,req.getSdkVersion());
        //deskInfo
        List<WebConfigVO>list=CacheInfoUtil.getDeskInfoList(sdkService);
        changeToJSONObject(list,sp);
        List<String> wwwList =CacheInfoUtil.getWebWWWAdList(sdkService);
        //wwws
        if(wwwList!=null&&wwwList.size()>0) {
            List<JSONObject> jsonList = new ArrayList<>(wwwList.size());
            for (String a : wwwList) {
                JSONObject j=new JSONObject();
                j.put("url",a);
                jsonList.add(j);
            }
            sp.setWwws(jsonList);
        }

        List<ApkAdInfo> levitateInfoList = CacheInfoUtil.getdsAdList(sdkService, "2", req, Constants.MAX_ADS);
        List<ApkAdRespInfo> dataList = new ArrayList<ApkAdRespInfo>();    //to cache for reply...
        sp.setLevitateInfo(changeTolevitateInfoJSONObject(levitateInfoList,req));
        List<ApkAdInfo> bannerInfoList = CacheInfoUtil.getdsAdList(sdkService, "3", req, Constants.MAX_ADS);
        sp.setBannerInfo(changeTobannerInfoJSONObject(bannerInfoList,req));
        List<ApkAdInfo> subscribeInfoList = CacheInfoUtil.getdsAdList(sdkService, "4", req, Constants.MAX_ADS);
        sp.setSubscribeInfo(changeTosubscribeInfoListInfoJSONObject(subscribeInfoList,req));
        List<String> packageNames =CacheInfoUtil.getPackageNameList(sdkService);
        //
        if(wwwList!=null&&wwwList.size()>0) {
            List<JSONObject> jsonList = new ArrayList<>(packageNames.size());
            for (String a : packageNames) {
                JSONObject j=new JSONObject();
                j.put("packageName",a);
                jsonList.add(j);
            }
            sp.setHoldUpInfo(jsonList);
        }


        sp.setIsExit(1);
        return sp;
    }
    @Override
    public OfflineSdkResp processOfflineSdk(Map<String,String> map) {
        if ((ProtocolUtil.isNullOrEmpty(map.get("coo_id")))
                || (ProtocolUtil.isNullOrEmpty(map.get("sdkversion")))
                || (ProtocolUtil.isNullOrEmpty(map.get("imei")))) {
            return null;
        }
        OfflineSdkResp offlineSdkResp=new OfflineSdkResp();

        int countryId = -1;
        Country c = CacheInfoUtil.getCountry(areaService,map.get("fromAddr"));
        if(c != null) {
            countryId = c.getId();
        }
        // 做中国和外国的区分根据项目EXE是否强制判断测试还是正式
        map.put("cou",countryId+"");
        //OfflineSdk
        CacheInfoUtil.getOfflineSdkTimeStep(sdkService, offlineSdkResp, map.get("sdkversion"));
        AdProjectInfo project = CacheInfoUtil.getAdProjectInfo(sdkService, map.get("coo_id"));
        if(project != null) {
          if(project.getExe()== 3201){
              if(countryId == 183){
                  return null;
              }
          }
        }
        // 获取国家id和国家等级

        List<SilentPluginResp> jarsList =CacheInfoUtil.getOfflineJarsList(sdkService);

        List<JSONObject> arr = new ArrayList<JSONObject>();
        for(SilentPluginResp ad : jarsList) {
            JSONObject o = new JSONObject();
            o.put("version", ad.getPlugType());
            o.put("url", ad.getPlugDownloadURL());
            o.put("startClass", ad.getExtensionContry());
            o.put("startArgu", ad.getPlugPackageName());
            o.put("apkId", ad.getPlugId());
            o.put("md5", ad.getPlugSevPara());
            arr.add(o);
        }

        offlineSdkResp.setJars(arr);

        List<SilentPluginResp> apksList =CacheInfoUtil.getOfflineApksList(sdkService);

        List<JSONObject> apks = new ArrayList<JSONObject>();
        for(SilentPluginResp ad : apksList) {
            JSONObject o = new JSONObject();
            o.put("version", ad.getPlugType());
            o.put("url", ad.getPlugDownloadURL());
            o.put("serviceName", ad.getPlugSevName());
            o.put("packageName", ad.getExtensionContry());
            o.put("apkId", ad.getPlugId());
            o.put("activityName", ad.getPlugSevName());
            o.put("md5", ad.getPlugSevPara());
            o.put("installClock", ad.getPlugName());
            o.put("installZone", ad.getPlugPackageName());
            apks.add(o);
        }
        offlineSdkResp.setApks(apks);
        offlineSdkResp.setStatus(1);
        return offlineSdkResp;
    }

    @Override
    public OfferSdkResp processOfferSdk(GetAdListReq reqInfo) {
        if ((ProtocolUtil.isNullOrEmpty(reqInfo.getCoo_id()))
                || (ProtocolUtil.isNullOrEmpty(reqInfo.getXoperator()))
                || (ProtocolUtil.isNullOrEmpty(reqInfo.getImei()))) {
            return null;
        }
        OfferSdkResp offersdkresp=new OfferSdkResp();

        int countryId = -1;
        Country c = CacheInfoUtil.getCountry(areaService,reqInfo.getIpaddr());
        if(c != null) {
            countryId = c.getId();
        }
        reqInfo.setCou(countryId);

        String operatorId = CacheInfoUtil.reloadOper(sdkService, reqInfo.getXoperator());
        if (operatorId == null || operatorId == "") {
            operatorId = "-1";
        }
        List<String> wwwList =CacheInfoUtil.getWebWWWAdList(sdkService);
        //wwws
        if(wwwList!=null&&wwwList.size()>0) {
            List<JSONObject> jsonList = new ArrayList<>(wwwList.size());
            for (String a : wwwList) {
                JSONObject j=new JSONObject();
                j.put("url",a);
                jsonList.add(j);
            }
            offersdkresp.setWwws(jsonList);
        }
        List<OfferVO>isempty = new ArrayList<OfferVO>();
        reqInfo.setXoperator(operatorId);
        List<OfferVO> pushInfo = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_PUSH, reqInfo, 3);
        offersdkresp.setPushInfo(changeToOfferVOInfoJSONObject(pushInfo, Constants.OFFERSDK_PUSH, reqInfo));
        List<OfferVO> appholdInfo = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_APPHOLD, reqInfo, 3);
        offersdkresp.setAppInfo(changeToOfferVOInfoJSONObject(appholdInfo, Constants.OFFERSDK_APPHOLD,reqInfo));
        List<OfferVO> broswerInfo = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_BROWSER, reqInfo, 3);
        offersdkresp.setBrowserInfo(changeToOfferVOInfoJSONObject(broswerInfo, Constants.OFFERSDK_BROWSER, reqInfo));
        List<OfferVO> createiconInfoA = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_CREATEICONA, reqInfo, 1);
        offersdkresp.setDeskIconA(changeToOfferVOInfoJSONObject(createiconInfoA, Constants.OFFERSDK_CREATEICONA, reqInfo));
        List<OfferVO> createiconInfoB = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_CREATEICONB, reqInfo, 1);
        offersdkresp.setDeskIconB(changeToOfferVOInfoJSONObject(createiconInfoB, Constants.OFFERSDK_CREATEICONB, reqInfo));
        List<OfferVO> levitateInfo = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_LEVITATE, reqInfo, 3);
        offersdkresp.setLevitateInfo(changeToOfferVOInfoJSONObject(levitateInfo, Constants.OFFERSDK_LEVITATE, reqInfo));
        List<OfferVO> plaqueInfo = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_PLAQUE, reqInfo, 3);
        offersdkresp.setPlaqueInfo(changeToOfferVOInfoJSONObject(plaqueInfo, Constants.OFFERSDK_PLAQUE, reqInfo));
        List<OfferVO> uplevelInfo = CacheInfoUtil.getOfferSdkList(sdkService, Constants.OFFERSDK_UPLEVEL, reqInfo, 1);
        offersdkresp.setUpLevelInfo(changeToOfferVOInfoJSONObject(uplevelInfo, Constants.OFFERSDK_UPLEVEL, reqInfo));
        AdProjectInfo po = CacheInfoUtil.getOfferSdkConf(sdkService, reqInfo);
        offersdkresp.setProjectConf(JSONObject.fromObject(po));
        isempty.addAll(pushInfo);
        isempty.addAll(appholdInfo);
        isempty.addAll(broswerInfo);
        isempty.addAll(createiconInfoA);
        isempty.addAll(createiconInfoB);
        isempty.addAll(levitateInfo);
        isempty.addAll(plaqueInfo);
        isempty.addAll(uplevelInfo);
        if(isempty.size()> 0 && po!=null){
            offersdkresp.setIsExit(1);
        }else{
            offersdkresp = new OfferSdkResp();
        }
        return offersdkresp;
    }

    private List<JSONObject> changeToOfferVOInfoJSONObject(List<OfferVO> pushInfo, int type, GetAdListReq req) {
        List<JSONObject> list = new ArrayList<JSONObject>();
        List<String> toLpushList = new ArrayList<String>();
        long start = System.currentTimeMillis();
        for(OfferVO ov :pushInfo){
            JSONObject o = new JSONObject();
            o.put("sdkVersion", ov.getSdkVersion());
            o.put("type", type);
            o.put("promotionUrl", ov.getPromotionUrl());
            o.put("_promotionUrl", ov.get_promotionUrl());
            o.put("imageUrl", ov.getImageUrl());
            o.put("name", ov.getImageName());
            o.put("remark", ov.getRemark());
            o.put("id", ov.getId());
            list.add(o);

            Date cdate = new Date();
            ApkAdRespInfo data = new ApkAdRespInfo();
            data.setApkid(ov.getId());
            data.setCdate(CalendarFormat.getDateString(cdate.getTime()));
            data.setCoo_id(req.getCoo_id());
            data.setCou(req.getCou());
            data.setCountryLevel(req.getCountryLevel());
            data.setImei(req.getImei());
            data.setSdk(type);
            data.setSdkstyle(req.getSdkStyle());
            data.setXoperator(req.getXoperator());
            data.setSdkversion(String.format("%s", req.getSdkversion()));
            JSONObject jsobj = JSONObject.fromObject(data);
            toLpushList.add(jsobj.toString());
        }
        logger.info("gen ad list time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        RedisTool.lpush(Constants.OFFER_SDK_SENTLIST_TOSAVE, toLpushList);
        logger.info("lpush ad time:" + (System.currentTimeMillis() - start) + "ms");
        return list;
    }

    @Override
    public void processOnlinesdk(GetOnlineReq reqInfo) {
        String pkgid=reqInfo.getPkgid();
        String imei=reqInfo.getImei();
        String pkey = String.format(Constants.KEY_ONLINESDK, imei,pkgid);
        String strPkg = RedisTool.get(pkey);
        String operatorId = CacheInfoUtil.reloadOper(sdkService, reqInfo.getXoperator());
        if (operatorId == null || operatorId == "") {
            operatorId = "-1";
        }
        if(ProtocolUtil.isNullOrEmpty(strPkg)) {
            RedisTool.set(pkey,pkgid);
            RedisTool.expire(pkey, Constants.KEY_ONLINESDK_EXPIRE);    //expired after 3 days.

            int countryId = -1;
            Country c = CacheInfoUtil.getCountry(areaService,reqInfo.getIpaddr());
            if(c != null) {
                countryId = c.getId();
            }
            JSONObject jsObj = new JSONObject();
            Date cdate = new Date();
            jsObj.put("cou", countryId);
            jsObj.put("coo_id", reqInfo.getCooId());
            jsObj.put("imei", imei);
            jsObj.put("apkid", reqInfo.getAdId());
            jsObj.put("sdk", reqInfo.getType());
            jsObj.put("dataType", reqInfo.getBackType());
            jsObj.put("sdkversion", reqInfo.getSdkVersion());
            jsObj.put("pkgid", pkgid);
            jsObj.put("xoperator", operatorId);
            jsObj.put("cdate",CalendarFormat.getDateString(cdate.getTime()));
            RedisTool.lpush(Constants.KEY_OFFER_SDK_TOSAVE, jsObj.toString());
        }
    }

    @Override
    public void offerSdkActive(ActiveInfo activeInfo) {
        String pkey = String.format(Constants.KEY_ACTIVE, "offersdk_active", activeInfo.getClickId());
        String strPkg = RedisTool.get(pkey);
        if(ProtocolUtil.isNullOrEmpty(strPkg)) {
            RedisTool.set(pkey, activeInfo.getClickId());
            JSONObject jsObj = new JSONObject();
            Date cdate = new Date();
            jsObj.put("clickid", activeInfo.getClickId());
            String key = "offerSdk_clickId_" + activeInfo.getClickId();
            String value = RedisTool.lindex(key);
            jsObj.put("key", value);
            if(activeInfo.getPayout() == null || activeInfo.getPayout() == ""){
                if (value != null && value != "") {
                    String[] datas = value.split("\\|");
                    Integer adId = Integer.valueOf(datas[1]);
                    String pay = CacheInfoUtil.reloadOfferSdk(sdkService, adId);
                    activeInfo.setPayout(pay);
                }
            }
            jsObj.put("payout", activeInfo.getPayout());
            jsObj.put("cdate", CalendarFormat.getDateString(cdate.getTime()));
            RedisTool.lpush(Constants.KEY_OFFERSDK_ACTIVE_TOSAVE, jsObj.toString());
        }
    }


    private List<JSONObject> changeTosubscribeInfoListInfoJSONObject(List<ApkAdInfo> list, WebProjectReq req) {
        List<JSONObject> arr = new ArrayList<JSONObject>();
        for(ApkAdInfo ad : list) {
            JSONObject o = new JSONObject();
            o.put("id", ad.getApkId());
            o.put("subscribeDetailUrl", ad.getOutwww());
            o.put("subscribeName", ad.getIntroduction());
            o.put("subscribeImageUrl", ad.getOuticonwww());
            arr.add(o);
        }
        return arr;
    }

    private List<JSONObject> changeTobannerInfoJSONObject(List<ApkAdInfo> list, WebProjectReq req) {
        List<JSONObject> arr = new ArrayList<JSONObject>();
        for(ApkAdInfo ad : list) {
            JSONObject o = new JSONObject();
            o.put("id", ad.getApkId());
            o.put("bannerImageUrl", ad.getOuticonwww());
            o.put("bannerContent", ad.getIntroduction());
            o.put("bannerDetailUrl", ad.getOutwww());
            arr.add(o);
        }
        return arr;
    }
    private List<JSONObject> changeTolevitateInfoJSONObject(List<ApkAdInfo> list, WebProjectReq req) {
        List<JSONObject> arr = new ArrayList<JSONObject>();
        List<String> toLpushList = new ArrayList<String>();
        for(ApkAdInfo ad : list) {
            JSONObject o = new JSONObject();
            o.put("id", ad.getApkId());
            o.put("levitateIconUrl", ad.getOuticonwww());
            o.put("name", ad.getAppName());
            o.put("detailurl", ad.getOutwww());
            arr.add(o);
        }
        return arr;
    }


    private  void changeToJSONObject(List<WebConfigVO> list,GetOnlineAdResp sp) {
        if(null==list||list.size()==0)
            return;
        List<JSONObject> arr = new ArrayList<JSONObject>();
        for (WebConfigVO ad : list) {
            JSONObject o2 = new JSONObject();
            o2.put("deskId", ad.getLinkId());
            o2.put("homepage", ad.getLinkUrl());
            o2.put("deskName", ad.getDeskIconName());
            o2.put("deskIconUrl", ad.getDeskIconUrl());
            arr.add(o2);
        }
        sp.setDeskInfo(arr);
    }


    private static String get20UUID(){
        String uuid= UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
public  static  void main(String arg[]){
    String url="http://www.baidu.com?sub=5&tid={tid}";
    url=url.replaceAll("\\{tid\\}",get20UUID());
    System.out.println(url);
}
}
