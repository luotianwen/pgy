package com.kkgame.feeop.sdkinfo.action;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.sdkinfo.bean.GuideInfoVO;
import com.kkgame.feeop.sdkinfo.bean.SdkdomainVO;
import com.kkgame.feeop.sdkinfo.service.SdkdomainService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import static org.apache.zookeeper.ZooDefs.OpCode.delete;

/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class SdkdomainAction extends BaseAction {
	private static Log logger = LogFactory.getLog(SdkdomainAction.class);	
	private SdkdomainVO sdkdomainVO;
	private List<SdkdomainVO> sdkdomainVOList;
	private SdkdomainService sdkdomainService;
	 

	public String doList() {
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		try {	
			sdkdomainVOList = sdkdomainService.getSdkdomainVOList(sdkdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		try {
			sdkdomainService.create(sdkdomainVO);
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
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		try {
			sdkdomainService.update(sdkdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		try {
			sdkdomainVO = sdkdomainService.getSdkdomainVO(sdkdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	public String doDelete() {
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		try {
			sdkdomainService.delete(sdkdomainVO);
		} catch(Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}

		this.printMessage("删除成功！");
		return null;
	}
	public String doDetail() {
		if(sdkdomainVO == null) {
			sdkdomainVO = new SdkdomainVO();
		}
		try {
			sdkdomainVO = sdkdomainService.getSdkdomainVO(sdkdomainVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public SdkdomainVO getSdkdomainVO() {
		return sdkdomainVO;
	}

	public void setSdkdomainVO(SdkdomainVO sdkdomainVO) {
		this.sdkdomainVO = sdkdomainVO;
	}

	public List<SdkdomainVO> getSdkdomainVOList() {
		return sdkdomainVOList;
	}

	public void setSdkdomainVOList(List<SdkdomainVO> sdkdomainVOList) {
		this.sdkdomainVOList = sdkdomainVOList;
	}

	public SdkdomainService getSdkdomainService() {
		return sdkdomainService;
	}

	public void setSdkdomainService(SdkdomainService sdkdomainService) {
		this.sdkdomainService = sdkdomainService;
	}
	 
	 

}
