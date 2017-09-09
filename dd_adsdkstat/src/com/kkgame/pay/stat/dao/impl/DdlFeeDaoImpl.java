package com.kkgame.pay.stat.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kkgame.pay.stat.bean.DdlData;
import com.kkgame.pay.stat.bean.NameVO;
import com.kkgame.pay.stat.dao.DdlFeeDao;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.DatabaseException;

public class DdlFeeDaoImpl extends SqlMapClientDaoSupport implements DdlFeeDao {

	@Override
	public List<DdlData> getClickDataList(int i,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("CLICKS_LOG_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getClickDataList",map);
	}
	
	@Override
	public List<DdlData> getValidClickDataList(int i, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("CLICKS_LOG_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getValidClickDataList",map);
	}
	
	@Override
	public List<DdlData> getSaleDataList(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("PRODUCTSALE_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getSaleDataList",map);
	}
	
	@Override
	public List<DdlData> getSendSaleDataList(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("PRODUCTSALE_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getSendSaleDataList",map);
	}
	
	@Override
	public List<NameVO> getChannelList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getChannelList");
	}
	
	@Override
	public List<NameVO> getProductList() throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("ddlSqlMap.getProductList");
	}
	
	@Override
	public void updateTotalSaleData(DdlData data) throws DatabaseException {

		getSqlMapClientTemplate().update("ddlSqlMap.updateTotalSaleData", data);
	}
}
