package com.kokmobi.server.bean;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/26
 *          Time: 18:17
 * @author: mm
 * @since 3.0
 */
public class ActiveInfo {
    private String clickId;
    private String affId;
    private String payout;

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getAffId() {
        return affId;
    }

    public void setAffId(String affId) {
        this.affId = affId;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }
}
