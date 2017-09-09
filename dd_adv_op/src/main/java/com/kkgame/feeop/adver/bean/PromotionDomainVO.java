package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO; 

public class PromotionDomainVO  extends BasicVO {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * download       db_column: download 
     */ 	
	private java.lang.String download;
    /**
     * cdate       db_column: cdate 
     */ 	
	private String cdate;
	//columns END

 
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setDownload(java.lang.String value) {
		this.download = value;
	}
	
	public java.lang.String getDownload() {
		return this.download;
	}
	public void setCdate(String value) {
		this.cdate = value;
	}
	
	public String getCdate() {
		return this.cdate;
	}

	
	 
}
 
