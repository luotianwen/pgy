package com.kokmobi.server.test;

import java.io.Serializable;

public class AdDataVO implements Serializable {

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
	
	public int getPkgstatus() {
		return pkgstatus;
	}

	public void setPkgstatus(int pkgstatus) {
		this.pkgstatus = pkgstatus;
	}

	private int isVersionRepeat;
	
	private int isProjectRepeat;
	
	private String table;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(int isRepeat) {
		this.isRepeat = isRepeat;
	}

	public int getIsVersionRepeat() {
		return isVersionRepeat;
	}

	public void setIsVersionRepeat(int isVersionRepeat) {
		this.isVersionRepeat = isVersionRepeat;
	}

	public int getIsProjectRepeat() {
		return isProjectRepeat;
	}

	public void setIsProjectRepeat(int isProjectRepeat) {
		this.isProjectRepeat = isProjectRepeat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
		this.coo_id = coo_id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getSdk() {
		return sdk;
	}

	public void setSdk(int sdk) {
		this.sdk = sdk;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public int getApkid() {
		return apkid;
	}

	public void setApkid(int apkid) {
		this.apkid = apkid;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

	public int getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(int sdkversion) {
		this.sdkversion = sdkversion;
	}

	public String getPkgid() {
		return pkgid;
	}

	public void setPkgid(String pkgid) {
		this.pkgid = pkgid;
	}

	public int getXc_coo_id() {
		return xc_coo_id;
	}

	public void setXc_coo_id(int xc_coo_id) {
		this.xc_coo_id = xc_coo_id;
	}

	public int getCountryLevel() {
		return countryLevel;
	}

	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}

	public int getSdkstyle() {
		return sdkstyle;
	}

	public void setSdkstyle(int sdkstyle) {
		this.sdkstyle = sdkstyle;
	}
}
