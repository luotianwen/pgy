package com.kkgame.feeop.customer.dao;

import java.util.List;

import com.kkgame.feeop.customer.bean.BdVO;
import com.kkgame.feeop.util.DatabaseException;


public interface BdDAO {
	public int insert(BdVO bdVO) throws DatabaseException;

	public void update(BdVO bdVO) throws DatabaseException;

	public void delete(int id) throws DatabaseException;

	public BdVO getBdById(int id) throws DatabaseException;

	public List<BdVO> getBdList(BdVO bdVO) throws DatabaseException;

	public List<BdVO> getBdVOList(BdVO bdVO) throws DatabaseException;

	public List<BdVO> getBdByCriteria(BdVO bdVO) throws DatabaseException;

	public BdVO getBdByName(BdVO bdVO) throws DatabaseException;

	List<BdVO> getAllBdList(BdVO bdVO) throws DatabaseException;

}
