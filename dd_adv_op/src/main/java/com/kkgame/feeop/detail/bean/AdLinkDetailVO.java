package com.kkgame.feeop.detail.bean;

import com.kkgame.feeop.base.BasicVO;

public class AdLinkDetailVO extends BasicVO {


	
	private String imei;
	
	private int clickType;
	
	private String linkType;
	
	private int coo_id;
	
	private int adId;
	
	private String cdate;
	
	private String table;
	private String cou;

	public int getCoo_id() {
		return coo_id;
	}

	public void setCoo_id(int coo_id) {
		this.coo_id = coo_id;
	}

	public String getCou() {
		return cou;
	}

	public void setCou(String cou) {
		this.cou = cou;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}



	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getClickType() {
		return clickType;
	}

	public void setClickType(int clickType) {
		this.clickType = clickType;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}



	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
}
