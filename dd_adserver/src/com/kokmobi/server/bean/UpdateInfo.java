package com.kokmobi.server.bean;

public class UpdateInfo {

	private int pkgType;
	
	private int hasNew;
	
	private String url;
	
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getPkgType() {
		return pkgType;
	}

	public void setPkgType(int pkgType) {
		this.pkgType = pkgType;
	}

	public int getHasNew() {
		return hasNew;
	}

	public void setHasNew(int hasNew) {
		this.hasNew = hasNew;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
