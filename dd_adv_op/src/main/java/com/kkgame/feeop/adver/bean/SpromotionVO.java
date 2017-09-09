package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO;

public class SpromotionVO extends BasicVO {
	private int id;
	private int cooId;
	private String promotionLink;
	private int type;
	private String createTime;
	private String notes;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCooId() {
		return cooId;
	}

	public void setCooId(int cooId) {
		this.cooId = cooId;
	}

	public String getPromotionLink() {
		return promotionLink;
	}

	public void setPromotionLink(String promotionLink) {
		this.promotionLink = promotionLink;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
 
