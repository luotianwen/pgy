package com.kkgame.pay.stat.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/10.
 */
public class IframeVO implements Serializable{
    private int cid;                //渠道id
    private int pid;                //iframe id
    private int cou;
    private int operatorId;
    private String channelId;       //渠道点击id
    private int top;                //顶部广告激活状态
    private int iframe1;            //iframe1广告激活状态
    private int iframe2;
    private int iframe3;
    private int iframe4;
    private int iframe5;
    private int others;             //备用广告激活状态
    private String cdate;
    private String clickId;         //系统内部分配点击id，用于唯一性区分
    private Double payout;          //广告资费
    private int index;
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCou() {
        return cou;
    }

    public void setCou(int cou) {
        this.cou = cou;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getIframe1() {
        return iframe1;
    }

    public void setIframe1(int iframe1) {
        this.iframe1 = iframe1;
    }

    public int getIframe2() {
        return iframe2;
    }

    public void setIframe2(int iframe2) {
        this.iframe2 = iframe2;
    }

    public int getIframe3() {
        return iframe3;
    }

    public void setIframe3(int iframe3) {
        this.iframe3 = iframe3;
    }

    public int getIframe4() {
        return iframe4;
    }

    public void setIframe4(int iframe4) {
        this.iframe4 = iframe4;
    }

    public int getIframe5() {
        return iframe5;
    }

    public void setIframe5(int iframe5) {
        this.iframe5 = iframe5;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }
    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
