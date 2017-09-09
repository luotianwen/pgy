package com.kkgame.feeop.data.service.impl;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.data.dao.DataDAO;
import com.kkgame.feeop.data.service.DataService;
import com.kkgame.feeop.sdkinfo.dao.ThreeSdkDAO;
import com.kkgame.feeop.util.CalendarFormat;
import com.kkgame.feeop.util.DatabaseException;

import java.util.*;

public class DataServiceImpl implements DataService {

	private DataDAO dataDAO;
	private ThreeSdkDAO threeSdkDAO;

	@Override
	public void updateAdLinkDataList(List<AdLinkDataVO> list, String statDate) throws DatabaseException {
		// TODO Auto-generated method stub
		String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
		DataVO data = new DataVO();
		data.setStatDate(statDate);
		data.setTable(month);
		data.setYearMonth(Integer.parseInt(month));
		
		dataDAO.insertAdLinkIncomeList(list,statDate);
		dataDAO.updateAdLinkPercent(data);
		dataDAO.updateAdLinkProjectPercent(data);
		
		List<DataVO> incomeList = dataDAO.getAdLinkIncome(data);
		if(incomeList!=null&&incomeList.size()>0) {
			dataDAO.insertAdLinkIncome(incomeList,data.getTable());
		}
		dataDAO.updateExpectIncome(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateMonthUserIncomeData(data);
	}
	
	@Override
	public void updateAdIncomeList(List<DataVO> list,String statDate) throws DatabaseException {
		String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
		DataVO data = new DataVO();
		data.setStatDate(statDate);
		data.setTable(month);
		data.setYearMonth(Integer.parseInt(month));
		dataDAO.insertAdIncomeList(list,statDate);
		dataDAO.updateAdPercent(data);//更新广告比例
		dataDAO.updateAdProjectPercent(data);//更新广告比例
		List<DataVO> incomeList = dataDAO.getProjectIncome(data);
		if(incomeList!=null&&incomeList.size()>0) {
			dataDAO.insertProjectIncome(incomeList,data.getTable());
		}
		//更新项目各个等级人数
		dataDAO.updateExpectIncome(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateMonthUserIncomeData(data);
		//dataDAO.updateAdCpm(data);
	}

	@Override
	public void updateProjectIncomeList(List<DataVO> list,String statDate) throws DatabaseException {
		//更新项目收益
		String month = CalendarFormat.switchFormatDate(statDate,"yyyy-MM-dd","yyyyMM");
		DataVO data = new DataVO();
		data.setStatDate(statDate);
		data.setTable(month);
		data.setYearMonth(Integer.parseInt(month));
		data.setType(600400);

		dataDAO.insertProjectIncomeDataList(list,statDate);
		//更新项目各个等级人数
		dataDAO.updateExpectIncome(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateMonthUserIncomeData(data);
	}

	@Override
	public void insertProjectSdkList(List<DataVO> list, String statDate) throws DatabaseException {
		// 插入明细
		threeSdkDAO.delectProjectThreeList(statDate);
		threeSdkDAO.insertProjectThreeList(list,statDate);
		// 获得项目列表
		Map<Integer, DataVO> map = new HashMap<>();
		int projectId; DataVO data;
		for (DataVO dataVO : list) {
			projectId = dataVO.getProjectId();
			if (map.containsKey(projectId)) {
				data = map.get(projectId);
				data.setThirdIncome(data.getThirdIncome() + dataVO.getThirdIncome());
			} else {
				map.put(projectId, dataVO);
			}
		}
		// 更新项目收入
		updateProjectIncomeList(new ArrayList<>(map.values()), statDate);
	}

	@Override
	public void updateProjectIncome(DataVO data) throws DatabaseException {
		//更新项目收益
		dataDAO.insertProjectIncomeData(data);
		//更新项目个收益
//		dataDAO.updateProjectIncomePercent(data);
		//更新合作项目收益
//		dataDAO.updateHzProjectIncome(data);
		//更新项目各个等级人数
		dataDAO.updateExpectIncome(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		//dataDAO.updateProjectUsersNeedBack(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		//dataDAO.updateProjectOutcome(data);
		//dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateMonthUserIncomeData(data);
	}
	
	@Override
	public void updateProjectIncomeNew(DataVO data) throws DatabaseException {
		//更新项目收益
//		dataDAO.insertProjectIncomeData(data);
		//更新项目个收益
//		dataDAO.updateProjectIncomePercent(data);
		//更新合作项目收益
//		dataDAO.updateHzProjectIncome(data);
		//更新项目各个等级人数
		dataDAO.updateExpectIncome(data);
		dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateProjectUsersNeedBack(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateMonthUserIncomeData(data);
	}
	
	@Override
	public void updateAdIncome(DataVO data) throws DatabaseException {
		dataDAO.insertAdIncome(data);
		dataDAO.updateAdPercent(data);//更新广告比例
		dataDAO.updateAdProjectPercent(data);//更新广告比例
		List<DataVO> incomeList = dataDAO.getProjectIncome(data);
		if(incomeList!=null&&incomeList.size()>0) {
			dataDAO.insertProjectIncome(incomeList,data.getTable());
		}
		//更新项目各个等级人数
		dataDAO.updateExpectIncome(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		dataDAO.updateProjectOutcome(data);
		dataDAO.updateProjectOutcomeRate(data);
		//dataDAO.updateProjectUsersNeedBack(data);
		//dataDAO.updateProjectUsers(data);
		//更新支出
		//dataDAO.updateProjectOutcome(data);
		//dataDAO.updateProjectOutcomeRate(data);
		dataDAO.updateMonthUserIncomeData(data);
		dataDAO.updateAdCpm(data);
//		List<DataVO> cpmList = dataDAO.getCpmList(data);
//		if(cpmList!=null&&cpmList.size()>0) {
//			dataDAO.insertCpmList(cpmList);
//		}
	}
	
	public DataDAO getDataDAO() {
		return dataDAO;
	}

	public void setDataDAO(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public ThreeSdkDAO getThreeSdkDAO() {
		return threeSdkDAO;
	}

	public void setThreeSdkDAO(ThreeSdkDAO threeSdkDAO) {
		this.threeSdkDAO = threeSdkDAO;
	}
}
