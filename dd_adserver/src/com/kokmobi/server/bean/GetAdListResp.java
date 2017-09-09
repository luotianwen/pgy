package com.kokmobi.server.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.sf.json.JSONObject;

public class GetAdListResp {

	private int isExit;
	@JsonProperty("PkgId") 
	private String PkgId;	
	private List<JSONObject> cpApkInfo;
	private List<JSONObject> pushApkInfo;
	private List<JSONObject> apkInfo;	//静默列表
	private List<JSONObject> plugInfor; //静默插件列表
	private int isDownLoad;
	private int time;
	private int lower;
	private int isNotice;
	private int pushStatus;
	private int isPops;
	private int statue;
	private int delayTimes;
	private int times;	//插屏、静默上线
	private int isSilent;
	private int adcount;
	private int effect;
	private int effectmethod;
	private int number =10;

	private int frequency;	//静默频率
	public int getIsExit() {
		return isExit;
	}
	public void setIsExit(int isExit) {
		this.isExit = isExit;
	}
	@JsonIgnore
	public String getPkgId() {
		return PkgId;
	}
	public void setPkgId(String pkgId) {
		PkgId = pkgId;
	}
	public List<JSONObject> getCpApkInfo() {
		return cpApkInfo;
	}
	public void setCpApkInfo(List<JSONObject> cpApkInfo) {
		this.cpApkInfo = cpApkInfo;
	}
	public List<JSONObject> getPushApkInfo() {
		return pushApkInfo;
	}
	public void setPushApkInfo(List<JSONObject> pushApkInfo) {
		this.pushApkInfo = pushApkInfo;
	}
	public int getIsDownLoad() {
		return isDownLoad;
	}
	public void setIsDownLoad(int isDownLoad) {
		this.isDownLoad = isDownLoad;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLower() {
		return lower;
	}
	public void setLower(int lower) {
		this.lower = lower;
	}
	public int getIsNotice() {
		return isNotice;
	}
	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}
	public int getPushStatus() {
		return pushStatus;
	}
	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}
	public int getIsPops() {
		return isPops;
	}
	public void setIsPops(int isPops) {
		this.isPops = isPops;
	}
	public int getStatue() {
		return statue;
	}
	public void setStatue(int statue) {
		this.statue = statue;
	}
	public int getDelayTimes() {
		return delayTimes;
	}
	public void setDelayTimes(int delayTimes) {
		this.delayTimes = delayTimes;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getIsSilent() {
		return isSilent;
	}
	public void setIsSilent(int isSilent) {
		this.isSilent = isSilent;
	}
	public int getAdcount() {
		return adcount;
	}
	public void setAdcount(int adcount) {
		this.adcount = adcount;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	public int getEffectmethod() {
		return effectmethod;
	}
	public void setEffectmethod(int effectmethod) {
		this.effectmethod = effectmethod;
	}
	public List<JSONObject> getApkInfo() {
		return apkInfo;
	}
	public void setApkInfo(List<JSONObject> apkInfo) {
		this.apkInfo = apkInfo;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public List<JSONObject> getPlugInfor() {
		return plugInfor;
	}

	public void setPlugInfor(List<JSONObject> plugInfor) {
		this.plugInfor = plugInfor;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
