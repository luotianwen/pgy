package com.kkgame.hz.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.base.BaseAction;
import com.kkgame.hz.entities.OsVO;
import com.kkgame.hz.service.OsService;

public class OsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(OsAction.class);
	private OsService osService;
	private OsVO osVO;
	private List<OsVO> osVOList;
	private List<OsVO> osList;
	private static final String ACTION_RESUTL_CLIENTMATCHMODIFY = "cmmodify";
	private static final String ACTION_RESUTL_CLIENTMATCHLIST = "cmlist";
	private static final String ACTION_RESUTL_CLIENTMATCHCREATE = "cmcreate";

	public String doList() {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			osList = osService.getOsList(osVO);
			osVOList = osService.getOsVOList(osVO);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return ACTION_RESULT_LIST;
	}

	public String doCreate() {
		return ACTION_RESULT_CREATE;
	}

	public String doSave() {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			if (osService.getOsByName(osVO) == null) {
				osService.insertOs(osVO);
			} else {
				printMessage("-1");
				return null;
			}
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("创建成功");
		return null;
	}

	public String doValidOsName() throws UnsupportedEncodingException {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			osVO = osService.getOsByName(osVO);
			if (osVO != null) {
				this.addActionMessage("true");
			} else {
				this.addActionMessage("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PlAIN_MESSAGE;
	}

	public String doModify() {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			osVO = osService.getOs(osVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_MODIFY;
	}

	public String doUpdate() {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			if (osService.getOsByName(osVO) == null) {
				osService.updateOs(osVO);
			} else {
				printMessage("-1");
				return null;
			}

		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			printMessage("-1");
			return null;
		}
		printMessage("更新成功");
		return null;
	}

	public String doDelete() {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			osService.deleteOs(osVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			printMessage("-1");
			return null;
		}
		printMessage("删除成功");
		return null;
	}

	public String doClientMatchCreate() {
		if (osVO == null) {
			osVO = new OsVO();
		}
		try {
			osVOList = osService.getOsList(osVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ACTION_RESUTL_CLIENTMATCHCREATE;
	}

	public OsService getOsService() {
		return osService;
	}

	public void setOsService(OsService osService) {
		this.osService = osService;
	}

	public OsVO getOsVO() {
		return osVO;
	}

	public void setOsVO(OsVO osVO) {
		this.osVO = osVO;
	}

	public List<OsVO> getOsVOList() {
		return osVOList;
	}

	public void setOsVOList(List<OsVO> osVOList) {
		this.osVOList = osVOList;
	}

	public List<OsVO> getOsList() {
		return osList;
	}

	public void setOsList(List<OsVO> osList) {
		this.osList = osList;
	}
}
