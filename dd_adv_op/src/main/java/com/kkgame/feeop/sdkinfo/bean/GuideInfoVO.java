package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

public class GuideInfoVO extends BasicVO{

	private int id;
	
	private int version;
	
	private int ydcs;
	
	private int ydjg;
	
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

	public int getYdcs() {
		return ydcs;
	}

	public void setYdcs(int ydcs) {
		this.ydcs = ydcs;
	}

	public int getYdjg() {
		return ydjg;
	}

	public void setYdjg(int ydjg) {
		this.ydjg = ydjg;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}
}
