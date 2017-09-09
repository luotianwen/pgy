package com.kkgame.feeop.customer.bean;

import com.kkgame.feeop.base.BasicVO;

public class AgentVO extends BasicVO {

	private int id;
	private String name;
	private int type;
	private int level;
	private String cooperateMode;
	private String description;
	private int portalUserId;
	private int isPage;

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getPortalUserId() {
		return portalUserId;
	}

	public void setPortalUserId(int portalUserId) {
		this.portalUserId = portalUserId;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCooperateMode() {
		return cooperateMode;
	}

	public void setCooperateMode(String cooperateMode) {
		this.cooperateMode = cooperateMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
