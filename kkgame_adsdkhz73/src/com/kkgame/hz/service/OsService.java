package com.kkgame.hz.service;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.OsVO;

public interface OsService {
	/**
	 * 获取平台
	 * 
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public OsVO getOs(OsVO osVO) throws DatabaseException;

	public void updateFlag(OsVO osVO) throws DatabaseException;

	/**
	 * 新增平台
	 * 
	 * @param osVO
	 * @throws DatabaseException
	 */
	public void insertOs(OsVO osVO) throws DatabaseException;

	/**
	 * 删除平台
	 * 
	 * @param id
	 * @throws DatabaseException
	 */
	public void deleteOs(OsVO osVO) throws DatabaseException;

	/**
	 * 获取平台LIST
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public List<OsVO> getOsList(OsVO osVO) throws DatabaseException;

	public List<OsVO> getOsVOList(OsVO osVO) throws DatabaseException;

	void updateOs(OsVO osVO) throws DatabaseException;

	OsVO getOsByName(OsVO osVO) throws DatabaseException;
}
