package com.kokmobi.server.bean;

import java.io.Serializable;

public class GetAdListFeebackReq implements Serializable {

	private String imei;
	private String pkgId;
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getPkgId() {
		return pkgId;
	}
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
}
