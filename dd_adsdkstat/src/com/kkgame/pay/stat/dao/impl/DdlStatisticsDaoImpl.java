package com.kkgame.pay.stat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.bean.NameVO;
import com.kkgame.pay.stat.dao.DdlStatisticsDao;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.DatabaseException;

public class DdlStatisticsDaoImpl extends SqlMapClientDaoSupport implements
		DdlStatisticsDao {

	@Override
	public void deleteClickData(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		table.append("DDL_CLICK_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		getSqlMapClientTemplate().delete("ddlStatSqlMap.deleteClickData", map);
	}

	@Override
	public void insertClickData(int i, List<DdlData> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		
		table.append("DDL_CLICK_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("list", list);
		getSqlMapClientTemplate().insert("ddlStatSqlMap.insertClickData", map);
	}
	
	@Override
	public void insertValidClickData(int i, List<DdlData> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		
		table.append("DDL_CLICK_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("list", list);
		getSqlMapClientTemplate().insert("ddlStatSqlMap.insertValidClickData", map);
	}
	
	@Override
	public List<DdlData> getTotalClickList(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("DDL_CLICK_DATA_").append(CalendarFormat.getYM(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("ddlStatSqlMap.getTotalClickList",map);
	}
	
	@Override
	public void insertTotalClickData(int i, List<DdlData> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		table.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("list", list);
		getSqlMapClientTemplate().delete("ddlStatSqlMap.insertTotalClickData", map);
	}
	
	@Override
	public List<DdlData> getSendData(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("ddlStatSqlMap.getSendData",map);
	}
	
	@Override
	public void deleteSaleData(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		table.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		getSqlMapClientTemplate().delete("ddlStatSqlMap.deleteSaleData", map);
	}
	
	@Override
	public void insertSaleData(int i, List<DdlData> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		table.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("list", list);
		getSqlMapClientTemplate().insert("ddlStatSqlMap.insertSaleData", map);
	}
	
	@Override
	public void insertSendSaleData(int i, List<DdlData> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		table.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("list", list);
		getSqlMapClientTemplate().insert("ddlStatSqlMap.insertSendSaleData", map);
	}
	
	@Override
	public void insertChannelList(List<NameVO> list)
			throws DatabaseException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		getSqlMapClientTemplate().insert("ddlStatSqlMap.insertChannelList", map);
	}
	
	@Override
	public void insertProductList(List<NameVO> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		getSqlMapClientTemplate().insert("ddlStatSqlMap.insertProductList", map);
	}
	
	@Override
	public void deleteMonthSaleData(int i) throws DatabaseException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yearMonth", CalendarFormat.getYM(i));
		getSqlMapClientTemplate().delete("ddlStatSqlMap.deleteMonthSaleData", map);
	}
	
	@Override
	public List<DdlData> getMonthSaleData(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer table = new StringBuffer();
		table.append("DDL_SALE_DATA_").append(CalendarFormat.getYM(i));
		map.put("table", table.toString());
		map.put("yearMonth", CalendarFormat.getYM(i));
		
		return getSqlMapClientTemplate().queryForList("ddlStatSqlMap.getMonthSaleData", map);
	}
	
	@Override
	public void insertMonthSaleData(int i, List<DdlData> list)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
			getSqlMapClientTemplate().insert("ddlStatSqlMap.insertMonthSaleData", map);
	}
	@Override
	public List<DdlData> getTotalSaleData(int i) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlStatSqlMap.getTotalSaleData");
	}
	
}
