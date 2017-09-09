package com.kokmobi.server.bean;

import java.io.Serializable;
import java.util.Date;

public class GetAdListReq implements Serializable {
	private String coo_id;
	private String imei;
	private String sdk;
	private String channelId;
	private String internet;
	private String language;
	private String xc_coo_id;
	private String xmodel;
	private String xversion;
	private String ximsi;
	private String xinternet;
	private String xoperator;
	private String xwidth;
	private String xheight;
	private int sdkversion;
	private String apkid;
	private String brand;
	private String ipaddr;
	private Date cdate;
	private int cou;
	private int countryLevel;
	private int sdkStyle;

	private String os;
	private String device;
	private String displaySize;
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public int getCou() {
		return cou;
	}
	public void setCou(int cou) {
		this.cou = cou;
	}
	public int getCountryLevel() {
		return countryLevel;
	}
	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}
	public String getCoo_id() {
		return coo_id;
	}
	public void setCoo_id(String coo_id) {
		this.coo_id = coo_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSdk() {
		return sdk;
	}
	public void setSdk(String sdk) {
		this.sdk = sdk;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getXc_coo_id() {
		return xc_coo_id;
	}
	public void setXc_coo_id(String xc_coo_id) {
		this.xc_coo_id = xc_coo_id;
	}
	public String getXmodel() {
		return xmodel;
	}
	public void setXmodel(String xmodel) {
		this.xmodel = xmodel;
	}
	public String getXversion() {
		return xversion;
	}
	public void setXversion(String xversion) {
		this.xversion = xversion;
	}
	public String getXimsi() {
		return ximsi;
	}
	public void setXimsi(String ximsi) {
		this.ximsi = ximsi;
	}
	/**
	 * @deprecated 
	 */
	public String getXinternet() {
		return xinternet;
	}
	/**
	 * @deprecated 
	 */
	public void setXinternet(String xinternet) {
		this.xinternet = xinternet;
	}
	public String getXoperator() {
		return xoperator;
	}
	public void setXoperator(String xoperator) {
		this.xoperator = xoperator;
	}
	public String getXwidth() {
		return xwidth;
	}
	public void setXwidth(String xwidth) {
		this.xwidth = xwidth;
	}
	public String getXheight() {
		return xheight;
	}
	public void setXheight(String xheight) {
		this.xheight = xheight;
	}
	public int getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(int sdkversion) {
		this.sdkversion = sdkversion;
	}
	public String getApkid() {
		return apkid;
	}
	public void setApkid(String apkid) {
		this.apkid = apkid;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public int getSdkStyle() {
		return sdkStyle;
	}
	public void setSdkStyle(int sdkStyle) {
		this.sdkStyle = sdkStyle;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}
}
