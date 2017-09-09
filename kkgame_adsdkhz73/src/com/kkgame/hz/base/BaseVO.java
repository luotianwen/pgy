package com.kkgame.hz.base;

import com.kkgame.hz.tag.Pagination;

public class BaseVO {
	
	private int sortIndex;

	private int errorId ;
	
	private String errorMsg;

	public String sortItem;

	public String sortType;

	private Pagination page = new Pagination();
	
	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getSortIndex() {
		return sortIndex < 1 ? 1 : sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
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

}