	package com.kkgame.feeop.adver.service;
	import java.util.List;

	import com.kkgame.feeop.adver.bean.PromotionDomainVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface PromotionDomainService {

		List<PromotionDomainVO> getPromotionDomainVOList(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		

		void create(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		
		void update(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		

		PromotionDomainVO getPromotionDomainVO(PromotionDomainVO promotionDomainVO) throws DatabaseException;


		void delete(PromotionDomainVO promotionDomainVO);

		List<PromotionDomainVO> getPromotionDomainIframeVOList(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		void createIframe(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		void updateIframe(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		PromotionDomainVO getPromotionDomainIframeVO(PromotionDomainVO promotionDomainVO) throws DatabaseException;
		void deleteIframe(PromotionDomainVO promotionDomainVO);
	}

