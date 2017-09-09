package com.kkgame.feeop.detail.bean;
import com.kkgame.feeop.base.BasicVO; 

public class AdvSentsListModelVO    {
	 

    /**
     * 包状态（3200表示已接收）       db_column: pkgstatus 
     */ 	
	private java.lang.String pkgstatus;
	
	private String sdkstyle;
	//columns END
	private String table;
	private int isPage;



	private int coo_id;
	private String imei;
	private int sdk;
	private String channelid;
	private int apkid;
	private String cdate;
	private int cou;
	private int sdkversion;
	private String pkgid;
	private int xc_coo_id;
	private int countryLevel;


	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
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

	public int getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(int sdkversion) {
		this.sdkversion = sdkversion;
	}

	public String getPkgid() {
		return pkgid;
	}

	public void setPkgid(String pkgid) {
		this.pkgid = pkgid;
	}

	public int getXc_coo_id() {
		return xc_coo_id;
	}

	public void setXc_coo_id(int xc_coo_id) {
		this.xc_coo_id = xc_coo_id;
	}

	public int getCountryLevel() {
		return countryLevel;
	}

	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}



	public java.lang.String getPkgstatus() {
		return pkgstatus;
	}

	public void setPkgstatus(java.lang.String pkgstatus) {
		this.pkgstatus = pkgstatus;
	}

	public String getSdkstyle() {
		return sdkstyle;
	}

	public void setSdkstyle(String sdkstyle) {
		this.sdkstyle = sdkstyle;
	}
}
 
