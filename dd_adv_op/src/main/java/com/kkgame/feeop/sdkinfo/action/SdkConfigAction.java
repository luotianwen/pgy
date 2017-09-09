package com.kkgame.feeop.sdkinfo.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.sdkinfo.bean.LinkAdverVO;
import com.kkgame.feeop.sdkinfo.bean.SdkConfigVO;
import com.kkgame.feeop.sdkinfo.service.SdkConfigService;
import com.kkgame.feeop.util.DatabaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/25
 *          Time: 16:29
 * @author: mm
 * @since 3.0
 */
public class SdkConfigAction extends BaseAction{
    private static Log logger = LogFactory.getLog(SdkConfigAction.class);
    private SdkConfigVO sdkConfigVO;
    private SdkConfigService sdkConfigService;
    private List<SdkConfigVO> sdkConfigVOList;

    public String doList(){
        if(sdkConfigVO == null){
            sdkConfigVO = new SdkConfigVO();
        }
        try {
            sdkConfigVOList = sdkConfigService.getSdkConfigVOList(sdkConfigVO);
        } catch (DatabaseException e) {
           logger.debug(e);
        }
        return ACTION_RESULT_LIST;
    }

    public String doCreate(){
        if(sdkConfigVO == null){
            sdkConfigVO = new SdkConfigVO();
        }
        return  ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if(sdkConfigVO == null) {
            sdkConfigVO = new SdkConfigVO();
        }
        try {
            sdkConfigService.insert(sdkConfigVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("创建配置信息成功");
        return null;
    }

    public String doModify() {
        if (sdkConfigVO == null) {
            sdkConfigVO = new SdkConfigVO();
        }
        try {
            sdkConfigVO = sdkConfigService.getSdkConfigVO(sdkConfigVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_CREATE;
    }

    public String doUpdate() {
        if(sdkConfigVO == null) {
            sdkConfigVO = new SdkConfigVO();
        }
        try {
            sdkConfigService.update(sdkConfigVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息成功");
        return null;
    }

    public String doRemove() {

        try {
            sdkConfigService.delete(sdkConfigVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("删除成功");
        return null;
    }

    public SdkConfigVO getSdkConfigVO() {
        return sdkConfigVO;
    }

    public void setSdkConfigVO(SdkConfigVO sdkConfigVO) {
        this.sdkConfigVO = sdkConfigVO;
    }

    public SdkConfigService getSdkConfigService() {
        return sdkConfigService;
    }

    public void setSdkConfigService(SdkConfigService sdkConfigService) {
        this.sdkConfigService = sdkConfigService;
    }

    public List<SdkConfigVO> getSdkConfigVOList() {
        return sdkConfigVOList;
    }

    public void setSdkConfigVOList(List<SdkConfigVO> sdkConfigVOList) {
        this.sdkConfigVOList = sdkConfigVOList;
    }
}
