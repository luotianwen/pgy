package com.kkgame.feeop.sdkinfo.bean;
import com.kkgame.feeop.base.BasicVO; 

public class SdkdomainVO  extends BasicVO {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * download       db_column: download 
     */ 	
	private String download;
    /**
     * cdate       db_column: cdate 
     */ 	
	private String cdate;
	//columns END

 
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setDownload(String value) {
		this.download = value;
	}
	
	public String getDownload() {
		return this.download;
	}
	public void setCdate(String value) {
		this.cdate = value;
	}
	
	public String getCdate() {
		return this.cdate;
	}

	
	 
}
 
