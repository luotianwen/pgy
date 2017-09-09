	package com.kkgame.feeop.adver.dao;

	import com.kkgame.feeop.adver.bean.SpromotionVO;
	import com.kkgame.feeop.util.DatabaseException;

	import java.util.List;

	public interface SpromotionDAO {

	List<SpromotionVO> getSpromotionVOList(SpromotionVO spromotionVO) throws DatabaseException;
		

		void create(SpromotionVO spromotionVO) throws DatabaseException;

		void update(SpromotionVO spromotionVO) throws DatabaseException;

		SpromotionVO getSpromotionVO(SpromotionVO spromotionVO) throws DatabaseException;

	}

