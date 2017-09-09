package com.kkgame.feeop.sdkinfo.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.sdkinfo.bean.DesktopInfoVO;
import com.kkgame.feeop.sdkinfo.bean.LinkAdverVO;
import com.kkgame.feeop.sdkinfo.service.LinkAdverService;
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
public class LinkAdverAction extends BaseAction{
    private static Log logger = LogFactory.getLog(LinkAdverAction.class);
    private LinkAdverVO linkAdverVO;
    private LinkAdverService linkAdverService;
    private List<LinkAdverVO> linkAdverVOList;

    public String doList(){
        if(linkAdverVO == null){
            linkAdverVO = new LinkAdverVO();
        }
        try {
            linkAdverVOList = linkAdverService.getLinkAdverVOList(linkAdverVO);
        } catch (Exception e) {
            e.printStackTrace();
           logger.debug(e);
        }
        return ACTION_RESULT_LIST;
    }
    public String doCreate(){
        if(linkAdverVO == null){
            linkAdverVO = new LinkAdverVO();
        }
        return  ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if(linkAdverVO == null) {
            linkAdverVO = new LinkAdverVO();
        }
        try {
            linkAdverService.insert(linkAdverVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("创建配置信息成功");
        return null;
    }

    public String doModify() {
        if (linkAdverVO == null) {
            linkAdverVO = new LinkAdverVO();
        }
        try {
            linkAdverVO = linkAdverService.getLinkAdverVO(linkAdverVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_CREATE;
    }

    public String doUpdate() {
        if(linkAdverVO == null) {
            linkAdverVO = new LinkAdverVO();
        }
        try {
            linkAdverService.update(linkAdverVO);
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
            linkAdverService.delete(linkAdverVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("删除成功");
        return null;
    }

    public LinkAdverVO getLinkAdverVO() {
        return linkAdverVO;
    }

    public void setLinkAdverVO(LinkAdverVO linkAdverVO) {
        this.linkAdverVO = linkAdverVO;
    }

    public LinkAdverService getLinkAdverService() {
        return linkAdverService;
    }

    public void setLinkAdverService(LinkAdverService linkAdverService) {
        this.linkAdverService = linkAdverService;
    }

    public List<LinkAdverVO> getLinkAdverVOList() {
        return linkAdverVOList;
    }

    public void setLinkAdverVOList(List<LinkAdverVO> linkAdverVOList) {
        this.linkAdverVOList = linkAdverVOList;
    }
}
