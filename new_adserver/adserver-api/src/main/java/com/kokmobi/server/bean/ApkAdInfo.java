package com.kokmobi.server.bean;

import java.io.Serializable;

public class ApkAdInfo implements Cloneable, Serializable {
	private int apkId;							//apkid
	private String appName;						//app name
	private String pushText; 					//推送提示文字
	private String sizes;
	private String introduction; 				//广告介绍
	private String apkPackageName;				//包名
	private int pushStatus;						//是否可推送
	private int isNotification;					//通知栏是否可清除
	private int isInterface;					//提示界面是否可清除
	private int isGprsDownLoad;					//GPRS是否预下载
	private String language;					//推广国家id，用逗号隔开
	private int isOutDownload;					//是否外部下载
	private String outwww;						//外部下载地址
	private int popStatus;						//是否支持插屏
	private int maxPushTimes;					//推送上限
	private int maxPopTimes;					//插屏上限
	private double price;						//
	private int adtype;							//广告类型(ddl,apk,gp,...)
	private String tracinglink;					//tracinglink
	private double orders;						//cpm
	private int atype;							//应用类型
	private int tracinglinkc;					//tracing链接点击次数
	private int silentStatus;					//是否支持静默
	private int isouticon;						//是否外部icon
	private String outiconwww;					//外部icon地址
	private int isoutcptp;						//是否外部插屏图片
	private String outcptpwww;					//外部插屏图片路径
	private int clsj;							//存留时间
	private int isdel;							//静默安装广告，是否删除
	private String icon;						//icon图标
	private String pushImage1;					//
	private String pushImage2;					//
	private String apkDownloadUrl;				//apk下载地址
	private String popImage1;					//pop image1 not used
	private String popImage2;					//插屏图片
	private String popImage3;					//pop image3 not used
	private int dataOrSys; 						//指定安装在data区还是系统区，data区是0，系统区是1。默认0（V1.0.7新增）
	private int retentionRate;                 	//次日存留率，40表示40%的存留率，默认值为0（V1.0.7新增）
	private int actionStatus;                   //激活条件：0表示暗屏激活，1表示亮屏激活，2表示两种情况都激活，3表示都不激活。默认值为0。（V1.0.7新增）
    private String passnote;
    
	public String getPassnote() {
		return passnote;
	}

	public void setPassnote(String passnote) {
		this.passnote = passnote;
	}

	public int getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(int actionStatus) {
		this.actionStatus = actionStatus;
	}

	public int getDataOrSys() {
		return dataOrSys;
	}

	public void setDataOrSys(int dataOrSys) {
		this.dataOrSys = dataOrSys;
	}

	public int getRetentionRate() {
		return retentionRate;
	}

	public void setRetentionRate(int retentionRate) {
		this.retentionRate = retentionRate;
	}

	public int getApkId() {
		return apkId;
	}
	public void setApkId(int apkId) {
		this.apkId = apkId;
	}
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
	public String getSizes() {
		return sizes;
	}
	public void setSizes(String sizes) {
		this.sizes = sizes;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getApkPackageName() {
		return apkPackageName;
	}
	public void setApkPackageName(String apkPackageName) {
		this.apkPackageName = apkPackageName;
	}
	public int getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}
	public int getIsNotification() {
		return isNotification;
	}
	public void setIsNotification(int isNotification) {
		this.isNotification = isNotification;
	}
	public int getIsInterface() {
		return isInterface;
	}
	public void setIsInterface(int isInterface) {
		this.isInterface = isInterface;
	}
	public int getIsGprsDownLoad() {
		return isGprsDownLoad;
	}
	public void setIsGprsDownLoad(int isGprsDownLoad) {
		this.isGprsDownLoad = isGprsDownLoad;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getIsOutDownload() {
		return isOutDownload;
	}
	public void setIsOutDownload(int isOutDownload) {
		this.isOutDownload = isOutDownload;
	}
	public String getOutwww() {
		return outwww;
	}
	public void setOutwww(String outwww) {
		this.outwww = outwww;
	}
	public int getPopStatus() {
		return popStatus;
	}
	public void setPopStatus(int popStatus) {
		this.popStatus = popStatus;
	}
	public int getMaxPushTimes() {
		return maxPushTimes;
	}
	public void setMaxPushTimes(int maxPushTimes) {
		this.maxPushTimes = maxPushTimes;
	}
	public int getMaxPopTimes() {
		return maxPopTimes;
	}
	public void setMaxPopTimes(int maxPopTimes) {
		this.maxPopTimes = maxPopTimes;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public String getTracinglink() {
		return tracinglink;
	}
	public void setTracinglink(String tracinglink) {
		this.tracinglink = tracinglink;
	}
	public double getOrders() {
		return orders;
	}
	public void setOrders(double orders) {
		this.orders = orders;
	}
	public int getAtype() {
		return atype;
	}
	public void setAtype(int atype) {
		this.atype = atype;
	}
	public int getTracinglinkc() {
		return tracinglinkc;
	}
	public void setTracinglinkc(int tracinglinkc) {
		this.tracinglinkc = tracinglinkc;
	}
	public int getSilentStatus() {
		return silentStatus;
	}
	public void setSilentStatus(int silentStatus) {
		this.silentStatus = silentStatus;
	}
	public int getIsouticon() {
		return isouticon;
	}
	public void setIsouticon(int isouticon) {
		this.isouticon = isouticon;
	}
	public String getOuticonwww() {
		return outiconwww;
	}
	public void setOuticonwww(String outiconwww) {
		this.outiconwww = outiconwww;
	}
	public int getIsoutcptp() {
		return isoutcptp;
	}
	public void setIsoutcptp(int isoutcptp) {
		this.isoutcptp = isoutcptp;
	}
	public String getOutcptpwww() {
		return outcptpwww;
	}
	public void setOutcptpwww(String outcptpwww) {
		this.outcptpwww = outcptpwww;
	}
	public int getClsj() {
		return clsj;
	}
	public void setClsj(int clsj) {
		this.clsj = clsj;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPushImage1() {
		return pushImage1;
	}
	public void setPushImage1(String pushImage1) {
		this.pushImage1 = pushImage1;
	}
	public String getPushImage2() {
		return pushImage2;
	}
	public void setPushImage2(String pushImage2) {
		this.pushImage2 = pushImage2;
	}
	public String getApkDownloadUrl() {
		return apkDownloadUrl;
	}
	public void setApkDownloadUrl(String apkWownloadUrl) {
		this.apkDownloadUrl = apkWownloadUrl;
	}
	public String getPopImage1() {
		return popImage1;
	}
	public void setPopImage1(String popImage1) {
		this.popImage1 = popImage1;
	}
	public String getPopImage2() {
		return popImage2;
	}
	public void setPopImage2(String popImage2) {
		this.popImage2 = popImage2;
	}
	public String getPopImage3() {
		return popImage3;
	}
	public void setPopImage3(String popImage3) {
		this.popImage3 = popImage3;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
}
