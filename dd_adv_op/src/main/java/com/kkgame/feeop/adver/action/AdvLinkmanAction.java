package com.kkgame.feeop.adver.action;

import com.kkgame.feeop.adver.bean.AdvLinkmanVO;
import com.kkgame.feeop.adver.service.AdvLinkmanService;
import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.util.DatabaseException;
import com.opensymphony.xwork2.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/4/14
 *          Time: 18:28
 * @author: XJ
 * @since 3.0
 */
public class AdvLinkmanAction extends BaseAction {
    private static final Log logger = LogFactory.getLog(AdvLinkmanAction.class);

    private AdvLinkmanService advLinkmanService;

    private AdvLinkmanVO advLinkmanVO;
    private List<AdvLinkmanVO> advLinkmanVOList;

    public String getAllAdvLinkman() {
        try {
            advLinkmanVOList = advLinkmanService.getAllAdvLinkman();
        } catch (Exception e) {
            e.printStackTrace();
            printMessage("-1");
            return null;
        }
        return Action.SUCCESS;
    }

    public String doCreate() {
        advLinkmanVO = new AdvLinkmanVO();
        return ACTION_RESULT_CREATE;
    }

    public String doList() {
        if (null == advLinkmanVO) {
            advLinkmanVO = new AdvLinkmanVO();
        }
        try {
            advLinkmanVOList = advLinkmanService.getAdvLinkmanList(advLinkmanVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String doModify() {
        try {
            advLinkmanVO = advLinkmanService.getAdvLinkman(advLinkmanVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage("-1");
            return null;
        }
        return ACTION_RESULT_MODIFY;
    }

    public String doSave() {
        try {
            String resStr;
            if (0 >= advLinkmanVO.getId()) {
                advLinkmanService.insertAdvLinkman(advLinkmanVO);
                resStr = "成功创建接入人员";
            } else {
                advLinkmanService.updateAdvLinkman(advLinkmanVO);
                resStr = "成功更改接入人员";
            }
            printMessage(resStr);
        } catch (Exception e) {
            logger.debug(e);
            printMessage("-1");
        }
        return null;
    }

    public AdvLinkmanService getAdvLinkmanService() {
        return advLinkmanService;
    }

    public void setAdvLinkmanService(AdvLinkmanService advLinkmanService) {
        this.advLinkmanService = advLinkmanService;
    }

    public AdvLinkmanVO getAdvLinkmanVO() {
        return advLinkmanVO;
    }

    public void setAdvLinkmanVO(AdvLinkmanVO advLinkmanVO) {
        this.advLinkmanVO = advLinkmanVO;
    }

    public List<AdvLinkmanVO> getAdvLinkmanVOList() {
        return advLinkmanVOList;
    }

    public void setAdvLinkmanVOList(List<AdvLinkmanVO> advLinkmanVOList) {
        this.advLinkmanVOList = advLinkmanVOList;
    }
}
