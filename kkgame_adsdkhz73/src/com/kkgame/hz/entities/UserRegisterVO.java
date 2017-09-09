package com.kkgame.hz.entities;

public class UserRegisterVO {

	private String time;
	
	private int serverId;
	private String table;
	
	private String provinceName;
	
	private String cityName;
	
	private String customerName;
	
	private String projectName;
	
	private String agentName;
	
	private String bdName;
	
	private int downCount;
	
	private int registerCount;
	
	private int registerInCount;
	
	private int adType;
	
	private String adTypeName;
	
	private int registerHighCount;
	
	private int registerLowCount;
	
	private int registerMidCount;
	
	public int getRegisterHighCount() {
		return registerHighCount;
	}

	public void setRegisterHighCount(int registerHighCount) {
		this.registerHighCount = registerHighCount;
	}

	public int getRegisterLowCount() {
		return registerLowCount;
	}

	public void setRegisterLowCount(int registerLowCount) {
		this.registerLowCount = registerLowCount;
	}

	public int getRegisterMidCount() {
		return registerMidCount;
	}

	public void setRegisterMidCount(int registerMidCount) {
		this.registerMidCount = registerMidCount;
	}

	public String getAdTypeName() {
		return adTypeName;
	}

	public void setAdTypeName(String adTypeName) {
		this.adTypeName = adTypeName;
	}

	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	private int firstCount;
	private int secondCount;
	private int thirdCount;
	private int fourthCount;
	private int fifthCount;
	private int sixthCount;
	private int sevenCount;
	
	private double firstPercent;
	private double secondPercent;
	private double thirdPercent;
	private double fourthPercent;
	private double fifthPercent;
	private double sixthPercent;
	private double sevenPercent;
	
	private int customerId;
	
	private int projectId;
	
	private int provinceId;
	
	private int productId;
	
	private String productName;
	
	private int cityId;
	
	private Integer versions;
	
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBdName() {
		return bdName;
	}

	public void setBdName(String bdName) {
		this.bdName = bdName;
	}
	
	public Integer getVersions() {
		return versions == null ? 0 : versions;
	}

	public void setVersions(Integer versions) {
		this.versions = versions;
	}

	public int getRegisterInCount() {
		return registerInCount;
	}

	public void setRegisterInCount(int registerInCount) {
		this.registerInCount = registerInCount;
	}
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getDownCount() {
		return downCount;
	}

	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}

	public int getFirstCount() {
		return firstCount;
	}

	public void setFirstCount(int firstCount) {
		this.firstCount = firstCount;
	}

	public int getSecondCount() {
		return secondCount;
	}

	public void setSecondCount(int secondCount) {
		this.secondCount = secondCount;
	}

	public int getThirdCount() {
		return thirdCount;
	}

	public void setThirdCount(int thirdCount) {
		this.thirdCount = thirdCount;
	}

	public int getFourthCount() {
		return fourthCount;
	}

	public void setFourthCount(int fourthCount) {
		this.fourthCount = fourthCount;
	}

	public int getFifthCount() {
		return fifthCount;
	}

	public void setFifthCount(int fifthCount) {
		this.fifthCount = fifthCount;
	}

	public int getSixthCount() {
		return sixthCount;
	}

	public void setSixthCount(int sixthCount) {
		this.sixthCount = sixthCount;
	}

	public int getSevenCount() {
		return sevenCount;
	}

	public void setSevenCount(int sevenCount) {
		this.sevenCount = sevenCount;
	}

	public double getFirstPercent() {
		return firstPercent;
	}

	public void setFirstPercent(double firstPercent) {
		this.firstPercent = firstPercent;
	}

	public double getSecondPercent() {
		return secondPercent;
	}

	public void setSecondPercent(double secondPercent) {
		this.secondPercent = secondPercent;
	}

	public double getThirdPercent() {
		return thirdPercent;
	}

	public void setThirdPercent(double thirdPercent) {
		this.thirdPercent = thirdPercent;
	}

	public double getFourthPercent() {
		return fourthPercent;
	}

	public void setFourthPercent(double fourthPercent) {
		this.fourthPercent = fourthPercent;
	}

	public double getFifthPercent() {
		return fifthPercent;
	}

	public void setFifthPercent(double fifthPercent) {
		this.fifthPercent = fifthPercent;
	}

	public double getSixthPercent() {
		return sixthPercent;
	}

	public void setSixthPercent(double sixthPercent) {
		this.sixthPercent = sixthPercent;
	}

	public double getSevenPercent() {
		return sevenPercent;
	}

	public void setSevenPercent(double sevenPercent) {
		this.sevenPercent = sevenPercent;
	}
}
