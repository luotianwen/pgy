package com.kkgame.hz.service.impl;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.ProductDAO;
import com.kkgame.hz.entities.ProductVO;
import com.kkgame.hz.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	public Integer create(ProductVO productVO) throws DatabaseException {
		return productDAO.create(productVO);
	}
	
	public void delete(ProductVO productVO) throws DatabaseException {
		productDAO.delete(productVO);
	}
	
	public List<ProductVO> getAllProductVOList(ProductVO productVO) throws DatabaseException {
		return productDAO.getAllProductVOList(productVO);
	}
	
	public ProductVO getProductVO(ProductVO productVO) throws DatabaseException {
		return productDAO.getProductVO(productVO);
	}
	
	public List<ProductVO> getProductVOList(ProductVO productVO)
			throws DatabaseException {
		return productDAO.getProductVOList(productVO);
	}
	
	public void update(ProductVO productVO) throws DatabaseException {
		productDAO.update(productVO);
	}
	
	public ProductVO getProductVOByName(ProductVO productVO)
			throws DatabaseException {

		return productDAO.getProductVOByName(productVO);
	}
	
	public List<ProductVO> getProductVOListByProductIds(String productIds)
			throws DatabaseException {
		return productDAO.getProductVOListByProductIds(productIds);
	}

	@Override
	public List<ProductVO> getAllProductVOSubscribeList(ProductVO productVO) throws DatabaseException {
		return productDAO.getAllProductVOSubscribeList(productVO);
	}

	@Override
	public List<ProductVO> getProductVOSubscribeList(ProductVO productVO) throws DatabaseException {
		return productDAO.getProductVOSubscribeList(productVO);
	}

	@Override
	public int createSubscribe(ProductVO productVO) throws DatabaseException {
		return productDAO.createSubscribe(productVO);
	}

	@Override
	public ProductVO getProductVOSubscribe(ProductVO productVO) throws DatabaseException {
		return productDAO.getProductVOSubscribe(productVO);
	}

	@Override
	public void updateSubscribe(ProductVO productVO) throws DatabaseException {
		  productDAO.updateSubscribe(productVO);
	}

	@Override
	public void deleteSubscribe(ProductVO productVO) throws DatabaseException {
		  productDAO.deleteSubscribe(productVO);
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}
