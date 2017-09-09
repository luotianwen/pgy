package com.kkgame.feeop.data.dao;

import java.util.List;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DataDAO {

	void insertProjectIncomeData(DataVO data) throws DatabaseException;

	void updateProjectIncomePercent(DataVO data) throws DatabaseException;

	void updateHzProjectIncome(DataVO data) throws DatabaseException;

	void updateProjectUsers(DataVO data) throws DatabaseException;

	void updateProjectOutcome(DataVO data) throws DatabaseException;

	void updateProjectUsersNeedBack(DataVO data) throws DatabaseException;

	void updateProjectOutcomeRate(DataVO data) throws DatabaseException;

	void insertAdIncome(DataVO data) throws DatabaseException;

	void updateAdPercent(DataVO data) throws DatabaseException;

	void updateAdProjectPercent(DataVO data) throws DatabaseException;

	List<DataVO> getProjectIncome(DataVO data) throws DatabaseException;

	void insertProjectIncome(List<DataVO> incomeList,String month) throws DatabaseException;

	void updateAdCpm(DataVO data) throws DatabaseException;

	List<DataVO> getCpmList(DataVO data) throws DatabaseException;

	void insertCpmList(List<DataVO> cpmList) throws DatabaseException;

	void updateExpectIncome(DataVO data) throws DatabaseException;

	void updateMonthUserIncomeData(DataVO data) throws DatabaseException;

	void insertAdIncomeList(List<DataVO> list, String statDate) throws DatabaseException;

	void insertProjectIncomeDataList(List<DataVO> list, String statDate) throws DatabaseException;

	void insertAdLinkIncomeList(List<AdLinkDataVO> list, String statDate) throws DatabaseException;

	void updateAdLinkPercent(DataVO data) throws DatabaseException;

	void updateAdLinkProjectPercent(DataVO data) throws DatabaseException;

	List<DataVO> getAdLinkIncome(DataVO data) throws DatabaseException;

	void insertAdLinkIncome(List<DataVO> incomeList, String table) throws DatabaseException;

}
