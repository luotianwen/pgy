package com.kokmobi.server.bean;

import java.util.Date;

/**
 * 销量与活跃用户信息
 * @author min
 *
 */
public class AdUserInfo {

	/** 产品id */
	private String coo_id;
	private String imei;
	/** 渠道id */
	private String channelid;

	private int sdk;	//插件类型：SDK，引导，下沉 ,静默（2,3,4 ,5）
	private int sdkStyle;	//字典数据，对应上面的插件类型	--数据库无此字段
	/** 创建时间 */
	private String cdate;
	/** 插件ID */
	private String xc_coo_id;
	private String xmodel;
	/** 系统版本 */
	private String xversion;
	private String ximsi;
	private String xinternet;
	/** 运营商 */
	private String xoperator;
	private int xcou;
	private int countryLevel;
	/** 活跃时间 */
	private String sdate;
	private String sdkversion;
	private String ipaddr;

	/** 销量时间 */
	private String xdate;
	/** 销量coo_id */
	private String scoo_id;
	
	private String type;	//1销量2活跃
	private int dataType;	// 活跃数据标记：1表示总活跃，0表示项目活跃
	  
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
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public int getSdk() {
		return sdk;
	}
	public void setSdk(int sdk) {
		this.sdk = sdk;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
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
	public String getXinternet() {
		return xinternet;
	}
	public void setXinternet(String xinternet) {
		this.xinternet = xinternet;
	}
	public String getXoperator() {
		return xoperator;
	}
	public void setXoperator(String xoperator) {
		this.xoperator = xoperator;
	}
	public int getXcou() {
		return xcou;
	}
	public void setXcou(int xcou) {
		this.xcou = xcou;
	}
	public int getCountryLevel() {
		return countryLevel;
	}
	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getXdate() {
		return xdate;
	}
	public void setXdate(String xdate) {
		this.xdate = xdate;
	}
	public String getScoo_id() {
		return scoo_id;
	}
	public void setScoo_id(String scoo_id) {
		this.scoo_id = scoo_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSdkStyle() {
		return sdkStyle;
	}
	public void setSdkStyle(int sdkType) {
		this.sdkStyle = sdkType;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int isRepeat) {
		this.dataType = isRepeat;
	}
}
