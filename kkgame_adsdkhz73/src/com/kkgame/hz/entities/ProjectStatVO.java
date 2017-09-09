package com.kkgame.hz.entities;

import com.kkgame.hz.base.BaseVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/21
 *          Time: 10:58
 * @author: Administrator
 * @since 3.0
 */
public class ProjectStatVO extends BaseVO {
    // 用户填写的查询条件
    private int queryType;
    private String startTime;
    private String endTime;
    private int bdId;
    private int status;
    // 是否显示
    private int isShowTime;
    // 返回结果
    private String time;
    private int packegaNum;
    private String bdName;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBdName() {
        return bdName;
    }

    public void setBdName(String bdName) {
        this.bdName = bdName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPackegaNum() {
        return packegaNum;
    }

    public void setPackegaNum(int packegaNum) {
        this.packegaNum = packegaNum;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBdId() {
        return bdId;
    }

    public void setBdId(int bdId) {
        this.bdId = bdId;
    }

    public int getIsShowTime() {
        return isShowTime;
    }

    public void setIsShowTime(int isShowTime) {
        this.isShowTime = isShowTime;
    }

}



