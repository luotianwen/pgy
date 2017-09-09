package com.kkgame.hz.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkfun.exceptions.DatabaseException;
import com.kkgame.hz.dao.UserDivideDAO;
import com.kkgame.hz.entities.BillSearchVO;
import com.kkgame.hz.entities.SpecialDataVO;
import com.kkgame.hz.entities.UserDivideVO;
import com.kkgame.hz.util.CalendarFormat;

public class UserDivideDAOImpl extends SqlMapClientDaoSupport implements
		UserDivideDAO {

	public List<UserDivideVO> getUserDivideVOList(BillSearchVO billSearchVO)
			throws DatabaseException {
		String month = billSearchVO.getSearchMonth();
		billSearchVO.setTable("DIVIDE_STAT_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("userDivideSqlMap.getUserDivideVOListMonth", billSearchVO);
	}

	public List<UserDivideVO> getUserDivideVOInterfaceList(
			BillSearchVO billSearchVO) throws DatabaseException {
		String searchDay = billSearchVO.getSearchDay();
		String month = CalendarFormat.switchFormatDate(searchDay, CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		billSearchVO.setTable("DIVIDE_STAT_"+month);
		return getSqlMapClientTemplate().queryForList("userDivideSqlMap.getUserDivideVOListInterfaceDay", billSearchVO);
	}
	
	public List<UserDivideVO> getUserDivideVOMenLongList(
			BillSearchVO billSearchVO) throws DatabaseException {
		billSearchVO.setTable("DIVIDE_DATA");
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("userDivideSqlMap.getUserDivideVOMenLongList",billSearchVO);
	}

	public List<UserDivideVO> getUserDivideVOListDay(BillSearchVO billSearchVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(billSearchVO.getStartTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		billSearchVO.setTable("DIVIDE_STAT_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		
		return getSqlMapClientTemplate().queryForList("userDivideSqlMap.getUserDivideVOListDay",billSearchVO);
	}
	
	public void createDivideData(UserDivideVO userDivideVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(userDivideVO.getTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		userDivideVO.setTable("DIVIDE_STAT_"+month);
		
		getSqlMapClientTemplate().insert("userDivideSqlMap.createDivideData",userDivideVO );
	}	
	
	public void createRegisterData(UserDivideVO userDivideVO)
			throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(userDivideVO.getTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		userDivideVO.setTable("REGISTER_DATA_"+month);
		
		getSqlMapClientTemplate().insert("userDivideSqlMap.createRegisterData",userDivideVO );		
	}
	
	public List<SpecialDataVO> getSpecialDataVOListDay(BillSearchVO billSearchVO)
			throws DatabaseException {
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());
		return getSqlMapClientTemplate().queryForList("userDivideSqlMap.getSpecialDataVOListDay",billSearchVO);
	}
	
	@Override
	public void insert(UserDivideVO userDivideVO) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("userDivideSqlMap.insert", userDivideVO);

	}

	@Override
	public List<SpecialDataVO> getSpecialsubscribeDataVOListDay(BillSearchVO billSearchVO) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(billSearchVO.getStartTime(), CalendarFormat.YYYY_MM_DD, CalendarFormat.YYYYMM);
		billSearchVO.setTable("PROJECT_SUBSCRIBE_"+month);
		String[] rowFields = billSearchVO.getRowFieldString().substring(0, billSearchVO.getRowFieldString().length()-1).split(",");
		billSearchVO.setRowFields(rowFields);
		billSearchVO.getRowFieldVO().setShowRowField(billSearchVO.getRowFields());

		return getSqlMapClientTemplate().queryForList("userDivideSqlMap.getSpecialsubscribeDataVOListDay",billSearchVO);
	}

	@Override
	public void insertSubscribe(SpecialDataVO u) throws DatabaseException {
		getSqlMapClientTemplate().insert("userDivideSqlMap.insertsubscribe", u);
	}
}
