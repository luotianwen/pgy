package com.kkgame.feeop.record.bean;
import com.kkgame.feeop.base.BasicVO; 

public class CountryLevelVO  extends BasicVO {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * countrycode       db_column: countrycode 
     */ 	
	private java.lang.String countrycode;
    /**
     * countryname       db_column: countryname 
     */ 	
	private java.lang.String countryname;
    /**
     * 等级,1高2中3低       db_column: level 
     */ 	
	private java.lang.Integer level;
	//columns END
	private java.lang.Integer countryId;
	//columns END
	private java.lang.Integer productId;
	private java.lang.String productname;
	
	private int isUpdate;
	 

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public java.lang.String getProductname() {
		return productname;
	}

	public void setProductname(java.lang.String productname) {
		this.productname = productname;
	}

	public java.lang.Integer getProductId() {
		return productId;
	}

	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}

	public java.lang.Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(java.lang.Integer countryId) {
		this.countryId = countryId;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setCountrycode(java.lang.String value) {
		this.countrycode = value;
	}
	
	public java.lang.String getCountrycode() {
		return this.countrycode;
	}
	public void setCountryname(java.lang.String value) {
		this.countryname = value;
	}
	
	public java.lang.String getCountryname() {
		return this.countryname;
	}
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	
	public java.lang.Integer getLevel() {
		return this.level;
	}

	
	 
}
 
