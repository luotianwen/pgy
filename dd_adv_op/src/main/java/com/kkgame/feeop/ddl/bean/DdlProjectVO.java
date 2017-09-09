package com.kkgame.feeop.ddl.bean;

import com.kkgame.feeop.base.BasicVO;

public class DdlProjectVO extends BasicVO {

	private int id;
	
	private String name;
	
	private String productName;
	
	private String channelName;
	
	private int version;
	
	private int productId;
	
	private int channelId;
	
	private String clickUrl;
	
	private String redirectUrl;
	
	private float rate;
	
	private int status;
	
	private String createTime;
	
	private String saleUrl;
	
	private String uaTypes;
	
	private int coo_id;
	
	private int countSale;
	
	private int isAllSale;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSaleUrl() {
		return saleUrl;
	}

	public void setSaleUrl(String saleUrl) {
		this.saleUrl = saleUrl;
	}

	public String getUaTypes() {
		return uaTypes;
	}

	public void setUaTypes(String uaTypes) {
		this.uaTypes = uaTypes;
	}

	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
		this.coo_id = coo_id;
	}

	public int getCountSale() {
		return countSale;
	}

	public void setCountSale(int countSale) {
		this.countSale = countSale;
	}

	public int getIsAllSale() {
		return isAllSale;
	}

	public void setIsAllSale(int isAllSale) {
		this.isAllSale = isAllSale;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}
