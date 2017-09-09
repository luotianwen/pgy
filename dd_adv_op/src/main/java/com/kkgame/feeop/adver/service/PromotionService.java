	package com.kkgame.feeop.adver.service;
	import java.util.List;

	import com.kkgame.feeop.adver.bean.PromotionVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface PromotionService {

		List<PromotionVO> getPromotionVOList(PromotionVO promotionVO) throws DatabaseException;
		

		void create(PromotionVO promotionVO) throws DatabaseException;
		
		void update(PromotionVO promotionVO) throws DatabaseException;
		

		PromotionVO getPromotionVO(PromotionVO promotionVO) throws DatabaseException;

		List<PromotionVO> getPromotionIframeVOList(PromotionVO promotionVO) throws DatabaseException;


		int createIframe(PromotionVO promotionVO) throws DatabaseException;

		void updateIframe(PromotionVO promotionVO) throws DatabaseException;


		PromotionVO getPromotionIframeVO(PromotionVO promotionVO) throws DatabaseException;

	}

