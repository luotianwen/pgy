package com.kkgame.hz.entities;

public class BhVO extends PortalUserVO {

	private int id;
	private String name;
	private int businessDeveloperId;
	private String description;
	private int portalUserId;
	private String businessDeveloperName;
	private boolean ifOwn;
	
	public boolean isIfOwn() {
		return ifOwn;
	}

	public void setIfOwn(boolean ifOwn) {
		this.ifOwn = ifOwn;
	}

	public String getBusinessDeveloperName() {
		return businessDeveloperName;
	}

	public void setBusinessDeveloperName(String businessDeveloperName) {
		this.businessDeveloperName = businessDeveloperName;
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

	public int getBusinessDeveloperId() {
		return businessDeveloperId;
	}

	public void setBusinessDeveloperId(int businessDeveloperId) {
		this.businessDeveloperId = businessDeveloperId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPortalUserId() {
		return portalUserId;
	}

	public void setPortalUserId(int portalUserId) {
		this.portalUserId = portalUserId;
	}
}
