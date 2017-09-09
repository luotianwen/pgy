package com.kkgame.feeop.customer.bean;

public class ProductSdkVO {

	private int productId;
	
	private int sdkId;
	
	private String productName;
	
	private String sdkName;
	
	private int hasSelfConfirm;

	public int getHasSelfConfirm() {
		return hasSelfConfirm;
	}

	public void setHasSelfConfirm(int hasSelfConfirm) {
		this.hasSelfConfirm = hasSelfConfirm;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSdkId() {
		return sdkId;
	}

	public void setSdkId(int sdkId) {
		this.sdkId = sdkId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSdkName() {
		return sdkName;
	}

	public void setSdkName(String sdkName) {
		this.sdkName = sdkName;
	}
}
