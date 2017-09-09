package com.kokmobi.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class GetOnlineAdResp implements Serializable {

	private int isExit;
	private List<JSONObject> deskInfo;
	private List<JSONObject> wwws;
	private List<JSONObject> levitateInfo;
	private List<JSONObject> bannerInfo;
	private List<JSONObject> holdUpInfo;
	private List<JSONObject> subscribeInfo;
	private String holdUp;
	private String push;
	public int getIsExit() {
		return isExit;
	}
	public void setIsExit(int isExit) {
		this.isExit = isExit;
	}

	public List<JSONObject> getDeskInfo() {
		return deskInfo;
	}

	public void setDeskInfo(List<JSONObject> deskInfo) {
		this.deskInfo = deskInfo;
	}

	public List<JSONObject> getHoldUpInfo() {
		return holdUpInfo;
	}

	public void setHoldUpInfo(List<JSONObject> holdUpInfo) {
		this.holdUpInfo = holdUpInfo;
	}
	public List<JSONObject> getWwws() {
		return wwws;
	}

	public void setWwws(List<JSONObject> wwws) {
		this.wwws = wwws;
	}

	public List<JSONObject> getLevitateInfo() {
		return levitateInfo;
	}

	public void setLevitateInfo(List<JSONObject> levitateInfo) {
		this.levitateInfo = levitateInfo;
	}

	public List<JSONObject> getBannerInfo() {
		return bannerInfo;
	}

	public void setBannerInfo(List<JSONObject> bannerInfo) {
		this.bannerInfo = bannerInfo;
	}

	public String getHoldUp() {
		return holdUp;
	}

	public void setHoldUp(String holdUp) {
		this.holdUp = holdUp;
	}

	public String getPush() {
		return push;
	}

	public void setPush(String push) {
		this.push = push;
	}

	public List<JSONObject> getSubscribeInfo() {
		return subscribeInfo;
	}

	public void setSubscribeInfo(List<JSONObject> subscribeInfo) {
		this.subscribeInfo = subscribeInfo;
	}
}
