package com.kkgame.feeop.detail.service.impl;

import com.kkgame.feeop.detail.bean.LinkDetailVO;
import com.kkgame.feeop.detail.dao.LinkDetailDAO;
import com.kkgame.feeop.detail.service.LinkDetailService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class LinkDetailServiceImpl implements LinkDetailService {

	private LinkDetailDAO sinkDetailDAO;

	@Override
	public List<LinkDetailVO> getLinkDetailVOList(LinkDetailVO sinkDetailVO) throws DatabaseException {
		// TODO Auto-generated method stub
		return sinkDetailDAO.getLinkDetailVOList(sinkDetailVO);
	}
	
	public LinkDetailDAO getLinkDetailDAO() {
		return sinkDetailDAO;
	}

	public void setLinkDetailDAO(LinkDetailDAO sinkDetailDAO) {
		this.sinkDetailDAO = sinkDetailDAO;
	}
}
