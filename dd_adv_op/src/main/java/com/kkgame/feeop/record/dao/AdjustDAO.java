	package com.kkgame.feeop.record.dao;

	import java.util.List;

	import com.kkgame.feeop.record.bean.AdjustVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface AdjustDAO {

	List<AdjustVO> getAdjustVOList(AdjustVO adjustVO) throws DatabaseException;
		

		void create(AdjustVO adjustVO) throws DatabaseException;

		void update(AdjustVO adjustVO) throws DatabaseException;

		AdjustVO getAdjustVO(AdjustVO adjustVO) throws DatabaseException;

	}

