	package com.kkgame.feeop.adver.service;
	import java.util.List;

	import com.kkgame.feeop.adver.bean.PcustomerVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface PcustomerService {

		List<PcustomerVO> getPcustomerVOList(PcustomerVO pcustomerVO) throws DatabaseException;
		

		void create(PcustomerVO pcustomerVO) throws DatabaseException;
		
		void update(PcustomerVO pcustomerVO) throws DatabaseException;
		

		PcustomerVO getPcustomerVO(PcustomerVO pcustomerVO) throws DatabaseException;

		List<PcustomerVO> getPcustomerIframeVOList(PcustomerVO pcustomerVO) throws DatabaseException;


		void createIframe(PcustomerVO pcustomerVO) throws DatabaseException;

		void updateIframe(PcustomerVO pcustomerVO) throws DatabaseException;


		PcustomerVO getPcustomerIframeVO(PcustomerVO pcustomerVO) throws DatabaseException;

	}

