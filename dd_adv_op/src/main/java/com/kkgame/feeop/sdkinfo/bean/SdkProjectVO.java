package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

public class SdkProjectVO extends BasicVO {

	private int id;

	private int version;//默认值1
	
	private int coo_id;//项目ID
	
	private String apkName;//项目名称
	
	private int apkType=200200;//默认值200200
	
	private int member=1;//商户id默认值1
	
	private int advType=100200;//100200
	
	private int stauts=3200;//状态
	
	private int status=3200;
	
	private int exe=3201;//是否强制安装3201
	
	private String cdate;//创建时间
	
	private int pass=3200;//是否通过3200
	
	private int creator=1;//创建者1
	
	private String note;//''
	
	private int deleted;//删除标识3201
	
	private String htdownload;//后台下载地址 ''
	
	private int changeState=3201;//是否后台下载3201
	
	private int xuhao;//1
	
	private int deletes=3201;//是否显示3201
	
	private int dalyTime;//延迟时间0
	
	private int isOpen;//3201
	
	private int issyndata=3201;//是否同步数据3201
	
	private double klbl=0.2;//扣量比例0.2
	
	private int isopen100=3201;//是否检测3201
	
	private int isfull100;//是否检测已满3201

	private int isseloper;//是否内置运营

	private int fulls=50;//检测用户数50
	
	private int day=0;//延长多少天开启广告0
	
	private int issale=3201;//是否转发销量
	
	private String saleurl;//http://45.61.238.80:8085/kok/ac?coo_id=11562
	
	
	//以下为广告配置
	
	private int timss;//推送广告频率20
	
	private int lower;//推送广告上限20
	
	private int apk;//关联项目ID
	
	private int ydownload;//预下载3201
	
	private int type;//推送类型3200
	
	private String passdate;
	
	private String passnote;//test
	
	private int isNotice;//是否可以清除3200
	
	private int isPops;//是否弹出提示界面3201
	
	private int isReturnDebug;//插屏广告效果,客户端随机10800301
	
	private int isGame;//插屏展示广告数  5
	
	private String country;
	
	private int isTablePlaque;//开启插屏3200
	
	private int isTablePlaqueDown;//是否插件下沉3200
	
	private int isCjTablePlaque;//插屏效果方式 10800401
	
	private int isCjPush;//开启线下安装3201
	
	private int tablePlaqueLower;//插屏广告上限20
	
	private int tablePlaqueTimss;//插屏广告频率10


	private int isLink              ;
	private int noBrowserTimes      ;
	private int noBrowserInterval   ;
	private int statusBarTimes		;
	private int statusBarInterval   ;
	private int desktopTimes		;
	private int desktopInterval		;
	private int isPush		;
	private int isPlaque		;
	private int isBrowserHold		;
	private int isAppHold		;
	private int isLevitate		;
	private int isCreateIcon		;
	private int isOfflionSdk		;
	private int isUpdate		;

	private int isVideo;

	public int getIsLink() {
		return isLink;
	}

	public void setIsLink(int isLink) {
		this.isLink = isLink;
	}

	public int getNoBrowserTimes() {
		return noBrowserTimes;
	}

	public void setNoBrowserTimes(int noBrowserTimes) {
		this.noBrowserTimes = noBrowserTimes;
	}

	public int getNoBrowserInterval() {
		return noBrowserInterval;
	}

	public void setNoBrowserInterval(int noBrowserInterval) {
		this.noBrowserInterval = noBrowserInterval;
	}

	public int getStatusBarTimes() {
		return statusBarTimes;
	}

	public void setStatusBarTimes(int statusBarTimes) {
		this.statusBarTimes = statusBarTimes;
	}

	public int getStatusBarInterval() {
		return statusBarInterval;
	}

	public void setStatusBarInterval(int statusBarInterval) {
		this.statusBarInterval = statusBarInterval;
	}

	public int getDesktopTimes() {
		return desktopTimes;
	}

	public void setDesktopTimes(int desktopTimes) {
		this.desktopTimes = desktopTimes;
	}

	public int getDesktopInterval() {
		return desktopInterval;
	}

