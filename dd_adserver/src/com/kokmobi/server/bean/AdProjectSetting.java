package com.kokmobi.server.bean;

/**
 * 项目的广告下发相关信息的设置
 * @author min
 *
 */
public class AdProjectSetting {
	private int	projectId;
	private int status;				//开启推送
	private int timss;				//推送广告频率
	private int lower;				//推送广告上限
	private int ydownLoad;			//是否预下载
	private int isNotice;			//是否可以清除
	private int isPops;				//是否弹出提示界面
	private int isReturnDebug;		//插屏广告效果-------------+
	private int isGame;				//插屏展示广告数-----------+
	private int isTablePlaque;		//开启插屏
	private int isCjTablePlaque;	//插屏效果方式
	private int isCjPush;			//开启静默安装
	private int tablePlaquelower;	//插屏广告上限
	private int tablePlaquetimss;	//插屏广告频率
	
	private int isTablePlaqueDown;
	private int isopen;
	
	public int getIsTablePlaqueDown() {
		return isTablePlaqueDown;
	}
	public void setIsTablePlaqueDown(int isTablePlaqueDown) {
		this.isTablePlaqueDown = isTablePlaqueDown;
	}
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getYdownLoad() {
		return ydownLoad;
	}
	public void setYdownLoad(int ydownLoad) {
		this.ydownLoad = ydownLoad;
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
	public int getIsTablePlaque() {
		return isTablePlaque;
	}
	public void setIsTablePlaque(int isTablePlaque) {
		this.isTablePlaque = isTablePlaque;
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
	public int getTablePlaquelower() {
		return tablePlaquelower;
	}
	public void setTablePlaquelower(int tablePlaquelower) {
		this.tablePlaquelower = tablePlaquelower;
	}
	public int getTablePlaquetimss() {
		return tablePlaquetimss;
	}
	public void setTablePlaquetimss(int tablePlaquetimss) {
		this.tablePlaquetimss = tablePlaquetimss;
	}
	


}