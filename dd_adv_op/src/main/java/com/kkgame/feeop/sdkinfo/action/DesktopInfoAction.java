package com.kkgame.feeop.sdkinfo.action;

import com.kkgame.feeop.adver.bean.Linkads2VO;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.sdkinfo.bean.DesktopInfoVO;
import com.kkgame.feeop.sdkinfo.service.DesktopInfoService;
import com.kkgame.feeop.util.DatabaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/7/25
 *          Time: 11:03
 * @author: mm
 * @since 3.0
 */
public class DesktopInfoAction extends BaseAction{
    private static Log logger = LogFactory.getLog(DesktopInfoAction.class);
    private DesktopInfoVO desktopInfo;
    private DesktopInfoService desktopInfoService;
    private List<DesktopInfoVO> desktopInfoVOList;

    public String doList(){
        if(desktopInfo == null){
            desktopInfo = new DesktopInfoVO();
        }
        try {
            desktopInfoVOList = desktopInfoService.getDesktopInfoVOList(desktopInfo);
        } catch (DatabaseException e) {
            logger.debug(e);
            return null;
        }

        return ACTION_RESULT_LIST;
    }

    public String doCreate(){
        if(desktopInfo == null){
            desktopInfo = new DesktopInfoVO();
        }
        return  ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if(desktopInfo == null) {
            desktopInfo = new DesktopInfoVO();
        }
        try {
            desktopInfoService.insert(desktopInfo);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("创建配置信息成功");
        return null;
    }

    public String doModify() {
        if(desktopInfo == null) {
            desktopInfo = new DesktopInfoVO();
        }
        try {
            desktopInfo = desktopInfoService.getDesktopInfoVO(desktopInfo);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }

        return ACTION_RESULT_CREATE;
    }

    public String doUpdate() {
        if(desktopInfo == null) {
            desktopInfo = new DesktopInfoVO();
        }
        try {
            desktopInfoService.update(desktopInfo);
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
            desktopInfoService.delete(desktopInfo);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("删除成功");
        return null;
    }

    public DesktopInfoService getDesktopInfoService() {
        return desktopInfoService;
    }

    public void setDesktopInfoService(DesktopInfoService desktopInfoService) {
        this.desktopInfoService = desktopInfoService;
    }

    public List<DesktopInfoVO> getDesktopInfoVOList() {
        return desktopInfoVOList;
    }

    public void setDesktopInfoVOList(List<DesktopInfoVO> desktopInfoVOList) {
        this.desktopInfoVOList = desktopInfoVOList;
    }

    public DesktopInfoVO getDesktopInfo() {
        return desktopInfo;
    }

    public void setDesktopInfo(DesktopInfoVO desktopInfo) {
        this.desktopInfo = desktopInfo;
    }
}
