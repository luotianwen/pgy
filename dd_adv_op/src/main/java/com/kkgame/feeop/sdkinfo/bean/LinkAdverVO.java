package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

import java.util.Date;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/21
 *          Time: 17:05
 * @author: mm
 * @since 3.0
 */
public class LinkAdverVO extends BasicVO {
    private int id;
    private String name;
    private int advLinkmanId;
    private String redirectUrl;
    private int adverId;
    private int status;
    private int clickType;
    private int cap;
    private int cpm;
    private String notes;
    private String extensionContry;
    private String iconUrl;
    private String createTime;

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

    public int getAdvLinkmanId() {
        return advLinkmanId;
    }

    public void setAdvLinkmanId(int advLinkmanId) {
        this.advLinkmanId = advLinkmanId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getAdverId() {
        return adverId;
    }

    public void setAdverId(int adverId) {
        this.adverId = adverId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClickType() {
        return clickType;
    }

    public void setClickType(int clickType) {
        this.clickType = clickType;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getCpm() {
        return cpm;
    }

    public void setCpm(int cpm) {
        this.cpm = cpm;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExtensionContry() {
        return extensionContry;
    }

    public void setExtensionContry(String extensionContry) {
        this.extensionContry = extensionContry;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

