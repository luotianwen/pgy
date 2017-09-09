	package com.kkgame.feeop.detail.dao;

	import java.util.List;

	import com.kkgame.feeop.detail.bean.AdvFeebackListClickModelVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface AdvFeebackListClickModelDAO {

	List<AdvFeebackListClickModelVO> getAdvFeebackListClickModelVOList(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException;
		

		void create(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException;

		void update(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException;

		AdvFeebackListClickModelVO getAdvFeebackListClickModelVO(AdvFeebackListClickModelVO advFeebackListClickModelVO) throws DatabaseException;

	}

