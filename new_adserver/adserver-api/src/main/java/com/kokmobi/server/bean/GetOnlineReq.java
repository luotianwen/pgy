package com.kokmobi.server.bean;

import java.io.Serializable;

public class GetOnlineReq implements Serializable {

	private String imei;
	private String adId;
	private String type;
	private String pkgid;
	private String ipaddr;
	private String cooId;
	private String backType;//日志回传类型：点击回传，展示回传
	private int sdkVersion;
	private String xoperator;

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getPkgid() {
		return pkgid;
	}

	public void setPkgid(String pkgid) {
		this.pkgid = pkgid;
	}

	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCooId() {
		return cooId;
	}

	public void setCooId(String cooId) {
		this.cooId = cooId;
	}

	public String getBackType() {
		return backType;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}

	public int getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(int sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getXoperator() {
		return xoperator;
	}

	public void setXoperator(String xoperator) {
		this.xoperator = xoperator;
	}
}
