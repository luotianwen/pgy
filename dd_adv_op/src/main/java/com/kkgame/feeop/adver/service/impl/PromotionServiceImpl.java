package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.PromotionVO;
import com.kkgame.feeop.adver.dao.PromotionDAO;
import com.kkgame.feeop.adver.service.PromotionService;
import com.kkgame.feeop.util.DatabaseException;

public class PromotionServiceImpl implements PromotionService {

	private PromotionDAO promotionDAO;

	public PromotionDAO getPromotionDAO() {
		return promotionDAO;
	}

	public void setPromotionDAO(PromotionDAO promotionDAO) {
		this.promotionDAO = promotionDAO;
	}

	public void create(PromotionVO promotionVO) throws DatabaseException {

		promotionDAO.create(promotionVO);
	}

	public PromotionVO getPromotionVO(PromotionVO promotionVO) throws DatabaseException {

		return promotionDAO.getPromotionVO(promotionVO);
	}



	public List<PromotionVO> getPromotionVOList(PromotionVO promotionVO)
			throws DatabaseException {

		return promotionDAO.getPromotionVOList(promotionVO);
	}

	public void update(PromotionVO promotionVO) throws DatabaseException {

		promotionDAO.update(promotionVO);
	}

	public int createIframe(PromotionVO promotionVO) throws DatabaseException {

		return promotionDAO.createIframe(promotionVO);
	}

	public PromotionVO getPromotionIframeVO(PromotionVO promotionVO) throws DatabaseException {

		return promotionDAO.getPromotionIframeVO(promotionVO);
	}



	public List<PromotionVO> getPromotionIframeVOList(PromotionVO promotionVO)
			throws DatabaseException {

		return promotionDAO.getPromotionIframeVOList(promotionVO);
	}

	public void updateIframe(PromotionVO promotionVO) throws DatabaseException {

		promotionDAO.updateIframe(promotionVO);
	}
}
