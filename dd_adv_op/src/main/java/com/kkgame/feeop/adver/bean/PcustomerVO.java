package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO; 

public class PcustomerVO  extends BasicVO {
	 
	
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
	/**
	 * notes       db_column: Notes
	 */
	private String couid;
	/**
	 * notes       db_column: Notes
	 */
	private  int cou;
	private String operatorId;
	private java.lang.String otherUrl;
	private String channelBackUrl;
	private double payout;
	public String getOtherUrl() {
		return otherUrl;
	}

	public void setOtherUrl(String otherUrl) {
		this.otherUrl = otherUrl;
	}

	public String getCouid() {
		return couid;
	}

	public void setCouid(String couid) {
		this.couid = couid;
	}

	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setPromotionId(java.lang.Integer value) {
		this.promotionId = value;
	}
	
	public java.lang.Integer getPromotionId() {
		return this.promotionId;
	}
	public void setRedirectUrl(java.lang.String value) {
		this.redirectUrl = value;
	}
	
	public java.lang.String getRedirectUrl() {
		return this.redirectUrl;
	}
	public void setCreateTime(String value) {
		this.createTime = value;
	}
	
	public String getCreateTime() {
		return this.createTime;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setNotes(java.lang.String value) {
		this.notes = value;
	}
	
	public java.lang.String getNotes() {
		return this.notes;
	}
	public void setCustomerId(java.lang.Integer value) {
		this.customerId = value;
	}
	
	public java.lang.Integer getCustomerId() {
		return this.customerId;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getChannelBackUrl() {
		return channelBackUrl;
	}

	public void setChannelBackUrl(String channelBackUrl) {
		this.channelBackUrl = channelBackUrl;
	}

	public double getPayout() {
		return payout;
	}

	public void setPayout(double payout) {
		this.payout = payout;
	}
}
 
