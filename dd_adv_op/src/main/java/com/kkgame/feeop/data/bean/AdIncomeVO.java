package com.kkgame.feeop.data.bean;

public class AdIncomeVO {

	private String statDate;
	
	private int adId;
	
	private String adName;
	
	private int installedCount;
	
	private int silenceInstalledCount;
	
	private int installTotal;
	
	private int showTotal;
	
	private int silenceShowTotal;
	
	private int realTotal;
	
	private int inputTotal;
	
	private double price;
	
	private double income;
	
	private double cpm;
	
	private double scpm;
	
	private double expectCpm;
	
	private double expectScpm;
	
	private double percent;
	
	private double cpConversionRate;

	public double getExpectCpm() {
		return expectCpm;
	}

	public void setExpectCpm(double expectCpm) {
		this.expectCpm = expectCpm;
	}

	public double getExpectScpm() {
		return expectScpm;
	}

	public void setExpectScpm(double expectScpm) {
		this.expectScpm = expectScpm;
	}

	public double getCpConversionRate() {
		return cpConversionRate;
	}

	public void setCpConversionRate(double cpConversionRate) {
		this.cpConversionRate = cpConversionRate;
	}

	public int getInstalledCount() {
		return installedCount;
	}

	public void setInstalledCount(int installedCount) {
		this.installedCount = installedCount;
	}

	public int getSilenceInstalledCount() {
		return silenceInstalledCount;
	}

	public void setSilenceInstalledCount(int silenceInstalledCount) {
		this.silenceInstalledCount = silenceInstalledCount;
	}

	public int getSilenceShowTotal() {
		return silenceShowTotal;
	}

	public void setSilenceShowTotal(int silenceShowTotal) {
		this.silenceShowTotal = silenceShowTotal;
	}

	public double getScpm() {
		return scpm;
	}

	public void setScpm(double scpm) {
		this.scpm = scpm;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public int getInstallTotal() {
		return installTotal;
	}

	public void setInstallTotal(int installTotal) {
		this.installTotal = installTotal;
	}

	public int getShowTotal() {
		return showTotal;
	}

	public void setShowTotal(int showTotal) {
		this.showTotal = showTotal;
	}

	public int getRealTotal() {
		return realTotal;
	}

	public void setRealTotal(int realTotal) {
		this.realTotal = realTotal;
	}

	public int getInputTotal() {
		return inputTotal;
	}

	public void setInputTotal(int inputTotal) {
		this.inputTotal = inputTotal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getCpm() {
		return cpm;
	}

	public void setCpm(double cpm) {
		this.cpm = cpm;
	}
}
