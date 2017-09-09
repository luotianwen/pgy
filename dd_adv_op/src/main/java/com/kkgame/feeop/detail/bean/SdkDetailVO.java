package com.kkgame.feeop.detail.bean;

import com.kkgame.feeop.base.BaseAction;

import java.util.Date;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/26
 *          Time: 15:24
 * @author: mm
 * @since 3.0
 */
public class SdkDetailVO {

    private int isPage;

    private  int adId;
    private String cou;
    private String imei;
    private int type;
    private String pkgid;
    private String cdate;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPkgid() {
        return pkgid;
    }

    public void setPkgid(String pkgid) {
        this.pkgid = pkgid;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getIsPage() {
        return isPage;
    }

    public void setIsPage(int isPage) {
        this.isPage = isPage;
    }
}

