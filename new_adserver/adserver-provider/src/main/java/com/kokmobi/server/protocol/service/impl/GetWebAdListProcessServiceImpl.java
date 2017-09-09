package com.kokmobi.server.protocol.service.impl;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.GetAdListProcessService;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.servlet.util.CompatibleUtil;
import com.kokmobi.server.servlet.util.TimeSpaceControllUtil;
import com.kokmobi.server.util.CalendarFormat;
import com.kokmobi.server.util.UpYun;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetWebAdListProcessServiceImpl implements GetAdListProcessService {

    private static Log logger = LogFactory.getLog(GetWebAdListProcessServiceImpl.class);

    private SdkService sdkService;
    private AreaService areaService;


    @Override
    public GetAdListResp process(GetAdListReq req) {
        return process(sdkService, areaService, req);
    }



    //    @Override
    public GetAdListResp process(SdkService sdkService, AreaService areaService,
                                 GetAdListReq req) {
        long start = System.currentTimeMillis();
        GetAdListResp resp = new GetAdListResp();
        // TODO 根据国家下发对应的push和pop广告，是否可push和pop先检查项目的设置
        //TODO: 判断基本参数是否异常
        String projectId = req.getCoo_id();
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
        boolean isInControll = TimeSpaceControllUtil.isAdListInControll(imei, req.getSdkStyle(), req.getCoo_id());
        if (false == isInControll) {
            logger.error(String.format("error: %s  got webad list at %s, out of number:%s", imei,
                    CalendarFormat.getCurrentDateTime(), String.valueOf(Constants.KEY_AD_LAST_SENT_NUM)));
            resp.setIsExit(0);
            return resp;
        }
        logger.info("check last req time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();

        try {
           /* AdProjectSetting projectSetting = CacheInfoUtil.getAdProjectSetting(sdkService, projectId);
            if (projectSetting == null) {

                logger.error(String.format("error: can not find project setting with id: %s", projectId));
                resp.setIsExit(0);
                return resp;
            }
*/

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
                CountryLevel cl = CacheInfoUtil.getCountryLevel(sdkService, req.getCoo_id(), c.getId());
                if (cl != null) {
                    countryLevel = cl.getLevel();
                }
            }
            logger.info(String.format("current coutry:%s of %s", countryId, req.getIpaddr()));
            logger.info("get country level time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();

            req.setCou(countryId);
            req.setCountryLevel(countryLevel);
            String pkgId = CompatibleUtil.uuid();
            /*PkgId是用来做什么：用来标识下发数据的接收状况*/
            resp.setPkgId(pkgId);

            List<ApkAdRespInfo> dataList = new ArrayList<ApkAdRespInfo>();    //to cache for reply...

            List<ApkAdInfo> popList = CacheInfoUtil.getWebApkAdList(sdkService,   req, Constants.MAX_ADS);
            resp.setCpApkInfo(changeToJSONObject(popList, Constants.ADTYPE_PUSH, dataList, req, pkgId));
            if (resp.getCpApkInfo().size() > 0) {
                resp.setIsExit(1);
                JSONArray jsarr = JSONArray.fromObject(dataList);
                RedisTool.set(String.format(Constants.KEY_AD_SENT_PACKAGE, pkgId), jsarr.toString());
                RedisTool.expire(String.format(Constants.KEY_AD_SENT_PACKAGE, pkgId), Constants.KEY_AD_SENT_PACKAGE_EXPIRE);//expired after 48 hours
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

    private String getwww() {
        String www = Constants.BASE_APK_URL_PATH;
        String key = "downloadUrl";
        String value = RedisTool.get(key);
        if (!ProtocolUtil.isNullOrEmpty(value)) {
            www = value;
        } else {
            RedisTool.set(key, www);
        }
        return www;
    }

    private List<JSONObject> changeToJSONObject(List<ApkAdInfo> list, int adType, List<ApkAdRespInfo> dataList, GetAdListReq req, String pkgId) {
        List<JSONObject> arr = new ArrayList<JSONObject>();
        List<String> toLpushList = new ArrayList<String>();
        long start = System.currentTimeMillis();
        String separate = "/";
        String iconStr, popImage, apkDownload;
        for (ApkAdInfo ad : list) {
            JSONObject o = new JSONObject();
            o.put("apkId", ad.getApkId());

            o.put("adtype", ad.getAdtype() - 100200);
            o.put("tracinglink", ad.getTracinglink());
            arr.add(o);

            Date cdate = new Date();
            ApkAdRespInfo data = new ApkAdRespInfo();
            data.setApkid(ad.getApkId());
            data.setCdate(CalendarFormat.getDateString(cdate.getTime()));
            data.setChannelid(req.getChannelId());
            data.setCoo_id(req.getCoo_id());
            data.setCou(req.getCou());
            data.setCountryLevel(req.getCountryLevel());
            data.setImei(req.getImei());
            data.setPkgid(pkgId);
            data.setPkgstatus(Constants.STATUS_NO);
            data.setSdk(adType);
            data.setSdkstyle(req.getSdkStyle());
            data.setSdkversion(String.format("%s", req.getSdkversion()));
            data.setXc_coo_id("0");
            dataList.add(data);
            JSONObject jsobj = JSONObject.fromObject(data);
            toLpushList.add(jsobj.toString());
        }
        logger.info("gen ad list time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        RedisTool.lpush(Constants.KEY_AD_SENTLIST_TOSAVE, toLpushList);
        logger.info("lpush ad time:" + (System.currentTimeMillis() - start) + "ms");
        return arr;
    }

    public void setSdkService(SdkService sdkService) {
        this.sdkService = sdkService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }
}
