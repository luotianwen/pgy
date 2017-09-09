package com.kokmobi.server.bean;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/9
 *          Time: 14:56
 * @author: Xiao Jia
 */
public class UpdateJarResp {
    private int sdkId;
    private int version;
    private int type;
    private String apkpackage;
    private String apkdownloadURL;
    private String startArgu;
    private String startClass;
    private int rank;

    public String getStartArgu() {
        return startArgu;
    }

    public void setStartArgu(String startArgu) {
        this.startArgu = startArgu;
    }

    public String getStartClass() {
        return startClass;
    }

    public void setStartClass(String startClass) {
        this.startClass = startClass;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getApkpackage() {
        return apkpackage;
    }

    public void setApkpackage(String apkpackage) {
        this.apkpackage = apkpackage;
    }

    public String getApkdownloadURL() {
        return apkdownloadURL;
    }

    public void setApkdownloadURL(String apkdownloadURL) {
        this.apkdownloadURL = apkdownloadURL;
    }

    public int getSdkId() {
        return sdkId;
    }

    public void setSdkId(int sdkId) {
        this.sdkId = sdkId;
    }
}
