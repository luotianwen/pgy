	package com.kkgame.feeop.record.service;

	import com.kkgame.feeop.record.bean.OperatorVO;
	import com.kkgame.feeop.util.DatabaseException;

	import java.util.List;

	public interface OperatorService {

		List<OperatorVO> getOperatorVOList(OperatorVO operatorVO) throws DatabaseException;
		

		void create(OperatorVO operatorVO) throws DatabaseException;
		
		void update(OperatorVO operatorVO) throws DatabaseException;
		

		OperatorVO getOperatorVO(OperatorVO operatorVO) throws DatabaseException;


		List<OperatorVO> getAllOperatorVOList();

        List<OperatorVO> getOperatorsByCou(OperatorVO operatorVO);
    }

