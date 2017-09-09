package com.kokmobi.server.bean;

public class AdLanguageInfo {

	private String appName;						//app name
	private String pushText; 					//推送提示文字
	private String introduction; 				//广告介绍
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPushText() {
		return pushText;
	}
	public void setPushText(String pushText) {
		this.pushText = pushText;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
