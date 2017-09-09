package com.kkgame.pay.stat.dao;

import java.util.List;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.UserData;

public interface StatisticsDao {

	void deleteUserData(int i, int type) throws DatabaseException;

	void deleteTempUserData(int i, int type) throws DatabaseException;

	void insertUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertRetentionUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void deleteUserActiveData(int i, int type) throws DatabaseException;

	void insertUserActiveData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertShowData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertClickData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertDownloadData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertInstallData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertActiveData(int i, int j, List<Data> list)
			throws DatabaseException;

	void insertFirstUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertSecondUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertThirdUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertFourthUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertFifthUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertSixthUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertSeventhUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertFiftyUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertThirtyUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	void insertAdSendData(int i, int type, List<Data> sendList)
			throws DatabaseException;

	void insertAdReceiveData(int i, int type, List<Data> receiveList)
			throws DatabaseException;

	void deleteAdSendData(int i) throws DatabaseException;

	void deleteTempAdSendData(int i) throws DatabaseException;

	void deleteBackData(int i) throws DatabaseException;

	void deleteMonthUserData(int i) throws DatabaseException;

	List<UserData> getMonthUserDataList(int i) throws DatabaseException;

	void insertMonthUserData(int i, int type, List<UserData> list)
			throws DatabaseException;

	List<UserData> getTotalUserData() throws DatabaseException;

	List<Data> getDaySendData(int i) throws DatabaseException;

	void deleteProjectData(int i) throws DatabaseException;

	void insertProjectSendData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertProjectReceiveData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertProjectShowData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertProjectClickData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertProjectDownloadData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertProjectActiveData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertProjectInstallData(int i, int type, List<Data> list)
			throws DatabaseException;

	void insertAdBackData(int i, List<Data> list) throws DatabaseException;

	void insertAdData(int i, List<Data> list) throws DatabaseException;

	List<Data> getAdData(int i) throws DatabaseException;

	void updateAdPercent(int i) throws DatabaseException;

	void updateAdProjectPercent(int i) throws DatabaseException;

	List<Data> getAdProjectData(int i) throws DatabaseException;

	void insertAdProjectData(int i, List<Data> list) throws DatabaseException;

	List<Data> getProjectUserData(int i) throws DatabaseException;

	void insertProjectUserData(int i, int type, List<Data> list)
			throws DatabaseException;

	List<Data> getAdEffectiveData(int i) throws DatabaseException;

	void insertAdEffectiveData(int i, List<Data> effectiveList)
			throws DatabaseException;

	List<Data> getAdProjectEffectiveData(int i) throws DatabaseException;

	void insertAdProjectEffectiveData(int i, List<Data> list)
			throws DatabaseException;

	List<Data> getTotalProjectUserData(int i) throws DatabaseException;

	List<Data> getProjectShow(int i) throws DatabaseException;

	void insertProjectShow(int i, List<Data> list) throws DatabaseException;

	List<Data> getProjectIncome(int i) throws DatabaseException;

	void insertProjectIncome(int i, List<Data> list) throws DatabaseException;

	void updateProjectUsers(int i) throws DatabaseException;

	void updateProjectOutcome(int i) throws DatabaseException;

	void updateProjectUsersNeedBack(int i) throws DatabaseException;

	void updateProjectOutcomeRate(int i) throws DatabaseException;

	void updateAdPrice(int i) throws DatabaseException;

	void updateAdCpm(int i) throws DatabaseException;

	List<Data> getAdCpm(int i) throws DatabaseException;

	void syncTempUserData(int i) throws DatabaseException;

	void syncTempAdData(int i) throws DatabaseException;

	void deleteRetentionUserData(int i, int type) throws DatabaseException;

	void syncTempRetentionUserData(int i) throws DatabaseException;

	void insertTotalUserActiveData(int i, List<UserData> list)
			throws DatabaseException;

	List<UserData> getMonthUserActiveDataList(int i) throws DatabaseException;

	void insertMonthUserActiveData(int i, int type, List<UserData> list)
			throws DatabaseException;

	List<UserData> getMonthUserIncomeDataList(int i) throws DatabaseException;

	void insertMonthUserIncomeData(int i, List<UserData> list)
			throws DatabaseException;

	void updateExpectIncome(int i) throws DatabaseException;

	void insertUserProjectActiveData(int i, int type, List<UserData> list) throws DatabaseException;

	void deleteTempVersionUserData(int i, int type) throws DatabaseException;

	void insertVersionUserData(int i, int type, List<UserData> list) throws DatabaseException;

	void insertVersionUserActiveData(int i, int type, List<UserData> list) throws DatabaseException;

	void insertVersionUserProjectActiveData(int i, int type, List<UserData> list) throws DatabaseException;

	void insertTotalVersionUserActiveData(int i, List<UserData> list) throws DatabaseException;

	void insertVersionProjectSendData(int i, int j, List<Data> firstList) throws DatabaseException;

	void insertVersionProjectReceiveData(int i, int j, List<Data> firstList) throws DatabaseException;

	void insertVersionProjectShowData(int i, int j, List<Data> firstList) throws DatabaseException;

	void insertVersionProjectClickData(int i, int j, List<Data> firstList) throws DatabaseException;

	void insertVersionProjectDownloadData(int i, int j, List<Data> firstList) throws DatabaseException;

	void insertVersionProjectInstallData(int i, int j, List<Data> firstList) throws DatabaseException;

	void insertVersionProjectActiveData(int i, int j, List<Data> firstList) throws DatabaseException;

	void deleteVersionUserData(int i, int type) throws DatabaseException;

	void syncTempVersionUserData(int i) throws DatabaseException;

	void syncTempAdProjectData(int i) throws DatabaseException;

	void deleteAdData(int i) throws DatabaseException;

	void statTotalUserData(int i) throws DatabaseException;

	void statDayUserData(int i) throws DatabaseException;

	void statDayActiveData(int i) throws DatabaseException;

	void statDayInstalledData(int i) throws DatabaseException;

	void insertUserDataProject(int i, int type, List<UserData> list) throws DatabaseException;

	void insertVersionProjectUserData(int i, int type, List<UserData> list) throws DatabaseException;

	List<Data> getFirstProjectUserData(int i) throws DatabaseException;

	void insertFirstProjectUserData(int i, List<Data> list) throws DatabaseException;

	List<Data> getDdlList(int i) throws DatabaseException;

	void insertDdlList(int i, List<Data> list) throws DatabaseException;

	void insertAdLinkData(int i, int type, List<Data> list) throws DatabaseException;

	void updateLinkData(int i) throws DatabaseException;

	void insertAdTotalLinkData(int i) throws DatabaseException;

	void insertAdLinkDataUv(int i, List<Data> insertList)throws DatabaseException;

	List<Data> getLinkPv(int i);

    void insertDssdkData(int i, Integer integer, List<Data> insertList)throws DatabaseException;

	void updateDsData(int i)throws DatabaseException;

	List<Data> getDsPv(int i)throws DatabaseException;

	void insertAdSubData(int i, List<Data> insertList);

	void insertSubTotal(int i,List<Data> insertList);

	void deleteTempAdSubData(int i);

	void insertOfferSdkShowData(int i, List<Data> insertList);

	void insertOfferSdkClickData(int i, List<Data> insertList);

	void insertOfferSdkSendData(int i, List<Data> insertList);

	void deleteOfferSdkData(int i);

	void syncTempAdOfferSdkData(int i);

	void insertOfferSdkTotal(int i, List<Data> insertList);
}
