package com.kkgame.feeop.data.service;

import java.util.List;

import com.kkgame.feeop.data.bean.AdLinkDataVO;
import com.kkgame.feeop.data.bean.DataVO;
import com.kkgame.feeop.util.DatabaseException;

public interface DataService {

	void updateProjectIncome(DataVO data) throws DatabaseException;
	void updateProjectIncomeNew(DataVO data) throws DatabaseException;
	void updateAdIncome(DataVO data) throws DatabaseException;
	void updateAdIncomeList(List<DataVO> dataVO,String statDate) throws DatabaseException;
	void updateProjectIncomeList(List<DataVO> data,String statDate) throws DatabaseException;
	void insertProjectSdkList(List<DataVO> data,String statDate) throws DatabaseException;
	void updateAdLinkDataList(List<AdLinkDataVO> list, String statDate) throws DatabaseException;
}
