package com.kkgame.feeop.data.bean;

public class RowFieldVO {


	/**
	 * 0: 不显示，1：显示
	 */
	private int isShowDate = 0;
	private int isShowCustomer = 0;
	private int isShowProduct = 0;
	private int isShowSp = 0;
	private int isShowChannel = 0;
	private int isShowOperator = 0;
	private int isShowCodeMode = 0;
	private int isShowService = 0;
	private int isShowAgent = 0;
	private int isShowProvince = 0;
	private int isShowReportStatus = 0;
	private int isShowServiceType = 0;
	private int isShowServiceRealType = 0;
	private int isShowCustomChannel = 0;
	private int isShowSdk = 0;
	private int isShowPrice = 0;
	private int isShowType = 0;
	private int isShowProject = 0;
	private int isShowCountry = 0;
	private int isShowBd = 0;
	private int isShowAd = 0;
	private int isShowHour = 0;
	private int isShowVersion =0;
	private int isShowInternet =0;
	private int isShowPlatform =0;
	public static final int ROW_FIELD_DATE = 1;
	public static final int ROW_FIELD_CUSTOMER = 2;
	public static final int ROW_FIELD_PRODUCT = 3;
	public static final int ROW_FIELD_SP = 4;
	public static final int ROW_FIELD_CHANNEL = 5;
	public static final int ROW_FIELD_OPERATOR = 6;
	public static final int ROW_FIELD_CODEMODE = 7;
	public static final int ROW_FIELD_SERVICE=8;
	public static final int ROW_FIELD_AGENT=9;
	public static final int ROW_FIELD_PROVINCE=10;
	public static final int ROW_FIELD_REPORTSTATUS=11;
	public static final int ROW_FIELD_SERVICETYPE=12;
	public static final int ROW_FIELD_SERVICEREALTYPE=13;
	public static final int ROW_FIELD_CUSTOMCHANNEL=14;
	public static final int ROW_FIELD_SDK=15;
	public static final int ROW_FIELD_PRICE=16;
	public static final int ROW_FIELD_TYPE=17;
	public static final int ROW_FIELD_PROJECT=18;
	public static final int ROW_FIELD_COUNTRY=19;
	public static final int ROW_FIELD_BD=20;
	public static final int ROW_FIELD_AD=21;
	public static final int ROW_FIELD_HOUR=22;
	public static final int ROW_FIELD_VERSION=23;
	public static final int ROW_FIELD_INTERNET=24;
	public static final int ROW_FIELD_PLATFORM = 25;
	public void setShowRowField(String[] rowFields){
		for (String rowField : rowFields) {
			int rowValue = Integer.valueOf(rowField);
			if (rowValue == RowFieldVO.ROW_FIELD_DATE) {
				isShowDate = 1;
			} else if (rowValue == RowFieldVO.ROW_FIELD_CUSTOMER) {
				isShowCustomer = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_PRODUCT) {
				isShowProduct = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_SP) {
				isShowSp = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_CHANNEL) {
				isShowChannel = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_OPERATOR) {
				isShowOperator = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_CODEMODE) {
				isShowCodeMode = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_SERVICE) {
				isShowService = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_AGENT) {
				isShowAgent = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_PROVINCE) {
				isShowProvince = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_REPORTSTATUS) {
				isShowReportStatus = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_SERVICETYPE) {
				isShowServiceType = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_SERVICEREALTYPE) {
				isShowServiceRealType = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_CUSTOMCHANNEL) {
				isShowCustomChannel = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_SDK) {
				isShowSdk = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_PRICE) {
				isShowPrice = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_BD) {
				isShowBd = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_COUNTRY) {
				isShowCountry = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_PROJECT) {
				isShowProject = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_TYPE) {
				isShowType = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_AD) {
				isShowAd = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_HOUR) {
				isShowHour = 1;
			}  else if(rowValue == RowFieldVO.ROW_FIELD_VERSION) {
				isShowVersion = 1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_INTERNET) {
				isShowInternet=1;
			} else if(rowValue == RowFieldVO.ROW_FIELD_PLATFORM){
				isShowPlatform = 1;
			}
		}
	}

	public int getIsShowPlatform() {
		return isShowPlatform;
	}

	public void setIsShowPlatform(int isShowPlatform) {
		this.isShowPlatform = isShowPlatform;
	}

