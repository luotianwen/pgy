package com.kkgame.feeop.customer.dao;

import com.kkgame.feeop.customer.bean.DdlVO;
import com.kkgame.feeop.util.DatabaseException;

import java.util.List;

public interface DdlDAO {

	DdlVO getDdlVO(DdlVO ddlVO) throws DatabaseException;
	
	void update(DdlVO ddlVO) throws DatabaseException;

	List<DdlVO> getDdlVOClickList(DdlVO ddlVO) throws DatabaseException;
}
