package com.kkgame.hz.service;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BdVO;
import com.kkgame.hz.entities.PortalUserVO;

public interface BdService {

	public void insert(BdVO bdVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	public boolean checkBdNameExist(BdVO bdVO) throws DatabaseException;

	public List<BdVO> getBdList(BdVO bdVO) throws DatabaseException;

	public BdVO getBdById(int id) throws DatabaseException;

	public void update(BdVO bdVO, PortalUserVO portalUserVO)
			throws DatabaseException;

	public void delete(int id, int portalUserId) throws DatabaseException;

	public List<BdVO> getAllBdList(BdVO bdVO) throws DatabaseException;

	public BdVO getBdByName(BdVO bdVO) throws DatabaseException;

	public List<BdVO> getBdVOList(BdVO bdVO) throws DatabaseException;

}
