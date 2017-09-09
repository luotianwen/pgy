package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BdVO;

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
