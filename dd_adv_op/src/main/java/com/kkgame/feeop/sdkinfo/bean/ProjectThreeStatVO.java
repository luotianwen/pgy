package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/7
 *          Time: 18:46
 * @author: XJ
 * @since 3.0
 */
public class ProjectThreeStatVO extends BasicVO {
    private String saleDate; // 销售时间
    private int projectId;
    private int sdkId;
    private int activate; // 销量
    private Double income; // 收入
    //
    private String projectName;
    private String sdkName;

    public BigDecimal getPrice() {
        return new BigDecimal(income / activate).setScale(3, RoundingMode.HALF_EVEN);
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSdkId() {
        return sdkId;
    }

    public void setSdkId(int sdkId) {
        this.sdkId = sdkId;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSdkName() {
        return sdkName;
    }

    public void setSdkName(String sdkName) {
        this.sdkName = sdkName;
    }
}
