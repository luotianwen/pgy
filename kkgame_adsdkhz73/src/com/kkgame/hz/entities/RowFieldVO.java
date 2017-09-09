package com.kkgame.hz.entities;

public class RowFieldVO {

	/**
	 * 0: 不显示，1：显示
	 */
	private int isShowDate = 0;
	private int isShowCustomer = 0;
	private int isShowProject = 0;
	private int isShowCp = 0;
	private int isShowGame = 0;
	private int isShowProvince= 0;
	private int isShowCity= 0;
	private int isShowAgent = 0;
	private int isShowBd = 0;
	private int isShowProduct = 0;
	private int isShowAdType = 0;
	
	public static final int ROW_FIELD_DATE = 1;
	public static final int ROW_FIELD_AGENT = 2;
	public static final int ROW_FIELD_BD = 3;
	public static final int ROW_FIELD_CUSTOMER = 4;
	public static final int ROW_FIELD_PROJECT = 5;
	public static final int ROW_FIELD_CP = 6;
	public static final int ROW_FIELD_GAME = 7;
	public static final int ROW_FIELD_PROVINCE = 8;
	public static final int ROW_FIELD_CITY = 9;
	public static final int ROW_FIELD_PRODUCT = 10;
	public static final int ROW_FIELD_ADTYPE = 11;
	
	public void setShowRowField(String[] rowFields){
		for (String rowField : rowFields) {
			int rowValue = Integer.valueOf(rowField);
			if (rowValue == RowFieldVO.ROW_FIELD_DATE) {
				isShowDate = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_AGENT) {
				isShowAgent = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_BD) {
				isShowBd = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_CUSTOMER) {
				isShowCustomer = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_PROJECT) {
				isShowProject = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_CP) {
				isShowCp = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_GAME) {
				isShowGame = 1;
			}else if (rowValue == RowFieldVO.ROW_FIELD_PROVINCE) {
				isShowProvince = 1;
			}else if(rowValue == RowFieldVO.ROW_FIELD_CITY) {
				isShowCity = 1;
			}else if(rowValue == RowFieldVO.ROW_FIELD_PRODUCT) {
				isShowProduct = 1;
			}else if(rowValue == RowFieldVO.ROW_FIELD_ADTYPE) {
				isShowAdType = 1;
			}
		}
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
	public int getIsShowProject() {
		return isShowProject;
	}
	public void setIsShowProject(int isShowProject) {
		this.isShowProject = isShowProject;
	}
	public int getIsShowCp() {
		return isShowCp;
	}
	public void setIsShowCp(int isShowCp) {
		this.isShowCp = isShowCp;
	}
	public int getIsShowGame() {
		return isShowGame;
	}
	public void setIsShowGame(int isShowGame) {
		this.isShowGame = isShowGame;
	}

	public int getIsShowProvince() {
		return isShowProvince;
	}

	public void setIsShowProvince(int isShowProvince) {
		this.isShowProvince = isShowProvince;
	}

	public int getIsShowCity() {
		return isShowCity;
	}

	public void setIsShowCity(int isShowCity) {
		this.isShowCity = isShowCity;
	}

	public int getIsShowAgent() {
		return isShowAgent;
	}

	public void setIsShowAgent(int isShowAgent) {
		this.isShowAgent = isShowAgent;
	}

	public int getIsShowBd() {
		return isShowBd;
	}

	public void setIsShowBd(int isShowBd) {
		this.isShowBd = isShowBd;
	}

	public int getIsShowProduct() {
		return isShowProduct;
	}

	public void setIsShowProduct(int isShowProduct) {
		this.isShowProduct = isShowProduct;
	}

	public int getIsShowAdType() {
		return isShowAdType;
	}

	public void setIsShowAdType(int isShowAdType) {
		this.isShowAdType = isShowAdType;
	}
	
	
}
