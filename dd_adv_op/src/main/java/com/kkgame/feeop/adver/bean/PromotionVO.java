package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO; 

public class PromotionVO  extends BasicVO {
	 
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Integer id;
    /**
     * name       db_column: Name 
     */ 	
	private java.lang.String name;
    /**
     * adverId       db_column: AdverId 
     */ 	
	private java.lang.Integer adverId;
    /**
     * redirectUrl       db_column: RedirectUrl 
     */ 	
	private java.lang.String redirectUrl;
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
     * advLinkmanId       db_column: advLinkmanId 
     */ 	
	private java.lang.Integer advLinkmanId;
	//columns END
	private  String cou;
	private String operatorId;
	private  String iframe1;
	private String iframe2;
	private  String iframe3;
	private String iframe4;
	private  String iframe5;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAdverId() {
		return adverId;
	}

	public void setAdverId(Integer adverId) {
		this.adverId = adverId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
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

	public Integer getAdvLinkmanId() {
		return advLinkmanId;
	}

	public void setAdvLinkmanId(Integer advLinkmanId) {
		this.advLinkmanId = advLinkmanId;
	}

	public String getCou() {
		return cou;
	}

	public void setCou(String cou) {
		this.cou = cou;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getIframe1() {
		return iframe1;
	}

	public void setIframe1(String iframe1) {
		this.iframe1 = iframe1;
	}

	public String getIframe2() {
		return iframe2;
	}

	public void setIframe2(String iframe2) {
		this.iframe2 = iframe2;
	}

	public String getIframe3() {
		return iframe3;
	}

	public void setIframe3(String iframe3) {
		this.iframe3 = iframe3;
	}

	public String getIframe4() {
		return iframe4;
	}

	public void setIframe4(String iframe4) {
		this.iframe4 = iframe4;
	}

	public String getIframe5() {
		return iframe5;
	}

	public void setIframe5(String iframe5) {
		this.iframe5 = iframe5;
	}
}
 
