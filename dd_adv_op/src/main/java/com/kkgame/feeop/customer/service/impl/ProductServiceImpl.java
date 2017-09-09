package com.kkgame.feeop.customer.service.impl;

import java.util.List;

import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.dao.ProductDAO;
import com.kkgame.feeop.customer.service.ProductService;
import com.kkgame.feeop.util.DatabaseException;

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
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}
