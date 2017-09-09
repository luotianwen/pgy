package com.kokmobi.server.bean;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/21
 *          Time: 14:52
 * @author: mm
 * @since 3.0
 */
public class WebProjectReq implements Serializable {
    private String imei;
    private String cooId;
    private String sdkVersion;
    private int cou;
    private int countryLevel;
    private String internet;
    private String operator;
    private String type;
    private String imsi;
    private String operatorn;

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getOperatorn() {
        return operatorn;
    }

    public void setOperatorn(String operatorn) {
        this.operatorn = operatorn;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    private String ipaddr;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCooId() {
        return cooId;
    }

    public void setCooId(String cooId) {
        this.cooId = cooId;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public int getCou() {
        return cou;
    }

    public void setCou(int cou) {
        this.cou = cou;
    }

    public int getCountryLevel() {
        return countryLevel;
    }

    public void setCountryLevel(int countryLevel) {
        this.countryLevel = countryLevel;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }
}
