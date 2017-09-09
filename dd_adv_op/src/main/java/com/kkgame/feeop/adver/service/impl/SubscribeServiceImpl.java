package com.kkgame.feeop.adver.service.impl;

import com.kkgame.feeop.adver.bean.SubscribeVO;
import com.kkgame.feeop.adver.dao.SubscribeDAO;
import com.kkgame.feeop.adver.service.SubscribeService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class SubscribeServiceImpl implements SubscribeService {

	private SubscribeDAO subscribeDAO;

	public SubscribeDAO getubscribeDAO() {
		return subscribeDAO;
	}

	public void setubscribeDAO(SubscribeDAO subscribeDAO) {
		this.subscribeDAO = subscribeDAO;
	}

	public void create(SubscribeVO subscribeVO) throws DatabaseException {

		subscribeDAO.create(subscribeVO);
	}

	public SubscribeVO getSubscribeVO(SubscribeVO subscribeVO) throws DatabaseException {

		return subscribeDAO.getSubscribeVO(subscribeVO);
	}

	@Override
	public void copy(SubscribeVO subscribeVO) {
		subscribeDAO.copy(subscribeVO);
	}

	@Override
	public List<SubscribeVO> getSelectSubscribeVOList(SubscribeVO subscribeVO) {
		return subscribeDAO.getSelectSubscribeVOList(subscribeVO);
	}

	@Override
	public void insertSubs(List<SubscribeVO> subscribeVOs) {
		subscribeDAO.insertSubs(subscribeVOs);
	}

	public List<SubscribeVO> getSubscribeVOList(SubscribeVO subscribeVO)
			throws DatabaseException {

		return subscribeDAO.getSubscribeVOList(subscribeVO);
	}

	public void update(SubscribeVO subscribeVO) throws DatabaseException {

		subscribeDAO.update(subscribeVO);
	}

	public SubscribeDAO getSubscribeDAO() {
		return subscribeDAO;
	}

	public void setSubscribeDAO(SubscribeDAO subscribeDAO) {
		this.subscribeDAO = subscribeDAO;
	}
}
