package com.kkgame.pay.stat.bean;

import java.io.Serializable;

public class AdDataVO
  implements Serializable
{
  private static final long serialVersionUID = -6181750918410936505L;

  private int coo_id;
  private String imei;
  private int sdk;
  private String channelid;
  private int apkid;
  private String cdate;
  private int cou;
  private int sdkversion;
  private String pkgid;
  private int xc_coo_id;
  private int countryLevel;
  private int sdkstyle;

  private int pkgstatus;
  private String backType;



  public int getPkgstatus() {
    return this.pkgstatus;
  }

  public void setPkgstatus(int pkgstatus) {
    this.pkgstatus = pkgstatus;
  }





  public int getCoo_id() {
    return this.coo_id;
  }

  public void setCoo_id(int coo_id) {
    this.coo_id = coo_id;
  }

  public String getImei() {
    return this.imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public int getSdk() {
    return this.sdk;
  }

  public void setSdk(int sdk) {
    this.sdk = sdk;
  }

  public String getChannelid() {
    return this.channelid;
  }

  public void setChannelid(String channelid) {
    this.channelid = channelid;
  }

  public int getApkid() {
    return this.apkid;
  }

  public void setApkid(int apkid) {
    this.apkid = apkid;
  }

  public String getCdate() {
    return this.cdate;
  }

  public void setCdate(String cdate) {
    this.cdate = cdate;
  }

  public int getCou() {
    return this.cou;
  }

  public void setCou(int cou) {
    this.cou = cou;
  }

  public int getSdkversion() {
    return this.sdkversion;
  }

  public void setSdkversion(int sdkversion) {
    this.sdkversion = sdkversion;
  }

  public String getPkgid() {
    return this.pkgid;
  }

  public void setPkgid(String pkgid) {
    this.pkgid = pkgid;
  }

  public int getXc_coo_id() {
    return this.xc_coo_id;
  }

  public void setXc_coo_id(int xc_coo_id) {
    this.xc_coo_id = xc_coo_id;
  }

  public int getCountryLevel() {
    return this.countryLevel;
  }

  public void setCountryLevel(int countryLevel) {
    this.countryLevel = countryLevel;
  }

  public int getSdkstyle() {
    return this.sdkstyle;
  }

  public void setSdkstyle(int sdkstyle) {
    this.sdkstyle = sdkstyle;
  }

  public String getBackType() {
    return backType;
  }

  public void setBackType(String backType) {
    this.backType = backType;
  }
}