package com.kkgame.hz.service;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.ProductVO;

public interface ProductService {

	List<ProductVO> getProductVOList(ProductVO productVO) throws DatabaseException;

	Integer create(ProductVO productVO) throws DatabaseException;

	ProductVO getProductVO(ProductVO productVO) throws DatabaseException;

	void update(ProductVO productVO) throws DatabaseException;

	void delete(ProductVO productVO) throws DatabaseException;
	
	List<ProductVO> getAllProductVOList(ProductVO productVO) throws DatabaseException;

	ProductVO getProductVOByName(ProductVO productVO) throws DatabaseException;

	List<ProductVO> getProductVOListByProductIds(String productIds) throws DatabaseException;

	List<ProductVO> getAllProductVOSubscribeList(ProductVO productVO) throws DatabaseException;

	List<ProductVO> getProductVOSubscribeList(ProductVO productVO)throws DatabaseException;

	int createSubscribe(ProductVO productVO)throws DatabaseException;

	ProductVO getProductVOSubscribe(ProductVO productVO)throws DatabaseException;

	void updateSubscribe(ProductVO productVO)throws DatabaseException;

	void deleteSubscribe(ProductVO productVO)throws DatabaseException;
}