	public int getIsShowDate() {
		return isShowDate;
	}
	
	public void setIsShowDate(int isShowDate) {
		this.isShowDate = isShowDate;
	}
	
	public int getIsShowCustomer() {
		return isShowCustomer;
	}

	public void setIsShowCustomer(int isShowCustomer) {
		this.isShowCustomer = isShowCustomer;
	}

	public int getIsShowProduct() {
		return isShowProduct;
	}

	public void setIsShowProduct(int isShowProduct) {
		this.isShowProduct = isShowProduct;
	}

	public int getIsShowSp() {
		return isShowSp;
	}

	public void setIsShowSp(int isShowSp) {
		this.isShowSp = isShowSp;
	}

	public int getIsShowChannel() {
		return isShowChannel;
	}

	public void setIsShowChannel(int isShowChannel) {
		this.isShowChannel = isShowChannel;
	}

	public int getIsShowOperator() {
		return isShowOperator;
	}

	public void setIsShowOperator(int isShowOperator) {
		this.isShowOperator = isShowOperator;
	}

	public int getIsShowCodeMode() {
		return isShowCodeMode;
	}

	public void setIsShowCodeMode(int isShowCodeMode) {
		this.isShowCodeMode = isShowCodeMode;
	}

	public int getIsShowService() {
		return isShowService;
	}

	public void setIsShowService(int isShowService) {
		this.isShowService = isShowService;
	}

	public int getIsShowAgent() {
		return isShowAgent;
	}

	public void setIsShowAgent(int isShowAgent) {
		this.isShowAgent = isShowAgent;
	}

	public int getIsShowProvince() {
		return isShowProvince;
	}

	public void setIsShowProvince(int isShowProvince) {
		this.isShowProvince = isShowProvince;
	}

	public int getIsShowReportStatus() {
		return isShowReportStatus;
	}

	public void setIsShowReportStatus(int isShowReportStatus) {
		this.isShowReportStatus = isShowReportStatus;
	}

	public int getIsShowServiceType() {
		return isShowServiceType;
	}

	public void setIsShowServiceType(int isShowServiceType) {
		this.isShowServiceType = isShowServiceType;
	}

	public int getIsShowServiceRealType() {
		return isShowServiceRealType;
	}

	public void setIsShowServiceRealType(int isShowServiceRealType) {
		this.isShowServiceRealType = isShowServiceRealType;
	}

	public int getIsShowCustomChannel() {
		return isShowCustomChannel;
	}

	public void setIsShowCustomChannel(int isShowCustomChannel) {
		this.isShowCustomChannel = isShowCustomChannel;
	}

	public int getIsShowSdk() {
		return isShowSdk;
	}

	public void setIsShowSdk(int isShowSdk) {
		this.isShowSdk = isShowSdk;
	}

	public int getIsShowPrice() {
		return isShowPrice;
	}

	public void setIsShowPrice(int isShowPrice) {
		this.isShowPrice = isShowPrice;
	}

	public int getIsShowType() {
		return isShowType;
	}

	public void setIsShowType(int isShowType) {
		this.isShowType = isShowType;
	}

	public int getIsShowProject() {
		return isShowProject;
	}

	public void setIsShowProject(int isShowProject) {
		this.isShowProject = isShowProject;
	}

	public int getIsShowCountry() {
		return isShowCountry;
	}

	public void setIsShowCountry(int isShowCountry) {
		this.isShowCountry = isShowCountry;
	}

	public int getIsShowBd() {
		return isShowBd;
	}

	public void setIsShowBd(int isShowBd) {
		this.isShowBd = isShowBd;
	}

	public int getIsShowAd() {
		return isShowAd;
	}

	public void setIsShowAd(int isShowAd) {
		this.isShowAd = isShowAd;
	}

	public int getIsShowHour() {
		return isShowHour;
	}

	public void setIsShowHour(int isShowHour) {
		this.isShowHour = isShowHour;
	}

	public int getIsShowVersion() {
		return isShowVersion;
	}

	public void setIsShowVersion(int isShowVersion) {
		this.isShowVersion = isShowVersion;
	}

	public int getIsShowInternet() {
		return isShowInternet;
	}

	public void setIsShowInternet(int isShowInternet) {
		this.isShowInternet = isShowInternet;
	}
}
