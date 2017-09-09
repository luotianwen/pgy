package com.kkgame.pay.stat.bean;

import java.io.Serializable;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/7
 *          Time: 18:07
 * @author: mm
 * @since 3.0
 */
public class SubLinkDataVO implements Serializable{
    private int cou;
    private String xmodel;
    private int adId;
    private int cooid;
    private String xoperator;
    private int internet;
    private String xversion;
    private String cdate;
    private String clickId;
    private String agent;
    private String clickReferer;
    private String brand;
    private String browser;
    private int height;
    private int width;
    private String device;
    private String os;
    private String adType;

    public int getCou() {
        return cou;
    }

    public void setCou(int cou) {
        this.cou = cou;
    }

    public String getXmodel() {
        return xmodel;
    }

    public void setXmodel(String xmodel) {
        this.xmodel = xmodel;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getCooid() {
        return cooid;
    }

    public void setCooid(int cooid) {
        this.cooid = cooid;
    }

    public String getXoperator() {
        return xoperator;
    }

    public void setXoperator(String xoperator) {
        this.xoperator = xoperator;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public String getXversion() {
        return xversion;
    }

    public void setXversion(String xversion) {
        this.xversion = xversion;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getClickReferer() {
        return clickReferer;
    }

    public void setClickReferer(String clickReferer) {
        this.clickReferer = clickReferer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }
}
