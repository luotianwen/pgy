package com.kkgame.feeop.customer.bean;

import java.util.List;

import com.kkgame.feeop.base.BasicVO;


public class ProjectVO extends BasicVO {

	private int id;
	
	private String name;
	
	private int customerId;//客户
	
	private int bdId;//商务
	
	private int agentId;//代理商
	
	private String customerName;
	
	private String bdName;
	
	private String agentName;
	
	private int status;//状态 1.新建项目,2待技术审核,3.待出包,4.已出包,5.测试通过,6技术参数错误
	
	private int screenId;//分辨率
	
	private String screenName;
	
	private int osId;//平台
	
	private String osName;
	
	private int isRemoveLogo;//是否去掉LOGO
	
	private String price;//预计价格
	
	private String intro;//备注
	
	private String createTime;//创建时间
	
	private String schemeName;//方案商名称
	
	private String submitTime;//提交审核时间
	
	private String confirmTime;//审核时间
	
	private String confirmText;
	
	private String packageTime;//出包时间
	
	private String testTime;//测试时间
	
	private String testText;
	
	private int cooperateType;//合作模式
	
	private int populariseType;//推广方式
	
	private int priceStatus;//1.待价格审核2.价格未确认3.审核通过4.审核未通过
	
	private String priceText;
	
	private String roleType;
	
	private String productIds;
	
	private List<ProductVO> productVOList;
	
	private int bhId;
	
	private String bhName;
	
	private int opType;//操作类型1.新增，2.修改
	
	private int queryType;
	
	private String oldIntro;
	
	private int isPage;
	
	private int big;
	
	private String gameName;
	
	private String feeName;

	private double rateHigh;//预计价格
	private double rateMid;//预计价格
	private double rateLow;//预计价格
	private double minIncomeRate;//预计价格
 
	public double getRateHigh() {
		return rateHigh;
	}

	public void setRateHigh(double rateHigh) {
		this.rateHigh = rateHigh;
	}

	public double getRateMid() {
		return rateMid;
	}

	public void setRateMid(double rateMid) {
		this.rateMid = rateMid;
	}

	public double getRateLow() {
		return rateLow;
	}

	public void setRateLow(double rateLow) {
		this.rateLow = rateLow;
	}

	public double getMinIncomeRate() {
		return minIncomeRate;
	}

	public void setMinIncomeRate(double minIncomeRate) {
		this.minIncomeRate = minIncomeRate;
	}

	public int getBig() {
		return big;
	}

	public void setBig(int big) {
		this.big = big;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public String getOldIntro() {
		return oldIntro;
	}

	public void setOldIntro(String oldIntro) {
		this.oldIntro = oldIntro;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public List<ProductVO> getProductVOList() {
		return productVOList;
	}

	public void setProductVOList(List<ProductVO> productVOList) {
		this.productVOList = productVOList;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBdId() {
		return bdId;
	}

	public void setBdId(int bdId) {
		this.bdId = bdId;
	}

	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBdName() {
		return bdName;
	}

	public void setBdName(String bdName) {
		this.bdName = bdName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getOsId() {
		return osId;
	}

	public void setOsId(int osId) {
		this.osId = osId;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public int getIsRemoveLogo() {
		return isRemoveLogo;
	}

	public void setIsRemoveLogo(int isRemoveLogo) {
		this.isRemoveLogo = isRemoveLogo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
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

	public String getPackageTime() {
		return packageTime;
	}

	public void setPackageTime(String packageTime) {
		this.packageTime = packageTime;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public int getPriceStatus() {
		return priceStatus;
	}

	public void setPriceStatus(int priceStatus) {
		this.priceStatus = priceStatus;
	}

	public int getOpType() {
		return opType;
	}

	public void setOpType(int opType) {
		this.opType = opType;
	}

	public int getBhId() {
		return bhId;
	}

	public void setBhId(int bhId) {
		this.bhId = bhId;
	}

	public String getBhName() {
		return bhName;
	}

	public void setBhName(String bhName) {
		this.bhName = bhName;
	}

	public String getConfirmText() {
		return confirmText;
	}

	public void setConfirmText(String confirmText) {
		this.confirmText = confirmText;
	}

	public String getTestText() {
		return testText;
	}

	public void setTestText(String testText) {
		this.testText = testText;
	}

	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}

	public int getCooperateType() {
		return cooperateType;
	}

	public void setCooperateType(int cooperateType) {
		this.cooperateType = cooperateType;
	}

	public int getPopulariseType() {
		return populariseType;
	}

	public void setPopulariseType(int populariseType) {
		this.populariseType = populariseType;
	}
}
