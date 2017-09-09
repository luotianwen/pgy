package com.kkgame.hz.entities;

import com.kkgame.hz.base.BaseVO;

public class ProductVO extends BaseVO {

	private int id;
	
	private int projectId;
	
	private int type;
	
	private String name;
	
	private int cooperateMode;
	
	private int agentId;
	
	private int bdId;
	
	private int bhId;
	
	private int customerId;
	
	private String agentName;
	
	private String bdName;
	
	private String customerName;
	
	private int osId;
	
	private int screenId;
	
	private String url;
	private int status;



	private int bdBuildInRegisterPrice;//内置激活单价
	private int cuBuildInRegisterPrice;//内置激活单价
	private int bhBuildInRegisterPrice;//内置激活单价
	
	private int bdUninstallRegisterPrice;//可卸载单价
	private int cuUninstallRegisterPrice;//可卸载单价
	private int bhUninstallRegisterPrice;//可卸载单价
	
	private double bdDividePercent;//分成比例
	private double cuDividePercent;//分成比例
	private double bhDividePercent;//分成比例
	
	private int isPage;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getCooperateMode() {
		return cooperateMode;
	}

	public void setCooperateMode(int cooperateMode) {
		this.cooperateMode = cooperateMode;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getBdId() {
		return bdId;
	}

	public void setBdId(int bdId) {
		this.bdId = bdId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBdName() {
		return bdName;
	}

	public void setBdName(String bdName) {
		this.bdName = bdName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getBdBuildInRegisterPrice() {
		return bdBuildInRegisterPrice;
	}

	public void setBdBuildInRegisterPrice(int bdBuildInRegisterPrice) {
		this.bdBuildInRegisterPrice = bdBuildInRegisterPrice;
	}

	public int getCuBuildInRegisterPrice() {
		return cuBuildInRegisterPrice;
	}

	public void setCuBuildInRegisterPrice(int cuBuildInRegisterPrice) {
		this.cuBuildInRegisterPrice = cuBuildInRegisterPrice;
	}

	public int getBdUninstallRegisterPrice() {
		return bdUninstallRegisterPrice;
	}

	public void setBdUninstallRegisterPrice(int bdUninstallRegisterPrice) {
		this.bdUninstallRegisterPrice = bdUninstallRegisterPrice;
	}

	public int getCuUninstallRegisterPrice() {
		return cuUninstallRegisterPrice;
	}

	public void setCuUninstallRegisterPrice(int cuUninstallRegisterPrice) {
		this.cuUninstallRegisterPrice = cuUninstallRegisterPrice;
	}

	public double getBdDividePercent() {
		return bdDividePercent;
	}

	public void setBdDividePercent(double bdDividePercent) {
		this.bdDividePercent = bdDividePercent;
	}

	public double getCuDividePercent() {
		return cuDividePercent;
	}

	public void setCuDividePercent(double cuDividePercent) {
		this.cuDividePercent = cuDividePercent;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOsId() {
		return osId;
	}

	public void setOsId(int osId) {
		this.osId = osId;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getBhBuildInRegisterPrice() {
		return bhBuildInRegisterPrice;
	}

	public void setBhBuildInRegisterPrice(int bhBuildInRegisterPrice) {
		this.bhBuildInRegisterPrice = bhBuildInRegisterPrice;
	}

	public int getBhUninstallRegisterPrice() {
		return bhUninstallRegisterPrice;
	}

	public void setBhUninstallRegisterPrice(int bhUninstallRegisterPrice) {
		this.bhUninstallRegisterPrice = bhUninstallRegisterPrice;
	}

	public double getBhDividePercent() {
		return bhDividePercent;
	}

	public void setBhDividePercent(double bhDividePercent) {
		this.bhDividePercent = bhDividePercent;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getBhId() {
		return bhId;
	}

	public void setBhId(int bhId) {
		this.bhId = bhId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
