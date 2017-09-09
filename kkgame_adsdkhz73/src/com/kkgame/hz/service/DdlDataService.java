package com.kkgame.hz.service;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.DdlDataVO;

public interface DdlDataService {

	List<DdlDataVO> getDdlDataVOListDay(BillSearchVO billSearchVO) throws DatabaseException;

	void insert(DdlDataVO ddlDataVO) throws DatabaseException;

}
