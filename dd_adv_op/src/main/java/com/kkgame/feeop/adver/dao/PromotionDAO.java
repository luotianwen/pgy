	package com.kkgame.feeop.adver.dao;

	import java.util.List;

	import com.kkgame.feeop.adver.bean.PromotionVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface PromotionDAO {

	List<PromotionVO> getPromotionVOList(PromotionVO promotionVO) throws DatabaseException;
		

		void create(PromotionVO promotionVO) throws DatabaseException;

		void update(PromotionVO promotionVO) throws DatabaseException;

		PromotionVO getPromotionVO(PromotionVO promotionVO) throws DatabaseException;
		List<PromotionVO> getPromotionIframeVOList(PromotionVO promotionVO) throws DatabaseException;


		int createIframe(PromotionVO promotionVO) throws DatabaseException;

		void updateIframe(PromotionVO promotionVO) throws DatabaseException;

		PromotionVO getPromotionIframeVO(PromotionVO promotionVO) throws DatabaseException;

	}

