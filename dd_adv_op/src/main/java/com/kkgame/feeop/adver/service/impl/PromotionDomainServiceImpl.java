package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.PromotionDomainVO;
import com.kkgame.feeop.adver.dao.PromotionDomainDAO;
import com.kkgame.feeop.adver.service.PromotionDomainService;
import com.kkgame.feeop.util.DatabaseException;

public class PromotionDomainServiceImpl implements PromotionDomainService {

	private PromotionDomainDAO promotionDomainDAO;

	public PromotionDomainDAO getPromotionDomainDAO() {
		return promotionDomainDAO;
	}

	public void setPromotionDomainDAO(PromotionDomainDAO promotionDomainDAO) {
		this.promotionDomainDAO = promotionDomainDAO;
	}

	public void create(PromotionDomainVO promotionDomainVO) throws DatabaseException {

		promotionDomainDAO.create(promotionDomainVO);
	}

	public PromotionDomainVO getPromotionDomainVO(PromotionDomainVO promotionDomainVO) throws DatabaseException {

		return promotionDomainDAO.getPromotionDomainVO(promotionDomainVO);
	}

	@Override
	public void delete(PromotionDomainVO promotionDomainVO) {
		promotionDomainDAO.delete(promotionDomainVO);
	}

	public List<PromotionDomainVO> getPromotionDomainVOList(PromotionDomainVO promotionDomainVO)
			throws DatabaseException {

		return promotionDomainDAO.getPromotionDomainVOList(promotionDomainVO);
	}

	public void update(PromotionDomainVO promotionDomainVO) throws DatabaseException {

		promotionDomainDAO.update(promotionDomainVO);
	}

	public void createIframe(PromotionDomainVO promotionDomainVO) throws DatabaseException {

		promotionDomainDAO.createIframe(promotionDomainVO);
	}

	public PromotionDomainVO getPromotionDomainIframeVO(PromotionDomainVO promotionDomainVO) throws DatabaseException {

		return promotionDomainDAO.getPromotionDomainIframeVO(promotionDomainVO);
	}

	@Override
	public void deleteIframe(PromotionDomainVO promotionDomainVO) {
		promotionDomainDAO.deleteIframe(promotionDomainVO);
	}

	public List<PromotionDomainVO> getPromotionDomainIframeVOList(PromotionDomainVO promotionDomainVO)
			throws DatabaseException {

		return promotionDomainDAO.getPromotionDomainIframeVOList(promotionDomainVO);
	}

	public void updateIframe(PromotionDomainVO promotionDomainVO) throws DatabaseException {

		promotionDomainDAO.updateIframe(promotionDomainVO);
	}

}
