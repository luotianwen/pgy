package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO; 

public class Linkads2VO  extends BasicVO {
	 
	
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private Integer id;
    /**
     * name       db_column: Name 
     */ 	
	private String name;
    /**
     * adverId       db_column: AdverId 
     */ 	
	private Integer adverId;
    /**
     * linkType       db_column: LinkType 
     */ 	
	private Integer linkType;
    /**
     * linkUrl       db_column: LinkUrl 
     */ 	
	private String linkUrl;
    /**
     * redirectUrl       db_column: RedirectUrl 
     */ 	
	private String redirectUrl;
    /**
     * deskIconName       db_column: DeskIconName 
     */ 	
	private String deskIconName;
    /**
     * deskIconUrl       db_column: DeskIconUrl 
     */ 	
	private String deskIconUrl;
    /**
     * statusBarTitle       db_column: StatusBarTitle 
     */ 	
	private String statusBarTitle;
    /**
     * statusBarContent       db_column: StatusBarContent 
     */ 	
	private String statusBarContent;
    /**
     * createTime       db_column: CreateTime 
     */ 	
	private String createTime;
    /**
     * status       db_column: Status 
     */ 	
	private Integer status;
    /**
     * orders       db_column: Orders 
     */ 	
	private Integer cap;
    /**
     * operator       db_column: Operator 
     */ 	
	private Integer operator;
    /**
     * countryIds       db_column: CountryIds 
     */ 	
	private String countryIds;
    /**
     * isRss       db_column: IsRss 
     */ 	
	private Integer cpm;
    /**
     * linkAdType       db_column: LinkAdType 
     */ 	
	private Integer linkAdType;
    /**
     * 点击类型       db_column: ClickType 
     */ 	
	private Integer clickType;
	//columns END
	private String notes   ;
	private Integer  advLinkmanId  ;

	public Integer getAdvLinkmanId() {
		return advLinkmanId;
	}

	public void setAdvLinkmanId(Integer advLinkmanId) {
		this.advLinkmanId = advLinkmanId;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setAdverId(Integer value) {
		this.adverId = value;
	}
	
	public Integer getAdverId() {
		return this.adverId;
	}
	public void setLinkType(Integer value) {
		this.linkType = value;
	}
	
	public Integer getLinkType() {
		return this.linkType;
	}
	public void setLinkUrl(String value) {
		this.linkUrl = value;
	}
	
	public String getLinkUrl() {
		return this.linkUrl;
	}
	public void setRedirectUrl(String value) {
		this.redirectUrl = value;
	}
	
	public String getRedirectUrl() {
		return this.redirectUrl;
	}
	public void setDeskIconName(String value) {
		this.deskIconName = value;
	}
	
	public String getDeskIconName() {
		return this.deskIconName;
	}
	public void setDeskIconUrl(String value) {
		this.deskIconUrl = value;
	}
	
	public String getDeskIconUrl() {
		return this.deskIconUrl;
	}
	public void setStatusBarTitle(String value) {
		this.statusBarTitle = value;
	}
	
	public String getStatusBarTitle() {
		return this.statusBarTitle;
	}
	public void setStatusBarContent(String value) {
		this.statusBarContent = value;
	}
	
	public String getStatusBarContent() {
		return this.statusBarContent;
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

	public void setOperator(Integer value) {
		this.operator = value;
	}
	
	public Integer getOperator() {
		return this.operator;
	}
	public void setCountryIds(String value) {
		this.countryIds = value;
	}
	
	public String getCountryIds() {
		return this.countryIds;
	}

	public void setLinkAdType(Integer value) {
		this.linkAdType = value;
	}
	
	public Integer getLinkAdType() {
		return this.linkAdType;
	}
	public void setClickType(Integer value) {
		this.clickType = value;
	}
	
	public Integer getClickType() {
		return this.clickType;
	}

	public Integer getCap() {
		return cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public Integer getCpm() {
		return cpm;
	}

	public void setCpm(Integer cpm) {
		this.cpm = cpm;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
 
