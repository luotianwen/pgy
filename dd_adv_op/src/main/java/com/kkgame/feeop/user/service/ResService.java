package com.kkgame.feeop.user.service;

import java.util.List;

import com.kkgame.feeop.user.bean.ResVO;
import com.kkgame.feeop.util.DatabaseException;

public interface ResService {

	public List<ResVO> getResByParent(int pid) throws DatabaseException;

	public void create(ResVO resVO) throws DatabaseException;

	public ResVO getRes(ResVO resVO) throws DatabaseException;

	public void delete(ResVO resVO) throws DatabaseException;

	public void update(ResVO resVO) throws DatabaseException;
	/**
	 * 查询角色所拥有资源模块列表
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public List<ResVO> getResByRole(int id) throws DatabaseException;

}
