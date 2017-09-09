package com.kokmobi.server.bean;

public class ApkInfoVO{
	
	private int apkId;
	
	private int version;
	
	private String apkUrl;
	
	private String pkgName;
	
	private int rank;
	
	private int apkType;
	
	private String startArgu;
	
	private String startClass;

	private String extensionContry;

	public int getApkId() {
		return apkId;
	}

	public void setApkId(int apkId) {
		this.apkId = apkId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public String getExtensionContry() {
		return extensionContry;
	}

	public void setExtensionContry(String extensionContry) {
		this.extensionContry = extensionContry;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
