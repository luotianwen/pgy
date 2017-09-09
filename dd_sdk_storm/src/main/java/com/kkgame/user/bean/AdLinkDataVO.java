package com.kkgame.user.bean;

import java.io.Serializable;

public class AdLinkDataVO
  implements Serializable
{
  private static final long serialVersionUID = 283329679303753090L;
  private int id;
  private int coo_id;
  private String imei;
  private int adId;
  private int linkType;
  private int clickType;
  private String cdate;
  private String table;
  private String cou;
  private String adType;
  private String device;
  private String platId;
  private String os;

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

  public int getLinkType() {
    return this.linkType;
  }

  public void setLinkType(int linkType) {
    this.linkType = linkType;
  }

  public int getClickType() {
    return this.clickType;
  }

  public void setClickType(int clickType) {
    this.clickType = clickType;
  }

  public String getAdType() {
    return adType;
  }

  public void setAdType(String adType) {
    this.adType = adType;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getPlatId() {
    return platId;
  }

  public void setPlatId(String platId) {
    this.platId = platId;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }
}
