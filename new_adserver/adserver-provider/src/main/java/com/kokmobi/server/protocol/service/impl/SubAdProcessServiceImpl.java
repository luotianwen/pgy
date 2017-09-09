package com.kokmobi.server.protocol.service.impl;

import com.kokmobi.server.bean.*;
import com.kokmobi.server.commons.Constants;
import com.kokmobi.server.protocol.service.ProtocolUtil;
import com.kokmobi.server.protocol.service.SubAdProcessService;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.CacheInfoUtil;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.service.SdkService;
import com.kokmobi.server.util.CalendarFormat;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

import static com.kokmobi.server.service.CacheInfoUtil.reloadSub;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/18
 *          Time: 14:15
 * @author: mm
 * @since 3.0
 */
public class SubAdProcessServiceImpl implements SubAdProcessService {
    private static Log logger = LogFactory.getLog(SubAdProcessServiceImpl.class);

    private SdkService sdkService;
    private AreaService areaService;

    @Override
    public String processSubCli(GetAdListReq sr) {
        String url = null;
        long start = System.currentTimeMillis();

        if ((ProtocolUtil.isNullOrEmpty(sr.getInternet()))
                || (ProtocolUtil.isNullOrEmpty(sr.getXversion()))
                || (ProtocolUtil.isNullOrEmpty(sr.getImei()))
                || (ProtocolUtil.isNullOrEmpty(sr.getCoo_id()))) {

            return null;
        }

        String projectId = sr.getCoo_id();

        //黑名单
        if (CacheInfoUtil.isBlack(sr.getImei())) {
            return null;
        }
        logger.info("valid black time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();

        try {
            logger.info("check project Setting time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            // 获取国家id和国家等级
            int countryId = -1;
            int countryLevel = 4;
            Country c = CacheInfoUtil.getCountry(areaService, sr.getIpaddr());
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
            logger.info(String.format("current coutry:%s of %s", countryId, sr.getIpaddr()));
            logger.info("get country level time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();

            sr.setCou(countryId);
            sr.setCountryLevel(countryLevel);

            SubscribeVO a = CacheInfoUtil.getSubAdList(sdkService, sr);
            if (a != null) {
                url = a.getRedirectUrl();
            } else {
                logger.info(String.format("has not subscribe in :0 of %s", countryId, sr.getImei()));
            }
            logger.info("get push ads time:" + (System.currentTimeMillis() - start) + "ms");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return url;

    }

    @Override
    public void processActive(ActiveInfo activeInfo) {
        String pkey = String.format(Constants.KEY_ACTIVE, "sub_active", activeInfo.getClickId());
        String strPkg = RedisTool.get(pkey);
        if(ProtocolUtil.isNullOrEmpty(strPkg)) {
            RedisTool.set(pkey, activeInfo.getClickId());
            JSONObject jsObj = new JSONObject();
            Date cdate = new Date();
            jsObj.put("clickid", activeInfo.getClickId());
            String key = "clickId_" + activeInfo.getClickId();
            String value = RedisTool.lindex(key);
            jsObj.put("key", value);
            if(activeInfo.getPayout() == null || activeInfo.getPayout() == ""){
                if (value != null && value != "") {
                    String[] datas = value.split("\\|");
                    Integer adId = Integer.valueOf(datas[0]);
                    String pay = CacheInfoUtil.reloadSub(sdkService, adId);
                    activeInfo.setPayout(pay);
                }
            }
            jsObj.put("payout", activeInfo.getPayout());
            jsObj.put("cdate", CalendarFormat.getDateString(cdate.getTime()));
            RedisTool.lpush(Constants.KEY_AD_ACTIVE_TOSAVE, jsObj.toString());
        }
    }

    @Override
    public String processSubLink(SubLinkVO sl) {
        String url = null;
        long start = System.currentTimeMillis();

        String projectId = sl.getCooid();

        start = System.currentTimeMillis();

        try {
            logger.info("check project Setting time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            // 获取国家id和国家等级
            int countryId = -1;
            int countryLevel = 4;
            Country c = CacheInfoUtil.getCountry(areaService, sl.getIpaddr());
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
            logger.info(String.format("current coutry:%s of %s", countryId, sl.getIpaddr()));
            logger.info("get country level time:" + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();

            sl.setCou(countryId);

            SubscribeVO a = CacheInfoUtil.getSubLinkList(sdkService, sl);
            if (a != null) {
                url = a.getRedirectUrl();
//                webAdToRedis(a, sr);
            } else {
                logger.info(String.format("has not subscribe in :0 of %s", countryId, sl.getCooid()));
            }
            logger.info("get push ads time:" + (System.currentTimeMillis() - start) + "ms");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return url;

    }

    public SdkService getSdkService() {
        return sdkService;
    }

    public void setSdkService(SdkService sdkService) {
        this.sdkService = sdkService;
    }

    public AreaService getAreaService() {
        return areaService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }
}
