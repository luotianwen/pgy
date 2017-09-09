package com.kkgame.user.bean;

import java.io.Serializable;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/1
 *          Time: 11:48
 * @author: mm
 * @since 3.0
 */
public class SubActiveVO implements Serializable{
    private static final long serialVersionUID = 283329679303753099L;
    private long clickid;
    private String cdate;
    //该次激活的key值，ad|cou|cooid|operatorId|adType
    private String key;
    //该次激活支付金额
    private float payout;

    public long getClickid() {
        return clickid;
    }

    public void setClickid(long clickid) {
        this.clickid = clickid;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getPayout() {
        return payout;
    }

    public void setPayout(float payout) {
        this.payout = payout;
    }
}
