package com.kkgame.feeop.customer.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kkgame.feeop.base.BaseAction;
import com.kkgame.feeop.customer.bean.ProductVO;
import com.kkgame.feeop.customer.service.ProductService;
import com.kkgame.feeop.util.DatabaseException;

public class ProductAction extends BaseAction {

	private static Log logger = LogFactory.getLog(ProductAction.class);
	
	private ProductService productService;
	
	private ProductVO productVO;
	
	private List<ProductVO> productVOList;

	public String doQuery() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		return ACTION_RESULT_QUERY;
	}
	
	public String doList() {
		if(productVO == null) {
			productVO = new ProductVO();
		}		
		try {
			productVOList = productService.getProductVOList(productVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		return ACTION_RESULT_LIST;
	}
	
	public String getProductForCheck() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		try {
			productVOList = productService.getAllProductVOList(productVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			this.printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("-1");
			return null;
		}
		return "success";
	}
	
	public String doCreate() {
		if(productVO == null) {
			productVO = new ProductVO();
			productVO.setType(-1);
		}
		return ACTION_RESULT_CREATE;
	}
	
	public String doSave() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		int productId = 0;
		try {
			productVO.setBdDividePercent(productVO.getBdDividePercent()/100.0);
			productVO.setCuDividePercent(productVO.getCuDividePercent()/100.0);
			productId = productService.create(productVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("产品信息创建成功!");
		return null;
	}
	
	public String doModify() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		
		try {
			productVO = productService.getProductVO(productVO);
			productVO.setType(2);
		} catch (DatabaseException e) {
			logger.debug(e);	
			printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}		
		return ACTION_RESULT_CREATE;
	}
	
	public String doUpdate() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		int productId = productVO.getId();
		try {
			productService.update(productVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("产品信息修改成功!");
		return null;
	}
	
	public String doDelete() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		try {
			productService.delete(productVO);
		} catch (DatabaseException e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			printMessage("-1");
			return null;
		}
		printMessage("产品信息删除成功!");
		return null;
	}
	
	public String doVaildName() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		try {
			ProductVO pvo = productService.getProductVOByName(productVO);
			if(pvo == null) {
				this.printMessage("false");
			} else {
				this.printMessage("true");
			}
		}catch (DatabaseException e) {
			logger.debug(e);
			this.printMessage("true");
			return null;
		} catch (Exception e) {
			logger.debug(e);
			this.printMessage("true");
			return null;
		}
		return null;
	}
	
	public String getAllProduct() {
		if(productVO == null) {
			productVO = new ProductVO();
		}
		try {
			productVOList = productService.getAllProductVOList(productVO);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

	public List<ProductVO> getProductVOList() {
		return productVOList;
	}

	public void setProductVOList(List<ProductVO> productVOList) {
		this.productVOList = productVOList;
	}
}
