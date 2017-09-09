package com.kkgame.user.bean;

import java.io.Serializable;

public class SubClickVO
  implements Serializable
{
  private static final long serialVersionUID = 283329679303753091L;
  private int id;
  private int coo_id;
  private String imei;
  private int adId;
  private String cdate;
  private String table;
  private String cou;
  private int internet;
  private String xmodel;
  private String xoperator;
  private String xversion;
  private long clickId;
  private int platId;
  private int adType;


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

  public long getClickId() {
    return clickId;
  }

  public void setClickId(long clickId) {
    this.clickId = clickId;
  }

  public int getPlatId() {
    return platId;
  }

  public void setPlatId(int platId) {
    this.platId = platId;
  }

  public int getAdType() {
    return adType;
  }

  public void setAdType(int adType) {
    this.adType = adType;
  }
}