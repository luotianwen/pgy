package com.kokmobi.server.bean;

import java.io.Serializable;

public class SilentSetting implements Serializable {
	/** 频率 */
	private int frequency;
	/** 次数 */
	private int times;
	/** 下发多少个广告 */
	private int days;
	/** 未启用 */
	private int isdel;
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	
}
