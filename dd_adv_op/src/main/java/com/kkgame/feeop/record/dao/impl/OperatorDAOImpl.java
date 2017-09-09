package com.kkgame.feeop.record.dao.impl;

import com.kkgame.feeop.record.bean.OperatorVO;
import com.kkgame.feeop.record.dao.OperatorDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

public class OperatorDAOImpl extends SqlMapClientDaoSupport implements
		OperatorDAO {

	// 新增
	public void create(OperatorVO operatorVO) throws DatabaseException {
		getSqlMapClientTemplate().insert(
				"operatorSqlMap.create", operatorVO);
	}

	public OperatorVO getOperatorVO(OperatorVO operatorVO)
			throws DatabaseException {
		return (OperatorVO) getSqlMapClientTemplate().queryForObject(
				"operatorSqlMap.getOperatorVO", operatorVO);
	}

	@Override
	public List<OperatorVO> getAllOperatorVO() {
		int count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"operatorSqlMap.getOperatorVOListCount"
						);
		return getSqlMapClientTemplate().queryForList(
				"operatorSqlMap.getAllOperatorVOList");
	}

	@Override
	public List<OperatorVO> getOperatorsByCou(OperatorVO operatorVO) {
		return getSqlMapClientTemplate().queryForList(
				"operatorSqlMap.getOperatorsByCou",operatorVO);
	}

	public List<OperatorVO> getOperatorVOList(
			OperatorVO operatorVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"operatorSqlMap.getOperatorVOListCount",
						operatorVO);
		operatorVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList(
				"operatorSqlMap.getOperatorVOList", operatorVO);
	}
	
	// 修改
	public void update(OperatorVO operatorVO) throws DatabaseException {
		getSqlMapClientTemplate().update("operatorSqlMap.update",
				operatorVO);
	}

}
