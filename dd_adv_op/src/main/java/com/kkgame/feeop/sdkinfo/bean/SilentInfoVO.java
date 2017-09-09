package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

public class SilentInfoVO extends BasicVO {

	
	private int id;
	
	private int version;
	
	private int frequency;
	
	private int times;
	
	private int days;
	
	private int isDel;
	
	private String versions;
	
	private int status;
	
	private String note;
	
	private String noteInfo;
	
	public String getNoteInfo() {
		return noteInfo;
	}

	public void setNoteInfo(String noteInfo) {
		this.noteInfo = noteInfo;
	}

	private String yl1;
	
	private String yl2;
	
	private int creator;

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

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getYl1() {
		return yl1;
	}

	public void setYl1(String yl1) {
		this.yl1 = yl1;
	}

	public String getYl2() {
		return yl2;
	}

	public void setYl2(String yl2) {
		this.yl2 = yl2;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}
}
