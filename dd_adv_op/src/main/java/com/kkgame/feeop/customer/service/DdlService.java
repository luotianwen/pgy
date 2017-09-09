package com.kkgame.feeop.customer.service;

import com.kkgame.feeop.customer.bean.DdlVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface DdlService {

	DdlVO getDdlVO(DdlVO ddlVO) throws DatabaseException;
	
	void update(DdlVO ddlVO) throws DatabaseException;

	List<DdlVO> getDdlVOClickList(DdlVO ddlVO) throws DatabaseException;

}
