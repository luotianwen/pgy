package com.kkgame.hz.base;

public class NameVO {
	private String id;
	private String name;
	private String parentId;
	
	private String tele;

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public NameVO() {

	}

	public NameVO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public NameVO(int id, String name) {
		this.id = id + "";
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
