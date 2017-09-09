package com.kokmobi.server.bean;

import java.io.Serializable;

public class DistReqVO implements Serializable {

	private String imei;
	
	private int cooId;
	
	private int xcooId;
	
	private int sdkVersion;
	
	private String ipaddr;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}


	public int getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(int sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public int getCooId() {
		return cooId;
	}

	public void setCooId(int cooId) {
		this.cooId = cooId;
	}

	public int getXcooId() {
		return xcooId;
	}

	public void setXcooId(int xcooId) {
		this.xcooId = xcooId;
	}

}
