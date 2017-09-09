package com.kkgame.feeop.detail.service.impl;

import com.kkgame.feeop.detail.bean.IframeDetailVO;
import com.kkgame.feeop.detail.bean.IframeVO;
import com.kkgame.feeop.detail.dao.IframeDetailDAO;
import com.kkgame.feeop.detail.service.IframeDetailService;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public class IframeDetailServiceImpl implements IframeDetailService {

	private IframeDetailDAO iframeDetailDAO;

	@Override
	public List<IframeDetailVO> getIframeDetailVOList(IframeDetailVO iframeDetailVO) throws DatabaseException {
		return iframeDetailDAO.getIframeDetailVOList(iframeDetailVO);
	}

	@Override
	public List<IframeVO> getIframeDataVOList(IframeDetailVO iframeDataVO) {
		return iframeDetailDAO.getIframeDataVOList(iframeDataVO);
	}

	public IframeDetailDAO getIframeDetailDAO() {
		return iframeDetailDAO;
	}

	public void setIframeDetailDAO(IframeDetailDAO iframeDetailDAO) {
		this.iframeDetailDAO = iframeDetailDAO;
	}
}
