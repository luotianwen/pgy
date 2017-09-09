package com.kokmobi.server.bean;

import java.io.Serializable;

public class AdPluginInfo implements Serializable {
	private int versions;
	private int apkid;
	private String packagename;
	private int isoutdownload;
	private String attachmentPath;
	private String wwwurl;
	public int getVersions() {
		return versions;
	}
	public void setVersions(int versions) {
		this.versions = versions;
	}
	public int getApkid() {
		return apkid;
	}
	public void setApkid(int apkid) {
		this.apkid = apkid;
	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public int getIsoutdownload() {
		return isoutdownload;
	}
	public void setIsoutdownload(int isoutdownload) {
		this.isoutdownload = isoutdownload;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getWwwurl() {
		return wwwurl;
	}
	public void setWwwurl(String wwwurl) {
		this.wwwurl = wwwurl;
	}
}
