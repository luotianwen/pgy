package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.DdlDataVO;

public interface DdlDataDAO {

	List<DdlDataVO> getDdlDataVOListDay(BillSearchVO billSearchVO) throws DatabaseException;

	void insert(DdlDataVO ddlDataVO) throws DatabaseException;

}
