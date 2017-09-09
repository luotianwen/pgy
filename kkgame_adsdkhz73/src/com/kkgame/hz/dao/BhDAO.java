package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BhVO;

public interface BhDAO {
	public int insert(BhVO bhVO) throws DatabaseException;

	public void update(BhVO bhVO) throws DatabaseException;

	public void delete(int id) throws DatabaseException;

	public BhVO getBhById(int id) throws DatabaseException;

	public List<BhVO> getBhList(BhVO bhVO) throws DatabaseException;

	public List<BhVO> getBhByCriteria(BhVO bhVO) throws DatabaseException;

}
