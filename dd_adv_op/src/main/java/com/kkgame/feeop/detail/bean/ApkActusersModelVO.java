package com.kkgame.feeop.detail.bean;
import com.kkgame.feeop.base.BasicVO;

public class ApkActusersModelVO  extends BasicVO {


	//columns START
    /**
     * id       db_column: id
     */
	private java.lang.Integer id;
    /**
     * 产品id       db_column: coo_id
     */
	private java.lang.String cooId;
    /**
     * imei       db_column: imei
     */
	private java.lang.String imei;
    /**
     * 渠道id       db_column: channelid
     */
	private java.lang.String channelid;
    /**
     * 推送/插屏       db_column: sdk
     */
	private java.lang.String sdk;
    /**
     * 创建时间       db_column: cdate
     */
	private String cdate;
    /**
     * sdk版本       db_column: sdkversion
     */
	private java.lang.String sdkversion;
    /**
     * 销量时间       db_column: xdate
     */
	private String xdate;
    /**
     * 插件ID       db_column: xc_coo_id
     */
	private java.lang.String xcCooId;
    /**
     * 机型       db_column: xmodel
     */
	private java.lang.String xmodel;
    /**
     * 系统版本       db_column: xversion
     */
	private java.lang.String xversion;
    /**
     * ximsi       db_column: ximsi
     */
	private java.lang.String ximsi;
    /**
     * 网络类型       db_column: xinternet
     */
	private java.lang.String xinternet;
    /**
     * 运营商       db_column: xoperator
     */
	private java.lang.String xoperator;
    /**
     * 国家       db_column: xcou
     */
	private java.lang.String xcou;
    /**
     * 销量coo_id       db_column: scoo_id
     */
	private java.lang.String scooId;
	//columns END

	private String sdkType;

	private String type;

	private String table;

	private int dataType;

	private int status;

	private int guardPluginId;

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGuardPluginId() {
		return guardPluginId;
	}

	public void setGuardPluginId(int guardPluginId) {
		this.guardPluginId = guardPluginId;
	}

	public String getSdkType() {
		return sdkType;
	}

	public void setSdkType(String sdkType) {
		this.sdkType = sdkType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getId() {
		return this.id;
	}
	public void setCooId(java.lang.String value) {
		this.cooId = value;
	}

	public java.lang.String getCooId() {
		return this.cooId;
	}
	public void setImei(java.lang.String value) {
		this.imei = value;
	}

	public java.lang.String getImei() {
		return this.imei;
	}
	public void setChannelid(java.lang.String value) {
		this.channelid = value;
	}

	public java.lang.String getChannelid() {
		return this.channelid;
	}
	public void setSdk(java.lang.String value) {
		this.sdk = value;
	}

	public java.lang.String getSdk() {
		return this.sdk;
	}
	public void setCdate(String value) {
		this.cdate = value;
	}

	public String getCdate() {
		return this.cdate;
	}
	public void setSdkversion(java.lang.String value) {
		this.sdkversion = value;
	}

	public java.lang.String getSdkversion() {
		return this.sdkversion;
	}
	public void setXdate(String value) {
		this.xdate = value;
	}

	public String getXdate() {
		return this.xdate;
	}
	public void setXcCooId(java.lang.String value) {
		this.xcCooId = value;
	}

	public java.lang.String getXcCooId() {
		return this.xcCooId;
	}
	public void setXmodel(java.lang.String value) {
		this.xmodel = value;
	}

	public java.lang.String getXmodel() {
		return this.xmodel;
	}
	public void setXversion(java.lang.String value) {
		this.xversion = value;
	}

	public java.lang.String getXversion() {
		return this.xversion;
	}
	public void setXimsi(java.lang.String value) {
		this.ximsi = value;
	}

	public java.lang.String getXimsi() {
		return this.ximsi;
	}
	public void setXinternet(java.lang.String value) {
		this.xinternet = value;
	}

	public java.lang.String getXinternet() {
		return this.xinternet;
	}
	public void setXoperator(java.lang.String value) {
		this.xoperator = value;
	}

	public java.lang.String getXoperator() {
		return this.xoperator;
	}
	public void setXcou(java.lang.String value) {
		this.xcou = value;
	}

	public java.lang.String getXcou() {
		return this.xcou;
	}
	public void setScooId(java.lang.String value) {
		this.scooId = value;
	}

	public java.lang.String getScooId() {
		return this.scooId;
	}



}
 
