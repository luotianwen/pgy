package com.kkgame.feeop.user.bean;

import java.util.List;

import com.kkgame.feeop.base.BasicVO;

public class UserVO extends BasicVO{
	private int id;//用户ID
	
	private String loginId;
	
	private String passwd;//密码
	
	private String realName;//真实姓名
	
	private int type;//用户类型
	
	private String userType;
	
	private int status;//用户见账号状态
	
	private String createTime;//创建时间
	
	private String lastLogin;//最后登陆时间
	
	private int projectGroupId;
		
	private int isCustomer;
	
	private int isBd;
	
	private int customerId;
	
	private int agentId;
	
	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProjectGroupId() {
		return projectGroupId;
	}

	public void setProjectGroupId(int projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(int isCustomer) {
		this.isCustomer = isCustomer;
	}

	public int getIsBd() {
		return isBd;
	}

	public void setIsBd(int isBd) {
		this.isBd = isBd;
	}
}
