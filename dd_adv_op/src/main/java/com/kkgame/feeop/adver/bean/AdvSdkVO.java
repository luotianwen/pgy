package com.kkgame.feeop.adver.bean;

import com.kkgame.feeop.base.BasicVO;

import java.util.List;

public class AdvSdkVO extends BasicVO {
	
	private int id;//广告ID

	private int cpid;//别人的广告id
	
	private int cpname;//2avazu
	
	private int version;
	
	private String appName;//APK名称

	private String apkPackageName;//包名

	private String introduction;//说明
	
	private String pushText;//推送广告语
	
	private int adtype;//广告类型
	
	private int atype;//应用类型
	
	private String sizes;//大小
	
	private double price;//单价

	private String language;//国家识别码
	
	private String countryName;
	
	private int isOutDownload;//是否外部下载
	
	private String outwww;//外部下载地址
	
	private String tracinglink;

	private int tracinglinkc;//点击次数
	
	private int isouticon;

	private String outiconwww;
	
	private int isoutcptp;//是否外部插屏
	
	private String outcptpwww; //插屏图片地址
	
	private int isSlient;//是否线下

	private int tsUp;//1000000
	
	private int cpUp;//1000000
	
	private String sdate;
	
	private String cdate;

	private String durl;//详情URL

	private int pushStatus;//是否推送
	
	private int status;//审核状态
	
	private int isNotification;//通知栏是否可清除
	
	private int isInterface;//提示界面是否可清除
	
	private int isGprsDownLoad;//GPRS是否预下载

	private int isTablePlaque;//是否插屏apk

	private int isCpoy;//是否抄数据

	private double orders;//cpm
	
	private double silenceCpm;//线下cpm
	
	private int iconURL_attachment;//icon图标
	
	private int imageURL_attachment;//详细图片
	
	private int apkDownloadURL_attachment;//apk下载地址
	
	private int swmc;//商务姓名
	
	private int creator;
	
	private int deleted;//上下线
	
	private int dalyTime;//延迟时间
	
	private int userType;//用户类型
	
	private int apkStatus;//apk状态
	
	private double jhl;//激活率
	
	private int isDel;//是否删除
	
	private int clsj;//存留时间 默认值1
		
	private int fileType;
	
	private String url;
	
	private int dataOrSys;
	
	private int retentionRate;
	
	private int actionStatus;
	
	private double cpConversionRate;
	
	private int installedCount;
	
	private int silenceInstalledCount;
	private String passnote;     //md5
	private int cap;     //线下上限

	// 批量待需改的数据id
	private List<Integer> idList;
	// 批量操作类型 【1-预下载，2-推送，3-插屏，4-线下安装，5-弹出提示界面，6-插件下沉，7-插件升级，8-所有】
	private int type;
	// 是否开启操作 【0-开启，1-关闭】
	private int isOpen;
	// 接入人员
	private int advLinkmanId;

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getPassnote() {
		return passnote;
	}

	public void setPassnote(String passnote) {
		this.passnote = passnote;
	}

	public int getInstalledCount() {
		return installedCount;
	}

	public void setInstalledCount(int installedCount) {
		this.installedCount = installedCount;
	}

	public int getSilenceInstalledCount() {
		return silenceInstalledCount;
	}

	public void setSilenceInstalledCount(int silenceInstalledCount) {
		this.silenceInstalledCount = silenceInstalledCount;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpid() {
		return cpid;
	}

	public void setCpid(int cpid) {
		this.cpid = cpid;
	}

	public int getCpname() {
		return cpname;
	}

	public void setCpname(int cpname) {
		this.cpname = cpname;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getApkPackageName() {
		return apkPackageName;
	}

	public void setApkPackageName(String apkPackageName) {
		this.apkPackageName = apkPackageName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPushText() {
		return pushText;
	}

	public void setPushText(String pushText) {
		this.pushText = pushText;
	}

	public int getAdtype() {
		return adtype;
	}

	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}

	public int getAtype() {
		return atype;
	}

	public void setAtype(int atype) {
		this.atype = atype;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getTracinglink() {
		return tracinglink;
	}

	public void setTracinglink(String tracinglink) {
		this.tracinglink = tracinglink;
	}

	public int getTracinglinkc() {
		return tracinglinkc;
	}

	public void setTracinglinkc(int tracinglinkc) {
		this.tracinglinkc = tracinglinkc;
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

	public int getIsSlient() {
		return isSlient;
	}

	public void setIsSlient(int isSlient) {
		this.isSlient = isSlient;
	}

	public int getTsUp() {
		return tsUp;
	}

	public void setTsUp(int tsUp) {
		this.tsUp = tsUp;
	}

	public int getCpUp() {
		return cpUp;
	}

	public void setCpUp(int cpUp) {
		this.cpUp = cpUp;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getDurl() {
		return durl;
	}

	public void setDurl(String durl) {
		this.durl = durl;
	}

	public int getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getIsTablePlaque() {
		return isTablePlaque;
	}

	public void setIsTablePlaque(int isTablePlaque) {
		this.isTablePlaque = isTablePlaque;
	}

	public int getIsCpoy() {
		return isCpoy;
	}

	public void setIsCpoy(int isCpoy) {
		this.isCpoy = isCpoy;
	}

	public double getOrders() {
		return orders;
	}

	public void setOrders(double orders) {
		this.orders = orders;
	}

	public int getIconURL_attachment() {
		return iconURL_attachment;
	}

	public void setIconURL_attachment(int iconURL_attachment) {
		this.iconURL_attachment = iconURL_attachment;
	}

	public int getImageURL_attachment() {
		return imageURL_attachment;
	}

	public void setImageURL_attachment(int imageURL_attachment) {
		this.imageURL_attachment = imageURL_attachment;
	}

	public int getApkDownloadURL_attachment() {
		return apkDownloadURL_attachment;
	}

	public void setApkDownloadURL_attachment(int apkDownloadURL_attachment) {
		this.apkDownloadURL_attachment = apkDownloadURL_attachment;
	}

	public int getSwmc() {
		return swmc;
	}

	public void setSwmc(int swmc) {
		this.swmc = swmc;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getDalyTime() {
		return dalyTime;
	}

	public void setDalyTime(int dalyTime) {
		this.dalyTime = dalyTime;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getApkStatus() {
		return apkStatus;
	}

	public void setApkStatus(int apkStatus) {
		this.apkStatus = apkStatus;
	}

	public double getJhl() {
		return jhl;
	}

	public void setJhl(double jhl) {
		this.jhl = jhl;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getClsj() {
		return clsj;
	}

	public void setClsj(int clsj) {
		this.clsj = clsj;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public int getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(int actionStatus) {
		this.actionStatus = actionStatus;
	}

	public double getCpConversionRate() {
		return cpConversionRate;
	}

	public void setCpConversionRate(double cpConversionRate) {
		this.cpConversionRate = cpConversionRate;
	}

	public double getSilenceCpm() {
		return silenceCpm;
	}

	public void setSilenceCpm(double silenceCpm) {
		this.silenceCpm = silenceCpm;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getAdvLinkmanId() {
		return advLinkmanId;
	}

	public void setAdvLinkmanId(int advLinkmanId) {
		this.advLinkmanId = advLinkmanId;
	}
}
