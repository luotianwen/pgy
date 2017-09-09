package com.kkgame.pay.stat.bean;

import java.io.Serializable;

public class AdSubDataVO implements Serializable {

    private static final long serialVersionUID = 283329679303753090L;

    private int cooid;
    private String imei;
    private int adId;
    private String cdate;
    private String table;
    private String cou;
    private int internet;
    private String xmodel;
    private String xoperator;
    private String xversion;
    private String clickId;
    private int platformId;
    private int adType;
    public int getCooid() {
        return cooid;
    }

    public void setCooid(int cooid) {
        this.cooid = cooid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public String getXmodel() {
        return xmodel;
    }

    public void setXmodel(String xmodel) {
        this.xmodel = xmodel;
    }

    public String getXoperator() {
        return xoperator;
    }

    public void setXoperator(String xoperator) {
        this.xoperator = xoperator;
    }

    public String getXversion() {
        return xversion;
    }

    public void setXversion(String xversion) {
        this.xversion = xversion;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }
}
 
