package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.OsVO;

public interface OsDAO {

	/**
	 * 获取平台
	 * 
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	OsVO getOs(OsVO osVO) throws DatabaseException;

	OsVO getOsByName(OsVO osVO) throws DatabaseException;

	void updateFlag(OsVO osVO) throws DatabaseException;

	/**
	 * 新增平台
	 * 
	 * @param osVO
	 * @throws DatabaseException
	 */
	void insertOs(OsVO osVO) throws DatabaseException;

	/**
	 * 删除平台
	 * 
	 * @param id
	 * @throws DatabaseException
	 */
	void deleteOs(OsVO osVO) throws DatabaseException;

	void updateOs(OsVO osVO) throws DatabaseException;

	/**
	 * 获取平台LIST
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	List<OsVO> getOsList(OsVO osVO) throws DatabaseException;

	// 用于分页
	List<OsVO> getOsVOList(OsVO osVO) throws DatabaseException;
}
