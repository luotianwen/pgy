package com.kkgame.feeop.data.bean;

/**
 * 查询VO
 * @author rayi
 *
 */
public class SearchVO {
			
	private int queryType;
	
	private String yearMonth;
	
	private String startMonth;
	
	private String endMonth;
	
	private String startDate;
	
	private String endDate;
	
	private int type;
	
	private int spId;
	
	private int channelId;
				
	private int productId;
	
	private int serviceId;
				
	private int customerId;
	
	private int agentId;
	
	private int operator;
	
	private int codeMode;
	
	private int reportStatus;  //状态报告
	
	private int province; //省份
	
	private int provinceId; //省份
		
	private String table;//表名
	
	private int serviceType;
	
	private int serviceRealType;
	
	private String customChannelId;
	
	private int sdkId;
	
	private int price;
					
	private int rowFieldLen;//显示多少列
	
	private String[] rowFields;
	
	private String rowFieldString;
	
	private String month;
	
	private int bdId;
	
	private int projectId;
	
	private String startTime;
	private String endTime;
	
	private int guardPluginId;
	
	private int country;
	
	private String adId;
	
	private String version;

	private String platformId;

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public int getGuardPluginId() {
		return guardPluginId;
	}

	public void setGuardPluginId(int guardPluginId) {
		this.guardPluginId = guardPluginId;
	}

	private RowFieldVO rowFieldVO = new RowFieldVO();

	public RowFieldVO getRowFieldVO() {
		return rowFieldVO;
	}

	public void setRowFieldVO(RowFieldVO rowFieldVO) {
		this.rowFieldVO = rowFieldVO;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getRowFieldLen() {
		return rowFields.length;
	}

	public void setRowFieldLen(int rowFieldLen) {
		this.rowFieldLen = rowFields.length;
	}

	public String[] getRowFields() {
		return rowFields;
	}

	public void setRowFields(String[] rowFields) {
		this.rowFields = rowFields;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}
	
	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRowFieldString() {
		return rowFieldString;
	}

	public void setRowFieldString(String rowFieldString) {
		this.rowFieldString = rowFieldString;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getCodeMode() {
		return codeMode;
	}

	public void setCodeMode(int codeMode) {
		this.codeMode = codeMode;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public int getServiceRealType() {
		return serviceRealType;
	}

	public void setServiceRealType(int serviceRealType) {
		this.serviceRealType = serviceRealType;
	}

	public String getCustomChannelId() {
		return customChannelId;
	}

	public void setCustomChannelId(String customChannelId) {
		this.customChannelId = customChannelId;
	}

	public int getSdkId() {
		return sdkId;
	}

	public void setSdkId(int sdkId) {
		this.sdkId = sdkId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getBdId() {
		return bdId;
	}

	public void setBdId(int bdId) {
		this.bdId = bdId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
