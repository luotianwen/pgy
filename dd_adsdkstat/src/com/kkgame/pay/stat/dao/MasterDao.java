package com.kkgame.pay.stat.dao;

import java.util.List;

import com.kkgame.pay.stat.util.DatabaseException;
import com.kkgame.pay.stat.bean.Data;
import com.kkgame.pay.stat.bean.UserData;

public interface MasterDao {

	void updateProjectUserData(UserData data) throws DatabaseException;

	void insertDaySendData(int i, List<Data> data) throws DatabaseException;

	void updateAdCpm(Data data) throws DatabaseException;
	  void insertLinkPv(int i,List<Data> list) throws DatabaseException;

	void insertDsPv(int i, List<Data> sendDataList)throws DatabaseException;

	void putDataToSubscribe(List<Data> datas);

	void putDataToOfferSdk(List<Data> datas);
}
