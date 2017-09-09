package com.kkgame.feeop.adver.action;

import com.kkgame.feeop.adver.bean.PromotionDomainVO;
import com.kkgame.feeop.adver.bean.SpromotionVO;
import com.kkgame.feeop.adver.service.PromotionDomainService;
import com.kkgame.feeop.adver.service.PromotionService;
import com.kkgame.feeop.adver.service.SpromotionService;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Random;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class SpromotionAction extends BaseAction {
    private static Log logger = LogFactory.getLog(SpromotionAction.class);
    private SpromotionVO spromotionVO;
    private List<SpromotionVO> spromotionVOList;
    private SpromotionService spromotionService;
    private PromotionDomainService promotionDomainService;
    private PromotionService promotionService;

    public String doList() {
        if (spromotionVO == null) {
            spromotionVO = new SpromotionVO();
        }
        try {
            spromotionVOList = spromotionService.getSpromotionVOList(spromotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doCreate() {
        if (spromotionVO == null) {
            spromotionVO = new SpromotionVO();
        }
        return ACTION_RESULT_CREATE;
    }

    private SpromotionVO setUrl(SpromotionVO spromotionVO) throws Exception {
        List<PromotionDomainVO> a = promotionDomainService.getPromotionDomainVOList(new PromotionDomainVO());
        if (null != a && a.size() > 0) {
            PromotionDomainVO b = a.get(new Random().nextInt(a.size()));
            spromotionVO.setPromotionLink("http://" + b.getDownload() + "/gosublink?cooId=" + spromotionVO.getCooId()+"&type="+spromotionVO.getType());
        }
        return spromotionVO;
    }

    //新增配置
    public String doSave() {
        if (spromotionVO == null) {
            spromotionVO = new SpromotionVO();
        }
        try {
            setUrl(spromotionVO);
            spromotionService.create(spromotionVO);

        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("创建配置信息成功");
        return null;
    }


    //修改通道
    public String doUpdate() {
        if (spromotionVO == null) {
            spromotionVO = new SpromotionVO();
        }
        try {
            setUrl(spromotionVO);
            spromotionService.update(spromotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息成功");
        return null;
    }

    public String doModify() {
        if (spromotionVO == null) {
            spromotionVO = new SpromotionVO();
        }
        try {
            spromotionVO = spromotionService.getSpromotionVO(spromotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }

        return ACTION_RESULT_CREATE;
    }

    public String doDetail() {
        if (spromotionVO == null) {
            spromotionVO = new SpromotionVO();
        }
        try {
            spromotionVO = spromotionService.getSpromotionVO(spromotionVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_DETAIL;
    }

    public SpromotionVO getSpromotionVO() {
        return spromotionVO;
    }

    public void setSpromotionVO(SpromotionVO spromotionVO) {
        this.spromotionVO = spromotionVO;
    }

    public List<SpromotionVO> getSpromotionVOList() {
        return spromotionVOList;
    }

    public void setSpromotionVOList(List<SpromotionVO> spromotionVOList) {
        this.spromotionVOList = spromotionVOList;
    }



    public PromotionDomainService getPromotionDomainService() {
        return promotionDomainService;
    }

    public void setPromotionDomainService(PromotionDomainService promotionDomainService) {
        this.promotionDomainService = promotionDomainService;
    }

    public SpromotionService getSpromotionService() {
        return spromotionService;
    }

    public void setSpromotionService(SpromotionService spromotionService) {
        this.spromotionService = spromotionService;
    }

    public PromotionService getPromotionService() {
        return promotionService;
    }

    public void setPromotionService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
}
