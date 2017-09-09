	package com.kkgame.feeop.adver.service;
	import java.util.List;

	import com.kkgame.feeop.adver.bean.Linkads2VO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface Linkads2Service {

		List<Linkads2VO> getLinkads2VOList(Linkads2VO linkads2VO) throws DatabaseException;
		

		void create(Linkads2VO linkads2VO) throws DatabaseException;
		
		void update(Linkads2VO linkads2VO) throws DatabaseException;
		

		Linkads2VO getLinkads2VO(Linkads2VO linkads2VO) throws DatabaseException;


		void copy(Linkads2VO linkads2VO)throws DatabaseException;
	}

