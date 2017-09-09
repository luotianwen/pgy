package com.kkgame.feeop.offline.bean;

import com.kkgame.feeop.base.BasicVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/9/21
 *          Time: 14:07
 * @author: mm
 * @since 3.0
 */
public class OfflinesdkVO extends BasicVO{
    private int id;
    private int timeStep;
    private int status;
    private String cdate;
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(int timeStep) {
        this.timeStep = timeStep;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
