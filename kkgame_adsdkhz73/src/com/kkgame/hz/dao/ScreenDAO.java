package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.ScreenVO;

public interface ScreenDAO {
	/**
	 * 获得分辨率
	 * 
	 * @param screenVO
	 * @return
	 * @throws DatabaseException
	 */
	public ScreenVO getScreen(ScreenVO screenVO) throws DatabaseException;

	/**
	 * 分辨率LIST
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public List<ScreenVO> getAllScreenList(ScreenVO screenVO)
			throws DatabaseException;

	/**
	 * 删除分辨率
	 * 
	 * @param screenVO
	 * @throws DatabaseException
	 */
	public void delete(ScreenVO screenVO) throws DatabaseException;

	/**
	 * 新增分辨率
	 * 
	 * @param screenVO
	 * @throws DatabaseException
	 */
	public void insert(ScreenVO screenVO) throws DatabaseException;

}
