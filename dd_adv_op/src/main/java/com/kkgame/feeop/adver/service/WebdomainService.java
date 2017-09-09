	package com.kkgame.feeop.adver.service;
	import java.util.List;

	import com.kkgame.feeop.adver.bean.WebdomainVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface WebdomainService {

		List<WebdomainVO> getWebdomainVOList(WebdomainVO webdomainVO) throws DatabaseException;
		

		void create(WebdomainVO webdomainVO) throws DatabaseException;
		
		void update(WebdomainVO webdomainVO) throws DatabaseException;
		void delete(WebdomainVO webdomainVO) throws DatabaseException;

		WebdomainVO getWebdomainVO(WebdomainVO webdomainVO) throws DatabaseException;
		
		
		
	}

