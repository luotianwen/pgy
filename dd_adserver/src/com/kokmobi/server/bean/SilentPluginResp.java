package com.kokmobi.server.bean;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/2/19
 *          Time: 11:59
 * @author: Xiao Jia
 */
public class SilentPluginResp {

    private int plugId;                      //插件id
    private int plugType;                    //0表示独立插件，1表示互斥插件，2表示其他插件
    private String plugName;                 //名称
    private String plugDownloadURL;          //下载路径
    private String plugPackageName;          //包名
    private String plugSevName;              //插件服务名
    private String plugSevPara;              //插件参数

    public String getPlugName() {
        return plugName;
    }

    public void setPlugName(String plugName) {
        this.plugName = plugName;
    }

    public String getPlugDownloadURL() {
        return plugDownloadURL;
    }

    public void setPlugDownloadURL(String plugDownloadURL) {
        this.plugDownloadURL = plugDownloadURL;
    }

    public String getPlugPackageName() {
        return plugPackageName;
    }

    public void setPlugPackageName(String plugPackageName) {
        this.plugPackageName = plugPackageName;
    }

    public int getPlugId() {
        return plugId;
    }

    public void setPlugId(int plugId) {
        this.plugId = plugId;
    }

    public int getPlugType() {
        return plugType;
    }

    public void setPlugType(int plugType) {
        this.plugType = plugType;
    }

    public String getPlugSevName() {
        return plugSevName;
    }

    public void setPlugSevName(String plugSevName) {
        this.plugSevName = plugSevName;
    }

    public String getPlugSevPara() {
        return plugSevPara;
    }

    public void setPlugSevPara(String plugSevPara) {
        this.plugSevPara = plugSevPara;
    }
}
