package com.kkgame.feeop.ddl.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.kkgame.feeop.ddl.bean.DomainVO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.ddl.service.DdlProjectService;

public class DdlProjectAction extends BaseAction {

	private static Log logger = LogFactory.getLog(DdlProjectAction.class);
	private static final String ACTION_RESULT_DOMAIN_LIST = "domainlist";
	private static final String ACTION_RESULT_DOMAIN_CREATE = "domaincreate";

	private DdlProjectVO ddlProjectVO;
	
	private List<DdlProjectVO> ddlProjectVOList;
	
	private DdlProjectService ddlProjectService;

	private DomainVO domainVO;
	private List<DomainVO> domainVOList;
	private int refreshRow; // 判断域名页面是否需要更新行

	public String doDomainList() {
		// 获取所有domain
		try {
			domainVOList = ddlProjectService.getAllDomainVO();
		} catch (DatabaseException e) {
			logger.debug(e);
		}
		return ACTION_RESULT_DOMAIN_LIST;
	}

	public String doDomainCreate() {
		domainVO = new DomainVO();
		return ACTION_RESULT_DOMAIN_CREATE;
	}

	public String doDomainModify() {
		if (null == domainVO) return null;
		try {
			domainVO = ddlProjectService.getDomainVO(domainVO.getId());
		} catch (DatabaseException e) {
			logger.debug(e);
		}
		return ACTION_RESULT_DOMAIN_CREATE;
	}

	public String doDomainSave() {
		if (null == domainVO) return null;
		try {
			if (domainVO.getId() <= 0) {
				domainVO.setVersion(1);
				SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
				domainVO.setCdate(ymdFormat.format(new Date()));
				domainVO.setCreator(1);
				ddlProjectService.insertDomain(domainVO);
				printMessage("创建域名成功！");
			} else {
				ddlProjectService.updateDomain(domainVO);
				printMessage("更新域名成功！");
			}
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
		}
		return null;
	}

	public String doList() {
		if(ddlProjectVO == null) {
			ddlProjectVO = new DdlProjectVO();
		}
		try {
			ddlProjectVOList = ddlProjectService.getDdlProjectVOList(ddlProjectVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(ddlProjectVO == null) {
			ddlProjectVO = new DdlProjectVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doModify() {
		if(ddlProjectVO == null) {
			ddlProjectVO = new DdlProjectVO();
		}
		try {
			ddlProjectVO = ddlProjectService.getDdlProjectVO(ddlProjectVO);
		} catch (Exception e) {
			logger.debug(e);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(ddlProjectVO == null) {
			ddlProjectVO = new DdlProjectVO();
		}
		try {
			if(ddlProjectVO.getId()>0) {
				ddlProjectService.update(ddlProjectVO);
				this.printMessage("更新渠道产品信息成功！！！");
			} else {
				ddlProjectService.insert(ddlProjectVO);
				this.printMessage("创建渠道产品信息成功！！！");
			}
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("系统出错，请重试或联系管理员");
		}
		return null;
	}
	
	public DdlProjectVO getDdlProjectVO() {
		return ddlProjectVO;
	}

	public void setDdlProjectVO(DdlProjectVO ddlProjectVO) {
		this.ddlProjectVO = ddlProjectVO;
	}

	public List<DdlProjectVO> getDdlProjectVOList() {
		return ddlProjectVOList;
	}

	public void setDdlProjectVOList(List<DdlProjectVO> ddlProjectVOList) {
		this.ddlProjectVOList = ddlProjectVOList;
	}

	public DdlProjectService getDdlProjectService() {
		return ddlProjectService;
	}

	public void setDdlProjectService(DdlProjectService ddlProjectService) {
		this.ddlProjectService = ddlProjectService;
	}

	public DomainVO getDomainVO() {
		return domainVO;
	}

	public void setDomainVO(DomainVO domainVO) {
		this.domainVO = domainVO;
	}

	public List<DomainVO> getDomainVOList() {
		return domainVOList;
	}

	public void setDomainVOList(List<DomainVO> domainVOList) {
		this.domainVOList = domainVOList;
	}

	public int getRefreshRow() {
		return refreshRow;
	}

	public void setRefreshRow(int refreshRow) {
		this.refreshRow = refreshRow;
	}
}
