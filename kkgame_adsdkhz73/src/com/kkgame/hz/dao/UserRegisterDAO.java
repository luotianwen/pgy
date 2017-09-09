package com.kkgame.hz.dao;

import java.util.List;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.entities.ProjectVO;
import com.kkgame.hz.entities.UserRegisterVO;

public interface UserRegisterDAO {

	
	/**
	 * 查找所有客户信息
	 * @param customerVO
	 * @return
	 * @throws DatabaseException
	 */
	public List<CustomerVO> getCustomerList(CustomerVO customerVO) throws DatabaseException;
	
	/**
	 * 查找客户的项目
	 * @param 
	 * @return
	 * @throws DatabaseException
	 */
	public List<ProjectVO> getProjectList(ProjectVO projectVO) throws DatabaseException;
	
	/**
	 * 用户激活数据查询
	 * @param billSearchVO
	 * @return
	 * @throws DatabaseException
	 */
	public List<UserRegisterVO> getUserRegisterVOListMonth(BillSearchVO billSearchVO) throws DatabaseException;
	
	public List<UserRegisterVO> getUserRegisterVOListDay(
			BillSearchVO billSearchVO) throws DatabaseException;

	public List<UserRegisterVO> getUserRegisterPercentVOListDay(
			BillSearchVO billSearchVO) throws DatabaseException;
	
	public void insert(UserRegisterVO userRegisterVO) throws DatabaseException;

	public void update(UserRegisterVO userRegisterVO) throws DatabaseException;

	List<ProjectVO> getSubscribeProjectList(ProjectVO projectVO)throws DatabaseException;
}
