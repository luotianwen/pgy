package com.kokmobi.server.bean;


/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/22
 *          Time: 15:27
 * @author: mm
 * @since 3.0
 */
public class WebAdVO implements Cloneable {
    private int    id;
    private int linkType ;
    private String redirectUrl ;
    private String cou ;
    private int linkAdType ;
    private int operator ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    public int getLinkAdType() {
        return linkAdType;
    }

    public void setLinkAdType(int linkAdType) {
        this.linkAdType = linkAdType;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
