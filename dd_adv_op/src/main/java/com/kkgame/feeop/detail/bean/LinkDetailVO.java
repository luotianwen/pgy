package com.kkgame.feeop.detail.bean;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/31
 *          Time: 17:31
 * @author: mm
 * @since 3.0
 */
public class LinkDetailVO {
    private String cou;
    private String agent;
    private String clickReferer;
    private String browser;
    private String height;
    private String width;
    private String cooid;
    private String cdate;
    private String clickId;
    private int xoperator;
    private int adId;
    private int internet;
    private int isPage;

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }


    public String getCooid() {
        return cooid;
    }

    public void setCooid(String cooid) {
        this.cooid = cooid;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
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

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int getXoperator() {
        return xoperator;
    }

    public void setXoperator(int xoperator) {
        this.xoperator = xoperator;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public int getIsPage() {
        return isPage;
    }

    public void setIsPage(int isPage) {
        this.isPage = isPage;
    }
}
