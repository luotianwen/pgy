package com.kokmobi.server.bean;

import java.util.List;

public class AdLogPackage {
	private String pkgid;
	private List<AdLogInfo> infors;
	
	public List<AdLogInfo> getInfors() {
		return infors;
	}
	public void setInfors(List<AdLogInfo> infors) {
		this.infors = infors;
	}
	public String getPkgid() {
		return pkgid;
	}
	public void setPkgid(String pkgid) {
		this.pkgid = pkgid;
	}
	
}
