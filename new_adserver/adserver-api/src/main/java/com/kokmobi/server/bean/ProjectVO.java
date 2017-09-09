package com.kokmobi.server.bean;

public class ProjectVO {

	private int id;
	
	private int status;
	
	private int isDown;
	
	private int isGuide;
	
	private int guideTimes;
	
	private int guideInterval;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsDown() {
		return isDown;
	}

	public void setIsDown(int isDown) {
		this.isDown = isDown;
	}

	public int getIsGuide() {
		return isGuide;
	}

	public void setIsGuide(int isGuide) {
		this.isGuide = isGuide;
	}

	public int getGuideTimes() {
		return guideTimes;
	}

	public void setGuideTimes(int guideTimes) {
		this.guideTimes = guideTimes;
	}

	public int getGuideInterval() {
		return guideInterval;
	}

	public void setGuideInterval(int guideInterval) {
		this.guideInterval = guideInterval;
	}
}
