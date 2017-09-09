package com.kkgame.feeop.ddl.bean;

/**
 * Function:
 *      域名
 * @version $Revision$ $Date$
 *          Date: 2016/3/31
 *          Time: 10:08
 * @author: XJ
 * @since 3.0
 */
public class DomainVO {
    private int id;
    private int version;
    private String wwwDomain;
    private String downDomain;
    private String cdate;
    private int status;
    private int creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getWwwDomain() {
        return wwwDomain;
    }

    public void setWwwDomain(String wwwDomain) {
        this.wwwDomain = wwwDomain;
    }

    public String getDownDomain() {
        return downDomain;
    }

    public void setDownDomain(String downDomain) {
        this.downDomain = downDomain;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cDate) {
        this.cdate = cdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }
}
