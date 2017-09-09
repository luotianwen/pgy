	package com.kkgame.feeop.adver.dao;

	import java.util.List;

	import com.kkgame.feeop.adver.bean.PromotionCustomerVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface PromotionCustomerDAO {

	List<PromotionCustomerVO> getPromotionCustomerVOList(PromotionCustomerVO promotionCustomerVO) throws DatabaseException;
		

		void create(PromotionCustomerVO promotionCustomerVO) throws DatabaseException;

		void update(PromotionCustomerVO promotionCustomerVO) throws DatabaseException;

		PromotionCustomerVO getPromotionCustomerVO(PromotionCustomerVO promotionCustomerVO) throws DatabaseException;

	}

