package com.kkgame.feeop.base;

import com.kkgame.feeop.tag.Pagination;

public class BasicVO {
	
	private Pagination page = new Pagination();
	
	private int sortIndex;

	public String sortType;
	
	private int isPage;
	
	private int isUpdate;
	
	private String productServer;
	
	private double random;
	
	public String getProductServer() {
		return productServer;
	}

	public void setProductServer(String productServer) {
		this.productServer = productServer;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getSortIndex() {
		return sortIndex < 1 ? 1 : sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getSortType() {
		if (null == sortType || "".equals(sortType)) {
			return "desc";
		}
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public double getRandom() {
		return random;
	}

	public void setRandom(double random) {
		this.random = random;
	}
}
