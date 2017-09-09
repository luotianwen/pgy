package com.kkgame.feeop.sdkinfo.bean;

import com.kkgame.feeop.base.BasicVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/1
 *          Time: 10:14
 * @author: XJ
 * @since 3.0
 */
public class ProjectSdkVO extends BasicVO {
    private int projectId;
    private int sdkId;
    private String projectName;
    private String sdkName;
    private String sdkKey;
    private String note;
    // Update
    private int oldProjectId;
    private int oldSdkId;;
    // Logs
    private String operateDate;
    private String operateNote;
    // ExportTemplate
    private int activate;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getSdkKey() {
        return sdkKey;
    }

    public void setSdkKey(String sdkKey) {
        this.sdkKey = sdkKey;
    }

    public int getOldProjectId() {
        return oldProjectId;
    }

    public void setOldProjectId(int oldProjectId) {
        this.oldProjectId = oldProjectId;
    }

    public int getOldSdkId() {
        return oldSdkId;
    }

    public void setOldSdkId(int oldSdkId) {
        this.oldSdkId = oldSdkId;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateNote() {
        return operateNote;
    }

    public void setOperateNote(String operateNote) {
        this.operateNote = operateNote;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }
}
