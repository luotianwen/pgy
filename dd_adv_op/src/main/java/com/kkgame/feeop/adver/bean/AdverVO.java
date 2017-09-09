package com.kkgame.feeop.adver.bean;

import com.kkgame.feeop.base.BasicVO;

public class AdverVO extends BasicVO {

	private int id;
	
	private String name;
	
	private String implementClass;
	
	private String url;
	
	private String postValue;
	
	private int status;
	
	private int type;
	
	private String createTime;

	private String linkmanName;
	private String linkmanPhone;
	private String linkmanQQ;
	private String linkmanAddress;

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

	public String getImplementClass() {
		return implementClass;
	}

	public void setImplementClass(String implementClass) {
		this.implementClass = implementClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPostValue() {
		return postValue;
	}

	public void setPostValue(String postValue) {
		this.postValue = postValue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public String getLinkmanQQ() {
		return linkmanQQ;
	}

	public void setLinkmanQQ(String linkmanQQ) {
		this.linkmanQQ = linkmanQQ;
	}

	public String getLinkmanAddress() {
		return linkmanAddress;
	}

	public void setLinkmanAddress(String linkmanAddress) {
		this.linkmanAddress = linkmanAddress;
	}
}
