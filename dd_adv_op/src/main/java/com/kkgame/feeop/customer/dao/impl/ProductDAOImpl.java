package com.kkgame.feeop.customer.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.dao.ProductDAO;
import com.kkgame.feeop.util.DatabaseException;

public class ProductDAOImpl extends SqlMapClientDaoSupport implements
		ProductDAO {

	public Integer create(ProductVO productVO) throws DatabaseException {
		return (Integer)getSqlMapClientTemplate().insert("productSqlMap.insert", productVO);
	}
	
	public void delete(ProductVO productVO) throws DatabaseException {
		getSqlMapClientTemplate().delete("productSqlMap.delete", productVO);
	}
	
	public List<ProductVO> getAllProductVOList(ProductVO productVO) throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("productSqlMap.getAllProductVOList");
	}
	
	public ProductVO getProductVO(ProductVO productVO) throws DatabaseException {
		return (ProductVO) getSqlMapClientTemplate().queryForObject("productSqlMap.getProductVO", productVO);
	}
	
	public ProductVO getProductVOByName(ProductVO productVO)
			throws DatabaseException {
		return (ProductVO) getSqlMapClientTemplate().queryForObject("productSqlMap.getProductVOByName", productVO);
	}
	
	public List<ProductVO> getProductVOList(ProductVO productVO)
			throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"productSqlMap.getProductVOListCount", productVO);
		productVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("productSqlMap.getProductVOList",productVO);
	}
	
	public List<ProductVO> getProductVOListByProductIds(String productIds)
			throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("productSqlMap.getProductVOListByProductIds",productIds);
	}
	
	public void update(ProductVO productVO) throws DatabaseException {
		getSqlMapClientTemplate().update("productSqlMap.update", productVO);
	}
}
