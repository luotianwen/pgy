	package com.kkgame.feeop.detail.dao;

	import java.util.List;

	import com.kkgame.feeop.detail.bean.AdvSentsListModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface AdvSentsListModelDAO {

	List<AdvSentsListModelVO> getAdvSentsListModelVOList(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException;
		

		void create(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException;

		void update(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException;

		AdvSentsListModelVO getAdvSentsListModelVO(AdvSentsListModelVO advSentsListModelVO) throws DatabaseException;

	}

