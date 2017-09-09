package com.kkgame.pay.stat.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.UserData;
import com.kkgame.pay.stat.dao.FeeDao;
import com.kkgame.pay.stat.util.CalendarFormat;
import com.kkgame.pay.stat.util.Constants;

public class FeeDaoImpl extends SqlMapClientDaoSupport implements FeeDao {

	
	@Override
	public List<UserData> getUserDataList(int i,int type) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_USER_MAP.get(type)).append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		map.put("type", type);
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getUserDataList",map);
	}
	
	@Override
	public List<UserData> getVersionUserDataList(int i,int type) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_USER_MAP.get(type)).append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		map.put("type", type);
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionUserDataList",map);
	}
	
	@Override
	public List<UserData> getUserActiveDataList(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_ACTIVE_MAP.get(type)).append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("type", type);
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getUserActiveDataList",map);
	}
	
	@Override
	public List<UserData> getVersionUserActiveDataList(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_ACTIVE_MAP.get(type)).append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("type", type);
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionUserActiveDataList",map);
	}
	
	@Override
	public List<UserData> getUserProjectActiveDataList(int i, int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_PROJECT_ACTIVE_MAP.get(type)).append(CalendarFormat.getyyyyMMdd(i));
		map.put("type", type);
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getUserProjectActiveDataList",map);
	}
	
	@Override
	public List<UserData> getVersionUserProjectActiveDataList(int i, int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_PROJECT_ACTIVE_MAP.get(type)).append(CalendarFormat.getyyyyMMdd(i));
		map.put("type", type);
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionUserProjectActiveDataList",map);
	}
	
	@Override
	public List<UserData> getTotalUserActiveDataList(int i, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("actusers_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getTotalUserActiveDataList",map);
	}
	
	@Override
	public List<UserData> getTotalVersionUserActiveDataList(int i, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("actusers_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getTotalVersionUserActiveDataList",map);
	}
	
	@Override
	public List<Data> getClickData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_click_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getClickData",map);
	}
	
	@Override
	public List<Data> getDownloadData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_download_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getDownloadData",map);
	}
	
	@Override
	public List<Data> getInstallData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_installed_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getInstallData",map);
	}
	
	@Override
	public List<Data> getActiveData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_activate_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getActiveData",map);
	}
	
	@Override
	public List<Data> getShowData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_show_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getShowData",map);
	}
	
	@Override
	public List<UserData> getRetentionUserDataList(int day,int i,int type,int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.TYPE_ACTIVE_MAP.get(type)).append(j).append("_").append(CalendarFormat.getyyyyMMdd(day));
		map.put("type", type);
		map.put("registerDate", CalendarFormat.getYmd(i+1));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getRetentionUserDataList",map);
	}
	
	
	@Override
	public List<Data> getAdReceiveData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_sents_list_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("table", sb.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getAdReceiveData",map);
	}
	
	@Override
	public List<Data> getAdSendData(int i,int type,int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_sents_list_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("table", sb.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getAdSendData",map);
	}
	

	@Override
	public List<Data> getProjectReceiveData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_sents_list_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("table", sb.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectReceiveData",map);
	}
	
	@Override
	public List<Data> getProjectSendData(int i,int type, int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_sents_list_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("table", sb.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectSendData",map);
	}
	
	@Override
	public List<Data> getProjectShowData(int i,int type, int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_show_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectShowData",map);
	}
	
	
	@Override
	public List<Data> getProjectClickData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("adv_feeback_list_click_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectClickData",map);
	}
	
	@Override
	public List<Data> getProjectDownloadData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_download_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectDownloadData",map);
	}
	
	@Override
	public List<Data> getProjectInstallData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_installed_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectInstallData",map);
	}
	
	@Override
	public List<Data> getProjectActiveData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_activate_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getProjectActiveData",map);
	}
	
	@Override
	public List<Data> getVersionProjectClickData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("adv_feeback_list_click_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectClickData",map);
	}
	
	@Override
	public List<Data> getVersionProjectDownloadData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_download_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectDownloadData",map);
	}
	
	@Override
	public List<Data> getVersionProjectInstallData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_installed_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectInstallData",map);
	}
	
	@Override
	public List<Data> getVersionProjectActiveData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_activate_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectActiveData",map);
	}

	@Override
	public List<Data> getVersionProjectReceiveData(int i,int type, int j)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_sents_list_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("table", sb.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectReceiveData",map);
	}
	
	@Override
	public List<Data> getVersionProjectSendData(int i,int type, int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_sents_list_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("table", sb.toString());
		map.put("statDate", CalendarFormat.getYmd(i));
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectSendData",map);
	}
	
	@Override
	public List<Data> getVersionProjectShowData(int i,int type, int j) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("adv_feeback_list_show_").append(j).append("_").append(CalendarFormat.getyyyyMMdd(i));

		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getVersionProjectShowData",map);
	}
	
	@Override
	public List<Data> getAdBackData(int i) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("a_cphc_").append(CalendarFormat.getyyyyMMdd(i));
		map.put("statDate", CalendarFormat.getYmd(i));
		map.put("table", sb.toString());
		return getSqlMapClientTemplate().queryForList("feeSqlMap.getAdBackData",map);
	}
}
