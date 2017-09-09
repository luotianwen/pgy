package com.kokmobi.server.bean;

import java.io.Serializable;

public class AdUsersProcessResp implements Serializable {
	private int status;		//1成功，0失败
	private int isfull100;	//1限制弹广告 0为正常（销量多少之前不弹广告）
	private int day;		//O为 不限制  否则为延后多少天才请求广告
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsfull100() {
		return isfull100;
	}
	public void setIsfull100(int isfull100) {
		this.isfull100 = isfull100;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}	
}
