package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

import java.util.Date;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/21
 *          Time: 15:32
 * @author: mm
 * @since 3.0
 */
public class DesktopInfoVO extends BasicVO {
    private int id ;
    private String deskIconUrl;
    private String deskName;
    private String homePage;
    private int status;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskIconUrl() {
        return deskIconUrl;
    }

    public void setDeskIconUrl(String deskIconUrl) {
        this.deskIconUrl = deskIconUrl;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
