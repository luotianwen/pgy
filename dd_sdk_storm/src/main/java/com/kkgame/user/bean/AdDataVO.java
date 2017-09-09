package com.kkgame.user.bean;

import java.io.Serializable;

public class AdDataVO
  implements Serializable
{
  private static final long serialVersionUID = -6181750918410936505L;
  private int id;
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
  private int isRepeat;
  private int pkgstatus;
  private int dataType;
  private int isVersionRepeat;
  private int isProjectRepeat;
  private String table;

  public int getDataType()
  {
    return this.dataType;
  }

  public void setDataType(int dataType) {
    this.dataType = dataType;
  }

  public int getPkgstatus() {
    return this.pkgstatus;
  }

  public void setPkgstatus(int pkgstatus) {
    this.pkgstatus = pkgstatus;
  }

  public String getTable()
  {
    return this.table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public int getIsRepeat() {
    return this.isRepeat;
  }

  public void setIsRepeat(int isRepeat) {
    this.isRepeat = isRepeat;
  }

  public int getIsVersionRepeat() {
    return this.isVersionRepeat;
  }

  public void setIsVersionRepeat(int isVersionRepeat) {
    this.isVersionRepeat = isVersionRepeat;
  }

  public int getIsProjectRepeat() {
    return this.isProjectRepeat;
  }

  public void setIsProjectRepeat(int isProjectRepeat) {
    this.isProjectRepeat = isProjectRepeat;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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
}