package com.kkgame.feeop.record.bean;
import com.kkgame.feeop.base.BasicVO; 

public class ApkCphcTotalModelVO  extends BasicVO {
	 
	
	//columns START
    /**
     * 日期       db_column: sdate 
     */ 	
	private String sdate;
    /**
     * 广告ID       db_column: apkid 
     */ 	
	private java.lang.Integer apkid;
    /**
     * version       db_column: version 
     */ 	
	private java.lang.Integer version;
    /**
     * cpid       db_column: cpid 
     */ 	
	private java.lang.Integer cpid;
    /**
     * 安装数       db_column: installtotal 
     */ 	
	private java.lang.Integer installtotal;
    /**
     * 展示数       db_column: showtotal 
     */ 	
	private java.lang.Integer showtotal;
    /**
     * 实时对接数据总和       db_column: realtotal 
     */ 	
	private java.lang.Integer realtotal;
    /**
     * 手动输入对接数据       db_column: inputtotal 
     */ 	
	private java.lang.Integer inputtotal;
    /**
     * 单价       db_column: price 
     */ 	
	private Double price;
    /**
     * 单价*对接数据总和       db_column: income 
     */ 	
	private Double income;
    /**
     * 收入/展示*1000       db_column: cpm 
     */ 	
	private Double cpm;
    /**
     * creator       db_column: creator 
     */ 	
	private java.lang.Integer creator;
    /**
     * realPercent       db_column: realPercent 
     */ 	
	private Double realPercent;
    /**
     * inputPercent       db_column: inputPercent 
     */ 	
	private Double inputPercent;
	//columns END
	private String table;
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public java.lang.Integer getApkid() {
		return apkid;
	}
	public void setApkid(java.lang.Integer apkid) {
		this.apkid = apkid;
	}
	public java.lang.Integer getVersion() {
		return version;
	}
	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}
	public java.lang.Integer getCpid() {
		return cpid;
	}
	public void setCpid(java.lang.Integer cpid) {
		this.cpid = cpid;
	}
	public java.lang.Integer getInstalltotal() {
		return installtotal;
	}
	public void setInstalltotal(java.lang.Integer installtotal) {
		this.installtotal = installtotal;
	}
	public java.lang.Integer getShowtotal() {
		return showtotal;
	}
	public void setShowtotal(java.lang.Integer showtotal) {
		this.showtotal = showtotal;
	}
	public java.lang.Integer getRealtotal() {
		return realtotal;
	}
	public void setRealtotal(java.lang.Integer realtotal) {
		this.realtotal = realtotal;
	}
	public java.lang.Integer getInputtotal() {
		return inputtotal;
	}
	public void setInputtotal(java.lang.Integer inputtotal) {
		this.inputtotal = inputtotal;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getCpm() {
		return cpm;
	}
	public void setCpm(Double cpm) {
		this.cpm = cpm;
	}
	public java.lang.Integer getCreator() {
		return creator;
	}
	public void setCreator(java.lang.Integer creator) {
		this.creator = creator;
	}
	public Double getRealPercent() {
		return realPercent;
	}
	public void setRealPercent(Double realPercent) {
		this.realPercent = realPercent;
	}
	public Double getInputPercent() {
		return inputPercent;
	}
	public void setInputPercent(Double inputPercent) {
		this.inputPercent = inputPercent;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
}
 
