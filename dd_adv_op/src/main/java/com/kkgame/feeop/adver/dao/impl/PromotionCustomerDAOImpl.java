	package com.kkgame.feeop.adver.dao.impl;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.adver.bean.PromotionCustomerVO;
import com.kkgame.feeop.adver.dao.PromotionCustomerDAO;
import com.kkgame.feeop.util.DatabaseException;

public class PromotionCustomerDAOImpl extends SqlMapClientDaoSupport implements
		PromotionCustomerDAO { 

	//新增
	public void create(PromotionCustomerVO promotionCustomerVO) throws DatabaseException {
		int id = (Integer)getSqlMapClientTemplate().insert("promotionCustomerSqlMap.create", promotionCustomerVO);
	}
	 
	
	public PromotionCustomerVO getPromotionCustomerVO(PromotionCustomerVO promotionCustomerVO) throws DatabaseException {
		return (PromotionCustomerVO) getSqlMapClientTemplate().queryForObject("promotionCustomerSqlMap.getPromotionCustomerVO", promotionCustomerVO);
	}
	
	public List<PromotionCustomerVO> getPromotionCustomerVOList(PromotionCustomerVO promotionCustomerVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("promotionCustomerSqlMap.getPromotionCustomerVOListCount", promotionCustomerVO);
		promotionCustomerVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("promotionCustomerSqlMap.getPromotionCustomerVOList", promotionCustomerVO);
	}
	
	
	//修改
	public void update(PromotionCustomerVO promotionCustomerVO) throws DatabaseException {
		getSqlMapClientTemplate().update("promotionCustomerSqlMap.update", promotionCustomerVO);
	}
	
 

	 
	 

}
