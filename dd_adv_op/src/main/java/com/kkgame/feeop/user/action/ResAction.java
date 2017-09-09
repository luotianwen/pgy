package com.kkgame.feeop.user.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.base.datasource.DataSourceContextHolder;
import com.kkgame.feeop.base.datasource.DataSourceMap;
import com.kkgame.feeop.user.bean.ResVO;
import com.kkgame.feeop.user.service.ResService;
import com.kkgame.feeop.util.DatabaseException;

public class ResAction extends BaseAction {

	private static Log logger = LogFactory.getLog(ResAction.class);
	
	private ResVO resVO;
	
	private ResService resService;
	
	private List<ResVO> resVOList;

	public String doList() {
		if(resVO == null) {
			resVO = new ResVO();
		}
		int pid = resVO.getParentId();
		try {
			
			resVOList = resService.getResByParent(pid);
			resVO.setId(resVO.getParentId());
			ResVO rVO = resService.getRes(resVO);
			int ppId = 0;
			String pname = "顶级资源";
			if(rVO!=null) {
				if(rVO.getParentId()!=0) {
					ResVO res = new ResVO();
					res.setId(rVO.getParentId());
					ResVO r = resService.getRes(res);
					if(r!=null) {
						pname = r.getResDesc();
						ppId = r.getId();
					}
				}
			}
			resVO.setParentId(pid);
			resVO.setPpId(ppId);
			resVO.setResDesc(pname);
		} catch (DatabaseException e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
		}
//		int ppid = 0;
				
		return ACTION_RESULT_LIST;
	}
	
	public String doCreate() {
		if(resVO == null) {
			resVO = new ResVO();
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(resVO == null) {
			resVO = new ResVO();
		}
		try {
			
			resService.create(resVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("创建资源信息成功!");
		return null;
	}
	
	public String doModify() {
		if(resVO == null) {
			resVO = new ResVO();
		}
		try {
			
			resVO = resService.getRes(resVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doUpdate() {
		if(resVO == null) {
			resVO = new ResVO();
		}
		try {
			
			resService.update(resVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("修改资源信息成功!");
		return null;
	}
	
	public String doDelete() {
		if(resVO == null) {
			resVO = new ResVO();
		}
		try {
			
			resService.delete(resVO);
		} catch (Exception e) {
			logger.debug(e);
			errorMsg = "系统出错，请重试或联系管理员";
			printMessage("-1");
			return null;
		}
		printMessage("删除资源信息成功!");
		return null;
	}
	
	public ResVO getResVO() {
		return resVO;
	}

	public void setResVO(ResVO resVO) {
		this.resVO = resVO;
	}

	public ResService getResService() {
		return resService;
	}

	public void setResService(ResService resService) {
		this.resService = resService;
	}

	public List<ResVO> getResVOList() {
		return resVOList;
	}

	public void setResVOList(List<ResVO> resVOList) {
		this.resVOList = resVOList;
	}
}
