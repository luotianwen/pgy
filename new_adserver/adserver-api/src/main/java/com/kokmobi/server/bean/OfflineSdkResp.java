package com.kokmobi.server.bean;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/21
 *          Time: 14:52
 * @author: mm
 * @since 3.0
 */
public class OfflineSdkResp implements Serializable {
    private int status;
    private int timestep;

    private List<JSONObject> jars;
    private List<JSONObject> apks;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimestep() {
        return timestep;
    }

    public void setTimestep(int timestep) {
        this.timestep = timestep;
    }

    public List<JSONObject> getJars() {
        return jars;
    }

    public void setJars(List<JSONObject> jars) {
        this.jars = jars;
    }

    public List<JSONObject> getApks() {
        return apks;
    }

    public void setApks(List<JSONObject> apks) {
        this.apks = apks;
    }
}
