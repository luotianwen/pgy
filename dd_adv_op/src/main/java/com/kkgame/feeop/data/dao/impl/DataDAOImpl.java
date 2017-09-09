package com.kkgame.feeop.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.dao.DataDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

public class DataDAOImpl extends SqlMapClientDaoSupport implements DataDAO {

	@Override
	public void insertAdLinkIncomeList(List<AdLinkDataVO> list, String statDate) throws DatabaseException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", month);
		map.put("list", list);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.insertAdLinkIncomeList", map);
	}
	
	@Override
	public void updateAdLinkPercent(DataVO data) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("dataSqlMap.updateAdLinkPercent", data);

	}
	
	@Override
	public void updateAdLinkProjectPercent(DataVO data) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("dataSqlMap.updateAdLinkProjectPercent", data);

	}
	
	@Override
	public void insertAdIncomeList(List<DataVO> list, String statDate) throws DatabaseException {
		// TODO Auto-generated method stub
		String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", month);
		map.put("list", list);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.insertAdIncomeList", map);
	}
	
	@Override
	public void insertProjectIncomeDataList(List<DataVO> list, String statDate) throws DatabaseException {
		// TODO Auto-generated method stub
		String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", month);
		map.put("list", list);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.insertProjectIncomeDataList", map);
	}
	
	@Override
	public void insertProjectIncomeData(DataVO data) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(data.getStatDate(),"yyyy-MM-dd","yyyyMM");
		data.setTable(month);
		data.setYearMonth(Integer.parseInt(month));
		getSqlMapClientTemplate().insert("dataSqlMap.insertProjectIncomeData", data);
	}
	
	@Override
	public void updateProjectIncomePercent(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().update("dataSqlMap.updateProjectIncomePercent", data);
	}
	
	@Override
	public void updateHzProjectIncome(DataVO data) throws DatabaseException {

		getSqlMapClientTemplate().update("dataSqlMap.updateHzProjectIncome", data);
	}
	
	@Override
	public void updateProjectOutcome(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().update("dataSqlMap.updateProjectOutcome", data);
	}
	
	
	@Override
	public void updateProjectUsers(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().update("dataSqlMap.updateProjectUsers", data);
	}
	
	@Override
	public void updateProjectUsersNeedBack(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().update("dataSqlMap.updateProjectUsersNeedBack", data);
	}
	
	@Override
	public void updateProjectOutcomeRate(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().insert(
				"dataSqlMap.updateProjectOutcomeRateFirst", data);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.updateProjectOutcomeRateSecond", data);
	}
	
	@Override
	public void insertAdIncome(DataVO data) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(data.getStatDate(),"yyyy-MM-dd","yyyyMM");
		data.setTable(month);
		data.setYearMonth(Integer.parseInt(month));
		getSqlMapClientTemplate().update("dataSqlMap.insertAdIncome", data);
		
	}
	
	@Override
	public void updateAdPercent(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().update("dataSqlMap.updateAdPercent", data);
		
	}
	
	@Override
	public void updateAdProjectPercent(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().update("dataSqlMap.updateAdProjectPercent", data);
		
	}
	
	@Override
	public List<DataVO> getProjectIncome(DataVO data) throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("dataSqlMap.getProjectIncome",data);
	}
	
	@Override
	public List<DataVO> getAdLinkIncome(DataVO data) throws DatabaseException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("dataSqlMap.getAdLinkIncome",data);
	}
	
	@Override
	public void insertAdLinkIncome(List<DataVO> list, String table) throws DatabaseException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", table);
		map.put("list", list);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.insertAdLinkIncome", map);
	}
	
	@Override
	public void insertProjectIncome(List<DataVO> list,String month)
			throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", month);
		map.put("list", list);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.insertProjectIncome", map);
	}
	
	@Override
	public void updateExpectIncome(DataVO data) throws DatabaseException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("dataSqlMap.updateAdCpm", data);

	}
	
	@Override
	public void updateAdCpm(DataVO data) throws DatabaseException {

		getSqlMapClientTemplate().update("dataSqlMap.updateAdCpm", data);
	}
	
	@Override
	public List<DataVO> getCpmList(DataVO data) throws DatabaseException {
		return getSqlMapClientTemplate().queryForList("dataSqlMap.getCpmList",data);
	}
	
	@Override
	public void insertCpmList(List<DataVO> list) throws DatabaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		getSqlMapClientTemplate().insert(
				"dataSqlMap.insertCpmList", map);
	}
	
	@Override
	public void updateMonthUserIncomeData(DataVO data) throws DatabaseException {
		getSqlMapClientTemplate().insert(
				"dataSqlMap.updateMonthUserIncomeData", data);
	}
}
