package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

public class ApkInfoVO extends BasicVO {

	private int id;
	
	private int version;
	
	private int apkId;
	
	private double versions;
	
	private String pkgName;
	
	private int state;
	
	private String cdate;
	
	private int isOutDownload;
	
	private String title;
	
	private String wwwurl;
	
	private int apkType;
	
	private String startArgu;
	
	private String startClass;
	
	private int rank;
	
	private int creator;
	
	private String note;

	private String extensionContry;

	public String getExtensionContry() {
		return extensionContry;
	}

	public void setExtensionContry(String extensionContry) {
		this.extensionContry = extensionContry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getApkId() {
		return apkId;
	}

	public void setApkId(int apkId) {
		this.apkId = apkId;
	}

	public double getVersions() {
		return versions;
	}

	public void setVersions(double versions) {
		this.versions = versions;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getIsOutDownload() {
		return isOutDownload;
	}

	public void setIsOutDownload(int isOutDownload) {
		this.isOutDownload = isOutDownload;
	}

	public String getWwwurl() {
		return wwwurl;
	}

	public void setWwwurl(String wwwurl) {
		this.wwwurl = wwwurl;
	}

	public int getApkType() {
		return apkType;
	}

	public void setApkType(int apkType) {
		this.apkType = apkType;
	}

	public String getStartArgu() {
		return startArgu;
	}

	public void setStartArgu(String startArgu) {
		this.startArgu = startArgu;
	}

	public String getStartClass() {
		return startClass;
	}

	public void setStartClass(String startClass) {
		this.startClass = startClass;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
