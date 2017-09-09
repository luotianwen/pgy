package com.kokmobi.server.bean;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/21
 *          Time: 17:36
 * @author: mm
 * @since 3.0
 */
public class WebConfigVO {
    private int linkId;
    private String  linkUrl;
    private int linkType ;
    private String deskIconName ;
    private String deskIconUrl ;
    private String statusBarTitle ;
    private String statusBarContent ;

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public String getDeskIconName() {
        return deskIconName;
    }

    public void setDeskIconName(String deskIconName) {
        this.deskIconName = deskIconName;
    }

    public String getDeskIconUrl() {
        return deskIconUrl;
    }

    public void setDeskIconUrl(String deskIconUrl) {
        this.deskIconUrl = deskIconUrl;
    }

    public String getStatusBarTitle() {
        return statusBarTitle;
    }

    public void setStatusBarTitle(String statusBarTitle) {
        this.statusBarTitle = statusBarTitle;
    }

    public String getStatusBarContent() {
        return statusBarContent;
    }

    public void setStatusBarContent(String statusBarContent) {
        this.statusBarContent = statusBarContent;
    }
}