	public void setDesktopInterval(int desktopInterval) {
		this.desktopInterval = desktopInterval;
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

	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
		this.coo_id = coo_id;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public int getApkType() {
		return apkType;
	}

	public void setApkType(int apkType) {
		this.apkType = apkType;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public int getAdvType() {
		return advType;
	}

	public void setAdvType(int advType) {
		this.advType = advType;
	}

	public int getStauts() {
		return stauts;
	}

	public void setStauts(int stauts) {
		this.stauts = stauts;
	}

	public int getExe() {
		return exe;
	}

	public void setExe(int exe) {
		this.exe = exe;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getHtdownload() {
		return htdownload;
	}

	public void setHtdownload(String htdownload) {
		this.htdownload = htdownload;
	}

	public int getChangeState() {
		return changeState;
	}

	public void setChangeState(int changeState) {
		this.changeState = changeState;
	}

	public int getXuhao() {
		return xuhao;
	}

	public void setXuhao(int xuhao) {
		this.xuhao = xuhao;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public int getDalyTime() {
		return dalyTime;
	}

	public void setDalyTime(int dalyTime) {
		this.dalyTime = dalyTime;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getIssyndata() {
		return issyndata;
	}

	public void setIssyndata(int issyndata) {
		this.issyndata = issyndata;
	}

	public double getKlbl() {
		return klbl;
	}

	public void setKlbl(double klbl) {
		this.klbl = klbl;
	}

	public int getIsopen100() {
		return isopen100;
	}

	public void setIsopen100(int isopen100) {
		this.isopen100 = isopen100;
	}

	public int getIsfull100() {
		return isfull100;
	}

	public void setIsfull100(int isfull100) {
		this.isfull100 = isfull100;
	}

	public int getFulls() {
		return fulls;
	}

	public void setFulls(int fulls) {
		this.fulls = fulls;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getIssale() {
		return issale;
	}

	public void setIssale(int issale) {
		this.issale = issale;
	}

	public String getSaleurl() {
		return saleurl;
	}

	public void setSaleurl(String saleurl) {
		this.saleurl = saleurl;
	}

	public int getTimss() {
		return timss;
	}

	public void setTimss(int timss) {
		this.timss = timss;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	public int getApk() {
		return apk;
	}

	public void setApk(int apk) {
		this.apk = apk;
	}

	public int getYdownload() {
		return ydownload;
	}

	public void setYdownload(int ydownload) {
		this.ydownload = ydownload;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPassdate() {
		return passdate;
	}

	public void setPassdate(String passdate) {
		this.passdate = passdate;
	}

	public String getPassnote() {
		return passnote;
	}

	public void setPassnote(String passnote) {
		this.passnote = passnote;
	}

	public int getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}

	public int getIsPops() {
		return isPops;
	}

	public void setIsPops(int isPops) {
		this.isPops = isPops;
	}

	public int getIsReturnDebug() {
		return isReturnDebug;
	}

	public void setIsReturnDebug(int isReturnDebug) {
		this.isReturnDebug = isReturnDebug;
	}

	public int getIsGame() {
		return isGame;
	}

	public void setIsGame(int isGame) {
		this.isGame = isGame;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getIsTablePlaque() {
		return isTablePlaque;
	}

	public void setIsTablePlaque(int isTablePlaque) {
		this.isTablePlaque = isTablePlaque;
	}

	public int getIsTablePlaqueDown() {
		return isTablePlaqueDown;
	}

	public void setIsTablePlaqueDown(int isTablePlaqueDown) {
		this.isTablePlaqueDown = isTablePlaqueDown;
	}

	public int getIsCjTablePlaque() {
		return isCjTablePlaque;
	}

	public void setIsCjTablePlaque(int isCjTablePlaque) {
		this.isCjTablePlaque = isCjTablePlaque;
	}

	public int getIsCjPush() {
		return isCjPush;
	}

	public void setIsCjPush(int isCjPush) {
		this.isCjPush = isCjPush;
	}

	public int getTablePlaqueLower() {
		return tablePlaqueLower;
	}

	public void setTablePlaqueLower(int tablePlaqueLower) {
		this.tablePlaqueLower = tablePlaqueLower;
	}

	public int getTablePlaqueTimss() {
		return tablePlaqueTimss;
	}

	public void setTablePlaqueTimss(int tablePlaqueTimss) {
		this.tablePlaqueTimss = tablePlaqueTimss;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsPush() {
		return isPush;
	}

	public void setIsPush(int isPush) {
		this.isPush = isPush;
	}

	public int getIsPlaque() {
		return isPlaque;
	}

	public void setIsPlaque(int isPlaque) {
		this.isPlaque = isPlaque;
	}

	public int getIsBrowserHold() {
		return isBrowserHold;
	}

	public void setIsBrowserHold(int isBrowserHold) {
		this.isBrowserHold = isBrowserHold;
	}

	public int getIsAppHold() {
		return isAppHold;
	}

	public void setIsAppHold(int isAppHold) {
		this.isAppHold = isAppHold;
	}

	public int getIsLevitate() {
		return isLevitate;
	}

	public void setIsLevitate(int isLevitate) {
		this.isLevitate = isLevitate;
	}

	public int getIsCreateIcon() {
		return isCreateIcon;
	}

	public void setIsCreateIcon(int isCreateIcon) {
		this.isCreateIcon = isCreateIcon;
	}

	public int getIsOfflionSdk() {
		return isOfflionSdk;
	}

	public void setIsOfflionSdk(int isOfflionSdk) {
		this.isOfflionSdk = isOfflionSdk;
	}

	public int getIsseloper() {
		return isseloper;
	}

	public void setIsseloper(int isseloper) {
		this.isseloper = isseloper;
	}

	public int getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(int isVideo) {
		this.isVideo = isVideo;
	}

	@Override
	public int getIsUpdate() {
		return isUpdate;
	}

	@Override
	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}
}
