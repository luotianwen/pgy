package com.kkgame.feeop.sdkinfo.bean;


import com.kkgame.feeop.base.BasicVO;

import java.util.Date;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/21
 *          Time: 16:10
 * @author: mm
 * @since 3.0
 */
public class SdkUpLimitVO extends BasicVO {
    private int id;
    private Date CreateTime;
    private int linkAdvId;
    private int upLimitValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        this.CreateTime = createTime;
    }

    public int getLinkAdvId() {
        return linkAdvId;
    }

    public void setLinkAdvId(int linkAdvId) {
        this.linkAdvId = linkAdvId;
    }

    public int getUpLimitValue() {
        return upLimitValue;
    }

    public void setUpLimitValue(int upLimitValue) {
        this.upLimitValue = upLimitValue;
    }
}
