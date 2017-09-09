package com.kkgame.feeop.detail.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.detail.bean.DdlDetailVO;
import com.kkgame.feeop.detail.dao.DdlDetailDAO;
import com.kkgame.feeop.util.DatabaseException;

public class DdlDetailDAOImpl extends SqlMapClientDaoSupport implements
		DdlDetailDAO {

	@Override
	public List<DdlDetailVO> getDdlAdjustDetailVOList(DdlDetailVO ddlDetailVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("ddlDetailSqlMap.getDdlAdjustDetailVOListCount",ddlDetailVO); 
		ddlDetailVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("ddlDetailSqlMap.getDdlAdjustDetailVOList", ddlDetailVO);
	}

	@Override
	public List<DdlDetailVO> getDdlSdkDetailVOList(DdlDetailVO ddlDetailVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("ddlDetailSqlMap.getDdlSdkDetailVOListCount",ddlDetailVO); 
		ddlDetailVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("ddlDetailSqlMap.getDdlSdkDetailVOList", ddlDetailVO);
	}

}
