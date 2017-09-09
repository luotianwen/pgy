package com.kkgame.hz.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.UserRegisterDAO;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.CustomerVO;
import com.kkgame.hz.entities.ProjectVO;
import com.kkgame.hz.entities.UserRegisterVO;
import com.kkgame.hz.util.CalendarFormat;

public class UserRegisterDAOImpl extends SqlMapClientDaoSupport implements UserRegisterDAO {

	public List<CustomerVO> getCustomerList(CustomerVO customerVO)
			throws DatabaseException {

		return (List<CustomerVO>)getSqlMapClientTemplate().queryForList("userRegisterSqlMap.getCustomerList", customerVO);
	}

	public List<ProjectVO> getProjectList(ProjectVO projectVO)
			throws DatabaseException {

		return (List<ProjectVO>)getSqlMapClientTemplate().queryForList("userRegisterSqlMap.getProjectList", projectVO);
	}


	public List<UserRegisterVO> getUserRegisterVOListMonth(
			BillSearchVO billSearchVO) throws DatabaseException {

		String month = billSearchVO.getSearchMonth();
		billSearchVO.setTable("REGISTER_DATA_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		
		return (List<UserRegisterVO>) getSqlMapClientTemplate().queryForList("userRegisterSqlMap.getUserRegisterVOListMonth", billSearchVO);
	}

	public List<UserRegisterVO> getUserRegisterVOListDay(
			BillSearchVO billSearchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(billSearchVO.getStartTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		billSearchVO.setTable("REGISTER_DATA_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		return (List<UserRegisterVO>) getSqlMapClientTemplate().queryForList("userRegisterSqlMap.getUserRegisterVOListDay", billSearchVO);
	}

	public List<UserRegisterVO> getUserRegisterPercentVOListDay(
			BillSearchVO billSearchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(billSearchVO.getStartTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		billSearchVO.setTable("REGISTER_DATA_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		return (List<UserRegisterVO>) getSqlMapClientTemplate().queryForList("userRegisterSqlMap.getUserRegisterPercentVOListDay", billSearchVO);
	}
	
	public void insert(UserRegisterVO userRegisterVO) throws DatabaseException {

		getSqlMapClientTemplate().insert("userRegisterSqlMap.insert", userRegisterVO);
	}

	public void update(UserRegisterVO userRegisterVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(userRegisterVO.getTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		userRegisterVO.setTable("REGISTER_DATA_"+month);
		getSqlMapClientTemplate().update("userRegisterSqlMap.updateLive",userRegisterVO);
	}

	@Override
	public List<ProjectVO> getSubscribeProjectList(ProjectVO projectVO) throws DatabaseException {
		return (List<ProjectVO>)getSqlMapClientTemplate().queryForList("userRegisterSqlMap.getSubscribeProjectList", projectVO);
	}
}
