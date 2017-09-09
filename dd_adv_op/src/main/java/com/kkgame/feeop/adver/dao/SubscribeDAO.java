	package com.kkgame.feeop.adver.dao;

	import com.kkgame.feeop.adver.bean.SubscribeVO;
	import com.kkgame.feeop.util.DatabaseException;

	import java.util.List;

	public interface SubscribeDAO {

	List<SubscribeVO> getSubscribeVOList(SubscribeVO subscribeVO) throws DatabaseException;
		

		void create(SubscribeVO subscribeVO) throws DatabaseException;

		void update(SubscribeVO subscribeVO) throws DatabaseException;

		SubscribeVO getSubscribeVO(SubscribeVO subscribeVO) throws DatabaseException;

		void copy(SubscribeVO subscribeVO);

		void insertSubs(List<SubscribeVO> subscribeVOs);

		List<SubscribeVO> getSelectSubscribeVOList(SubscribeVO subscribeVO);
	}

