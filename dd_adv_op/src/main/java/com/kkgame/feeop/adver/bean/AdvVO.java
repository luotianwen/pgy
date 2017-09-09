package com.kkgame.feeop.adver.bean;

import com.kkgame.feeop.base.BasicVO;

public class AdvVO extends BasicVO {

	private int id;
	
	private String name;
	
	private int status;
	
	private int linkType;
	
	private String linkUrl;
	
	private String deskIconName;
	
	private String deskIconUrl;
	
	private String statusBarTitle;
	
	private String statusBarContent;
	
	private String createTime;
	
	private int adverId;
	
	private String adverName;
	
	private String redirectUrl;
	
	private int orders;

	public int getAdverId() {
		return adverId;
	}

	public void setAdverId(int adverId) {
		this.adverId = adverId;
	}

	public String getAdverName() {
		return adverName;
	}

	public void setAdverName(String adverName) {
		this.adverName = adverName;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getDeskIconName() {
		return deskIconName;
	}

	public void setDeskIconName(String deskIconName) {
		this.deskIconName = deskIconName;
	}

	public String getDeskIconUrl() {
		return deskIconUrl;
	}

	public void setDeskIconUrl(String deskIconUrl) {
		this.deskIconUrl = deskIconUrl;
	}

	public String getStatusBarTitle() {
		return statusBarTitle;
	}

	public void setStatusBarTitle(String statusBarTitle) {
		this.statusBarTitle = statusBarTitle;
	}

	public String getStatusBarContent() {
		return statusBarContent;
	}

	public void setStatusBarContent(String statusBarContent) {
		this.statusBarContent = statusBarContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}
	
	
}
