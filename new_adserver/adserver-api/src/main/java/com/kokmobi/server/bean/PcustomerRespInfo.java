package com.kokmobi.server.bean;

import java.io.Serializable;

public class PcustomerRespInfo implements Serializable {


	//columns START
	/**
	 * id       db_column: ID
	 */
	private java.lang.Integer id;
	/**
	 * promotionId       db_column: promotionId
	 */
	private java.lang.Integer promotionId;
	/**
	 * redirectUrl       db_column: RedirectUrl
	 */
	private java.lang.String redirectUrl;
	private java.lang.String linkUrl;

	private java.lang.String dredirectUrl;


	/**
	 * createTime       db_column: CreateTime
	 */
	private String createTime;
	/**
	 * status       db_column: Status
	 */
	private Integer status;
	/**
	 * notes       db_column: Notes
	 */
	private java.lang.String notes;
	/**
	 * customerId       db_column: customerId
	 */
	private java.lang.Integer customerId;
	//columns END


	private  int cou;
	private String operatorId;
	private java.lang.String otherUrl;

	public String getOtherUrl() {
		return otherUrl;
	}

	public void setOtherUrl(String otherUrl) {
		this.otherUrl = otherUrl;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getDredirectUrl() {
		return dredirectUrl;
	}

	public void setDredirectUrl(String dredirectUrl) {
		this.dredirectUrl = dredirectUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}



	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
}
 
