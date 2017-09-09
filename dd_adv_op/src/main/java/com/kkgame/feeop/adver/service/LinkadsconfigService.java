	package com.kkgame.feeop.adver.service;
	import java.util.List;

	import com.kkgame.feeop.adver.bean.LinkadsconfigVO;
	import com.kkgame.feeop.util.DatabaseException;

	public interface LinkadsconfigService {

		List<LinkadsconfigVO> getLinkadsconfigVOList(LinkadsconfigVO linkadsconfigVO) throws DatabaseException;
		

		void create(LinkadsconfigVO linkadsconfigVO) throws DatabaseException;
		
		void update(LinkadsconfigVO linkadsconfigVO) throws DatabaseException;
		

		LinkadsconfigVO getLinkadsconfigVO(LinkadsconfigVO linkadsconfigVO) throws DatabaseException;
		
		
		
	}
