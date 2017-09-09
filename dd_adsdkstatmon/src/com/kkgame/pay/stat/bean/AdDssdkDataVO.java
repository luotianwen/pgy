package com.kkgame.pay.stat.bean;

import java.io.Serializable;

public class AdDssdkDataVO
  implements Serializable
{
  private static final long serialVersionUID = 283329679303753090L;


  private String imei;
  private int adId;
  private String pkgid;
  private int type;
  private String cdate;
  private String table;
  private String cou;
  private String cooId;
  private int backType;

  public String getCou() {
    return cou;
  }

  public void setCou(String cou) {
    this.cou = cou;
  }
  public String getTable()
  {
    return this.table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public String getImei() {
    return this.imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getCdate() {
    return this.cdate;
  }

  public void setCdate(String cdate) {
    this.cdate = cdate;
  }

  public int getAdId() {
    return this.adId;
  }

  public void setAdId(int adId) {
    this.adId = adId;
  }

  public String getPkgid() {
    return pkgid;
  }

  public void setPkgid(String pkgid) {
    this.pkgid = pkgid;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getCooId() {
    return cooId;
  }

  public void setCooId(String cooId) {
    this.cooId = cooId;
  }

  public int getBackType() {
    return backType;
  }

  public void setBackType(int backType) {
    this.backType = backType;
  }
}