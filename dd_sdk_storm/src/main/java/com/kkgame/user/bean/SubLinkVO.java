package com.kkgame.user.bean;

import java.io.Serializable;

public class SubLinkVO
  implements Serializable
{
  private static final long serialVersionUID = 283329679303753091L;
  private int id;
  private int cooid;
  private int adId;
  private String cdate;
  private String table;
  private String cou;
  private String internet;
  private int xheight;
  private int xwidth;
  private String clickIp;
  private String xbrowser;
  private String xmodel; //机型
  private String xoperator;
  private String xversion;  //系统版本
  private String xbrand;     //品牌
  private String xname;  //型号
  private String userAgent;
  private String referer;
  private long clickId;
  private String device;
  private String os;
  private int platId;
  private int adType;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCooid() {
    return cooid;
  }

  public void setCooid(int cooid) {
    this.cooid = cooid;
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

  public String getInternet() {
    return internet;
  }

  public void setInternet(String internet) {
    this.internet = internet;
  }

  public int getXheight() {
    return xheight;
  }

  public void setXheight(int xheight) {
    this.xheight = xheight;
  }

  public int getXwidth() {
    return xwidth;
  }

  public void setXwidth(int xwidth) {
    this.xwidth = xwidth;
  }

  public String getClickIp() {
    return clickIp;
  }

  public void setClickIp(String clickIp) {
    this.clickIp = clickIp;
  }

  public String getXbrowser() {
    return xbrowser;
  }

  public void setXbrowser(String xbrowser) {
    this.xbrowser = xbrowser;
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

  public String getXbrand() {
    return xbrand;
  }

  public void setXbrand(String xbrand) {
    this.xbrand = xbrand;
  }

  public String getXname() {
    return xname;
  }

  public void setXname(String xname) {
    this.xname = xname;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public long getClickId() {
    return clickId;
  }

  public void setClickId(long clickId) {
    this.clickId = clickId;
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

  public int getAdType() {
    return adType;
  }

  public void setAdType(int adType) {
    this.adType = adType;
  }

  public int getPlatId() {
    return platId;
  }

  public void setPlatId(int platId) {
    this.platId = platId;
  }
}