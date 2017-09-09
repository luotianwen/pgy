package com.kkgame.feeop.detail.bean;

import com.kkgame.feeop.base.BasicVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/31
 *          Time: 17:31
 * @author: mm
 * @since 3.0
 */
public class IframeDetailVO extends BasicVO{
    private int index;
    private String clickId;
    private String payout;
    private int cou;
    private String couName;
    private int operatorId;
    private String operator;
    private String cdate;
    private int cid;
    private String cidName;
    private int pid;
    private String pidName;
    private String channelId;
    private int totalShowCount;
    private int activeCount;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }
    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public int getCou() {
        return cou;
    }

    public void setCou(int cou) {
        this.cou = cou;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCidName() {
        return cidName;
    }

    public void setCidName(String cidName) {
        this.cidName = cidName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getTotalShowCount() {
        return totalShowCount;
    }

    public void setTotalShowCount(int totalShowCount) {
        this.totalShowCount = totalShowCount;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }
}
