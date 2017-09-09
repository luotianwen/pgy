package com.kokmobi.server.bean;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/3
 *          Time: 10:53
 * @author: mm
 * @since 3.0
 */
public class OfferSdkResp {
    private  int isExit;
    private JSONObject projectConf;
    private List<JSONObject> pushInfo;
    private List<JSONObject> plaqueInfo;
    private List<JSONObject> browserInfo;
    private List<JSONObject> appInfo;
    private List<JSONObject> levitateInfo;
    private List<JSONObject> deskIconA;
    private List<JSONObject> deskIconB;
    private List<JSONObject> upLevelInfo;
    private List<JSONObject> wwws;

    public int getIsExit() {
        return isExit;
    }

    public void setIsExit(int isExit) {
        this.isExit = isExit;
    }

    public List<JSONObject> getPushInfo() {
        return pushInfo;
    }

    public void setPushInfo(List<JSONObject> pushInfo) {
        this.pushInfo = pushInfo;
    }

    public List<JSONObject> getPlaqueInfo() {
        return plaqueInfo;
    }

    public void setPlaqueInfo(List<JSONObject> plaqueInfo) {
        this.plaqueInfo = plaqueInfo;
    }

    public List<JSONObject> getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(List<JSONObject> browserInfo) {
        this.browserInfo = browserInfo;
    }

    public List<JSONObject> getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(List<JSONObject> appInfo) {
        this.appInfo = appInfo;
    }

    public List<JSONObject> getLevitateInfo() {
        return levitateInfo;
    }

    public void setLevitateInfo(List<JSONObject> levitateInfo) {
        this.levitateInfo = levitateInfo;
    }

    public List<JSONObject> getDeskIconA() {
        return deskIconA;
    }

    public void setDeskIconA(List<JSONObject> deskIconA) {
        this.deskIconA = deskIconA;
    }

    public List<JSONObject> getDeskIconB() {
        return deskIconB;
    }

    public void setDeskIconB(List<JSONObject> deskIconB) {
        this.deskIconB = deskIconB;
    }

    public List<JSONObject> getUpLevelInfo() {
        return upLevelInfo;
    }

    public void setUpLevelInfo(List<JSONObject> upLevelInfo) {
        this.upLevelInfo = upLevelInfo;
    }

    public JSONObject getProjectConf() {
        return projectConf;
    }

    public void setProjectConf(JSONObject projectConf) {
        this.projectConf = projectConf;
    }

    public void setWwws(List<JSONObject> wwws) {
        this.wwws = wwws;
    }

    public List<JSONObject> getWwws() {
        return wwws;
    }
}
