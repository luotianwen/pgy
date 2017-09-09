package com.kkgame.feeop.customer.dao;

import java.util.List;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ProductDAO {

	List<ProductVO> getProductVOList(ProductVO productVO) throws DatabaseException;

	Integer create(ProductVO productVO) throws DatabaseException;

	ProductVO getProductVO(ProductVO productVO) throws DatabaseException;

	void update(ProductVO productVO) throws DatabaseException;

	void delete(ProductVO productVO) throws DatabaseException;
	
	List<ProductVO> getAllProductVOList(ProductVO productVO) throws DatabaseException;

	ProductVO getProductVOByName(ProductVO productVO) throws DatabaseException;
	
	List<ProductVO> getProductVOListByProductIds(String productIds) throws DatabaseException;

}
