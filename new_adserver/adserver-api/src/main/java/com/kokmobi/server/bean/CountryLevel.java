package com.kokmobi.server.bean;

import java.io.Serializable;

public class CountryLevel implements Serializable {
	private int countryId;
	private int level;
	private int productId;
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
}
