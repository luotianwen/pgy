package com.kkgame.hz.entities;

/**
 * 查询VO
 * @author rayi
 *
 */
public class BillSearchVO {
	
	private String searchMonth;
	
	private String searchDay;
	
	private String startTime;
	
	private String endTime;
	
	private int orderMode;//1：订购 2：取消
	
	private int operate;//用户好友1：增加 2：删除
	
	private int agentId;
	
	private int bdId;
	
	private int bhId;
	
	private int queryType;
	
	private int provinceId;
	
	private int cityId;
	
	private int newCustomerId;
	
	private int customerId;
	
	private int projectId;
	
	private int middlerId;
	
	private int programerId;
	
	private int providerId;
	
	private int gameId;
	
	private int productId;
	
	private int indexType;
	
	private String table;//表名
	
	private String jointable;//联接表名
	
	private String userName;
	
	private String passwd;
	
	private int adType;
	
	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	private int rowFieldLen;//显示多少列
	
	private String[] rowFields;
	
	private String rowFieldString;
	
	private int status;//订购状态
	
	private int operator;
	
	private int searchVersions;
	
	private RowFieldVO rowFieldVO = new RowFieldVO();

	public RowFieldVO getRowFieldVO() {
		return rowFieldVO;
	}

	public void setRowFieldVO(RowFieldVO rowFieldVO) {
		this.rowFieldVO = rowFieldVO;
	}

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
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

	public int getNewCustomerId() {
		return newCustomerId;
	}

	public void setNewCustomerId(int newCustomerId) {
		this.newCustomerId = newCustomerId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getIndexType() {
		return indexType;
	}

	public void setIndexType(int indexType) {
		this.indexType = indexType;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public String getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}

	public String getJointable() {
		return jointable;
	}

	public void setJointable(String jointable) {
		this.jointable = jointable;
	}

	public int getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(int orderMode) {
		this.orderMode = orderMode;
	}

	public int getOperate() {
		return operate;
	}

	public void setOperate(int operate) {
		this.operate = operate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getBdId() {
		return bdId;
	}

	public void setBdId(int bdId) {
		this.bdId = bdId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getSearchVersions() {
		return searchVersions;
	}

	public void setSearchVersions(int searchVersions) {
		this.searchVersions = searchVersions;
	}

	public int getMiddlerId() {
		return middlerId;
	}

	public void setMiddlerId(int middlerId) {
		this.middlerId = middlerId;
	}

	public int getProgramerId() {
		return programerId;
	}

	public void setProgramerId(int programerId) {
		this.programerId = programerId;
	}

	public int getBhId() {
		return bhId;
	}

	public void setBhId(int bhId) {
		this.bhId = bhId;
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
	
}
