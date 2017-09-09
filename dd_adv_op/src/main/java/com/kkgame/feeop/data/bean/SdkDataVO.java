package com.kkgame.feeop.data.bean;

public class SdkDataVO {

	private int cou;
	private String couName;
	private int AdId;
	private String StatDate;
	private int desktopClickTimes;
	private int suspensionClickTimes;
	private int pushClickTimes;
	private int appClickTimes;
	private int otherappClickTimes;
	private int TotalClickTimes;
	private String adName;

	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

	public int getAdId() {
		return AdId;
	}

	public void setAdId(int adId) {
		AdId = adId;
	}

	public String getStatDate() {
		return StatDate;
	}

	public void setStatDate(String statDate) {
		StatDate = statDate;
	}

	public int getDesktopClickTimes() {
		return desktopClickTimes;
	}

	public void setDesktopClickTimes(int desktopClickTimes) {
		this.desktopClickTimes = desktopClickTimes;
	}

	public int getSuspensionClickTimes() {
		return suspensionClickTimes;
	}

	public void setSuspensionClickTimes(int suspensionClickTimes) {
		this.suspensionClickTimes = suspensionClickTimes;
	}

	public int getPushClickTimes() {
		return pushClickTimes;
	}

	public void setPushClickTimes(int pushClickTimes) {
		this.pushClickTimes = pushClickTimes;
	}

	public int getAppClickTimes() {
		return appClickTimes;
	}

	public void setAppClickTimes(int appClickTimes) {
		this.appClickTimes = appClickTimes;
	}

	public int getOtherappClickTimes() {
		return otherappClickTimes;
	}

	public void setOtherappClickTimes(int otherappClickTimes) {
		this.otherappClickTimes = otherappClickTimes;
	}

	public int getTotalClickTimes() {
		return TotalClickTimes;
	}

	public void setTotalClickTimes(int totalClickTimes) {
		TotalClickTimes = totalClickTimes;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}
}
