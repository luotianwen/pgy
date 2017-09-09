package com.kkgame.feeop.ddl.service;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DdlChannelVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlChannelService {

	List<DdlChannelVO> getDdlChannelVOList(DdlChannelVO ddlChannelVO) throws DatabaseException;
	List<DdlChannelVO> getAllDdlChannel(DdlChannelVO ddlChannelVO) throws DatabaseException;

	DdlChannelVO getDdlChannelVO(DdlChannelVO ddlChannelVO) throws DatabaseException;

	void update(DdlChannelVO ddlChannelVO) throws DatabaseException;

	void insert(DdlChannelVO ddlChannelVO) throws DatabaseException;

}
