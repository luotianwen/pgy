package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.WebdomainVO;
import com.kkgame.feeop.adver.dao.WebdomainDAO;
import com.kkgame.feeop.adver.service.WebdomainService;
import com.kkgame.feeop.util.DatabaseException;

public class WebdomainServiceImpl implements WebdomainService {

	private WebdomainDAO webdomainDAO;

	public WebdomainDAO getWebdomainDAO() {
		return webdomainDAO;
	}

	public void setWebdomainDAO(WebdomainDAO webdomainDAO) {
		this.webdomainDAO = webdomainDAO;
	}

	public void create(WebdomainVO webdomainVO) throws DatabaseException {

		webdomainDAO.create(webdomainVO);
	}

	public WebdomainVO getWebdomainVO(WebdomainVO webdomainVO) throws DatabaseException {

		return webdomainDAO.getWebdomainVO(webdomainVO);
	}

	public List<WebdomainVO> getWebdomainVOList(WebdomainVO webdomainVO)
			throws DatabaseException {

		return webdomainDAO.getWebdomainVOList(webdomainVO);
	}

	public void update(WebdomainVO webdomainVO) throws DatabaseException {

		webdomainDAO.update(webdomainVO);
	}

	public void delete(WebdomainVO webdomainVO) throws DatabaseException {
		webdomainDAO.delete(webdomainVO);
	}

}
