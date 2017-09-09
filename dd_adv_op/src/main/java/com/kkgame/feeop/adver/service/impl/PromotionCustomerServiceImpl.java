package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.PromotionCustomerVO;
import com.kkgame.feeop.adver.dao.PromotionCustomerDAO;
import com.kkgame.feeop.adver.service.PromotionCustomerService;
import com.kkgame.feeop.util.DatabaseException;

public class PromotionCustomerServiceImpl implements PromotionCustomerService {

	private PromotionCustomerDAO promotionCustomerDAO;

	public PromotionCustomerDAO getPromotionCustomerDAO() {
		return promotionCustomerDAO;
	}

	public void setPromotionCustomerDAO(PromotionCustomerDAO promotionCustomerDAO) {
		this.promotionCustomerDAO = promotionCustomerDAO;
	}

	public void create(PromotionCustomerVO promotionCustomerVO) throws DatabaseException {

		promotionCustomerDAO.create(promotionCustomerVO);
	}

	public PromotionCustomerVO getPromotionCustomerVO(PromotionCustomerVO promotionCustomerVO) throws DatabaseException {

		return promotionCustomerDAO.getPromotionCustomerVO(promotionCustomerVO);
	}

	public List<PromotionCustomerVO> getPromotionCustomerVOList(PromotionCustomerVO promotionCustomerVO)
			throws DatabaseException {

		return promotionCustomerDAO.getPromotionCustomerVOList(promotionCustomerVO);
	}

	public void update(PromotionCustomerVO promotionCustomerVO) throws DatabaseException {

		promotionCustomerDAO.update(promotionCustomerVO);
	}

}
