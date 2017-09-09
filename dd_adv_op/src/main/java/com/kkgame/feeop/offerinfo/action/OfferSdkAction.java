package com.kkgame.feeop.offerinfo.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.offerinfo.bean.OfferSdkVO;
import com.kkgame.feeop.offerinfo.service.OfferSdkService;
import com.kkgame.feeop.record.bean.AdjustVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/11/9
 *          Time: 14:42
 * @author: mm
 * @since 3.0
 */
public class OfferSdkAction extends BaseAction{
    private OfferSdkVO offerSdkVO;
    private List<OfferSdkVO> offerSdkVOList;
    private OfferSdkService offerSdkService;
    private static Log logger = LogFactory.getLog(OfferSdkVO.class);
    public String doList(){
        if(offerSdkVO == null){
            offerSdkVO = new OfferSdkVO();
        }
        offerSdkVOList = offerSdkService.getOfferSdkList(offerSdkVO);
        return  ACTION_RESULT_LIST;
    }

    public String doCreate(){
        return  ACTION_RESULT_CREATE;
    }

    public String doModify(){
        if(offerSdkVO!= null){
            offerSdkVO = offerSdkService.getOfferSdkById(offerSdkVO);
        }
        return  ACTION_RESULT_CREATE;
    }

    public void doDelete(){
        try{
            if(offerSdkVO!= null){
               offerSdkService.deleteOfferSdkById(offerSdkVO);
            }
        }catch (Exception e){
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
        }
        printMessage("删除信息成功");
    }

    public String getAllOfferSdkList(){
        if(offerSdkVO == null){
            offerSdkVO = new OfferSdkVO();
        }
        offerSdkVOList = offerSdkService.getOfferSdkList();
        return  ACTION_RESULT_LIST;
    }

    public void doSave(){
        if(offerSdkVO == null) {
            offerSdkVO = new OfferSdkVO();
        }
        try {
            String pa = "";
            if(offerSdkVO.getPromotionUrl().indexOf("?")==-1){
                pa = "?";
            } else{
                pa = "&";
            }
            offerSdkVO.setPromotionUrl(offerSdkVO.getPromotionUrl()+pa+"tid={tid}");
            if(offerSdkVO.get_promotionUrl().indexOf("?")==-1){
                pa = "?";
            } else{
                pa = "&";
            }
            offerSdkVO.set_promotionUrl(offerSdkVO.get_promotionUrl()+pa+"tid={tid}");
            offerSdkService.save(offerSdkVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
        }
        printMessage("创建配置信息成功");
    }
    //修改通道
    public String doUpdate() {
        if(offerSdkVO == null) {
            offerSdkVO = new OfferSdkVO();
        }
        try {
            offerSdkService.update(offerSdkVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息成功");
        return null;
    }
    public OfferSdkVO getOfferSdkVO() {
        return offerSdkVO;
    }

    public void setOfferSdkVO(OfferSdkVO offerSdkVO) {
        this.offerSdkVO = offerSdkVO;
    }

    public List<OfferSdkVO> getOfferSdkVOList() {
        return offerSdkVOList;
    }

    public void setOfferSdkVOList(List<OfferSdkVO> offerSdkVOList) {
        this.offerSdkVOList = offerSdkVOList;
    }

    public OfferSdkService getOfferSdkService() {
        return offerSdkService;
    }

    public void setOfferSdkService(OfferSdkService offerSdkService) {
        this.offerSdkService = offerSdkService;
    }
}
