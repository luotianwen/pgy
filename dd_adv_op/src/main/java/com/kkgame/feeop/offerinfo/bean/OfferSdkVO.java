package com.kkgame.feeop.offerinfo.bean;

import com.kkgame.feeop.base.BasicVO;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/8
 *          Time: 17:27
 * @author: mm
 * @since 3.0
 */
public class OfferSdkVO extends BasicVO{
    private int id;
    private String type;
    private String promotionUrl;
    private String _promotionUrl;
    private String imageUrl;
    private String adName;  //广告名称
    private String imageName;  //用于图片名或者包名
    private String title;
    private int review;
    private String cou;
    private int adverId;
    private int advLinkManId;
    private int internet;
    private String operator;
    private String remark;
    private double manuEcpm;
    private double autoEcpm;
    private int ecpmStatus;
    private double price;
    private String cdate;
    private int status;
    private double ctr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    public int getAdverId() {
        return adverId;
    }

    public void setAdverId(int adverId) {
        this.adverId = adverId;
    }

    public int getAdvLinkManId() {
        return advLinkManId;
    }

    public void setAdvLinkManId(int advLinkManId) {
        this.advLinkManId = advLinkManId;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getManuEcpm() {
        return manuEcpm;
    }

    public void setManuEcpm(double manuEcpm) {
        this.manuEcpm = manuEcpm;
    }

    public double getAutoEcpm() {
        return autoEcpm;
    }

    public void setAutoEcpm(double autoEcpm) {
        this.autoEcpm = autoEcpm;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String get_promotionUrl() {
        return _promotionUrl;
    }

    public void set_promotionUrl(String _promotionUrl) {
        this._promotionUrl = _promotionUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getEcpmStatus() {
        return ecpmStatus;
    }

    public void setEcpmStatus(int ecpmStatus) {
        this.ecpmStatus = ecpmStatus;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }
}
