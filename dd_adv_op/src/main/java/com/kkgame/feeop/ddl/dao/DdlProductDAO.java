package com.kkgame.feeop.ddl.dao;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DdlProductVO;
import com.kkgame.feeop.tag.bean.CountryVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlProductDAO {

	List<DdlProductVO> getDdlProductVOList(DdlProductVO ddlProductVO) throws DatabaseException;
	List<DdlProductVO> getAllDdlProduct(DdlProductVO ddlProductVO) throws DatabaseException;
	DdlProductVO getDdlProductVO(DdlProductVO ddlProductVO) throws DatabaseException;
	void update(DdlProductVO ddlProductVO) throws DatabaseException;
	void insert(DdlProductVO ddlProductVO) throws DatabaseException;
	public List<CountryVO> getDdlCountryList(String valueCode) throws DatabaseException;

}
