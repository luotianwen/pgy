package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

public class SdkInfoVO extends BasicVO {

	private int id;
	
	private int sdkVersion;
	
	private String name;
	
	private String descInfo;
	
	private String createTime;
	
	private int status;
	
	private String url;
	
	private int isPage;

	private int sdkId;
	// add field
	private String startClass; // 启动类
	private String startArg;   // 启动参数
	private int classType;     // 类类型【0：接口，1：静态类】
	private int rank;          // 排序等级
	private String md5;
	public int getSdkId() {
		return sdkId;
	}

	public void setSdkId(int sdkId) {
		this.sdkId = sdkId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(int sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public String getStartClass() {
		return startClass;
	}

	public void setStartClass(String startClass) {
		this.startClass = startClass;
	}

	public String getStartArg() {
		return startArg;
	}

	public void setStartArg(String startArg) {
		this.startArg = startArg;
	}

	public int getClassType() {
		return classType;
	}

	public void setClassType(int classType) {
		this.classType = classType;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
