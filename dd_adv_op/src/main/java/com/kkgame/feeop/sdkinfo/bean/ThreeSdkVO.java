package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/1
 *          Time: 10:03
 * @author: XJ
 * @since 3.0
 */
public class ThreeSdkVO extends BasicVO {
    private int id;
    private String name;
    private int status;  // (3200  是 3201 否)状态
    private String createDate;
    private String note; // 说明
    private String remark;
    private int sort; // 排序

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
