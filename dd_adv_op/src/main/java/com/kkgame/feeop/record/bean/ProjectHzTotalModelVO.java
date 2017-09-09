package com.kkgame.feeop.record.bean;
import com.kkgame.feeop.base.BasicVO; 

public class ProjectHzTotalModelVO  extends BasicVO {
	 
	
	//columns START
    /**
     * 日期       db_column: sdate 
     */ 	
	private String sdate;
    /**
     * 项目ID       db_column: projectId 
     */ 	
	private java.lang.Integer projectId;
    /**
     * 1:sdk,2:apk       db_column: sdktype 
     */ 	
	private java.lang.Integer sdktype;
    /**
     * 销量       db_column: newusers 
     */ 	
	private java.lang.Integer newusers;
    /**
     * 活跃数       db_column: actusers 
     */ 	
	private java.lang.Integer actusers;
    /**
     * 安装次数--高       db_column: install_high 
     */ 	
	private java.lang.Integer installHigh;
    /**
     * 安装次数--中       db_column: install_mid 
     */ 	
	private java.lang.Integer installMid;
    /**
     * 安装次数--低       db_column: install_low 
     */ 	
	private java.lang.Integer installLow;
    /**
     * 安装次数-无结算国家       db_column: install_none 
     */ 	
	private java.lang.Integer installNone;
    /**
     * 展示次数       db_column: showtotal 
     */ 	
	private java.lang.Integer showtotal;
    /**
     * 收入-根据广告收益统计       db_column: income 
     */ 	
	private Double income;
    /**
     * 第三方收益--手动填写       db_column: thirdincome 
     */ 	
	private Double thirdincome;
    /**
     * 结算销量-高       db_column: highusers 
     */ 	
	private java.lang.Integer highusers;
    /**
     * 结算销量-中       db_column: midusers 
     */ 	
	private java.lang.Integer midusers;
    /**
     * 结算销量-低       db_column: lowusers 
     */ 	
	private java.lang.Integer lowusers;
    /**
     * 成本，结算销量*单价总和       db_column: outcome 
     */ 	
	private Double outcome;
    /**
     * 状态，结算数据是否同步（0未同步）       db_column: status 
     */ 	
	private java.lang.Integer status;
    /**
     * creator       db_column: creator 
     */ 	
	private java.lang.Integer creator;
	//columns END

	private String table;

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public java.lang.Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(java.lang.Integer projectId) {
		this.projectId = projectId;
	}

	public java.lang.Integer getSdktype() {
		return sdktype;
	}

	public void setSdktype(java.lang.Integer sdktype) {
		this.sdktype = sdktype;
	}

	public java.lang.Integer getNewusers() {
		return newusers;
	}

	public void setNewusers(java.lang.Integer newusers) {
		this.newusers = newusers;
	}

	public java.lang.Integer getActusers() {
		return actusers;
	}

	public void setActusers(java.lang.Integer actusers) {
		this.actusers = actusers;
	}

	public java.lang.Integer getInstallHigh() {
		return installHigh;
	}

	public void setInstallHigh(java.lang.Integer installHigh) {
		this.installHigh = installHigh;
	}

	public java.lang.Integer getInstallMid() {
		return installMid;
	}

	public void setInstallMid(java.lang.Integer installMid) {
		this.installMid = installMid;
	}

	public java.lang.Integer getInstallLow() {
		return installLow;
	}

	public void setInstallLow(java.lang.Integer installLow) {
		this.installLow = installLow;
	}

	public java.lang.Integer getInstallNone() {
		return installNone;
	}

	public void setInstallNone(java.lang.Integer installNone) {
		this.installNone = installNone;
	}

	public java.lang.Integer getShowtotal() {
		return showtotal;
	}

	public void setShowtotal(java.lang.Integer showtotal) {
		this.showtotal = showtotal;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getThirdincome() {
		return thirdincome;
	}

	public void setThirdincome(Double thirdincome) {
		this.thirdincome = thirdincome;
	}

	public java.lang.Integer getHighusers() {
		return highusers;
	}

	public void setHighusers(java.lang.Integer highusers) {
		this.highusers = highusers;
	}

	public java.lang.Integer getMidusers() {
		return midusers;
	}

	public void setMidusers(java.lang.Integer midusers) {
		this.midusers = midusers;
	}

	public java.lang.Integer getLowusers() {
		return lowusers;
	}

	public void setLowusers(java.lang.Integer lowusers) {
		this.lowusers = lowusers;
	}

	public Double getOutcome() {
		return outcome;
	}

	public void setOutcome(Double outcome) {
		this.outcome = outcome;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Integer getCreator() {
		return creator;
	}

	public void setCreator(java.lang.Integer creator) {
		this.creator = creator;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	 
}
 
