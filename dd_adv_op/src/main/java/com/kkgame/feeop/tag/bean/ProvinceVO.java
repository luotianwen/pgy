package com.kkgame.feeop.tag.bean;

public class ProvinceVO {

	private int provinceId;
	
	private String provinceName;

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public ProvinceVO(int provinceId, String provinceName) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public ProvinceVO() {
		super();
	}

}
