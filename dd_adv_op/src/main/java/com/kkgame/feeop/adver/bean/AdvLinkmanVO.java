package com.kkgame.feeop.adver.bean;

import com.kkgame.feeop.base.BasicVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/14
 *          Time: 17:16
 * @author: XJ
 * @since 3.0
 */
public class AdvLinkmanVO extends BasicVO {
    private int id;
    private String name;
    private String phone;
    private String qq;
    private String address;
    private Double tcbl;
    private int creator;
    private int deleted;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTcbl() {
        return tcbl;
    }

    public void setTcbl(Double tcbl) {
        this.tcbl = tcbl;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
