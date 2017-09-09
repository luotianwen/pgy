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
public class WebProjectResp implements Serializable {
    private int isLink;
    private int noBrowserTimes;
    private int noBrowserInterval;
    private int statusBarTimes;
    private int statusBarInterval;
    private int desktopTimes;
    private int desktopInterval;
    private int isExit;



    private List<JSONObject> linkInfors;
    private List<JSONObject> wwws;
    public int getIsLink() {
        return isLink;
    }

    public void setIsLink(int isLink) {
        this.isLink = isLink;
    }

    public int getNoBrowserTimes() {
        return noBrowserTimes;
    }

    public void setNoBrowserTimes(int noBrowserTimes) {
        this.noBrowserTimes = noBrowserTimes;
    }

    public int getNoBrowserInterval() {
        return noBrowserInterval;
    }

    public void setNoBrowserInterval(int noBrowserInterval) {
        this.noBrowserInterval = noBrowserInterval;
    }

    public int getStatusBarTimes() {
        return statusBarTimes;
    }

    public void setStatusBarTimes(int statusBarTimes) {
        this.statusBarTimes = statusBarTimes;
    }

    public int getStatusBarInterval() {
        return statusBarInterval;
    }

    public void setStatusBarInterval(int statusBarInterval) {
        this.statusBarInterval = statusBarInterval;
    }

    public int getDesktopTimes() {
        return desktopTimes;
    }

    public void setDesktopTimes(int desktopTimes) {
        this.desktopTimes = desktopTimes;
    }

    public int getDesktopInterval() {
        return desktopInterval;
    }

    public void setDesktopInterval(int desktopInterval) {
        this.desktopInterval = desktopInterval;
    }
    public List<JSONObject> getLinkInfors() {
        return linkInfors;
    }

    public void setLinkInfors(List<JSONObject> linkInfors) {
        this.linkInfors = linkInfors;
    }

    public List<JSONObject> getWwws() {
        return wwws;
    }

    public void setWwws(List<JSONObject> wwws) {
        this.wwws = wwws;
    }
    public int getIsExit() {
        return isExit;
    }

    public void setIsExit(int isExit) {
        this.isExit = isExit;
    }
}
