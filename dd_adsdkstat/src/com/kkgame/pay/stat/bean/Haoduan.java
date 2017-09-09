package com.kkgame.pay.stat.bean;

import java.io.Serializable;

public class Haoduan implements Serializable {
	private static final long serialVersionUID = -3039008918596302921L;
	private Integer haoduanHeader;
	private Integer subHaoduan;
	private String fullHaoduan;
	private Integer cityId;
	private String city;
	private String province;

	public Integer getHaoduanHeader() {
		/* 18 */return this.haoduanHeader;
	}

	public void setHaoduanHeader(Integer haoduanHeader) {
		/* 22 */this.haoduanHeader = haoduanHeader;
	}

	public Integer getSubHaoduan() {
		/* 26 */return this.subHaoduan;
	}

	public void setSubHaoduan(Integer subHaoduan) {
		/* 30 */this.subHaoduan = subHaoduan;
	}

	public String getFullHaoduan() {
		/* 34 */return this.fullHaoduan;
	}

	public void setFullHaoduan(String fullHaoduan) {
		/* 38 */this.fullHaoduan = fullHaoduan;
	}

	public Integer getCityId() {
		/* 42 */return this.cityId;
	}

	public void setCityId(Integer cityId) {
		/* 46 */this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(haoduanHeader).append("|")
		.append(subHaoduan).append("|")
		.append(fullHaoduan).append("|")
		.append(cityId).append("|")
		.append(city).append("|")
		.append(province);
		return sb.toString();
	}
}