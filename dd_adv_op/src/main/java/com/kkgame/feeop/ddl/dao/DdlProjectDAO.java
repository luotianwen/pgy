package com.kkgame.feeop.ddl.dao;

import java.util.List;

import com.kkgame.feeop.ddl.bean.DdlProjectVO;
import com.kkgame.feeop.ddl.bean.DomainVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DdlProjectDAO {

	List<DdlProjectVO> getDdlProjectVOList(DdlProjectVO ddlProjectVO) throws DatabaseException;
	
	List<DdlProjectVO> getAllDdlProjectVO(DdlProjectVO ddlProjectVO) throws DatabaseException;

	DdlProjectVO getDdlProjectVO(DdlProjectVO ddlProjectVO) throws DatabaseException;

	void update(DdlProjectVO ddlProjectVO) throws DatabaseException;

	void insert(DdlProjectVO ddlProjectVO) throws DatabaseException;

	List<DomainVO> getAllDomainVO() throws DatabaseException;

	DomainVO getDomainVO(int id) throws DatabaseException;

	void insertDomain(DomainVO domainVO) throws DatabaseException;

	void updateDomain(DomainVO domainVO) throws DatabaseException;
}
