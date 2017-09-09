package com.kkgame.hz.service.impl;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.OsDAO;
import com.kkgame.hz.entities.OsVO;
import com.kkgame.hz.service.OsService;

public class OsServiceImpl implements OsService {

	private OsDAO osDAO;

	public void deleteOs(OsVO osVO) throws DatabaseException {

		osDAO.deleteOs(osVO);
	}

	public List<OsVO> getOsList(OsVO osVO) throws DatabaseException {

		return osDAO.getOsList(osVO);
	}

	public void insertOs(OsVO osVO) throws DatabaseException {

		osDAO.insertOs(osVO);
	}

	public OsDAO getOsDAO() {
		return osDAO;
	}

	public void setOsDAO(OsDAO osDAO) {
		this.osDAO = osDAO;
	}

	public void updateFlag(OsVO osVO) throws DatabaseException {

		osDAO.updateFlag(osVO);
	}

	public OsVO getOs(OsVO osVO) throws DatabaseException {
		return osDAO.getOs(osVO);
	}

	public void updateOs(OsVO osVO) throws DatabaseException {
		this.osDAO.updateOs(osVO);
	}

	public List<OsVO> getOsVOList(OsVO osVO) throws DatabaseException {
		return this.osDAO.getOsVOList(osVO);
	}

	public OsVO getOsByName(OsVO osVO) throws DatabaseException {
		return this.osDAO.getOsByName(osVO);
	}
}
