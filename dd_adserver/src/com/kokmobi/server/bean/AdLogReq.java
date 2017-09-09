package com.kokmobi.server.bean;

import java.util.Date;
import java.util.List;

public class AdLogReq {
	private String coo_id;
	private String imei;
	private String channelId;
	private String xc_coo_id;
	private String xmodel;
	private String xversion;
	private String ximsi;
	private String xinternet;
	private String xoperator;
	private String xwidth;
	private String xheight;
	private String sdkversion;
	private List<AdLogPackage> infors;
	private String ipaddr;
	private Date cdate;
	private int cou;
	private int countryLevel;
	
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public int getCou() {
		return cou;
	}
	public void setCou(int cou) {
		this.cou = cou;
	}
	public int getCountryLevel() {
		return countryLevel;
	}
	public void setCountryLevel(int countryLevel) {
		this.countryLevel = countryLevel;
	}
	public String getCoo_id() {
		return coo_id;
	}
	public void setCoo_id(String coo_id) {
		this.coo_id = coo_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getXc_coo_id() {
		return xc_coo_id;
	}
	public void setXc_coo_id(String xc_coo_id) {
		this.xc_coo_id = xc_coo_id;
	}
	public String getXmodel() {
		return xmodel;
	}
	public void setXmodel(String xmodel) {
		this.xmodel = xmodel;
	}
	public String getXversion() {
		return xversion;
	}
	public void setXversion(String xversion) {
		this.xversion = xversion;
	}
	public String getXimsi() {
		return ximsi;
	}
	public void setXimsi(String ximsi) {
		this.ximsi = ximsi;
	}
	public String getXinternet() {
		return xinternet;
	}
	public void setXinternet(String xinternet) {
		this.xinternet = xinternet;
	}
	public String getXoperator() {
		return xoperator;
	}
	public void setXoperator(String xoperator) {
		this.xoperator = xoperator;
	}
	public String getXwidth() {
		return xwidth;
	}
	public void setXwidth(String xwidth) {
		this.xwidth = xwidth;
	}
	public String getXheight() {
		return xheight;
	}
	public void setXheight(String xheight) {
		this.xheight = xheight;
	}
	public String getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}
	public List<AdLogPackage> getInfors() {
		return infors;
	}
	public void setInfors(List<AdLogPackage> infors) {
		this.infors = infors;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

}
