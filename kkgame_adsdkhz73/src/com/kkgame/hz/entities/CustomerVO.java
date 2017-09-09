package com.kkgame.hz.entities;

import com.kkgame.hz.base.PkigConstants;

public class CustomerVO extends PortalUserVO {

	private int id;
	private String name;
	private String city;
	private int businessDeveloperId;
	private int status;
	private String submitTime;
	private String confirmTime;
	private String abandonTime;
	private String signupTime;
	private String CallBackTime;
	private String description;
	private String businessDeveloperName;
	private int portalUserId;
	private String agentName;
	private int newStatus;
	private int agentId;
	private String auditFailReason;
	private String callBackReason;
	private String abandonReason;
	private int relayBdId;
	private int relayStatus;
	private String contactPerson;
	private String contactInfo;
	private int method;
	private int query;
	private Integer salesViewPermit;
	/**
	 * 是否为客户的主人 即该客户是否属于自己 Y 是 N 否
	 */
	private String isMaster;
	private String relayBdName;
	/** 审核通过的日期离今天的天数 */
	private int leftDays;
	/** isMyAll 标示自有客户，'Y':是，'N'：不是 */
	private String isMyAll;
	/** bd 拓展级别 */
	private int bdLevel;
	/** agent 拓展级别 */
	private int agentLevel;
	/** 客户流转状态， 跳转页面用 */
	private int flowStatus;
	/** 拓展日期： 拥有客户拓展权的天数 */
	private int expiryDate;
	/** 收回天数,客户被收回后，原BD 30天不能申请 */
	private int callBackDays;
	/** 查询的商务ID */
	private int searchBdId;
	private String levelName;

	private String typeName;
	private int cooperateType;

	public int getCooperateType() {
		return cooperateType;
	}

	public void setCooperateType(int cooperateType) {
		this.cooperateType = cooperateType;
	}

	public int getSearchBdId() {
		return searchBdId;
	}

	public void setSearchBdId(int searchBdId) {
		this.searchBdId = searchBdId;
	}

	public int getCallBackDays() {
		return callBackDays;
	}

	public void setCallBackDays(int callBackDays) {
		this.callBackDays = callBackDays;
	}

	public int getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(int flowStatus) {
		this.flowStatus = flowStatus;
	}

	public int getBdLevel() {
		return bdLevel;
	}

	public void setBdLevel(int bdLevel) {
		this.bdLevel = bdLevel;
	}

	public int getAgentLevel() {
		return agentLevel;
	}

	public void setAgentLevel(int agentLevel) {
		this.agentLevel = agentLevel;
	}

	public String getIsMyAll() {
		return isMyAll;
	}

	public void setIsMyAll(String isMyAll) {
		this.isMyAll = isMyAll;
	}
	public int getLeftDays() {
		return leftDays;
	}

	public void setLeftDays(int leftDays) {
		this.leftDays = leftDays;
	}

	public String getRelayBdName() {
		return relayBdName;
	}

	public void setRelayBdName(String relayBdName) {
		this.relayBdName = relayBdName;
	}

	public String getAbandonReason() {
		return abandonReason;
	}

	public void setAbandonReason(String abandonReason) {
		this.abandonReason = abandonReason;
	}

	public int getRelayBdId() {
		return relayBdId;
	}

	public void setRelayBdId(int relayBdId) {
		this.relayBdId = relayBdId;
	}

	public int getRelayStatus() {
		return relayStatus;
	}

	public void setRelayStatus(int relayStatus) {
		this.relayStatus = relayStatus;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}
	
	public String getAuditFailReason() {
		return auditFailReason;
	}

	public void setAuditFailReason(String auditFailReason) {
		this.auditFailReason = auditFailReason;
	}

	public String getCallBackReason() {
		return callBackReason;
	}

	public void setCallBackReason(String callBackReason) {
		this.callBackReason = callBackReason;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getBusinessDeveloperName() {
		return businessDeveloperName;
	}

	public void setBusinessDeveloperName(String businessDeveloperName) {
		this.businessDeveloperName = businessDeveloperName;
	}

	public int getPortalUserId() {
		return portalUserId;
	}

	public void setPortalUserId(int portalUserId) {
		this.portalUserId = portalUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public int getBusinessDeveloperId() {
		return businessDeveloperId;
	}

	public void setBusinessDeveloperId(int businessDeveloperId) {
		this.businessDeveloperId = businessDeveloperId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getAbandonTime() {
		return abandonTime;
	}

	public void setAbandonTime(String abandonTime) {
		this.abandonTime = abandonTime;
	}

	public String getSignupTime() {
		return signupTime;
	}

	public void setSignupTime(String signupTime) {
		this.signupTime = signupTime;
	}

	public String getCallBackTime() {
		return CallBackTime;
	}

	public void setCallBackTime(String callBackTime) {
		CallBackTime = callBackTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusDisplay() {
		String s = "";

		if (status == PkigConstants.CUSTOMER_STATUS_NEW) {
			s = "新建";
		} else if (status == PkigConstants.CUSTOMER_STATUS_WAIT_CONFIRM) {
			s = "待审";
		} else if (status == PkigConstants.CUSTOMER_STATUS_DEVING) {
			s = "拓展中";
		} else if (status == PkigConstants.CUSTOMER_STATUS_NO_PASS) {
			s = "审核不通过";
		} else if (status == PkigConstants.CUSTOMER_STATUS_SIGN_UP) {
			s = "已签约";
		} else if (status == PkigConstants.CUSTOMER_STATUS_ABANDON) {
			s = "代理放弃";
		} else if (status == PkigConstants.CUSTOMER_STATUS_CALL_BACK) {
			s = "收回拓展权";
		} else if (status == PkigConstants.CUSTOMER_STATUS_OVERDUE) {
			s = "已过期";
		} else if (status == PkigConstants.CUSTOMER_STATUS_DEFERRED) {
			s = "延期";
		} else if (status == PkigConstants.CUSTOMER_STATUS_IDLE) {
			s = "闲置";
		} else {
			s = "";
		}

		return s;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public int getQuery() {
		return query;
	}

	public void setQuery(int query) {
		this.query = query;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Integer getSalesViewPermit() {
		return salesViewPermit == null ? 0 : salesViewPermit;
	}

	public void setSalesViewPermit(Integer salesViewPermit) {
		this.salesViewPermit = salesViewPermit;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return sb.append("ID: ").append(this.id).append(" name: ").append(this.name)
			.append(" status: ").append(this.status).append(" bdId: ").append(this.businessDeveloperId)
			.append(" relayId: ").append(this.relayBdId).append(" relayStatus").append(this.relayStatus).toString();
	}

}
