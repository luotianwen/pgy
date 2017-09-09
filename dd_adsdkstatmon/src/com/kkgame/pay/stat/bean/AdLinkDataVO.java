package com.kkgame.pay.stat.bean;

import java.io.Serializable;

public class AdLinkDataVO
  implements Serializable
{
  private static final long serialVersionUID = 283329679303753090L;

  private int coo_id;
  private String imei;
  private int adId;
  private int linkType;
  private int clickType;
  private String cdate;
  private String table;
  private String cou;

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
}