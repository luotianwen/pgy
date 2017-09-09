package com.kkgame.feeop.sdkinfo.dao;

import com.kkgame.feeop.sdkinfo.bean.LinkAdverVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface LinkAdverDAO {

	List<LinkAdverVO> getLinkAdverVOList(LinkAdverVO linkAdverVO) throws DatabaseException;
	List<LinkAdverVO> getAllLinkAdverVOList(LinkAdverVO linkAdverVO) throws DatabaseException;

	LinkAdverVO getLinkAdverVO(LinkAdverVO linkAdverVO) throws DatabaseException;
	void insert(LinkAdverVO linkAdverVO) throws DatabaseException;
	void update(LinkAdverVO linkAdverVO) throws DatabaseException;
	void delete(LinkAdverVO linkAdverVO) throws DatabaseException;
}
