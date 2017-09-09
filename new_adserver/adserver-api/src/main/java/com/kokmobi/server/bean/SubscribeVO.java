package com.kokmobi.server.bean;

import java.io.Serializable;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/17
 *          Time: 15:25
 * @author: mm
 * @since 3.0
 */
public class SubscribeVO implements Cloneable, Serializable {

    private Integer id;

    private String name;

    private Integer adverId;

    private String redirectUrl;

    private String createTime;

    private Integer status;

    private String notes;

    private Integer advLinkmanId;

    private String operatorId;

    private String cou;

    private Integer versionId;

    private Integer modelId;

    private Integer internet;

    private Integer type;

    private Double manualECPM;

    private Double autoECPM;

    private int ecpmStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAdverId() {
        return adverId;
    }

    public void setAdverId(Integer adverId) {
        this.adverId = adverId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getAdvLinkmanId() {
        return advLinkmanId;
    }

    public void setAdvLinkmanId(Integer advLinkmanId) {
        this.advLinkmanId = advLinkmanId;
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
