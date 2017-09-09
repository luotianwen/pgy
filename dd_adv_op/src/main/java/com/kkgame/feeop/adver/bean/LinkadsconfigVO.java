package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO; 

public class LinkadsconfigVO  extends BasicVO {
	 
	
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
     * linkType       db_column: LinkType 
     */ 	
	private Integer linkType;
    /**
     * linkUrl       db_column: LinkUrl 
     */ 	
	private String linkUrl;
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
	//columns END

 
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

	
	 
}
 
