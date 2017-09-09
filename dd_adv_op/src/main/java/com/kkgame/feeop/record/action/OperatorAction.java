package com.kkgame.feeop.record.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.record.service.OperatorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/8/19
 *          Time: 18:07
 * @author: mm
 * @since 3.0
 */
public class OperatorAction extends BaseAction {
    private static Log logger = LogFactory.getLog(OperatorAction.class);
    private OperatorVO operatorVO;
    private List<OperatorVO> operatorVOList;
    private OperatorService operatorService;


    public String doList() {
        if(operatorVO == null) {
            operatorVO = new OperatorVO();
        }
        try {
            operatorVOList = operatorService.getOperatorVOList(operatorVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return ACTION_RESULT_LIST;
    }

    public String getAllOperator(){
        try {
            operatorVOList = operatorService.getAllOperatorVOList();
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return SUCCESS;
    }

    public String GetOperatorsByCou(){
        try {
            operatorVOList = operatorService.getOperatorsByCou(operatorVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        return SUCCESS;
    }

    public String doCreate() {
        if(operatorVO == null) {
            operatorVO = new OperatorVO();
        }
        return ACTION_RESULT_CREATE;
    }

    //新增配置
    public String doSave() {
        if(operatorVO == null) {
            operatorVO = new OperatorVO();
        }
        try {
            operatorService.create(operatorVO);
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
        if(operatorVO == null) {
            operatorVO = new OperatorVO();
        }
        try {
            operatorService.update(operatorVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }
        printMessage("更新配置信息成功");
        return null;
    }
    public String doModify() {
        if(operatorVO == null) {
            operatorVO = new OperatorVO();
        }
        try {
            operatorVO = operatorService.getOperatorVO(operatorVO);
        } catch (Exception e) {
            logger.debug(e);
            printMessage(PkigConstants.RESPONSE_ERROR);
            return null;
        }

        return ACTION_RESULT_CREATE;
    }

    public OperatorVO getOperatorVO() {
        return operatorVO;
    }

    public void setOperatorVO(OperatorVO operatorVO) {
        this.operatorVO = operatorVO;
    }

    public List<OperatorVO> getOperatorVOList() {
        return operatorVOList;
    }

    public void setOperatorVOList(List<OperatorVO> operatorVOList) {
        this.operatorVOList = operatorVOList;
    }

    public OperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
}
