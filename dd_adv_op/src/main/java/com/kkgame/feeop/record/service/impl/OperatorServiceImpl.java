package com.kkgame.feeop.record.service.impl;

import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.record.dao.OperatorDAO;
import com.kkgame.feeop.record.service.OperatorService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class OperatorServiceImpl implements OperatorService {

	private OperatorDAO operatorDAO;

	public OperatorDAO getOperatorDAO() {
		return operatorDAO;
	}

	public void setOperatorDAO(OperatorDAO operatorDAO) {
		this.operatorDAO = operatorDAO;
	}

	public void create(OperatorVO operatorVO) throws DatabaseException {

		operatorDAO.create(operatorVO);
	}

	public OperatorVO getOperatorVO(OperatorVO operatorVO) throws DatabaseException {

		return operatorDAO.getOperatorVO(operatorVO);
	}

	@Override
	public List<OperatorVO> getAllOperatorVOList() {
		return operatorDAO.getAllOperatorVO();
	}

	@Override
	public List<OperatorVO> getOperatorsByCou(OperatorVO operatorVO) {
		return operatorDAO.getOperatorsByCou(operatorVO);
	}

	public List<OperatorVO> getOperatorVOList(OperatorVO operatorVO)
			throws DatabaseException {

		return operatorDAO.getOperatorVOList(operatorVO);
	}
	

	public void update(OperatorVO operatorVO) throws DatabaseException {

		operatorDAO.update(operatorVO);
	}

}
