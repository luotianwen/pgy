package com.kkgame.feeop.record.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.record.action.CountryLevelAction;
import com.kkgame.feeop.record.bean.CountryLevelVO;
import com.kkgame.feeop.record.service.CountryLevelService;
/**
 * @author luotianwen
 * @version 1.0
 * @since 1.0
 */
public class CountryLevelAction extends BaseAction {
	private static Log logger = LogFactory.getLog(CountryLevelAction.class);	
	private CountryLevelVO countryLevelVO;
	private List<CountryLevelVO> countryLevelVOList;
	private CountryLevelService countryLevelService;
	 

	public String doList() {
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		try {	
			countryLevelVOList = countryLevelService.getCountryLevelVOList(countryLevelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	//新增配置
	public String doSave() {
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		try {
			countryLevelService.create(countryLevelVO);
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
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		try {
			countryLevelService.update(countryLevelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	public String doModify() {
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		try {
			countryLevelVO = countryLevelService.getCountryLevelVO(countryLevelVO);
			countryLevelVO.setIsUpdate(1);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		
		return ACTION_RESULT_CREATE;
	}
	
	public String doDelete() {
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		try {
			countryLevelService.delete(countryLevelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		printMessage("更新配置信息成功");
		return null;
	}
	
	public String doDetail() {
		if(countryLevelVO == null) {
			countryLevelVO = new CountryLevelVO();
		}
		try {
			countryLevelVO = countryLevelService.getCountryLevelVO(countryLevelVO);
		} catch (Exception e) {
			logger.debug(e);
			printMessage(PkigConstants.RESPONSE_ERROR);
			return null;
		}
		return ACTION_RESULT_DETAIL;
	}

	public CountryLevelVO getCountryLevelVO() {
		return countryLevelVO;
	}

	public void setCountryLevelVO(CountryLevelVO countryLevelVO) {
		this.countryLevelVO = countryLevelVO;
	}

	public List<CountryLevelVO> getCountryLevelVOList() {
		return countryLevelVOList;
	}

	public void setCountryLevelVOList(List<CountryLevelVO> countryLevelVOList) {
		this.countryLevelVOList = countryLevelVOList;
	}

	public CountryLevelService getCountryLevelService() {
		return countryLevelService;
	}

	public void setCountryLevelService(CountryLevelService countryLevelService) {
		this.countryLevelService = countryLevelService;
	}
	 
	 

}
