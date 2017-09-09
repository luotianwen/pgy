package com.kkgame.pay.stat.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.UserData;
import com.kkgame.pay.stat.dao.MasterDao;
import com.kkgame.pay.stat.util.CalendarFormat;

public class MasterDaoImpl extends SqlMapClientDaoSupport implements MasterDao {


	@Override
	public void updateAdCpm(Data data) throws DatabaseException {
		//data.setTable(CalendarFormat.switchFormatDate(data.getStatDate(), "yyyy-MM-dd", "yyyyMM"));
		getSqlMapClientTemplate().update("feeSqlMap.updateAdCpm", data);
	}

	@Override
	public void updateProjectUserData(UserData data) throws DatabaseException {
		getSqlMapClientTemplate().update("feeSqlMap.updateProjectUserData",data);

	}
	
	@Override
	public void insertDaySendData(int i,List<Data> list) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", CalendarFormat.getYM(i));
		map.put("list", list);
		getSqlMapClientTemplate().insert("feeSqlMap.insertDaySendData",map);
	}
	@Override
	public void insertLinkPv(int i,List<Data> list) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", CalendarFormat.getYM(i));
		map.put("list", list);
		getSqlMapClientTemplate().insert("feeSqlMap.insertLinkPv",map);
	}

	@Override
	public void insertDsPv(int i, List<Data> sendDataList) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", CalendarFormat.getYM(i));
		map.put("list", sendDataList);
		getSqlMapClientTemplate().insert("feeSqlMap.insertDsPv",map);
	}

	@Override
	public void putDataToSubscribe(final List<Data> subTotal) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor sme) throws SQLException {
				sme.startBatch();
				for (Data data : subTotal) {
					sme.update("feeSqlMap.updateSub", data);
				}
				sme.executeBatch();
				return null;
			}
		});
	}

	@Override
	public void putDataToOfferSdk(final List<Data> datas) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor sme) throws SQLException {
				sme.startBatch();
				for (Data data : datas) {
					sme.update("feeSqlMap.updateOfferSdk", data);
				}
				sme.executeBatch();
				return null;
			}
		});
	}
}
