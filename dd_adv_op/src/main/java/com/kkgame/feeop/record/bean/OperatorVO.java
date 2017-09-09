package com.kkgame.feeop.record.bean;

import com.kkgame.feeop.base.BasicVO;

public class OperatorVO extends BasicVO{

	private int operatorId;

	private int code;

	private String operatorName;

	private int cou;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}
}
