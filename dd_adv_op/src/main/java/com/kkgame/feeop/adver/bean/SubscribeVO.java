package com.kkgame.feeop.adver.bean;

import com.kkgame.feeop.base.BasicVO;

import java.io.Serializable;

public class SubscribeVO extends BasicVO implements Cloneable, Serializable {


    //columns START
    /**
     * id       db_column: ID
     */
    private Integer id;
    /**
     * name       db_column: Name
     */
    private String name;
    /**
     * adverId       db_column: AdverId
     */
    private Integer adverId;
    /**
     * redirectUrl       db_column: RedirectUrl
     */
    private String redirectUrl;
    /**
     * createTime       db_column: CreateTime
     */
    private String createTime;
    /**
     * status       db_column: Status
     */
    private Integer status;
    /**
     * notes       db_column: Notes
     */
    private String notes;
    /**
     * advLinkmanId       db_column: advLinkmanId
     */
    private Integer advLinkmanId;

    private String operatorId;

    private String cou;

    private Integer versionId;

    private Integer modelId;

    private Integer internet;

    private Integer type;
    private String param1;

    private String param2;

    private Double manualECPM;

    private Double autoECPM;

    private Double price;

    private int ecpmStatus;
    private Double ctr;

    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setAdverId(Integer value) {
        this.adverId = value;
    }

    public Integer getAdverId() {
        return this.adverId;
    }

    public void setRedirectUrl(String value) {
        this.redirectUrl = value;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setCreateTime(String value) {
        this.createTime = value;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setNotes(String value) {
        this.notes = value;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setAdvLinkmanId(Integer value) {
        this.advLinkmanId = value;
    }

    public Integer getAdvLinkmanId() {
        return this.advLinkmanId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getInternet() {
        return internet;
    }

    public void setInternet(Integer internet) {
        this.internet = internet;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public Double getManualECPM() {
        return manualECPM;
    }

    public void setManualECPM(Double manualECPM) {
        this.manualECPM = manualECPM;
    }

    public Double getAutoECPM() {
        return autoECPM;
    }

    public void setAutoECPM(Double autoECPM) {
        this.autoECPM = autoECPM;
    }

    public int getEcpmStatus() {
        return ecpmStatus;
    }

    public void setEcpmStatus(int ecpmStatus) {
        this.ecpmStatus = ecpmStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
 
