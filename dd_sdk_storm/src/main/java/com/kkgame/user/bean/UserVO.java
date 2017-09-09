package com.kkgame.user.bean;

import java.io.Serializable;

public class UserVO
  implements Serializable
{
  private static final long serialVersionUID = 3698085572562895559L;
  private int id;
  private String imei;
  private String channelid;
  private int sdk;
  private String cdate;
  private int xc_coo_id;
  private int coo_id;
  private String xmodel;
  private String xversion;
  private String ximsi;
  private int xinternet;
  private String xoperator;
  private int xcou;
  private int countryLevel;
  private int creator;
  private int sdkversion;
  private String xdate;
  private int scoo_id;
  private String ipaddr;
  private int sdkStyle;
  private String table;
  private String sdate;
  private int dataType;

  public String getSdate()
  {
    return this.sdate;
  }

  public void setSdate(String sdate) {
    this.sdate = sdate;
  }

  public String getTable() {
    return this.table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public int getCoo_id()
  {
    return this.coo_id;
  }

  public void setCoo_id(int coo_id) {
    this.coo_id = coo_id;
  }

  public int getSdkStyle() {
    return this.sdkStyle;
  }

  public void setSdkStyle(int sdkStyle) {
    this.sdkStyle = sdkStyle;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImei() {
    return this.imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getChannelid() {
    return this.channelid;
  }

  public void setChannelid(String channelid) {
    this.channelid = channelid;
  }

  public int getSdk() {
    return this.sdk;
  }

  public void setSdk(int sdk) {
    this.sdk = sdk;
  }

  public String getCdate() {
    return this.cdate;
  }

  public void setCdate(String cdate) {
    this.cdate = cdate;
  }

  public int getXc_coo_id() {
    return this.xc_coo_id;
  }

  public void setXc_coo_id(int xc_coo_id) {
    this.xc_coo_id = xc_coo_id;
  }

  public String getXmodel() {
    return this.xmodel;
  }

  public void setXmodel(String xmodel) {
    this.xmodel = xmodel;
  }

  public String getXversion() {
    return this.xversion;
  }

  public void setXversion(String xversion) {
    this.xversion = xversion;
  }

  public String getXimsi() {
    return this.ximsi;
  }

  public void setXimsi(String ximsi) {
    this.ximsi = ximsi;
  }

  public int getXinternet() {
    return this.xinternet;
  }

  public void setXinternet(int xinternet) {
    this.xinternet = xinternet;
  }

  public String getXoperator() {
    return this.xoperator;
  }

  public void setXoperator(String xoperator) {
    this.xoperator = xoperator;
  }

  public int getXcou() {
    return this.xcou;
  }

  public void setXcou(int xcou) {
    this.xcou = xcou;
  }

  public int getCountryLevel() {
    return this.countryLevel;
  }

  public void setCountryLevel(int countryLevel) {
    this.countryLevel = countryLevel;
  }

  public int getCreator() {
    return this.creator;
  }

  public void setCreator(int creator) {
    this.creator = creator;
  }

  public int getSdkversion() {
    return this.sdkversion;
  }

  public void setSdkversion(int sdkversion) {
    this.sdkversion = sdkversion;
  }

  public String getXdate() {
    return this.xdate;
  }

  public void setXdate(String xdate) {
    this.xdate = xdate;
  }

  public int getScoo_id() {
    return this.scoo_id;
  }

  public void setScoo_id(int scoo_id) {
    this.scoo_id = scoo_id;
  }

  public String getIpaddr() {
    return this.ipaddr;
  }

  public void setIpaddr(String ipaddr) {
    this.ipaddr = ipaddr;
  }

  public int getDataType() {
    return this.dataType;
  }

  public void setDataType(int dataType) {
    this.dataType = dataType;
  }
}