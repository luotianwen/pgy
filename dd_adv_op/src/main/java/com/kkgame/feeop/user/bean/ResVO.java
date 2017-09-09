package com.kkgame.feeop.user.bean;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ResVO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String resName;
	
	private String resDesc;
	
	private int parentId;
	
	private int ppId;
	
	private List<ResVO> childList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<ResVO> getChildList() {
		return childList;
	}

	public void setChildList(List<ResVO> childList) {
		this.childList = childList;
	}

	public int getPpId() {
		return ppId;
	}

	public void setPpId(int ppId) {
		this.ppId = ppId;
	}
}
