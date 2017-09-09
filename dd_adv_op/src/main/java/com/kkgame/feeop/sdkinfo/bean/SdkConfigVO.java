package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

import java.util.Date;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/21
 *          Time: 16:01
 * @author: mm
 * @since 3.0
 */
public class SdkConfigVO extends BasicVO {
    private int id;
    private int intercepterRate;
    private int pushNoticeRate;
    private String version;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntercepterRate() {
        return intercepterRate;
    }

    public void setIntercepterRate(int intercepterRate) {
        this.intercepterRate = intercepterRate;
    }

    public int getPushNoticeRate() {
        return pushNoticeRate;
    }

    public void setPushNoticeRate(int pushNoticeRate) {
        this.pushNoticeRate = pushNoticeRate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
