package com.kkgame.hz.service;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BhVO;
import com.kkgame.hz.entities.PortalUserVO;

public interface BhService {

	public void insert(BhVO bhVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	public boolean checkBhNameExist(BhVO bhVO) throws DatabaseException;

	public List<BhVO> getBhList(BhVO bhVO) throws DatabaseException;

	public BhVO getBhById(int id) throws DatabaseException;

	public void update(BhVO bhVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	public void delete(int id, int portalUserId) throws DatabaseException;
}
