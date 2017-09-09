package com.kokmobi.server.bean;

import java.util.Date;

/**
 * 下发的广告明细数据，用来保存到数据库
 * @author min
 *
 */
public class ApkAdRespInfo {

	private String coo_id;	//varchar(100) NULL产品id
	private String imei;	//varchar(100) NULL
	private int sdk;		//int(11) NULL推送/插屏
	private String channelid;	//varchar(100) NULL
	private int apkid;			//int(11) NULL
	private String cdate;			//datetime NULL
	private int cou;			//int(11) NULL国家
	private String sdkversion;	//varchar(3) NULL
	private String pkgid;		//varchar(50) NULL包id
	private String xc_coo_id;	//varchar(11) NULL插件id
	private int countryLevel;	//int(11) NOT NULL
	private int sdkstyle;		//int(11) NULLsdk类型对应600400
	private int pkgstatus;		//int(4) NULL包状态（3200表示已接收）
	private String xoperator;
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
	public String getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}
	public String getPkgid() {
		return pkgid;
	}
	public void setPkgid(String pkgid) {
		this.pkgid = pkgid;
	}
	public String getXc_coo_id() {
		return xc_coo_id;
	}
	public void setXc_coo_id(String xc_coo_id) {
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
	public int getPkgstatus() {
		return pkgstatus;
	}
	public void setPkgstatus(int pkgstatus) {
		this.pkgstatus = pkgstatus;
	}

	public String getXoperator() {
		return xoperator;
	}

	public void setXoperator(String xoperator) {
		this.xoperator = xoperator;
	}
}
