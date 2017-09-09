package com.kkgame.hz.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.ScreenDAO;
import com.kkgame.hz.entities.ScreenVO;
import com.kkgame.hz.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(ScreenServiceImpl.class);

	private ScreenDAO screenDAO;

	public void delete(ScreenVO screenVO) throws DatabaseException {
		screenDAO.delete(screenVO);
	}

	public List<ScreenVO> getAllScreenList(ScreenVO screenVO)
			throws DatabaseException {
		List<ScreenVO> list = screenDAO.getAllScreenList(screenVO);
		for (ScreenVO sVO : list) {
			sVO.setName(sVO.getName() + "[" + sVO.getAlias() + "]");
		}
		return list;
	}

	public void insert(ScreenVO screenVO) throws DatabaseException {
		screenDAO.insert(screenVO);
	}

	public ScreenDAO getScreenDAO() {
		return screenDAO;
	}

	public void setScreenDAO(ScreenDAO screenDAO) {
		this.screenDAO = screenDAO;
	}

	public ScreenVO getScreen(ScreenVO screenVO) throws DatabaseException {
		return screenDAO.getScreen(screenVO);
	}

}
