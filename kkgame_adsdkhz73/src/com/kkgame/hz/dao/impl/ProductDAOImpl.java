package com.kkgame.hz.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.ProductDAO;
import com.kkgame.hz.entities.ProductVO;

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

	@Override
	public List<ProductVO> getAllProductVOSubscribeList(ProductVO productVO) throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("productSqlMap.getAllProductVOSubscribeList");
	}

	@Override
	public List<ProductVO> getProductVOSubscribeList(ProductVO productVO) throws DatabaseException {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"productSqlMap.getProductVOSubscribeListCount", productVO);
		productVO.getPage().setTotalRow(count);
		return getSqlMapClientTemplate().queryForList("productSqlMap.getProductVOSubscribeList",productVO);
	}

	@Override
	public int createSubscribe(ProductVO productVO) throws DatabaseException {
		return (Integer)getSqlMapClientTemplate().insert("productSqlMap.createSubscribe", productVO);
	}

	@Override
	public ProductVO getProductVOSubscribe(ProductVO productVO) throws DatabaseException {
		return (ProductVO) getSqlMapClientTemplate().queryForObject("productSqlMap.getProductVOSubscribe",productVO);
	}

	@Override
	public void updateSubscribe(ProductVO productVO) throws DatabaseException {
		getSqlMapClientTemplate().update("productSqlMap.updateSubscribe",productVO);
	}

	@Override
	public void deleteSubscribe(ProductVO productVO) throws DatabaseException {
		getSqlMapClientTemplate().delete("productSqlMap.deleteSubscribe",productVO);
	}

	public void update(ProductVO productVO) throws DatabaseException {
		getSqlMapClientTemplate().update("productSqlMap.update", productVO);
	}
}
