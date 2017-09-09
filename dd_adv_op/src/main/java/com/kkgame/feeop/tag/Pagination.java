package com.kkgame.feeop.tag;

import com.kkgame.feeop.base.PkigConstants;

public class Pagination {

	private int totalRow = 0;
	private int pageCount = 0;
	private int index = 0;
	private int pageSize = PkigConstants.PAGE_SIZE;
	private int pageNum = PkigConstants.PAGE_NUM;
	
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
		getPageCount();
		getPageNum();
	}
	public int getPageCount() {
		if(pageSize>0) {
			pageCount = (0==totalRow%pageSize)?totalRow/pageSize:totalRow/pageSize+1;
		}
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getIndex() {
		index = (pageNum>1)?(pageNum-1)*pageSize:0;
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		pageNum = (totalRow>0)?((0 == pageNum)?PkigConstants.PAGE_NUM:pageNum):0;
		if(pageNum>getPageCount()) {
			pageNum = getPageCount();
		}
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
