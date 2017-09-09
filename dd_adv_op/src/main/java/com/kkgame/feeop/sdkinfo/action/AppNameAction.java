package com.kkgame.feeop.sdkinfo.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.sdkinfo.bean.AppNameVO;
import com.kkgame.feeop.sdkinfo.bean.AppNameVO;
import com.kkgame.feeop.sdkinfo.service.AppNameService;
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
public class AppNameAction extends BaseAction{
    private static Log logger = LogFactory.getLog(AppNameAction.class);
    private AppNameVO appNameVO;
    private AppNameService appNameService;
    private List<AppNameVO> appNameVOList;

    public String doList(){
        if(appNameVO == null){
            appNameVO = new AppNameVO();
        }
        try {
            appNameVOList = appNameService.getAppNameVOList(appNameVO);
        } catch (DatabaseException e) {
           logger.debug(e);
        }
        return ACTION_RESULT_LIST;
    }

    public String doCreate(){
        if(appNameVO == null){
            appNameVO = new AppNameVO();
        }
        return  ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if(appNameVO == null) {
            appNameVO = new AppNameVO();
        }
        try {
            appNameService.insert(appNameVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("创建配置信息成功");
        return null;
    }

    public String doModify() {
        if (appNameVO == null) {
            appNameVO = new AppNameVO();
        }
        try {
            appNameVO = appNameService.getAppNameVO(appNameVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_CREATE;
    }

    public String doUpdate() {
        if(appNameVO == null) {
            appNameVO = new AppNameVO();
        }
        try {
            appNameService.update(appNameVO);
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
            appNameService.delete(appNameVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("删除成功");
        return null;
    }

    public AppNameVO getAppNameVO() {
        return appNameVO;
    }

    public void setAppNameVO(AppNameVO appNameVO) {
        this.appNameVO = appNameVO;
    }

    public AppNameService getAppNameService() {
        return appNameService;
    }

    public void setAppNameService(AppNameService appNameService) {
        this.appNameService = appNameService;
    }

    public List<AppNameVO> getAppNameVOList() {
        return appNameVOList;
    }

    public void setAppNameVOList(List<AppNameVO> appNameVOList) {
        this.appNameVOList = appNameVOList;
    }
}
