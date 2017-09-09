package com.kkgame.feeop.adver.service.impl;

import java.util.List;

import com.kkgame.feeop.adver.bean.PcustomerVO;
import com.kkgame.feeop.adver.dao.PcustomerDAO;
import com.kkgame.feeop.adver.service.PcustomerService;
import com.kkgame.feeop.util.DatabaseException;

public class PcustomerServiceImpl implements PcustomerService {

	private PcustomerDAO pcustomerDAO;

	public PcustomerDAO getPcustomerDAO() {
		return pcustomerDAO;
	}

	public void setPcustomerDAO(PcustomerDAO pcustomerDAO) {
		this.pcustomerDAO = pcustomerDAO;
	}

	public void create(PcustomerVO pcustomerVO) throws DatabaseException {

		pcustomerDAO.create(pcustomerVO);
	}

	public PcustomerVO getPcustomerVO(PcustomerVO pcustomerVO) throws DatabaseException {

		return pcustomerDAO.getPcustomerVO(pcustomerVO);
	}



	public List<PcustomerVO> getPcustomerVOList(PcustomerVO pcustomerVO)
			throws DatabaseException {

		return pcustomerDAO.getPcustomerVOList(pcustomerVO);
	}

	public void update(PcustomerVO pcustomerVO) throws DatabaseException {

		pcustomerDAO.update(pcustomerVO);
	}



	public void createIframe(PcustomerVO pcustomerVO) throws DatabaseException {

		pcustomerDAO.createIframe(pcustomerVO);
	}

	public PcustomerVO getPcustomerIframeVO(PcustomerVO pcustomerVO) throws DatabaseException {

		return pcustomerDAO.getPcustomerIframeVO(pcustomerVO);
	}



	public List<PcustomerVO> getPcustomerIframeVOList(PcustomerVO pcustomerVO)
			throws DatabaseException {

		return pcustomerDAO.getPcustomerIframeVOList(pcustomerVO);
	}

	public void updateIframe(PcustomerVO pcustomerVO) throws DatabaseException {

		pcustomerDAO.updateIframe(pcustomerVO);
	}
}
