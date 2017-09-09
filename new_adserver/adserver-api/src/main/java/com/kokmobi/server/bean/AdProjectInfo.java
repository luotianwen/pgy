package com.kokmobi.server.bean;

import java.io.Serializable;

/**
 * 项目的基本信息，包括是否转发销量、是否达到下发广告信息
 * @author min
 *
 */
public class AdProjectInfo implements Serializable {
	private String coo_id;
	private int member;
	private int stauts;
	private int exe;
	private int pass;	
	private int deleted;
	private int changeState;
	private int deletes;
	private int dalyTime;
	private int isopen;
	private int issyndata;
	private int isopen100;
	private int isfull100;
	private int fulls;
	private int day;
	private int issale;
	private String saleurl;
	private int isPush		;
	private int isPlaque		;
	private int isBrowserHold		;
	private int isAppHold		;
	private int isLevitate		;
	private int isCreateIcon		;
	private int isOfflionSdk		;
	private int isUpdate		;

	public String getCoo_id() {
		return coo_id;
	}
	public void setCoo_id(String coo_id) {
		this.coo_id = coo_id;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
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
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getChangeState() {
		return changeState;
	}
	public void setChangeState(int changeState) {
		this.changeState = changeState;
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
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public int getIssyndata() {
		return issyndata;
	}
	public void setIssyndata(int issyndata) {
		this.issyndata = issyndata;
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

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}
}
