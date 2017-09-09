package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.LinkadsconfigVO;
import com.kkgame.feeop.adver.dao.LinkadsconfigDAO;
import com.kkgame.feeop.adver.service.LinkadsconfigService;
import com.kkgame.feeop.util.DatabaseException;

public class LinkadsconfigServiceImpl implements LinkadsconfigService {

	private LinkadsconfigDAO linkadsconfigDAO;

	public LinkadsconfigDAO getLinkadsconfigDAO() {
		return linkadsconfigDAO;
	}

	public void setLinkadsconfigDAO(LinkadsconfigDAO linkadsconfigDAO) {
		this.linkadsconfigDAO = linkadsconfigDAO;
	}

	public void create(LinkadsconfigVO linkadsconfigVO) throws DatabaseException {

		linkadsconfigDAO.create(linkadsconfigVO);
	}

	public LinkadsconfigVO getLinkadsconfigVO(LinkadsconfigVO linkadsconfigVO) throws DatabaseException {

		return linkadsconfigDAO.getLinkadsconfigVO(linkadsconfigVO);
	}

	public List<LinkadsconfigVO> getLinkadsconfigVOList(LinkadsconfigVO linkadsconfigVO)
			throws DatabaseException {

		return linkadsconfigDAO.getLinkadsconfigVOList(linkadsconfigVO);
	}

	public void update(LinkadsconfigVO linkadsconfigVO) throws DatabaseException {

		linkadsconfigDAO.update(linkadsconfigVO);
	}

}
