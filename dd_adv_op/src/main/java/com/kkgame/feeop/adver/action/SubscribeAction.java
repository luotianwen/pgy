package com.kkgame.feeop.adver.action;

import com.kkgame.feeop.adver.bean.SubscribeVO;
import com.kkgame.feeop.adver.service.SubscribeService;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.util.DatabaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class SubscribeAction extends BaseAction {
    private static Log logger = LogFactory.getLog(SubscribeAction.class);
    private SubscribeVO subscribeVO;
    private List<SubscribeVO> subscribeVOList;
    private SubscribeService subscribeService;


    public String doList() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            subscribeVOList = subscribeService.getSubscribeVOList(subscribeVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doSelectList() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            subscribeVOList = subscribeService.getSelectSubscribeVOList(subscribeVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doCreate() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        return ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            String pa = "";
            if(subscribeVO.getRedirectUrl().indexOf("?")==-1){
                pa = "?";
            } else{
                pa = "&";
            }
            subscribeVO.setRedirectUrl(subscribeVO.getRedirectUrl()+pa+subscribeVO.getParam1()+"={tid}&"+
            subscribeVO.getParam2()+"={cid}");
            subscribeService.create(subscribeVO);
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
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            subscribeService.update(subscribeVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息成功");
        return null;
    }

    public String doModify() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            subscribeVO = subscribeService.getSubscribeVO(subscribeVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }

        return ACTION_RESULT_CREATE;
    }

    public void doCopy() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            subscribeService.copy(subscribeVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
        }
        printMessage("复制配置信息成功");
    }
    public void doCopycous(){
        if(subscribeVO.getId() == null){
            printMessage("无复制对象，复制失败");
        }else{
            try {
                subscribeVO = subscribeService.getSubscribeVO(subscribeVO);
                List<SubscribeVO> subscribeVOs = new ArrayList<SubscribeVO>();
                int cou = 0;
                String coustr = subscribeVO.getCou();
                if(StringUtils.hasLength(coustr)){
                     String[]cous =  coustr.split(",");
                     if(cous.length==1 || (cous.length==2&&cous[1]=="")){
                         cou = Integer.valueOf(cous[0]);
                     }else{
                         Integer.valueOf(coustr);
                     }
                }
                for(int i = 1;i<=183;i++){
                    SubscribeVO s =(SubscribeVO) subscribeVO.clone();
                    if(i != cou){
                       s.setCou(String.valueOf(i));
                       subscribeVOs.add(s);
                    }
                }
                subscribeService.insertSubs(subscribeVOs);
                printMessage("复制配置信息成功");
            } catch (DatabaseException  e) {
                printMessage(PkigConstants.RESPONSE_ERROR);
            }
            catch (CloneNotSupportedException e) {
                printMessage(PkigConstants.RESPONSE_ERROR);
            }
            catch (NumberFormatException e){
                printMessage(PkigConstants.RESPONSE_ERROR);
            }
        }
    }

    public String doDetail() {
        if (subscribeVO == null) {
            subscribeVO = new SubscribeVO();
        }
        try {
            subscribeVO = subscribeService.getSubscribeVO(subscribeVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_DETAIL;
    }

    public void setSubscribeVO(SubscribeVO subscribeVO) {
        this.subscribeVO = subscribeVO;
    }

    public void setSubscribeVOList(List<SubscribeVO> subscribeVOList) {
        this.subscribeVOList = subscribeVOList;
    }

    public void setSubscribeService(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    public SubscribeVO getSubscribeVO() {
        return subscribeVO;
    }

    public List<SubscribeVO> getSubscribeVOList() {
        return subscribeVOList;
    }

    public SubscribeService getSubscribeService() {
        return subscribeService;
    }
}
